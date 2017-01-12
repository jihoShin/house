package com.jiho.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public class Item {

    private String bnkNm;

    private long cnt;

    private long loanAmt;

    private double avgLoanRat;

    private double avgLoanRat_2;

    private double maxLoanRat;

    private double minLoanRat;





    public String getBnkNm() {
        return bnkNm;
    }

    public void setBnkNm(String bnkNm) {
        this.bnkNm = bnkNm;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    public long getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(long loanAmt) {
        this.loanAmt = loanAmt;
    }

    public double getAvgLoanRat() {
        return avgLoanRat;
    }

    public void setAvgLoanRat(double avgLoanRat) {
        this.avgLoanRat = avgLoanRat;
    }

    public double getAvgLoanRat_2() {
        return avgLoanRat_2;
    }

    public void setAvgLoanRat_2(double avgLoanRat_2) {
        this.avgLoanRat_2 = avgLoanRat_2;
    }

    public double getMaxLoanRat() {
        return maxLoanRat;
    }

    public void setMaxLoanRat(double maxLoanRat) {
        this.maxLoanRat = maxLoanRat;
    }

    public double getMinLoanRat() {
        return minLoanRat;
    }

    public void setMinLoanRat(double minLoanRat) {
        this.minLoanRat = minLoanRat;
    }
}
