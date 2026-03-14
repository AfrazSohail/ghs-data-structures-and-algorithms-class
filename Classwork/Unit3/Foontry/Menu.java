package Classwork.Unit3.Foontry;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    Entree[] menu;
    public int size;
    public static final double MAXLOAD = 0.75d;
    public static final Cuisine GARBAGE = new Cuisine(new HashSet<>(Set.of("Garbage")));

    public Menu() {
        menu = new Entree[10];
        size = 0;
    }

    public Cuisine add(Entree entree) {
        int index = entree.key.hashCode() % menu.length;

        do {
            if (menu[index] == null || menu[index].value.equals(GARBAGE)) {
                if (menu[index] == null)
                    size++;
                menu[index] = entree;
                if (1.0d * size / menu.length >= MAXLOAD) {
                    rehash();
                }
                return null;
            }
            if (menu[index].key.equals(entree.key)) {
                Cuisine temp = menu[index].value;
                menu[index].value = entree.value;
                return temp;
            }
            index = (index + 1) % menu.length;
        } while (true);
    }

    public void rehash() {
        Entree[] newEntrees = new Entree[menu.length * 2];
        Menu newMenu = new Menu();
        newMenu.menu = newEntrees;
        for (Entree entree : menu) {
            if (entree == null || entree.value.equals(GARBAGE))
                continue;
            newMenu.add(entree);
        }
        this.size = newMenu.size;
        this.menu = newEntrees;
        System.out.println("HASHY HASHY!!!");
    }

    public String toString() {
        String output = "";
        int count = 0;
        for (Entree entree : menu) {
            if (entree != null) {
                output += count++ + ": " + entree + "\n";
            }
        }
        return output;
    }

    public Cuisine eat(Foontry key) {
        if (key == null)
            return null;
        int index = key.hashCode() % menu.length;

        while (menu[index] != null) {
            if (menu[index].key.equals(key))
                if (menu[index].value.equals(GARBAGE))
                    return null;
                else {
                    Cuisine eaten = menu[index].value;
                    menu[index].value = GARBAGE;
                    return eaten;
                }
            index = (index + 1) % menu.length;
        }
        return null;
    }

    public Cuisine get(Foontry key) {
        if (key == null)
            return null;
        int index = key.hashCode() % menu.length;
        while (menu[index] != null) {
            if (menu[index].key.equals(key)) {
                return menu[index].value.equals(GARBAGE) ? null : menu[index].value;
            }
        }
        return null;
    }
}
