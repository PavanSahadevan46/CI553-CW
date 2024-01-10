package clients.reservation;

import middle.MiddleFactory;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class reservationView extends JFrame implements Observer  {
    private int H = 300;         // Height of window 
    private int W = 400;         // Width  of window 
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

        // create jtable 
        table = new JTable(model.getTableModel());
        // create a scroll bar
        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane, BorderLayout.CENTER);
        
        // add a refresh button that calls the retrieveTableData() method to update the table's rows
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e ->
            model.retrieveTableData()
        );
       // add the refresh button
        cp.add(refreshButton, BorderLayout.SOUTH);
    }


  /**
   * Update the view
   * @param modelC   The observed model
   * @param arg      Specific args 
   */
    @Override
    public void update(Observable o, Object arg) {
        table.setModel(model.getTableModel());
        
    }

    /**
    * The controller object, used so that an interaction can be passed to the controller
    * @param c   The controller
    */
    public void setController(reservationController c) {
        cont = c;
    }
}
