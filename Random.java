public class Random extends Event{

   // Child of Event
   // Random events are recycable and can occur repeatedly
	public Random(String id, int statType, int statReq, int changeType, int changeAmount, EventPhase[] phases){
		super(id, statType, statReq, changeType, changeAmount, phases);	
	}
}