import javax.swing.*;
import java.io.*;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{
    static ArrayList <Formula1Driver> driverList=new ArrayList<Formula1Driver>(); //Array to store Formula1 Driver information
    static ArrayList <Race> raceInfo=new ArrayList<Race>(); //Array to store race information such as race name and date

    File formula1File = new File("playerInformation.txt"); //File to store data of drivers such as driver name
    File formula1File2 = new File("raceInformation.txt"); //File to store data of races such as race name, date

    //This method is to get all names of current drivers as a list
    public ArrayList nameToString(){
        ArrayList nameToString= new ArrayList<String>();
        for(int x=0;x< driverList.size();x++){
            nameToString.add(driverList.get(x).getName());
        }
        return nameToString;
    }

    //This method is to display all currently existing drivers
    public void displayExistingDrivers(){
        System.out.println("-----Displaying existing drivers-----");
        for(int x=0;x<Formula1ChampionshipManager.driverList.size();x++){
            System.out.println(Formula1ChampionshipManager.driverList.get(x).getName());
        }
        System.out.println("\n");
    }

    //Adding a new driver to the system
    @Override
    public void createDriver(Formula1Driver formula1Driver, String name, String location, String team, int firstPositions, int secondPositions, int thirdPositions, int totalPoints, int numberOfRaces) {
        formula1Driver = new Formula1Driver(name,location,team,firstPositions,secondPositions,thirdPositions
                ,totalPoints,numberOfRaces);
        driverList.add(formula1Driver); //Adding the driver to the array
        try {
            saveToFileDriverInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Driver: "+name+" added successfully!\n");
    }

    //Changing the current constructor team of a driver.(Eg: From Ferrari to Nissan)
    @Override
    public void changeConstructorTeam(int index,String newTeam){
        driverList.get(index).setTeam(newTeam);

        try {
            saveToFileDriverInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully updated the constructor team!\n");
    }

    //Deleting a driver in the system
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
            if(nameList.contains(name)){ //Checking if the driver name is valid or if the name is an existing name
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
            saveToFileDriverInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displaying information related to a specific driver according to the user input
    @Override
    public void displaySelectedDriver() {
        Scanner input=new Scanner(System.in);
        int exit=1;

        ArrayList nameList=new ArrayList();
        nameList=nameToString();

        while (exit==1){ //To exit from the program if the driver is not found
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

    //Displaying Formula1 Points Table
    @Override
    public void displayFormula1DriverTable() {
        Collections.sort(driverList, new ComparePoints()); //Sorting according to total number of points
        String[][] formula1Table = new String[driverList.size()+1][];

        formula1Table[0] = new String[]{"Name","Location","Team","1st Places","2nd Places","3rd Places",
                "Total Points","No. of Races"}; //Formula1 Points table column names

        for(int i=0;i<driverList.size();i++){
            formula1Table[i+1] = new String[]{driverList.get(i).getName(),driverList.get(i).getLocation(),
                    (driverList.get(i).getTeam()), String.valueOf(driverList.get(i).getFirstPositions()), String.valueOf(driverList.get(i).getSecondPositions()), String.valueOf(driverList.get(i).getThirdPositions()), String.valueOf(driverList.get(i).getTotalPoints()), String.valueOf(driverList.get(i).getNumberOfRaces())};
        }

        System.out.println("-------------------------------------------------------------Formula1 Points Table-------------------------------------------------------------\n");

        for(String[] info: formula1Table){ //Printing statistics of drivers
            System.out.format("%17s %17s %17s %17s %17s %17s %17s %17s",info);
            System.out.print("\n");
        }
        System.out.println("\n");
    }

    /*This method is to add results of a new race. The system will ask to enter all the details of a driver such as name,
    constructor team when the system recognizes a new driver name. */
    @Override
    public void addRaceResult(String[] racePositions, String rDate, String rName,String first,String second,String third) {
        int []pointsAllocationList={25,18,15,12,10,8,6,4,2,1}; //Points allocation according to the position from 1 to 10

        String raceName=rName;
        String raceDate=rDate;

        Race r1 = new Race(raceName,raceDate,first,second,third);
        raceInfo.add(r1);

        ArrayList nameList;
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

                        //Getting current statistics of the relevant driver
                        firstPositions = driverList.get(index).getFirstPositions();
                        totalPoints = driverList.get(index).getTotalPoints();
                        participatedRaces = driverList.get(index).getNumberOfRaces();

                        //Updating statistics after getting the current statistics of the relevant driver
                        firstPositions++;
                        totalPoints = totalPoints + pointsAllocationList[y];
                        participatedRaces++;

                        //Setting statistics after updating the current statistics of the relevant driver
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
                        //Calling updateStatistics method to update stats of the driver
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
            //Updating files with newly added race results.
            saveToFileDriverInfo();
            saveToFileRaceInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*This method gets called after the execution of the previous method in order to update statistics such as total points
     after adding new race results to the system. */
    public void updateStatistics(ArrayList nameList,String[] racePositions,int y,int []pointsAllocationList){
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

    //Saving all the data of drivers in the system to a file for future use
    public void saveToFileDriverInfo() throws IOException {
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

    //Saving all the data of races in the system to a file for future use
    public void saveToFileRaceInfo() throws IOException {
        FileWriter formula1File = new FileWriter("raceInformation.txt");
        for(int x=0;x<raceInfo.size();x++){
            formula1File.write(raceInfo.get(x).getRaceName()+" "
                    +raceInfo.get(x).getRaceDate()+" "
                    +raceInfo.get(x).getFirstPlace()+" "
                    +raceInfo.get(x).getSecondPlace()+" "
                    +raceInfo.get(x).getThirdPlace()+"\n");
            formula1File.write("");
        }
        formula1File.close();
        System.out.println("");
    }

    //Getting all saved data of drivers into the system from the fille
    public void getSavedInfoDriver() throws FileNotFoundException {
        FileInputStream formula1File=new FileInputStream("playerInformation.txt");
        Scanner reader=new Scanner(formula1File);
        while(reader.hasNext()){
            String fileContent = reader.nextLine();
            String[] driverInfoArray = fileContent.split(" "); //Reading the file word by word, splitting accordingly.
            updateSystemDriverInfo(driverInfoArray[0], driverInfoArray[1],driverInfoArray[2],driverInfoArray[3],driverInfoArray[4],driverInfoArray[5],
                    driverInfoArray[6],driverInfoArray[7]);
        }
    }

    //Getting all saved data of races into the system from the file
    public void getSavedInfoRace() throws FileNotFoundException {
        FileInputStream formula1File2=new FileInputStream("raceInformation.txt");
        Scanner reader=new Scanner(formula1File2);

        while(reader.hasNext()){
            String fileContent = reader.nextLine();
            String[] raceInfoArray = fileContent.split(" ");
            updateSystemRaceInfo(raceInfoArray[0], raceInfoArray[1],raceInfoArray[2],raceInfoArray[3],raceInfoArray[4]);
        }
    }

    //This method gets called inside the 'getSavedInfoDriver()' method in order to update the system with saved data in the file
    public void updateSystemDriverInfo(String name, String location, String team, String fP, String sP, String tP, String totPoints, String totRaces) {
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

    //This method gets called inside the 'getSavedInfoRace()' method in order to update the system with saved data in the file
    public void updateSystemRaceInfo(String raceName,String raceDate,String fP,String sP,String tP){
        Race r1=new Race();
        r1.setRaceName(raceName);
        r1.setRaceDate(raceDate);
        r1.setFirstPlace(fP);
        r1.setSecondPlace(sP);
        r1.setThirdPlace(tP);

        raceInfo.add(r1);
    }

    // ---------------------------Methods for Graphical user interface section---------------------------

    /*This method gets called inside 'driverStatTableDescending()', 'driverStatTableAscending()',
     and 'driverStatTablePosition()'. Returns statArray which stores statistics of drivers */
    public static String[][] statTable(){
        String[] statType;
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

    //Displaying race information in the GUI
    public static String[][] raceInfoTable(){
        String[] type;
        String[][] raceInfoArray=new String[raceInfo.size()][5];
        for(int x=0;x< raceInfo.size();x++){
            for(int y=0;y<5;y++){
                type= new String[]{raceInfo.get(x).getRaceName(),raceInfo.get(x).getRaceDate(),raceInfo.get(x).getFirstPlace(),raceInfo.get(x).getSecondPlace(),raceInfo.get(x).getThirdPlace()};
                raceInfoArray[x][y]=type[y];
            }
        }
        return raceInfoArray;
    }

    //Displaying drivers according the descending order of their total points
    public static String[][] driverStatTableDescending(){
        Collections.sort(driverList, new ComparePoints());
        String[][] statArrayAscending=statTable();
        return statArrayAscending;
    }

    //Displaying drivers according the ascending order of their total points
    public static String[][] driverStatTableAscending(){
        Collections.sort(driverList, new ComparePoints().reversed());
        String[][] statArrayAscending=statTable();
        return statArrayAscending;
    }

    //Displaying drivers according the descending order of the total first positions
    public static String[][] driverStatTablePosition(){
        Collections.sort(driverList, new CompareFirstPosition());
        String[][] statArrayPosition=statTable();
        return statArrayPosition;

    }

    //Displaying race information according to the ascending order of the date the race happened
    public static String[][] sortByDate(){
        Collections.sort(raceInfo,new CompareDate());
        String[][] raceArray=raceInfoTable();
        return raceArray;
    }

    //Generating a random race with random results.
    public void randomRace(){
        ArrayList <Integer> randomDrivers=new ArrayList<Integer>();
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

                System.out.println("\n----Race Results Sheet----\n");
                for(int x=0;x< 10;x++){
                    System.out.print("Position "+(x+1)+": "+driverList.get(randomDrivers.get(x)).getName()+"\n");
                }
                System.out.println("------------\nEnter your choice?");
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

                System.out.println("\n----Race Results Sheet----\n");
                for(int x=0;x< 10;x++){
                    System.out.print("Position: "+(x+1)+": "+driverList.get(randomDrivers.get(x)).getName()+"\n");
                }
                System.out.println("------------\nEnter your choice?");

                addRaceResult(randomRaceWinners,dateOfRace,nameOfRace,driverList.get(randomDrivers.get(0)).getName(),driverList.get(randomDrivers.get(1)).getName(),driverList.get(randomDrivers.get(2)).getName());
            }
        }
    }

    //This method allows to search races a given driver participated.
    public static String[][] searchDriverInfo() {
        String name;
        name = PointsTableGUI.searchBar.getText(); //Getting the string value in the text field

        ArrayList raceNameToString = new ArrayList<String>();
        ArrayList fp = new ArrayList<String>(); // To store names of first positions
        ArrayList sp = new ArrayList<String>(); // To store names of second positions
        ArrayList tp = new ArrayList<String>(); // To store names of third positions
        for (int x = 0; x < raceInfo.size(); x++) {
            raceNameToString.add(raceInfo.get(x).getRaceName());
            fp.add(raceInfo.get(x).getFirstPlace());
            sp.add(raceInfo.get(x).getSecondPlace());
            tp.add(raceInfo.get(x).getThirdPlace());
        }

        String[][] raceDetails = new String[3][6]; //To store race details
        System.out.println("\n\n----Displaying races a given driver participated with race details----\n");
        if (fp.contains(name)) { //Checking if the driver has participated in a race
            int index= fp.indexOf(name);

            System.out.println("\n"+"Driver Name:"+name+"\n"+"Race Name:"+raceInfo.get(index).getRaceName()+"\n"+"Race Date:"+ raceInfo.get(index).getRaceDate()+"\n"+"First Position:"+ raceInfo.get(index).getFirstPlace()+"\n"+
                            "Second Position:"+raceInfo.get(index).getSecondPlace()+"\n"+"Third Position:"+raceInfo.get(index).getThirdPlace());
            System.out.println("--------------");

            raceDetails[0][0]=name;
            raceDetails[0][1]=raceInfo.get(index).getRaceName();
            raceDetails[0][2]=raceInfo.get(index).getRaceDate();
            raceDetails[0][3]=raceInfo.get(index).getFirstPlace();
            raceDetails[0][4]= raceInfo.get(index).getSecondPlace();
            raceDetails[0][5]=raceInfo.get(index).getThirdPlace();


            JOptionPane.showMessageDialog(null,"Driver Name:"+name+"\n"+"Race Name:"+raceInfo.get(index).getRaceName()+"\n"+"Race Date:"+ raceInfo.get(index).getRaceDate()+"\n"+"First Position:"+ raceInfo.get(index).getFirstPlace()+"\n"+
                    "Second Position:"+raceInfo.get(index).getSecondPlace()+"\n"+"Third Position:"+raceInfo.get(index).getThirdPlace());

        }
        if (sp.contains(name)){
            int index= sp.indexOf(name);

            System.out.println("Driver Name:"+name+"\n"+"Race Name:"+raceInfo.get(index).getRaceName()+"\n"+"Race Date:"+ raceInfo.get(index).getRaceDate()+"\n"+"First Position:"+ raceInfo.get(index).getFirstPlace()+"\n"+
                    "Second Position:"+raceInfo.get(index).getSecondPlace()+"\n"+"Third Position:"+raceInfo.get(index).getThirdPlace());

            System.out.println("--------------");

            raceDetails[1][0]=name;
            raceDetails[1][1]=raceInfo.get(index).getRaceName();
            raceDetails[1][2]=raceInfo.get(index).getRaceDate();
            raceDetails[1][3]=raceInfo.get(index).getFirstPlace();
            raceDetails[1][4]= raceInfo.get(index).getSecondPlace();
            raceDetails[1][5]=raceInfo.get(index).getThirdPlace();

            JOptionPane.showMessageDialog(null,"Driver Name:"+name+"\n"+"Race Name:"+raceInfo.get(index).getRaceName()+"\n"+"Race Date:"+ raceInfo.get(index).getRaceDate()+"\n"+"First Position:"+ raceInfo.get(index).getFirstPlace()+"\n"+
                    "Second Position:"+raceInfo.get(index).getSecondPlace()+"\n"+"Third Position:"+raceInfo.get(index).getThirdPlace());

        }
        if (tp.contains(name)){
            int index= tp.indexOf(name);

            System.out.println("Driver Name:"+name+"\n"+"Race Name:"+raceInfo.get(index).getRaceName()+"\n"+"Race Date:"+ raceInfo.get(index).getRaceDate()+"\n"+"First Position:"+ raceInfo.get(index).getFirstPlace()+"\n"+
                    "Second Position:"+raceInfo.get(index).getSecondPlace()+"\n"+"Third Position:"+raceInfo.get(index).getThirdPlace());

            raceDetails[2][0]=name;
            raceDetails[2][1]=raceInfo.get(index).getRaceName();
            raceDetails[2][2]=raceInfo.get(index).getRaceDate();
            raceDetails[2][3]=raceInfo.get(index).getFirstPlace();
            raceDetails[2][4]= raceInfo.get(index).getSecondPlace();
            raceDetails[2][5]=raceInfo.get(index).getThirdPlace();

            JOptionPane.showMessageDialog(null,"Driver Name:"+name+"\n"+"Race Name:"+raceInfo.get(index).getRaceName()+"\n"+"Race Date:"+ raceInfo.get(index).getRaceDate()+"\n"+"First Position:"+ raceInfo.get(index).getFirstPlace()+"\n"+
                    "Second Position:"+raceInfo.get(index).getSecondPlace()+"\n"+"Third Position:"+raceInfo.get(index).getThirdPlace());

        }
        System.out.println("\nEnter your choice: ");
        return raceDetails;
    }
}

