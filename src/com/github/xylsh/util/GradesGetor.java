package com.github.xylsh.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 从URP教务系统获取本学期成绩的类
 *
 */
public class GradesGetor {

    // 系统登陆url
    private String loginURL = "http://newjw.cduestc.cn/loginAction.do";
    // 获取本学期成绩的url
    private String gradesURL = "http://newjw.cduestc.cn/bxqcjcxAction.do";
    
    /**
     * 获得对应用户Cookie的JSESSIONID，URP系统依靠该Cookie识别用户
     * @param account 用户帐号
     * @param password 帐号密码
     * @return 该帐号登陆后获得的JSESSIONID，eg:JSESSIONID=cbakV9X8sSTwp4RL3CHeu
     */
    public String getJSESSIONID(String account,String password) throws IOException{
        
        URL url = new URL(loginURL);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        
        urlConnection.setDoOutput(true);         // 打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true
        urlConnection.setDoInput(true);          // 打算使用 URL 连接进行输入，则将 DoIutput 标志设置为 true
        urlConnection.setRequestMethod("POST");  // 设置 URL 请求的方法
        urlConnection.setUseCaches(false);
        urlConnection.connect();
        
        DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        String postContent = "zjh=" + account + "&mm=" + password;
        out.writeBytes(postContent);
        out.flush();      // 清空此数据输出流。这迫使所有缓冲的输出字节被写出到流中。
        out.close();
        
        // 获得Cookie:JSESSIONID
        String setCookie = urlConnection.getHeaderField("Set-Cookie");
        String jsessionid = setCookie.substring(0, setCookie.indexOf(";"));
        
        urlConnection.disconnect();
        
        return jsessionid;
    }

    /**
     * 获取本学期成绩
     * @param jsessionid URP保存在本地Cookie的JSESSIONID，eg:JSESSIONID=cbakV9X8sSTwp4RL3CHeu
     * @return 保存着成绩的ArrayList对象，HashMap的KEY有"courseNumber","courseName","courseCredit","courseAttribute","courseGrade"
     * @throws IOException
     */
    public ArrayList<HashMap<String,String>> getGrades(String jsessionid) throws IOException{
        // 保存成绩查询结果
        ArrayList<HashMap<String,String>> grades = new ArrayList<HashMap<String,String>>();
        
        Document doc = Jsoup.connect(gradesURL)   // 获得一个新Connection，用于更新和解析HTML页面
                .header("Cookie",jsessionid)      // 设置 cookie
                .post();                          // 使用 POST 方法访问 URL 
        
        //System.out.println("\n-----------\n"+doc+"\n-----------\n");
        
        Elements trs = doc.getElementsByClass("odd");      // 每一条科目的tr标签都class="odd"
        Elements tds = null;
        Element td = null;
        HashMap<String,String> grade = null;
        
        for(int i=0; i<trs.size(); i++){
            tds = trs.get(i).getElementsByTag("td");
            grade = new HashMap<String,String>();
            for(int j=0; j<tds.size(); j++){
                td = tds.get(j);
                switch (j){
                case 0: grade.put("courseNumber", td.ownText());      // 课程号
                        break;
                case 2: grade.put("courseName", td.ownText());        // 课程名
                        break;     
                case 4: grade.put("courseCredit", td.ownText());      // 该课程学分
                        break;
                case 5: grade.put("courseAttribute", td.ownText());   // 课程属性：选修，必修
                        break;
                case 6: grade.put("courseGrade", td.ownText());       // 课程成绩
                        break;
                }
            }
            grades.add(grade);
        }
        
        /*输出查询结果
        HashMap<String,String> kemu = null;
        for(int i=0;i<grades.size();i++){
            kemu = grades.get(i);
            System.out.println("courseNumber: " + kemu.get("courseNumber"));
            System.out.println("courseName: " + kemu.get("courseName"));
            System.out.println("courseCredit: " + kemu.get("courseCredit"));
            System.out.println("courseAttribute: " + kemu.get("courseAttribute"));
            System.out.println("courseGrade: " + kemu.get("courseGrade"));
            System.out.println("---------------");
        }*/
        
        return grades;
    }
    
    
    /**
     * 获取URP系统登陆的URL，默认为http://newjw.cduestc.cn/loginAction.do
     * @return URP系统登陆的URL
     */
    public String getLoginURL() {
        return loginURL;
    }

    /**
     * 设置URP系统登陆的URL，默认为http://newjw.cduestc.cn/loginAction.do
     * @param loginURL URP系统登陆的URL
     */
    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }

    /**
     * 获取URP系统显示本学期成绩的URL，默认为http://newjw.cduestc.cn/bxqcjcxAction.do
     * @return URP系统显示本学期成绩的URL
     */
    public String getGradesURL() {
        return gradesURL;
    }

    /**
     * 设置URP系统显示本学期成绩的URL，默认为http://newjw.cduestc.cn/bxqcjcxAction.do
     * @param gradesURL URP系统显示本学期成绩的URL
     */
    public void setGradesURL(String gradesURL) {
        this.gradesURL = gradesURL;
    }
    
}