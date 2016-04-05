package taller2;

import java.util.Comparator;


public class HeaderList<T> {

	private Node<T> first;
	private Comparator<T> cmp;
	
	private static class Node<T> { // static para ahorrar espacio
		private T elem;
		private Node<T> tail;
		
		public Node(T elem, Node<T> tail){
			this.elem = elem;
			this.tail = tail;
		}
	}

	
	//Insercion en lista ordenada
	//Iterativo
	public void add(T elem){
		Node<T> prev = null;
		Node<T> curr = first;
		
		
		while(curr != null && cmp.compare(curr.elem, first.elem) < 0){
			prev = curr;
			curr = curr.tail;
		}
		
		Node<T> n = new Node<T>(elem, curr);
		
		if(prev == null){
			first = n;
		}
		else{
			prev.tail = n;
		}
	}
	
	
	
	//Insercion en lista ordenada
	//Recursivo
	public void addR(T elem){
		first = add(elem, first);
	}
	
	
	private Node<T> add(T elem, Node<T> current){
		if(current == null || cmp.compare(elem, current.elem) < 0){
			return new Node<T>(elem, current);
		}
		current.tail = add(elem, current.tail);
		return current;
	}
	
	
	
}
