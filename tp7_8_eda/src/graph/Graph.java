package graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph<V, E extends ArcGraph> extends GraphAdjList<V, E> {

	@Override
	public void addArc(V v, V w, E e) {
		super.addArc(v, w, e);
		super.addArc(w, v, e);
	}
	
	@Override
	public void removeArc(V v, V w) {
		super.removeArc(v, w);
		super.removeArc(w, v);
	}
	
	public int degree(V v) {
		Node node = nodes.get(v);
		if (node != null) {
			return node.adj.size();
		}
		return 0;
	}

	public boolean isConnected() {
		if (isEmpty()) {
			return true;
		}
		clearMarks();
		List<Node> l = getNodes();
		List<V> laux = new ArrayList<V>();
		DFS(l.get(0), laux);
		for (Node node : l) {
			if (!node.visited) {
				return false;
			}
		}
		return true;
	}
	
	public int connectedComponents() {
		clearMarks();
		return pathCount();
	}
	
	private int pathCount() {
		int count = 0;
		Node node;
		while ((node = unvisited()) != null) {
			count++;
			DFS(node, new ArrayList<V>());
		}
		return count;
	}

	private Node unvisited() {
		for(Node node : getNodes()) {
			if (! node.visited )
				return node;
		}
		return null;
	}
	
	public boolean cutVertex(V vertex) {
		Node node = nodes.get(vertex);
		if (node == null || node.adj.size() == 0)
			return false;
		clearMarks();
		int components = pathCount();
		clearMarks();
		node.visited = true;
		return components != pathCount();
	}
	
	public boolean isBridge(V v, V w) {
		E e = isArc(v,w);
		if ( e == null)
			return false;
		int components = connectedComponents();
		removeArc(v, w);
		int newComponents = connectedComponents();
		addArc(v, w, e);
		return components != newComponents;
		
	}
	
	/** Practica 7*/
	
	public void BFS(V origin, Function<V> f){
		Node n = nodes.get(origin);
		if(n == null) return;
		clearMarks();
		
		Queue<Node> q = new LinkedList<Node>();
		q.offer(n);
		while(!q.isEmpty()){
			n = q.poll();
			n.visited = true;
			n.info = f.apply(n.info);
			for(Arc a : n.adj){
				if(!a.neighbor.visited){
					q.offer(a.neighbor);
				}
			}
		}
		
		
	}

	
	public void DFS(V origin, Function<V> f){
		Node n = nodes.get(origin);
		if(n == null) return;
		clearMarks();
		DFS(n, f);
		
	}
	
	private void DFS(Node n, Function<V> f){
		n.visited = true;
		n.info = f.apply(n.info);
		for(Arc a: n.adj){
			if(!a.neighbor.visited){
				DFS(a.neighbor, f);
			}
		}
	}
	
	
	public void DFSStack(V origin, Function<V> f){
		Node n = nodes.get(origin);
		if(n == null) return;
		clearMarks();

		Deque<Node> stack = new LinkedList<Node>();
		stack.push(n);
		while(!stack.isEmpty()){
			n = stack.pop();
			n.visited = true;
			n.info = f.apply(n.info);
			
			for(Arc a: n.adj){
				if(!a.neighbor.visited){
					stack.push(a.neighbor);
				}
			}
		}
	}
	
	
	
	public boolean isTree(){
		if(nodeList.isEmpty()) return true;
		clearMarks();
		
		return isTree(nodeList.get(0), null);
	}
	
	private boolean isTree(Node n, Node prev){
		n.visited = true;
		for(Arc a: n.adj){
			Node next = a.neighbor;
			if(next.visited && !next.equals(prev))
				return false;
			if(!isTree(next, n))
				return false;
		}
		
		return true;
	}
	
	
	public boolean isBipartite(){
		if(nodeList.size() < 1)
			return false; //Medio arbitrario
		
		Node n = nodeList.get(0);
		clearMarks();
		
		return isBipartite(n, 1);
	}
	
	private boolean isBipartite(Node n, int color){
		if(n.tag == -color)
			return false;
		
		if(n.tag == color)
			return true;
		
		n.tag = color;
		
		for(Arc a : n.adj){
			if(!isBipartite(a.neighbor, -color))
				return false;
		}
		
		return true;
	}
	
	
	
	public Graph<V,E> minHeightSpanTree(V vertex){
		Node n = nodes.get(vertex);
		if(n == null) return null;
		clearMarks();
		
		Queue<Node> q = new LinkedList<>();
		
		Graph<V, E> tree = new Graph<V,E>();
	
		q.offer(n);
		tree.AddVertex(n.info);
		n.visited = true;
		while(!q.isEmpty()){
			n = q.poll();
			for(Arc a: n.adj){
				if(!a.neighbor.visited){
					tree.AddVertex(a.neighbor.info);
					tree.addArc(n.info, a.neighbor.info, a.info);
					a.neighbor.visited = true;
					q.offer(a.neighbor);
				}
			}
		}
	
		return tree;
	}
	
	
	
	public int countPaths(V origin, V destination){
		Node orig = nodes.get(origin);
		Node dest = nodes.get(destination);
		if(orig == null || dest == null)
			return 0;
		
		return countPaths(orig, dest);
	}
	
	private int countPaths(Node orig, Node dest){
		if(orig == dest)
			return 1;
		orig.visited = true;
		int count = 0;
		for(Arc a : orig.adj){
			if(! a.neighbor.visited){
				count += countPaths(a.neighbor, dest);
			}
		}
		orig.visited = false;
		return count;
		
		
	}
	
	public boolean isFriendly(){
		if(nodeList.isEmpty())
			return true;
				
		for(Node n: nodeList){
			clearMarks();
			if(!friendlyNode(n)){
				return false;
			}
			
			return isConnected();
		}
	}
	
	
	private boolean friendlyNode(Node n){
		Queue<Node> q = new LinkedList<>();
		n.visited = true;
		n.tag = 6;
		while(!q.isEmpty()){
			n = q.poll();
			if(n.tag == -1)
				return false;
			for(Arc a: n.adj){
				if(!a.neighbor.visited){
					Node na = a.neighbor;
					na.tag = n.tag - 1;
					na.visited = true;
					q.offer(na);
				}
			}
		}
		return true;
	}
	
	
	public List<V> possibleFriends(V v, int N){
		Node origin = nodes.get(v);
		if(origin == null)
			throw new IllegalArgumentException("No such node");
		clearMarks();
		
		List<V> friends = new ArrayList<V>();

		origin.visited = true;
		
		Queue<Node> q = new LinkedList<>();
		
		for(Arc a : origin.adj){
			a.neighbor.visited = true;
			q.offer(a.neighbor);
		}
		
		Node n;
		while(!q.isEmpty()){
			n = q.poll();
			for(Arc a: n.adj){
				Node neigh = a.neighbor;
				if(!neigh.visited){
					neigh.tag += 1;
					if(neigh.tag == N){
						friends.add(neigh.info);
						neigh.visited = true;
					}
				}
			}
		}
		return friends;
	}
	
	public static void main(String[] args) {
		Graph<Integer, ?> graph = new Graph<>();
		graph.AddVertex(1);
		graph.AddVertex(2);
		graph.AddVertex(3);
		graph.AddVertex(4);
		graph.AddVertex(5);
		graph.AddVertex(6);
		graph.AddVertex(7);

		graph.addArc(1, 2, null);
		graph.addArc(2, 3, null);
		graph.addArc(3, 7, null);
		graph.addArc(1, 6, null);
		graph.addArc(6, 7, null);
		graph.addArc(1, 4, null);
		graph.addArc(4, 5, null);
		graph.addArc(4, 6, null);
		graph.addArc(6, 5, null);
		graph.addArc(4, 2, null);
		

		System.out.println(graph.possibleFriends(1,3));
		
	}
}
