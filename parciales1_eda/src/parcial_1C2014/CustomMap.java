package parcial_1C2014;

public interface CustomMap<K, V> {

/**
* O(N)
* Retorna el valor asociado a la clave, o null si la clave no existe.
*/

public V get(K key);

/**
* O(N)
* Agrega un par clave/valor al mapa. Si la clave ya existe, la actualiza
* con el nuevo valor (en este caso esta operación se cuenta como un acceso más
* a la clave).
*/
public void put(K key, V value);

/**
* O(1)
* Retorna la clave que más veces fue accedida.
*/
public K getMostAccessed();

/**
* O(1)
* Elimina del mapa la clave (y valor) que menos veces fue accedida.
* Se pueden realizar sucesivas llamadas a este método.
*/
public void removeLeastAccessed();
}