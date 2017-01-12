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


    @Autowired
    private RestTemplate restTemplate;


    public Response requestApi(String loan_ym, String cb_grd, String job_cd, String house_tycd, String age, String income, String debt){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path(path)
                .queryParam("serviceKey", serviceKey)
                .queryParam("loan_ym",loan_ym);
        addParam(uriComponentsBuilder, "cb_grd", cb_grd);
        addParam(uriComponentsBuilder, "job_cd", job_cd);
        addParam(uriComponentsBuilder, "house_tycd", house_tycd);
        addParam(uriComponentsBuilder, "age", age);
        addParam(uriComponentsBuilder, "income", income);
        addParam(uriComponentsBuilder, "debt", debt);

        URI uri = uriComponentsBuilder.build(true).toUri();
        MultiValueMap<String, String> headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);

        ResponseEntity<Response> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Response.class);
        return result.getBody();
    }


    private void addParam(UriComponentsBuilder uriComponentsBuilder, String name, String value){
        if(uriComponentsBuilder == null || StringUtils.isEmpty(name) || StringUtils.isEmpty(value) ){
            return;
        }
        uriComponentsBuilder.queryParam(name, value);
    }

}
