import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Scanner;

public class MenuOption {
    public static void menu() throws FileNotFoundException {
        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println("--------Welcome to the Formula1 Championship Manager!--------");
        System.out.println("-------------------------------------------------------------");
        System.out.print("\n");
        String exitProgram="d";
        while (exitProgram!="EXT"){
            System.out.println("    ------------------Menu Options------------------\n" +
                    "    Add a new driver-------------------------------1\n" +
                    "    Change driver constructor team-----------------2\n"+
                    "    Delete an existing driver----------------------3\n"+
                    "    Display a driver information-------------------4\n"+
                    "    Display Formula1 driver table------------------5\n"+
                    "    Add results of a new race----------------------6\n"+
                    "    Update the system with past results------------7\n"+
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
                    Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
                    formula1ChampionshipManager.getSavedInfo();
                    System.out.println("System updated successfully!\n");
                    break;
                case "EXT":
                    exitProgram = "EXT";
                    System.out.println("Thank you for using the Formula1 Championship Manager.\nHave a nice day!");
                    break;
                default:
                    System.out.println("Sorry, Invalid choice! Please try again!\n");
        }
    }
}
    public static void createDriver() {
        Formula1Driver formula1Driver=new Formula1Driver();

        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

        ArrayList nameList=new ArrayList();
        nameList=formula1ChampionshipManager.nameToString();

        Scanner input=new Scanner(System.in);
        System.out.println("-----Creating a new driver-----");
        int exit=1;
        while(exit==1){
            System.out.println("Enter the name of the driver?");
            String name=input.next();
            if(nameList.contains(name)){
                System.out.println("Driver already registered! Enter a different name.\n");
                exit=1;
            }
            else {
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

                formula1ChampionshipManager.createDriver(formula1Driver,name,location,team,firstPlaces,secondPlaces,thirdPlaces,totalPoints,numberOfRaces);
                exit=0;
            }
        }
    }
    public static void deleteDriver(){
        Scanner input=new Scanner(System.in);
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        if(formula1ChampionshipManager.driverList.size()==0){
            System.out.println("No existing driver to delete!\n");
        }
        else {
            formula1ChampionshipManager.deleteDriver();
        }
    }
    public static void displaySelectedDriver() {
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        if(formula1ChampionshipManager.driverList.size()==0){
            System.out.println("No existing driver to display!\n");
        }
        else {
            formula1ChampionshipManager.displaySelectedDriver();
        }
    }
    public static void changeDriverConstructorTeam(){
        Scanner input=new Scanner(System.in);
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

        if(formula1ChampionshipManager.driverList.size()==0){
            System.out.println("No existing driver to display!\n");
        }
        else {
            ArrayList nameList=new ArrayList();
            nameList=formula1ChampionshipManager.nameToString();

            formula1ChampionshipManager.displayExistingDrivers();

            System.out.println("Enter the name of the drive that you want to change the constructor team?");
            String name= input.next();
            int index= nameList.indexOf(name);
            System.out.println("Enter the name of the new team?");
            String newTeam=input.next();
            formula1ChampionshipManager.changeConstructorTeam(index,newTeam);
        }
    }
    public static void displayFormula1DriverTable(){
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        formula1ChampionshipManager.displayFormula1DriverTable();
    }
    public static void addRaceResult() {
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

        ArrayList nameList=new ArrayList();
        nameList=formula1ChampionshipManager.nameToString();

        System.out.println("-----Adding Race Results-----");
        String []placesList=new String[10];
        Scanner input=new Scanner(System.in);
        System.out.println("Enter first 10 places one by one to update the points table");
        for(int x=0;x<10;x++){
            String name=input.next();
            if(nameList.contains(name)){
                System.out.println((x+1)+"----"+name);
                placesList[x]=name;
            }
            else{
                System.out.println((x+1)+"----"+name);
                System.out.println("A new driver identified! Please add following details too.");
                createDriver();

                System.out.println("\n");
                System.out.println((x+1)+"----"+name);

                System.out.println("!!!!---Back to update the points table---!!!!");
                System.out.println("Enter the place starting from "+(x+2));
            }
        }
        formula1ChampionshipManager.addRaceResult(placesList);
        System.out.println("Race results successfully added to the points table!\n");
    }

    public static void saveToFile()  {
        Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
        try {
            formula1ChampionshipManager.saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        menu();
    }
}





