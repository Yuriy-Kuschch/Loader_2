package com.company.Controller;

import com.company.Model.Cart;
import com.company.View.Logger;

import java.util.concurrent.Exchanger;

public class Unloader implements Runnable{
    final int SPEED = 2;

    boolean isEx;
    Cart cart;

    Exchanger<Cart> ex;
    Thread transporter;
    Thread myThread;

    public Unloader(Exchanger<Cart> ex) {
        this.ex = ex;
        isEx = true;
    }
    public Unloader(Cart cart) {
        this.cart = cart;
        isEx = false;
    }
    public void setThreads(Thread transporter, Thread myThread){
        this.transporter = transporter;
        this.myThread = myThread;
    }

    @Override
    public void run() {
        while (true){
            try {
                if(!isEx){
                    synchronized (myThread){
                        myThread.wait();
                    }
                }
                else
                    cart = ex.exchange(cart);
                Logger.startCartDo("Unloader", "unload", SPEED);
                //cart = ex.exchange(cart);
                unload();
                //ex.exchange(cart);
                Logger.endCartDo("Unloader", "unloading");
                Logger.delegating("Unloader", "Transporter");
                if(!isEx){
                    synchronized (transporter){
                        transporter.notify();
                    }
                }
                else
                    ex.exchange(cart);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void unload() throws InterruptedException {
        boolean tracker = false;
        do{
            myThread.sleep(1000);
            tracker = !cart.deleteFromCart(SPEED);
            Logger.cartDo("unload", SPEED, cart.getFullness());
        }while (tracker);
    }
}
