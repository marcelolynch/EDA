package tp2_eda;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class MySet<T> implements Set<T> {
	
	private static class Node<T>{
		T elem;
		Node<T> next;
		
		public Node(T elem, Node<T> next){
			this.elem = elem;
			this.next = next;
		}
		
	}
	
	private static class Iter<T> implements Iterator<T>{
		Node<T> next;
		
		public Iter(Node<T> first){
			next = first;
		}
		
		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new IllegalStateException();
			
			T elem = next.elem;
			next = next.next;
			return elem;
		}
		
	}
	
	private Node<T> first;
	private int size;
	
	//O(N)
	public boolean add(T elem){
		Node<T> curr = first;
		Node<T> prev = null;
		while(curr != null){
			if(curr.elem.equals(elem))
				return false;
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
		return true;
	}
	
//	
//	public void print(){
//		Node<T> curr = first;
//		while(curr != null){
//			System.out.println(curr.elem);
//			curr = curr.next;
//		}
//	}

	//O(NM) donde N es la lista y M la long de la coleccion
	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean changed = false;
		for(T t: c){
			if(add(t))
				changed = true;
		}
		return changed;
	}

	//O(1)
	@Override
	public void clear() {
		first = null;
		size = 0;
	}

	//O(N)
	@Override
	public boolean contains(Object o) {
		for(T t: this){
			if(t.equals(o))
				return true;
		}
		return false;
			
	}

	//O(NM)
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o: c)
			if(!contains(o))
				return false;
		return true;
	}

	//O(1)
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	
	@Override
	public Iterator<T> iterator() {
		return new Iter<T>(first);
	}

	
	//O(N)
	@Override
	public boolean remove(Object o) {
		if(isEmpty())
			return false;
		
		Node<T> curr = first;
		Node<T> prev = null;
		while(curr != null && !curr.elem.equals(o)){
			prev = curr;
			curr = curr.next;
		}
		if(curr == null){ //Llegue al final y no estaba
			return false;
		}
		
		if(prev == null){ //Era el primer elemento
			first = null;
		}
		else{
			curr.next = null; //Leaks?
			prev.next = curr.next; //Borro curr
		}
		size--;
		return true;
	}

	//O(NM)
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean removed = false;
		for(Object o: c){
			if(remove(o)){
				removed = true;
			}
		}
		return removed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	//O(1)
	@Override
	public int size() {
		return size;
	}

	//O(N)
	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
	
		Node<T> curr = first;
		for(int i = 0; curr != null ; i++){
			arr[i] = curr.elem;
			curr = curr.next;
		}
		return arr;
	}

	//O(N)
	@Override
	@SuppressWarnings("unchecked")
	public <E> E[] toArray(E[] a) {
		E[] arr = a;
		if (a.length < size())
			arr = (E[])Array.newInstance(a.getClass(), size());

		Node<T> curr = first;
		for(int i = 0; curr != null ; i++){
			arr[i] = (E)curr.elem;
			curr = curr.next;
		}
	
		return arr;
	}
	
	public static void main(String[] args) {
		MySet<String> l = new MySet<String>();
		l.add("Hola");
		l.add("A");
		l.add("C");
		l.add("B");
		l.add("A");
		String[] c = l.toArray(new String[0]);
		
		for(String s: l){
			System.out.println(s);
		}
	}

	
}
