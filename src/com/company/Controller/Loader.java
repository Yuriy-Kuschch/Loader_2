package com.company.Controller;

import com.company.Model.Cart;
import com.company.Model.Storage;
import com.company.View.Logger;

import java.util.concurrent.Exchanger;

public class Loader implements Runnable {
    final int SPEED = 3;

    boolean isEx;
    Cart cart;
    Storage storage;

    Exchanger<Cart> ex;
    Thread transporter;
    Thread myThread;

    public Loader(Storage storage, Cart cart) {
        this.storage = storage;
        this.cart = cart;
        isEx = false;
    }
    public Loader(Storage storage, Exchanger<Cart> ex) {
        this.storage = storage;
        this.ex = ex;
        isEx = true;
    }
    public void setThreads(Thread transporter, Thread myThread){
        this.transporter = transporter;
        this.myThread = myThread;
    }
    @Override
    public void run() {
        if(isEx)
            cart = new Cart();
        while (!storage.isEmpty()){
            try {
                Logger.startCartDo("Loader", "loading", SPEED);
                load();
                Logger.endCartDo("Loader", "loading");
                Logger.storageRemainder(storage.getFullness());
                Logger.delegating("Loader", "Transporter");
                if(!isEx) {
                    synchronized (transporter) {
                        transporter.notify();
                    }
                    synchronized (myThread) {
                        myThread.wait();
                    }
                }
                else{
                    ex.exchange(cart);
                    ex.exchange(cart);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Logger.storageEmpty();
    }

    private void load() throws InterruptedException {
        int amount = 0;
        boolean track = false;
        do{
            if (storage.isEmpty())
                break;
            amount = storage.getMaterial(SPEED);
            myThread.sleep(1000);
            track = !cart.addToCart(amount);
            Logger.cartDo("load", amount, cart.getFullness());
        }while (track);
    }
    }
