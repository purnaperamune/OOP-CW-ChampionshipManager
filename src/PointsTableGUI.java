import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class PointsTableGUI extends JFrame {
    public JPanel topPanel;
    public JTable table;
    public JScrollPane scrollPane;
    public String[] tableHeader = new String[3];
    public String[] tableHeaderRaceInfo = new String[2];
    public String[][] driverStatDescending = new String[9][3];
    public String[][] driverStatAscending = new String[9][3];
    public String[][] driverStatPosition = new String[9][3];
    public String[][] raceInfo = new String[2][5];


    JButton btnPointsDescendingOrder = new JButton("Points Table - Descending Order");
    JButton btnPointsAscendingOrder= new JButton("Points Table - Ascending Order");
    JButton btnSortFirstPosition= new JButton("Sort - 1st Positions");
    JButton btnRandomRace= new JButton("Random Race");
    JButton btnSortByDate= new JButton("Sort Race - Date");


    public PointsTableGUI() {
        setTitle("Formula1 Championship-Points Table");
        setSize(1040, 570);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnPointsDescendingOrder.setBounds(375, 250, 280, 50);
        btnPointsAscendingOrder.setBounds(375,300,280,50);
        btnSortFirstPosition.setBounds(375,350,145,50);
        btnRandomRace.setBounds(520,350,135,50);
        btnSortByDate.setBounds(375,400,280,50);

        tableHeader = new String[]{"Name", "Location", "Team","First Positions","Second Positions",
        "Third Positions","Total Points","Number Of Races"};
        tableHeaderRaceInfo= new String[]{"Race Name","Race Date","First Place","Second Place","Third Place"};
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

        topPanel.add(scrollPane);
        topPanel.add(btnPointsDescendingOrder);
        topPanel.add(btnPointsAscendingOrder);
        topPanel.add(btnSortFirstPosition);
        topPanel.add(btnRandomRace);
        topPanel.add(btnSortByDate);


        btnPointsDescendingOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel statTableDescending = new statTableDescending();
                table.setModel(statTableDescending);
            }
        }
        );

        btnPointsAscendingOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel statTableAscending = new statTableAscending();
                table.setModel(statTableAscending);
            }
        }
        );

        btnSortFirstPosition.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel statTablePosition = new statTablePosition();
                table.setModel(statTablePosition);
            }
        }
        );

        btnRandomRace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                PointsTableGUI gui = new PointsTableGUI();

                Formula1ChampionshipManager formula1ChampionshipManager=new Formula1ChampionshipManager();

                formula1ChampionshipManager.randomRace();


                JOptionPane.showMessageDialog(topPanel,"Random Race Successfully Generated! Click " +
                        "'Points Table - Descending Order' button to view updated results. ");

                gui.setVisible(true);
            }
        }
        );

        btnSortByDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TableModel raceInfoTable = new raceInfoTable();
                table.setModel(raceInfoTable);
            }
        }
        );
    }

    public class statTableDescending extends DefaultTableModel {
        statTableDescending() {
            super(driverStatDescending, tableHeader);
        }
    }
    public class statTableAscending extends DefaultTableModel {
        statTableAscending() {
            super(driverStatAscending, tableHeader);
        }
    }
    public class statTablePosition extends DefaultTableModel {
        statTablePosition() {
            super(driverStatPosition, tableHeader);
        }
    }
    public class raceInfoTable extends DefaultTableModel {
        raceInfoTable() {
            super(raceInfo, tableHeaderRaceInfo);
        }
    }


    public static void main(String args[]) {
        PointsTableGUI x = new PointsTableGUI();
        x.setVisible(true);
    }
}
