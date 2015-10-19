package com.savosh.exx.exx11.model;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.*;

public class ImageInfo implements Serializable {

    public static final List<ImageInfo> CACHE = new Vector<ImageInfo>();
    static {
        CACHE.add(new ImageInfo("About 11", "http://elevencampaign.org/wp-content/themes/elevn/images/eleven_logo_kids_large_notext.png", "elevencampaign.org"));
//        CACHE.add(new ImageInfo("Cat 1", "http://i.ytimg.com/vi/mSFTRoBY99s/hqdefault.jpg", "ytimg.com"));
//        CACHE.add(new ImageInfo("Cat 2", "http://i.ytimg.com/vi/mSFTRoBY99s/hqdefault.jpg", "ytimg.com"));
        CACHE.add(new ImageInfo("About 11", "http://bamalovesoul.com/wp-content/uploads/2010/08/11.jpg", "bamalovesoul.com"));
        CACHE.add(new ImageInfo("About 11", "http://www.i2symbol.com/images/symbols/roman/roman_numeral_eleven_u216A_icon_256x256.png", "i2symbol.com"));
        CACHE.add(new ImageInfo("About 11", "http://upload.wikimedia.org/wikipedia/commons/d/d8/Eleven.jpg", "wikimedia.org"));
        CACHE.add(new ImageInfo("About 11", "http://s.twistynoodle.com/img/r/number-eleven/eleven/eleven_coloring_page_png_468x609_q85.jpg?ctok=20120123092921", "twistynoodle.com"));
        CACHE.add(new ImageInfo("About 11", "http://yuzhigang.droppages.com/eleven.jpg", "droppages.com"));
        CACHE.add(new ImageInfo("About 11", "http://hipaustinmom.com/wp-content/uploads/2013/02/Eleven-logo.jpg", "hipaustinmom.com"));
        CACHE.add(new ImageInfo("About 11", "http://www.deadcity.ca/images/eleven.jpg", "deadcity.ca"));
        CACHE.add(new ImageInfo("About 11", "http://bpando.org/wp-content/uploads/01_Celestine_Eleven_BPO_Construct_Logo.jpg", "bpando.org"));
        CACHE.add(new ImageInfo("About 11", "http://classroomclipart.com/images/gallery/Clipart/Numbers/eleven.jpg", "classroomclipart.com"));
    }
    public static int currentItemPosition = 0;


    private Bitmap image;
    private String header;
    private String url;
    private String from;

    public ImageInfo(String header, String url, String from) {
        this.header = header;
        this.url = url;
        this.from = from;
    }

    public String getHeader() {
        return header;
    }

    public String getUrl() {
        return url;
    }

    public String getFrom() {
        return from;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
