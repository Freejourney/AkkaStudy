package akka_helloworld;

import akka.actor.UntypedAbstractActor;


/*
 * Akka中的Actor基于收到的消息来运作。
 * 简单来说，Actor就好像处于一种被动的状态，需要收到别的Actor
 * 发来的消息它才工作，它在工作的时候也可以给其它的Actor发送消息。
 */
public class GreeterActor extends UntypedAbstractActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.GREET) {
            System.out.println("Hello World");
            getSender().tell(Msg.DONE, getSelf());  // tell()将消息发送给发来消息的Actor，消息的类型是Object类型
        } else {
            unhandled(message);
        }
    }
}
