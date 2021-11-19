import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager{
    static ArrayList <Formula1Driver> driverList=new ArrayList<Formula1Driver>();
    File formula1File = new File("playerInformation.txt");


    public ArrayList nameToString(){
        ArrayList nameToString= new ArrayList<String>();
        for(int x=0;x< driverList.size();x++){
            nameToString.add(driverList.get(x).getName());
        }
        return nameToString;
    }

    public void displayExistingDrivers(){
        System.out.println("-----Displaying existing drivers-----");
        for(int x=0;x<Formula1ChampionshipManager.driverList.size();x++){
            System.out.println(Formula1ChampionshipManager.driverList.get(x).getName());
        }
        System.out.println("\n");
    }

    @Override
    public void createDriver(Formula1Driver formula1Driver, String name, String location, String team, int firstPositions, int secondPositions, int thirdPositions, int totalPoints, int numberOfRaces) {
        formula1Driver = new Formula1Driver(name,location,team,firstPositions,secondPositions,thirdPositions
                ,totalPoints,numberOfRaces);
        driverList.add(formula1Driver);
        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Driver: "+name+" added successfully!\n");
    }

    @Override
    public void changeConstructorTeam(int index,String newTeam){
        driverList.get(index).setTeam(newTeam);

        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully updated the constructor team!\n");
    }


    @Override
    public void deleteDriver() {
        Scanner input=new Scanner(System.in);

        ArrayList nameList=new ArrayList();
        nameList=nameToString();

        int exit=1;
        while (exit==1){
            displayExistingDrivers();
            System.out.println("");
            System.out.println("Enter the name of the driver to delete?");
            String name=input.next();
            if(nameList.contains(name)){
                int index= nameList.indexOf(name);
                driverList.remove(index);
                System.out.println("Driver: "+name+" successfully deleted!\n");
                exit=0;
                break;
            }
            else {
                System.out.println("Invalid driver name! Please try again!\n");
                exit=1;
            }
        }
        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displaySelectedDriver() {
        Scanner input=new Scanner(System.in);
        int exit=1;

        ArrayList nameList=new ArrayList();
        nameList=nameToString();

        while (exit==1){
            displayExistingDrivers();
            System.out.println("");
            System.out.println("Enter the name of the driver to display statistics?");
            String name=input.next();
            if(nameList.contains(name)){
                int index= nameList.indexOf(name);
                System.out.println("Name: "+driverList.get(index).getName()+"\n"+
                        "Location: "+driverList.get(index).getLocation()+"\n"+
                        "Team: "+driverList.get(index).getTeam()+"\n"+
                        "1st Positions: "+driverList.get(index).getFirstPositions()+"\n"+
                        "2nd Positions: "+driverList.get(index).getSecondPositions()+"\n"+
                        "3rd Positions: "+driverList.get(index).getThirdPositions()+"\n"+
                        "Current Points: "+driverList.get(index).getTotalPoints()+"\n"+
                        "Participated Races: "+driverList.get(index).getNumberOfRaces()+"\n");
                exit=0;
                break;
            }
            else {
                System.out.println("Driver not found! Please try again!\n");
                exit=1;
            }
        }
    }

    @Override
    public void displayFormula1DriverTable() {
        Collections.sort(driverList, new CompareStat());
        String[][] formula1Table = new String[driverList.size()+1][];
        formula1Table[0] = new String[]{"Name","Location","Team","1st Places","2nd Places","3rd Places",
                "Total Points","No. of Races"};
        for(int i=0;i<driverList.size();i++){
            formula1Table[i+1] = new String[]{driverList.get(i).getName(),driverList.get(i).getLocation(),
                    (driverList.get(i).getTeam()), String.valueOf(driverList.get(i).getFirstPositions()), String.valueOf(driverList.get(i).getSecondPositions()), String.valueOf(driverList.get(i).getThirdPositions()), String.valueOf(driverList.get(i).getTotalPoints()), String.valueOf(driverList.get(i).getNumberOfRaces())};
        }

        System.out.println("--------------Formula1 Points Table--------------");
        for(String[] info: formula1Table){
            System.out.format("%17s %17s %17s %17s %17s %17s %17s %17s",info);
            System.out.print("\n");
        }
        System.out.println("\n");
    }

    @Override
    public void addRaceResult(String[] racePositions) {
        Formula1Driver formula1Driver=new Formula1Driver();
        int []pointsAllocationList={25,18,15,12,10,8,6,4,2,1};


        ArrayList nameList=new ArrayList();
        nameList=nameToString();

        int firstPositions,secondPositions,thirdPositions;
        int totalPoints;
        int participatedRaces;
        int index;

        for(int y=0;y<racePositions.length;y++){
            switch (y){
                case 0:
                    if(nameList.contains(racePositions[y])) {
                        index = nameList.indexOf(racePositions[y]);

                        firstPositions = driverList.get(index).getFirstPositions();
                        totalPoints = driverList.get(index).getTotalPoints();
                        participatedRaces = driverList.get(index).getNumberOfRaces();

                        firstPositions++;
                        totalPoints = totalPoints + pointsAllocationList[y];
                        participatedRaces++;

                        driverList.get(nameList.indexOf(racePositions[y])).setFirstPositions(firstPositions);
                        driverList.get(nameList.indexOf(racePositions[y])).setTotalPoints(totalPoints);
                        driverList.get(nameList.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
                    }
                        break;
                case 1:
                    if(nameList.contains(racePositions[y])) {
                        index = nameList.indexOf(racePositions[y]);

                        secondPositions = driverList.get(index).getSecondPositions();
                        totalPoints = driverList.get(index).getTotalPoints();
                        participatedRaces = driverList.get(index).getNumberOfRaces();

                        secondPositions++;
                        totalPoints = totalPoints + pointsAllocationList[y];
                        participatedRaces++;

                        driverList.get(nameList.indexOf(racePositions[y])).setSecondPositions(secondPositions);
                        driverList.get(nameList.indexOf(racePositions[y])).setTotalPoints(totalPoints);
                        driverList.get(nameList.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
                    }
                        break;
                case 2:
                    if(nameList.contains(racePositions[y])) {
                        index = nameList.indexOf(racePositions[y]);

                        thirdPositions = driverList.get(index).getThirdPositions();
                        totalPoints = driverList.get(index).getTotalPoints();
                        participatedRaces = driverList.get(index).getNumberOfRaces();

                        thirdPositions++;
                        totalPoints = totalPoints + pointsAllocationList[y];
                        participatedRaces++;

                        driverList.get(nameList.indexOf(racePositions[y])).setThirdPositions(thirdPositions);
                        driverList.get(nameList.indexOf(racePositions[y])).setTotalPoints(totalPoints);
                        driverList.get(nameList.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
                    }
                        break;
                case 3:
                    if(nameList.contains(racePositions[y])) {
                        updateStatistics(nameList,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 4:
                    if(nameList.contains(racePositions[y])) {
                        updateStatistics(nameList,racePositions,y,pointsAllocationList);

                    }
                        break;
                case 5:
                    if(nameList.contains(racePositions[y])) {
                        updateStatistics(nameList,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 6:
                    if(nameList.contains(racePositions[y])) {
                        updateStatistics(nameList,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 7:
                    if(nameList.contains(racePositions[y])) {
                        updateStatistics(nameList,racePositions,y,pointsAllocationList);
                    }

                    break;
                case 8:
                    if(nameList.contains(racePositions[y])) {
                        updateStatistics(nameList,racePositions,y,pointsAllocationList);
                    }
                        break;
                case 9:
                    if(nameList.contains(racePositions[y])) {
                        updateStatistics(nameList,racePositions,y,pointsAllocationList);
                    }
                        break;
            }
        }
        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateStatistics(ArrayList nameList,String[] racePositions,int y,int []pointsAllocationList){
        int firstPositions,secondPositions,thirdPositions;
        int totalPoints;
        int participatedRaces;
        int index=nameList.indexOf(racePositions[y]);
        totalPoints = driverList.get(index).getTotalPoints();
        participatedRaces = driverList.get(index).getNumberOfRaces();

        totalPoints = totalPoints + pointsAllocationList[y];
        participatedRaces++;

        driverList.get(nameList.indexOf(racePositions[y])).setTotalPoints(totalPoints);
        driverList.get(nameList.indexOf(racePositions[y])).setNumberOfRaces(participatedRaces);
    }

    public void saveToFile() throws IOException {
        FileWriter formula1File = new FileWriter("playerInformation.txt");
        for(int x=0;x<driverList.size();x++){
            formula1File.write(driverList.get(x).getName()+" "
                    +driverList.get(x).getLocation()+" "
                    +driverList.get(x).getTeam()+" "
                    +driverList.get(x).getFirstPositions()+" "
                    +driverList.get(x).getSecondPositions()+" "
                    +driverList.get(x).getThirdPositions()+" "
                    +driverList.get(x).getTotalPoints()+" "
                    +driverList.get(x).getNumberOfRaces()+"\n");
            formula1File.write("");
        }
        formula1File.close();
        System.out.println("");
    }

    public void getSavedInfo() throws FileNotFoundException {
        FileInputStream formula1File=new FileInputStream("playerInformation.txt");
        Scanner reader=new Scanner(formula1File);
        while(reader.hasNext()){
            String fileContent = reader.nextLine();
            String[] playerInfoArray = fileContent.split(" ");
            updateSystem(playerInfoArray[0], playerInfoArray[1],playerInfoArray[2],playerInfoArray[3],playerInfoArray[4],playerInfoArray[5],
                    playerInfoArray[6],playerInfoArray[7]);
        }
    }

    public void updateSystem(String name, String location, String team, String fP, String sP, String tP, String totPoints, String totRaces) {
        Formula1Driver f1=new Formula1Driver();
        f1.setName(name);
        f1.setLocation(location);
        f1.setTeam(team);
        f1.setFirstPositions(Integer.parseInt(fP));
        f1.setSecondPositions(Integer.parseInt(sP));
        f1.setThirdPositions(Integer.parseInt(tP));
        f1.setTotalPoints(Integer.parseInt(totPoints));
        f1.setNumberOfRaces(Integer.parseInt(totRaces));

        driverList.add(f1);


    }

}

