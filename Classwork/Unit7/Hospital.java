package Classwork.Unit7;

import java.util.ArrayList;
import java.util.Arrays;

public class Hospital {

    public static void main(String[] args) {
        Injury headache = new Injury("Headache", 2);
        Injury GSW = new Injury("GSW", 10);
        Injury bone = new Injury("Broken Bone", 5);
        Triage Tri = new Triage(new ArrayList<Injury>(Arrays.asList(headache)));
        Tri.newPatient(GSW);
        Tri.newPatient(bone);
        System.out.println(Tri);
        for (int i = 0; i < 4; i++) {
            Tri.newPatient(new Injury("Injury" + (i + 1) * 2, (i + 1) * 2));
        }
        System.out.println(Tri.injuries);
        System.out.println(Tri);
        System.out.println("Treat" + Tri.treat());
        System.out.println(Tri);

        while (Tri.nextPatient() != null)
            System.out.println(Tri.treat());
    }

}
