package com.jiho.airport.code;

/**
 * Created by jiho87.shin on 2017-01-17.
 */
public enum TerminalCode {

    TERMINAL_1 ("1", "1터미널"),
    TERMINAL_2 ("2", "2터미널");

    public String code;
    public String desc;

    private TerminalCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
