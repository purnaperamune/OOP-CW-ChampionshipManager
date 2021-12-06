import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuOption {
     public static void menu() throws FileNotFoundException {
        Scanner input=new Scanner(System.in);
         Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

         //Getting saved information in files into the system
         formula1ChampionshipManager.getSavedInfoDriver();
         formula1ChampionshipManager.getSavedInfoRace();

        boolean toExit=true;
        while (toExit){
            System.out.println("    ------------------Menu Options------------------\n" +
                    "    Add a new driver-------------------------------1\n" +
                    "    Change driver constructor team-----------------2\n"+
                    "    Delete an existing driver----------------------3\n"+
                    "    Display a driver information-------------------4\n"+
                    "    Display Formula1 driver table------------------5\n"+
                    "    Add results of a new race----------------------6\n"+
                    "    Open GUI---------------------------------------7\n"+
                    "    Exit the Formula1 Championship Manager---------EXT\n");

            System.out.print("Enter your choice : ");
            String userInput = input.next();
            switch (userInput) {
                case "1":
                    createDriver();
                    break;
                case "2":
                    changeDriverConstructorTeam();
                    break;
                case "3":
                    deleteDriver();
                    break;
                case "4":
                    displaySelectedDriver();
                    break;
                case "5":
                    displayFormula1DriverTable();
                    break;
                case "6":
                    addRaceResult();
                    break;
                case "7":
                    PointsTableGUI gui = new PointsTableGUI();
                    gui.setVisible(true);
                    break;
                case "EXT":
                    toExit=false;
                    System.out.println("Thank you for using the Formula1 Championship Manager.\nHave a nice day!");
                    break;
                default:
                    System.out.println("Sorry, Invalid choice! Please try again!\n");
        }
    }
}

    /* This method is to get details about a new driver.
    This method calls 'createDriver(..)' method in the Formula1ChampionshipManager class in order to add the driver
    details to the system  */
    public static void createDriver() {
        Formula1Driver formula1Driver=new Formula1Driver();

        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

        ArrayList nameList;
        nameList=formula1ChampionshipManager.nameToString();

        Scanner input=new Scanner(System.in);
        System.out.println("-----Creating a new driver-----");
        int exit=1;
        while(exit==1){
            System.out.println("Enter the name of the driver?");
            String name=input.next();
            if(nameList.contains(name)){ //Checking if the driver is already in the system
                System.out.println("Driver already registered! Enter a different name.\n");
                exit=1;
            }
            else {
                System.out.println("Enter the location?");
                String location=input.next(); //Location of the driver
                System.out.println("Enter the name of the team?");
                String team=input.next(); //Constructor team of the driver such as Ferrari
                System.out.println("Number of 1st places?");
                int firstPlaces=input.nextInt(); //Number of first places
                System.out.println("Number of 2nd places?");
                int secondPlaces=input.nextInt(); //Number of second places
                System.out.println("Number of 3rd places?");
                int thirdPlaces=input.nextInt(); //Number of third places
                System.out.println("Current total number of points?");
                int totalPoints=input.nextInt();  //Current total points
                System.out.println("Number of participated races?");
                int numberOfRaces=input.nextInt(); //Number of participated races

                formula1ChampionshipManager.createDriver(formula1Driver,name,location,team,firstPlaces,secondPlaces,thirdPlaces,totalPoints,numberOfRaces);
                exit=0;
            }
        }
    }

    //This method is to get the name of the driver in order to delete the driver from the system
    public static void deleteDriver(){
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        if(formula1ChampionshipManager.driverList.size()==0){ //Checking if the driver is in the system to delete
            System.out.println("No existing driver to delete!\n");
        }
        else {
            formula1ChampionshipManager.deleteDriver();
        }
    }

    //This method is get a specific name of a driver from the console in order to display driver information
    public static void displaySelectedDriver() {
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        if(formula1ChampionshipManager.driverList.size()==0){ //Checking if there is a driver to display
            System.out.println("No existing driver to display!\n");
        }
        else {
            formula1ChampionshipManager.displaySelectedDriver();
        }
    }

    //This method is get a specific name of a driver from the console in order to change driver constructor team
    public static void changeDriverConstructorTeam(){
        Scanner input=new Scanner(System.in);
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

        if(formula1ChampionshipManager.driverList.size()==0){ //Checking if there is a driver to change their constructor team
            System.out.println("No existing driver to display!\n");
        }
        else {
            ArrayList nameList;
            nameList=formula1ChampionshipManager.nameToString();

            formula1ChampionshipManager.displayExistingDrivers();

            System.out.println("Enter the name of the drive that you want to change the constructor team?");
            String name= input.next();
            int index= nameList.indexOf(name);
            System.out.println("Enter the name of the new team?");
            String newTeam=input.next(); // New constructor team
            formula1ChampionshipManager.changeConstructorTeam(index,newTeam);
        }
    }

    //This method calls the 'displayFormula1DriverTable()' method in Formula1ChampionshipManager class to display points table
    public static void displayFormula1DriverTable(){
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        formula1ChampionshipManager.displayFormula1DriverTable();
    }

    //This method is to get the driver positions of a new race in order to update the statistics such as total points
    public static void addRaceResult() {
        Scanner input=new Scanner(System.in);
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

        ArrayList nameList;
        nameList=formula1ChampionshipManager.nameToString();

        String dateOfRace;
        String nameOfRace;

        System.out.println("Enter name of the race?");
        nameOfRace=input.next();

        System.out.println("Enter date of the race?");
        dateOfRace=input.next();

        System.out.println("-----Adding Race Results-----");
        String []placesList=new String[10];
        System.out.println("Enter first 10 places one by one to update the points table");
        for(int x=0;x<10;x++){
            String name=input.next();
            if(nameList.contains(name)){ // Checking if the driver is a new driver or not
                System.out.println((x+1)+"----"+name);
                placesList[x]=name;
            }
            else{
                System.out.println((x+1)+"----"+name);
                System.out.println("A new driver identified! Please add following details too.");
                createDriver(); //Getting details of a driver when the system get identified the driver as a new driver

                System.out.println("\n");
                System.out.println((x+1)+"----"+name);

                System.out.println("!!!!---Back to update the points table---!!!!");
                System.out.println("Enter the place starting from "+(x+2));
            }
        }
        formula1ChampionshipManager.addRaceResult(placesList,dateOfRace,nameOfRace,placesList[0],placesList[1],placesList[2]);
        System.out.println("Race results successfully added to the points table!\n");
    }

    //Execution of the program starts from here
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("-------------------------------------------------------------");
        System.out.println("--------Welcome to the Formula1 Championship Manager!--------");
        System.out.println("-------------------------------------------------------------");
        System.out.print("\n");

         menu();
    }
}





