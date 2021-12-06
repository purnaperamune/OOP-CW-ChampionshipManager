import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompareDateTest {

    @Test
    void compare() {
        Race race1=new Race("Race1","2019-10-23","Kevin","Joe","Archer");
        Race race2=new Race("Race2","2021-12-03","James","Taylor","Selena");

        CompareDate compareDate=new CompareDate();
        compareDate.compare(race1,race2);

        int actualOutput=compareDate.compare(race1,race2);
        int expectedOutput=-1;

        assertEquals(expectedOutput,actualOutput);
    }
}