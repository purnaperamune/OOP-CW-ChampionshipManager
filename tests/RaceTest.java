import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

    @Test
    void getRaceName() {
        Race race=new Race("Race1","2021-12-01","Teron","Janice","Selena");
        String actualOutput=race.getRaceName();

        assertEquals("Race1",actualOutput);
    }

    @Test
    void setRaceName() {
        Race race=new Race();
        race.setRaceName("Race101");

        String actualOutput= race.getRaceName();
        assertEquals("Race101",actualOutput);
    }

    @Test
    void getRaceDate() {
        Race race=new Race("Race1","2021-12-01","Teron","Janice","Selena");
        String actualOutput=race.getRaceDate();

        assertEquals("2021-12-01",actualOutput);
    }

    @Test
    void setRaceDate() {
        Race race=new Race();
        race.setRaceDate("2019-06-23");

        String actualOutput= race.getRaceDate();
        assertEquals("2019-06-23",actualOutput);
    }

    @Test
    void getFirstPlace() {
        Race race=new Race("Race1","2021-12-01","Teron","Janice","Selena");
        String actualOutput=race.getFirstPlace();

        assertEquals("Teron",actualOutput);
    }

    @Test
    void setFirstPlace() {
        Race race=new Race();
        race.setFirstPlace("Halsey");

        String actualOutput= race.getFirstPlace();
        assertEquals("Halsey",actualOutput);
    }

    @Test
    void getSecondPlace() {
        Race race=new Race("Race1","2021-12-01","Teron","Janice","Selena");
        String actualOutput=race.getSecondPlace();

        assertEquals("Janice",actualOutput);
    }

    @Test
    void setSecondPlace() {
        Race race=new Race();
        race.setSecondPlace("Anne");

        String actualOutput= race.getSecondPlace();
        assertEquals("Anne",actualOutput);
    }

    @Test
    void getThirdPlace() {
        Race race=new Race("Race1","2021-12-01","Teron","Janice","Selena");
        String actualOutput=race.getThirdPlace();

        assertEquals("Selena",actualOutput);
    }

    @Test
    void setThirdPlace() {
        Race race=new Race();
        race.setThirdPlace("Clara");

        String actualOutput= race.getThirdPlace();
        assertEquals("Clara",actualOutput);
    }
}