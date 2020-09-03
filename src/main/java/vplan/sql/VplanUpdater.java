package vplan.sql;


import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class VplanUpdater{

    private int loadDays;
    public int lenght = 0;
    public LocalDateTime localDate;

    public VplanUpdater(int loadDays, LocalDateTime localDate) {
        this.loadDays = loadDays;
        this.localDate = localDate;
        VplanUpdateTasks();
    }

    public synchronized void update() {

        for(String client : Data.clientUpdateList.keySet()) {
            Data.clientUpdateList.put(client, false);
        }
        for(int i = 0; i <= loadDays; i++) {
            FLSVertretungsplan.instance.getVplanSQLMethods().getVertretungsstundenAtDate(FLSVertretungsplan.instance.getVplanSQLMethods().getAddedDate(i, "2020-09-05"));
        }

        //for(Vertretung vertretung : Data._vertretungsList) Main.vplanSQLMethods.removeDoubleStunden(vertretung);

    }

    public void VplanUpdateTasks() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(Data.downloadCounter == loadDays+1) {



                }
            }
        }, 0 , 2000);

    }

}
