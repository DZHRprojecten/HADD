package com.project.hadd;

public class Globals {
    private String btAdress = "B8:27:EB:EC:4F:E5";

    private static final Globals ourInstance = new Globals();

    public static Globals getInstance() {
        return ourInstance;
    }

    private Globals() {
    }

    public String getBtAdress() {
        return btAdress;
    }

    public void setBtAdress(String btAdress) {
        this.btAdress = btAdress;
    }
}
