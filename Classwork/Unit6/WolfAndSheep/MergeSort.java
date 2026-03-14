import java.util.ArrayList;
import java.util.List;

/**
 * Stable merge sort implementation for lists of strings.
 * Documentation done by AI.
 *
 * @author AfrazSohail
 */
public class MergeSort {
    /**
     * Sorts the list in ascending order using merge sort.
     *
     * @param list list of strings to sort in-place
     */
    public static void sort(List<String> list) {
        if (list == null || list.size() <= 1)
            return;

        int mid = list.size() / 2;
        List<String> left = new ArrayList<>(list.subList(0, mid));
        List<String> right = new ArrayList<>(list.subList(mid, list.size()));

        sort(left);
        sort(right);

        merge(list, left, right);
    }

    /**
     * Merges two sorted halves into the target list.
     *
     * @param list  target list to update
     * @param left  sorted left half
     * @param right sorted right half
     */
    private static void merge(List<String> list, List<String> left, List<String> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0)
                list.set(k++, left.get(i++));
            else
                list.set(k++, right.get(j++));
        }

        while (i < left.size())
            list.set(k++, left.get(i++));

        while (j < right.size())
            list.set(k++, right.get(j++));
    }
}
