package Classwork.Unit6;

public class AVLNode {
    AVLNode left;
    AVLNode right;
    int key;
    String value;

    public AVLNode(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public AVLNode(AVLNode left, AVLNode right, int key, String value) {
        this.left = left;
        this.right = right;
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + ": " + value + "\t";
    }

    public AVLNode addNode(AVLNode newNode) {
        if (newNode.key > key) {
            if (right == null)
                right = newNode;
            else
                right = right.addNode(newNode);
        } else if (newNode.key < key) {
            if (left == null)
                left = newNode;
            else
                left = left.addNode(newNode);
        } else {
            this.value = newNode.value;
        }

        int bf = this.getBalance();
        if (bf > 1 && this.left.getBalance() > 0) {
            // left-left case
            return this.rotateRight();
        } else if (bf > -1 && this.right.getBalance() < 0) {
            // right-right case
            return this.rotateLeft();
        } else if (bf > 1) {
            // left-right case
            this.left = this.left.rotateLeft();
            return this.rotateRight();
        } else if (bf < -1) {
            // right-left case
            this.right = this.right.rotateRight();
            return this.rotateLeft();
        }
        return this;
    }

    public int getHeight() {
        if (left == null && right == null)
            return 0;
        if (left == null)
            return 1 + right.getHeight();
        if (right == null)
            return 1 + left.getHeight();
        return 1 + Math.max(right.getHeight(), left.getHeight());
    }

    public AVLNode rotateRight() {
        AVLNode newRoot = this.left;
        this.left = newRoot.right;
        newRoot.right = this;
        return newRoot;
    }

    public AVLNode rotateLeft() {
        AVLNode newRoot = this.right;
        this.right = newRoot.left;
        newRoot.left = this;
        return newRoot;
    }

    public int getBalance() {
        int L = this.left == null ? -1 : this.left.getHeight();
        int R = this.right == null ? -1 : this.right.getHeight();
        return L - R;
    }
}
