package com.example.helpaid;

public class Addquery {
    String roll_no,name,query;
    public Addquery(String roll_no, String name, String query) {
        this.roll_no = roll_no;
        this.name = name;
        this.query = query;
    }

    public Addquery() {
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
