package chapter7_1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * Created by ua28 on 6/19/20.
 */
public class GreeterMainSimple {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));

        ActorRef actor = system.actorOf(Props.create(Greeter2.class), "Greeter2");

        System.out.println("Greeter2 Actor Path: " + actor.path());
    }
}
