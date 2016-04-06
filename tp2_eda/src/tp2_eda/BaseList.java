package tp2_eda;

public class BaseList<T> {
	private T value;
	private BaseList<T> next;

	public BaseList() {
		this(null, null);
	}

	public BaseList(T v) {
		this(v, null);
	}

	public BaseList(T v, BaseList<T> n) {
		value = v;
		next = n;
	}


	public void add(T v) {
		if (value != null) {
			BaseList<T> list = new BaseList<T> (value, next);
			next = list;
		}
		value = v;
	}

	public BaseList<T> remove(T v) {
		return remove(v, this);
	}

	private BaseList<T> remove(T v, BaseList<T> n) {
		if (n == null)
			return n;
		if (v.equals(n.value))
			return n.next;
		n.next = remove(v, n.next);
		return n;
	}

	public boolean contains(T v) {
		return contains(v, this);
	}

	private boolean contains(T v, BaseList<T> n) {
		if (n == null)
			return false;
		if (v.equals(n.value))
			return true;
		return contains(v, n.next);
	}
	
	
	//count: retorna la cantidad de elementos que cumplen con una determinada condición.
	public int count(Criterion<T> c){
		int sum = c.satisfies(value) ? 1 : 0;
		if(next == null){
			return sum;
		}
		else
			return sum + next.count(c);
	}
	
	// filter: retorna una nueva lista con aquellos elementos que cumplen una condición.
	public BaseList<T> filter(Criterion<T> c){
		if(!c.satisfies(value)){
			return next == null ? null : next.filter(c);
		}
		else{
			BaseList<T> list = new BaseList<T>(value);
			list.next = next == null ? null : next.filter(c);
			return list;
		}
			
			
	}
	
	
	// map: dada una función f: T -> S, retorna una nueva lista de tipo S, con el resultado de
	// aplicarle la función a cada elemento de la lista.
	public <S> BaseList<S> map(Transformer<T,S> t){
		BaseList<S> mapped = new BaseList<S>(t.transform(value));
		
		if(next != null)
			mapped.next = next.map(t);

		return mapped;

		
	}
	
	
//	inject: dada una función f: S,T -> S y un valor inicial de tipo S, retorna el resultado de
//	evaluar la función para cada elemento de la lista, utilizando como primer argumento el valor
//	retornado por la función en el nodo anterior, y como segundo argumento el valor del nodo
//	actual. Para el primer nodo usa como primer argumento el valor inicial.
	
	public <S> S inject(Injector<T,S> i, S seed){
		S s = i.apply(seed, value);
		if(next == null)
			return s;
		
		return next.inject(i, s);
	}
	
	
	
	public BaseList<T> reverse(){
		BaseList<T> reversed = reverse(null);
		return reversed;
	}
	
	public BaseList<T> reverse(BaseList<T> prev){
		if(next == null){
			next = prev;
			return this;
		}
		else{
			BaseList<T> tail = next;
			next = prev;
			return tail.reverse(this);
		}
	}

	
	
	public static void main(String[] args) {
		BaseList<Integer> l = new BaseList<Integer>(1);
		l.add(2);		
		l.add(3);
		l.add(4);
		l.add(5);
		l.add(6);
		l.add(7);
		l.add(8);
		l.add(9);
		l.add(10);
		l.add(11);
		l.add(12);

		BaseList<Integer> f = l.filter(new Criterion<Integer>(){

			@Override
			public boolean satisfies(Integer elem) {
				return elem > 29;
			}
			
		});
		
		
		BaseList<Integer> t = l.map(new Transformer<Integer, Integer>(){

			@Override
			public Integer transform(Integer elem) {
				return elem%3;
			}
			
		});

		
		BaseList<Integer> curr = l.reverse();
		while (curr != null){
			System.out.println(curr.value);
			curr = curr.next;
		}



	}
	
}
