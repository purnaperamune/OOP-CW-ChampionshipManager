/* This is an interface to implement methods related to the ChampionshipManager.
   Formula1ChampionshipManager class contains the implementation of method bodies.
 */

public interface ChampionshipManager {
    void createDriver(Formula1Driver formula1Driver,String name,String location,String team,int firstPositions,int secondPositions,
                             int thirdPositions,int totalPoints,int numberOfRaces);
    void changeConstructorTeam(int index,String newTeam);
    void deleteDriver();
    void displaySelectedDriver();
    void displayFormula1DriverTable();

    void addRaceResult(String[] racePositions, String rDate, String rName, String first, String second, String third);
}
