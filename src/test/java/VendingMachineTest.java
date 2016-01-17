

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by bullprog3 on 1/17/16.
 */
public class VendingMachineTest {

    @Test
    public void selectCola() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton("cola");
        assertEquals("INSERT COIN.", myVendingMachine.getDisplay());
    }

    @Test
    public void payForCola() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton("cola");
        assertEquals("INSERT COIN.", myVendingMachine.getDisplay());
        try {
            myVendingMachine.insertMoney(.25);
            assertEquals("$0.25", myVendingMachine.getDisplay());
        } catch (RuntimeException re) {
            fail();
        }
    }

    @Test
    public void noCoinsInserted() {
        final VendingMachine myVendingMachine = new VendingMachine();
        assertEquals("INSERT COIN.", myVendingMachine.getDisplay());
    }


    @Test
    public void rejectInvalidCoins() {
        final VendingMachine myVendingMachine = new VendingMachine();
        try {
            myVendingMachine.insertMoney(1.00);
            fail();
        } catch (RuntimeException re) {
            Double myReturnCoins[] = {Double.valueOf(1.00)};
            assertArrayEquals(myReturnCoins, myVendingMachine.removeCoinsFromCoinReturn().toArray());
        }
    }

}
