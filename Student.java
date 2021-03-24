import java.util.Scanner;

public class Student extends Person {
    // Variables unique to Students
    private float gpa;
    private int creditHours;
    private final double TUITION = 236.45;


    // Constructor
    void student(){
        name = "";
        ID = "";
        gpa = 0;
        creditHours = 0;
    }

    // Overloaded Constructor
    void student(String name, String ID) {
        this.name = name;
        this.ID = ID;
        gpa = 0;
        creditHours = 0;
    }

    // Gpa Setter
    public void setGpa(Scanner sc){
        while(true){
            try{
                System.out.printf("\tEnter Gpa: ");
                sc.nextLine();
                this.gpa = sc.nextFloat();
            }catch(Exception e){
                System.out.println("Invalid input. Try again: ");
                continue;
            }
            break;
        }


    }
    // Gpa getter
    public float getGpa(){
        return gpa;
    }

    // Credit Hours Setter
    public void setCreditHours(Scanner sc){
        while(true){
            try {
                System.out.printf("\tEnter Credit Hours: ");
                sc.nextLine();
                this.creditHours = sc.nextInt();
            }catch(Exception e){
                System.out.println("Invalid input. Try again: ");
                continue;
            }
            break;
        }
    }

    // Credit Hours Getter
    public int getCreditHours(){
        return creditHours;
    }

    // Calculates and outputs how much tuition will cost to console
    private void calculateTuition(int creditHours, float gpa){
        double cost = 0;
        double discount = 0;
        if (gpa >= 3.85){
            cost = 52 + (.75 * TUITION) * creditHours;
            discount = .25 * TUITION * creditHours;
        }
        else{
            cost = 52 + TUITION * creditHours;
        }
        System.out.printf("\t\tTotal payment: $%.2f\t\t($%.2f discount applied )\n", cost, discount);

    }

    // Print out tuition Fees
    public void print(){
        System.out.printf("\n\t\tHere is the tuition invoice for %s:\n\n", getName());
        System.out.println("\t\t----------------------------------------------------");
        System.out.printf("\t\t%s\t%s\n", getName(), getID());
        System.out.printf("\t\tCredit Hours: %d ($236.45/credit hour)\n", getCreditHours());
        System.out.println("\t\tFees $52");
        System.out.println("");
        calculateTuition(getCreditHours(), getGpa());
        System.out.println("\t\t----------------------------------------------------\n");

    }

}
