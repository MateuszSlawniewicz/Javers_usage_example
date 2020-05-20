package models;

import play.db.jpa.JPAApi;
import services.ExternalLogInfoService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class JPAQuoteRepository implements QuoteRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;
    private final ExternalLogInfoService externalLogInfoService;
    private static final String DELETE_QUERY = "delete from Quote q where q.id = :id";
    private static final String LIST_QUERY = "select q from Quote q";
    private static final String GET_QUOTE_QUERY = "select q from Quote q where q.id = :id";

    @Inject
    public JPAQuoteRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext, ExternalLogInfoService externalLogInfoService) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.externalLogInfoService = externalLogInfoService;
    }

    @Override
    public CompletionStage<Quote> add(Quote quote) {
        this.externalLogInfoService.senLogInfo(quote);
        return supplyAsync(() -> wrap(entityManager -> insert(entityManager, quote)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Quote>> list() {
        return supplyAsync(() -> wrap(this::list), executionContext);
    }

    @Override
    public CompletionStage<Integer> delete(Long id) {
        return supplyAsync(() -> wrap(entityManager -> delete(entityManager, id)), executionContext);
    }

    @Override
    public CompletionStage<Quote> getQuote(Long id) {
        return supplyAsync(() -> wrap(entityManager -> getOneQuote(entityManager, id)), executionContext);
    }


    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Quote getOneQuote(EntityManager entityManager, Long id) {
        TypedQuery<Quote> query = entityManager.createQuery(GET_QUOTE_QUERY, Quote.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    private Quote insert(EntityManager entityManager, Quote quote) {
        entityManager.persist(quote);
        return quote;
    }

    private Integer delete(EntityManager entityManager, Long id) {
        Query query = entityManager.createQuery(DELETE_QUERY, Quote.class);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    private Stream<Quote> list(EntityManager entityManager) {
        List<Quote> quotes = entityManager.createQuery(LIST_QUERY, Quote.class).getResultList();
        return quotes.stream();
    }


}
