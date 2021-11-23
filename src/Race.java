public class Race {
    String raceName;
    String raceDate;
    String firstPlace;
    String secondPlace;
    String thirdPlace;

    public Race(String raceName, String raceDate,String firstPlace,String secondPlace,String thirdPlace) {
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.firstPlace=firstPlace;
        this.secondPlace=secondPlace;
        this.thirdPlace=thirdPlace;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public String getThirdPlace() {
        return thirdPlace;
    }

    public void setThirdPlace(String thirdPlace) {
        this.thirdPlace = thirdPlace;
    }
}
