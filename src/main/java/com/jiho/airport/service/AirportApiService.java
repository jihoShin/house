package com.jiho.airport.service;

import com.jiho.airport.code.TerminalCode;
import com.jiho.airport.model.DeparturesCongestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by jiho87.shin on 2017-01-17.
 */
@Service
public class AirportApiService {

    private String serviceKey="bS8hhTmLZnZCmzt%2FteDztdYpMuMoo8Nx8vEaS8yevML%2Ftbsims%2F23ui3Mf0PGF0MUsv8vmXfrYxvR0x97QMgSQ%3D%3D";
    private String baseUrl="http://openapi.airport.kr";
    private String path = "/openapi/service/StatusOfDepartures/getDeparturesCongestion";

    public static String SERVICE_KEY="serviceKey";
    public static String TERMINAL_NO="terno";

    @Autowired
    private RestTemplate restTemplate;

    public DeparturesCongestionResponse requestDeparturesCongestion(TerminalCode terminalCode){
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path(path)
                .queryParam(SERVICE_KEY, serviceKey)
                .queryParam(TERMINAL_NO,terminalCode.code);

        URI uri = uriComponentsBuilder.build(true).toUri();
        MultiValueMap<String, String> headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);

        ResponseEntity<DeparturesCongestionResponse> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, DeparturesCongestionResponse.class);
        return result.getBody();
    }


}
