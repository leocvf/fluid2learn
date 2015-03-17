package pt.c02classes.s01knowledge.s02app.app;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;

public class Orchestrator
{
	public static void New(String ani)
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		
		IBaseConhecimento base = new BaseConhecimento();

		base.setScenario("animals");

			System.out.println("Enquirer com " + ani + "...");
			stat = new Statistics();
			resp = new ResponderAnimals(stat, ani);
			enq = new EnquirerAnimals();
			enq.connect(resp);
			enq.discover();
			System.out.println("----------------------------------------------------------------------------------------\n");
				
        		
	}
}
