package com.xgen.interview;

import java.lang.reflect.Array;
import java.util.*;


/**
 * This is the current implementation of ShoppingCart.
 * Please write a replacement
 */
public class ShoppingCart implements IShoppingCart {
    LinkedHashMap<String, Integer> contents = new LinkedHashMap<>();
    Pricer pricer;
    boolean showPriceFirst;

    public ShoppingCart(Pricer pricer) {
        this.pricer = pricer;
        this.showPriceFirst = false;
    }

    public ShoppingCart(Pricer pricer, boolean showPriceFirst) {
        this.pricer = pricer;
        this.showPriceFirst = showPriceFirst;
    }

    public void addItem(String itemType, int number) {
        if (!contents.containsKey(itemType)) {
            contents.put(itemType, number);
        } else {
            int existing = contents.get(itemType);
            contents.put(itemType, existing + number);
        }
    }

    public void printReceipt() {
        float total = 0f;
        String priceTotal = "";

        for (String itemType: contents.keySet()) {
            float priceFloat = (float) pricer.getPrice(itemType) * contents.get(itemType) / 100;
            total += priceFloat;
            priceTotal = String.format("€%.2f", total);
            String priceString = String.format("€%.2f", priceFloat);

            if(this.showPriceFirst)
                System.out.println(priceString + " - " + itemType + " - " + contents.get(itemType));
            else
                System.out.println(itemType + " - " + contents.get(itemType) + " - " + priceString);
        }

        System.out.println("total - " + priceTotal);
    }
}
