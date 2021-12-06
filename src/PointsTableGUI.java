import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class PointsTableGUI extends JFrame {
    private JPanel topPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private String[] tableHeader = new String[3];
    private String[] tableHeaderRaceInfo = new String[2];
    private String[] tableHeaderSearchRaceInfo;
    private String[][] driverStatDescending = new String[9][3];
    private String[][] driverStatAscending = new String[9][3];
    private String[][] driverStatPosition = new String[9][3];
    private String[][] raceInfo = new String[2][5];
    private String[][] searchRaceInfo ;


    /* Declaration of buttons in the GUI */
    JButton btnPointsDescendingOrder = new JButton("Points Table - Descending Order");
    JButton btnPointsAscendingOrder= new JButton("Points Table - Ascending Order");
    JButton btnSortFirstPosition= new JButton("Sort - 1st Positions");
    JButton btnRandomRace= new JButton("Random Race");
    JButton btnSortByDate= new JButton("Sort Race - Date");
    JButton btnProbabilityRace= new JButton("Race - Probability");
    JButton btnSearch= new JButton("Search Driver");
    public static JTextField searchBar = new JTextField(20);


    public PointsTableGUI() {
        setTitle("Formula1 Championship-Points Table");
        setSize(1040, 670);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);  I have commented this so when I close the GUI,
//        the program in the CLI will not terminate.


        /* Declaration of the place of buttons and the textfield */
        btnPointsDescendingOrder.setBounds(375, 250, 280, 50);
        btnPointsAscendingOrder.setBounds(375,300,280,50);
        btnSortFirstPosition.setBounds(375,350,145,50);
        btnRandomRace.setBounds(520,350,135,50);
        btnSortByDate.setBounds(375,400,280,50);
        btnProbabilityRace.setBounds(375,450,280,50);
        searchBar.setBounds(375,500,145,50);
        btnSearch.setBounds(520,500,135,50);


        /* Columns name of each of the table used in the GUI */
        tableHeader = new String[]{"Name", "Location", "Team","First Positions","Second Positions",
        "Third Positions","Total Points","Number Of Races"};
        tableHeaderRaceInfo= new String[]{"Race Name","Race Date","First Place","Second Place","Third Place"};
        tableHeaderSearchRaceInfo= new String[]{"Driver","Race Name","Race Date","First Position","Second Position","Third Position",};

        /* Getting data from Formula1ChampionshipManager class */
        driverStatDescending = Formula1ChampionshipManager.driverStatTableDescending();
        driverStatAscending= Formula1ChampionshipManager.driverStatTableAscending();
        driverStatPosition= Formula1ChampionshipManager.driverStatTablePosition();
        raceInfo=Formula1ChampionshipManager.sortByDate();

        topPanel = new JPanel();
        topPanel.setLayout(null);
        getContentPane().add(topPanel);

        table = new JTable();
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,20,1000,200);

        scrollPane.setBackground(Color.red);
        topPanel.setBackground(Color.darkGray);

        /* Adding buttons to the GUI */
        topPanel.add(scrollPane);
        topPanel.add(btnPointsDescendingOrder);
        topPanel.add(btnPointsAscendingOrder);
        topPanel.add(btnSortFirstPosition);
        topPanel.add(btnRandomRace);
        topPanel.add(btnSortByDate);
        topPanel.add(btnProbabilityRace);
        topPanel.add(searchBar);
        topPanel.add(btnSearch);

        /* Adding actions to buttons in the GUI */

        // Adding actions to the 'Points Table - Descending Order' button in the GUI
        btnPointsDescendingOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel statTableDescending = new statTableDescending();
                table.setModel(statTableDescending);
            }
        }
        );

        // Adding actions to the 'Points Table - Ascending Order' button in the GUI
        btnPointsAscendingOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel statTableAscending = new statTableAscending();
                table.setModel(statTableAscending);
            }
        }
        );

        // Adding actions to the 'Sort - 1st Positions' button in the GUI
        btnSortFirstPosition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel statTablePosition = new statTablePosition();
                table.setModel(statTablePosition);
            }
        }
        );

        // Adding actions to the 'Random Race' button in the GUI
        btnRandomRace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
                formula1ChampionshipManager.randomRace();

                raceInfo=Formula1ChampionshipManager.sortByDate();
                TableModel raceInfoTable = new raceInfoTable();
                table.setModel(raceInfoTable);

                JOptionPane.showMessageDialog(topPanel,"Random Race Successfully Generated!");

            }
        }
        );

        // Adding actions to the 'Sort Race - Date' button in the GUI
        btnSortByDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel raceInfoTable = new raceInfoTable();
                table.setModel(raceInfoTable);
            }
        }
        );

        // Adding actions to the 'Search' button in the GUI
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();
                searchRaceInfo=Formula1ChampionshipManager.searchDriverInfo();


                TableModel searchInfoTable = new searchInfoTable();
                table.setModel(searchInfoTable);
            }
        }
        );
    }

    /* Table Declarations */

    //Table for statistics in descending order of total points
    public class statTableDescending extends DefaultTableModel {
        statTableDescending() {
            super(driverStatDescending, tableHeader);
        }
    }

    //Table for statistics in ascending order of total points
    public class statTableAscending extends DefaultTableModel {
        statTableAscending() {
            super(driverStatAscending, tableHeader);
        }
    }

    //Table for statistics in descending order of first positions
    public class statTablePosition extends DefaultTableModel {
        statTablePosition() {
            super(driverStatPosition, tableHeader);
        }
    }

    //Table to show race information according to the descending order of the date the race happened
    public class raceInfoTable extends DefaultTableModel {
        raceInfoTable() {
            super(raceInfo, tableHeaderRaceInfo);
        }
    }

    //Table to show data of races a given driver participated
    public class searchInfoTable extends DefaultTableModel {
        searchInfoTable() {
            super(searchRaceInfo, tableHeaderSearchRaceInfo);
        }
    }


    public static void main(String args[]) {
        PointsTableGUI x = new PointsTableGUI();
        x.setVisible(true);
    }
}
