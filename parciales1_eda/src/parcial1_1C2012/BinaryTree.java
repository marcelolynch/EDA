package parcial1_1C2012;

import java.util.Comparator;

public class BinaryTree<T> {
	private T value;
	private BinaryTree<T> left, right;
	
	public BinaryTree(T value, BinaryTree<T> left, BinaryTree<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public T getValue() {	
		return value;
	}
	
	public BinaryTree<T> getLeft() {
		return left;
	}

	public BinaryTree<T> getRight() {
		return right;
	}
	
	
	
//	Implementar un método que dado un árbol binario y un valor a buscar, busque todas las ocurrencias
//	del mismo, y retorne un árbol recubridor que contenga únicamente las ramas desde la raíz hasta cada
//	nodo que contenga el valor buscado
	
	public static <T> BinaryTree<T> spanning(BinaryTree<T> tree, T value){
		if(tree == null){
			return null;
		}
		if(tree.getValue().equals(value)){
			return new BinaryTree<T>(value, spanning(tree.left, value), spanning(tree.right, value));
		}
		else{
			BinaryTree<T> rightSpan = spanning(tree.right, value);
			BinaryTree<T> leftSpan = spanning(tree.left, value);
		
			if(rightSpan == null && leftSpan == null)
				return null;
			else
				return new BinaryTree<T>(tree.getValue(), leftSpan, rightSpan);
		}
		
	}
	
	
	
//	Implementar un método que dado un árbol binario (clase BinaryTree del ejercicio anterior) y un
//	comparador, retorne true si el árbol cumple con la definición de heap, y false en caso contrario
	
	
//	private T isHeap(BinaryTree<T> t, Comparator<T> cmp){
//		if((BinaryTree<T> left = t.getLeft()) == null){
//			if(t.getRight() != null) // No cumple
//				return null;
//			else{
//				if(cmp.compare(left.value, ))
//			}
				
				
//		}
		
	//}
	public static void main(String[] args) {
		for(int i = 0 ; i < 100 ; i++)
			System.out.println("" + (int)Math.pow(2*i+1, 4) % 120 + " ");
	}
	
}