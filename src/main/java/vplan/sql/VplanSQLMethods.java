package vplan.sql;

import vplan.main.FLSVertretungsplan;
import vplan.utils.Data;
import vplan.utils.Vertretung;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class VplanSQLMethods {

    private boolean done = true;

    public void getVertretungsstundenAtDate(final String date) {

        ArrayList<Vertretung> vertretungsList = new ArrayList<Vertretung>();

        System.out.println(">>> Lade Daten vom " + date);
        Data.acceptClients = false;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            ResultSet rs = FLSVertretungsplan.instance.getMysql().getResults("SELECT DISTINCT vpday, vphour, vpstart, vpend, vpclass, vptutor, vpsubject, vproom, vpctutor, vpcsubject, vpcroom, vpnote, vpinfo, vpcourse, vpschool, vptype, vpguid FROM fls_vplan WHERE vpday = '" + date + "'");

            int downloadCount = 0;
            @Override
            public void run() {

                try {

                    int vpid = 0;

                    if(rs.next()) {

                        String schultyp = String.valueOf(rs.getInt("vpschool"));
                        String datum = rs.getDate("vpday").toString();
                        String klasse = rs.getString("vpclass");
                        String stunde = rs.getString("vphour") + "|" + rs.getTime("vpstart").toString() + "|" + rs.getTime("vpend").toString();
                        String teacher = getVpTutor(rs.getInt("vptutor"));
                        String fach = getVpSubject(rs.getInt("vpsubject"));
                        String raum = rs.getString("vproom");
                        String vteacher = getVpcTutor(rs.getInt("vpctutor"));
                        String vfach = getVpcSubject(rs.getInt("vpcsubject"));
                        String vraum = rs.getString("vpcroom");
                        String merkmal = rs.getString("vpnote");
                        String info = rs.getString("vpinfo");

                        Vertretung vertretung = new Vertretung(schultyp, datum, klasse, stunde, teacher, fach, raum, vteacher, vfach, vraum, merkmal, info);
                        Data._vertretungsList.add(vertretung);
                        downloadCount++;
                        //System.out.println("Loading...  DAY(" + date + ")   >>   " + vertretung + "  (" + currentPos + " / " + lenght + ")");

                    }else {
                        System.out.println("<<< Download der Daten vom " + date + " abgeschlossen!  (Stunden: " + downloadCount + ")");
                        downloadCount = 0;
                        Data.acceptClients = true;
                        Data.downloadCounter++;
                        this.cancel();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }, 1000 * 2, 10);

    }

    public void packVertretungsstunden() {

        if(Data._vertretungsList.size() != 0) {
            int packed = 0;
            for (Vertretung vstunde : Data._vertretungsList) {

                for(Vertretung check : Data._vertretungsList) {
                    String schultyp = vstunde.getSchultyp();
                    String datum = vstunde.getDatum();
                    String klasse = vstunde.getKlasse();
                    String raum = vstunde.getRaum();
                    String tutor = vstunde.getTeacher();
                    String fach = vstunde.getFach();

                    if (schultyp.equals(check.getSchultyp())
                            && datum.equals(check.getDatum())
                            && klasse.equals(check.getKlasse())
                            && raum.equals(check.getRaum())
                            && tutor.equals(check.getTeacher())
                            && fach.equals(check.getFach())) {

                        if(check != vstunde) {
                            int stundeCHECK = Integer.valueOf(check.getStunde().split("|")[0]);
                            int stundeVSTUNDE = Integer.valueOf(vstunde.getStunde().split("|")[0]);
                            if(stundeVSTUNDE > stundeCHECK  && (Math.abs(stundeCHECK-stundeVSTUNDE) == 1)) {
                                if(stundeCHECK == 1 && stundeVSTUNDE == 2 || stundeCHECK == 3 && stundeVSTUNDE == 4 || stundeCHECK == 5 && stundeVSTUNDE == 6 || stundeCHECK == 7 && stundeVSTUNDE == 8 || stundeCHECK == 9 && stundeVSTUNDE == 10) {
                                    packed++;
                                    System.out.println();
                                    System.out.println("{<package>");
                                    //System.out.println(check.getStunde().split("|")[0] + " " + check.getStunde().split("|")[2] + " " + check.getStunde().split("|")[4]);
                                    //System.out.println(vstunde.getStunde().split("|")[0] + " " + vstunde.getStunde().split("|")[2] + " " + vstunde.getStunde().split("|")[4]);

                                    String firstTime = "";
                                    String secondTime = "";
                                    for(int i = 2; i <= 9; i++) {
                                        firstTime += check.getStunde().split("|")[i];
                                    }
                                    for(int i = 10; i <= 18; i++) {
                                        firstTime += vstunde.getStunde().split("|")[i];
                                    }
                                    System.out.println(check.getSchultyp() + ";" + check.getDatum() + ";" + check.getKlasse() + ";" + check.getStunde() + ";" + check.getTeacher() + ";" + check.getFach() + ";" + check.getRaum() + ";" + check.getVteacher() + ";" + check.getVfach() + ";" + check.getVraum() + ";" + check.getMerkmal() + ";" + check.getInfo());
                                    System.out.println(vstunde.getSchultyp() + ";" + vstunde.getDatum() + ";" + vstunde.getKlasse() + ";" + vstunde.getStunde() + ";" + vstunde.getTeacher() + ";" + vstunde.getFach() + ";" + vstunde.getRaum() + ";" + vstunde.getVteacher() + ";" + vstunde.getVfach() + ";" + vstunde.getVraum() + ";" + vstunde.getMerkmal() + ";" + vstunde.getInfo());
                                    System.out.println("<packed>");
                                    System.out.println(check.getSchultyp() + ";" + check.getDatum() + ";" + check.getKlasse() + ";" + stundeCHECK + "./" + stundeVSTUNDE + ".|" + firstTime + "|" + secondTime + ";" + check.getTeacher() + ";" + check.getFach() + ";" + check.getRaum() + ";" + check.getVteacher() + ";" + check.getVfach() + ";" + check.getVraum() + ";" + check.getMerkmal() + ";" + check.getInfo());
                                    System.out.println("</packed>");
                                    System.out.println("</package>}");
                                    System.out.println();
                                }
                            }
                        }
                    }
                }
            }
            System.out.println();
            System.out.println();
            System.out.println("Es wurden " + packed + " Stunden zusammengefasst!");
        }
    }

    public String getVpTutor(int vptutorID) throws SQLException {

        String vptutor = "";

        String _teacherFirstname = "";
        String _teacherLastname = "";
        String _teacherShortcut = "";
        ResultSet rsTeacherTable = FLSVertretungsplan.instance.getMysql().getResults("SELECT * FROM fls_teacher WHERE id = '" + vptutorID + "';");
        while (rsTeacherTable.next()) {
            _teacherFirstname = rsTeacherTable.getString("firstname");
            _teacherLastname = rsTeacherTable.getString("lastname");
            _teacherShortcut = rsTeacherTable.getString("shortcut");
        }

        vptutor = _teacherFirstname + "|" + _teacherLastname + "|" + _teacherShortcut;

        return vptutor;
    }

    public String getVpSubject(int vpsubjectID) throws SQLException {

        String _subjectName = "";
        String _subjectShortcut = "";
        ResultSet rsSubjectTable = FLSVertretungsplan.instance.getMysql().getResults("SELECT * FROM fls_subject WHERE id = '" + vpsubjectID + "';");
        while (rsSubjectTable.next()) {
            _subjectName = rsSubjectTable.getString("name");
            _subjectShortcut = rsSubjectTable.getString("shortcut");
        }
        String vpsubject = _subjectName + "|" + _subjectShortcut;

        return vpsubject;
    }


    public String getVpcTutor(int vpctutorID) throws SQLException {

        String vpctutor = "";

        String _teacherFirstname = "";
        String _teacherLastname = "";
        String _teacherShortcut = "";
        ResultSet rsTeacherTable = FLSVertretungsplan.instance.getMysql().getResults("SELECT * FROM fls_teacher WHERE id = '" + vpctutorID + "';");
        while (rsTeacherTable.next()) {
            _teacherFirstname = rsTeacherTable.getString("firstname");
            _teacherLastname = rsTeacherTable.getString("lastname");
            _teacherShortcut = rsTeacherTable.getString("shortcut");
        }

        vpctutor = _teacherFirstname + "|" + _teacherLastname + "|" + _teacherShortcut;

        return vpctutor;
    }

    public String getVpcSubject(int vpcsubjectID) throws SQLException {

        String _subjectName = "";
        String _subjectShortcut = "";
        ResultSet rsSubjectTable = FLSVertretungsplan.instance.getMysql().getResults("SELECT * FROM fls_subject WHERE id = '" + vpcsubjectID + "';");
        while (rsSubjectTable.next()) {
            _subjectName = rsSubjectTable.getString("name");
            _subjectShortcut = rsSubjectTable.getString("shortcut");
        }
        String vpcsubject = _subjectName + "|" + _subjectShortcut;

        return vpcsubject;
    }

    public String getAddedDate(int plusDays, String date) {

        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));

        int year = Integer.valueOf(date.split("/")[0]);
        int month = Integer.valueOf(date.split("/")[1]);
        int day = Integer.valueOf(date.split("/")[2]);

        day += plusDays;

        String returnDateValue = year + "-" + month + "-" + day;

        return returnDateValue;

    }


}
