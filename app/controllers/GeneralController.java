package controllers;


import models.Car;
import models.CarRepository;
import org.javers.core.Changes;
import org.javers.repository.jql.QueryBuilder;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.JaversConfiguration;


import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
public class GeneralController extends Controller {


    private final HttpExecutionContext ec;
    private final CarRepository carRepository;
    private final JaversConfiguration javersConfiguration;

    @Inject
    public GeneralController(HttpExecutionContext ec, CarRepository carRepository, JaversConfiguration javersConfiguration) {
        this.ec = ec;
        this.carRepository = carRepository;
        this.javersConfiguration = javersConfiguration;
    }

    public CompletionStage<Result> getCar(final Http.Request request, final Long id) {
        return carRepository.getCar(id).thenApply(car -> {
           return ok(Json.toJson(car));
        });
    }

    public CompletionStage<Result> addCar(final Http.Request request) {
        Car car = Json.fromJson(request.body().asJson(), Car.class);
        return carRepository.add(car).thenApply(carSaved -> {
           return ok(Json.toJson(carSaved));
        });
    }

    public CompletionStage<Result> updateCar(final Http.Request request,  final Long id) {
        Car car = Json.fromJson(request.body().asJson(), Car.class);
        return carRepository.updateCar(car).thenApply(carSaved -> {
            return ok(Json.toJson(carSaved));
        });
    }

    public CompletionStage<Result> deleteCar(final Http.Request request, final Long id) {
        return carRepository.delete(id)
                .thenApply(it -> {
                    if(it>0) {
                        return ok("delete");
                    }
                    return badRequest("cant delete");
        });
    }

    public CompletionStage<Result> getAllCars(final Http.Request request) {
        return carRepository.list().thenApply(it->{
            return ok(Json.toJson(it));
        });
    }

    public CompletionStage<Result> getCarChanges(final Http.Request request) {
        Changes changes = javersConfiguration.getJavers().findChanges(QueryBuilder.byClass(Car.class).build());
        System.out.println(changes.size());
        return CompletableFuture.completedFuture(ok(Json.toJson(changes)));
    }

}
