package com.xgen.interview;

import com.xgen.interview.Pricer;
import com.xgen.interview.ShoppingCart;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class ShoppingCartTest {
    @Test
    public void canAddAnItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] output = myOut.toString().split("\r\n");

        assertEquals("apple - 1 - €1.00", output[0]);
    }

    @Test
    public void canAddMoreThanOneItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] output = myOut.toString().split("\r\n");

        assertEquals("apple - 2 - €2.00", output[0]);
    }

    @Test
    public void canAddDifferentItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] output = myOut.toString().split("\r\n");

        assertEquals("apple - 2 - €2.00", output[0]);
        assertEquals("banana - 1 - €2.00", output[1]);
    }

    @Test
    public void doesntExplodeOnMysteryItem() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] output = myOut.toString().split("\r\n");

        assertEquals("crisps - 2 - €0.00", output[0]);
    }

    @Test
    public void doesntExplodeOnMysteryItemPriceFirst() {
        ShoppingCart sc = new ShoppingCart(new Pricer(), true);

        sc.addItem("crisps", 2);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] output = myOut.toString().split("\r\n");

        assertEquals("€0.00 - crisps - 2", output[0]);
    }

    @Test
    public void canPrintReceiptTotalPriceFirst() {
        ShoppingCart sc = new ShoppingCart(new Pricer(), true);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();
        String[] output = result.split("\r\n");

        assertEquals("€2.00 - apple - 2", output[0]);
        assertEquals("€2.00 - banana - 1", output[1]);
        assertEquals("total - €4.00", output[2]);
    }

    @Test
    public void canPrintPriceFirst() {
        ShoppingCart sc = new ShoppingCart(new Pricer(), true);

        sc.addItem("apple", 2);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String[] output = myOut.toString().split("\r\n");

        assertEquals("€2.00 - apple - 2", output[0]);
        assertEquals("€2.00 - banana - 1", output[1]);
    }

    @Test
    public void canPrintReceiptTotal() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 3);
        sc.addItem("banana", 4);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();
        String[] output = result.split("\r\n");

        assertEquals("apple - 3 - €3.00", output[0]);
        assertEquals("banana - 4 - €8.00", output[1]);
        assertEquals("total - €11.00", output[2]);
    }

    @Test
    public void multipleSeparatedItems() {
        ShoppingCart sc = new ShoppingCart(new Pricer());

        sc.addItem("apple", 1);
        sc.addItem("banana", 1);
        sc.addItem("apple", 2);
        sc.addItem("banana", 1);
        sc.addItem("banana", 1);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));

        sc.printReceipt();

        String result = myOut.toString();
        String[] output = result.split("\r\n");

        assertEquals("apple - 3 - €3.00", output[0]);
        assertEquals("banana - 3 - €6.00", output[1]);
    }
}


