package clients.customer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dbAccess.reserveR;
import middle.LocalMiddleFactory;
import middle.reserveException;

class CustomerModelTest {

	@Test
	final void testDoReserve() throws reserveException {
        CustomerModel customerModel = new CustomerModel(new LocalMiddleFactory()); //  creating middlefactory to instantiate objects
        reserveR ReserveR; // need to connect to the database
        
        int expected = 0; 
        int result = 0; 
        try {
            ReserveR = new reserveR(); // creating new instance of reserveR to get access to database
            expected = ReserveR.checkReservationCount(); // call the method to count the records
            customerModel.doReserve(); // perform a reservation
            result = ReserveR.checkReservationCount(); //  call the method to count the records
        } catch (Exception e) {
            System.out.println("Error whilst checking count: " + e);
        }
        assertEquals(expected , result); //result should be equal to expected as the reservation is an autoincrement 

        if (expected != result) {
            fail("Expected count to be identical to id number");
        }



    }

}
