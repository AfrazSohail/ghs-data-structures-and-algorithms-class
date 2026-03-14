// public class StackList<T> {
// Box<T> head;

// public StackList() {
// head = null;
// }

// public Box<T> push(Box<T> newBox) {
// if (newBox == null) {
// return head;
// }
// if (head == null) {
// head = newBox;
// return head;
// }

// newBox.next = head;
// head = newBox;
// return head;
// }

// public Box<T> pop() {
// if (head == null) {
// return null;
// }
// Box<T> poppedBox = head;
// head = head.next;
// poppedBox.next = null;
// return poppedBox;
// }

// public Box<T> peek() {
// if (head == null) {
// return null;
// }
// return new Box<T>(head.data, head.next);
// }

// public int size() {
// int size = 0;
// Box<T> runner = head;
// while (runner != null) {
// size++;
// runner = runner.next;
// }
// return size;
// }
// }
