package org.vafer.minijar.ant;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;

import fr.jayasoft.ivy.Artifact;
import fr.jayasoft.ivy.Ivy;
import fr.jayasoft.ivy.IvyNode;
import fr.jayasoft.ivy.ModuleRevisionId;
import fr.jayasoft.ivy.ant.IvyBuildList;
import fr.jayasoft.ivy.filter.FilterHelper;
import fr.jayasoft.ivy.report.ArtifactDownloadReport;
import fr.jayasoft.ivy.report.ResolveReport;

public final class MiniJarAntTask extends IvyBuildList {

	private String organization;
	private String module;
	private String revision;
	private String pubdate;
	private String type;
	private boolean transitive;
	private boolean changing;
	private boolean useCacheOnly;
	private boolean useOrigin;
	
	private File[] getDependencies2() throws ParseException, IOException {
		final Project antProject = getProject();
		final Ivy ivy = (Ivy) antProject.getReference("ivy.instance");

		final String[] confs = new String[0]; 
		
		{
			final String config = "default";
	        final ResolveReport report = ivy.resolve(
	        		new File("ivy.xml").toURL(),
	        		null,
	        		new String[] { config },
	        		ivy.getDefaultCache(),
	        		null,
	        		true);

	        final ArtifactDownloadReport[] reports = report.getConfigurationReport(config).getDownloadReports(null);
	        final Artifact artifact = reports[0].getArtifact();
	        final File artifactFile = ivy.getArchiveFileInCache(ivy.getDefaultCache(), artifact);
	        
	        final List nodes = report.getDependencies();
			for (Iterator it = nodes.iterator(); it.hasNext();) {
				final IvyNode node = (IvyNode) it.next();
				
			}

		}
		
		{
			final ResolveReport report = ivy.resolve(
	        		ModuleRevisionId.newInstance(organization, module, revision),
	                confs, 
	                transitive,
	                changing,
	                ivy.getDefaultCache(), 
	                getPubDate(pubdate, null), 
	                doValidate(ivy),
	                useCacheOnly,
	                useOrigin,
	                FilterHelper.getArtifactTypeFilter(type));

			final List nodes = report.getDependencies();

			for (Iterator it = nodes.iterator(); it.hasNext();) {
				final IvyNode node = (IvyNode) it.next();
				
			}
		}
		

		{
			final IvyNode[] nodes = ivy.getDependencies(
					null, // module descriptor
					confs,
					ivy.getDefaultCache(),
					getPubDate(pubdate, null),
					null, // report,
					true, // validate				
					transitive);
			for (int i = 0; i < nodes.length; i++) {
				final IvyNode ivyNode = nodes[i];
				
			}
		}

		return null;
	}
	
	public void execute() throws BuildException {
	}

}
