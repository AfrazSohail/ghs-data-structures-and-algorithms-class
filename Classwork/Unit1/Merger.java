
public class Merger {
    public static Track mergeSortedLists(Track l1, Track l2) {
        Track runner1 = l1;
        Track runner2 = l2;

        Track first = l1;
        Track mainRunner = l1;

        while (runner1.next != null || runner2.next != null) {
            if (runner2.next != null) {
                runner1 = runner1.next;
                mainRunner.next = runner2;
                mainRunner = mainRunner.next;
            }

            if (runner1.next != null) {
                runner2 = runner2.next;
                mainRunner.next = runner1;
                mainRunner = mainRunner.next;
            }

            System.out.println("R1: " + runner1);
            System.out.println("R2: " + runner2);
            System.out.println("MR: " + mainRunner);
            System.out.println("----");
        }

        return first;
    }
}
