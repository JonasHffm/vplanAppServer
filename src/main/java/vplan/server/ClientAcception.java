package vplan.server;


import vplan.utils.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientAcception extends Thread {

    private ServerSocket server;
    private Boolean timerBool = false;


    @Override
    public void run() {
        accept();
    }

    public ClientAcception(ServerSocket server) {
        this.server = server;
    }

    public void accept() {
        Timer timerScheduleAtFixed = new Timer();
        timerScheduleAtFixed.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (!timerBool) {
                        Client client = new Client(server.accept());
                        Data.clientList.add(client.getClient().getInetAddress().toString());
                        timerBool = true;
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                timerBool = false;
                            }
                        }, 1000 / 4);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 50);

    }

}
