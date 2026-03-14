package Classwork.Unit6;

public class Tree {
    AVLNode root;

    public Tree() {

    }

    public Tree(AVLNode root) {
        this.root = root;
    }

    public void addNode(AVLNode newNode) {
        if (newNode == null)
            return;
        if (root == null)
            root = newNode;
        else
            root.addNode(newNode);
    }

    public AVLNode delete(int key) {
        return delete(key, root);
    }

    private AVLNode delete(int key, AVLNode rootRunner) {
        if (rootRunner == null)
            return null;
        if (key < rootRunner.key)
            rootRunner.left = delete(key, rootRunner.left);
        else if (key > rootRunner.key)
            rootRunner.right = delete(key, rootRunner.right);
        else { // CASE I: Childless
            System.out.println("am i childless?!");
            if (rootRunner.left == null && rootRunner.right == null)
                return null;
            else if (rootRunner.left == null)
                return rootRunner.right;
            else if (rootRunner.right == null)
                return rootRunner.left;
            else {
                AVLNode replacement = findMin(rootRunner.right);
                rootRunner.value = replacement.value;
                rootRunner.key = replacement.key;
                rootRunner.right = delete(replacement.key, rootRunner.right);
            }
        }
        return rootRunner;
    }

    public AVLNode findMin(AVLNode rootRunner) {
        if (rootRunner.left == null)
            return rootRunner;
        return findMin(rootRunner.left);
    }

    public String inOrder() {
        if (root == null)
            return "null";
        return inOrder(root);
    }

    private String inOrder(AVLNode rootRunner) {
        if (rootRunner == null)
            return "";
        return inOrder(rootRunner.left) + rootRunner + inOrder(rootRunner.right);
    }

    public String preOrder() {
        if (root == null)
            return "null";
        return preOrder(root);
    }

    private String preOrder(AVLNode rootRunner) {
        if (rootRunner == null)
            return "";
        return rootRunner + preOrder(rootRunner.left) + preOrder(rootRunner.right);
    }

    public int size() {
        if (root == null)
            return 0;
        return size(root);
    }

    private int size(AVLNode rootRunner) {
        if (rootRunner == null)
            return 0;
        return size(rootRunner.left) + 1 + size(rootRunner.right);
    }

    public boolean equals(Tree otherTree) {
        return equals(root, otherTree.root);
    }

    private boolean equals(AVLNode root, AVLNode otherRoot) {
        if (root == null && otherRoot == null)
            return true;
        if (root == null || otherRoot == null)
            return false;
        if (root.key != otherRoot.key || !root.value.equals(otherRoot.value))
            return false;
        return equals(root.left, otherRoot.left) && equals(root.right, otherRoot.right);
    }

    public String get(int key) {
        return get(root, key);
    }

    private String get(AVLNode rootRunner, int key) {
        if (rootRunner == null)
            return null;
        if (key == rootRunner.key)
            return rootRunner.value;
        if (key < rootRunner.key)
            return get(rootRunner.left, key);
        return get(rootRunner.right, key);
    }

    public int getDepth(AVLNode node) {
        if (node == null)
            return -1;
        return getDepth(node, root);
    }

    private int getDepth(AVLNode node, AVLNode rootRunner) {
        if (rootRunner == null)
            return Integer.MIN_VALUE;
        if (node.key == rootRunner.key)
            return 0;
        if (node.key < rootRunner.key)
            return 1 + getDepth(node, rootRunner.left);
        return 1 + getDepth(node, rootRunner.right);
    }

    public int getHeight() {
        return root.getHeight();
    }

    public void inverse(AVLNode node) {
        if (node == null)
            return;
        inverse(node.right);
        inverse(node.left);

        AVLNode temp = node.right;
        node.right = node.left;
        node.left = temp;
    }

    public String edgeOrder() {
        if (root == null)
            return "null";
        return edgeLeft(root.left) + root + edgeRight(root.right);
    }

    private String edgeLeft(AVLNode rootRunner) {
        if (rootRunner == null)
            return "";
        return edgeLeft(rootRunner.left) + rootRunner;
    }

    private String edgeRight(AVLNode rootRunner) {
        if (rootRunner == null)
            return "";
        return rootRunner + edgeRight(rootRunner.right);
    }

    public boolean hasSum(int sum) {
        return hasSum(root, sum);
    }

    private boolean hasSum(AVLNode rootRun, int sum) {
        if (rootRun == null)
            return (sum == 0);

        return hasSum(rootRun.left, sum - Integer.parseInt(rootRun.value))
                || hasSum(rootRun.right, sum - Integer.parseInt(rootRun.value));
    }

    public void makeClone(AVLNode rootRun) {
        if (rootRun == null)
            return;

        AVLNode clone = new AVLNode(rootRun.key, rootRun.value);
        clone.left = rootRun.left;
        rootRun.left = clone;

        makeClone(rootRun.left.left);
        makeClone(rootRun.right);
    }
}
