
public interface SortedList<T> {
	
	/** Agrega un elemento a la lista (en la posicion correspondiente 
	 * segun el comparador */
	
	//O(N)
	public void add(T elem);
	
	/**
	 * Elimina el elemento agregado mas recientemente,
	 * Se lo puede invocar sucesivas veces, elminando los elementos
	 * en el orden inverso al que fueron agregados a la lista
	 */
	
	//Debe tener O(1)
	public void undo();
	
	//O(N)
	/** Imprime ,los elementos de la lista */
	public void print();


}
