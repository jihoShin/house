package com.jiho;

import com.jiho.code.*;
import com.jiho.config.ApplicationConfig;
import com.jiho.model.Item;
import com.jiho.model.Response;
import com.jiho.util.EsUtil;
import com.jiho.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by jiho87.shin on 2017-01-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class})
@ActiveProfiles("local")
public class RestTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EsUtil esUtil;


    public void testApi(){

        Response response = requestApi("201601", CreditGradeCode.GRADE_1.code, JobCode.ETC.code, null, null, null, null);
        System.out.println(JsonUtil.getJsonString(response));

    }


    public void testAll() throws UnsupportedEncodingException, InterruptedException {

        int year = 2016;
        for(int i= 0 ; i < 12; i++){
            Date loan_ym_date = getDate(year, i);
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
            String loan_ym = transFormat.format(loan_ym_date);

            for(CreditGradeCode creditGrade :  CreditGradeCode.class.getEnumConstants()){
                for(JobCode jobCode :  JobCode.class.getEnumConstants()) {
                    for(HouseTypeCode houseTypeCode :  HouseTypeCode.class.getEnumConstants()) {
                        for(IncomeCode incomeCode :  IncomeCode.class.getEnumConstants()) {
                            for(AgeCode ageCode :  AgeCode.class.getEnumConstants()) {
                                for(DebtCode debtCode :  DebtCode.class.getEnumConstants()) {

                                    Response response = requestApi(loan_ym, creditGrade.code, jobCode.code, houseTypeCode.code, ageCode.code, incomeCode.code, debtCode.code);
                                    System.out.println(loan_ym+", "+creditGrade.desc+", "+ jobCode.desc+ ", " + houseTypeCode.desc +","+ageCode.desc+", "+incomeCode.desc+", "+debtCode.desc);

                                    try {
                                        if(response.getBody() == null || response.getBody().getItems() == null || response.getBody().getItems().getItem() == null
                                                || ! response.getHeaeder().getResultCode().equals("00")
                                                ){
                                            continue;
                                        }
                                        List<Item> list = response.getBody().getItems().getItem();
                                        System.out.println(JsonUtil.getJsonString(response));
                                        esUtil.insert(list, loan_ym_date, creditGrade.code, jobCode.code, houseTypeCode.code, ageCode.code, incomeCode.code, debtCode.code);
                                        Thread.sleep(500);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            };
        }
    }

    @Test
    public void test() throws UnsupportedEncodingException, InterruptedException {

        int year = 2016;
        for(int i= 1 ; i < 12; i++){
            Date loan_ym_date = getDate(year, i);
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
            String loan_ym = transFormat.format(loan_ym_date);

            for(CreditGradeCode creditGrade :  CreditGradeCode.class.getEnumConstants()){
                for(JobCode jobCode :  JobCode.class.getEnumConstants()) {
                            System.out.println(loan_ym+", "+creditGrade.desc+", "+ jobCode.desc+ ", " + null);
                            Response response = requestApi(loan_ym, creditGrade.code, jobCode.code, null, null, null, null);
                            try {
                                if(response.getBody() == null || response.getBody().getItems() == null || response.getBody().getItems().getItem() == null
                                        || ! response.getHeaeder().getResultCode().equals("00")
                                        ){
                                    continue;
                                }
                                List<Item> list = response.getBody().getItems().getItem();
                                System.out.println(JsonUtil.getJsonString(response));
                                esUtil.insert(list, loan_ym_date, creditGrade.code, jobCode.code, null, null, null, null);
                                Thread.sleep(500);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                    }
                }
            };
    }


    private Response requestApi(String loan_ym, String cb_grd, String job_cd, String house_tycd, String age, String income, String debt){

        String serviceKey="yS1djcMZ9BW0TowDL1iBgdKEWNP%2FfNItjmvxiHD5qy7HdJPvNlc8Kkbchj6DJ7wrxbNenLdmJdto7g8cWaFbfw%3D%3D";
        String baseUrl="http://apidev.hf.go.kr:8090";
        String path = "/service/rest/rentloanratmultidim/getRentLoanRatMultiDim";

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

    private Date getDate(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 15);
        return cal.getTime();
    }




}
