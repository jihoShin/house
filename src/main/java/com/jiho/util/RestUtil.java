package com.jiho.util;

import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by jiho87.shin on 2017-01-17.
 */
public class RestUtil {


    public static void addParam(UriComponentsBuilder uriComponentsBuilder, String name, String value){
        if(uriComponentsBuilder == null || StringUtils.isEmpty(name) || StringUtils.isEmpty(value) ){
            return;
        }
        uriComponentsBuilder.queryParam(name, value);
    }
}
