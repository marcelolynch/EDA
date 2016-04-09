import java.util.Random;


public class Printer {
	static double arrivalProbability = 0.13;
	static int maxServiceTime = 16;
	static int minServiceTime = 2;
	
	private int hourglass;
	private double queueAvg;
	private double waitingAvg;
	private int totalJobs;
	private int jobsDone;
	
	
	private Random rand = new Random(System.nanoTime());
	private int simulationTime;
	
	private Queue<PrintJob> queue = new Queue<PrintJob>();
	
	public Printer(int simTime) {
		simulationTime = simTime;
	}
	
	private boolean busy(){
		return hourglass > 0;
	}

	public void simulate(){
		int simTime = simulationTime;
		
		while(simTime-- > 0){
			if(rand.nextDouble() < arrivalProbability){
				queue.enqueue(new PrintJob());
				totalJobs++;
			}
			
			if(busy() || queue.isEmpty()){
				hourglass = hourglass > 0 ? hourglass-1 : 0;	// Sigue trabajando o esperando
			}else{
				jobsDone++;
				System.out.print("To print: ");
				System.out.print(queue.dequeue());//Saco un trabajo
				
				double eta = minServiceTime + rand.nextDouble()*(maxServiceTime - minServiceTime + 1); 
				hourglass = (int)eta; // La pone a trabajar
				
				waitingAvg = (waitingAvg + hourglass)/2.0;
				
				System.out.print("   ETA: ");
				System.out.println(hourglass);
			}
			queueAvg = (queueAvg + queue.size()) / 2.0; //Actualiza el promedio de trabajos encolados
		}
		
		System.out.print("AVG_QUEUE: ");
		System.out.println(queueAvg);
		System.out.print("AVG WAITING: ");
		System.out.println(waitingAvg);
		System.out.print("TOTAL JOBS: ");
		System.out.println(totalJobs);
		System.out.print("ATTENDED: ");
		System.out.println(jobsDone);


		
		}
	
	public static void main(String[] args) {
		new Printer(100000).simulate();
	}
	
}
