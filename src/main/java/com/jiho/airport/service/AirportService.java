package com.jiho.airport.service;

import com.jiho.Application;
import com.jiho.airport.code.TerminalCode;
import com.jiho.airport.model.DeparturesCongestionResponse;
import com.jiho.airport.model.Item;
import com.jiho.util.DateUtil;
import com.jiho.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * Created by jiho87.shin on 2017-01-17.
 */
@Service
public class AirportService {

    private final static Logger logger = LoggerFactory.getLogger(AirportService.class);

    @Autowired
    private AirportApiService airportApiService;

    @Autowired
    private AirportEsService airportEsService;

    @Scheduled(cron = "10 */5 * * * *")
    public void execute(){


        logger.info("execute : "+new Date().toString());

        try {
            DeparturesCongestionResponse response = airportApiService.requestDeparturesCongestion(TerminalCode.TERMINAL_1);
            logger.info(JsonUtil.getJsonString(response));
            if(response.getHeader().getResultCode().equals("00")){

                List<Item> itemList = response.getBody().getItems().getItem();
                for(Item item : itemList){
                    Date cgtDate = getcgtDate(item.getCgtdt(), item.getCgthm());
                    airportEsService.insert(cgtDate, item.getTerno(),
                            item.getCgtlvlg2(), item.getCgtlvlg3(), item.getCgtlvlg4(), item.getCgtlvlg5()
                            ,item.getPwcntg2() ,item.getPwcntg3() ,item.getPwcntg4() ,item.getPwcntg5());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Date getcgtDate(String cgtdt, String cgthm) throws ParseException {
        return DateUtil.getDate(cgtdt+cgthm, "yyyyMMddHHmm");
    }

}
