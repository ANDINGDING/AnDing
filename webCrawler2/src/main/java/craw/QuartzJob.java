package craw;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by ad10830 on 2015/4/13.
 */
public class QuartzJob implements Job {
    public void execute(JobExecutionContext jobCtx)throws JobExecutionException {
        textIp ip = new textIp();
        ip.craw();
    }

}
