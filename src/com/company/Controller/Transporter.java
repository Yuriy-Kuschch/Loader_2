package com.company.Controller;

import com.company.Model.Cart;
import com.company.View.Logger;

import java.util.concurrent.Exchanger;

public class Transporter implements Runnable {
    Cart cart;
    boolean to = false;
    boolean isEx;

    Thread loader;
    Thread unloader;
    Thread myThread;

    Exchanger<Cart> loadEx;
    Exchanger<Cart> unloadEx;

    public Transporter(Cart cart) {
        this.cart = cart;
        isEx = false;
    }
    public Transporter(Exchanger<Cart> loadEx, Exchanger<Cart> unloadEx) {
        this.loadEx = loadEx;
        this.unloadEx = unloadEx;
        isEx = true;
    }
    public void setThreads(Thread loader, Thread unloader, Thread myThread){
        this.loader = loader;
        this.unloader = unloader;
        this.myThread = myThread;
    }

    @Override
    public void run() {

        while(true){
            try {
                Exchanger<Cart> switchEx = null;
                if(!isEx){
                    synchronized (myThread){
                        myThread.wait();
                    }
                }
                else {
                    switchEx = this.to ? unloadEx : loadEx;
                    cart = switchEx.exchange(cart);
                }
                this.to = !this.to;
                //cart = ex.exchange(cart);
                String to = this.to ? "Unloader" : "Loader";
                Logger.runTransporter(to);
                ride();
                Logger.transporterArrived(to);
                //ex.exchange(cart);
                Logger.delegating("Transporter", to);
                if(!isEx){
                    Thread notifying = this.to ? unloader : loader;
                    synchronized (notifying){
                        notifying.notify();
                    }
                }
                else {
                    switchEx = this.to ? unloadEx : loadEx;
                    cart = switchEx.exchange(cart);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void ride() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Logger.runningTransporter(i);
            myThread.sleep(1000);
        }
    }
}
