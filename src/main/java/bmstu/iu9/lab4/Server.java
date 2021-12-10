package bmstu.iu9.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.http.scaladsl.model.HttpRequest;
import akka.http.scaladsl.model.HttpResponse;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import bmstu.iu9.lab4.message.GetMessage;
import bmstu.iu9.lab4.message.PackageMessage;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.CompletionStage;

public class Server {

    private final ActorRef router;

    private Server(ActorRef router) {
        this.router = router;
    }

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        Server app = new Server(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.create
    }

    private Route createRoute() {
        return route(
                get(() -> parameter("packageId", (packageId) -> {
                    Future<Object> result = Patterns.ask(router, new GetMessage(Integer.parseInt(packageId)), 5000);
                    return completeOKWithFuture(result, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(PackageMessage.class), msg -> {
                    
                }))
        );
    }

}
