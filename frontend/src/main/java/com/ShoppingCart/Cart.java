package com.ShoppingCart;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Cart {
    private static Cart INSTANCE;

    public static Cart getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Cart();
        }
        return INSTANCE;
    }
    private Map<String, CartItem> entries;

    public Cart() {
        this.entries = new HashMap<>();
    }

    public void addProduct(String productName) {
        if (entries.containsKey(productName.toUpperCase())) {
            entries.get(productName.toUpperCase()).increaseQuantity();
        } else {
            entries.put(productName.toUpperCase(), new CartItem(productName.toUpperCase(), 1));
        }
    }

    public void removeProduct(String productName) {
        if (entries.containsKey(productName.toUpperCase())) {
            entries.get(productName.toUpperCase()).decreaseQuanitity();
        }
    }

    public int getQuantity(String productName) {
        if (entries.containsKey(productName.toUpperCase())) {
            return entries.get(productName.toUpperCase()).getQuantity();
        }
        return 0;
    }

    public double calculateTotal() {
        float total = 0;
        for (CartItem entry: entries.values()) {
            double entCost = entry.getPrice() * entry.getQuantity();
            total += entCost;
        }

        return total;
    }

    public ArrayList<CartItem> getEntries() {
        return new ArrayList<>(entries.values());
    }
}
