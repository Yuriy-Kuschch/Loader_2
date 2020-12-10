package com.company.View;

public class Logger {
    private Logger() {}

    /**
     * Выезжания Транспортера
     * @param route - путь транспортера
     */
    public static void runTransporter(String route){
        System.out.println("Transporter go to " + route + ", he will spent 5 sec.");
    }
    /**
     * Передвижения транспортера
     * @param current - Количество секунд трансопртера в дороге.
     */
    public static void runningTransporter(int current){
        System.out.println("Transporter is riding. He will arrive via "+(5-current)+" sec.");
    }
    /**
     * Приезд транспортера
     * @param to - Цель назначения
     */
    public static void transporterArrived(String to){
        System.out.println("Transporter is arrived to "+to);
    }

    /**
     * Начало действий с телегой
     * @param who - кто производит действие
     * @param doing - тип действие
     * @param speed - количество материала в секунду
     */
    public static void startCartDo(String who, String doing, int speed){
        System.out.println("Transporter arrived to " + who + ". " + who + " start " + doing + " with speed " + speed + " det/sec");
    }

    /**
     * Действие с телегой
     * @param doing - тип действия
     * @param amount - количество материала
     * @param size - текущее состояние телеги
     */
    public static void cartDo(String doing, int amount, int size){
        System.out.println("Materials was " + doing + ", in quantity " + amount + ". On cart " + size + " material");
    }

    /**
     * Конец действий с телегой
     * @param who - кто закончил
     * @param doing - тип действия
     */
    public static void endCartDo(String who, String doing){
        System.out.println("The "+who+" ended "+doing);
    }

    public static void storageRemainder(int remainder){
        System.out.println("On the storage "+remainder+" materials remained");
    }
    /**
     * Логирование конца песка на складе
     */
    public static void storageEmpty(){
        System.out.println("Material in storage is end");
    }

    /**
     * Логирование делегирования
     * @param who - кто делегирует
     * @param to - кому делегируют
     */
    public static void delegating(String who, String to){
        System.out.println(" \n" + who + " delegating to " + to + "\n");
    }

}
