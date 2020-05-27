package akka_helloworld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;


public class HelloMainSimple {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("HelloActorSystem", ConfigFactory.load("samplehello.conf"));

        ActorRef actorRef = system.actorOf(Props.create(HelloWorldActor.class), "helloworldActor");
        System.out.println("HelloWorld Actor Path: " + actorRef.path());

        system.terminate();
    }
}
