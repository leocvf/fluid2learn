package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;

public class OrquestratorInit {
	private static Scanner scanner;

	public static void main(String args[])
	{
		scanner = new Scanner(System.in);
		System.out.print("(A)nimals, (M)aze ou (F)im? ");
		String tipo = scanner.nextLine();
		while (!tipo.equalsIgnoreCase("F")) {
			   System.out.print("  --> ");
			   switch (tipo.toUpperCase()) {
			      case "A": System.out.print("Qual animal?");
			      			String animal = scanner.nextLine();
			    	  		Orchestrator.New(animal);
			                break;
			      case "M": System.out.print("Qual labirinto?");
	      					String labirinto = scanner.nextLine();
			    	  		OrchestratorInteractive.New(labirinto);
			                break;
			   }
				System.out.print("(A)nimals, (M)aze ou (F)im? ");
				tipo = scanner.nextLine();
			}
			
	}

}
