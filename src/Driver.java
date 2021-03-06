/* This is an abstract class and
child class of this class is Formula1Driver
 */
public abstract class Driver {
    String name;
    String location;
    String team;

    public abstract String getName();
    public abstract void setName(String name);
    public abstract String getLocation() ;
    public abstract void setLocation(String location) ;
    public abstract String getTeam() ;
    public abstract void setTeam(String team) ;
    public abstract int getFirstPositions();
    public abstract void setFirstPositions(int firstPositions);
    public abstract int getSecondPositions();
    public abstract void setSecondPositions(int secondPositions);
    public abstract int getThirdPositions();
    public abstract void setThirdPositions(int thirdPositions);
    public abstract int getTotalPoints();
    public abstract void setTotalPoints(int totalPoints);
    public abstract int getNumberOfRaces();
    public abstract void setNumberOfRaces(int numberOfRaces);
}
