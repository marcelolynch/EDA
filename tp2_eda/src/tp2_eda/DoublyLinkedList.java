package tp2_eda;

public class DoublyLinkedList<T> {
		private T value;
		private DoublyLinkedList<T> next;
		private DoublyLinkedList<T> prev;
		
		public DoublyLinkedList() {
			this(null, null, null);
		}

		public DoublyLinkedList(T v) {
			this(v, null, null);
		}

		public DoublyLinkedList(T v, DoublyLinkedList<T> prev, DoublyLinkedList<T> next){
			value = v;
			this.next = next;
			this.prev = prev;
		}


		public void add(T v) {
			if (value != null) {
				DoublyLinkedList<T> list = new DoublyLinkedList<T> (value, this, next);
				next = list;
			}
			value = v;
		}

//		public BaseList<T> remove(T v) {
//			return remove(v, this);
//		}
//
//		private BaseList<T> remove(T v, BaseList<T> n) {
//			if (n == null)
//				return n;
//			if (v.equals(n.value))
//				return n.next;
//			n.next = remove(v, n.next);
//			return n;
//		}
//
//		public boolean contains(T v) {
//			return contains(v, this);
//		}
//
//		private boolean contains(T v, BaseList<T> n) {
//			if (n == null)
//				return false;
//			if (v.equals(n.value))
//				return true;
//			return contains(v, n.next);
//		}

		
		
		public DoublyLinkedList<T> reverse(){
			DoublyLinkedList<T> tail = next;
			next = prev;
			prev = tail;
		
			if(tail == null)
				return this;
			
			return tail.reverse();
		}
}
