package tp2_eda;

/** Lista sin repetidos. Se agrega siempre al final. El tamaño se guarda */
@Deprecated
public class SinglyLinkedList<T> {
	
	private static class Node<T>{
		T elem;
		Node<T> next;
		
		public Node(T elem, Node<T> next){
			this.elem = elem;
			this.next = next;
		}
		
	}
	
	private Node<T> first;
	private int size;
	
	public void add(T elem){
		Node<T> curr = first;
		Node<T> prev = null;
		while(curr != null){
			if(curr.elem.equals(elem))
				return;
			prev = curr;
			curr = curr.next;
		}
		
		Node<T> toAdd = new Node<T>(elem, null);
		
		if(prev == null){
			first = toAdd;
		}else{
			prev.next = toAdd;
		}
		size++;
	}
	
	public void print(){
		Node<T> curr = first;
		while(curr != null){
			System.out.println(curr.elem);
			curr = curr.next;
		}
	}
	
		
		public static void main(String[] args) {
			SinglyLinkedList<String> l = new SinglyLinkedList<String>();
			l.add("Hola");
			l.add("Hola");
			l.add("A");
			l.add("C");
			l.add("B");
			l.add("A");
			l.print();
		}
	
}

