package Classwork.Unit3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import Classwork.Unit3.Foontry.Cuisine;
import Classwork.Unit3.Foontry.Entree;
import Classwork.Unit3.Foontry.Foontry;
import Classwork.Unit3.Foontry.Menu;

public class Hashcodes {
    public static void main(String args[]) throws FileNotFoundException {
        // Map<String, Integer> calories = new HashMap<String, Integer>();
        // calories.put("Apple", 95);
        // calories.put("Cucumber", 45);
        // calories.put("Banana", 105);

        // System.out.println(calories.get("Apple"));
        // for (String key : calories.keySet()) {
        // System.out.println(key + " : " + calories.get(key));
        // }
        Scanner GordamRamsay = new Scanner(new File("Classwork/Unit3/Foontry/FoodList.txt"));
        Menu Bertuccis = new Menu();
        GordamRamsay.nextLine();
        for (int i = 0; i < 100; i++) {
            String line = GordamRamsay.nextLine();
            String[] columns = line.split(",");
            Foontry key = new Foontry(columns[0], columns[1]);
            HashSet<String> dishes = new HashSet<String>();
            System.out.println(line);
            for (int j = 2; j < 6; j++)
                dishes.add(columns[j]);
            Cuisine value = new Cuisine(dishes);
            Bertuccis.add(new Entree(key, value));
        }

        System.out.println(Bertuccis);
        System.out.println(Bertuccis.size);
        System.out.println(Bertuccis.eat(new Foontry("Egg", "Greece")));
        System.out.println(Bertuccis);
        Bertuccis.get(new Foontry("Wheat", "Mexico")).dishes.remove("Marquesote Cake");

        GordamRamsay.close();
    }
}
