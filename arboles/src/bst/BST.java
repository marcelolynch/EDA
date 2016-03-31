package bst;

public class BST<T extends Comparable<T>> {
		
		/** Arbol interno BST, con metodos correspondientes */
		private static class Node<T extends Comparable<T>>{
			private T value;
			private Node<T> left;
			private Node<T> right;
			
			public Node(T value){
				this.value = value;
			}
			
			public boolean contains(T key){
				int cmp = value.compareTo(key);
				if(cmp == 0){
					return true;
				}
				else if(cmp < 0 && right != null){ // value < key 
					return right.contains(key); 
				}
				else if(cmp > 0 && left != null){  // value > key
					return left.contains(key);
				}
				else{
					return false;
				}
			}

			public void insert(T elem) {
				int comp = elem.compareTo(value);
				if(comp == 0)
					return;
				if(comp < 0){
					if(left == null){
						left = new Node<T>(elem);
					}
					else{
						left.insert(elem);
					}
				}
				else{
					if(right == null){
						right = new Node<T>(elem);
					}
					else{
						right.insert(elem);
					}
				}
				
			}
			
			public Node<T> delete(T key){
				int cmp = key.compareTo(value); // key - value > 
			
				if(cmp > 0){ //key > value 
					right = right.delete(key);
					return this;
				}
				else if(cmp < 0){
					left = left.delete(key);
					return this;
				}
				
				// cmp = 0, key = value
				if(right == null && left == null)
					return null;
				
				else if(right == null && left != null)
					return left;
				
				else if(left == null && right != null)
					return right;
				
				//Tiene los dos hijos. Busco el minimo a la derecha
				Node<T> nav = right.left;
				Node<T> prev = left;
				while(nav.left != null){
					prev = nav;
					nav = nav.left;
				}
				
				value = nav.value; //Paso el minimo a la raiz
				prev.left = null; // Y lo elimino
				
				return this;
				
			}

			public void printInOrder() {
				if(left != null)
					left.printInOrder();
				
				System.out.print(value);
				System.out.print("  ");
				
				if(right != null)
					right.printInOrder();
			}
			
			
		}

		Node<T> root;
		
		private BST(Node<T> root){
			this.root = root;
		}
		
		public BST(T value){
			this.root = new Node<T>(value);
		}
		
	
		public boolean isEmpty(){
			return root == null;
		}
		
		private void assertNotEmpty(){
			if(isEmpty())
				throw new IllegalStateException("Tree is empty");
		}
		
		public BST<T> leftSubtree(){
			assertNotEmpty();
			return new BST<T>(root.left);
		}
		
		public BST<T> rightSubtree(){
			assertNotEmpty();
			return new BST<T>(root.right);
		}
		
		
		public boolean contains(T key){
			if(isEmpty())
				return false;
			
			return root.contains(key);
		}
		
		public void insert(T elem){
			if(elem == null)
				throw new IllegalArgumentException();
			
			if(isEmpty()){
				root = new Node<T>(elem);
				return;
			}
			root.insert(elem);
		}
		
		public void delete(T key){
			if(isEmpty() || !contains(key))
				return;
			root.delete(key);
		}
		
		public void printInOrder(){
			if(isEmpty())
				return;
			root.printInOrder();
			System.out.println();
		}
		

		public static void main(String[] args) {
			BST<Integer> t = new BST<Integer>(10);
			t.insert(16);
			t.insert(14);
			t.insert(4);
			t.insert(10);
			t.insert(-0);
			t.insert(29);
			t.insert(16);
			
			t.printInOrder();
			
			System.out.println(t.contains(10));
			System.out.println(t.contains(4));
			System.out.println(t.contains(123));
			System.out.println(t.contains(29));
			
			t.delete(29);
			System.out.println(t.contains(29));
			System.out.println(t.contains(16));
						
			
		}
		

}
		

