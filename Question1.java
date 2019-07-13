/*
 * Assignment 3
 * Written by: Anthony Chraim 40091014
 * For COMP 248 Section W - Winter 2019
 */

//This program was made by me, Anthony Chraim, on March 16th, 2019.
//The purpose of this program is to create a GPA calculator for an undergraduate Concordia Student

//Start of the program

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Question1 {
	

	public static void main(String[] args) {

		//Storing every possible letter grade key with the corresponding GPA equivalent value
		Map<String, Double> GPAMap = new HashMap<> ();	
			GPAMap.put("A+", 4.30);
			GPAMap.put("A", 4.00);
			GPAMap.put("A-", 3.70);
			GPAMap.put("B+", 3.30);
			GPAMap.put("B", 3.00);
			GPAMap.put("B-", 2.70);
			GPAMap.put("C+", 2.30);
			GPAMap.put("C", 2.00);
			GPAMap.put("C-", 1.70);
			GPAMap.put("D+", 1.30);
			GPAMap.put("D", 1.00);
			GPAMap.put("D-", 0.70);
			GPAMap.put("FNS", 0.00);
			
		Scanner keyboard = new Scanner(System.in);	//initializing Scanner Object
		double creditAccumulated, currentGPA = 0;	//credits accumulated and current GPA not including the current semester
		//Initializing the String arrays that will contain the user's course names and letter grades
		String courseNames[] = new String [100], letterGrades[] = new String [100];
		//initializing the array that will contain the number of credits of a given course
		double numCredit[] = new double [100];
		String sLetterGrade = "";	//initializing the letter grade string that we will use as our HashMap keys
		//declaring the user input ints that chose an option, chooses what course to delete, and how many to add to the calculator
		int option, deleteCourses, addedCourses;
		String option4Course = "", option4LetterGrade = "";	//initializing the added course name and letter grade for option 4
		double option4Credits;	//initializing the number of credits of the added class in option 4
		
		
		System.out.println("***********************************************************\n" +	//Welcome banner
							"*** Welcome to the bested GPA calculator ever...\t***\n" +
							"*** The Program will ask you first to enter the\t\t***\n" +
							"*** numer of credits you have accumulated so far.\t***\n" +
							"\n"+
							"*** If this is your very first semester, please\t\t***\n" + 
							"*** enter 0.\t\t\t\t\t\t***\n" +
							"***********************************************************");
		
		do {	//prompt user for number of credits accumulated until he enters a positive number
		System.out.print("Please enter how many credits you have accumulated so \n" + 
							"far not including your current semester: ");
		creditAccumulated = keyboard.nextDouble();
		if (creditAccumulated < 0)
			System.out.println("\nERROR: number of past credits has to be a positive number. \n");
		} while (creditAccumulated < 0);
		
		
		if(creditAccumulated != 0) {	//skip the next step if user has not accumulated credits thus far
			
			do {	//prompt user for his GPA until he enters a number between 0 and 4.3 inclusive
				System.out.print("Please enter your GPA: ");
				currentGPA = keyboard.nextDouble();
				if ((currentGPA < 0 || currentGPA > 4.3))
					System.out.println("\nERROR: a valid GPA is between 0 and 4.3 insclusive. \n");
			} while ((currentGPA < 0 || currentGPA > 4.3));
		}
		
		do {	//prompt user for number of courses he wants to add until he enters a positive number
		System.out.print("Please enter how many courses do you want to add to the GPA calculator: ");
		addedCourses = keyboard.nextInt();
		if (addedCourses <= 0)
			System.out.println("\nERROR: number of courses has to be a positive number. \n");
		} while (addedCourses <= 0);
		
		
		for (int i = 0; i < addedCourses; i++) {	//for every class he wants to add, ask the user for the name, credits and letter grade of the class
			System.out.print("Please enter the name of course number " + (i + 1) +" : ");
			courseNames[i] = keyboard.next();
			
			do {	//prompt user for the number of credits of the added class until he enters a positive number
				System.out.print("Please enter the number of credits for course number " + (i + 1) + " : ");
				numCredit[i] = keyboard.nextDouble();
				if (numCredit[i] < 0)
					System.out.println("\nERROR: course credit has to be a positive number\n");
			} while (numCredit[i] < 0);

			do {	//prompt user for the letter grade of the added class until he enters a letter graded contained in the HashMap keys
			System.out.print("Please enter the letter grade of course number " + (i + 1) + " : ");
			letterGrades[i] = keyboard.next();
			sLetterGrade = letterGrades[i];
			if(!GPAMap.containsKey(sLetterGrade))
					System.out.println("\nERROR: course grade has to be one of the following...\n\n"+ 
										"A+, A, A-, B+, B, B-, C+, C, C-, D+, D, D-, FNS");

			}while (!GPAMap.containsKey(sLetterGrade));
			System.out.println();	//skip line
			}

		
		
		
		do {	//ask user what he wants to do next until he decides to exit
		System.out.println("Please choose one of the following options \n" +
							"Press 1: To display the courses in the system\n" +
							"Press 2: To display the new GPA\n"+ 
							"Press 3: to delete a course\n" + 
							"Press 4: to add a course\n" +
							"Press 5: to exit program")  ;
		option = keyboard.nextInt();
		
		switch (option) {
		
		case 1:	//print out the current GPA and accumulated credits with a table containing the new information he just input
			System.out.println("Your current GPA is: " + currentGPA);
			System.out.println("Your accumulated credits so far: " + creditAccumulated);
			getDisplay(courseNames, numCredit, letterGrades, addedCourses);	//Call function getDisplay that outputs the table (line 180)
		break;
		
		case 2:	//print out new GPA
			//Call function that uses the GPA equation (line 195)
			System.out.println("Your new GPA is " + getGPA(addedCourses,currentGPA,creditAccumulated,numCredit,letterGrades,GPAMap));
			break;
			
		case 3:  //delete course selected by user
			if (addedCourses == 0)	//error message if user has already removed all the courses
				System.out.println("ERROR: you have already deleted all the courses.");
			else {
				getDisplay(courseNames, numCredit, letterGrades, addedCourses) ; //call getDisplay function
				System.out.print("Please enter the course number you want to delete: ");
				deleteCourses = keyboard.nextInt();
				//replace the following arrays with the ones that do not contain the deletes course
				courseNames = removeCourseName(courseNames, addedCourses, deleteCourses);	//line 217
				letterGrades = removeLetterGrades(letterGrades, deleteCourses, addedCourses);	//line 237
				numCredit = removeNumCredit(numCredit, deleteCourses, addedCourses);	//line 257
				addedCourses--;	//Decrement the amount of added courses
			}
			break;
			
		case 4:	//add a course to the GPA calculator
			System.out.print("Please enter the name of course number " + (addedCourses + 1) + ": ");
			option4Course = keyboard.next();
			//replace the following arrays with the ones that contain the new course
			courseNames = addCourseName(courseNames, addedCourses, option4Course);	//line 276
			System.out.print("Please enter the number of credits of course number " + (addedCourses + 1) + ": " );
			option4Credits = keyboard.nextInt();
			numCredit = addNumCredit(numCredit, addedCourses, option4Credits);	//line 288
			System.out.print("Please enter the letter grade of course number " + (addedCourses + 1) + ": ");
			option4LetterGrade = keyboard.next();
			letterGrades = addGradeLetter(letterGrades, addedCourses, option4LetterGrade);	//line 300
			addedCourses ++;	//increment the amount of added courses
			break;
			
		case 5:	//get out of the switch and terminate the program
			System.out.println("Thank you for using the GPA Calculator!");	//closing message
			break;
			
		default:	//prompt user with error message if he writes a number that isn't between 1 and 5 inclusive 
			System.out.println("\nMenu option has to be between 1 and 5 inclusive.");

		}

		}	while(option != 5);
	
	keyboard.close();	//close Scanner Object

	}
	//this function prints out a table containing all the information the user has input so far
	private static void getDisplay(String courseNames[],double numCredit[],String letterGrades[],int addedCourses) {
		
		final Object[][] tableDisplay = new String [(int) (addedCourses + 1)][];	//initializing table
			
		tableDisplay[0] = new String[] {"Course Number", "Course Name", "Course Credit", "Course Grade"};	//declaring first row of the table
			
		for(int j = 0; j < addedCourses; j++) {	//filling up table with the user's information using the toString method to be able to print them
			tableDisplay[j + 1] = new String[] {Integer.toString(j + 1), courseNames[j], Double.toString(numCredit[j]), letterGrades[j]};
		}
		for(final Object [] row : tableDisplay) {
			//print everything in the table centered to the left with 18 characters between them (including their length)
			System.out.format("%-18s%-18s%-18s%-18s\n", row);
		}	
	}

	//this function returns the new GPA including the user's current courses
	private static double getGPA(double addedCourses,double currentGPA,double creditAccumulated,double numCredit[],String letterGrades[],Map<String, Double> GPAMap) {

		double newGPA = 0, totalAddedCredits = 0, creditGPA = 0;	//initializing the variables we will only need in this method
		double addedCredits, x, y;	//initializing the variable we will use to calculate the GPA

		for(int j = 0; j < addedCourses; j++) {	//calculate the total credits by multiplying all the corresponding values to the letter grade keys
			Double grade = GPAMap.get(letterGrades[j]);	
			creditGPA += numCredit[j] * grade;
		}
		for (int k = 0; k < addedCourses; k++) {	//calculate the total credits of all the added courses
			addedCredits = numCredit[k];
			totalAddedCredits += addedCredits;
		}
		x = currentGPA * creditAccumulated + creditGPA;
		y = creditAccumulated + totalAddedCredits;
		newGPA = x / y;	//GPA equation

		return newGPA;
	}

	//this function returns an array that contains all the course names minus the one the user has deleted
	private static String[] removeCourseName(String[] courseName, int addedCourses, int deleteCourse) {

		int count = 0;	//initializing counter
		String[] courseName1 = Arrays.copyOfRange(courseName, 0, deleteCourse -1 );	//array from annex 0 to the position of the deleted course (not including it)
		String[] courseName2 = Arrays.copyOfRange(courseName, deleteCourse, addedCourses);	//array going from deleted course position to amount of added classes
		String[] courseNameFinal =  new String [courseName1.length + courseName2.length];	//initializing length of final array that will contain the 2 previous ones

		for(int i = 0; i < courseName1.length; i++) {	//fill the final array with the content of the one containing the first part without the deleted course
	         courseNameFinal[i] = courseName1[i];
	         count++;
	      }

	      for(int j = 0; j < courseName2.length; j++) {	//fill the final array with the one containing the second part without the deleted course
	         courseNameFinal[count++] = courseName2[j];
	      }

	      return courseNameFinal;		
	}

	//this function returns an array that contains all the letter grades minus the one the user has deleted
	private static String[] removeLetterGrades(String[] letterGrades, int deletedCourse, int addedCourses) {

		int count = 0;	//initializing counter
		String[] letterGrades1 = Arrays.copyOfRange(letterGrades, 0, deletedCourse -1 );	//array form annex 0 to position of deleted course (not including it)
		String[] letterGrades2 = Arrays.copyOfRange(letterGrades, deletedCourse, addedCourses);	//array from deleted course position to amount of added classes
		String[] letterGradesFinal =  new String [letterGrades1.length + letterGrades2.length];	//initializing length of final array that will contain 2 previous ones

		for(int i = 0; i < letterGrades1.length; i++) {	//fill the final array with the content of the one containing the first part without the deleted course
			letterGradesFinal[i] = letterGrades1[i];
	         count++;
	      }

	      for(int j = 0;j<letterGrades2.length;j++) {	//fill the final array with the one containing the second part without the deleted course
	    	  letterGradesFinal[count++] = letterGrades2[j];
	      }

	     return letterGradesFinal;	
	}

	//this function returns an array that contains all the credits minus the one the user has deleted
	private static double[] removeNumCredit(double[] numCredit, int deletedCourse, int addedCourses) {

		int count = 0;
		double[] numCredit1 = Arrays.copyOfRange(numCredit, 0, deletedCourse -1 );	//array form annex 0 to the position of the deleted course (not including it)
		double[] numCredit2 = Arrays.copyOfRange(numCredit, deletedCourse, addedCourses); //array from the deleted course position to the amount of added classes
		double[] numCreditFinal =  new double [numCredit1.length + numCredit2.length];	//initializing length of final array that will contain the 2 previous ones

		for(int i = 0; i < numCredit1.length; i++) {	//fill the final array with the content of the one containing the first part without the deleted course
			numCreditFinal[i] = numCredit1[i];	
	         count++;
	      }

	      for(int j = 0;j<numCredit2.length;j++) {	//fill the final array with the one containing the second part without the deleted course
	    	  numCreditFinal[count++] = numCredit2[j];
	      }
	      return numCreditFinal;
	}

	//this function returns an array that contains all the course names plus the one the user added on option 4
	private static String[] addCourseName(String[] courseNames, int addedCourses, String option4Course) {

		String[] courseNameFinal = new String [addedCourses + 1];	//initializing new array

		for(int i = 0; i < addedCourses; i++) {	//copying the array to the final form
			courseNameFinal[i] = courseNames[i];	
		}
		courseNameFinal[addedCourses] = option4Course;	//concatenate the added course to the last position of the array	      
		return courseNameFinal;
	}

	//this function returns an array that contains all the numbers of credits plus the one the user added on option 4
	private static double[] addNumCredit(double[] numCredit, int addedCourses, double option4Credits) {

		double[] numCreditFinal = new double [addedCourses + 1];	//initializing new array

		for(int i = 0; i < addedCourses; i++) {	//copying the array to the final form
			numCreditFinal[i] = numCredit[i];	
		}
	    numCreditFinal[addedCourses] = option4Credits;	//concatenate the added course to the last position of the array
	    return numCreditFinal;
	}

	//this function returns an array that contains all the letter grades plus the one the user added on option 4
	private static String[] addGradeLetter(String[] letterGrades, int addedCourses, String option4LetterGrade) {

		String[] letterGradesFinal = new String [addedCourses + 1];	//initializing new array
		
		for(int i = 0; i < addedCourses; i++) {	//copying the array to the final form
			letterGradesFinal[i] = letterGrades[i];	
		}
	    letterGradesFinal[addedCourses] = option4LetterGrade;	//concatenate the added course to the last position of the array
	    return letterGradesFinal;
	}
	//end of program
}