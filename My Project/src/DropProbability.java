import java.util.ArrayList;

public class DropProbability {

    public static void main(String[] args) {
        printDropProb(250, 250);
    }


    private static void printDropProb(int rarity, int killAmount){
        for(int i = 0; i <= killAmount; i++) {
            double probability = 1.0 - Math.pow((rarity - 1.0) / rarity, Double.parseDouble(Integer.toString(i)));
            System.out.println(String.format("In %4d kills item drop probability is %.2f%s", i, probability * 100.0, "%"));
        }
    } 
}
