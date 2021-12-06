import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Formula1DriverTest {

    @Test
    void getName() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        String actualOutput = formula1Driver.getName();
        assertEquals("Purna",actualOutput);
    }

    @Test
    void setName() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setName("Devon");
        String actualOutput = formula1Driver.getName();
        assertEquals("Devon",actualOutput);
    }

    @Test
    void getLocation() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        String actualOutput = formula1Driver.getLocation();
        assertEquals("Kottawa",actualOutput);
    }

    @Test
    void setLocation() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setLocation("Nugegoda");
        String actualOutput = formula1Driver.getLocation();
        assertEquals("Nugegoda",actualOutput);
    }

    @Test
    void getTeam() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        String actualOutput = formula1Driver.getTeam();
        assertEquals("Toyota",actualOutput);
    }

    @Test
    void setTeam() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setTeam("Ferrari");
        String actualOutput = formula1Driver.getTeam();
        assertEquals("Ferrari",actualOutput);
    }

    @Test
    void getFirstPositions() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        int actualOutput = formula1Driver.getFirstPositions();
        assertEquals(23,actualOutput);
    }

    @Test
    void setFirstPositions() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setFirstPositions(54);
        int actualOutput = formula1Driver.getFirstPositions();
        assertEquals(54,actualOutput);
    }

    @Test
    void getSecondPositions() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        int actualOutput = formula1Driver.getSecondPositions();
        assertEquals(32,actualOutput);
    }

    @Test
    void setSecondPositions() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setSecondPositions(34);
        int actualOutput = formula1Driver.getSecondPositions();
        assertEquals(34,actualOutput);
    }

    @Test
    void getThirdPositions() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        int actualOutput = formula1Driver.getThirdPositions();
        assertEquals(21,actualOutput);
    }

    @Test
    void setThirdPositions() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setThirdPositions(43);
        int actualOutput = formula1Driver.getThirdPositions();
        assertEquals(43,actualOutput);
    }

    @Test
    void getTotalPoints() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        int actualOutput = formula1Driver.getTotalPoints();
        assertEquals(5600,actualOutput);
    }

    @Test
    void setTotalPoints() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setTotalPoints(4532);
        int actualOutput = formula1Driver.getTotalPoints();
        assertEquals(4532,actualOutput);
    }

    @Test
    void getNumberOfRaces() {
        Formula1Driver formula1Driver=new Formula1Driver("Purna","Kottawa","Toyota",23,32,21,5600,87);
        int actualOutput = formula1Driver.getNumberOfRaces();
        assertEquals(87,actualOutput);
    }

    @Test
    void setNumberOfRaces() {
        Formula1Driver formula1Driver= new Formula1Driver();
        formula1Driver.setNumberOfRaces(67);
        int actualOutput = formula1Driver.getNumberOfRaces();
        assertEquals(67,actualOutput);
    }
}