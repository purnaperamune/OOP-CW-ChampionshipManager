import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComparePointsTest {

    @Test
    void compare() {
        Formula1Driver formula1Driver1=new Formula1Driver("Jofra","Colombo","Ferrari",33,62,23,5600,87);
        Formula1Driver formula1Driver2=new Formula1Driver("James","Galle","Toyota",23,32,21,3433,45);

        ComparePoints comparePoints = new ComparePoints();
        int actualOutput = comparePoints.compare(formula1Driver1,formula1Driver2);
        int expectedOutput=-1;

        assertEquals(expectedOutput,actualOutput);
    }
}