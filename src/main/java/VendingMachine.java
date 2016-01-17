import java.text.NumberFormat;
import java.util.*;

/**
 * Created by bullprog3 on 1/17/16.
 */
public class VendingMachine {

    public enum Coin {
        QUARTER, NICKEL, DIME, DOLLAR
    }

    public enum Button {
        COLA, CHIPS, CANDY
    }

    private final Map<Coin, Double> coinValues = new HashMap<Coin, Double>();
    private final static Set<Coin> validCoins = new HashSet<Coin>();
    private final static Map<Button, Double> productCost = new HashMap<Button, Double>();

    private double totalMoney;
    private List<Coin> coinReturn = new ArrayList<Coin>();
    private Button selectedProduct = null;

    {
        validCoins.add(Coin.NICKEL);
        validCoins.add(Coin.DIME);
        validCoins.add(Coin.QUARTER);

        coinValues.put(Coin.NICKEL, .05);
        coinValues.put(Coin.DOLLAR, 1.0);
        coinValues.put(Coin.DIME, .1);
        coinValues.put(Coin.QUARTER, .25);

        System.out.println("startup");
        productCost.put(Button.CANDY, .65);
        productCost.put(Button.CHIPS, .5);
        productCost.put(Button.COLA, 1.0);
    }

    public void pressButton(Button button) {
        this.selectedProduct = button;
    }

    public Button retrieveProduct() {
        if (totalMoney == productCost.get(this.selectedProduct)) {
            return this.selectedProduct;
        }
        return null;
    }

    public String getDisplay() {
        if (totalMoney > 0.00) {
            return NumberFormat.getCurrencyInstance().format(totalMoney);
        }
        return "INSERT COIN.";
    }

    public void insertMoney(Coin money) {
        if (validCoins.contains(money)) {
            totalMoney += coinValues.get(money);
        } else {
            coinReturn.add(money);
            throw new RuntimeException("Invalid Coin");
        }
    }

    public List<Coin> removeCoinsFromCoinReturn() {
        final List<Coin> myReturns = coinReturn;
        coinReturn = new ArrayList<Coin>();
        return myReturns;
    }
}
