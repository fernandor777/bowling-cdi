package org.fmino.bowlingscore;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
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
	
	private static CdiContainer cdiContainer;
	
	/**
	 * Main App start method
	 * @param args
	 */
	public static void main(String[] args){
		startCDI();
		
		/**
		 * Producer method for contextual reference
		 */
        BowlingScoreProcessor processor = BeanProvider.getContextualReference(BowlingScoreProcessor.class);
        processor.processScore(args[0]);
        
        stopCDI();
	}
	
	private static void startCDI(){
		cdiContainer = CdiContainerLoader.getCdiContainer();
		cdiContainer.boot();
		
		ContextControl contextControl = cdiContainer.getContextControl();
        contextControl.startContext(ApplicationScoped.class);
        contextControl.startContext(RequestScoped.class);
        contextControl.startContext(ConversationScoped.class);
	}
	
	private static void stopCDI(){
		cdiContainer.shutdown();
	}
}
