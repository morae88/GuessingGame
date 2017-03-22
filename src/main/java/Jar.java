import java.util.Random;

public class Jar {
    private String mItemName;
    private int mMaxItems;
    private int mAmount;

    public Jar(String itemName, int maxItems){
        mItemName = itemName;
        mMaxItems = maxItems;
        mAmount = fill(mMaxItems);
    }

    public String getItemName() {
        return mItemName;
    }


    public int getMaxItems() {

        return mMaxItems;
    }

    public int getAmount() {
        return mAmount;
    }

    private int fill(int maxItems) {
        Random random = new Random();
        return 1 + random.nextInt(maxItems);
    }
}