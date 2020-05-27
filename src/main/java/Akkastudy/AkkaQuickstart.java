package Akkastudy;

import akka.actor.typed.ActorSystem;

import java.io.IOException;

/**
 * Created by ua28 on 5/27/20.
 */
public class AkkaQuickstart {
    public static void main(String[] args) {
        //#actor-system
        final ActorSystem<GreeterMain.SayHello> greeterMain = ActorSystem.create(GreeterMain.create(), "helloakka");
        //#actor-system

        //#main-send-messages
        greeterMain.tell(new GreeterMain.SayHello("Charles"));
        //#main-send-messages

        /**
         *  avoid too early terminate
          */
        try {
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ignored) {
        } finally {
            greeterMain.terminate();
        }
    }
}
