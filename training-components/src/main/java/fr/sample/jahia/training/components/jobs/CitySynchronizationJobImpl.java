/*
 * ==========================================================================================
 * =                            JAHIA'S ENTERPRISE DISTRIBUTION                             =
 * ==========================================================================================
 *
 *                                  http://www.jahia.com
 *
 * JAHIA'S ENTERPRISE DISTRIBUTIONS LICENSING - IMPORTANT INFORMATION
 * ==========================================================================================
 *
 *     Copyright (C) 2002-2020 Jahia Solutions Group. All rights reserved.
 *
 *     This file is part of a Jahia's Enterprise Distribution.
 *
 *     Jahia's Enterprise Distributions must be used in accordance with the terms
 *     contained in the Jahia Solutions Group Terms &amp; Conditions as well as
 *     the Jahia Sustainable Enterprise License (JSEL).
 *
 *     For questions regarding licensing, support, production usage...
 *     please contact our team at sales@jahia.com or go to http://www.jahia.com/license.
 *
 * ==========================================================================================
 */
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
