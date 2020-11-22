package vplan.sql;

import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        //GET CURRENT DATE
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        for(int i = 0; i <= loadDays; i++) {                                                                                                              //DATE INPUT
            FLSVertretungsplan.instance.getVplanSQLMethods().getVertretungsstundenAtDate(FLSVertretungsplan.instance.getVplanSQLMethods().getAddedDate(i, dtf.format(now)));
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
