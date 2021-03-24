import java.util.Scanner;

public class Faculty extends Person {
    // Variables
    String department;
    String rank;

    // Constructor
    public Faculty(){
        name = "None";
        ID = "None";
        department = "None";
        rank = "None";
    }

    // Overloaded Constructor
    public Faculty(String name, String ID){
        this.name = name;
        this.ID = ID;
        department = "";
        rank = "";
    }

    public void setRank(Scanner sc){
        System.out.printf("\tRank: ");
        rank = sc.next();
        rank = rank.toLowerCase();
        rank = checkRank(rank, sc);
    }

    public String getRank(){
        return rank;
    }

    public void setDepartment(Scanner sc){
        System.out.printf("\tDepartment: ");
        department = sc.next();
        department = department.toLowerCase();
        department = checkDepartment(department, sc);
    }

    public String getDepartment(){
        return department;
    }

    // Compares entered department to list of departments that are valid
    private String checkDepartment(String department, Scanner sc){
        String validList[] = new String[]{"mathematics", "engineering", "physics"};
        int i;
        while(true){
            for (i = 0; i < 3; i++){
                if (department.compareTo(validList[i]) == 0) {
                    return department;
                }
            }
            System.out.printf("\t\t\tSorry entered department (%s) is invalid\n\t\t\tDepartment: ", department);
            department = sc.next();
            department = department.toLowerCase();
        }
    }

    // Checks if Rank Entered is a Valid Selection
    private String checkRank(String rank, Scanner sc){
        String validList[] = new String[]{"professor", "adjunct"};
        int i;
        while (true){
            for (i = 0; i < 2; i++){
                if (rank.compareTo(validList[i]) == 0){
                    return rank;
                }
            }
            System.out.printf("\t\t\tSorry entered rank (%s) is invalid\n\t\t\tRank: ", rank);
            rank = sc.next();
            rank = rank.toLowerCase();
        }
    }

    // Prints faculty information
    public void print(){
        System.out.println("\n\t\t----------------------------------------------------");
        System.out.println("\t\t" + getName());
        System.out.println("\t\t" + getDepartment() + " Department, " + getRank());
        System.out.println("\t\t----------------------------------------------------\n");
    }
}
