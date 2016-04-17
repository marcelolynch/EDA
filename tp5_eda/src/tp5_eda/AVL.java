package tp5_eda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Copia de BinarySearchTree con las funciones AVL 
 * TODO: Herencia?
 */
public class AVL<T>{


		private static class Node<T>{
			T value;
			Node<T> left;
			Node<T> right;

			int height;
			
			int balanceFactor(){
				return leftHeight() - rightHeight();
			}
			
			int leftHeight(){
				return left == null ? -1 : left.height;
			}
			
			int rightHeight(){
				return right == null ? -1 : left.height;
			}
			
			Node(T value){	
				this.value = value;
			}			

	}

		private Comparator<T> cmp;
		private Node<T> root;
	
		private AVL(Node<T> root){
			this.root = root;
		}
		
		public AVL(Comparator<T> comparator){
			cmp = comparator;
		}
		
	
		public boolean isEmpty(){
			return root == null;
		}
		
		private void assertNotEmpty(){
			if(isEmpty())
				throw new IllegalStateException("Tree is empty");
		}
		
		
		
		
		public AVL<T> leftSubtree(){
			assertNotEmpty();
			return new AVL<T>(root.left);
		}
		
		public AVL<T> rightSubtree(){
			assertNotEmpty();
			return new AVL<T>(root.right);
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
		
		
		private Node<T> insert(Node<T> node, T elem) {
			if(node == null)
				return new Node<T>(elem);
			
			int comp = cmp.compare(elem, node.value);
			
			if(comp < 0){
				node.left = insert(node.left, elem);
			}
			if(comp > 0){
				node.right = insert(node.right, elem);
			}
	
			fixHeight(node); //Se llama de abajo para arriba: los hijos ya tienen siempre la altura nueva
			return balance(node);
		}

		
		private void fixHeight(Node<T> node){
			if(node == null) //No pasa, pero por las dudas
				return;
			node.height = 1 + Math.max(node.leftHeight(), node.rightHeight());
			}
		
		
		private Node<T> balance(Node<T> node){
			int bf = node.balanceFactor();
			if(node == null || Math.abs(bf) < 1){
					return node; // No hay que rebalancear
			}
			
			if(bf > 0){ //Desbalance a la izquierda
				if(node.left.balanceFactor() > 0){ //Doble izquierda: rotacion simple a derecha
					node = rightRotate(node);
				}
				else{ //Rotacion primero a izquierda del hijo izquierdo y luego a izquierda del padre
					node.left = leftRotate(node.left);
					node = rightRotate(node);
				}
				
			}
			else{ //Desbalance a la derecha
				if(node.right.balanceFactor() < 0){ //Doble derecha: rotacion simple a izquierda
					node = leftRotate(node);
				}
				else{  //Rotacion primero a derecha del hijo derecho y luego a izquierda del padre
					node.right = rightRotate(node.right);
					node = leftRotate(node);
				}
			}
		
			return node;
			
		}
			
			
		

		private Node<T> rightRotate(Node<T> node){
			Node<T> newRoot = node.left;
			Node<T> swapChild = node.left.right;
		
			newRoot.right = node;
			node.left = swapChild;
			
			fixHeight(node);	//El orden importa, fixHeight(newRoot)
			fixHeight(newRoot);	//depende de que node tenga bien su altura
			
			return newRoot;
		}
		
		private Node<T> leftRotate(Node<T> node){
			Node<T> newRoot = node.right;
			Node<T> swapChild = node.right.left;
		
			newRoot.left = node;
			node.right = swapChild;
			
			fixHeight(node);
			fixHeight(newRoot);
			
			return newRoot;
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
			List<T> list = new ArrayList<T>();
			Node<T> curr = root;
			while(curr != null && !curr.value.equals(key)){
				list.add(curr.value);
				int c = cmp.compare(key, curr.value);
				if(c < 0){
					curr = curr.left;
				}
				else{
					curr = curr.right;
				}
			}
			if(curr != null)
				for(T t: list)
					System.out.println(t);
			
		}
		
		public void printDescendants(T key){
			boolean found = false;
			Node<T> curr = root;
			while(curr != null && !found){
				int c = cmp.compare(key, curr.value);
				if(c == 0){
					found = true;
				}
				else if(c < 0){
					curr = curr.left;
				}
				else{
					curr = curr.right;
				}
			}
			
			if(curr != null){
				NodeOperation<T> print = new NodeOperation<T>() {
			
				@Override
				public void apply(T value) {
						System.out.println(value);
					}
				
				};
			processInOrder(curr.left, print);
			processInOrder(curr.right, print);
			}			
		}
		
		/*Ejercicio 9
 		Agregarle al árbol binario de búsqueda métodos que permitan construir iteradores para obtener los
		nodos según recorridos preorder, inorder y postorder.*/

		private class preOrderIterator implements Iterator<T>{
			
			private Stack<Node<T>> stack;

			public preOrderIterator(Node<T> first){
				stack = new Stack<Node<T>>();
				if(first != null){
					stack.push(first);
				}
				
			}
			
			private void fillStack(Node<T> node){
				if(node == null)
					return;
				if(node.right != null){
					stack.push(node.right);
				}
				if(node.left != null){
					stack.push(node.left);
					}
			}
			
			@Override
			public boolean hasNext() {
				return !stack.isEmpty();
			}

			@Override
			public T next() {
				if(!hasNext())
					throw new NoSuchElementException();
				
				Node<T> popped = stack.pop();
				T next = popped.value;
				fillStack(popped);
				return next;
			}
			
		}
		public Iterator<T> preOrderIterator(){
			return new preOrderIterator(root);
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
		/*	t.processInOrder(new NodeOperation<Integer>() {
				
				@Override
				public void apply(Integer value) {
					System.out.println(value);					
				}
			});
		*/
			//t.printDescendants(16);
			Iterator<Integer> iterator = t.preOrderIterator();
			
			while(iterator.hasNext()){
				System.out.println(iterator.next());
			}
			
		}
		

}
		

