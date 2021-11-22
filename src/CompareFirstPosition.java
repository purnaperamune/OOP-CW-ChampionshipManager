import java.util.Comparator;

public class CompareFirstPosition implements Comparator<Formula1Driver> {
    @Override
    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
        if(driver1.getFirstPositions() > driver2.getFirstPositions()) {
            return -1;
        }
        else if(driver1.getFirstPositions() < driver2.getFirstPositions()){
            return 1;
        }

        return 0;
    }

}
