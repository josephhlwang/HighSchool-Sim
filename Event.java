import java.util.*; 
abstract class Event{
   private String id;
   private boolean occured = false; 
   EventPhase[] ePhases; 
   private int eStatType; // each stat assignned a number from 1-9
   private int eStatReq; // minimum num of the stat that needs to be met
   private int eChangeType; 
   private int eChangeAmount; 
   private int month = -1; 
   
   public Event(String id, int statType, int statReq, int changeType, int changeAmount, EventPhase[] phases){
      // for simplicity sake only one stat req per event
      this.id = id; 
      eStatType = statType; 
      eStatReq = statReq; 
      eChangeType = changeType; 
      eChangeAmount = changeAmount; 
      ePhases = new EventPhase [phases.length];
      for (int i = 0; i < phases.length; i++){
         ePhases[i] = phases[i]; 
      }
   } 
   
   public int getStatType(){
      return eStatType; 
   }
   
   public boolean getOccured(){
      return occured; 
   }
   
   public void setOccured(boolean b){
      occured = b; 
   }
   
   public int getStatReq(){
      return eStatReq; 
   }
   
   public int getChangeType(){
      return eChangeType; 
   }
   
   public int getChangeAmount(){
      return eChangeAmount;
   }
	
   public int getMonthReq(){
      return month; 
   }
	
   public void setMonth(int i){
      month = i;
   }
   
   public int getEventValue(){
      /*Returns the priority of each event type when being sorted
       *Larger the number, the further back in the events array in eventRunner the event is sorted
       */
      if (this instanceof Routine){
         return 2; 
      } else if (this instanceof Random){
         return 3; 
      } else if (this instanceof Predetermined){
         return 1; 
      }
      return 0; 
   }
   
   public void play(){
      /*
       * Plays through a given event manages the appropriate decision making and text displaying methods of each phase in the event
       */
      Scanner sc = new Scanner(System.in); 
      int playerChoice;
      boolean valid = false;
      String flush; 
      for (int i = 0; i < ePhases.length; i++){
         System.out.println("Event: " + id);
         ePhases[i].playPhase();						// displays the phase text and choices of the event phase
         if (i != ePhases.length-1)
         {
            do
            {
               try{
                  System.out.print("Choice #: "); 
                  playerChoice = sc.nextInt();					// user inputs the choice
                  valid = makeDecision(playerChoice,i);
                  if (!valid || playerChoice <0 || playerChoice > 1)		// checks it the choice number is appropriate
                  {
                     System.out.println("Incorrect input. Try again.");		
                  }
               }
               catch (NumberFormatException nfx)				// catches invalid input
               {
                  System.out.println("Incorrect input. Try again.");
                  flush = sc.next();
                  playerChoice = -1;
               }
               catch (InputMismatchException imx)
               {
                  System.out.println("Incorrect input. Try again.");		// catches invalid input
                  flush = sc.next();
                  playerChoice = -1;
               }
            }
            while (!valid || playerChoice <0 || playerChoice > 1);		// continues prompt until a valid answer is given
         }
         System.out.println("");
      }
      System.out.println("Event over. Press enter to continue.");
      System.out.println("");
      sc.nextLine();
      sc.nextLine();
   }
   
   private boolean makeDecision(int choiceMade, int phaseNum){
      if (choiceMade >= 0 && choiceMade < ePhases[phaseNum].getNumChoices()){		// checks if the choice number is valid for the specific phase
         if (!ePhases[phaseNum + 1].getPhaseText().equals(ePhases[phaseNum + 1].getBaseText())){	// checks to see if the story has changed due to a previous decision being made
            ePhases[phaseNum + 1].resetPhaseText();							// resets the text of any changes so a given decision's impact on the story will make sense
         }
         ePhases[phaseNum + 1].appendText(ePhases[phaseNum].getChoiceChangeToStory(choiceMade));	// changes the story based off of the choice made
         return true;
      }
      else {		// if an invalid choice was entered, indicate that a decision wasn't made
         return false;
      }
   }
}
   
