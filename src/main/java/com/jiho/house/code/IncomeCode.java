package com.jiho.house.code;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public enum IncomeCode {

    ANNUAL_INCOME_0 ("0", "무소득"),
    ANNUAL_INCOME_1 ("1", "연소득 0~1000"),
    ANNUAL_INCOME_2 ("2", "연소득 1000~2000"),
    ANNUAL_INCOME_3 ("3", "연소득 2000~3000"),
    ANNUAL_INCOME_4 ("4", "연소득 3000~4000"),
    ANNUAL_INCOME_5 ("5", "연소득 4000~5000"),
    ANNUAL_INCOME_6 ("6", "연소득 5000~6000"),
    ANNUAL_INCOME_7 ("7", "연소득 6000~7000"),
    ANNUAL_INCOME_8 ("8", "연소득 7000~8000"),
    ANNUAL_INCOME_9 ("9", "연소득 8000~9000"),
    ANNUAL_INCOME_10 ("10", "연소득 9000~10,000"),
    ANNUAL_INCOME_11 ("11", "연소득 10,000~"),
    ALL ("", "ALL");

    public String code;
    public String desc;

    private IncomeCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
