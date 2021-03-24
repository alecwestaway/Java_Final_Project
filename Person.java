import java.util.Scanner;

public abstract class Person {
    // Class Variables
    String name;
    String ID;

    // Constructor
    public Person(){
        name = "";
        ID = "";
    }

    public void setName(Scanner sc){
        System.out.printf("\tEnter Full Name: ");
        sc.nextLine();
        name = sc.nextLine();
    }

    public  String getName(){
        return name;
    }

    public void setID(Scanner sc){
        System.out.printf("\tEnter ID: ");
        ID = sc.next();
        checkID(ID);
    }

    private void checkID(String ID){
        if(ID.length() != 6){
            throw new IDexception("Sorry Invalid id format-It has to be LetterLetterDigitDigitDigitDigit");
        }
        int i;
        for(i = 0; i < 2; i++){
            if(Character.isLetter(ID.charAt(i)) == false){
                throw new IDexception("Sorry Invalid id format-It has to be LetterLetterDigitDigitDigitDigit");
            }
        }
        for(;i < 6; i++){
            if(Character.isDigit(ID.charAt(i)) == false){
                throw new IDexception("Sorry Invalid id format-It has to be LetterLetterDigitDigitDigitDigit");
            }
        }
    }

    public String getID(){
        return ID;
    }

    // To be Defined differently for Students and Faculty
    public abstract void print();
    //public abstract int search();
}
