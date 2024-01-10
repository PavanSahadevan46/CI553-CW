package clients.customer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dbAccess.reserveRW;
import middle.LocalMiddleFactory;
import middle.reserveException;

class CustomerModelTest {

	@Test
	final void testDoReserve() throws reserveException {
        CustomerModel customerModel = new CustomerModel(new LocalMiddleFactory());
        reserveRW ReserveRW;
        
        int expected = 0;
        int result = 0;
        try {
            ReserveRW = new reserveRW();
            expected = ReserveRW.checkReservationCount();
            customerModel.doReserve();
            result = ReserveRW.checkReservationCount();
        } catch (Exception e) {
            System.out.println("Error whilst checking count: " + e);
        }
        assertEquals(expected , result);

        if (expected != result) {
            fail("Expected count to be identical to id number");
        }



    }

}
