package com.company.Model;

public class Storage {
    private int capacity;
    private int fullness;

    public Storage(int storage) {
        this.capacity = storage;
        this.fullness = this.capacity;
    }

    public Storage() {
        this(100);
    }
    public boolean isEmpty(){return fullness==0;}
    public int getFullness() {
        return fullness;
    }

    public int getMaterial(int count){
        int amount = count;
        fullness -= amount;
        if(fullness < 0){
            amount += fullness;
            fullness = 0;
        }
        return amount;
    }
}
