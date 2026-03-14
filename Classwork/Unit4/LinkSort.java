package Classwork.Unit4;

import java.util.Arrays;

//REVIEW COUNTING SORT AND RADIX SORT AND SLECT SORT PPTS, BASE SORT RADIX LSD 3 DIGIT FIVE NUMBERS NUMBER OF BUCKETS == BASE, COUNTING SORT NUMBER OF BUCKETS IS THE MAX NUMBER LIKE 9999 OR 64 (BASE 4)

public class LinkSort {
    public static void main(String[] args) {
        // RNode<Integer> numbers = new RNode<Integer>(999);
        // // RNode<Object> anything = new RNode<Object>("head");

        // for (int i = 0; i < 5; i++) {
        // numbers.add((int) (Math.random() * 999));
        // }
        // System.out.println(numbers);
        // linkBubble(numbers);
        // System.out.println(numbers);
        // int[] arr = new int[10];
        // for (int i = 0; i < arr.length; i++) {
        // arr[i] = (int) (Math.random() * arr.length);
        // }
        // System.out.println(Arrays.toString(arr));
        // // quick1(arr)
        // quick2(arr);
        // // System.out.println(quickSelect(arr, 0, arr.length - 1, 4));
        // System.out.println(Arrays.toString(arr));

        RNode<Integer> nums2 = new RNode<Integer>((int) (Math.random() * 100));
        for (int i = 0; i < 5; i++) {
            nums2.add((int) (Math.random() * 100));
        }
        System.out.println(nums2);
        linkSelect(nums2);
        System.out.println(nums2);
    }

    public static void linkSelect(RNode<Integer> head) {
        if (head == null) {
            return;
        }

        for (RNode<Integer> i = head; i != null; i = i.next) {
            RNode<Integer> min = i;

            for (RNode<Integer> j = i; j != null; j = j.next) {
                if (j.data < min.data) {
                    min = j;
                }
            }

            int temp = min.data;
            min.data = i.data;
            i.data = temp;
        }
    }

    public static int quickSelect(int[] arr, int start, int end, int k) {
        if (start >= end)
            return -1;
        int pivotIndex = (int) (Math.random() * (end - start + 1)) + start;

        int red40 = start;
        swap(arr, pivotIndex, end);
        for (int focus = start; focus < end; focus++) {
            if (arr[focus] < arr[end]) {
                swap(arr, red40, focus);
                red40++;
            }
        }
        swap(arr, end, red40);
        if (red40 == k)
            return red40;
        else if (red40 > k)
            return quickSelect(arr, start, red40 - 1, k);
        else
            return quickSelect(arr, red40 + 1, end, k);
    }

    private static int recursionDepth = 0;

    public static void quick2(int[] arr) {
        System.out.println("\n=== QuickSort 2 (Two-Pointer) Visualization ===");
        System.out.println("Initial array: " + Arrays.toString(arr));
        System.out.println("=".repeat(50));
        recursionDepth = 0;
        quick2H(arr, 0, arr.length - 1);
        System.out.println("=".repeat(50));
        System.out.println("Final sorted: " + Arrays.toString(arr) + "\n");
    }

    public static void quick2H(int[] arr, int left, int right) {
        if (left >= right)
            return;

        recursionDepth++;
        String indent = "  ".repeat(recursionDepth - 1);

        int buddyL = left;
        int buddyR = right;
        int pivot = arr[(int) (Math.random() * (right - left + 1)) + left];

        System.out.println(indent + "┌─ Depth " + recursionDepth + " [" + left + ".." + right + "]");
        System.out.println(indent + "│  Pivot: " + pivot);
        System.out.println(indent + "│  Before: " + arrayRangeToString(arr, left, right));

        while (buddyL < buddyR) {
            while (arr[buddyL] < pivot)
                buddyL++;
            while (arr[buddyR] > pivot)
                buddyR--;
            if (buddyL <= buddyR) {
                if (buddyL != buddyR) {
                    System.out.println(indent + "│  Swap: arr[" + buddyL + "]=" + arr[buddyL] + " ↔ arr[" + buddyR
                            + "]=" + arr[buddyR]);
                    swap(arr, buddyL, buddyR);
                }
                buddyL++;
                buddyR--;
            }
        }

        System.out.println(indent + "│  After:  " + arrayRangeToString(arr, left, right));
        System.out.println(indent + "│  Split at: L=" + buddyL + ", R=" + buddyR);
        System.out.println(indent + "└─");

        quick2H(arr, left, buddyR);
        quick2H(arr, buddyL, right);
        recursionDepth--;
    }

    private static String arrayRangeToString(int[] arr, int left, int right) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = left; i <= right; i++) {
            sb.append(arr[i]);
            if (i < right)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void quick1(int[] arr) {
        quick1H(arr, 0, arr.length - 1);
    }

    public static void quick1H(int[] arr, int start, int end) {
        if (start >= end)
            return;
        int pivotIndex = (int) (Math.random() * (end - start + 1)) + start;

        int red40 = start;
        swap(arr, pivotIndex, end);
        for (int focus = start; focus < end; focus++) {
            if (arr[focus] < arr[end]) {
                swap(arr, red40, focus);
                red40++;
            }
        }
        swap(arr, end, red40);
        quick1H(arr, start, red40 - 1);
        quick1H(arr, red40 + 1, end);
    }

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void linkBubble(RNode<Integer> head) {
        linkBubbleH(head, head, false);
    }

    public static void linkBubbleH(RNode<Integer> head, RNode<Integer> run, boolean swapped) {
        if (run.next == null) {
            if (swapped)
                linkBubbleH(head, head, false);
            return;
        }

        if (run.data > run.next.data) {
            Integer temp = run.next.data;
            run.next.data = run.data;
            run.data = temp;
            swapped = true;
        }

        linkBubbleH(head, run.next, swapped);
    }
}
