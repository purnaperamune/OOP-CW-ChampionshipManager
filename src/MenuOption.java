import java.io.IOException;
import java.util.ArrayList;
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
                    "Saving data in a file--------------------------6\n"+
                    "Exit the Formula1 Championship Manager---------EXT\n");

            System.out.print("Enter your choice : ");
            String userInput = input.next();
            switch (userInput) {
                case "1":
                    createDriver();
                    break;
                case "2":
                    deleteDriver();
                    break;
                case "3":
                    displaySelectedDriver();
                    break;
                case "4":
                    displayFormula1DriverTable();
                    break;
                case "5":
                    addRaceResult();
                    break;
                case "6":
                    saveToFile();
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
        System.out.println("-----Creating a new driver-----");
        System.out.println("Enter the name of the driver?");
        String name=input.next();
        System.out.println("Enter the location?");
        String location=input.next();
        System.out.println("Enter the name of the team?");
        String team=input.next();
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
    public static void deleteDriver(){
        Scanner input=new Scanner(System.in);
        Formula1ChampionshipManager formula1Driver=new Formula1ChampionshipManager();
        formula1Driver.displayExistingDrivers();
        System.out.println("");
        System.out.println("Enter the name of the driver to delete?");
        String name=input.next();
        formula1Driver.deleteDriver(name);
    }
    public static void displaySelectedDriver() {
        Scanner input=new Scanner(System.in);
        Formula1ChampionshipManager formula1Driver=new Formula1ChampionshipManager();
        formula1Driver.displayExistingDrivers();
        System.out.println("");
        System.out.println("Enter the name of the driver to display statistics?");
        String name=input.next();
        formula1Driver.displaySelectedDriver(name);
    }
    public static void displayFormula1DriverTable(){
        //To be implemented
    }
    public static void addRaceResult() {
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        System.out.println("-----Adding Race Results-----");
        String []placesList=new String[10];
        Scanner input=new Scanner(System.in);
        System.out.println("Enter first 10 places one by one to update the points table");
        for(int x=0;x<10;x++){
            String name=input.next();
            System.out.println(x+"----"+name);
            placesList[x]=name;
        }
        formula1ChampionshipManager.addRaceResult(placesList);
    }
    public static void saveToFile()  {
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        try {
            formula1ChampionshipManager.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        menu();

    }
}





