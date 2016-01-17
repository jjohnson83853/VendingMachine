

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bullprog3 on 1/17/16.
 */
public class VendingMachineTest {

    @Test
    public void selectCola() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton("cola");
        assertEquals("COLA SELECTED.", myVendingMachine.getDisplay());
    }

    @Test
    public void payForCola() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton("cola");
        assertEquals("COLA SELECTED.", myVendingMachine.getDisplay());
        try {
            myVendingMachine.insertMoney(1.00);
            assertEquals("$1.00", myVendingMachine.getDisplay());
        } catch (RuntimeException re) {
            fail();
        }
    }
}
