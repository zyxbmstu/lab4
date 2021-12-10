package bmstu.iu9.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import bmstu.iu9.lab4.message.GetMessage;
import bmstu.iu9.lab4.message.PackageActor;
import bmstu.iu9.lab4.message.PackageMessage;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import scala.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

public class Server {

    private ActorRef storageActor;
    private ActorRef packageActor;
    private ActorRef executeActor;

    private Server(ActorSystem system) {
        storageActor = system.actorOf(Props.create(StorageActor.class), "storageActor");
        packageActor = system.actorOf(Props.create(PackageActor.class), "packageActor");
        executeActor = system.actorOf(new RoundRobinPool(5).props(Props.create(ExecuteActor.class)), "executeActor");
    }

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        Server app = new Server(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );

        System.out.println("Server started!");
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
        System.out.println("Server stopped!");

    }

    private Route createRoute() {
        return route(
                get(() -> parameter("packageId", (packageId) -> {
                    Future<Object> result = Patterns.ask(storageActor, new GetMessage(Integer.parseInt(packageId)), 5000);
                    return completeOKWithFuture(result, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(PackageMessage.class), msg -> {
                    PackageActor.tell(msg, ActorRef.noSender());
                    return complete("OK!");
                }))
        );
    }

}
