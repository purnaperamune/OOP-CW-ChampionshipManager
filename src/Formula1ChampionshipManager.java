import java.util.ArrayList;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager{
    ArrayList <Formula1Driver> driverList=new ArrayList<Formula1Driver>();

    @Override
    public void createDriver(Formula1Driver formula1Driver, String name, String location, String team, int firstPositions, int secondPositions, int thirdPositions, int totalPoints, int numberOfRaces) {
        formula1Driver = new Formula1Driver(name,location,team,firstPositions,secondPositions,thirdPositions
                ,totalPoints,numberOfRaces);
        driverList.add(formula1Driver);
    }

    @Override
    public void deleteDriver() {
        Scanner input=new Scanner(System.in);
        System.out.println("Please enter the name of the driver you want to delete?");
        String name= input.next();
        for(int x=0;x<driverList.size();x++){
            if(driverList.get(x).equals(name)){
                driverList.remove(x);
            }
        }
    }

    @Override
    public void displaySelectedDriver() {

    }

    @Override
    public void displayFormula1DriverTable() {

    }

    @Override
    public void addRaceResult() {

    }
}
