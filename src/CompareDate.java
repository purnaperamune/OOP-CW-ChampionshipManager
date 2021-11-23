import java.util.Comparator;

public class CompareDate implements Comparator<Race> {
    @Override
    public int compare(Race r1,Race r2) {
        return r1.getRaceDate().compareTo(r2.getRaceDate());
    }

}
