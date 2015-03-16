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
		
		while(!achou) {
			
			mudou = false;	
			String naoIr = new String();
			naoIr = "a";
			
			
						
			/* Ver se da pra ir a direita */
			if(!naoIr.equals("leste")) {
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
						System.out.println("leste");
					}
				}
			}
			
			
			
			
			if(!mudou)	
			/* Ver se da pra ir pra cima */
				if(!naoIr.equals("norte")) {
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
							System.out.println("norte");
						}
					}
				}
			
			
			if(!mudou)			
				/* Ver se da pra ir pra esquerda */
				if(!naoIr.equals("oeste")) {
					if(responder.ask("oeste").equals("passagem") || responder.ask("oeste").equals("saida")) {
						responder.move("oeste");
						if(coordenada.testa(sequencia)) {
							responder.move("leste");
							coordenada.move("leste");
						}
						else {						
							sequencia.add("oeste");
							mudou = true;
							System.out.println("oeste");
						}
					}
				}
			
			
			
			if(!mudou)			
				/* Ver se da pra ir pra baixo */
				if(!naoIr.equals("norte")) {
					if(responder.ask("sul").equals("passagem") || responder.ask("sul").equals("saida")) {
						responder.move("sul");
						if(coordenada.testa(sequencia)) {
							responder.move("norte");
							coordenada.move("norte");
						}
						else {						
							sequencia.add("sul");
							mudou = true;
							System.out.println("sul");
						}
					}
				}
			
			/* Ve se encontrou a saÌda */
			if(responder.ask("aqui").equals("saida")) {
				System.out.println("Voce encontrou a saida!");
				achou = true;
				return achou;
			}				
			
			if(!mudou) {				
				
				naoIr = sequencia.get(sequencia.size() - 1);
				
				if(naoIr.equals("norte")) {
					coordenada.move("sul");
					responder.move("sul");					
				}
				
				if(naoIr.equals("leste")) {
					coordenada.move("oeste");
					responder.move("oeste");					
				}
				
				if(naoIr.equals("sul")) {
					coordenada.move("norte");
					responder.move("norte");					
				}
				
				if(naoIr.equals("oeste")) {
					coordenada.move("leste");
					responder.move("leste");					
				}					
			}			
			
			/* Se voltou para a entrada */
			if(responder.ask("aqui").equals("entrada")) {
				System.out.println("FuÈm fuÈm fuÈm!");
				return true;			
			}
			System.out.println(naoIr);
		
		}	
		
		return true;
	
	}
}
