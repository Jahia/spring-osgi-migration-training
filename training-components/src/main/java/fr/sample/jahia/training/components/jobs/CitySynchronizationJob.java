package fr.sample.jahia.training.components.jobs;

import org.jahia.registries.ServicesRegistry;
import org.jahia.services.scheduler.BackgroundJob;
import org.jahia.services.scheduler.JobSchedulingBean;
import org.jahia.settings.SettingsBean;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDateTime;

/**
 * Synchronize cities
 *
 * @author tleclere
 */
@Component(service = JobSchedulingBean.class, immediate = true)
public class CitySynchronizationJob extends JobSchedulingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(CitySynchronizationJob.class);

    private static final String NAME = "CitySynchronizationJob";
    private static final String DESCRIPTION = "Synchronize cities";
    // every minutes
    private static final String CRON_EXPRESSION = "0 0 * ? * * *";

    @Activate
    public void onActivate() throws Exception {
        setSchedulerService(ServicesRegistry.getInstance().getSchedulerService());
        setSettingsBean(SettingsBean.getInstance());
        setRamJob(false);
        try {
            setTrigger(new CronTrigger(NAME, null, CRON_EXPRESSION));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        JobDetail jobDetail = BackgroundJob.createJahiaJob(DESCRIPTION, CitySynchronizationJobImpl.class);
        jobDetail.setName(NAME);
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("startTime", LocalDateTime.now(Clock.systemUTC()));
        jobDetail.setJobDataMap(jobDataMap);
        setJobDetail(jobDetail);

        super.afterPropertiesSet();
    }

    @Deactivate
    public void onDeactivate() throws Exception {
        super.destroy();
    }
}
