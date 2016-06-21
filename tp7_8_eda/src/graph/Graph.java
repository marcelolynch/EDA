package graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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
			if(next.visited){
				if(next.equals(prev))
					return false;
			}
			else if(!isTree(next, n))
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

		}

		return isConnected();

	
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
	
	
	private class PQNode implements Comparable<PQNode>{
		Node node;
		Node prev;
		Arc arc;
		double weight;
		
		public PQNode(Node node, Node prev, Arc arc, double weight){
			this.node = node;
			this.prev = prev;
			this.arc = arc;
			this.weight = weight;
		}
		
		public int compareTo(PQNode other){
			if(other == null) return 1;
			return Double.compare(weight, other.weight);
		}
	}
	
	public Graph<V,ArcGraph> minPathTree(V origin){
		
		Node n = nodes.get(origin);
		if(n == null) throw new IllegalArgumentException("No such vertex");
		clearMarks();
		
		Graph<V,ArcGraph> tree = new Graph<>();
		PriorityQueue<PQNode> pq = new PriorityQueue<PQNode>();
		pq.offer(new PQNode(n, null, null, 0));
		while(!pq.isEmpty()){
			PQNode pqn = pq.poll();
			if(!pqn.node.visited){
				pqn.node.visited = true;
				tree.AddVertex(pqn.node.info);
				if(pqn.prev != null){
					tree.addArc(pqn.node.info, pqn.prev.info, pqn.arc.info);
				}
				for(Arc a: pqn.node.adj){
					if(!a.neighbor.visited){
						pq.offer(new PQNode(a.neighbor, pqn.node, a, pqn.weight + a.info.getValue()));
					}
				}
			}
			
		}
		return tree;
	}
	
	
	private class ArcEnds{
		Arc arc;
		Node from;

		public ArcEnds(Node n, Arc a){
			from = n;
			this.arc = a;
		}
	}
	
	public Graph<V, ArcGraph> minWeightTree(){
			
		Graph<V,ArcGraph> ans = new Graph<V,ArcGraph>();
		if(nodeList.isEmpty())
			return ans;
		
		clearMarks();	
		Set<Node> visited = new HashSet<>();
		visited.add(nodeList.get(0));
		
		ans.AddVertex(nodeList.get(0).info);
		while(ans.vertexCount() < vertexCount()){
			ArcEnds minArc = findMinFrontierArc(visited);
			ans.AddVertex(minArc.arc.neighbor.info);
			ans.addArc(minArc.from.info, minArc.arc.neighbor.info, minArc.arc.info);
			visited.add(minArc.arc.neighbor);
		}
		
		return ans;
		
	}
	
	
	private ArcEnds findMinFrontierArc(Set<Node> from) {
		ArcEnds min = null;
		for(Node n: from){
			for(Arc a: n.adj){
				if((min == null || Double.compare(a.info.getValue(), min.arc.info.getValue()) < 0)
						&& !from.contains(a.neighbor)){
						min = new ArcEnds(n, a);
					}
					
				}
			}
		return min;
	}
	
	
	
	public void printFromNode(V v){
		DFS(nodes.get(v), new Function<V>(){

			@Override
			public V apply(V v) {
				System.out.println(v);
				return v;
			}
			
		});
	}

	public static void main(String[] args) {
		Graph<Integer, MyArc> graph = new Graph<>();
		graph.AddVertex(1);
		graph.AddVertex(2);
		graph.AddVertex(3);
		graph.AddVertex(4);
		graph.AddVertex(5);
		graph.AddVertex(6);
		graph.AddVertex(7);

		graph.addArc(1, 2, new MyArc(3));
		graph.addArc(2, 3, new MyArc(1));
		graph.addArc(3, 7, new MyArc(6));
		graph.addArc(1, 6, new MyArc(4));
		//graph.addArc(6, 7, new MyArc(5));
		graph.addArc(1, 4, new MyArc(2));
		graph.addArc(4, 5, new MyArc(5));
	//	graph.addArc(4, 6, new MyArc(6));
		graph.addArc(6, 5, new MyArc(2));
		graph.addArc(3, 6, new MyArc(7));
		//graph.addArc(4, 2, new MyArc(4));
		
		System.out.println(graph.isBipartite());

		graph.minWeightTree().printFromNode(6);
		
	}
}
