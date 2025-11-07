package snackattack.pojos;

import java.util.List;

public class CartItemPojo {

    private int productId;
    private int quantity;
    private double price;
    private double totalPrice;
    private List<Integer> additionIds;

    public CartItemPojo() {}

    public CartItemPojo(int productId, int quantity, double price, double totalPrice, List<Integer> additionIds) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.additionIds = additionIds;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Integer> getAdditionIds() {
        return additionIds;
    }

    public void setAdditionIds(List<Integer> additionIds) {
        this.additionIds = additionIds;
    }

    @Override
    public String toString() {
        return "CartItemPojo{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", additionIds=" + additionIds +
                '}';
    }
}
