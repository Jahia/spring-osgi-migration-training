package fr.sample.jahia.training.components.jobs;

import org.jahia.services.scheduler.BackgroundJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestJob extends BackgroundJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void executeJahiaJob(JobExecutionContext jobExecutionContext) throws Exception {
        LOGGER.info("Start job");
        // fake doing something
        Thread.sleep(2000);
        LOGGER.info("End job");
    }

    public static JobDetail createTestJob() {
        Map<String, Object> jobData = new HashMap<>();
        jobData.put("foo", "bar");
        JobDetail job = BackgroundJob.createJahiaJob("Test job", TestJob.class);
        job.getJobDataMap().putAll(jobData);
        return job;
    }
}