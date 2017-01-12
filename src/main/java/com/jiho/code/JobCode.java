package com.jiho.code;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public enum JobCode {

    OFFICE_WORKER ("01", "직장인"),
    PRIVATE_BUSINESS ("02", "자영업자"),
    ETC	("03", "기타"),
    ALL ("", "ALL");

    public String code;
    public String desc;

    private JobCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
