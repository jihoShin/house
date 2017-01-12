package com.jiho.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by jiho87.shin on 2017-01-11.
 */
public class Items {

    private List<Item> item;



    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
