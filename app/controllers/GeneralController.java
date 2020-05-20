package controllers;

import handlers.QuotesErrorHandler;
import models.Quote;
import models.QuoteRepository;
import org.pac4j.play.PlayWebContext;
import org.pac4j.play.store.PlaySessionStore;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.AuthService;
import services.ExternalAPIService;
import services.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class GeneralController extends Controller {


    private final QuoteRepository quoteRepository;
    private final HttpExecutionContext ec;
    private final AuthService authService;
    private final PlaySessionStore playSessionStore;
    private final ModelMapper modelMapper;
    private final QuotesErrorHandler quotesErrorHandler;
    private final ExternalAPIService externalAPIService;


    @Inject
    public GeneralController(QuoteRepository quoteRepository, HttpExecutionContext ec, AuthService authService, PlaySessionStore playSessionStore, ModelMapper modelMapper, QuotesErrorHandler quotesErrorHandler, ExternalAPIService externalAPIService) {
        this.quoteRepository = quoteRepository;
        this.ec = ec;
        this.authService = authService;
        this.playSessionStore = playSessionStore;
        this.modelMapper = modelMapper;
        this.quotesErrorHandler = quotesErrorHandler;
        this.externalAPIService = externalAPIService;
    }


    public CompletionStage<Result> getQuote(final Http.Request request, Long id) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            return quoteRepository.getQuote(id)
                    .thenApplyAsync(e -> ok(this.modelMapper.QuoteToJson(e)));
        }
        return CompletableFuture.completedFuture(forbidden());
    }


    public CompletionStage<Result> addQuote(final Http.Request request) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            Quote quote = this.modelMapper.transformToQuote(request.body().asJson());
            this.externalAPIService.populateDBWithExtraQuotes(quote);
            return quoteRepository
                    .add(quote)
                    .thenApplyAsync(e -> ok(this.modelMapper.QuoteToJson(e)));
        }
        return CompletableFuture.completedFuture(forbidden());

    }

    public CompletionStage<Result> deleteQuote(final Http.Request request, final Long id) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            return quoteRepository
                    .delete(id)
                    .thenApplyAsync(this::deleteHandling);
        }
        return CompletableFuture.completedFuture(forbidden());

    }

    public CompletionStage<Result> getAllQuotes(final Http.Request request) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            return quoteRepository.list()
                    .thenApplyAsync(e -> {
                        List<Quote> listOfQuotes = e.collect(Collectors.toList());
                        return ok(Json.toJson(listOfQuotes));
                    });
        }
        return CompletableFuture.completedFuture(forbidden());
    }


    public Result login(final Http.Request request) {
        final PlayWebContext context = new PlayWebContext(request, playSessionStore);
        return this.authService.login(request, context);
    }


    private Result deleteHandling(Integer e) {
        if (e.equals(1)) {
            return ok();
        } else {
            return notFound();
        }
    }


}
