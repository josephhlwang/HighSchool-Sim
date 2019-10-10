import java.io.*;
import java.util.*;

public class Course
{
// Variables
   private double currentMark = 0; // Curent mark in the course
   protected static int gradeLevel; // Current grade level of the course
   private String subject;		// Name of the subject
   private int numEval = 0;		// Number of evaluations performed
   private final int MAX_EVAL = 5;	// Maximum number of evaluations (equal to max months in a semester)
   Evaluations[] list = new Evaluations[MAX_EVAL];	// Creation of the evaluations array
   private final int NUM_STATS = 6;			// Number of stats
   private final int LEVELS = 2;			// Refers to junior and senior divisions of grades
   
   private String boostStat;	// Stat associated with the course

   String[] selection = new String[NUM_STATS]; 	// Storage for the stats
	
	// Storage for the files for the different courses
	// Works in paralell with selection []
   String[] fileSelect ={"logicalIntelligenceCourses.txt","spatialIntelligenceCourses.txt","linguisticIntelligenceCourses.txt","expressionCharismaCourses.txt","socialCharismaCourses.txt","strengthCourses.txt"};
   String[][] choices = new String[LEVELS][]; // Storage for the courses of that type, in both divisions 

// Constructors
   public Course (int grdLvl)
   {
      gradeLevel = grdLvl;
      try
      {
         BufferedReader in = new BufferedReader (new FileReader("statsList.txt"));
         for (int i =0; i < 6; i++)
         {
            selection[i] = in.readLine();
         }
         in.close();
      }
      catch(IOException iox)
      {
         System.out.println("Error in reading file.");
      }
   }

	// Accesors and mutators
   public double getCurrMark()
   {
      return currentMark;
   }

   public void setCurrMark(double mark)
   {
      currentMark = mark;
   }

   public int getGradeLevel()
   {
      return gradeLevel;
   }

   public void setGradeLevel(int grade)
   {
      gradeLevel = grade;
   }

   public String getSubject()
   {
      return subject;
   }

   public void setSubject(String sbj)
   {
      subject = sbj;
   }

   public int getNumEval()
   {
      return numEval;
   }

   public void setNumEval(int num)
   {
      numEval = num;
   }
	
   public String getBoostStat()
   {
      return boostStat;
   }
	
