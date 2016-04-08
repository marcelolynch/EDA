package tp2_eda;

public class Task {
	private static int count = 0;
	private int time;
	private int id;
	
	public void process(int processingTime){
		time -= processingTime;
	}
	
	public int remainingTime(){
		return time;
	}
	
	public Task(int time){
		this.time = time;
		count++;
		this.id = count;
	}
	
	public String toString(){
		return "Task " + new Integer(id).toString();
	}
}
