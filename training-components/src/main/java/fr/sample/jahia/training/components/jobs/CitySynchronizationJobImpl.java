package fr.sample.jahia.training.components.jobs;

import fr.sample.jahia.training.services.HelloService;
import org.jahia.osgi.BundleUtils;
import org.jahia.services.scheduler.BackgroundJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * City synchronization job implementation
 *
 * @author tleclere
 */
public class CitySynchronizationJobImpl extends BackgroundJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(CitySynchronizationJobImpl.class);

    @Override
    public void executeJahiaJob(JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        LOGGER.info("{}", jobDataMap);
        LOGGER.info("{}", jobDataMap.get("startTime"));
        HelloService helloService = BundleUtils.getOsgiService(HelloService.class, null);
        if (helloService != null) {
            helloService.sayHelloWorld();
        }
    }
}
