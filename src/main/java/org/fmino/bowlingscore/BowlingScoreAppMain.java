package org.fmino.bowlingscore;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.fmino.bowlingscore.api.BowlingScoreProcessor;

/**
 * Primary application implementation
 * @author Fernando
 *
 */
public class BowlingScoreAppMain {
	
	/**
	 * Main App start method
	 * @param args
	 */
	public static void main(String[] args){
		
		CdiContainer cdiContainer = CdiContainerLoader.getCdiContainer();
		cdiContainer.boot();
		
		ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
        contextControl.startContext(RequestScoped.class);
        contextControl.startContext(ConversationScoped.class);
		
        BowlingScoreProcessor processor = BeanProvider.getContextualReference(BowlingScoreProcessor.class);
        processor.processScore(args[0]);
        
		cdiContainer.shutdown();
		System.out.println("CDI closed");
	}
	
	
	
}
