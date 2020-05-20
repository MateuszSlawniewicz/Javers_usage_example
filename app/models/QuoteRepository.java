
package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

@ImplementedBy(JPAQuoteRepository.class)
public interface QuoteRepository {

    CompletionStage<Quote> add(Quote quote);

    CompletionStage<Stream<Quote>> list();

    CompletionStage<Integer> delete(Long id);


    CompletionStage<Quote> getQuote(Long id);

}

