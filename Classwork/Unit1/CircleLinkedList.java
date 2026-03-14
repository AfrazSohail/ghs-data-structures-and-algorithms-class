// public class CircleLinkedList {
// public static void main(String args[]) {
// Box<Integer> L1 = new Box(10, null);
// Box crun = L1;
// for (int i = 1; i < 4; i++) {
// crun.next = new Box(10 + i, null);
// crun = crun.next;
// }
// Box<Integer> L2 = new Box(20, null);
// crun = L2;
// for (int i = 5; i < 10; i++) {
// crun.next = new Box(20 + i, null);
// crun = crun.next;
// }
// System.out.println(L1);
// System.out.println(L2);

// L1 = encircle(L1, L2);
// crun = L1;
// while (crun.next != L1) {
// System.out.println(crun.data);
// crun = crun.next;
// }
// }

// public static <T> Box<T> encircle(Box<T> head1, Box<T> head2) {
// if (head1 == null || head2 == null) {
// return null;
// }
// Box<T> tail1 = head1;
// while (tail1.next != null) {
// tail1 = tail1.next;
// }
// tail1.next = head2;

// Box<T> tail2 = head2;
// while (tail2.next != null) {
// tail2 = tail2.next;
// }
// tail2.next = head1;

// return head1;
// }
// }
