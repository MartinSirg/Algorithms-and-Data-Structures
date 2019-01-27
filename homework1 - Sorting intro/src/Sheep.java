import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sheep {

    enum Animal {sheep, goat}
   
    public static void main (String[] param) {
        // for debugging
        Animal[] animals = new Animal[]{Animal.sheep, Animal.goat, Animal.sheep, Animal.goat, Animal.sheep, Animal.goat, Animal.sheep, Animal.goat};
        for(Animal animal: animals) {
            System.out.print(animal.toString() + ", ");
        }
        System.out.println();
        reorder(animals);
        for(Animal animal: animals) {
            System.out.print(animal.toString() + ", ");
        }
    }

    public static void reorder (Animal[] animals) {
        int lastAvailableIndex = animals.length - 1, currentIndex = 0;

        while(currentIndex <= lastAvailableIndex) {
            if(animals[currentIndex] == Animal.sheep) {
                animals[currentIndex] = animals[lastAvailableIndex];
                animals[lastAvailableIndex--] = Animal.sheep;
            }

            if(animals[currentIndex] == Animal.goat) {
                currentIndex++;
            }
        }
    }
}