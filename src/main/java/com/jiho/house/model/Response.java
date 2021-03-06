package com.jiho.house.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
@XmlRootElement(name="response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

    private Header header;

    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
