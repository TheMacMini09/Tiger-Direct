
package mymethods;

//Author: M. Klaassen
//Oct. 30, 2014
//ICS3UC GCVI

//external methods pertaining to input from keyboard (console)
//using Scanner class

import java.util.Scanner;

public class Console {
   
 	// member variables - accessible to this class only (private)
 	private static int intNum;
 	private static double dblNum;
 	private static Scanner keyboard = new Scanner(System.in);
 	
 	
        
       //****************************************************************************** 
	// METHODs to accept INTEGER from the user
        //***********************************************************
	//
	// get a valid integer from the console
        public static int getInt(String prompt)
	{
		System.out.print(prompt);
		
		while(keyboard.hasNextInt() == false)
		{
			keyboard.nextLine();   // clear input
			System.out.println("not a valid integer - please try again ");
			System.out.println(prompt);
		}
		
		// loop terminates when valid integer is entered
		intNum=keyboard.nextInt();  // we know the input is valid so get it
		keyboard.nextLine(); // flush the input stream
		return(intNum);
	}
	
	
	
	// get a valid integer between low and high range
	public static int getInt(String prompt,int lowRange,int highRange)
	{
					
		intNum = getInt(prompt);
		
		while (!(intNum >= lowRange && intNum <= highRange))
		{
			System.out.println("Entry is out of range");
			System.out.println("Range is from " + lowRange + " to " + highRange);
						
			intNum = getInt(prompt);
			
		}
		
		return intNum;
	}
	
	
	//get a valid integer >= low 
	public static int getInt(String prompt,int lowRange)
	{
		
		intNum = getInt(prompt);
		
		while (!(intNum >= lowRange))
		{
			System.out.println("Entry is out of range");
			System.out.println("Must be greater than or equal to " + lowRange);
						
			intNum = getInt(prompt);
			
		}
		
		return intNum;
	}
	
        //****************************************************************************** 
	// METHODs to accept DOUBLE from the user
        //***********************************************************
        
        
	// get a valid real number from the console
      	public static double getDbl(String prompt)
	{
		System.out.println(prompt);
		
		while(keyboard.hasNextDouble() == false)
		{
			keyboard.nextLine();   // clear input
			System.out.println("not a real number - please try again ");
			System.out.println(prompt);
		}
		
		// loop terminates when valid double is entered
		dblNum=keyboard.nextDouble();  // we know the input is valid so get it
		keyboard.nextLine(); // flush the input stream
		return(dblNum);
	}
        
        // get a valid real number between low and high range
	public static double getDbl(String prompt,double lowRange,double highRange)
	{
		dblNum = getDbl(prompt);
		
		while (!(dblNum >= lowRange && dblNum <= highRange))
		{
			System.out.println("Entry is out of range");
			System.out.println("Range is from " + lowRange + " to " + highRange);
						
			dblNum = getDbl(prompt);
			
		}
		
		return dblNum;
	}
	
	
	//get a valid real number >= low 
	public static double getDbl(String prompt,double lowRange)
	{
		dblNum = getDbl(prompt);
		
		while (!(dblNum >= lowRange))
		{
			System.out.println("Entry is out of range");
			System.out.println("Must be greater than " + lowRange);
						
			dblNum = getDbl(prompt);
			
		}
		
		return dblNum;
	}
	
 }

