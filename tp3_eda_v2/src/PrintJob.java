
public class PrintJob {
	private int creationTime;
	
	public PrintJob(int creationTime) {
		this.creationTime = creationTime;
	}
	
	public int age(int currentTime){
		return creationTime - currentTime; //El tiempo va bajando
	}
	
	
	
}
