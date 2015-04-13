package craw;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ad10830 on 2015/4/10.
 * 用httpclient连接
 */
public class ThreadHttpClient implements Runnable {
    proxy p=new proxy();
    String text="";
    public ThreadHttpClient(proxy p){
        this.p=p;
    }
    public  void run(){
        //用httpclient来设置代理  连接网页
        System.setProperty("http.proxySet","true");
        System.setProperty("http.proxyHost",p.getIp());
        System.setProperty("http.proxyPort",p.getPort());

        try {

            HttpClient httpClient = new HttpClient();
            HttpMethod method =
                    new GetMethod("http://httpproxy.17usoft.com/tcproxy/test/ip_a.jsp");
            httpClient.executeMethod(method);
            String html = method.getResponseBodyAsString();
            method.releaseConnection();
            Pattern p=Pattern.compile("(\\d{0,4})|(((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))))");
            Matcher m = p.matcher(html);
            while (m.find()) {
                if(m.group(1).matches(""))
                    text =  m.group(1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        if(!text.equals("222.92.145.62")){
            p.setTag(1);

        }
    }
}