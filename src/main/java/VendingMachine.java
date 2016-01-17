import java.text.NumberFormat;
import java.util.*;

/**
 * Created by bullprog3 on 1/17/16.
 */
public class VendingMachine {

    protected static final String THANK_YOU = "THANK YOU";
    protected static final String INSERT_COIN = "INSERT COIN.";

    public enum Coin {
        QUARTER, NICKEL, DIME, DOLLAR
    }

    public enum Button {
        COLA, CHIPS, CANDY
    }

    private final Map<Coin, Double> coinValues = buildCoinValue();
    private final static Set<Coin> validCoins = buildValidCoins();
    private final static Map<Button, Double> productCost = buildProductCost();

    private double totalMoney;
    private List<Coin> coinReturn = new ArrayList<Coin>();
    private Button selectedProduct = null;

    private static Set<Coin> buildValidCoins() {
        final Set<Coin> myValidCoins = new HashSet<Coin>();
        myValidCoins.add(Coin.NICKEL);
        myValidCoins.add(Coin.DIME);
        myValidCoins.add(Coin.QUARTER);
        return myValidCoins;
    }

    private static Map<Button, Double> buildProductCost() {
        final Map<Button, Double> myProductCost = new HashMap<Button, Double>();
        myProductCost.put(Button.CANDY, .65);
        myProductCost.put(Button.CHIPS, .5);
        myProductCost.put(Button.COLA, 1.0);
        return myProductCost;
    }

    private static Map<Coin, Double> buildCoinValue()
    {
        Map<Coin, Double> myCoinValue = new HashMap<Coin, Double>();
        myCoinValue.put(Coin.NICKEL, .05);
        myCoinValue.put(Coin.DOLLAR, 1.0);
        myCoinValue.put(Coin.DIME, .1);
        myCoinValue.put(Coin.QUARTER, .25);
        return myCoinValue;
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
        String myResult = INSERT_COIN;
        if (this.selectedProduct != null && this.selectedProduct.equals(retrieveProduct())) {
            this.selectedProduct = null;
            this.totalMoney = 0.00;
            myResult = THANK_YOU;
        } else if (this.totalMoney > 0.00) {
            myResult = NumberFormat.getCurrencyInstance().format(totalMoney);
        }
        return myResult;
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
