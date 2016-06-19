package tp6_eda;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MySimpleMap<K, V> implements SimpleMap<K, V> {
	
	private static final int THRESHOLD = 3;
	
	private static class Node<K,V>{
		K key;
		V value;
		
		public Node(K k, V v){
			key = k;
			value = v;
		}
	}
	
	List<Node<K,V>>[] buckets; 
	
	private final int BLOCK = 5;
	private int pointer = 0;
	private int doubled = 0;
	private int openBuckets;
	private int size = 0;
	
	
	@SuppressWarnings("unchecked")
	public MySimpleMap() {
        buckets = (List<Node<K,V>>[])Array.newInstance(List.class, 10);
        initializeBuckets(0,5);
        openBuckets = 5;
	}
	
	
	private void initializeBuckets(int start, int end) {
		for(int i = start; i < end ; i++)
			buckets[i] = new ArrayList<Node<K,V>>(THRESHOLD * 2); //Arbitrario
	}


	private Node<K,V> find(int bucket, K key){
		List<Node<K,V>> l = buckets[bucket];
		for(Node<K,V> node : l)
			if(node.key.equals(key)){
				return node;
			}
		return null;
	}
	
	@Override
	public void put(K key, V value) {
		int index = key.hashCode() % (BLOCK * (int)Math.pow(2, doubled));
		
		if(index < pointer) 
			index = key.hashCode() % (BLOCK * (int)Math.pow(2, doubled+1)); //Rehasheo a la segunda parte

		Node<K,V> f = find(index, key);
		if(f != null)
			f.value = value;
		else{
			buckets[index].add(new Node<K,V>(key,value));
			size++;
		}
		rehash();
	}

	private void rehash() {
		if(size/openBuckets < THRESHOLD)
			return;
		
		//El promedio de elementos por bucket es mayor que el limite
		//Debo rehashear el bucket apuntado por el puntero
		
	}


	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<K> keySet() {
		return null;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}