package pt.c02classes.s01knowledge.s02app.actors;

import java.util.ArrayList;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;


/* Define a coordenada partindo que a entrada do labirinto È o (0,0) */
class Coordenada {
	int longitude = 0;
	int latitude = 0;
	
	void move(String direcao) {
		
		if(direcao.equals("leste")) {
			longitude++;
			return;	
		}
		
		if(direcao.equals("oeste")) {
			longitude--;
			return;
		}
		
		if(direcao.equals("norte")) {
			latitude++;
			return;
		}
		
		if(direcao.equals("sul")) {
			latitude--;
			return;
		}		
	}	
	
	boolean testa(ArrayList<String> lista) {
		
		boolean passou = false;
		
		Coordenada teste = new Coordenada();
		
		if(this.latitude == 0 && this.longitude == 0)
			passou = true;
		
		for(int i = 0; !passou && i < lista.size(); i++) {
			
			teste.move(lista.get(i));
			
			/* Testa pra ver se ja passou */
			if(teste.latitude == this.latitude && teste.longitude == this.longitude)
				passou = true;
			
		}
		
		return passou;
	}	
}


public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {	
		
		ArrayList<String> sequencia = new ArrayList<String>();
		
		//ArrayList<Coordenada> naoIr = new ArrayList<Coordenada>();
		
		Coordenada coordenada = new Coordenada();
		
		
		/* Guarda se achou ou n„o a saÌda */
		boolean achou = false;
		
		/* Guarda se houve movimento na rodada */
		boolean mudou = false;
		
		/* Ve se encontrou a saÌda */
		if(responder.ask("aqui").equals("saida")) {
			System.out.println("Voce encontrou a saida!");
			achou = true;
			return achou;
		}		
		
		int voltas = 0;
		
		while(!achou) {
			
			mudou = false;
						
			/* Ver se da pra ir a direita */
		
				if(responder.ask("leste").equals("passagem") || responder.ask("leste").equals("saida")) {
					
					coordenada.move("leste");
					responder.move("leste");
					if(coordenada.testa(sequencia)) {
						responder.move("oeste");
						coordenada.move("oeste");
						
					}
					else {					
						sequencia.add("leste");
						mudou = true;
						
					}
				}
			
			
			
			
			
			if(!mudou)	
			/* Ver se da pra ir pra cima */
				
					if(responder.ask("norte").equals("passagem") || responder.ask("norte").equals("saida")) {					
						responder.move("norte");
						coordenada.move("norte");
						if(coordenada.testa(sequencia)) {
							responder.move("sul");
							coordenada.move("sul");	
						}
						else {						
							sequencia.add("norte");
							mudou = true;
							
						}
					
				}
			
			
			if(!mudou)			
				/* Ver se da pra ir pra esquerda */
				
					if(responder.ask("oeste").equals("passagem") || responder.ask("oeste").equals("saida")) {
						responder.move("oeste");
						coordenada.move("oeste");
						if(coordenada.testa(sequencia)) {
							responder.move("leste");
							coordenada.move("leste");
						}
						else {						
							sequencia.add("oeste");
							mudou = true;
							
						}
					
				}
			
			
			
			if(!mudou)			
				/* Ver se da pra ir pra baixo */
				
					if(responder.ask("sul").equals("passagem") || responder.ask("sul").equals("saida")) {
						responder.move("sul");
						coordenada.move("sul");
						if(coordenada.testa(sequencia)) {
							responder.move("norte");
							coordenada.move("norte");
						}
						else {						
							sequencia.add("sul");
							mudou = true;
							
						}
					
				}
			
			/* Ve se encontrou a saÌda */
			if(responder.ask("aqui").equals("saida")) {
				System.out.println("Voce encontrou a saida!");
				achou = true;
				return achou;
			}				
			
			if(!mudou) {				
				
				if(sequencia.get(sequencia.size() - voltas - 1).equals("leste")) {
					coordenada.move("oeste");
					responder.move("oeste");
					sequencia.add("oeste");
				}
				
				else if(sequencia.get(sequencia.size() - voltas - 1).equals("norte")) {
					coordenada.move("sul");
					responder.move("sul");
					sequencia.add("sul");
				}
				
				else if(sequencia.get(sequencia.size() - voltas - 1).equals("oeste")) {
					coordenada.move("leste");
					responder.move("leste");
					sequencia.add("leste");
				}
				
				else if(sequencia.get(sequencia.size() - voltas - 1).equals("sul")) {
					coordenada.move("norte");
					responder.move("norte");
					sequencia.add("norte");
				}
				
				voltas += 2;  
			}
			else
				voltas = 0;
		
			
			/* Se voltou para a entrada */
			if(responder.ask("aqui").equals("entrada")) {
				System.out.println("FuÈm fuÈm fuÈm!");
				return true;			
			}
			
			
		}	
		
		return true;
	
	}
}
