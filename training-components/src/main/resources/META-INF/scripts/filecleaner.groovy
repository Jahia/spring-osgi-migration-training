import org.jahia.api.Constants
import org.jahia.services.content.JCRTemplate

import javax.jcr.query.Query

log.info "Start script"
def rootPath = '/sites/digitall/files'
JCRTemplate.getInstance().doExecuteWithSystemSessionAsUser(null, Constants.EDIT_WORKSPACE, Locale.FRENCH, { session ->
    Set<String> usages = new TreeSet<>()
    def it = session.getWorkspace().getQueryManager().createQuery("SELECT * FROM [jnt:file] WHERE ISDESCENDANTNODE('${rootPath}')", Query.JCR_SQL2).execute().getNodes()
    while (it.hasNext()) {
        def node = it.nextNode()
        if (node.getWeakReferences().size() == 0 && node.getReferences().size() == 0) {
            usages.add(node.getPath())
        }
    }
    usages.each { path -> log.info path }
})
log.info "End script"
