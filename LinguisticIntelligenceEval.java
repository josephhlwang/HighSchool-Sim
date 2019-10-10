public class LinguisticIntelligenceEval extends Evaluations
{
	public LinguisticIntelligenceEval(String name, Player plyr)
	{
		super(name,plyr);
	}
	
	// Calculates mark for linguistic intelligence courses
	// Uses the linguistic intelligence stat 
	public void calculateMark()
	{
		double mark = 0;
		int grdLvl = super.user.getGrade();
		int control = super.user.stats.getLinguisticIntelligence();
      int hapCap = super.user.stats.getHappiness();
		if (grdLvl <= 10)
		{
			if (control < 7)
			{
				mark = 50;
			}
			else 
			{
				mark = 75;
				// Measures to make sure that if the player has an excessively high stat
				// They cannot simply just get 100
            if (control > 15 && control < 30)
            {
               mark = mark + (control - 15) * 1;
            }
            else if (control > 30)
            {
               mark = mark + (Math.log10(control) * 10);
            }			
         }
		}
		else if (grdLvl >= 11)
		{
			if (control < 15)
			{
				mark = 50;
			} 
			else
			{
				mark = 70;
            if (control > 30 && control < 50)
            {
            mark = mark + (control - 30) * 1;
            }
            else if (control > 50)
            {
               mark = mark + (Math.log10(control) * 10);
            }
			}	
		}
		// Uses the boost fucntions of the Evaluations class to modify the mark
		mark = super.teacherBoost(super.user.stats.getSocialCharisma(),mark);
		mark = super.luckBoost(super.user.stats.getLuck(), mark);
      
      // Ensures no mark over 100
      if (mark > 100)
      {
         mark = 100;
      }
      
      // Punishes player for being unhappy
      if (hapCap <= 1)
      {
         mark-=30;
      }
      else if (hapCap <3)
      {
         mark-=20;
      }
      else if (hapCap <5)
      {
         mark-=10;
      }
      
		super.setMark(mark);
	}
}	
