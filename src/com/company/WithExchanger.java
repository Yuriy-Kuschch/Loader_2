package com.company;

import com.company.Controller.Loader;
import com.company.Controller.Transporter;
import com.company.Controller.Unloader;
import com.company.Model.Cart;
import com.company.Model.Storage;

import java.util.concurrent.Exchanger;

public class WithExchanger {
    public static void main(String[] args) {
        Storage storage = new Storage();

        Exchanger<Cart> loadExchanger = new Exchanger<>();
        Exchanger<Cart> unloadExchanger = new Exchanger<>();

        Transporter transporter = new Transporter(loadExchanger, unloadExchanger);
        Loader loader = new Loader(storage, loadExchanger);
        Unloader unloader = new Unloader(unloadExchanger);
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
