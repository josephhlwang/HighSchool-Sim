public class Ending{   
   // Ending class
   // Glorified 'Event' 
   // Works similar to an Event where it has different requirements to access it or 'win' it
   // Has varying texts for different scenarios
	private String title;
	private int luckReq;
	private int happinessReq;
	private int stat1; 
	private int stat1Req;
	private int stat2;
	private int stat2Req;  
	private String endingText; 
	
   // Constructors
	public Ending(String title, int lReq, int hReq, int statType1, int stat1Req, int statType2, int stat2Req, String text){
		this.title = title; 
		luckReq = lReq;
		happinessReq = hReq; 
		stat1 = statType1; 
		this.stat1Req = stat1Req; 
		stat2 = statType2;
		this.stat2Req = stat2Req;
		endingText = text;   
	}
	
   // Accessors and mutators
	public int getLuckReq(){
		return luckReq;
	}
	
	public int getHapReq(){
		return happinessReq;
	}
	
	public int getStat1(){
		return stat1;
	}
	
	public int getStat1Req(){
		return stat1Req;
	}
	
	public int getStat2(){
		return stat2;
	}
	
	public int getStat2Req(){
		return stat2Req;
	}
	
	public String getText(){
		return endingText; 
	}
	
	public String getTitle(){
		return title; 
	}
	
}
