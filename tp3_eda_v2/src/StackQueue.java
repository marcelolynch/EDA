
public class StackQueue<T> {
	private Stack<T> queue = new Stack<T>();
	private Stack<T> aux = new Stack<T>();
	
	public T dequeue(){
		return queue.pop();
	}
	
	public void enqueue(T elem){
		fill(queue, aux);
		queue.push(elem);
		fill(aux, queue);
		
	}
	
	private void fill(Stack<T> from, Stack<T> to){
		while(!from.isEmpty()){
			to.push(from.pop());
		}
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	
	public static void main(String[] args) {
		StackQueue<String> q = new StackQueue<String>();
		
		q.enqueue("Hola");
		q.enqueue("CHelo");
		q.enqueue("Bue");
		q.enqueue("Na");
		
		while(!q.isEmpty())
			System.out.println(q.dequeue());

				
	}
	
	
}
