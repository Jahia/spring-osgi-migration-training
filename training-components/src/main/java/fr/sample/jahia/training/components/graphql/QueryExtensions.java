package fr.sample.jahia.training.components.graphql;

import fr.sample.jahia.training.components.jobs.TestJob;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.annotations.annotationTypes.GraphQLTypeExtension;
import org.jahia.modules.graphql.provider.dxm.DXGraphQLProvider;
import org.jahia.modules.graphql.provider.dxm.DataFetchingException;
import org.jahia.registries.ServicesRegistry;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;

@GraphQLTypeExtension(DXGraphQLProvider.Query.class)
public class QueryExtensions {
    private QueryExtensions() {
    }

    @GraphQLField
    public static String testExtension(@GraphQLName("arg") String arg) {
        return "test " + arg;
    }

    @GraphQLField
    public static String startJob(@GraphQLName("productSku") String productSku) {
        JobDetail job = TestJob.createTestJob();
        try {
            ServicesRegistry.getInstance().getSchedulerService().scheduleJobNow(job);
        } catch (SchedulerException e) {
            throw new DataFetchingException(e);
        }
        return job.getKey().toString();
    }
}
