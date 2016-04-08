package tp2_eda;

import java.util.Random;

public class RoundRobin {
	long duration;
	int interval;
	int hourglass; //Relojito hasta agregar la proxima tarea
	Processor processor;
	
	
	Random rand;
	CircularList<Task> tasks = new CircularList<Task>();
	public RoundRobin(long duration, int interval){
		this.duration = duration;
		this.interval = interval;
		hourglass = interval;
		rand = new Random(System.currentTimeMillis());
		processor = new Processor();
	}
	
	
	public void start(){
		while(duration-- > 0){
			if(hourglass ==  0){
				Task ta;
				tasks.add(ta = new Task(rand.nextInt(30)));
				System.out.print("Adding task: ");
				System.out.print(ta);
				System.out.print(" with time:");
				System.out.println(ta.remainingTime());
				hourglass = interval;
			}
			hourglass--; //Baja el relojito
			
			if(!tasks.isEmpty()){
				if(!processor.busy()){
					Task tp = tasks.next();
					System.out.print("To process task: ");
					System.out.println(tp);
					System.out.print("Remaining time pre-pr:");
					System.out.println(tp.remainingTime());
					
					processor.assign(tp);
					
					System.out.print("Remaining time post-pr: ");
					System.out.println(tp.remainingTime());
					
					if(tp.remainingTime() <= 0){
						System.out.print("Removing task  ");
						System.out.println(tp);
						tasks.removeLast();
						System.out.println(tasks.contains(tp));
					}
				}
				}
			
				processor.process();
			
			}
			
		}
		public static void main(String[] args) {
			RoundRobin rr = new RoundRobin(100, 15);
			rr.start();
			
			int i=0;
			while(i++ < 20){
				System.out.println(rr.tasks.next());
			}
		}
}
	

