/*package com.zihexin.business_interface.bank.scheduler;

import com.zihexin.business_interface.common.Loader;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
*/
/**
 * 银行签到定时任务
 * User: Administrator
 *
 
public class SingNoJob  extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(SingNoJob.class);

    //声明银行签到业务处理对象
    private SingNoJobService singNoJobService = (SingNoJobService) Loader.getContext().getBean("singNoJobService");

        protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

            logger.info("开始执行签到任务调度");

            String msg = singNoJobService.run();
             System.out.println(msg);
            logger.info("结束执行签到任务调度");
        }

}
*/