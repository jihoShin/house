package com.jiho;

import com.jiho.airport.code.TerminalCode;
import com.jiho.airport.model.DeparturesCongestionResponse;
import com.jiho.airport.service.AirportApiService;
import com.jiho.config.ApplicationConfig;
import com.jiho.airport.model.Item;
import com.jiho.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jiho87.shin on 2017-01-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationConfig.class})
@ActiveProfiles("local")
public class AirportRestTest {

    @Autowired
    private AirportApiService airportApiService;

    @Test
    public void test(){
        System.out.printf("jiho test");
        DeparturesCongestionResponse response = airportApiService.requestDeparturesCongestion(TerminalCode.TERMINAL_1);
        try {
            if (response.getBody() == null
                    || response.getBody().getItems() == null
                    || response.getBody().getItems().getItem() == null
                    || !response.getHeader().getResultCode().equals("00")) {
            }
            List<Item> list = response.getBody().getItems().getItem();
            System.out.println(JsonUtil.getJsonString(response));
        }catch (Exception e){
            e.printStackTrace();
        }


    }



}
