package com.ssga.ssgai.contact.entity;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String id;
    private String name;
    private String priorId;
    private List<Contact> nextIds = new ArrayList<Contact>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriorId() {
        return priorId;
    }

    public void setPriorId(String priorId) {
        this.priorId = priorId;
    }

    public Contact() {
        super();
    }

    public List<Contact> getNextIds() {
        return nextIds;
    }

    public void setNextIds(List<Contact> nextIds) {
        this.nextIds = nextIds;
    }

    public Contact(String id, String name, String priorId) {
        this.id = id;
        this.name = name;
        this.priorId = priorId;
    }
}
