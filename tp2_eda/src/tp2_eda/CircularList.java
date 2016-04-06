package tp2_eda;

public class CircularList<T> {
	
	//Nodo doble cadena
	private static class Node<T>{
		private Node<T> next;
		private T value;
		
		public Node(T value, Node<T> next){
			this.next = next;
			this.value = value;
		}
		
	}
	
	private Node<T> mouth;
	private Node<T> tail;
	
	public CircularList(){
	}
	
	public void add(T elem){
		if(mouth== null){
			mouth = new Node(elem, null);
			mouth.next = mouth;
			tail = mouth;
		}
		Node<T> node = new Node<T>(elem, tail);
		mouth.next = node;
		mouth = node;
	}
	
	
	
}
