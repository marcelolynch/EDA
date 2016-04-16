public class Stack<T> {
	
	private static class Node<T>{
		private Node<T> next;
		private T value;
		
		public Node(T value, Node<T> next){
			this.next = next;
			this.value = value;
		}
	}

	private Node<T> first;
	private int size;
	
	public void push(T elem){
		size++;
		Node<T> node = new Node<T>(elem, first);
		first = node;
	}
	
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public T peek(){
		if(isEmpty())
			throw new IllegalStateException();
		
		return first.value;
	}
	
	public T pop(){
		if(isEmpty())
			throw new IllegalStateException();
		T popped = first.value;
		first = first.next;
		size--;
		return popped;
	}


}
