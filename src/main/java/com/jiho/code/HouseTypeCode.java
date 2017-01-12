package com.jiho.code;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public enum HouseTypeCode {

    MULTIPLEX_HOUSE        ("01", "다세대"),
    MULTI_HOUSEHOLD_HOUSE ("02", "다가구"),
    DETACHED_HOUSE         ("03", "단독주택"),
    DETACHED_HOUSE_SOLAR    ("04", "단독주택_태양열"),
    TOWN_HOUSE    ("05", "연립주택"),
    APARTMENT ("06", "아파트"),
    MIX_BUILDING ("07", "혼합건물"),
    PREFAB ("08", "조립식건물"),
    COMPLEX_BUILDING ("09", "주상복합"),
    EFFICENCY_APARTMENT ("10", "오피스텔"),
    ELDERLY_WELFARE_HOUSE ("11", "노인복지주택"),
    ETC	("99", "기타"),
    ALL ("", "ALL");

    public String code;
    public String desc;

    private HouseTypeCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
