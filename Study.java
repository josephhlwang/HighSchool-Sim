public class Study{
      private double hourStudy;
      private double effectiveHours;
      private final static double MAXHOURS=2;
      
   	//Constructor
       public Study(double hourStudied ){
         hourStudy=hourStudied;
         effectiveHours=0;
      }
       public double getHoursStudy(){
         return hourStudy;
      }
       public void setHoursStudy(double hourStudied){
         hourStudy=hourStudied;
      }
       public double getEffectiveHours(){
         return effectiveHours;
      }
       public void setEffectiveHours(double effective){
         effectiveHours=effective;
      }
      
      
   	//recursive method to calculate effective hours
       public void effectiveCalculator(double hour,double effectiveness,boolean firstTime,double hourDecrease){
      //Adds in the max hours at 100% efficiency
         if(firstTime==true){    
            if (hourStudy<=MAXHOURS){
               effectiveHours+=hour;
            }
            else{
               effectiveHours+=MAXHOURS;
               effectiveCalculator(hour-MAXHOURS,effectiveness*0.9,false,hourDecrease);
            }
         }
         //Recursion part of code that decreases effectiveness and adds effective hours
         else if(hour>0){
            effectiveHours+=hourDecrease*effectiveness;
            effectiveCalculator(hour-hourDecrease,effectiveness*0.9,false,hourDecrease+2);
          
         }
      }
	}