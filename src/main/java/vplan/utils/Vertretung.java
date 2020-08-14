package vplan.utils;

public class Vertretung {

    private String schultyp;
    private String datum;
    private String klasse;
    private String stunde;
    private String teacher;
    private String fach;
    private String raum;
    private String vteacher;
    private String vfach;
    private String vraum;
    private String merkmal;
    private String info;


    public Vertretung(String schultyp, String datum, String klasse, String stunde, String teacher, String fach, String raum,
                      String vteacher, String vfach, String vraum,
                        String merkmal, String info) {

            this.schultyp = schultyp;
            this.datum = datum;
            this.klasse = klasse;
            this.stunde = stunde;
            this.teacher = teacher;
            this.fach = fach;
            this.raum = raum;
            this.vteacher = vteacher;
            this.vfach = vfach;
            this.vraum = vraum;
            this.merkmal = merkmal;
            this.info = info;


    }

    public String getSchultyp() {
        return schultyp;
    }
    public String getDatum() {
        return datum;
    }
    public String getKlasse() {
        return klasse;
    }
    public String getStunde() {
        return stunde;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getFach() {
        return fach;
    }
    public String getRaum() {
        return raum;
    }
    public String getVteacher() {
        return vteacher;
    }
    public String getVfach() {
        return vfach;
    }
    public String getVraum() {
        return vraum;
    }
    public String getMerkmal() {
        return merkmal;
    }
    public String getInfo() {
        return info;
    }
}
