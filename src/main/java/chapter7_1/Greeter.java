package chapter7_1;

import akka.actor.UntypedAbstractActor;

/**
 * Created by ua28 on 6/19/20.
 */
public class Greeter extends UntypedAbstractActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());

            getSender().tell(Msg.GREET, getSelf());
        } else
            unhandled(message);
    }

}
