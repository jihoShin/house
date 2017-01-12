package com.jiho.util;

import com.jiho.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.ServiceMode;
import java.net.URI;

/**
 * Created by jiho87.shin on 2017-01-09.
 */
@Service
public class RestUtil {

    private String serviceKey="yS1djcMZ9BW0TowDL1iBgdKEWNP%2FfNItjmvxiHD5qy7HdJPvNlc8Kkbchj6DJ7wrxbNenLdmJdto7g8cWaFbfw%3D%3D";
    private String baseUrl="http://apidev.hf.go.kr:8090";
    private String path = "/service/rest/rentloanratmultidim/getRentLoanRatMultiDim";

    public static String SERVICE_KEY="serviceKey";
    public static String LOAN_YM="loan_ym";
    public static String CB_GRD="cb_grd";
    public static String JOB_CD="job_cd";
    public static String HOUSE_TYCD="house_tycd";
    public static String AGE="age";
    public static String INCOME="income";
    public static String DEBT="debt";

    @Autowired
    private RestTemplate restTemplate;


    public Response requestApi(String loan_ym, String cb_grd, String job_cd, String house_tycd, String age, String income, String debt){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path(path)
                .queryParam(SERVICE_KEY, serviceKey)
                .queryParam(LOAN_YM,loan_ym);
        addParam(uriComponentsBuilder, CB_GRD, cb_grd);
        addParam(uriComponentsBuilder, JOB_CD, job_cd);
        addParam(uriComponentsBuilder, HOUSE_TYCD, house_tycd);
        addParam(uriComponentsBuilder, AGE, age);
        addParam(uriComponentsBuilder, INCOME, income);
        addParam(uriComponentsBuilder, DEBT, debt);

        URI uri = uriComponentsBuilder.build(true).toUri();
        MultiValueMap<String, String> headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);

        ResponseEntity<Response> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Response.class);
        if(result == null){
            return null;
        }
        return result.getBody();
    }


    private void addParam(UriComponentsBuilder uriComponentsBuilder, String name, String value){
        if(uriComponentsBuilder == null || StringUtils.isEmpty(name) || StringUtils.isEmpty(value) ){
            return;
        }
        uriComponentsBuilder.queryParam(name, value);
    }

}
