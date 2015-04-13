package craw;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jsoncrawer {


    public static List<String> getContext(String html) {

        List<String> resultList = new ArrayList<String>();
        Pattern p = Pattern.compile("<td>([^</td]*)");//匹配<td>开头，</td>结尾的文档

        Matcher m = p.matcher(html );

        while (m.find()) {
            if(m.group(1).matches("(\\d{0,4})|(((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))))"))
                resultList.add(m.group(1));//获取被匹配的部分
        }

        //处理ResultList为list数组 ，去掉返回值
        List <String>list = new ArrayList<String>();
        List<String>strlist=new ArrayList<String>();
        for(String strings:resultList){
            strlist.add(strings);
        }
        for(String strs:strlist){
            if(!strs.equals("")){
                list.add(strs);
            }
        }
        return list;
    }
}