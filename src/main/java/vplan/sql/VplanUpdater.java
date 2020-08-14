package vplan.sql;


import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;

import java.util.Timer;
import java.util.TimerTask;

public class VplanUpdater{

    private int loadDays;
    public int lenght = 0;

    public VplanUpdater(int loadDays) {
        this.loadDays = loadDays;
        VplanUpdateTasks();
    }

    public synchronized void update() {

        for(String client : Data.clientUpdateList.keySet()) {
            Data.clientUpdateList.put(client, false);
        }
        for(int i = 0; i <= loadDays; i++) {
            FLSVertretungsplan.instance.getVplanSQLMethods().getVertretungsstundenAtDate(FLSVertretungsplan.instance.getVplanSQLMethods().getAddedDate(i, "2020/02/10"));
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
