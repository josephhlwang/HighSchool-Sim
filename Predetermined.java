public class Predetermined extends Event{

   // Child of Event
   // Only type of event which makes it so it can occur only on a specific date (e.g. prom)
	private int month; 
	public Predetermined(String id, int statType, int statReq, int changeType, int changeAmount, int month, EventPhase[] phases){
		super(id, statType, statReq, changeType, changeAmount, phases);	
		this.month = month; 
	}
	
	public int getMonthReq(){
		return month; 
	}
}