package bmstu.iu9.lab4;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import akka.http.scaladsl.model.HttpRequest;
import akka.http.scaladsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;

public class Server {

    private Server(final ActorSystem system) {

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
                get(() -> parameter("packageId")
        );
    }

}
