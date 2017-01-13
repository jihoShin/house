package com.jiho;

import com.jiho.house.code.*;
import com.jiho.config.ApplicationLocalConfig;
import com.jiho.house.model.Item;
import com.jiho.house.model.Response;
import com.jiho.common.util.DateUtil;
import com.jiho.house.service.EsService;
import com.jiho.common.util.JsonUtil;
import com.jiho.house.service.RestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jiho87.shin on 2017-01-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationLocalConfig.class})
@ActiveProfiles("local")
public class RestTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EsService esUtil;

    @Autowired
    private RestService restUtil;

    public void testApi(){
        Response response = restUtil.requestApi("201601", CreditGradeCode.GRADE_1.code, JobCode.ETC.code, null, null, null, null);
        System.out.println(JsonUtil.getJsonString(response));
    }

    public void testAll() throws UnsupportedEncodingException, InterruptedException {

        int year = 2016;
        for(int i= 0 ; i < 12; i++){
            Date loan_ym_date = DateUtil.getDate(year, i);
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
            String loan_ym = transFormat.format(loan_ym_date);

            for(CreditGradeCode creditGrade :  CreditGradeCode.class.getEnumConstants()){
                for(JobCode jobCode :  JobCode.class.getEnumConstants()) {
                    for(HouseTypeCode houseTypeCode :  HouseTypeCode.class.getEnumConstants()) {
                        for(IncomeCode incomeCode :  IncomeCode.class.getEnumConstants()) {
                            for(AgeCode ageCode :  AgeCode.class.getEnumConstants()) {
                                for(DebtCode debtCode :  DebtCode.class.getEnumConstants()) {

                                    Response response = restUtil.requestApi(loan_ym, creditGrade.code, jobCode.code, houseTypeCode.code, ageCode.code, incomeCode.code, debtCode.code);
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
                                        Thread.sleep(2000);
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
            Date loan_ym_date = DateUtil.getDate(year, i);
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
            String loan_ym = transFormat.format(loan_ym_date);

            for(CreditGradeCode creditGrade :  CreditGradeCode.class.getEnumConstants()){
                for(JobCode jobCode :  JobCode.class.getEnumConstants()) {
                    System.out.println(loan_ym+", "+creditGrade.desc+", "+ jobCode.desc+ ", " + null);
                    Response response = restUtil.requestApi(loan_ym, creditGrade.code, jobCode.code, null, null, null, null);
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






}
