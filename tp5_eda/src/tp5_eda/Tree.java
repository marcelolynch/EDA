package tp5_eda;

import java.util.Comparator;

public class Tree<T> {

		Node<T> root;
		
		public void insert(T elem){
			
		}
		
		
		//Ejercicio 1
		public <S> Tree<S> transform(Transformer<T,S> transformer){
			Tree<S> ntree = new Tree<S>();
			Node<S> nroot = transform(root, transformer);
			ntree.root = nroot;
			return ntree;
		}

		private <S> Node<S> transform(Node<T> root, Transformer<T, S> transformer) {
			if(root == null){
				return null;
			}
			Node<S> node = new Node<S>(transformer.transform(root.value));
			node.left = transform(root.left, transformer);
			node.right = transform(root.right, transformer);
			return node;
		}
		
		
		// Ejercicio 2
		public boolean mirrored(Tree<?> tree){
			return mirrored(root, tree.root);
		}

		private boolean mirrored(Node<T> t1, Node<?> t2) {
			if(t1 == null){
				return t2 == null;
			}
			else if(t2 == null){
				return false;
			}
			else{
				return mirrored(t1.left, t2.right) 
						&& mirrored(t1.right, t2.left);
			}
		}
		
		
		//Ejercicio 3
		public Tree<Integer> bstFromOrderedArray(Integer[] arr){
			Tree<Integer> t = new Tree<Integer>();	
			t.root = bstFromOrderedArray(arr, 0, arr.length - 1);
			return t;
		}

		private Node<Integer> bstFromOrderedArray(Integer[] arr, int start, int end) {
			if(start > end)
				return null;
			
			int middle = (start + end)/2;
			Node<Integer> node = new Node<Integer>(arr[middle]);
			node.left = bstFromOrderedArray(arr, start, middle-1);
			node.right = bstFromOrderedArray(arr, middle+1, end);
			
			return node;
 		}
		
		//Ejercicio 4
		
		public static <T> boolean isBST(Tree<T> tree, Comparator<T> cmp){
			return isBST(tree.root, null, null, cmp);
		}
		
		private static <T> boolean isBST(Node<T> node, T min, T max, Comparator<T> cmp){
			if(node == null){
				return true;
			}
			if(max != null && cmp.compare(node.value, max) > 0){
				return false;
			}
			if(min != null && cmp.compare(node.value, min) < 0){
				return false;
			}
			
			return isBST(node.left, min, node.value, cmp) && isBST(node.right, node.value, max, cmp);
		}
		
		
		//Ejercicio 5
		//El chequeo de BST y factor de balance se hace en un solo recorrido, usando un arreglo auxiliar que guarda
		//Las alturas de los hijos. Lo unico raro es que hay que llamar a la recursion y chequear tambien cosas parecidas
		//aca en la wrapper. 
		public static <T> boolean isAVL(Tree<T> tree, Comparator<T> cmp){
			if(tree.root == null)
				return true;
			
			int[] heights = new int[2];
			return isAVL(tree.root.left, null, null, cmp, heights, 0) && isAVL(tree.root.right, null, null, cmp, heights, 1)
					&& Math.abs(heights[0] - heights[1]) < 1;
			}


		
		
		private static <T> boolean isAVL(Node<T> node, T min, T max, Comparator<T> cmp, int[] heights, int index){
			if(node == null){
				heights[index] = -1; //Soy una hoja (index == 0 ? izquierda: derecha)
				return true;
			}
			if(max != null && cmp.compare(node.value, max) > 0){
				return false;
			}
			if(min != null && cmp.compare(node.value, min) < 0){
				return false;
			}
			
			int childrenHeights[] = new int[2]; //Quiero saber cuan altos son mis hijos, este metodo lo va a guardar al final
			boolean left = isAVL(node.left, min, node.value, cmp, childrenHeights, 0);
			boolean right = isAVL(node.right, node.value, max, cmp, childrenHeights, 1);
			
			boolean condition = left && right && Math.abs(heights[0] - heights[1]) < 1; //"Soy un AVL". En heights quedaron las alturas de los subarboles
			heights[index] = max(childrenHeights) + 1;	//Te dejo la altura de todo el subarbol en el indice relevante
			return condition;
		}
		
		private static int max(int[] heights){
			return heights[0] < heights[1] ? heights[1] : heights[0];
		}
		

		
		
		public static <T> boolean checkPostOrdered(Tree<T> t, Comparator<T> cmp){
			if(t.root == null)
				return true;
			return checkPostOrdered(t.root, null, cmp) != null;
		}


		private static <T> T checkPostOrdered(Node<T> node, T lastPost, Comparator<T> cmp) {
			if(node.left == null && node.right == null)
				return node.value;
			if(node.left != null){
				lastPost = checkPostOrdered(node.left, lastPost, cmp);
				if(lastPost == null)
					return null;
			}
			if(node.right != null){
				lastPost = checkPostOrdered(node.right, lastPost, cmp);
				if(lastPost == null)
					return null;
			}
			if(cmp.compare(node.value, lastPost) >= 0)
				return node.value;
			else
				return null;
		}
		
			
}
