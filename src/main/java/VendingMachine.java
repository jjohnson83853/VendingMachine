import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bullprog3 on 1/17/16.
 */
public class VendingMachine {

    private double totalMoney;
    private List<Double> coinReturn = new ArrayList<Double>();
    private final static Set<Double> validCoins = new HashSet<Double>();

    {
        validCoins.add(.05);
        validCoins.add(.25);
        validCoins.add(.10);
    }
    public void pressButton(String button) {
    }

    public String getDisplay() {
        if (totalMoney > 0.00) {
            return NumberFormat.getCurrencyInstance().format(totalMoney);
        }
        return "INSERT COIN.";
    }

    public void insertMoney(double money) {
        if (validCoins.contains(money)) {
            totalMoney += money;
        } else {
            coinReturn.add(money);
            throw new RuntimeException("Invalid Coin");
        }

    }

    public List<Double> removeCoinsFromCoinReturn() {
        final List<Double> myReturns = coinReturn;
        coinReturn = new ArrayList<Double>();
        return myReturns;
    }
}
