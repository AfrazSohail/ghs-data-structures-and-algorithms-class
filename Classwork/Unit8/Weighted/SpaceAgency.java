package Weighted;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class SpaceAgency {
	HashSet<Celest> celests;
	Celest hub;
	public SpaceAgency(HashSet<Celest> celests, Celest hub) {
		this.celests = celests;
		this.hub = hub;
	}
	public void resetPrices() {
		for(Celest c:celests)
			if(c==hub)
				c.price = 0;
			else
				c.price = Double.POSITIVE_INFINITY;
	}
	public String getCheapest() {
		this.resetPrices();

		dijkstras();
		StringBuilder output = new StringBuilder();
		for(Celest c:celests)
			output.append(c.name + ": " + c.price+"\n");
		return output.toString();
	}

    public void dijkstras() {
        PriorityQueue<Celest> PQ = new PriorityQueue<Celest>();
        for (Celest c : celests)
            PQ.add(c);
        HashSet<Celest> beenTo = new HashSet<Celest>();
        while (!PQ.isEmpty()) {
            Celest exploring = PQ.poll();
            if (!beenTo.contains(exploring)) {
                beenTo.add(exploring);
                for (Celest c : exploring.neighbors.keySet()) {
                    if (!beenTo.contains(c)) {
                        if (c.price > exploring.price + exploring.neighbors.get(c)) {
                            c.price = exploring.price + exploring.neighbors.get(c);
                            //REMOVE THIS AND ADD IT BACK
                            //BUT YOU DON'T KNOW HE'S AT THE TOP SO YOU
                            //SEARCH AND REMOVE
                            PQ.add(c);//NEED TO READ SO THAT PQ REMOVES IN THE RIGHT ORDER
                            c.previous = exploring;
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Edge> prims() {
        ArrayList<Edge> mst = new ArrayList<Edge>();

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        HashSet<Celest> beenTo = new HashSet<Celest>();
        beenTo.add(hub);
        addEdges(hub, pq, beenTo);

        while (!pq.isEmpty()) {
            Edge cheapEdge = pq.poll();
            if (!beenTo.contains(cheapEdge.B)) {
                mst.add(cheapEdge);
                if (mst.size() == celests.size() - 1){
                    return mst;
                }
                beenTo.add(cheapEdge.B);
                addEdges(cheapEdge.B, pq, beenTo);
            }
        }
        return null;
    }

    private void addEdges(Celest c, PriorityQueue<Edge> pq, HashSet<Celest> beenTo) {
        for (Celest neighbor : c.neighbors.keySet()) {
            if (!beenTo.contains(neighbor)) {
                pq.add(new Edge(c, neighbor, c.neighbors.get(neighbor)));
            }
        }
    }

	public String getRoute(Celest c) {
		if(c==null)
			return "Can't Get to ";
		if(c==hub)
			return hub.name;
		return this.getRoute(c.previous)+c.name;
	}

	public String toString() {
		StringBuilder output = new StringBuilder("");
		output.append("Hub: " + hub.name+"\n");
		for(Celest celest:celests) {
			output.append(celest.name+"->");
			for(Celest celest2:celest.neighbors.keySet())
				output.append(celest2.name+":"+celest.neighbors.get(celest2)+", " );
			output.append("\n");
		}
		return output.toString();
	}
}
