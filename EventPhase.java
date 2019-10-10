public class EventPhase {
   private Choice [] EpChoices; // Array to hold the current phase choices
   private int numChoices;      // Number of choices
   private String phaseText;    // Text for the current phase
   private String baseText;     // Original text for phase
	
   // Constructor
   public EventPhase (String phaseText, Choice [] choices){
      this.phaseText = phaseText;
      numChoices = choices.length; 
      EpChoices = new Choice[numChoices];
      for (int i = 0; i < numChoices; i++){
         EpChoices[i] = choices[i]; 
      }
      baseText = phaseText;
   }
	
   // Plays (or prints rather) the text for the current choice (or phase of the event)
   public void playPhase(){
      System.out.println(phaseText);
      for (int i = 0; i < numChoices; i++){
         System.out.println(i + ": " + EpChoices[i].getChoiceText()); 
      }
      System.out.println();
   }
		
   // Accessors and mutators   
   public int getNumChoices(){
      return EpChoices.length; 
   }
	
   // Simply a function to add text to pre-existing text
   public void appendText(String text){
      phaseText = text + phaseText; 
   }
	
   public String getPhaseText(){
      return phaseText;
   }
	
   public String getChoiceChangeToStory(int choiceNum){
      return EpChoices[choiceNum].getChangeToStory();
   }
	
   public void setChangeToStory(int choiceNum, String s){
      EpChoices[choiceNum].setChangeToStory(s);
   }
   
   public String getBaseText(){
      return baseText; 
   }
	
   // Changes the phase text back to its original
   public void resetPhaseText(){ 
      phaseText = baseText; 
   }
   
   public String toString(){
      String s = "";
      for (int i = 0; i < numChoices; i++){
         s += i + ": " + EpChoices[i].toString() + "\n"; 
      } 
      s+= "\n";
      return s; 
   }
}
