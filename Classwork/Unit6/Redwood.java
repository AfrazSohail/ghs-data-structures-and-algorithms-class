package Classwork.Unit6;

public class Redwood {
    public static void main(String args[]) {

        Tree maple1 = new Tree();
        Tree maple2 = new Tree();
        for (int i = 0; i < (int) (Math.random() * 20); i++) {
            AVLNode temp1 = new AVLNode((int) (Math.random() * 20), (char) (65 + i) + "");
            AVLNode temp2 = new AVLNode(temp1.key, temp1.value);
            System.out.print(temp1);
            maple1.addNode(temp1);
            maple2.addNode(temp2);
        }
        System.out.println();
        System.out.println(maple1.inOrder());
        System.out.println(maple1.preOrder());
        System.out.println("Size: " + maple1.size());
        System.out.println(maple1.equals(maple2));
        maple1.addNode(new AVLNode(5, "@"));
        System.out.println(maple1.equals(maple2));
        System.out.print(maple1.get(5));

        // Tree oak = new Tree();
        // int[] arr = { 3, 1, 4, 1, 5, 9, 2, 6, 11, 10, 12 };
        // for (int i : arr)
        // oak.addNode(new TNode(i, (char) (i + 65) + ""));
        // System.out.println(oak.getDepth(new TNode(69, "A")));
        // System.out.println(oak.root.left.getHeight());
        // System.out.println(oak.root.right.getHeight());
        // System.out.println(oak.inOrder());
        // oak.root = oak.delete(9);
        // System.out.println(oak.inOrder());
        // oak.root = oak.delete(9);
        // System.out.println(oak.inOrder());

        System.out.println(maple1.inOrder());
        maple1.inverse(maple1.root);
        System.out.println(maple1.inOrder());

        System.out.println();

        System.out.println(maple2.preOrder());
        System.out.println(maple2.edgeOrder());
    }
}
