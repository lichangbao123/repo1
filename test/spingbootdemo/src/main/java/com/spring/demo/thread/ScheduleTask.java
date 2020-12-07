package com.spring.demo.thread;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.demo.utils.SpringUtils;

@Component
@Configuration       //主要用于标记配置类，兼备Component的效果
@EnableScheduling    //开启定时任务
public class ScheduleTask {
	
	 //.添加定时任务
    @Scheduled(cron = "0 1 0 ? 1-12 1-5 ")  //0 1 0 ? 1-12 1-5  每周1-5的00.01执行
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {

        getScheduleThread("001");
        getScheduleThread("002");
//        getScheduleThread("003");
        getScheduleThread("004");
        getScheduleThread("005");
        
        ScheduleThread st = new ScheduleThread(); 
//		判断是否有正在运行的线程，没有的话开始新的线程
        if(!st.isAlive()) {
        	st.start();
        }
    }
    
    @Scheduled(cron = "0 1 0 ? 1-12 6 ")
    private void configureTasksByNaction() {
    	
    	getScheduleThread("006");
    	
    	ScheduleThread st = new ScheduleThread();
//		判断是否有正在运行的线程，没有的话开始新的线程
        if(!st.isAlive()) {
        	st.start();
        }
    }
    
    public void getScheduleThread(String type) {
    	
//    	ApscTaskScheduleService apscScheduleService = SpringUtils.getBean(ApscTaskScheduleService.class);
//    	
//    	String task_id = apscScheduleService.generateId(type);
//		ApscTaskSchedule apscTaskSchedule = new ApscTaskSchedule(type,task_id);
//		String state = "1";
//		
//		apscTaskSchedule.setState(state);
//		apscScheduleService.insert(apscTaskSchedule);
    }

}
