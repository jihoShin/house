package com.jiho.airport.service;

import com.jiho.house.model.Item;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by jiho87.shin on 2017-01-17.
 */
@Service
public class AirportEsService {

    @Autowired
    private TransportClient client;

    public void insert(Date cgtDate, int terno,
                       int cgtlvlg2, int cgtlvlg3, int cgtlvlg4, int cgtlvlg5,
                       int pwcntg2, int pwcntg3, int pwcntg4, int pwcntg5)  {

        XContentBuilder builder = null;
        try {
            builder = jsonBuilder().startObject()
                    .field("timestamp", new Date())
                    .field("cgtDate", cgtDate)
                    .field("terno", terno)

                    .field("cgtlvlg2", cgtlvlg2)
                    .field("cgtlvlg3", cgtlvlg3)
                    .field("cgtlvlg4", cgtlvlg4)
                    .field("cgtlvlg5", cgtlvlg5)

                    .field("pwcntg2", pwcntg2)
                    .field("pwcntg3", pwcntg3)
                    .field("pwcntg4", pwcntg4)
                    .field("pwcntg5", pwcntg5)

                    .endObject();

            client.prepareIndex("airport", "myType").setSource(builder).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
