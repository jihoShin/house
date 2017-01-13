package com.jiho.house.service;

import com.jiho.house.model.Item;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
@Component
public class HouseEsService {

    @Autowired
    private TransportClient client;

    public void insert(List<Item> list, Date loan_ym, String cb_grd, String job_cd, String house_tycd, String age, String income, String debt){

        if(list == null || list.size() == 0){
            return;
        }

        for(Item item : list){
            try {
                XContentBuilder builder = jsonBuilder().startObject()
                        .field("timestamp", new Date())
                        .field("대출실행연월", loan_ym)
                        .field("CB등급", cb_grd)
                        .field("직업코드", job_cd)
                        .field("주택유형", house_tycd)
                        .field("연령대", age)
                        .field("연소득", income)
                        .field("부채", debt)

                        .field("은행명", item.getBnkNm())
                        .field("대출건수", item.getCnt())
                        .field("대출실행금액", item.getLoanAmt())
                        .field("산술평균대출금리", item.getAvgLoanRat())
                        .field("가중평균대출금리", item.getAvgLoanRat_2())
                        .field("최대대출금리", item.getMaxLoanRat())
                        .field("최소대출금리", item.getMinLoanRat())
                        .endObject();
                client.prepareIndex("jiho", "myType").setSource(builder).execute();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
