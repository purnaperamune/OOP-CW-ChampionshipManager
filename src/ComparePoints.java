import java.util.Comparator;

//Compares the total number of points
public class ComparePoints implements Comparator<Formula1Driver> {
    @Override
    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
        if(driver1.getTotalPoints() > driver2.getTotalPoints()) {
            return -1;
        }
        else if(driver1.getTotalPoints() < driver2.getTotalPoints()){
              return 1;
            }
        else{
            if(driver1.getFirstPositions() > driver2.getFirstPositions()) {
                return -1;
            }
        }
        return 0;
    }

}
