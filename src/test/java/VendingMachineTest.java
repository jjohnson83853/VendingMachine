
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bullprog3 on 1/17/16.
 */
public class VendingMachineTest {
    @Test
    public void selectProduct() {
        final VendingMachine myVendingMachine = new VendingMachine();
        myVendingMachine.pressButton("cola");
        assertEquals("COLA SELECTED.", myVendingMachine.getDisplay());
    }
}
