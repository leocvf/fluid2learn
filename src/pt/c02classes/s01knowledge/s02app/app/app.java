package pt.c02classes.s01knowledge.s02app.app;
import java.util.Scanner;

import pt.c02classes.s01knowledge.s02app.app.Orchestrator;
import pt.c02classes.s01knowledge.s02app.app.OrchestratorInteractive;
public class app {
	private static Scanner scanner;

	public static void main(String args[])
	{
		scanner = new Scanner(System.in);
		System.out.print("(A)nimals, (M)aze ou (F)im? ");
		String tipo = scanner.nextLine();
		while (!tipo.equalsIgnoreCase("F")) {
			   System.out.print("  --> ");
			   switch (tipo.toUpperCase()) {
			      case "A": Orchestrator.New();
			                break;
			      case "M": OrchestratorInteractive.New();
			                break;
			   }
				System.out.print("(A)nimals, (M)aze ou (F)im? ");
				tipo = scanner.nextLine();
			}
			
	}

}
