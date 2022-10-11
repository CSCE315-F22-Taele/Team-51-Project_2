package com.ShoppingCart;

public class CartItem {
    private String product;
    private int quantity;
    private double price;

    public CartItem(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        setQuantity(this.quantity+1);
    }

    public void decreaseQuanitity() {
        if (this.quantity > 0) {
            setQuantity(this.quantity - 1);
        }
    }
}
