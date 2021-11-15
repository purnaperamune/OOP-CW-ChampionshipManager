public abstract class Driver {
    String name;
    String location;
    String team;

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
