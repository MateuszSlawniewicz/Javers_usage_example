package services;

import models.JPAQuoteRepository;
import models.Quote;
import models.QuoteDTO;
import models.ResultModel;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class    ExternalAPIService {
    private static final String API_KEY_TOKEN = " ";
    private static final String API_URL = "https://api.paperquotes.com/";
    private final WSClient ws;
    private final ModelMapper modelMapper;
    private final JPAQuoteRepository jpaQuoteRepository;

    @Inject
    public ExternalAPIService(WSClient ws, ModelMapper modelMapper, JPAQuoteRepository jpaQuoteRepository) {
        this.ws = ws;
        this.modelMapper = modelMapper;
        this.jpaQuoteRepository = jpaQuoteRepository;
    }

    public void populateDBWithExtraQuotes(Quote quote) {
        List<Quote> quotes = Collections.emptyList();
        WSRequest complexRequest = customizeRequestUrl(quote);
        CompletionStage<List<Quote>> listCompletionStage = complexRequest.get()
                .thenApply(
                        response -> {
                            if (response.getStatus() == 401) {
                                return Collections.emptyList();
                            }
                            if (response.getStatus() == 200) {
                                return handlingResponseOK(quote, response);
                            }
                            return Collections.emptyList();
                        }
                );
        try {
            quotes = listCompletionStage.toCompletableFuture().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        quotes.forEach(jpaQuoteRepository::add);
    }

    private WSRequest customizeRequestUrl(Quote quote) {
        return ws.url(API_URL)
                .addHeader("Authorization", API_KEY_TOKEN)
                .addQueryParameter("tags", quote.getKeyWord());
    }

    private List<Quote> handlingResponseOK(Quote quote, WSResponse response) {
        ResultModel resultModel = this.modelMapper.JsonToResultModel(response);
        List<QuoteDTO> results = resultModel.getResults();
        return results.stream()
                .map(q -> {
                    Quote temp = new Quote();
                    temp.setContent(q.getQuote());
                    temp.setKeyWord(quote.getKeyWord());
                    return temp;
                }).collect(Collectors.toList());
    }
}







