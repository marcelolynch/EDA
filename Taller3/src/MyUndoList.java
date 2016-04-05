import java.util.Comparator;


//Se cuenta con una interfaz que representa una lista ordenada (se admiten elementos rep)
//Se proporciona un comparadaor como parametro en el constructor de la clase que implemente la interfaz

public class MyUndoList<T> implements SortedList<T>{
	private static class Node<T>{
		private T elem;

		//Doblemente encadenada
		Node<T> prev; 
		Node<T> next;
		
		Node<T> undoNext;
		
		public Node(T elem, Node<T> next, Node<T> prev, Node<T> undoNext){
			this.elem = elem;
			this.next = next;
			this.undoNext = undoNext;
			this.prev = prev;
		}
	}
	
	private Node<T> first;
	private Node<T> undoFirst;
	private Comparator<T> cmp;
	
	
	public MyUndoList(Comparator<T> cmp) {
		this.cmp = cmp;
	}
	
	
	@Override
	public void add(T elem) {
		first = add(first, null, elem);
	}
	
	private Node<T> add(Node<T> current,  Node<T> prev, T elem){
		if(current == null || cmp.compare(elem, current.elem) > 0){
			
			Node<T> node = new Node<T>(elem, current, prev, this.undoFirst);
			undoFirst = node;
			return node;
		}
		
		current.next = add(current.next, current, elem);
		return current;
	}
	
	

	@Override
	public void undo() {
		Node<T> toErase = undoFirst;
		if(toErase == null)
			throw new IllegalStateException();
		
		undoFirst = toErase.undoNext;
		
		if(toErase.prev != null){
			toErase.prev.next = toErase.next;
		}
		else{
			first = toErase.next;
		}
		if(toErase.next != null){
			toErase.next.prev = toErase.prev;
		}
		
		
	
	}

	@Override
	public void print() {
		Node<T> n = first;
		while(n != null){
			System.out.print(n.elem);
			System.out.print(" ");
			n = n.next;
		}
	}
	

}
