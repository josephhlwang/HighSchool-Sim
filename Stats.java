
public class Stats {
   // Object which stores all of the individual stats of a player
	private int linguisticIntelligence;
	private int spatialIntelligence;
	private int logicalIntelligence;
	private int expressionCharisma;
	private int socialCharisma;
	private int luck;
	private int happiness;
	private int strength;
	//constructor
	public Stats (int linInt, int spatInt, int logInt, int expChar, int socChar, int luck, int happy, int str) {
		linguisticIntelligence = linInt;
		spatialIntelligence = spatInt;
		logicalIntelligence = logInt;
		expressionCharisma = expChar;
		socialCharisma = socChar;
		this.luck = luck;
		happiness = happy;
		strength = str;
	}
	//accessors and mutators
	public int getTotalIntelligence() {
		return this.getSpatialIntelligence() + this.getLogicalIntelligence() + this.getLinguisticIntelligence();
	}
		
	public int getSpatialIntelligence() {
		return spatialIntelligence;
	}
	
	public void setSpatialIntelligence (int spatInt) {
		spatialIntelligence = spatInt;
	}
	
	public int getLogicalIntelligence () {
		return logicalIntelligence;
	}
	
	public void setLogicalIntelligence (int logInt) {
		logicalIntelligence = logInt;
	}
	
	public int getLinguisticIntelligence() {
		return linguisticIntelligence;
	}
	
	public void setLinguisticIntelligence(int linInt) {
		linguisticIntelligence = linInt;
	}
	
	public int getTotalCharisma () {
		return this.getExpressionCharisma() + this.getSocialCharisma();
	}
		
	public int getExpressionCharisma () {
		return expressionCharisma;
	}
	
	public void setExpressionCharisma(int expChar) {
		expressionCharisma = expChar;
	}
	
	public int getSocialCharisma () {
		return socialCharisma;
	}
	
	public void setSocialCharisma (int socChar) {
		socialCharisma = socChar;
	}
	
	public int getLuck () {
		return luck;
	}
	
	public void setLuck (int luck) {
		this.luck = luck;
	}
	
	public int getHappiness () {
		return happiness;
	}
	
	public void setHappiness (int happy) {
		happiness = happy;
      if (happiness > 10)
      {
         happiness = 10;
      }
	}
	
	public int getStrength () {
		return strength;
	}
	
	public void setStrength (int str) {
		strength = str;
	}
	//output string
	public String toString(){
		String str = "Stats:";
      str+= "\nTotal Intelligence: " + this.getTotalIntelligence();
		str+= "\nLinguisticIntelligence: " + this.getLinguisticIntelligence();
		str+= "\nSpatial Intelligence: " + this.getSpatialIntelligence() + "\nLogical Intelligence: " + this.getLogicalIntelligence();
		str+= "\nTotal Charisma: " + this.getTotalCharisma() + "\nExpression Charisma: " + this.getExpressionCharisma() + "\nSocial Charisma: " + this.getSocialCharisma();
		str+= "\nLuck: " + this.getLuck() + "\nHappiness: " + this.getHappiness() + "\nStrength: " + this.getStrength();
		return str;
	}
}
