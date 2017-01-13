package com.jiho.house.code;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public enum DebtCode {

    DEBT_0 ("0", "부채 없음"),
    DEBT_1 ("1", "부채 0~1000"),
    DEBT_2 ("2", "부채 1000~2000"),
    DEBT_3 ("3", "부채 2000~3000"),
    DEBT_4 ("4", "부채 3000~4000"),
    DEBT_5 ("5", "부채 4000~5000"),
    DEBT_6 ("6", "부채 5000~6000"),
    DEBT_7 ("7", "부채 6000~7000"),
    DEBT_8 ("8", "부채 7000~8000"),
    DEBT_9 ("9", "부채 8000~9000"),
    DEBT_10 ("10", "부채 9000~10,000"),
    DEBT_11 ("11", "부채 10,000~"),
    ALL ("", "ALL")
    ;

    public String code;
    public String desc;

    private DebtCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
