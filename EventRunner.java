
import java.io.*;
import java.util.*;

public class EventRunner{
   private Player player; 
   private Event [] events; 
   private Ending [] endings; 
   int eventNum;  
   private ArrayList <Integer> specialMonths = new ArrayList<Integer>();
	/* Reference for ids of the stats
	 * 0 - no stat req
	 * 1 - linguistic intelligence
	 * 2 - spatial intelligence
	 * 3 - logical intelligence
	 * 4 - expression charisma
	 * 5 - social charisma 
	 * 6 - luck
 	 * 7 - happiness
	 * 8 - strength
	 */
	 
	 
	/* Number for each event type
	 * 1 - Routine event
	 * 2 - Random event (reusable)
	 * 3 - Predetermined event (tracks time)
	 */
	 
   public EventRunner(Player p, String eventFile, String endingsFile){
      try {
         player = p;
      	
         BufferedReader in = new BufferedReader(new FileReader(eventFile)); 		//reads in event information
         BufferedReader in2 = new BufferedReader(new FileReader(endingsFile)); 		//reads in endings information 
         int eventType; 
         int numPhases; 
         int numChoices; 
         int statReqType; 	//type of stat required
         int statReq; 		//minimum of that stat needed
         String id; 
         int changeType; 
         int changeAmount; 
      	
         String title;
         int numEndings; 
         int luckReq, hapReq, stat1, req1, stat2, req2, numLinesOfText;
         String text = ""; 
      	
         String flush; //gets rid of empty lines in the file
      	 
         numEndings = Integer.parseInt(in2.readLine()); 
         endings = new Ending[numEndings];
         for(int i = 0; i < numEndings; i++){				//reads in all the endings in the text file 
            title = in2.readLine();
            luckReq = Integer.parseInt(in2.readLine());
            hapReq = Integer.parseInt(in2.readLine());
            stat1 = Integer.parseInt(in2.readLine());
            req1 = Integer.parseInt(in2.readLine());
            stat2 = Integer.parseInt(in2.readLine());
            req2 = Integer.parseInt(in2.readLine());
         
            for (int j = 0; j < 3; j++){
               text+= in2.readLine() + "\n";
            }
            flush = in2.readLine();
            endings[i] = new Ending (title, luckReq, hapReq, stat1, req1, stat2, req2, text);
            text = "";
         }
      	
         EventPhase [] phases;
         String [] phaseText; //base text that makes up each phase
         Choice [] choices; 
      				
         eventNum = Integer.parseInt(in.readLine()); // number of events
         events = new Event[eventNum];
         for (int i = 0; i < eventNum; i++){
            eventType = Integer.parseInt(in.readLine());	 		//reads in type of event
            id = in.readLine();                          			//reads in the ID for the event
            statReqType = Integer.parseInt(in.readLine());		 	//type of stat required
            statReq = Integer.parseInt(in.readLine());		 		//minimum stat number that needs to be met
            numPhases = Integer.parseInt(in.readLine()); 			//reads in number of phases in an event		
            phases = new EventPhase [numPhases]; 				//creates array of phases
            phaseText = new String [numPhases];
         	
            for (int j = 0; j < numPhases; j++){
               phaseText[j] = in.readLine(); 	
               numChoices = Integer.parseInt(in.readLine()); 	//reads in number of choices of a phase
               choices = new Choice [numChoices]; 		//creates array of choices for a phase
            	
               for (int k = 0; k < numChoices; k++){
                  String choiceText = in.readLine();
                  String changeToStoryText = in.readLine();
                  choices[k] = new Choice(k,choiceText, changeToStoryText);
               	//first reads in the text of the choice
               	//right below it should be its change to the story if you pick it 
               }
               phases[j] = new EventPhase(phaseText[j], choices); 
            }
            changeType = Integer.parseInt(in.readLine());		//type of stat to be changed
            changeAmount = Integer.parseInt(in.readLine());		//amount it is changed by
         	
            if (eventType == 1){
               events [i] = new Routine(id, statReqType, statReq, changeType, changeAmount, phases);
            } else if (eventType == 2){ // recyclable 
               events [i] = new Random(id, statReqType, statReq, changeType, changeAmount, phases);
            } else if (eventType == 3){ //keeps track of time
               int month = Integer.parseInt(in.readLine());
               events [i] = new Predetermined(id, statReqType, statReq, changeType, changeAmount, month, phases);
               events[i].setMonth(month);
            }
            flush = in.readLine(); //gets rid of the empty space between events 
         }
      } catch (IOException e){
         System.out.println("Error in reading file.");
      }
      findSpecialMonths();	// finds months with a predetermined event
      sortEvents();		// sorts the events array
   }
	
