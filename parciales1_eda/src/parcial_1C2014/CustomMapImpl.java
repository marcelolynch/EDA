package parcial_1C2014;

public class CustomMapImpl<K, V> implements CustomMap<K, V> {
	private static class Node<K,V>{
		K key;
		V value;
	
		Node<K,V> next;
		
		public Node(K key, V value){
			this.key = key;
			this.value = value;
		}
	}
	
	private static class HeaderNode<K,V>{
		
		int accesses;
		
		HeaderNode<K,V> next;
				
		Node<K,V> first;
		
		public HeaderNode(int n, Node<K,V> node){
			accesses = n;
			first = node;
		}
		
		public boolean isEmpty(){
			return first == null;
		}
		
		/**
		 * Busca un elemento y lo elimina de la lista
		 * Devuelve el nodo si estaba y fue elminado, 
		 * null de lo contrario
		 */
		public Node<K,V> popNode(K key){
			if(isEmpty())
				return null; //No va a suceder, de todas maneras
			
			Node<K,V> prev = null;
			Node<K,V> curr = first;
			
			while(curr != null && !curr.key.equals(key)){
				prev = curr;
				curr = curr.next;
			}
			
			if(curr == null) //No estaba
				return null;
			
			if(prev == null){ // Era el primer elemento
				first = curr.next;
			}else{	//Mitad de la lista
				prev.next = curr.next;
			}
			curr.next = null; // Lo desconecto de la lista
			return curr;
		}
		
		public void addNode(Node<K,V> node){
			node.next = first;
			first = node;
		}

		public void removeFirst() {
			if(isEmpty())
				return;
			first = first.next;
		}
		
	}
	
	/** Menos accedidos */
	private HeaderNode<K,V> first;
	
	/** Mas accedidos */
	private HeaderNode<K,V> last;
	
	
	public CustomMapImpl() {
		first = new HeaderNode<K,V>(0, null); //Lo hago aca porque no hay deletes
		last = first;
	}
	
	@Override
	public V get(K key) {
		Node<K,V> found = null;
		
		HeaderNode<K,V> prev = null;
		HeaderNode<K,V> curr = first;
		
		while(curr != null && (found = curr.popNode(key)) == null){
			prev = curr;
			curr = curr.next;
		}
		
		if(found == null) // No se encontró
			return null;
		else{
			relocate(curr, prev, found); //Se encontró la clave en curr. Tengo que pasarlo al siguiente
		}
			
		return found.value;
	}
	

	private void relocate(HeaderNode<K, V> origin, HeaderNode<K,V> prev, Node<K, V> found) {

		if(origin.next == null){ //Estaba al final
			origin.next = new HeaderNode<K,V>(origin.accesses + 1, found);
			last = origin.next;
		}
		else if(origin.next.accesses == origin.accesses + 1){ //Actualizo el acceso del elemento
			origin.next.addNode(found); 
		}
		else{
			HeaderNode<K,V> newHeader = new HeaderNode<K,V>(origin.accesses + 1, found);
			newHeader.next = origin.next;
			origin.next = newHeader;
			if(last.accesses < newHeader.accesses){ //Justo quedó al final
				this.last = newHeader;
				System.out.println("Max: " + newHeader.accesses);
			}
		}
		
		//Tengo que eliminar el origen si quedo vacio (no quedan elementos con esa cantidad de accesos)
		if(origin.isEmpty())
			if(prev == null){ //Era el primero
				first = origin.next;
			}
			else{
				prev.next = origin.next;
			}
		
	}

	@Override
	public void put(K key, V value) {
		Node<K,V> found = null;
		
		HeaderNode<K,V> prev = null;
		HeaderNode<K,V> curr = first;
		
		while(curr != null && (found = curr.popNode(key)) == null){
			prev = curr;
			curr = curr.next;
		}
		
		if(found == null){ // No se encontró
			if(first == null || first.accesses != 0){
				HeaderNode<K,V> newF = new HeaderNode<K,V>(0, null);
				newF.next = first;
				first = newF;
			}
			first.addNode(new Node<K,V>(key,value));
		}
		else{ //Se encontró la clave en curr.
			found.value = value;	//Actualizo el valor
			relocate(curr, prev, found); //Y debo reubicarlo

		}

	}

	@Override
	public K getMostAccessed() {
		return last.first.key;
	}

	@Override
	public void removeLeastAccessed() {
		if(first == null || first.isEmpty())
			return;
		
		first.removeFirst();
		
		if(first.isEmpty()){	//La primera quedo vacia
			first = first.next;
			if(first == null) // La primera era la unica
				last = null;  // Tengo que actualizar last
			
		}
			
			
	}
	
	

	
		public static void main(String[] args) {

			CustomMapImpl<String, String> m = new CustomMapImpl<String, String>();

			System.out.println(m.get("k1")); // Imprime null
			
			m.put("k1", "v1"); // Agrega k1=v1
		
			
			m.put("k2", "v2"); // Agrega k2=v2
			
			m.put("k3", "v3"); // Agrega k3=v3

			System.out.println(m.get("k2")); // Imprime v2
			
			System.out.println(m.getMostAccessed()); // Imprime k2

			System.out.println(m.get("k1")); // Imprime v1

			System.out.println(m.get("k2")); // Imprime v2

			m.removeLeastAccessed(); // Elimina k3

			m.removeLeastAccessed(); // Elimina k1

			m.put("k4", "v4"); // Agrega k4=v4

			m.put("k4", "v5"); // Actualiza k4=v5

			m.put("k4", "v6"); // Actualiza k4=v6

			System.out.println(m.get("k4")); // Imprime v6

			System.out.println(m.getMostAccessed()); // Imprime k4

			m.removeLeastAccessed(); // Elimina k2

			m.removeLeastAccessed(); // Elimina k4
	}
}
