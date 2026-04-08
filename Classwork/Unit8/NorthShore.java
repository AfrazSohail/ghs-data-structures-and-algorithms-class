import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NorthShore {
    HashMap<Meanie, HashSet<Meanie>> plastics;

    public NorthShore() {
        plastics = new HashMap<Meanie, HashSet<Meanie>>();
    }

    public boolean addMeanie(Meanie m, HashSet<Meanie> friends, boolean isDirected) {
        if (m == null || plastics.containsKey(m))
            return false;
        if (friends == null)
            friends = new HashSet<Meanie>();
        HashSet<Meanie> initiated = new HashSet<Meanie>();
        for (Meanie mean : friends)
            if (plastics.containsKey(mean))
                initiated.add(mean);
        plastics.put(m, initiated);
        if (!isDirected) {
            for (Meanie mean : initiated)
                plastics.get(mean).add(m);
        }
        return true;
    }

    public boolean connect(Meanie m1, Meanie m2, boolean isDirected) {
        if (m1 == null || !plastics.containsKey(m1) || m2 == null || !plastics.containsKey(m2))
            return false;
        plastics.get(m1).add(m2);
        if (!isDirected)
            plastics.get(m2).add(m1);
        return true;
    }

    public boolean dropOff(Meanie m) {
        if (m == null || !plastics.containsKey(m))
            return false;

        plastics.remove(m);
        for (Meanie mean : plastics.keySet())
            plastics.get(mean).remove(m);
        return true;
    }

    public boolean ghost(Meanie m1, Meanie m2, boolean isDirected) {
        if (m1 == null || !plastics.containsKey(m1) || m2 == null || !plastics.containsKey(m2))
            return false;
        boolean ghosted = plastics.get(m1).remove(m2);
        boolean ghostBack = !isDirected && plastics.get(m2).remove(m1);
        return ghosted || ghostBack;
    }

    public ArrayList<Meanie> DFSpread(Meanie m, String rumor, Meanie target) {
        if (m==null||!plastics.containsKey(m))
            return null;
        ArrayList<Meanie> beenThere = new ArrayList<Meanie>();
        Stack<Meanie> spreaders = new Stack<Meanie>();
        spreaders.push(m);
        while (!spreaders.isEmpty()) {
            Meanie spreader = spreaders.pop();
            spreader.rumor = rumor;
            if (beenThere.contains(spreader))
                continue;
            beenThere.add(spreader);
            for (Meanie meanie: plastics.get(spreader))
                if (!beenThere.contains(meanie)){
                    spreaders.push(meanie);
                    if (meanie == target) {
                        meanie.rumor = rumor;
                        return beenThere;
                    }
                }
        }

        return beenThere;
    }

    public ArrayList<Meanie> BFSpread(Meanie m, String rumor, Meanie target) {
        if (m==null||!plastics.containsKey(m))
            return null;
        ArrayList<Meanie> beenThere = new ArrayList<Meanie>();
        Queue<Meanie> spreaders = new LinkedList<Meanie>();
        spreaders.offer(m);
        while (!spreaders.isEmpty()) {
            Meanie spreader = spreaders.poll();
            spreader.rumor = rumor;
            if (beenThere.contains(spreader))
                continue;
            beenThere.add(spreader);
            System.out.println(spreader);
            for (Meanie meanie: plastics.get(spreader))
                if (!beenThere.contains(meanie)){
                    spreaders.offer(meanie);
                    if (meanie == target) {
                        meanie.rumor = rumor;
                        return beenThere;
                    }
                }
        }

        return beenThere;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Meanie mean: plastics.keySet())
            output.append(mean.toString() + "->" + plastics.get(mean) + "\n");
        return output.toString();
    }
}
