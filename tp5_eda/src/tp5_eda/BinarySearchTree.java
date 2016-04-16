package tp5_eda;

import java.util.Comparator;

public class BinarySearchTree<T> extends Tree<T> {
		private static class Node<T>{
			private T value;
			private Node<T> left;
			private Node<T> right;
			
			public Node(T value){
				this.value = value;
			}			
			
		}
		
		private Comparator<T> cmp;
		
		Node<T> root;
		
		private BinarySearchTree(Node<T> root){
			this.root = root;
		}
		
		public BinarySearchTree(Comparator<T> comparator){
			cmp = comparator;
		}
		
	
		public boolean isEmpty(){
			return root == null;
		}
		
		private void assertNotEmpty(){
			if(isEmpty())
				throw new IllegalStateException("Tree is empty");
		}
		
		
		
		
		public BinarySearchTree<T> leftSubtree(){
			assertNotEmpty();
			return new BinarySearchTree<T>(root.left);
		}
		
		public BinarySearchTree<T> rightSubtree(){
			assertNotEmpty();
			return new BinarySearchTree<T>(root.right);
		}
		
		
		
		
		public boolean contains(T key){
			return contains(root, key);
		}
		
		public boolean contains(Node<T> node, T key){
			if(node == null)
				return false;
			
			int c = cmp.compare(key, node.value);
			if(c == 0){
				return true;
			}
			else if(c > 0){ // value < key 
				return contains(node.right, key); 
			}
			else if(c < 0){  // value > key
				return contains(node.left, key);
			}
			else{
				return false;
			}
		}

		
		public void insert(T elem){
			if(elem == null)
				throw new IllegalArgumentException();
			
			root = insert(root, elem);
		}
		
		
		public Node<T> insert(Node<T> node, T elem) {
			if(node == null)
				return new Node<T>(elem);
			
			int comp = cmp.compare(elem, node.value);
			
			if(comp < 0){
				node.left = insert(node.left, elem);
			}
			if(comp > 0){
				node.right = insert(node.right, elem);
			}
			
			return node;
		}

		
		public void delete(T key){
			root = delete(root, key);
		}
		
		public Node<T> delete(Node<T> node, T key){
			if(node == null)
				return node;
			
			int comp = cmp.compare(key, node.value); // 
		
			if(comp > 0){ //key > value 
				node.right = delete(node.right, key);
			}
			else if(comp < 0){
				node.left = delete(node.left, key);
			}
			
			//comp == 0
			
			if(node.right == null)
				return node.left; //Si solo existe left, lo devuelvo. De ser ambos null devuelve null
			else if(node.left == null) //Solo esta el hijo derecho
				return node.right; 
			
			//Tiene los dos hijos. Busco el minimo a la derecha
			Node<T> curr = node.right.left;
			Node<T> prev = node.left; 
			
			while(curr.left != null){
				prev = curr;
				curr = curr.left;
			}
			
			node.value = prev.value; //Paso el minimo a la raiz
			prev.left = null;	//
			
			return node;
			
			
		}

		
		
		public void processInOrder(NodeOperation<T> op){
			processInOrder(root, op);
		}
		
		public void processInOrder(Node<T> node, NodeOperation<T> op){
			if(node == null)
				return;
			
			processInOrder(node.left, op);
			op.apply(node.value);
			processInOrder(node.right, op);
		}
		
		
		
/*		Ejercicio 7: Agregarle a la interfaz del BinarySearchTree métodos para resolver las siguientes operaciones:

			a. Dado un valor obtener el nivel que ocupa en el árbol (si el elemento no existe retorna -1).

			b. Determinar cuántas hojas tiene.

			c. Buscar el mayor elemento.

			d. Imprimir todos los antecesores de un determinado nodo.

			e. Imprimir todos los descendientes de un determinado nodo. */

		
		//a
		public int whichLevel(T key){
			return whichLevel(root, key, 0);
		}
		private int whichLevel(Node<T> node, T key, int lvl){
			if(node == null)
				return -1;
			
			int c = cmp.compare(key, node.value);
			if(c > 0)
				return whichLevel(node.right, key, ++lvl);
			else if(c < 0)
				return whichLevel(node.left, key, ++lvl);
			else
				return lvl;
			
		}
		
		
		//b
		public int numberOfLeaves(){
			return numberOfLeaves(root);
		}
		
		private int numberOfLeaves(Node<T> node){
			if(node == null)
				return 0;
			
			if(node.right == null && node.left == null)
				return 1;
			else
				return numberOfLeaves(node.left) + numberOfLeaves(node.right);
			
		}
		
		//c 
		public T greatestElem(){
			if(root == null)
				return null;
			Node<T> curr = root;
			while(curr.right != null)
				curr = curr.right;
			return curr.value;
		}
		
		
		//d
		public void printAncestors(T key){
			
		}
		
		
		public static void main(String[] args) {
			BinarySearchTree<Integer> t = new BinarySearchTree<Integer>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o1.compareTo(o2);
				}
				
			});
			t.insert(16);
			t.insert(14);
			t.insert(4);
			t.insert(10);
			t.insert(-0);
			t.insert(29);
			t.insert(16);
			t.insert(32);
			t.processInOrder(new NodeOperation<Integer>() {
				
				@Override
				public void apply(Integer value) {
					System.out.println(value);					
				}
			});
			
			System.out.println(t.contains(10));
			System.out.println(t.contains(4));
			System.out.println(t.contains(123));
			System.out.println(t.contains(29));
			
			System.out.println(t.contains(29));
			System.out.println(t.contains(16));
			System.out.println(t.numberOfLeaves());
			System.out.println(t.greatestElem());
			
		}
		

}
		

