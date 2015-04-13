package craw;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.TriggerBuilder.newTrigger;
public  class  main{
    public static  void main(String[] args) {

        try {

            // 首先，必需要取得一个Scheduler的引用
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

            // job1每隔10分钟执行一次
            JobDetail job = newJob(QuartzJob.class).withIdentity("job1", "group2").build();
            CronTrigger trigger = newTrigger().withIdentity("trigger1", "group2").withSchedule(cronSchedule("15 0/10 * * * ?")).build();

            Date ft = sched.scheduleJob(job, trigger);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression());

            sched.start();

            try{}
            catch (Exception e){
                e.printStackTrace();
                sched.shutdown(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}