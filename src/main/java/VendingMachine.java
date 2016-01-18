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

    private double totalMoney = 0.00;
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
        final Map<Coin, Double> myCoinValue = new HashMap<Coin, Double>();
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
        if (canRetrieveProduct()) {
            return this.selectedProduct;
        }
        return null;
    }

    private boolean canRetrieveProduct() {
        if (selectedProduct != null) {
            final double myProductCosts = this.productCost.get(this.selectedProduct);
            return (this.totalMoney >= myProductCosts);
        }
        return false;
    }

    public String getDisplay() {
        String myResult = INSERT_COIN;
        if (canRetrieveProduct()) {
            applyChangeToCoinReturn();
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
            this.totalMoney += coinValues.get(money);
        } else {
            this.coinReturn.add(money);
            throw new RuntimeException("Invalid Coin");
        }
    }

    public List<Coin> removeCoinsFromCoinReturn() {
        final List<Coin> myReturns = coinReturn;
        coinReturn = new ArrayList<Coin>();
        return myReturns;
    }

    private void applyChangeToCoinReturn() {
        int myQuarters = Double.valueOf((totalMoney - this.productCost.get(this.selectedProduct)) / .25).intValue();
        for (int i = 0; i < myQuarters; ++i) {
            coinReturn.add(Coin.QUARTER);
            totalMoney -= .25;
        }
        int myDimes = Double.valueOf((totalMoney - this.productCost.get(this.selectedProduct)) / .1).intValue();
        for (int i = 0; i < myDimes; ++i) {
            coinReturn.add(Coin.DIME);
            totalMoney -= .1;
        }
        int myNickels = Double.valueOf((totalMoney - this.productCost.get(this.selectedProduct)) / .05).intValue();
        for (int i = 0; i < myNickels; ++i) {
            coinReturn.add(Coin.NICKEL);
            totalMoney -= .05;
        }
    }

}
