package javalatro;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Mano
{
	int tamanoMano;
	public ArrayList<Carta> manoJugador = new ArrayList<Carta>();
	public Mano(int tamanoMano)
	{
		this.tamanoMano = tamanoMano;
	}
	//Elimina una carta de la mano
	public void quitarCartaMano(int index)
	{
		System.out.println("Se quita la carta "+this.manoJugador.get(index).nombre);
		this.manoJugador.remove(index);
	}
	//Selecciona x numero de cartas y las mete en una nueva mano
	public void seleccionarMano(Mano manoDelJugador, Scanner entrada)
	{
		
		
		boolean formatoValido = false;
		int[] posicionesSeparadasInt = new int[5];
		String posiciones = "";
		//Pedir unas posciiones hasta que el usuario meta unas validas
		do 
		{
			System.out.println("\nIntroduce el numero de la posición de las distintas cartas a seleccionar:\n");
			posiciones = entrada.nextLine();

			String[] posicionesSeparadasString = posiciones.split("\\s");

			if(Comprobador.formatoSeleccionNumeroValido(posicionesSeparadasString,manoDelJugador.manoJugador.size(),5))
			{
				formatoValido = true;
				posicionesSeparadasInt = Comprobador.posicionSeleccionarTransformar(posicionesSeparadasString);
			}
		
		}while(!formatoValido);
		
		for(int x = 0; x < posicionesSeparadasInt.length; x++)
		{
			this.meterCarta(manoDelJugador.getCartaMano(posicionesSeparadasInt[x]-1));
		}
	}
	//Vacia la mano
	public void vaciarMano()
	{
		int longitud = this.manoJugador.size();
		for(int x = 0; x<longitud; x++)
		{
			this.manoJugador.remove(this.manoJugador.getLast());
		}
	}
	//Mete una carta especificada
		public void meterCarta(Carta cartaMeter)
		{
			this.manoJugador.add(cartaMeter);
			
		}
		//Debuelve la carta en una posicion (contando desde 0)
		public Carta getCartaMano( int posicion)
		{
			if(Comprobador.numeroEntreValorGyP(posicion, this.manoJugador.size()-1, 0))
			{
				return this.manoJugador.get(posicion);
			}
			else
			{
				System.err.println("ERROR: comprobando posicion "+posicion);
			}
			return this.manoJugador.get(0);
		}
	//Robar cartas de la baraja
		public void robarMano2(Baraja baraja, int seed)
		{
			Random rand = new Random(seed);
			Collections.shuffle(baraja.baraja, rand);
			int longitudMano = this.tamanoMano;
			//Robar una carta por cada hueco libre en la mano
			if(!baraja.todaRobada()&&this.manoJugador.size()<longitudMano)
			{
				for(int x = this.manoJugador.size(); x <longitudMano; x++)
				{
				
					for(int y = 0; y < baraja.baraja.size(); y++)
					{
						if(baraja.baraja.get(y).robada == false)
						{
							//System.out.println("Robando carta: "+ baraja.baraja.get(y).numero + " "+baraja.baraja.get(y).palo);
							this.meterCarta(baraja.baraja.get(y));
							baraja.baraja.get(y).robada = true;
							break;
						}
					
					}
				
				}
			}
			
		}
	
	
	//Ordenar cartas de la mano por numero, yendo de picas a corazones a treboles a diamantes
	public static Mano ordenarManoNumero(Mano manoOrdenar)
	{
		String[] palosPosibles = {"♠","♥","♣","♦"};
		int longitudMano = manoOrdenar.manoJugador.size();
		Mano manoDevolver0 = new Mano(longitudMano);
		int[] arrayNumeros = new int[longitudMano];
		//Crea un array ordenado por numero de mayor a menor con los numeros de la mano
		for(int x = 0; x < longitudMano; x++)
		{
			int numeroComprobar = manoOrdenar.manoJugador.get(x).numero;
			for(int y = 0; y < longitudMano; y++)
			{
				if(arrayNumeros[y]==0)
				{
					arrayNumeros[y] = numeroComprobar;
					break;
				}
				else if(arrayNumeros[y] <= numeroComprobar)
				{
					Comprobador.moverALaDerecha(arrayNumeros, y, numeroComprobar);
					break;
				}
			}
		}
		
		
		//Ordenar por numero
		for(int x = 0; x < arrayNumeros.length; x++)
		{
			for(int y = 0; y < longitudMano; y++)
			{
				if(arrayNumeros[x] == manoOrdenar.manoJugador.get(y).numero&&!manoOrdenar.manoJugador.get(y).ordenada)
				{
					manoDevolver0.manoJugador.add(manoOrdenar.manoJugador.get(y));
					manoDevolver0.manoJugador.getLast().ordenada = true;
					break;
				}
			}
		}
		//Quitar estado ordenado
		for(Carta carta : manoDevolver0.manoJugador)
		{
			carta.ordenada = false;
		}
		Mano manoDevolver = new Mano(longitudMano);
		for(int x = 0; x < longitudMano; x++)
		{
			int frecuenciaNumero = Comprobador.contarFrecuenciaNumeroArray(arrayNumeros, arrayNumeros[x]);
			if(frecuenciaNumero > 1)
			{
				//System.out.printf("\nEl numero %d tiene %d frecuencia\n",arrayNumeros[x],frecuenciaNumero);
				int ultimaPosicion = Comprobador.ultimaPosicion(arrayNumeros, arrayNumeros[x]);				
				for(int y = 0; y < palosPosibles.length; y++)
				{
					for(int z = x; z <= ultimaPosicion; z++)
					{
						//System.out.printf("Se comprueba la carta %d %s con el palo %s\n",manoDevolver0.manoJugador.get(z).numero,manoDevolver0.manoJugador.get(z).palo, palosPosibles[y] );
						if(manoDevolver0.manoJugador.get(z).palo.contains(palosPosibles[y]))
						{
							manoDevolver.manoJugador.add(manoDevolver0.manoJugador.get(z));
							
						}
					}
					
				}
				x = ultimaPosicion;
			}
			//Si el valor de esa carta no sale mas de una vez se mete directamente
			else
			{

				manoDevolver.manoJugador.add(manoDevolver0.manoJugador.get(x));
				
			}
		}
		
		return manoDevolver;
	}
	//Quitar cartas jugadas de la mano
	public void quitarCartasJugadas(Mano manoSeleccionada)
	{
		for(int x = this.manoJugador.size()-1; x>=0; x--)
		{
			if(x < this.manoJugador.size())
			{

				for(int y = 0; y < manoSeleccionada.manoJugador.size(); y++)
				{

					Carta cartaManoJugador = this.manoJugador.get(x);

					Carta cartaManoSeleccionada = manoSeleccionada.getCartaMano(y);
					//System.out.printf("Se comprueba la carta %d %s y la %d %s con ids %d %d\n",cartaManoJugador.numero,cartaManoJugador.palo,cartaManoSeleccionada.numero, cartaManoSeleccionada.palo,cartaManoJugador.idCarta, cartaManoSeleccionada.idCarta);
					if(cartaManoSeleccionada.idCarta == cartaManoJugador.idCarta)
					{
						System.out.println("BINGO");
						this.manoJugador.remove(x);
						break;
					
					}
				}
			}
		}
	}
	//descartar cartas seleccionadas
	public static void descartarSeleccion(Mano manoJugador, Mano manoSeleccionada)
	{
		if(manoSeleccionada.manoJugador.size()!=0)
		{
			for(int x = manoJugador.manoJugador.size()-1; x>=0; x--)
			{
				if(x < manoJugador.manoJugador.size())
				{

					for(int y = 0; y < manoSeleccionada.manoJugador.size(); y++)
					{

						Carta cartaManoJugador = manoJugador.manoJugador.get(x);

						Carta cartaManoSeleccionada = manoSeleccionada.getCartaMano(y);
						//System.out.printf("Se comprueba la carta %d %s y la %d %s con ids %d %d\n",cartaManoJugador.numero,cartaManoJugador.palo,cartaManoSeleccionada.numero, cartaManoSeleccionada.palo,cartaManoJugador.idCarta, cartaManoSeleccionada.idCarta);
						if(cartaManoSeleccionada.idCarta == cartaManoJugador.idCarta)
						{
							System.out.println("BINGO");
							manoJugador.manoJugador.remove(x);
							break;
						
						}
					}
				}
			}
		}
		else
		{
			System.out.println("\nSelecciona cartas a descartar\n");
		}
	}
	//Mueve la carta en la posicion a, a la posicion b, moviendo el resto de cartas a la derecha
	public static Mano moverCarta(Mano estaMano,Scanner entrada)
	{
		
		boolean formatoValido = false;
		int[] posicionesSeparadasInt = new int[2];
		String posiciones = "";
		//Pedir unas posciiones hasta que el usuario meta unas validas
		do 
		{
			boolean dosNumeros = true;
			System.out.println("\nIntroduce el numero de la posición de las distintas cartas a seleccionar:\n");
			posiciones = entrada.nextLine();

			String[] posicionesSeparadasString = posiciones.split("\\s");
			if(posicionesSeparadasString.length != 2)
			{
				System.out.println("\nIntroduce dos numeros\n");
				dosNumeros = false;
				
			}
			if(dosNumeros&&Comprobador.formatoSeleccionNumeroValido(posicionesSeparadasString,estaMano.manoJugador.size(),2))
			{
				formatoValido = true;
				posicionesSeparadasInt = Comprobador.posicionSeleccionarTransformar(posicionesSeparadasString);
			}
		
		}while(!formatoValido);
		Carta cartaMover = estaMano.manoJugador.get(posicionesSeparadasInt[0]-1);
		int posicionAMover = posicionesSeparadasInt[1]-1;
		int posicionCartaMover = posicionesSeparadasInt[0]-1;
		Mano manoMovida = new Mano(estaMano.manoJugador.size());
		if(posicionCartaMover < posicionAMover)
		{
			for(int x = 0; x < estaMano.manoJugador.size(); x++)
			{
				if(x < posicionCartaMover)
				{
					manoMovida.manoJugador.add(estaMano.manoJugador.get(x));
				}
				else if(x >= posicionCartaMover && x < posicionAMover)
				{
					manoMovida.manoJugador.add(estaMano.manoJugador.get(x+1));
				}
				else if(x == posicionAMover)
				{
					manoMovida.manoJugador.add(cartaMover);
				}
				else if( x > posicionAMover)
				{
					manoMovida.manoJugador.add(estaMano.manoJugador.get(x));
				}		
			}
		}
		else
		{
			for(int x = 0; x < estaMano.manoJugador.size(); x++)
			{
				if(x < posicionAMover)
				{
					manoMovida.manoJugador.add(estaMano.manoJugador.get(x));
				}
				else if(x == posicionAMover)
				{
					manoMovida.manoJugador.add(cartaMover);
				}
				else if(x > posicionAMover && x <= posicionCartaMover)
				{
					manoMovida.manoJugador.add(estaMano.manoJugador.get(x-1));
				}
				else if(x > posicionCartaMover)
				{
					manoMovida.manoJugador.add(estaMano.manoJugador.get(x));
				}
				
			}
		}
		
	
		 
		 for(Carta carta : estaMano.manoJugador)
			{
				System.out.printf("Carta %d %s\n", carta.numero, carta.palo);
			}
		 return manoMovida;
		
		
	}
	//jugar mano seleccionada
	public void jugarMano(Scoring scoring, ComprobadorManoSeleccionada comprobadorMano)
	{
		TipoDeMano tipoManoJugada  = comprobadorMano.seleccionarManoJugada(this);
		Mano manoScored = comprobadorMano.sacarManoValida(this,tipoManoJugada);
		
		scoring.calcularScore(manoScored, tipoManoJugada, manoScored);
		scoring.reiniciarScoreMano();
		
	}
}
