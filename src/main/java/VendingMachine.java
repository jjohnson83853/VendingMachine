import java.text.NumberFormat;

/**
 * Created by bullprog3 on 1/17/16.
 */
public class VendingMachine {

    private double totalMoney;

    public void pressButton(String button) {
    }

    public String getDisplay() {
        if (totalMoney > 0.00) {
            return NumberFormat.getCurrencyInstance().format(totalMoney);
        }
        return "COLA SELECTED.";
    }

    public void insertMoney(double money) {
        ++totalMoney;
    }
}
