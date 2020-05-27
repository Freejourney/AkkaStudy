package akka_helloworld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/*
 * 程序的执行流程如下：
 *      1. 在该main方法中创建ActorSystem。ActorSystem是一个用来管理和维护Actor的系统，
 *         ActorSystem的名字对应于一个目录，在这里是HelloActorSystem, 在这个目录下又对
 *         应着更细小的目录。
 *               如这里的HelloWorldActor目录为：akka://HelloActorSystem/user/helloworldActor/
 *      2. 然后创建了Actor引用，实例化了HelloWorldActor对象并给其取名helloworldActor
 *      3. 在helloworldActor启动之前会执行它的preStart方法。在preStart()方法中又创建了GreeterActor
 *         对象，并向其发送了消息。
 *      4. GreeterActor收到消息后执行Receive()方法，在方法体中又发送消息回发来消息的Actor
 *      5. helloworldActor收到消息后再次向GreeterActor发送消息并stop掉了自己
 */
public class HelloMainSimple {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("HelloActorSystem", ConfigFactory.load("samplehello.conf"));

        ActorRef actorRef = system.actorOf(Props.create(HelloWorldActor.class), "helloworldActor");
        System.out.println("HelloWorld Actor Path: " + actorRef.path());

        system.terminate();
    }
}
