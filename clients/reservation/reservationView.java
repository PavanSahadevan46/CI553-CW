package clients.reservation;

import middle.MiddleFactory;
import middle.OrderException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class reservationView extends JFrame implements Observer  {
    private static final long serialVersionUID = 1L;
    private Font font = new Font("Monospaced",Font.BOLD,24);
    private int H = 300;         // Height of window 
    private int W = 400;         // Width  of window 
    private String textToDisplay = "eeee";
    private reservationController cont = null;
    
    private reservationModel model;
    private JTable table;

    public reservationView(RootPaneContainer rpc, MiddleFactory mf, int x, int y , reservationModel model){
        this.model = model;
        model.addObserver(this);

        Container cp         = rpc.getContentPane();    // Content Pane
        Container rootWindow = (Container) rpc;         // Root Window
        cp.setLayout( new BorderLayout() );             // Border N E S W CENTER 
        rootWindow.setSize( W, H );                     // Size of Window  
        rootWindow.setLocation( x, y );                 // Position on screen
        rootWindow.setVisible( true );                 // Make visible

        table = new JTable(model.getTableModel());
        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane, BorderLayout.CENTER);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e ->
            model.retrieveTableData()
        );
       
        cp.add(refreshButton, BorderLayout.SOUTH);
    }


    @Override
    public void update(Observable o, Object arg) {
        table.setModel(model.getTableModel());
        
    }


    public void setController(reservationController c) {
        cont = c;
    }
}
