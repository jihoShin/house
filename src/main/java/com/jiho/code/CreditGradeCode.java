package com.jiho.code;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public enum CreditGradeCode {

    GRADE_1 ("1", "1등급"),
    GRADE_2 ("2", "2등급"),
    GRADE_3 ("3", "3등급"),
    GRADE_4 ("4", "4등급"),
    GRADE_5 ("5", "5등급"),
    GRADE_6 ("6", "6등급"),
    GRADE_7 ("7", "7등급"),
    GRADE_8 ("8", "8등급"),
    GRADE_9 ("9", "9등급"),
    GRADE_10 ("10", "10등급"),
    ALL ("", "ALL")
    ;

    public String code;
    public String desc;

    private CreditGradeCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
