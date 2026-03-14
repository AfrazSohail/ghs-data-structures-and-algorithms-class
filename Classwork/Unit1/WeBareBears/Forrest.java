package WeBareBears;

public class Forrest {

    public static void main(String args[]) {
        Den JBwilliams = new Den();
        for (int i = 0; i < 10; i++) {
            Bear b = new Bear("Bear" + i, (int) (Math.random() * 100 + 400), i);
            System.out.println(b);
            JBwilliams.PushABear(b);
        }
        System.out.println(JBwilliams);
        System.out.println(JBwilliams.PeakABear());
        System.out.println("Iterating Bears");
        for (Bear b : JBwilliams) {
            System.out.println(b);
        }
        while (JBwilliams.hasBears()) {
            System.out.println(JBwilliams.HuntABear());
        }
    }
}
