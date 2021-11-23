import javax.swing.*;
import java.io.*;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{
    static ArrayList <Formula1Driver> driverList=new ArrayList<Formula1Driver>();
    static ArrayList <Race> raceInfo=new ArrayList<Race>();

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
        Collections.sort(driverList, new ComparePoints());
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
    public void addRaceResult(String[] racePositions, String rDate, String rName,String first,String second,String third) {
        Formula1Driver formula1Driver=new Formula1Driver();

        int []pointsAllocationList={25,18,15,12,10,8,6,4,2,1};

        String raceName=rName;
        String raceDate=rDate;

        Race r1 = new Race(raceName,raceDate,first,second,third);
        raceInfo.add(r1);

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

    // ------Method for Graphical user interface section-------
    public static String[][] statTable(){
        String[] statType=new String[8];
        String[][] statArray=new String[driverList.size()][8];
        for(int x=0;x< driverList.size();x++){
            for(int y=0;y<8;y++){
                statType= new String[]{driverList.get(x).getName(),driverList.get(x).getLocation(),driverList.get(x).getTeam()
                        , String.valueOf(driverList.get(x).getFirstPositions()), String.valueOf(driverList.get(x).getSecondPositions())
                        , String.valueOf(driverList.get(x).getThirdPositions()), String.valueOf(driverList.get(x).getTotalPoints()),String.valueOf(driverList.get(x).getNumberOfRaces())};
                statArray[x][y]=statType[y];
            }
        }
        return statArray;
    }
    public static String[][] raceInfoTable(){
        String[] type=new String[2];
        String[][] raceInfoArray=new String[raceInfo.size()][5];
        for(int x=0;x< raceInfo.size();x++){
            for(int y=0;y<5;y++){
                type= new String[]{raceInfo.get(x).getRaceName(),raceInfo.get(x).getRaceDate(),raceInfo.get(x).getFirstPlace(),raceInfo.get(x).getSecondPlace(),raceInfo.get(x).getThirdPlace()};
                raceInfoArray[x][y]=type[y];
            }
        }
        return raceInfoArray;
    }

    public static String[][] driverStatTableDescending(){
        Collections.sort(driverList, new ComparePoints());
        String[][] statArrayAscending=statTable();
        return statArrayAscending;
    }
    public static String[][] driverStatTableAscending(){
        Collections.sort(driverList, new ComparePoints().reversed());
        String[][] statArrayAscending=statTable();
        return statArrayAscending;
    }
    public static String[][] driverStatTablePosition(){
        Collections.sort(driverList, new CompareFirstPosition());
        String[][] statArrayPosition=statTable();
        return statArrayPosition;

    }
    public static String[][] sortByDate(){
        Collections.sort(raceInfo,new CompareDate());
        String[][] raceArray=raceInfoTable();
        return raceArray;
    }

    public void randomRace(){
        ArrayList <Integer> randomDrivers=new ArrayList<Integer>();
        Scanner input=new Scanner(System.in);
        String dateOfRace;
        String nameOfRace;

        String[] randomRaceWinners=new String[10];

        if(driverList.size()<2){
            System.out.println("There should be minimum of 2 existing drivers!\n");
        }
        else {
            nameOfRace = JOptionPane.showInputDialog("Name of the race?");
            dateOfRace = JOptionPane.showInputDialog("Date of the race?");

            if(driverList.size()>10){
                for(int x=0;x<10;x++){
                    randomDrivers.add(x);
                }
                Collections.shuffle(randomDrivers);

                for(int x=0;x<10;x++){
                    randomRaceWinners[x]=driverList.get(randomDrivers.get(x)).getName();
                }

//                System.out.println(("\n\n---Random Race results---"));

//                    System.out.println("Position "+(x+1)+": "+driverList.get(randomDrivers.get(x)).getName());
                JOptionPane.showMessageDialog(null,"Position 1:"+driverList.get(randomDrivers.get(0)).getName()+"\n"+
                        "Position 2:"+driverList.get(randomDrivers.get(1)).getName()+"\n"+
                        "Position 3:"+driverList.get(randomDrivers.get(2)).getName()+"\n"+
                        "Position 4:"+driverList.get(randomDrivers.get(3)).getName()+"\n"+
                        "Position 5:"+driverList.get(randomDrivers.get(4)).getName()+"\n"+
                        "Position 6:"+driverList.get(randomDrivers.get(5)).getName()+"\n"+
                        "Position 7:"+driverList.get(randomDrivers.get(6)).getName()+"\n"+
                        "Position 8:"+driverList.get(randomDrivers.get(7)).getName()+"\n"+
                        "Position 9:"+driverList.get(randomDrivers.get(8)).getName()+"\n"+
                        "Position 10:"+driverList.get(randomDrivers.get(9)).getName());


                System.out.println("");
                addRaceResult(randomRaceWinners,dateOfRace,nameOfRace,driverList.get(randomDrivers.get(0)).getName(),driverList.get(randomDrivers.get(1)).getName(),driverList.get(randomDrivers.get(2)).getName());

            }
            else {
                for(int x=0;x< driverList.size();x++){
                    randomDrivers.add(x);
                }
                Collections.shuffle(randomDrivers);

                for(int x=0;x< driverList.size();x++) {
                    randomRaceWinners[x]=driverList.get(randomDrivers.get(x)).getName();
                    System.out.println(randomDrivers.get(x));
                }
//                System.out.println(("---Random Race results---"));
                for(int x=0;x<10;x++){
                    JOptionPane.showMessageDialog(null,"Position "+(x+1)+": "+driverList.get(randomDrivers.get(x)).getName());

                }
                System.out.println("");
                addRaceResult(randomRaceWinners,dateOfRace,nameOfRace,driverList.get(randomDrivers.get(0)).getName(),driverList.get(randomDrivers.get(1)).getName(),driverList.get(randomDrivers.get(2)).getName());

            }
        }
    }
}

