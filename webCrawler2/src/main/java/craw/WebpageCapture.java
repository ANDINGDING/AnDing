package craw;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ad10830 on 2015/3/31.
 */
public class WebpageCapture {

    public String captureJavascript(String postid) throws Exception {
        String strURL = "http://www.xici.net.co/" + postid;
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();//建立到目的地的连接
        InputStreamReader input = new InputStreamReader(httpConn
                .getInputStream(), "utf-8");
        BufferedReader bufReader = new BufferedReader(input);
        String line = "";
        StringBuilder contentBuf = new StringBuilder();

        while ((line = bufReader.readLine()) != null) {
            contentBuf.append(line+"");
        }
        System.out.println("开始执行\n");
        Document doc = Jsoup.parse(contentBuf.toString());
        String html = contentBuf.toString();

        bufReader.close();
        input.close();
        return html;
         }

    }



