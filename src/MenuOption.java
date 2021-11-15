import java.util.Scanner;

public class MenuOption {
    public static void menu(){
        Scanner input=new Scanner(System.in);
        System.out.println("Welcome to the Formula1 Championship Manager!");
        String exitProgram="d";
        while (exitProgram!="EXT"){
            System.out.println("Add a new driver-------------------------------1\n" +
                    "Delete an existing driver----------------------2\n"+
                    "Display a driver information-------------------3\n"+
                    "Display Formula1 driver table------------------4\n"+
                    "Add results of a new race----------------------5\n"+
                    "Exit the Formula1 Championship Manager---------EXT\n");

            System.out.print("Enter your choice : ");
            String userInput = input.next();
            switch (userInput) {
                case "1":
                    createDriver();
                    break;
                case "2":
                    //Deleting an existing driver
                    break;
                case "3":
                    //Displaying information of a selected driver
                    break;
                case "4":
                    //Displaying Formula1 driver table
                    break;
                case "5":
                    //Adding results of a new race
                    break;
                case "EXT":
                    exitProgram = "EXT";
                    System.out.println("Thank you for using the Formula1 Championship Manager.\nGood day!");
                    break;
                default:
                    System.out.println("Sorry, Invalid choice! Please try again!");
        }
    }
}

    public static void createDriver() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the name of the driver?");
        String name=input.next();
        System.out.println("Enter the location?");
        String location=input.next();
        System.out.println("Enter the name of the team?");
        String team=location=input.next();
        System.out.println("Number of 1st places?");
        int firstPlaces=input.nextInt();
        System.out.println("Number of 2nd places?");
        int secondPlaces=input.nextInt();
        System.out.println("Number of 3rd places?");
        int thirdPlaces=input.nextInt();
        System.out.println("Current total number of points?");
        int totalPoints=input.nextInt();
        System.out.println("Number of participated races?");
        int numberOfRaces=input.nextInt();

        Formula1Driver formula1Driver=new Formula1Driver();

        Formula1ChampionshipManager newDriver=new Formula1ChampionshipManager();
        newDriver.createDriver(formula1Driver,name,location,team,firstPlaces,secondPlaces,thirdPlaces,totalPoints,numberOfRaces);
    }

    public static void main(String[] args) {
        menu();
    }
}





