package com.project.hadd;

import android.bluetooth.BluetoothDevice;

public class Globals {
    private String btAdress = "B8:27:EB:EC:4F:E5";
    private BluetoothDevice device;

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

    public BluetoothDevice getDevice() {
        return device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }
}
