import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompareFirstPositionTest {

    @Test
    void compare() {
        Formula1Driver formula1Driver1=new Formula1Driver("Jofra","Colombo","Ferrari",33,62,23,5600,87);
        Formula1Driver formula1Driver2=new Formula1Driver("James","Galle","Toyota",23,32,21,3433,45);

        CompareFirstPosition compareFirstPosition=new CompareFirstPosition();
        int actualOutput = compareFirstPosition.compare(formula1Driver1,formula1Driver2);

        int expectedOutput = -1;
        assertEquals(expectedOutput,actualOutput);
    }
}