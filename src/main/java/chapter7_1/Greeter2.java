package chapter7_1;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * Created by ua28 on 6/19/20.
 */
public class Greeter2 extends UntypedAbstractActor {

    ActorRef greeter;

    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path:" + greeter.path());
        greeter.tell(Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.DONE) {
            greeter.tell(Msg.GREET, getSelf());
            getContext().stop(getSelf());
        } else
            unhandled(message);
    }
}

