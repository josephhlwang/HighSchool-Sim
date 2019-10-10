public class Routine extends Event{

   // Child of Event
   // Routine events are similar to Random, however they cannot occur multiple times
	public Routine(String id, int statType, int statReq, int changeType, int changeAmount, EventPhase[] phases){
		super(id, statType, statReq, changeType, changeAmount, phases);	
	}
}
