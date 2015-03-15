package pt.c02classes.s01knowledge.s02app.actors;

import java.util.ArrayList;
import java.util.Scanner;

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
		
		
		while(!achou) {
			
			mudou = false;
			
			
			/* Ve se encontrou a saÌda */
			if(responder.ask("aqui").equals("saida")) {
				System.out.println("VocÍ encontrou a saida!");
				achou = true;
				return achou;
			}			
						
			/* Ver se da pra ir a direita */
			if(responder.ask("leste").equals(" ")) {
				responder.move("leste");
				if(coordenada.testa(sequencia))
					responder.move("oeste");
				else {
					coordenada.move("leste");
					sequencia.add("leste");
					mudou = true;
				}
			}
			
			else			
			/* Ver se da pra ir pra cima */
				if(responder.ask("norte").equals(" ")) {
					responder.move("norte");
					if(coordenada.testa(sequencia))
						responder.move("sul");
					else {
						coordenada.move("norte");
						sequencia.add("norte");
						mudou = true;
					}
				}
				else
			
					/* Ver se da pra ir pra esquerda */
					if(responder.ask("oeste").equals(" ")) {
						responder.move("oeste");
						if(coordenada.testa(sequencia))
							responder.move("leste");
						else {
							coordenada.move("oeste");
							sequencia.add("oeste");
							mudou = true;
						}
					}
			
					else			
						/* Ver se da pra ir pra baixo */
						if(responder.ask("sul").equals(" ")) {
							responder.move("sul");
							if(coordenada.testa(sequencia))
								responder.move("norte");
							else {
								coordenada.move("sul");
								sequencia.add("sul");
								mudou = true;
							}
						}
			
			if(!mudou) {
				System.out.println("FuÈm fuÈm fuÈm!");
				return true;				
			}
		
		}	
		
		return true;
	
	}
}