   public void setBoostStat(String stat)
   {
      boostStat = stat;
   }

// Process to allow user to pick course
   public void chooseCourse()
   {
      String flush;
      boolean valid = true;
      int choose = 0;
      int course = 0;
      Scanner sc = new Scanner(System.in);
   
   // Asks user to pick a class first based on stat
      System.out.println("Types of Courses:");
      for (int i = 0; i < NUM_STATS; i++)
      {
         System.out.println((i+1)+ " " +selection[i]);
      }
      // Input structure for user to input the type of class they want
      do
      {
         try
         {
            System.out.print("Please enter the number for the specific type of class for a stat: ");
            choose=sc.nextInt();
            if (!(choose >=1 && choose <=6))
            {
               System.out.println("Wrong input. Please enter the number again.");  
            }
         }
         catch(InputMismatchException imx)
         {
            System.out.println("Wrong input. Try again.");
            flush = sc.next();
         }
         catch(NumberFormatException nfe)
         {
            System.out.println("Wrong input. Try again.");
            flush = sc.next();
         }
      }while(!(choose >= 1 && choose <= 6));
   
   // Calls the method to allow this specific object to have a stat identity
      boostStatSelect(choose);
   
   // Based on user-selected stat, will read in courses for that specific stat
      int jr = 0;
      int sr = 0;
      try
      {
         BufferedReader in = new BufferedReader(new FileReader(fileSelect[choose-1]));
         
         jr = Integer.parseInt(in.readLine());
         choices[0] = new String[jr];
         for (int i = 0; i <jr; i++)
         {
            choices[0][i] = in.readLine();
         }
         
         sr = Integer.parseInt(in.readLine());
         choices[1] = new String[sr];
         for (int i = 0; i < sr; i++)
         {
            choices[1][i] = in.readLine();
         }
	 in.close();
      }
      catch(IOException iox)
      {
         System.out.println("Error in reading file.");
      }
   
      System.out.println("");
      
   // Asks user to select which course they would like to pick
      System.out.println("Available Courses for " + selection[choose-1] + ":");
   
      if (gradeLevel == 9 || gradeLevel == 10)
      {
         for (int i = 0; i < choices[0].length;i++)
         {
            System.out.println((i+1)+ " " + choices[0][i]);
         }
      }
      else
      {
         for (int i = 0; i < choices[1].length;i++)
         {
            System.out.println((i+1)+" " + choices[1][i]);
         }
      }
      
      //Input structure for specific course the user wants
      do
      {
         try
         {
            System.out.print("Please enter a number for the specific course accordingly: ");
            course=sc.nextInt();
            if (gradeLevel == 9 || gradeLevel == 10)
            {
               if (!(course >=1 && course <= jr))
               {
                  System.out.println("Wrong input. Please enter the number again.");
               
               }
            }
            else
            {
               if (!(course >= 1 && course <= sr))
               {
                  System.out.println("Wrong input. Please enter the number again.");
               }
            }
         }
         catch(InputMismatchException imx)
         {
            System.out.println("Wrong input. Try again.");
            flush = sc.next();
         }
         catch(NumberFormatException nfe)
         {
            System.out.println("Wrong input. Try again.");
            flush = sc.next();
         }
      }while(!(course >= 1 && course <= sr && gradeLevel > 10) && !(course >=1  && course <=jr && gradeLevel < 11));
   
   // Assigns this course object a name
      if (gradeLevel == 9 || gradeLevel == 10)
      {
         subject=choices[0][course-1];
      }   
      else
      {
         subject=choices[1][course-1];
      }
   
   }

//Calculates the current mark
   public void calcAverage()
   {
      double avg = currentMark;
      double recent = 0;
      for (int i = 0; i < MAX_EVAL; i++)
      {
         if (list[i] != null)
         {
            recent = list[i].getMark();
         }
      }
      currentMark = (currentMark*(numEval-1) + recent)/numEval;
   }

// Identifies this course with a specific stat 
   private void boostStatSelect (int num)
   {
      switch(num)
      {
         case 1:
            boostStat = "logicalIntelligence";
            break;
         case 2:
            boostStat = "spatialIntelligence";
            break;
         case 3:
            boostStat = "linguisticIntelligence";
            break;
         case 4:
            boostStat = "expressionCharisma";
            break;
         case 5:
            boostStat = "socialCharisma";
            break;
         case 6:
            boostStat = "strength";
            break;
      }
   }

	// Creates an evaluation based on the current stat of the associated course and the month (test 1 for month 1, etc)
   public void createEval(Player plyr, int month)
   {
      if (boostStat.equals("logicalIntelligence"))
      {
         list[month] = new LogicalIntelligenceEval((subject + " Test " + (month+1)),plyr);
      }	
      else if (boostStat.equals("spatialIntelligence"))
      {
         list[month] = new SpatialIntelligenceEval((subject+ " Test " + (month+1)), plyr);
      }
      else if (boostStat.equals("linguisticIntelligence"))
      {
         list[month] = new LinguisticIntelligenceEval((subject+ " Test " + (month+1)), plyr);
      }
      else if (boostStat.equals("expressionCharisma"))
      {
         list[month] = new ExpressionCharismaEval((subject+ " Test " + (month+1)), plyr);
      }
      else if (boostStat.equals("socialCharisma"))
      {
         list[month] = new SocialCharismaEval((subject+ " Test " + (month+1)), plyr);
      }
      else if (boostStat.equals("strength"))
      {
         list[month] = new StrengthEval((subject+ " Test " + (month+1)), plyr);
      }
      numEval++;
   }
}
