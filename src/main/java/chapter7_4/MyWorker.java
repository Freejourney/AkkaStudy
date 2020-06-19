package chapter7_4;

import akka.actor.UntypedAbstractActor;
import chapter7_1.Msg;
import scala.Option;

/**
 * Created by ua28 on 6/19/20.
 */
public class MyWorker extends UntypedAbstractActor {

    MyWorker() {
        System.out.println("I'm in Constructor");
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("I'm in preStarting");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("I'm in preRestarting");
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        System.out.println("I'm in postRestarting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("I'm in PostStopping");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Msg.DONE) {

        }
    }
}
