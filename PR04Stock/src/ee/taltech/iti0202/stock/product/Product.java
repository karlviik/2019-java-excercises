package ee.taltech.iti0202.stock.product;
import ee.taltech.iti0202.stock.exceptions.StockException;

public class Product {
    private static int idCount = 0;
    private int itemId;
    private String itemName;
    private int itemPrice;

    public Product(String name, int price) throws StockException {
        if (price < 0) throw new StockException(StockException.Reason.NEGATIVE_PRICE);
        itemId = getNextId();
        itemName = name;
        itemPrice = price;
    }

    public static int getNextId() {
        idCount++;
        return idCount;
    }

    public int getId() {
        return itemId;
    }

    public String getName() {
        return itemName;
    }

    public int getPrice() {
        return itemPrice;
    }
}
