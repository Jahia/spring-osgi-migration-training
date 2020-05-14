package fr.sample.jahia.training.components.graphql;

import graphql.annotations.annotationTypes.GraphQLDescription;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.annotations.annotationTypes.GraphQLTypeExtension;
import org.jahia.modules.graphql.provider.dxm.DXGraphQLProvider;
import org.jahia.modules.graphql.provider.dxm.node.NodeQueryExtensions;
import org.jahia.utils.LanguageCodeConverters;

import javax.jcr.RepositoryException;

/**
 * GraphQL mutation extensions for User Generated Content
 *
 * @author tleclere
 */
@GraphQLTypeExtension(DXGraphQLProvider.Mutation.class)
@GraphQLDescription("A mutation extension that adds a possibility to user generated content")
public class NodeUgcMutationExtensions {
    private NodeUgcMutationExtensions() {
    }

    /**
     * Root for all JCR mutations.
     *
     * @param workspace the name of the workspace to fetch the node from; either 'edit', 'live', or null to use 'edit' by default
     * @return GraphQL root object for JCR related mutations
     * @throws RepositoryException in case of JCR related errors
     */
    @GraphQLField
    @GraphQLName("ugc")
    @GraphQLDescription("UGC Mutation")
    public static GqlUgcMutation getUgc(
            @GraphQLName("workspace") @GraphQLDescription("The name of the workspace to fetch the node from; either 'edit', 'live', or null to use 'edit' by default") NodeQueryExtensions.Workspace workspace,
            @GraphQLName("language") @GraphQLDescription("The language to set the locale in JCR Session") String language
    ) {
        return new GqlUgcMutation(workspace != null ? workspace.getValue() : null, LanguageCodeConverters.languageCodeToLocale(language));
    }
}