   public void sortEvents(){
   	/*
   	 * Sorts events by event type in order of value
   	 * 		Predetermined - 1
   	 * 		Routine - 2
   	 * 		Random - 3
   	 * 	-Not that these aren't the identifiers for event type but for the value of each event
   	 * 	-Random events have the highest value(sorted last in the array) because they are the only event type that can occur multiple times
   	 *	and so are sorted to the back of the array to decrease the likelihood of them occuring before other events are exhausted
   	 *  -The events of each individual stat TYPE (not value) are sorted from highest stat requirement to highest stat requirement
   	 *  	-All routine events will be run eventually and methods have already been set in place so that each predetermined event will
   	 *  	eventually be run (assuming there is only one predetermined event for each special month)
   	 *  	-Sorting by stat mainly makes sure the reusable Random events occur at a somewhat equal probability
   	 */
      boolean sorted = false; 
      for (int i = events.length - 1; i > 0 && !sorted; i--){		//bubble sort
         for (int j = 0; j < i; j++){ 
            sorted = true; 
            Event temp; 
            if (events[j].getEventValue() > events[j+1].getEventValue()){
               temp = events[j];
               events[j] = events[j+1];
               events[j+1] = temp;
               sorted = false; 
            } else if (events[j].getEventValue() == events[j+1].getEventValue()){
               if (events[j].getStatReq() < events[j+1].getStatReq()){
                  temp = events[j]; 
                  events[j] = events[j+1]; 
                  events[j+1] = temp; 
                  sorted = false;
               }
            }
         }
      }
   }
	
   private void findSpecialMonths(){ //records all months with a predetermined event
      for (int i = 0; i < events.length; i++){
         specialMonths.add(events[i].getMonthReq());
      }
   }
	
   private boolean checkIfSpecialMonth(int month){ //checks if a given month has a predetermined event
      for (int i: specialMonths){
         if (month == i){
            return true;
         }
      }
      return false;
   }
   
