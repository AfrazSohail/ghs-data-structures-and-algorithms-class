package Unweighted;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//creates a number graphs from 0-9 linked to at most 5 random connections
public class NumberGraph {
	 Map<Integer, Set<Integer>> map;

     public NumberGraph() {
         map = new HashMap<>();
         for (int i = 0; i < 10; i++) {
             Set<Integer> randomSet = new HashSet<>();
             int size = (int) (Math.random() * 5);

             for (int j = 0; j < size; j++) {
                 int rando = (int) (Math.random() * 10);
                 if (rando != i)
                     randomSet.add(rando);
             }
             map.put(i, randomSet);
         }

     }

     public ArrayList<Integer> hasCycle(int start) {
         ArrayList<Integer> path = new ArrayList<>();
         ArrayList<Integer> spreaders = new ArrayList<>();
         if (hasCycle(start, path, spreaders)) {
             return spreaders;
         }
         return new ArrayList<>();
     }

     public boolean hasCycle(int vertex, ArrayList<Integer> path, ArrayList<Integer> spreaders){
         if (spreaders.contains(vertex)) {
             spreaders.add(vertex);
             return true;
         }
        if (path.contains(vertex))
            return false;
        path.add(vertex);
        spreaders.add(vertex);
        Set<Integer> neighbors = map.get(vertex);
        for (Integer neighbor : neighbors) {
            if (hasCycle(neighbor, path, spreaders))
                return true;
        }
        path.remove(path.size() - 1);
        spreaders.remove(spreaders.size() - 1);
        return false;
    }

	 public String toString() {
		 String output = "";
		 for(int key:map.keySet()) {
			 output+= key + "->"+map.get(key) + "\n";
		 }
		 return output;
	 }

}
