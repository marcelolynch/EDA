package parciales2_eda;

import java.util.*;

public class Graph<V>{
	
	private class Node{
		V info;
		boolean visited = false;
		List<Arc> adj = new ArrayList<Arc>();
		
		public Node(V info){
			this.info = info;
		}
		
		public int hashCode(){
			return info.hashCode();
		}
		
		public boolean equals(Object obj){
			if(obj == null || obj.getClass() != getClass()){
				return false;
			}
			
			return info.equals(((Node)obj).info);
		}
	}
	
	private class Arc{
		int weight;
		Node neighbor;
		
		public Arc(int weight, Node neighbor){
			this.weight = weight;
			this.neighbor = neighbor;
		}
	}
	
	private HashMap<V, Node> nodes = new HashMap<V, Node>();
	
	private List<Node> nodeList = new ArrayList<Node>();
	
	
	
	
	
	public void addVertex(V vertex){
		if(!nodes.containsKey(vertex)){
			Node node = new Node(vertex);
			nodes.put(vertex, node);
			nodeList.add(node);
		}
	}
	
	public void addArc(V v, V w, int weight){
		Node origin = nodes.get(v);
		Node dest = nodes.get(w);
		if(origin != null && dest != null && !origin.equals(dest)){
			for(Arc arc : origin.adj){
				if(arc.neighbor.info.equals(w)){
					return;
				}				
			}
			origin.adj.add(new Arc(weight, dest));
			dest.adj.add(new Arc(weight, origin));
				
		}
	}
	
	public void clearMarks(){
		for(Node node: nodeList){
			node.visited = false;
		}
	}
	
	
	
	public boolean isDFS(List<V> values){
		if(values.isEmpty())
			return nodes.isEmpty();
		
		Node start = nodes.get(values.get(0));
		if(start == null) return false;
		clearMarks();
		
		return isDFS(start, values, 1) == values.size();
	}
	
	private int isDFS(Node n, List<V> list, int index){
		if(list.size() <= index) return index;
		
		n.visited = true;
		V next = list.get(index);
		
		while(hasUnvisitedNeighbors(n)){
			boolean found = false;
			for(Arc a: n.adj){
				Node ne = a.neighbor;
				if(!ne.visited && ne.info.equals(next)){
					found = true;
					index = isDFS(ne, list, index + 1);
					if(list.size() <= index) return index;
					next = list.get(index);
					}
				}
			if(!found)
				return list.size() + 1;
			}
		return index;
	}
	

	private boolean hasUnvisitedNeighbors(Node n) {
		for(Arc a : n.adj){
			if(!a.neighbor.visited){
				return true;
			}
		}
		return false;
	}
	
	
	
	private class Coloring{
		Map<Node, Integer> colors = new HashMap<>();
		
		public Coloring(){
			trivialFill();
		}
		
		private void trivialFill(){
			int i = 0;
			for(Node n: colors.keySet()){
				colors.put(n, i++);
			}
		}
		
		public Coloring clone(){
			Coloring clone = new Coloring();
			clone.colors = new HashMap<>(colors);
			return clone;
		}
		
		
		public List<Coloring> neighbors(){
			List<Coloring> n = new ArrayList<Coloring>();
			for(Node node: colors.keySet()){
				int color = colors.get(node);
				if(color > 0){
					if(!neighborInConflict(node,color)){
						Coloring potential = clone();
						potential.colors.put(node, color-1);
						n.add(potential);
					}
				}
			}
			return n;
		}
		
		
		/**Hill climbing : coloreo */
		public int minColorAprox(int iterations){
			Coloring max;
			Coloring current = new Coloring();
		}
		
		
		private boolean neighborInConflict(Node node, int color){
			for(Arc a: node.adj){
				if(colors.get(a.neighbor) == color - 1)
					return true;
			}
			return false;
		}
	}
	
	
	
	
	public static void main(String[] args) {

		Graph<String> graph = new Graph<String>();

		graph.addVertex("A");

		graph.addVertex("B");

		graph.addVertex("C");

		graph.addVertex("D");

		graph.addVertex("E");

		graph.addArc("A", "B", 0);

		graph.addArc("B", "C", 0);

		graph.addArc("A", "D", 0);

		graph.addArc("D", "B", 0);

		graph.addArc("B", "E", 0);

		graph.addArc("E", "C", 0);

		System.out.println(graph.isDFS(Arrays.asList("A", "D", "B", "C", "E")));
		System.out.println(graph.isDFS(Arrays.asList("B", "D", "A", "E", "C")));
		System.out.println(graph.isDFS(Arrays.asList("B", "C", "E", "D", "A")));
		System.out.println(graph.isDFS(Arrays.asList("A", "B", "E", "D", "C")));
		System.out.println(graph.isDFS(Arrays.asList("B", "E", "D", "A", "C")));
		System.out.println(graph.isDFS(Arrays.asList("A", "F")));
		System.out.println(graph.isDFS(Arrays.asList("B", "E", "C", "E", "C")));

		
	 
	}
}