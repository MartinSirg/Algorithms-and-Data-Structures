import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answer {

    public static void main (String[] args) {
        System.out.println(result(new double[]{0., 1., 2., 3., 4.}));
        System.out.println(result(new double[]{100., 1., 40., 60.})); // result 50
    }

    public static double result (double[] marks) {
        //boxed() -> tagastab stream objekti, kus kõik elemendid on kapseldatud Objekti(int -> Integer)
        List<Double> doubleList = Arrays.stream(marks).boxed().collect(Collectors.toList());
        doubleList.remove(Collections.max(doubleList));
        doubleList.remove(Collections.min(doubleList));

        return doubleList.stream().collect(Collectors.averagingDouble(Double::doubleValue));
    }

}
