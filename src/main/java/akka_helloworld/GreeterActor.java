package akka_helloworld;

import akka.actor.UntypedAbstractActor;



public class GreeterActor extends UntypedAbstractActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.GREET) {
            System.out.println("Hello World");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(message);
        }
    }
}
