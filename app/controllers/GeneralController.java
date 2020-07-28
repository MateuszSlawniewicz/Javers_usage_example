package controllers;


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
    private final ExternalAPIService externalAPIService;


    @Inject
    public GeneralController(QuoteRepository quoteRepository, HttpExecutionContext ec, AuthService authService, PlaySessionStore playSessionStore, ModelMapper modelMapper, ExternalAPIService externalAPIService) {
        this.quoteRepository = quoteRepository;
        this.ec = ec;
        this.authService = authService;
        this.playSessionStore = playSessionStore;
        this.modelMapper = modelMapper;
        this.externalAPIService = externalAPIService;
    }

    public Result corsOk(final Http.Request request) {
        System.out.println("prepare CORS");
        System.out.println(request.method());
        return wrapCorseHeaders(ok());
    }


    public Result corsOk2(final Http.Request request, Long id) {
        System.out.println("prepare CORS");
        System.out.println(request.method());
        return wrapCorseHeaders(ok());
    }


    public CompletionStage<Result> getQuote(final Http.Request request, Long id) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            return quoteRepository.getQuote(id)
                    .thenApplyAsync(e -> wrapCorseHeaders(ok(this.modelMapper.QuoteToJson(e))));
        }
        return CompletableFuture.completedFuture(wrapCorseHeaders(forbidden()));
    }


    public CompletionStage<Result> addQuote(final Http.Request request) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            Quote quote = this.modelMapper.transformToQuote(request.body().asJson());
            this.externalAPIService.populateDBWithExtraQuotes(quote);
            return quoteRepository
                    .add(quote)
                    .thenApplyAsync(e -> wrapCorseHeaders(ok(this.modelMapper.QuoteToJson(e))));
        }
        return CompletableFuture.completedFuture(wrapCorseHeaders(forbidden()));

    }

    public CompletionStage<Result> deleteQuote(final Http.Request request, final Long id) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            return quoteRepository
                    .delete(id)
                    .thenApplyAsync(this::deleteHandling);
        }
        return CompletableFuture.completedFuture(wrapCorseHeaders(forbidden()));

    }

    public CompletionStage<Result> getAllQuotes(final Http.Request request) {
        Optional<String> optionalToken = request.getHeaders().get("token");
        if (this.authService.jwtValidation(optionalToken)) {
            return quoteRepository.list()
                    .thenApplyAsync(e -> {
                        List<Quote> listOfQuotes = e.collect(Collectors.toList());
                        return wrapCorseHeaders(ok(Json.toJson(listOfQuotes)));
                    });
        }
        return CompletableFuture.completedFuture(wrapCorseHeaders(forbidden()));
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

    private Result wrapCorseHeaders(Result result) {
        return result.withHeader("Access-Control-Allow-Origin", "*")
                .withHeader("Access-Control-Allow-Headers", "*")
                .withHeader("Access-Control-Allow-Credentials", "true")
                .withHeader("Access-Control-Allow-Methods", "*");

    }


}
