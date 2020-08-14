package vplan.utils;

public class Log {

    public static void prln(String message) {
        System.out.println(message);
    }
    public static void prln() {
        System.out.println(" ");
    }

    public static void prErr(String message) {
        System.out.println(Data.error + " " + message + " " + Data.error);
    }

    public static void prLogo() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("d88888b db      .d8888.     db    db d8888b. db       .d8b.  d8b   db     .d8888. d88888b d8888b. db    db d88888b d8888b.");
        System.out.println("88'     88      88'  YP     88    88 88  `8D 88      d8' `8b 888o  88     88'  YP 88'     88  `8D 88    88 88'     88  `8D");
        System.out.println("88ooo   88      `8bo.       Y8    8P 88oodD' 88      88ooo88 88V8o 88     `8bo.   88ooooo 88oobY' Y8    8P 88ooooo 88oobY'");
        System.out.println("88~~~   88        `Y8b.     `8b  d8' 88~~~   88      88~~~88 88 V8o88       `Y8b. 88~~~~~ 88`8b   `8b  d8' 88~~~~~ 88`8b  ");
        System.out.println("88      88booo. db   8D      `8bd8'  88      88booo. 88   88 88  V888     db   8D 88.     88 `88.  `8bd8'  88.     88 `88.");
        System.out.println("YP      Y88888P `8888Y'        YP    88      Y88888P YP   YP VP   V8P     `8888Y' Y88888P 88   YD    YP    Y88888P 88   YD");
        System.out.println("                                                                             ");
        System.out.println("Programmiert von Malte Fuchs und Jonas Hoffmann!");
        System.out.println();
        System.out.println();
    }

}
