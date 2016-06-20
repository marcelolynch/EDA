package graph;

import java.util.Iterator;

public class DiGraph<V, E extends ArcGraph> extends GraphAdjList<V, E> {

	public int inDegree(V v) {
		int count = 0;
		Node node = nodes.get(v);
		for (Node n : getNodes()) { // Recorremos lista de nodos
			if (!n.equals(node)) {
				for (Arc adj : n.adj)
					// Recorremos lista de adyacencia
					if (adj.neighbor.equals(node))
						count++;
			}
		}
		return count;
	}
	
	public int outDegree(V v) {
		Node node = nodes.get(v);
		if (node != null) {
			return node.adj.size();
		}
		return 0;
	}

	
	public boolean hasCycles(){
		
		boolean ans = false;
		Node n;
		Iterator<Node> iter = nodeList.iterator();
		clearMarks();
		
		while((n = nextUnvisited(iter)) != null){
			if(hasCycles(n, null))
				return true;
		}
		
		return ans;
	}

	private boolean hasCycles(Node n, Node prev) {
		n.visited = true;
		for(Arc a: n.adj){
			if(a.neighbor.visited && !a.neighbor.equals(prev))
				return true;
			if(hasCycles(a.neighbor, n))
				return true;
		}
		n.visited = false;
		return false;
		
	}
	
	
	
	public static void main(String[] args) {
		DiGraph<String,MyArc> graph = new DiGraph<String, MyArc>();
		graph.AddVertex("A");
		graph.AddVertex("B");
		graph.AddVertex("C");
		graph.AddVertex("D");
		graph.AddVertex("E");
		graph.AddVertex("F");
		graph.AddVertex("G");
		graph.AddVertex("H");
		graph.AddVertex("I");

		
		graph.addArc("A", "B", null);
		graph.addArc("B", "C", null);
		graph.addArc("C", "D", null);
		graph.addArc("A", "D", null);
		graph.addArc("E", "A", null);
		graph.addArc("G", "F", null);
		graph.addArc("H", "F", null);
		graph.addArc("G", "H", null);
		graph.addArc("H", "I", null);
		graph.addArc("I", "G", null);

		
		
		System.out.println(graph.hasCycles());
	}
}
