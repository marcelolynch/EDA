package tp3_eda;

public class Queue<T> {
	private static class Node<T>{
		private Node<T> next;
		private T value;
		
		public Node(T value, Node<T> next){
			this.next = next;
			this.value = value;
		}
	}

	private Node<T> first;
	private Node<T> last;
	
	public void enqueue(T elem){
		Node<T> node = new Node<T>(elem, null);
		if(first == null){
			first = node;
			last = first;
		}
		else{
			last.next = node;
			last = node;
		}
	}
	
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public T dequeue(){
		if(isEmpty())
			throw new IllegalStateException();
		T deq = first.value;
		first = first.next;
		return deq;
	}

	
}
