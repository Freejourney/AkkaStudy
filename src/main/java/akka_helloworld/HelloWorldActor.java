package akka_helloworld;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;


public class HelloWorldActor extends UntypedAbstractActor{
    ActorRef greeterActorRef;

    @Override
    public void preStart() throws Exception {
        greeterActorRef = getContext().actorOf(Props.create(GreeterActor.class), "greeterActor");
        System.out.println("GreeterActor Path : " + greeterActorRef.path());
        greeterActorRef.tell(GreeterActor.Msg.GREET, getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == GreeterActor.Msg.DONE) {
            greeterActorRef.tell(GreeterActor.Msg.GREET, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}
