package com.company.Model;

public class Cart {
    private int size;
    private int fullness = 0;

    public Cart(int size) {
        this.size = size;
    }

    public Cart() {
        this(6);
    }

    public int getFullness() {
        return fullness;
    }

    public boolean addToCart(int amount){
        fullness += amount;
        if(fullness >= size)
            return true;
        return false;
    }
    public boolean deleteFromCart(int amount){
        fullness -= amount;
        if(fullness <= 0){
            fullness = 0;
            return true;
        }
        return false;
    }
}
