package CNN;
import java.lang.FunctionalInterface;

@FunctionalInterface
interface Equation {
    double resolve(double... inputs);
}
