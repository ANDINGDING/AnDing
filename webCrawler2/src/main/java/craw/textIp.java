package craw;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ad10830 on 2015/4/10.
 */
public class textIp {
    int j=0;
    WebpageCapture demo = new WebpageCapture();
    List<proxy> list2 = new ArrayList<proxy>();
    ExecutorService threadpool = Executors.newFixedThreadPool(10);

    public void craw(){
        try{
            String html =  demo.captureJavascript("");
            List<String> list =Jsoncrawer.getContext(html);
        //  将 待处理的ip/port 包装成一个proxy 对象
         for(int i=0;i<list.size()-1;i++)
            {
                if(list.get(i).length()>6&&list.get(i+1).length()<6){
                proxy p = new proxy();
                p.setIp(list.get(i));
                p.setPort(list.get(i + 1));
                p.setTag(0);
                list2.add(p);
                }
            }
           //  多线程验证代理可用性
            for (int i=0;i<list2.size();i++){
                threadpool.submit(new ThreadHttpClient(list2.get(i)));
            }

            //主线程等待 子线程执行完毕
            //等待时间随线程数、任务量变化
            while (threadpool.awaitTermination(5, TimeUnit.SECONDS));

            threadpool.shutdown();

        }catch (Exception e){
                System.out.println("异常1");
            e.printStackTrace();
        }
        for(int i=0;i<list2.size();i++){
            //等待时间随线程数、任务量变化
            if(list2.get(i).getTag()==1){
                System.out.println(list2.get(i).getIp());
                System.out.println(list2.get(i).getPort());

                save(list2.get(i).getIp(), list2.get(i).getPort());
            }
        }
    }
    public void save(String ip,String port){
        System.out.println(ip+":"+port);
        //组织一条含有参数的sql语句
        String sql = "insert into AvailableProxy1 values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs  = null;
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //实例化 sql server驱动程序(建立中间件)
            //连接数据库
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://172.16.58.2:1433;DatabaseName=TCBaseCrawlTest";

            conn = DriverManager.getConnection(url, "sa", "sa3210.");
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,j++);
            stmt.setString(2,ip);
            stmt.setString(3,port);
            stmt.setString(4,df.format(new Date()));
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("异常2");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("异常3");
            e.printStackTrace();
        }
        try {
            if(rs!=null)
            rs.close();
            if (stmt!=null)
            stmt.close();
            if (conn!=null)
            conn.close();
        }catch (SQLException e){
            System.out.println("异常4");
           e.printStackTrace();
        }
    }
}
