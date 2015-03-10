package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.impl.Declaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{	
	private static IBaseConhecimento bc = new BaseConhecimento();
	/* Criamos um vetor de Strings com os nomes dos animais */
	private static String[] animals = bc.listaNomes();
    	IObjetoConhecimento[] obj = new IObjetoConhecimento[animals.length];
    
	public Enquirer()
	{
		
		for(int i = 0; i < animals.length;i++)//funcao responsavel por alocar o vetor de animais
		{
			obj[i] = bc.recuperaObjeto(animals[i]);
		}
		

	}
	
	
	@Override
	public void connect(IResponder responder)
	{
		boolean flag, flag2;
		/* Criamos um vetor de declaracoes para armazenar as perguntas feitas e suas respostas */
		IDeclaracao[] decl = new Declaracao[40];
		/* Variavel auxiliar de declaracoes */
		IDeclaracao declaux;
		int k = 0;
		int i = 0;
		declaux = obj[i].primeira();
		for(i = 0; i < animals.length && declaux != null; i++)//percorre cada animal perguntando
		 {
		 	flag = true;
		 	/* Perecorre cada pergunta ate a resposta for diferente da esperada */
			for(declaux = obj[i].primeira(); declaux != null && flag;  declaux = obj[i].proxima())
			{
				flag2 = true;
				/* Olha se ja perguntou para nao repetir */
				for(int j = 0; j < k && flag && flag2; j++)
				{
					/* Se for a mesma pergunta */
					if(declaux.getPropriedade().equalsIgnoreCase(decl[j].getPropriedade()))
					{
						flag2 = false;
						/* Se for reposta diferente */
						if(!declaux.getValor().equalsIgnoreCase(decl[j].getValor()))
						{
							flag = false;
								
						}
	
					}
				}
				if(flag2)
				{
					/* Poe uma nova pergunta ao vetor das repetidas*/
					decl[k] = new Declaracao(declaux.getPropriedade(), responder.ask(declaux.getPropriedade()));
					if(!declaux.getValor().equalsIgnoreCase(decl[k].getValor()))
					{
							
						flag = false;
					}
					k++;
						
				}
			}
		}
		boolean acertei = false;
		
		if (declaux == null)
			acertei = responder.finalAnswer(animals [i - 1]);
		else
			acertei = responder.finalAnswer("nao conheco");
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");

	}


	private IDeclaracao IDeclaracao(String propriedade, String ask) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
