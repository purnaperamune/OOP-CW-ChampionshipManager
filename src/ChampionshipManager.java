public interface ChampionshipManager {
    public void createDriver(Formula1Driver formula1Driver,String name,String location,String team,int firstPositions,int secondPositions,
                             int thirdPositions,int totalPoints,int numberOfRaces);
    public void deleteDriver();

    public void displaySelectedDriver();
    public void displayFormula1DriverTable();
    public void addRaceResult();
}
