package com.tianmao.api.quartz;

import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.type.quartz.TaskGroup;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * job服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
@FeignClient(name = "${server.app.name}")
public interface JobClient {

    /**
     * 添加任务
     *
     * @param quartzTask
     */
    @RequestMapping(value = "/job/add")
    void addJob(QuartzTask quartzTask);

    /**
     * 添加批量任务
     *
     * @param quartzTasks
     */
    @RequestMapping(value = "/job/adds")
    void addJobs(List<QuartzTask> quartzTasks);


    /**
     * 停止任务
     * @param taskId
     * @param group
     * @return
     */
    @RequestMapping(value = "/job/stop")
    boolean stopJob(@RequestParam("taskId") Long taskId, TaskGroup group);

    /**
     * 停止任务
     * @param quartzTask
     * @return
     */
    @RequestMapping(value = "/job/stops")
    boolean stopJobs(QuartzTask quartzTask);


}

