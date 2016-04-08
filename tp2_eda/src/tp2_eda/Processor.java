package tp2_eda;

public class Processor {
	private final int quantum = 10;
	private boolean busy;
	private int workTime;
	private Task t;
	
	public Processor(){
		
	}
	
	public boolean busy(){
		return busy;
	}
	
	public void assign(Task t){
		if(busy())
			throw new IllegalStateException();
		
		workTime = t.remainingTime() < quantum ? t.remainingTime() : quantum;
		t.process(workTime);
		this.t = t;
		busy = true;
	}
	
	public void process(){
		if(workTime > 0){
			workTime--;
			System.out.print("Processing task  ");
			System.out.print(t);
			System.out.print("  worktime: ");
			System.out.println(workTime);
		
		if(workTime == 0)
			busy = false;
		}
	}
	
	
	
	
}
