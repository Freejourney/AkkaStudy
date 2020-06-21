package akkapso;

import java.util.List;

/**
 * Created by ua28 on 6/21/20.
 */
public class Fitness {

    public static double fitness(List<Double> x) {
        double sum = 0;
        for (int i = 0; i < x.size(); i++) {
            sum += Math.sqrt(x.get(i));
        }
        return sum;
    }
}
