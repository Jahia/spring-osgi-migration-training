package fr.sample.jahia.training.components.jobs;

import fr.sample.jahia.training.services.HelloService;
import org.jahia.osgi.BundleUtils;
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
import org.quartz.JobExecutionContext;
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
    private static final String CRON_EXPRESSION = "* * * * * ?";

    public CitySynchronizationJob() {
        setSchedulerService(ServicesRegistry.getInstance().getSchedulerService());
        setSettingsBean(SettingsBean.getInstance());
    }

    @Deactivate
    public void onDeactivate() throws Exception {
        super.destroy();
    }

    @Activate
    public void onActivate() throws Exception {
        setRamJob(false);
        try {
            setTrigger(new CronTrigger(NAME, null, CRON_EXPRESSION));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        JobDetail jobDetail = BackgroundJob.createJahiaJob(NAME, JobImpl.class);
        jobDetail.setDescription(DESCRIPTION);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("startTime", LocalDateTime.now(Clock.systemUTC()));
        jobDetail.setJobDataMap(jobDataMap);
        setJobDetail(jobDetail);

        super.afterPropertiesSet();
    }

    public static class JobImpl extends BackgroundJob {
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
}