   private Event evaluateStatsForEvents(int eventType, int month){
      if (checkIfSpecialMonth(month)){ //if a predetermined event occurs in this month change to automatically search for that event
         eventType = 3; 
      }
      
      Event pE = events[0]; // Potential event 
      for (int h = 0; h < 2; h++){
         for (int i = 0; i < events.length; i++){ 
            int statType = events[i].getStatType();
            
            switch (statType){			// checks if the player's stat meets the requirement
               case 0: 
                  pE = events[i]; 
                  break;
               case 1:
                  if (getAStat(1) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break;
            	 	
               case 2: 
                  if (getAStat(2) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break; 
            	
               case 3:
                  if (getAStat(3) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break;
            	
               case 4: 
                  if (getAStat(4) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break; 
            	
               case 5:
                  if (getAStat(5) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break;
            	
               case 6: 
                  if (getAStat(6) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break; 
            	
               case 7:
                  if (getAStat(7) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break;
            	
               case 8: 
                  if (getAStat(8) >= events[i].getStatReq()){
                     pE = events[i]; 
                  }
                  break; 
               default:
                  break;
            } 
         	
            if (eventType == 1) {
               if (pE instanceof Routine && !pE.getOccured()){	
                  pE.setOccured(true);			
                  return pE; 
               }
            } else if (eventType == 2){
               if (pE instanceof Random) {
                  return pE;
               }
            } else if (eventType == 3){
               if (pE instanceof Predetermined && !pE.getOccured()){
                  if (pE.getMonthReq() == month){
                     pE.setOccured(true);
                     return pE; 
                  }
               }
            }
            		// if an event of type 1 or 3 was needed but all have occured,
            		// sets the search parameter to type 2 which are the recyclable events
         } 		// end of for loop			
         eventType = 2; 
      }
      return null;
   }	
	
   public void rollEvent(int month){
      Event e;
      e = evaluateStatsForEvents((int)(Math.random()*3) + 1, month); 
      e.play();
      setAStat(e.getChangeType(), e.getChangeAmount());
      shuffleEvents();
   }
	
   public void rollEnding(){		//causes the appropriate ending to play, called at end of game
      boolean occurred = false;
      for (int i = 0; i < endings.length && !occurred; i++){
         if (getAStat(endings[i].getStat1()) >= endings[i].getStat1Req() && getAStat(endings[i].getStat2()) >= endings[i].getStat2Req()){
            System.out.println("Ending: " + endings[i].getTitle());
            System.out.println(endings[i].getText());
            occurred = true;
         }
      }
   }
   
   
	
   public int getAStat(int statType){		// general accessor for a player's stat, implemented so stats can be identified by an integer
      switch (statType){ 
         case 0: 
            return 0;				
         case 1:
            return player.getStats().getLinguisticIntelligence();
         case 2: 
            return player.getStats().getSpatialIntelligence();
         case 3:
            return player.getStats().getLogicalIntelligence();
         case 4: 
            return player.getStats().getExpressionCharisma();
         case 5:
            return player.getStats().getSocialCharisma();
         case 6: 
            return player.getStats().getLuck();
         case 7:
            return player.getStats().getHappiness();
         case 8: 
            return player.getStats().getStrength();
         default:
            break;
      }
      return -1; 
   }
	
   public void setAStat(int statType, int addBy){	// general mutator for a player's stat, implemented so stats can be identified by an int
      switch (statType){ 
         case 0: 
            break;				
         case 1:
            player.getStats().setLinguisticIntelligence(getAStat(statType) + addBy);
            break;
         case 2: 
            player.getStats().setSpatialIntelligence(getAStat(statType) + addBy);
            break; 
         case 3:
            player.getStats().setLogicalIntelligence(getAStat(statType) + addBy);
            break;	
         case 4:
            player.getStats().setExpressionCharisma(getAStat(statType) + addBy);
            break; 
         case 5:
            player.getStats().setSocialCharisma(getAStat(statType) + addBy);
            break;
         case 6: 
            player.getStats().setLuck(getAStat(statType) + addBy);
            break;
         case 7:
            player.getStats().setHappiness(getAStat(statType) + addBy);
            break;
         case 8: 
            player.getStats().setStrength(getAStat(statType) + addBy);
            break;
      }
   }


   private void shuffleEvents() {		//shuffles the recyclable events to avoid repetitions in recyclable events
      int startOfRandom= 0;
      boolean found = false;
   	
      for (int i = 0; i < events.length && !found; i++){ 
      	//method is being called after sortEvents() so all type random events are at the end of the events array
         if (events[i] instanceof Random) {
            found = true;
            startOfRandom = i;
         }
      }
   	
      int numRandom = events.length - startOfRandom; 
      Event [] storage = new Event[numRandom]; 
      for (int i = startOfRandom; i < events.length; i++) {
         storage[i-startOfRandom] = events[i]; 
      	//stores all the random events in an array to have their indexes randomized
      }
      int random;
      Event temp;
      for (int i = storage.length - 1; i > 0; i--) { //loops through entire array doing random switches
         random = (int) (Math.random() * storage.length);
         temp = storage[i];
         storage[i] = storage[random];
         storage[random] = temp; 
      }
   	
      for (int i = startOfRandom; i < events.length; i++) {
         events[i] = storage[i - startOfRandom];
      	//rearranges the main array with the new indexes 
      }
   }
}
