import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager{
    static ArrayList <Formula1Driver> driverList=new ArrayList<Formula1Driver>();
    File formula1File = new File("vacccinationData.txt");

    public void saveToFile() throws IOException {
        FileWriter formula1File = new FileWriter("vacccinationData.txt");
        for(int x=0;x<driverList.size();x++){
            formula1File.write("Name: "+driverList.get(x).getName()+"\n"
            +"Location: "+driverList.get(x).getLocation()+"\n"+
                    "Team: "+driverList.get(x).getTeam()+"\n"+
                    "First Positions: "+driverList.get(x).getFirstPositions()+"\n"+
                    "Second Positions: "+driverList.get(x).getSecondPositions()+"\n"+
                    "Third Positions: "+driverList.get(x).getThirdPositions()+"\n"+
                    "Total Points: "+driverList.get(x).getTotalPoints()+"\n"+
                    "Participated Races: "+driverList.get(x).getNumberOfRaces()+"\n");
            formula1File.write("------------------\n");
            formula1File.write("");
        }
        formula1File.close();
        System.out.println("");
    }
    public void displayExistingDrivers(){
        System.out.println("-----Displaying existing drivers-----");
        for(int x=0;x<Formula1ChampionshipManager.driverList.size();x++){
            System.out.println(Formula1ChampionshipManager.driverList.get(x).getName());
        }
    }

    @Override
    public void createDriver(Formula1Driver formula1Driver, String name, String location, String team, int firstPositions, int secondPositions, int thirdPositions, int totalPoints, int numberOfRaces) {
        formula1Driver = new Formula1Driver(name,location,team,firstPositions,secondPositions,thirdPositions
                ,totalPoints,numberOfRaces);
        driverList.add(formula1Driver);
    }

    @Override
    public void deleteDriver(String name) {
        for (int x=0; x< driverList.size();x++) {
            if (driverList.get(x).getName().equals(name)) {
                driverList.remove(x);
            }
        }
    }

    @Override
    public void displaySelectedDriver(String name) {
        for (int x=0; x< driverList.size();x++) {
            if (driverList.get(x).getName().equals(name)) {
                System.out.println("Name: "+driverList.get(x).getName());
                System.out.println("Location: "+driverList.get(x).getLocation());
                System.out.println("Team: "+driverList.get(x).getTeam());
                System.out.println("1st Positions: "+driverList.get(x).getFirstPositions());
                System.out.println("2nd Positions: "+driverList.get(x).getSecondPositions());
                System.out.println("3rd Positions: "+driverList.get(x).getThirdPositions());
                System.out.println("Current Points: "+driverList.get(x).getTotalPoints());
                System.out.println("Participated Races: "+driverList.get(x).getNumberOfRaces());
            }
            else {
                System.out.println("Driver not found! Please try again!");
            }
        }
    }

    @Override
    public void displayFormula1DriverTable() {

    }

    @Override
    public void addRaceResult(String[] racePositions) {
        Formula1Driver formula1Driver=new Formula1Driver();
        int []pointsAllocationList={25,18,15,12,10,8,6,4,2,1};


        ArrayList <String > namesToString=new ArrayList<>();
        for(int x=0;x< driverList.size();x++){
            namesToString.add(driverList.get(x).getName());
        }

        int firstPositions,secondPositions,thirdPositions;
        int totalPoints;
        int participatedRaces;
        int index;

        for(int y=0;y<racePositions.length;y++){
            switch (y){
                case 0:
                    if(namesToString.contains(racePositions[y])) {
                        index = namesToString.indexOf(racePositions[y]);

                        firstPositions = driverList.get(index).getFirstPositions();
                        totalPoints = driverList.get(index).getTotalPoints();
                        participatedRaces = driverList.get(index).getNumberOfRaces();

                        firstPositions++;
                        totalPoints = totalPoints + pointsAllocationList[y];
                        participatedRaces++;

                        driverList.get(namesToString.indexOf(racePositions[y])).setFirstPositions(firstPositions);
                        driverList.get(namesToString.indexOf(racePositions[y])).setTotalPoints(totalPoints);
                        driverList.get(namesToString.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
                    }
                        break;
                case 1:
                    if(namesToString.contains(racePositions[y])) {
                        index = namesToString.indexOf(racePositions[y]);

                        secondPositions = driverList.get(index).getSecondPositions();
                        totalPoints = driverList.get(index).getTotalPoints();
                        participatedRaces = driverList.get(index).getNumberOfRaces();

                        secondPositions++;
                        totalPoints = totalPoints + pointsAllocationList[y];
                        participatedRaces++;

                        driverList.get(namesToString.indexOf(racePositions[y])).setSecondPositions(secondPositions);
                        driverList.get(namesToString.indexOf(racePositions[y])).setTotalPoints(totalPoints);
                        driverList.get(namesToString.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
                    }
                        break;
                case 2:
                    if(namesToString.contains(racePositions[y])) {
                        index = namesToString.indexOf(racePositions[y]);

                        thirdPositions = driverList.get(index).getThirdPositions();
                        totalPoints = driverList.get(index).getTotalPoints();
                        participatedRaces = driverList.get(index).getNumberOfRaces();

                        thirdPositions++;
                        totalPoints = totalPoints + pointsAllocationList[y];
                        participatedRaces++;

                        driverList.get(namesToString.indexOf(racePositions[y])).setThirdPositions(thirdPositions);
                        driverList.get(namesToString.indexOf(racePositions[y])).setTotalPoints(totalPoints);
                        driverList.get(namesToString.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
                    }
                        break;
                case 3:
                    if(namesToString.contains(racePositions[y])) {
                        updateStatistics(namesToString,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 4:
                    if(namesToString.contains(racePositions[y])) {
                        updateStatistics(namesToString,racePositions,y,pointsAllocationList);

                    }
                        break;
                case 5:
                    if(namesToString.contains(racePositions[y])) {
                        updateStatistics(namesToString,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 6:
                    if(namesToString.contains(racePositions[y])) {
                        updateStatistics(namesToString,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 7:
                    if(namesToString.contains(racePositions[y])) {
                        updateStatistics(namesToString,racePositions,y,pointsAllocationList);
                    }

                    break;
                case 8:
                    if(namesToString.contains(racePositions[y])) {
                        updateStatistics(namesToString,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 9:
                    if(namesToString.contains(racePositions[y])) {
                        updateStatistics(namesToString,racePositions,y,pointsAllocationList);
                    }
                        break;
            }
        }
    }
    public void updateStatistics(ArrayList namesToString,String[] racePositions,int y,int []pointsAllocationList){
        int firstPositions,secondPositions,thirdPositions;
        int totalPoints;
        int participatedRaces;
        int index=namesToString.indexOf(racePositions[y]);
        totalPoints = driverList.get(index).getTotalPoints();
        participatedRaces = driverList.get(index).getNumberOfRaces();

        totalPoints = totalPoints + pointsAllocationList[y];
        participatedRaces++;

        driverList.get(namesToString.indexOf(racePositions[y])).setTotalPoints(totalPoints);
        driverList.get(namesToString.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
    }
}

