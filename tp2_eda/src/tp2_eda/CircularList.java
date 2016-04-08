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
	
	private Node<T> tail;
	private Node<T> last;
	
	public boolean isEmpty(){
		return tail == null;
	}
	
	public T next() throws IllegalStateException{
		if(isEmpty())
			throw new IllegalStateException();
		last = tail; //Para borrar el ultimo escupido O(1)
		tail = tail.next; //Roto la lista
		T elem = tail.value;
		return elem;
		
	}
	
	public void removeLast(){
		if(last == null)
			return;
		
		System.out.print("Removing from list: ");
		System.out.println(tail.value);
		last.next = tail.next;
		tail = last;
	}
	
	public void add(T elem){
		if(tail== null){
			tail = new Node<T>(elem, null);
			tail.next = tail;
			return;
		}
		else{
			Node<T> node = new Node<T>(elem, tail.next);
			tail.next = node;
			return;
		}
	}
		
	public boolean contains(T elem){
		if(tail == null)
			return false;
		
		
		Node<T> curr = tail;
		if(curr.value.equals(elem))
			return true;
		
		curr = curr.next;
		while(curr != tail){
			if(curr.value.equals(elem))
				return true;

			curr = curr.next;
		}
		return false;
	}
	
	
	
	
	
	public static void main(String[] args) {
		CircularList<Integer> list = new CircularList<Integer>();
		
		list.add(10);
		list.add(9);
		list.add(8);
		list.add(7);
		list.add(6);
		list.add(5);
		list.add(4);
		list.add(3);
		list.add(2);
		list.add(1);

		int i = 0;
		while(i++ < 50){
			System.out.println(list.next());
			if(i == 5) 
				{list.removeLast();
				}
		}

	}
	
}
