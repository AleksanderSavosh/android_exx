package com.savosh.exx.twelve;

public class Notice {

    public String title = "Notice title";
    public String text = "Text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text " +
            "text text text text text ";
    public byte[] img;
    public String type = "Type";

    public Notice() {
    }

    public Notice(byte[] img) {
        this.img = img;
    }
}
