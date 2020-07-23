package com.example.helpaid;

public class Adduser {

    private  String Roll_no,Name,Pass,Email,Dept;

    public Adduser(String roll_no, String name, String pass, String email, String dept) {
        Roll_no = roll_no;
        Name = name;
        Pass = pass;
        Email = email;
        Dept = dept;
    }

    public String getRoll_no() {
        return Roll_no;
    }

    public void setRoll_no(String roll_no) {
        Roll_no = roll_no;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }
}
