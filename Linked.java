
public class Linked {
    public static void main(String[] args) {
        // LinkedList<String> spies = new LinkedList<String>();
        // spies.add("James Bond");
        // spies.add("Ethan Hunt");
        // spies.add("Elizabeth Smith");
        // System.out.println(spies);

        // System.out.println(spies.remove());
        // System.out.println(spies.peekFirst());
        // // There is also peekLast(), pollFirst(), pollLast()

        // Playlist pop = new Playlist(new Track("Dancing Queen", "ABBA", 230, null));
        // System.err.println(pop);
        // pop.addEnd(new Track("Lovestory", "Taylor Swift", 250, null));
        // pop.addFirst(new Track("Billie Jean", "Michael Jackson", 210, null));
        // System.out.println(pop);
        // pop.add(new Track("Sorry", "Justin Bieber", 200, null), "Dancing Queen");

        // Playlist list1 = new Playlist(new Track("A", 1));
        // list1.add(new Track("B", 2), "A");
        // list1.add(new Track("C", 3), "B");

        // Playlist list2 = new Playlist(new Track("D", 4));
        // list2.add(new Track("E", 5), "D");
        // list2.add(new Track("F", 6), "E");

        // Merger.mergeSortedLists(list1.first, list2.first);

        // System.out.println(list1);
        // System.err.println("DONE");

        Box box1 = new Box(1, null);
        box1.next = new Box(3, null);
        box1.next.next = new Box(5, null);

        Box box2 = new Box(2, null);
        Box runner = box2;
        for (int i = 4; i < 10; i += 2) {
            runner.next = new Box(i, null);
            runner = runner.next;
        }
        // runner.next = box2; // MAKES A CYCLE
        System.err.println(box1);
        System.err.println(box2);

        Box zipped = zip(box1, box2);
        System.out.println(zipped);

        System.out.println("-------------------------------");
        System.out.println(isCycle(box2));
        System.out.println(getMiddle(zipped));

        LinkBox<Integer> LB = new LinkBox<>();
        LB.head = zipped;

        for (Integer b : LB) {
            System.out.println(b);
        }

        System.out.println(zipped);
        zipped = rotateBy(zipped, 2);
        System.out.println(zipped);

        StackList stk = new StackList();
        for (int i = 0; i < 4; i++) {
            stk.push(new Box<Integer>(i, null));
        }
        while (stk.size() > 0) {
            System.out.println(stk.pop());
        }

        System.out.println(zipped);
        swapNext2(zipped);
        System.out.println(zipped);
        // bubble(zipped);
        // System.out.println("Whitty" + zipped);

        Cue ball = new Cue();
        ball.encue(new Box(1, null));
        ball.encue(new Box(2, null));
        ball.encue(new Box(3, null));
        System.out.println(ball.size());
        System.out.println(ball.decue().data);
        System.out.println(ball.size());
    }

    public static Box zip(Box b1, Box b2) {
        if (b1 == null)
            return b2;
        if (b2 == null)
            return b1;

        Box output = b1.compareTo(b2) < 0 ? b1 : b2;
        if (output == b1) {
            b1 = b1.next;
        } else {
            b2 = b2.next;
        }
        Box runner = output;

        while (b1 != null && b2 != null) {
            if (b1.compareTo(b2) < 0) {
                runner.next = b1;
                b1 = b1.next;
            } else {
                runner.next = b2;
                b2 = b2.next;
            }
            runner = runner.next;
        }
        runner.next = b1 == null ? b2 : b1;
        return output;
    }

    public static boolean isCycle(Box box) {
        Box jackBlack = box;
        Box usainBolt = box;

        while (true) {
            if (jackBlack.next == null || usainBolt.next == null || usainBolt.next.next == null) {
                return false;
            }
            jackBlack = jackBlack.next;
            usainBolt = usainBolt.next.next;

            if (jackBlack == usainBolt) {
                return true;
            }
        }
    }

    public static Object getMiddle(Box b) {

        if (b == null) {
            return Integer.MIN_VALUE;
        }
        if (b.next == null) {
            return b.data;
        }

        Box jackBlack = b;
        Box usainBolt = b;

        while (usainBolt != null && usainBolt.next != null) {
            usainBolt = usainBolt.next.next;
            jackBlack = jackBlack.next;
        }

        return jackBlack.data;
    }

    public static Box rotateBy(Box head, int rotationAmount) {
        if (rotationAmount <= 0) {
            return head;
        }

        Box runner = head;
        Box severPoint = head;
        int counter = rotationAmount;
        while (runner.next != null) {
            counter--;
            if (counter == 0) {
                severPoint = runner;
            }
            runner = runner.next;
        }
        runner.next = head;
        head = severPoint.next;
        severPoint.next = null;

        return head;
    }

    public static void bubble(Box<Integer> head) {
        Box<Integer> cap = new Box<Integer>(Integer.MIN_VALUE, head);

        boolean hasSwapped = false;
        do {
            Box<Integer> runner = cap;
            while (runner != null) {
                if (runner.next != null && runner.next.next != null) {
                    if (runner.next.next.data < runner.next.data) {
                        swapNext2(runner);
                        hasSwapped = true;
                    }
                }
                runner = runner.next;
            }
        } while (hasSwapped);
    }

    public static boolean swapNext2(Box pre) {
        if (pre == null || pre.next == null || pre.next.next == null)
            return false;

        Box temp = pre.next;
        pre.next = pre.next.next;
        temp.next = pre.next.next;
        pre.next.next = temp;

        return true;
    }
}
