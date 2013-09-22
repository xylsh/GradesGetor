package com.github.xylsh.action;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.xylsh.util.GradesGetor;
import com.opensymphony.xwork2.ActionSupport;

public class GetGradesAction extends ActionSupport {

    private static final long serialVersionUID = -7786703620164313230L;
    
    public String execute() throws Exception{
        
        GradesGetor gg = new GradesGetor();
        
        this.setGrades( gg.getGrades(gg.getJSESSIONID(account, password)) );

        return SUCCESS;
    }
    
    public void validate(){
        if( this.getAccount().equals("") ){
            this.addFieldError("account", "学号不能为空");
        }
        if( this.getPassword().length() == 0){
            this.addFieldError("password", "密码不能为空");
        }
    }
    
    private String account;         //帐号
    private String password;        //密码
    private ArrayList<HashMap<String,String>> grades;   //成绩

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<HashMap<String, String>> getGrades() {
        return grades;
    }
    public void setGrades(ArrayList<HashMap<String, String>> grades) {
        this.grades = grades;
    }

}
