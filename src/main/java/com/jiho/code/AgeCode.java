package com.jiho.code;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public enum AgeCode {

    TEENAGER ("1", "10대"),
    TWENTIES ("2", "20대"),
    THIRTY ("3", "30대"),
    FORTY ("4", "40대"),
    FIFTY ("5", "50대"),
    SIXTY ("6", "60대"),
    SEVENTY ("7", "70대"),
    EIGHTY ("8", "80대"),
    NINETY ("9", "90대");

    public String code;
    public String desc;

    private AgeCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
