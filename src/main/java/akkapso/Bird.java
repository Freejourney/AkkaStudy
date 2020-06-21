package akkapso;

import akka.actor.ActorSelection;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akkapso.msg.GBestMsg;
import akkapso.msg.PBestMsg;
import akkapso.msg.PsoValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ua28 on 6/21/20.
 */
public class Bird extends UntypedAbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private PsoValue pbest = null;
    private PsoValue gbest = null;
    private List<Double> velocity = new ArrayList<>(5);
    private List<Double> x = new ArrayList<>(5);
    private Random r = new Random();

    @Override
    public void preStart() throws Exception {
        for (int i = 0; i < 5; i++) {
            velocity.add(Double.NEGATIVE_INFINITY);
            x.add(Double.NEGATIVE_INFINITY);
        }

        x.set(1, (double)r.nextInt(401));

        double max = 400-1.1*x.get(1);
        if (max < 0) max = 0;
        x.set(2, r.nextDouble()*max);

        max = 484-1.21*x.get(1)-1.1*x.get(2);
        if (max <= 0) max = 0;
        x.set(3, r.nextDouble()*max);

        max = 532.4-1.331*x.get(1)-1.21*x.get(2)-1.1*x.get(3);
        if (max <= 0) max = 0;
        x.set(4, r.nextDouble()*max);

        /***
         *  masterbird is a center
         */
        double fitness = Fitness.fitness(x);
        pbest = new PsoValue(fitness, x);
        PBestMsg pBestMsg = new PBestMsg(pbest);

        ActorSelection selection = getContext().actorSelection("/user/masterbird");
        selection.tell(pBestMsg, getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        /***
         * msg-oriented => instanceof
         */
        if (message instanceof GBestMsg) {
            gbest = ((GBestMsg) message).getValue();
            // update speed
            for (int i = 1; i < velocity.size(); i++) {
                updateVelocity(i);
            }
            for (int i = 1; i < x.size(); i++) {
                updateX(i);
            }

            validateX();

            double fitness = Fitness.fitness(x);
            if (fitness > pbest.getValue()) {
                pbest = new PsoValue(fitness, x);
                PBestMsg pBestMsg = new PBestMsg(pbest);
                getSender().tell(pBestMsg, getSelf());
            }
        } else
            unhandled(message);
    }

    public double updateVelocity(int i) {
        double v = Math.random()*velocity.get(i)
                +2*Math.random()*(pbest.getX().get(i)-x.get(i))
                +2*Math.random()*(gbest.getX().get(i)-x.get(i));
        v = v > 0 ? Math.min(v, 5):Math.max(v, -5);
        velocity.set(i, v);
        return v;
    }

    public double updateX(int i) {
        double newX = x.get(i)+velocity.get(i);
        x.set(i, newX);
        return newX;
    }

    public void validateX() {
        if (x.get(1) > 400) {
            x.set(1, (double)r.nextInt(401));
        }

        double max = 400-1.1*x.get(1);
        if (x.get(2) > max || x.get(2) < 0) {
            x.set(2, r.nextDouble()*max);
        }

        max = 484-1.21*x.get(1)-1.1*x.get(2);
        if (x.get(3) > max || x.get(3) < 0) {
            x.set(3, r.nextDouble()*max);
        }

        max = 532.4-1.331*x.get(1)-1.21*x.get(2)-1.1*x.get(3);
        if (x.get(4) > max || x.get(4) < 0) {
            x.set(4, r.nextDouble()*max);
        }
    }
}
