package Classwork.Unit7;

import java.util.ArrayList;
import java.util.Collections;

public class Triage {
    ArrayList<Injury> injuries;

    public Triage(ArrayList<Injury> injuries) {
        super();
        this.injuries = injuries;
        for (int i = getParent(injuries.size() - 1); i > -1; i++)
            downHeap(i);
    }

    public Injury nextPatient() {// get or findMin
        return injuries.size() == 0 ? null : injuries.get(0);
    }

    public void heapSort() {
        int heapSize = injuries.size();
        while (heapSize > 0) {
            swap(0, --heapSize);
            downHeap(0, heapSize);
        }
        Collections.reverse(injuries);
    }

    public void downHeap(int i, int size) {
        //addition for heapsize
        if (getLeft(i) >= size)
            return;
        if (getLeft(i) == -1)
            return;
        if (getRight(i) == -1 || getRight(i) >= size) {
            if (injuries.get(i).compareTo(injuries.get(getLeft(i))) > 0) {
                swap(i, getLeft(i));
                return;
            }
        }

        // two children case
        int left = getLeft(i);
        int right = getRight(i);
        int bigger = (injuries.get(left).compareTo(injuries.get(right)) > 0) ? right : left;
        if (injuries.get(i).compareTo(injuries.get(bigger)) > 0) {
            swap(i, bigger);
            downHeap(bigger, size); // Update for heapsize
        }
    }

    public int get(Injury inj) {
        for (int i = 0; i < injuries.size(); i++)
            if (injuries.get(i).equals(inj))
                return i;
            return -1;
    }

    public boolean setPriority(int i, int priority) {
        if (i < 0 || i >= injuries.size())
            return false;
        if (priority > injuries.get(i).priority) {
            injuries.get(i).priority = priority;
            upHeap(priority);
        }
        if (priority < injuries.get(i).priority) {
            injuries.get(i).priority = priority;
            downHeap(priority);
        }
        return true;
    }

    public void newPatient(Injury inj) {// add
        injuries.add(inj);
        int i = injuries.size() - 1;
        upHeap(i);
    }

    public void upHeap(int i) {
        while (injuries.get(getParent(i)).compareTo(injuries.get(i)) > 0) {
            swap(this.getParent(i), i);
            i = this.getParent(i);
        }
    }

    public Injury treat() {
        swap(0, injuries.size() - 1);
        Injury treated = (injuries.size() > 0) ? injuries.removeLast() : null;
        downHeap(0);
        return treated;
    }

    public void downHeap(int i) {
        if (getLeft(i) == -1 && getRight(i) == -1)
            return;
        if (getRight(i) == -1) {
            if (injuries.get(i).compareTo(injuries.get(getLeft(i))) > 0) {
                swap(i, getLeft(i));
                return;
            }
        }
        // two children case
        int left = getLeft(i);
        int right = getRight(i);
        int bigger = (injuries.get(left).compareTo(injuries.get(right)) > 0) ? right : left;
        if (injuries.get(i).compareTo(injuries.get(bigger)) > 0) {
            swap(i, bigger);
            downHeap(bigger);
        }
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= injuries.size() || j < 0 || j >= injuries.size())
            return;
        Injury temp = injuries.get(i);
        injuries.set(i, injuries.get(j));
        injuries.set(j, temp);
    }

    public int getParent(int i) {
        return (i - 1) / 2;
    }

    public int getLeft(int i) {
        int left = i * 2 + 1;
        return (left < injuries.size()) ? left : -1;
    }

    public int getRight(int i) {
        int right = i * 2 + 2;
        return (right < injuries.size()) ? right : -1;
    }

    @Override
    public String toString() {
        String output = "";
        int levels = (int) (Math.log(injuries.size()) / Math.log(2));
        for (int i = 0; i <= levels; i++) {
            // int start = (int) (Math.pow(2, i) - 1);
            // for (int j = start; j <= )
        }
        return output;
    }

}
