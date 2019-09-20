package com.mycompany.Chen_Ciftarslan_a1.model;

/**
 *
 * @author Nancy Chen
 */
public class PizzaOrder {

    private String[] topping;
    private String size;
    private boolean delivery;

    public PizzaOrder() {
    }

    public String[] getTopping() {
        return topping;
    }

    public void setTopping(String[] topping) {
        this.topping = topping;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public double getPrice() {
        double price = 0.00;

        if (getSize().equalsIgnoreCase("small")) {
            price += 5;
        } else if (getSize().equalsIgnoreCase("medium")) {
            price += 7;
        } else {
            price += 9;
        }

        if (isDelivery()) {
            price += 2;
        }

        price += getTopping().length;

        return price;
    }

}
