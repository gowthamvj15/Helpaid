package com.example.helpaid;

public class Addlaw {

    String Law_title,Law_desc;

    public Addlaw(String law_title, String law_desc) {
        Law_title = law_title;
        Law_desc = law_desc;
    }
    public Addlaw() {
    }

    public String getLaw_title() {
        return Law_title;
    }

    public void setLaw_title(String law_title) {
        this.Law_title = law_title;
    }

    public String getLaw_desc() {
        return Law_desc;
    }

    public void setLaw_desc(String law_desc) {
        this.Law_desc = law_desc;
    }
}
