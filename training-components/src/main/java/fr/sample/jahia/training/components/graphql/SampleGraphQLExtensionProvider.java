package fr.sample.jahia.training.components.graphql;

import org.jahia.modules.graphql.provider.dxm.DXGraphQLExtensionsProvider;
import org.osgi.service.component.annotations.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * GraphQL extensions
 *
 * @author tleclere
 */
@Component(service = DXGraphQLExtensionsProvider.class, immediate = true)
public class SampleGraphQLExtensionProvider implements DXGraphQLExtensionsProvider {
    /**
     * This method will build the list of extension points to add to the DX GraphQL provider
     *
     * @return list of classes to be used as extension points for the DX GraphQL provider
     */
    @Override
    public Collection<Class<?>> getExtensions() {
        return Arrays.asList(JCRNodeExtensions.class, QueryExtensions.class, NodeUgcMutationExtensions.class);
    }
}
