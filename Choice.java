public class Choice {          // class directly involved with storing information of 'choices' with individual phases of events
   private int id;
   private String choiceText; //the text that actually makes up the choice
   private String changeToStory; //the text that is added to the phase text of the next phase if you make a certain decision
	
   // Constructor
   public Choice (int id, String text, String changeToStory){
      id = this.id;
      choiceText = text;  
      this.changeToStory = changeToStory; 
   } 
	
   // Accessors and mutators 
   public String getChangeToStory(){
      return changeToStory; 
   }
	
   public void setChangeToStory(String s){
      changeToStory = s; 
   }
   
   public String getChoiceText(){
      return choiceText; 
   }
	
   public String toString(){
      return choiceText; 
   }
}
