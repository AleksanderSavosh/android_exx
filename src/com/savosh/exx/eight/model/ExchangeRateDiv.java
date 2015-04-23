package com.savosh.exx.eight.model;


public class ExchangeRateDiv {

    private ExchangeRate base;
    private ExchangeRate another;
    private String divBuy;
    private String divSell;
    private String divNbu;


    public ExchangeRateDiv(ExchangeRate base, ExchangeRate another) {
        this.base = base;
        this.another = another;
        if (base != null && another != null) {
            if (base.getPurchaseRate() != null && another.getPurchaseRate() != null) {
                divBuy = "" + (Double.valueOf(base.getPurchaseRate()) - Double.valueOf(another.getPurchaseRate()));
            }
            if (base.getSaleRate() != null && another.getSaleRate() != null) {
                divSell = "" + (Double.valueOf(base.getSaleRate()) - Double.valueOf(another.getSaleRate()));
            }
            if (base.getPurchaseRateNB() != null && another.getPurchaseRateNB() != null) {
                divNbu = "" + (Double.valueOf(base.getPurchaseRateNB()) - Double.valueOf(another.getPurchaseRateNB()));
            }
        }
    }

    public ExchangeRate getBase() {
        return base;
    }

    public ExchangeRate getAnother() {
        return another;
    }

    public String getDivBuy() {
        return divBuy;
    }

    public String getDivSell() {
        return divSell;
    }

    public String getDivNbu() {
        return divNbu;
    }
}
