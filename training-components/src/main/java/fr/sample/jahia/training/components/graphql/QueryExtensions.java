package fr.sample.jahia.training.components.graphql;

import fr.sample.jahia.training.components.jobs.TestJob;
import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.annotations.annotationTypes.GraphQLTypeExtension;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang.StringUtils;
import org.jahia.modules.graphql.provider.dxm.DXGraphQLProvider;
import org.jahia.modules.graphql.provider.dxm.DataFetchingException;
import org.jahia.modules.graphql.provider.dxm.node.GqlJcrNode;
import org.jahia.modules.graphql.provider.dxm.node.GqlJcrNodeImpl;
import org.jahia.modules.graphql.provider.dxm.node.NodeQueryExtensions;
import org.jahia.modules.graphql.provider.dxm.relay.DXPaginatedData;
import org.jahia.modules.graphql.provider.dxm.relay.PaginationHelper;
import org.jahia.registries.ServicesRegistry;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.utils.LanguageCodeConverters;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;

import javax.jcr.RepositoryException;
import javax.jcr.query.Query;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@GraphQLTypeExtension(DXGraphQLProvider.Query.class)
public class QueryExtensions {
    public static final String QUERY_COMPANIES = "SELECT * FROM [jdnt:company] as company WHERE ISDESCENDANTNODE(company, '%s')";

    private QueryExtensions() {
    }

    @GraphQLField
    public static DXPaginatedData<GqlJcrNode> companies(
            @GraphQLName("workspace") NodeQueryExtensions.Workspace workspace,
            @GraphQLName("language") @GraphQLDescription("Language to access node properties in") String language,
            @GraphQLName("descendantPath") String descendantPath,
            DataFetchingEnvironment environment) {
        try {
            if (StringUtils.isBlank(descendantPath)) {
                throw new DataFetchingException(new NullPointerException());
            }
            return PaginationHelper.paginate(StreamSupport.stream(Spliterators.spliteratorUnknownSize(JCRSessionFactory.getInstance().getCurrentUserSession(workspace.getValue(), LanguageCodeConverters.languageCodeToLocale(language)).getWorkspace().getQueryManager().createQuery(String.format(QUERY_COMPANIES, descendantPath), Query.JCR_SQL2).execute().getNodes().iterator(), Spliterator.ORDERED), false).map(GqlJcrNodeImpl::new), n -> PaginationHelper.encodeCursor(n.getUuid()), PaginationHelper.parseArguments(environment));
        } catch (RepositoryException e) {
            throw new DataFetchingException(e);
        }
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
