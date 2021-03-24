/*
 * Alec Westaway
 * Project 2
 * COP 3330
 * 11.21.2020
 *
 * */

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Project3 {

    public static void main(String[] args){




        // Variables
        int userInput = 0;
        int numStudents = 0;
        int numFaculty = 0;
        Student[] students = new Student[100];
        Faculty[] faculty = new Faculty[100];
        Scanner sc = new Scanner(System.in);


        // Main Loop
        System.out.println("Welcome to my Personal Management Program");
        while(true) {
            // Display options for main selection
            System.out.println("1 - Add a new Faculty member");
            System.out.println("2 - Add a new Student");
            System.out.println("3- Print Tuition Invoice for a Student");
            System.out.println("4- Print information of a faculty");
            System.out.println("5- Exit Program");

            // Get User Input
            System.out.printf("\tEnter your Selection: ");
            userInput = checkSelection(sc);
            // -1 is returned by check selection method if entry is not a number
            if (userInput == -1){
                continue;
            }

            if(userInput == 1){
                boolean checkFaculty;
                checkFaculty = enterFaculty(faculty, numFaculty, sc);
                if(checkFaculty == true) {
                    numFaculty++;
                }
            }
            else if(userInput == 2){
                boolean checkStudent;
                checkStudent = enterStudents(students, numStudents, sc);
                if(checkStudent == true) {
                    numStudents++;
                }
            }
            else if(userInput == 3){
                int search = 0;
                search = search(students, numStudents, sc);
                if(search == -1) {continue;}
                students[search].print();

            }
            else if(userInput == 4){
                int selection = 0;
                selection = search(faculty, numFaculty, sc);
                if(selection == -1) {continue;}
                faculty[selection].print();
                //faculty[selection].print(faculty[selection]);
            }
            else if(userInput == 5){
                System.out.println("Would you like to create the report? (Y/N): ");
                String userSelection = "none";
                while(true) {
                    try {
                        //sc.nextLine();
                        userSelection = sc.next();
                        userSelection.toLowerCase();
                        if (userSelection.compareTo("y") != 0 && userSelection.compareTo("n") != 0) {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid entry. Try Again: ");
                        continue;
                    }
                    break;
                }
                if(userSelection.compareTo("y") == 0){
                    outputData(students,numStudents, faculty, numFaculty);
                }
                System.out.println("\n\tGoodbye!\n");
                break;
            }
            else{
                System.out.println("Invalid Selection. Please try again");
            }
        }
        sc.close();
    }

    // Checks to see if user selection is an integer. If it is not, it sends user back to beginning of main loop by returning -1,
    // Which in turn, is checked in main function
    public static int checkSelection(Scanner sc){
        int userInput = 0;
        String selection = sc.next();
        char[] selectionArray = selection.toCharArray();
        int i;
        // Sorting through elements of input to see if they are integers
        for (i = 0; i < selectionArray.length; i++){
            if (Character.isDigit(selectionArray[i]) == false){
                System.out.printf("\nInvalid entry - please try again\n\n");
                userInput = -1;
                break;
            }
            else{
                userInput = Integer.parseInt(selection);
            }
        }
        return userInput;
    }

    public static boolean enterFaculty(Faculty[] faculty, int numFaculty, Scanner sc){
        Faculty tempFaculty = new Faculty();
        System.out.println("\nEnter the faculty's info:");
        tempFaculty.setName(sc);
        try{
            tempFaculty.setID(sc);
        }catch(IDexception e){
            System.out.println("\n"+ e.getMessage() +"\n");
            return false;
        }
        tempFaculty.setRank(sc);
        tempFaculty.setDepartment(sc);
        faculty[numFaculty] = tempFaculty;
        System.out.println("\nThanks!\n");
        return true;
    }

    public static void enterStudents2(Student[] students, int numStudents, Scanner sc){
        System.out.println("\nEnter the Student's Info: ");
        students[numStudents] = new Student();
        students[numStudents].setName(sc);
        students[numStudents].setID(sc);
        students[numStudents].setGpa(sc);
        students[numStudents].setCreditHours(sc);
        System.out.println("\nThanks!\n");
    }

    public static boolean enterStudents(Student[] students, int numStudents, Scanner sc){
        System.out.println("\nEnter the Student's Info: ");
        Student tempStudent = new Student();


        tempStudent.setName(sc);
        try{
            tempStudent.setID(sc);
        }catch(IDexception e){
            System.out.println("\n" + e.getMessage() +"\n");
            return false;
        }
        tempStudent.setGpa(sc);
        tempStudent.setCreditHours(sc);
        students[numStudents] = tempStudent;
        System.out.println("\nThanks!\n");
        return true;
    }

    // Searches for a student based on its ID
    public static int search(Student[] students,int numStudents, Scanner sc){
        String ID;
        String temp;
        int index = 0;
        int foundID = 0;
        System.out.printf("\n\tEnter the Students's ID: ");
        ID = sc.next();
        int i;
        for(i = 0; i < numStudents; i++){
            temp = students[i].getID();
            if(temp.compareTo(ID) == 0){
                index = i;
                foundID = 1;
                break;
            }
        }

        if(foundID == 0){
            System.out.println("\n\tSorry " + ID + " does not exist\n");
            index = -1;
        }

        return index;
    }

    // Overloaded search method for finding faculty based on ID
    public static int search(Faculty[] faculty,int numFaculty, Scanner sc){
        String ID;
        String temp;
        int index = 0;
        int foundID = 0;
        System.out.printf("\n\tEnter the faculty's ID: ");
        ID = sc.next();
        int i = 0;
        for(i = 0; i < numFaculty; i++){
            temp = faculty[i].getID();
            if(temp.compareTo(ID) == 0){
                index = i;
                foundID = 1;
                break;
            }
        }

        if(foundID == 0){
            System.out.println("\n\tSorry " + ID + " does not exist\n");
            index = -1;
        }

        return index;
    }


    public static void outputData(Student[] student,int numStudents, Faculty[] faculty, int numFaculty){

        try{
            FileWriter myWriter = new FileWriter("report.txt");
            //myWriter.write("Attempting to write to file");


            myWriter.write("Report on Student and Faculty Records\n\n\n");
            myWriter.write("Faculty Members (sorted by Department)" + "\n");
            myWriter.write("-------------------------------------" + "\n");
            int i;
            for(i = 0; i < numFaculty; i++){
                myWriter.write("\t"+ (i+1) +". " +faculty[i].getName() + "\n");
                myWriter.write("\tId: "+faculty[i].getID() + "\n");
                myWriter.write("\t"+faculty[i].getRank() + ", " + faculty[i].getDepartment() + "\n");
            }

            myWriter.write("\n\nStudents" + "\n");
            myWriter.write("------\n");
            for(i = 0; i < numStudents; i++){
                myWriter.write("\t"+(i +  1)+". " + student[i].getName() + "\n");
                myWriter.write("\tID: "+student[i].getID() + "\n");
                myWriter.write("\tGPA: "+student[i].getGpa() + "\n");
                myWriter.write("\tCredit Hours: "+student[i].getCreditHours() + "\n");
            }


            myWriter.close();
        }catch(IOException e){
            System.out.println("Could not Create Output file");
            return;
        }
    }
}


