package com.company;

import com.company.Controller.Loader;
import com.company.Controller.Transporter;
import com.company.Controller.Unloader;
import com.company.Model.Cart;
import com.company.Model.Storage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Cart cart = new Cart();

        Transporter transporter = new Transporter(cart);
        Loader loader = new Loader(storage, cart);
        Unloader unloader = new Unloader(cart);

        Thread threadLoader = new Thread(loader);
        Thread threadTransporter = new Thread(transporter);
        threadTransporter.setDaemon(true);
        Thread threadUnloader = new Thread(unloader);
        threadUnloader.setDaemon(true);
        transporter.setThreads(threadLoader, threadUnloader, threadTransporter);
        loader.setThreads(threadTransporter, threadLoader);
        unloader.setThreads(threadTransporter, threadUnloader);

        threadTransporter.start();
        threadLoader.start();
        threadUnloader.start();
    }
}

