package clients.reservation;

import middle.MiddleFactory;
import middle.Names;
import middle.RemoteMiddleFactory;

import javax.swing.*;

import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;

public class reservationClient {
    public static void main(String[] args) {
        RemoteMiddleFactory mrf = new RemoteMiddleFactory();
        displayGUI(mrf);  
    }
    private static void displayGUI(MiddleFactory mf)
    {
        JFrame  window = new JFrame();     
        window.setTitle( "Reservation Client" );
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        CustomerModel model = new CustomerModel(mf);
        CustomerView  view  = new CustomerView( window, mf, 0, 0 );
        CustomerController cont  = new CustomerController( model, view );
        view.setController( cont );

        model.addObserver( view );       // Add observer to the model
        window.setVisible(true);         // Display Scree
    }
}


   
