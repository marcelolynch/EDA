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
	
	public boolean leftUnvisited(){
		for(Node node: nodeList){
			if(!node.visited){
				return true;
			}
		}
		return false;
	}

	
	public boolean isBFS(List<V> list){
		if(nodeList.isEmpty())
			return list.isEmpty();
		if(list.isEmpty())
			return false;
		
		Node origin = nodes.get(list.get(0));
		if(origin == null)
			return false;
		clearMarks();
		Set<Node> s1 = new HashSet<Node>();
		Set<Node> s2 = new HashSet<Node>();
		Set<Node> aux;
		
		origin.visited = true;
		s1.add(origin);
		
		Iterator<V> iter = list.iterator();
		while(iter.hasNext()){
			V v = iter.next();
			Node curr = nodes.get(v);
			if(curr == null){
				return false;
			}
			if(s1.isEmpty()){
				aux = s1;
				s1 = s2;
				s2 = aux;
				count++;
			}
			
			if(!s1.remove(curr)){
				return false;
			}
			
			for(Arc a: curr.adj){
				if(!a.neighbor.visited){
					a.neighbor.visited = true;
					s2.add(a.neighbor);
				}
			}
		}
		
		return !leftUnvisited();
	}
	
	
	
	public static void main(String[] args) {

		Graph<String> graph = new Graph<String>();

		graph.addVertex("A");

		graph.addVertex("B");

		graph.addVertex("C");

		graph.addVertex("D");

		graph.addVertex("E");

		graph.addVertex("F");

		
		graph.addArc("A", "B", 0);

		graph.addArc("B", "C", 0);

		graph.addArc("A", "C", 0);

		graph.addArc("B", "F", 0);

		graph.addArc("C", "D", 0);

		graph.addArc("C", "E", 0);

		System.out.println(graph.isBFS(Arrays.asList("A", "B", "C", "D", "E", "F")));
		System.out.println(graph.isBFS(Arrays.asList("A", "C", "B", "D", "F", "E")));
		System.out.println(graph.isBFS(Arrays.asList("B", "F", "A", "C", "E", "D")));
		System.out.println(graph.isBFS(Arrays.asList("A", "C", "D", "D", "E", "F")));
		System.out.println(graph.isBFS(Arrays.asList("A", "B", "C", "D", "A", "F")));
	 
	}
}