import java.util.Comparator;

//Compare the date of races according to the ascending order
public class CompareDate implements Comparator<Race> {
    @Override
    public int compare(Race r1,Race r2) {
        return r1.getRaceDate().compareTo(r2.getRaceDate());
    }

}
