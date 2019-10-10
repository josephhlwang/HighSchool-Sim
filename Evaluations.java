abstract class Evaluations
{
   // This class is responsible for simulating the marks for a player
   // Has children who are evaluations for specific stats
   // They are evaluted a little bit differently from each other and use the corresponding stat to calculate the mark 
   // Variables
	private String name;
	private int minCharisma;
	private double mark;
	
	Player user;
	
	// constructor
	public Evaluations(String eval, Player plyr)
	{
		name = eval;
		user = plyr;
		minCharisma = calcCharis();
	}
	
	// accesors and mutators
	public String getName()
	{
		return name;
	}
	
	public void setName(String nm)
	{
		name = nm;
	}
	
	public int getMinCharisma()
	{
		return minCharisma;
	}
	
	public void setMinCharisma(int charis)
	{
		minCharisma = charis;
	}
	
	public double getMark()
	{
		return mark;
	}
	
	public void setMark(double mrk)
	{
		mark = mrk;
	}
	
	// calculate independent minimum charisma
	private int calcCharis()
	{
		int charis;
		charis =(int)( Math.random() * 10 + 5);
		return charis;
	}
	
	// calculates the boost for a teacher
	public double teacherBoost(int playerCharis, double mark)
	{
		if (playerCharis > minCharisma)
		{
			mark = mark + (Math.random()*5);
		}
		else
		{
			mark = mark - (Math.random()*8);
		}
		return mark;
	}
	
	// calculates the boost for current luck
	public double luckBoost(int playerLuck, double mark)
	{
		int luck = playerLuck;
		int choose = (int) Math.random()*10;
		if (luck > choose)
		{
			mark = mark + (Math.random()*3);
		}
		return mark;
	}
	
	// calculates independent mark
	abstract void calculateMark();
}