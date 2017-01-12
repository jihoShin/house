package com.jiho.util;

import com.jiho.code.*;
import com.jiho.model.Item;
import com.jiho.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jiho87.shin on 2017-01-12.
 */
@Component
public class HouseApiUtil {

    @Autowired
    private RestUtil restUtil;


    public void test(String loan_ym, List<AgeCode> startAgeCode, List<CreditGradeCode> startCreditGrade, List<DebtCode> startDebtCode
            , List<HouseTypeCode> startHouseTypeCode, List<IncomeCode> startIncomeCode, List<JobCode> startJobCode) {

        for(CreditGradeCode creditGrade :  CreditGradeCode.class.getEnumConstants()){
            for(JobCode jobCode :  JobCode.class.getEnumConstants()) {
                for(HouseTypeCode houseTypeCode :  HouseTypeCode.class.getEnumConstants()) {
                    for(IncomeCode incomeCode :  IncomeCode.class.getEnumConstants()) {
                        for(AgeCode ageCode :  AgeCode.class.getEnumConstants()) {
                            for(DebtCode debtCode :  DebtCode.class.getEnumConstants()) {

                                System.out.println(loan_ym+", "+creditGrade.desc+", "+ jobCode.desc+ ", " + houseTypeCode.desc +", "+ageCode.desc+", "+incomeCode.desc+", "+debtCode.desc);
                                Response response = restUtil.requestApi(loan_ym, creditGrade.code, jobCode.code, houseTypeCode.code, ageCode.code, incomeCode.code, debtCode.code);

                                try {
                                    if(response.getBody() == null || response.getBody().getItems() == null || response.getBody().getItems().getItem() == null
                                            || ! response.getHeaeder().getResultCode().equals("00")
                                            ){
                                        continue;
                                    }
                                    List<Item> list = response.getBody().getItems().getItem();
                                    System.out.println(JsonUtil.getJsonString(response));
                                    //esUtil.insert(list, loan_ym_date, creditGrade.code, jobCode.code, houseTypeCode.code, ageCode.code, incomeCode.code, debtCode.code);
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
