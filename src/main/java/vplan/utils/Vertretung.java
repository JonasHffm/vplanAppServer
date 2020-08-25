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


    public Vertretung(String schultyp, String datum, String klasse, String stunde, String teacher, String fach, String raum, String vteacher, String vfach, String vraum, String merkmal, String info) {
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

    public void setSchultyp(String schultyp) {
        this.schultyp = schultyp;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getStunde() {
        return stunde;
    }

    public void setStunde(String stunde) {
        this.stunde = stunde;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }

    public String getVteacher() {
        return vteacher;
    }

    public void setVteacher(String vteacher) {
        this.vteacher = vteacher;
    }

    public String getVfach() {
        return vfach;
    }

    public void setVfach(String vfach) {
        this.vfach = vfach;
    }

    public String getVraum() {
        return vraum;
    }

    public void setVraum(String vraum) {
        this.vraum = vraum;
    }

    public String getMerkmal() {
        return merkmal;
    }

    public void setMerkmal(String merkmal) {
        this.merkmal = merkmal;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
