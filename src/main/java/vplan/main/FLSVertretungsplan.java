package vplan.main;

import vplan.server.Server;
import vplan.utils.Configuration;
import vplan.utils.Log;
import java.io.IOException;

public class FLSVertretungsplan {

    public static Server server;
    public static String dateToSend;

    public static Initializer instance;

    public static void main(String[] args) throws IOException {
        // Do we have to load specific configurations?
        Configuration serverConfiguration = null;
        if (args.length > 0) {
            serverConfiguration = Configuration.fromConfiguration(args[0]);
        } else {
            serverConfiguration = new Configuration();
        }

        Log.prLogo();
        instance = new Initializer(serverConfiguration);
        instance.init();

        System.out.println();
        System.out.println();

        server = new Server(serverConfiguration.getServerPort(), serverConfiguration.getListenAddress());
        server.boot();

        // Install something to gracefully shutting down.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutdown server!");
                server.shutdown();
                // Gracefully give us some time until everything is shut down.
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
