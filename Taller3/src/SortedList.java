
public interface SortedList<T> {
	
	/** Agrega un elemento a la lista (en la posicion correspondiente 
	 * segun el comparador */
	public void add(T elem);
	
	/**
	 * Elimina el elemento agregado mas recientemente,
	 * Se lo puede invocar sucesivas veces, elminando los elementos
	 * en el orden inverso al que fueron agregados a la lista
	 */
	public void undo();
	
	
	/** Imprime ,los elementos de la lista */
	public void print();


}
