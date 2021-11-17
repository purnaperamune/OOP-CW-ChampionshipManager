public interface ChampionshipManager {
    public void createDriver(Formula1Driver formula1Driver,String name,String location,String team,int firstPositions,int secondPositions,
                             int thirdPositions,int totalPoints,int numberOfRaces);


    public void deleteDriver(String name);


    void displaySelectedDriver(String name);

    public void displayFormula1DriverTable();

    void addRaceResult(String[] racePositions);
}
