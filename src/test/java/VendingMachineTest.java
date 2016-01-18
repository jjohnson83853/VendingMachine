

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
        myVendingMachine.pressButton(VendingMachine.Button.COLA);
        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
    }


    @Test
    public void dispenseCola() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton(VendingMachine.Button.COLA);
        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
        try {
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            assertEquals(VendingMachine.Button.COLA, myVendingMachine.retrieveProduct());
        } catch (RuntimeException re) {
            fail();
        }
        assertEquals(VendingMachine.THANK_YOU, myVendingMachine.getDisplay());
        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
    }

    @Test
    public void dispenseColaPlusChange() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton(VendingMachine.Button.COLA);
        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
        try {
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.NICKEL);
            myVendingMachine.insertMoney(VendingMachine.Coin.DIME);
            assertEquals(VendingMachine.Button.COLA, myVendingMachine.retrieveProduct());
        } catch (RuntimeException re) {
            fail();
        }
        assertEquals(VendingMachine.THANK_YOU, myVendingMachine.getDisplay());

        final List<VendingMachine.Coin> myCoins = myVendingMachine.removeCoinsFromCoinReturn();
        assertEquals(3, myCoins.size());
        assertEquals(VendingMachine.Coin.QUARTER, myCoins.get(0));
        assertEquals(VendingMachine.Coin.NICKEL, myCoins.get(2));
        assertEquals(VendingMachine.Coin.DIME, myCoins.get(1));

        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
    }
    @Test
    public void dispenseColaWithoutEnoughMoney() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton(VendingMachine.Button.COLA);
        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
        try {
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            assertNull(myVendingMachine.retrieveProduct());
        } catch (RuntimeException re) {
            fail();
        }
        assertEquals("$0.75", myVendingMachine.getDisplay());
    }
    @Test
    public void payForCola() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton(VendingMachine.Button.COLA);
        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
        try {
            myVendingMachine.insertMoney(VendingMachine.Coin.QUARTER);
            assertEquals("$0.25", myVendingMachine.getDisplay());
        } catch (RuntimeException re) {
            fail();
        }
    }

    @Test
    public void noCoinsInserted() {
        final VendingMachine myVendingMachine = new VendingMachine();
        assertEquals(VendingMachine.INSERT_COIN, myVendingMachine.getDisplay());
    }


    @Test
    public void rejectInvalidCoins() {
        final VendingMachine myVendingMachine = new VendingMachine();
        try {
            myVendingMachine.insertMoney(VendingMachine.Coin.DOLLAR);
            fail();
        } catch (RuntimeException re) {
            VendingMachine.Coin myReturnCoins[] = {VendingMachine.Coin.DOLLAR};
            assertArrayEquals(myReturnCoins, myVendingMachine.removeCoinsFromCoinReturn().toArray());
        }
    }


}
