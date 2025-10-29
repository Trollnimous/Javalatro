package balala;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
			System.out.println("Introduce el numero de la posición de las distintas cartas a seleccionar:");
			posiciones = entrada.nextLine();
			String[] posicionesSeparadasString = posiciones.split("\\s");
			if(Comprobador.formatoSeleccionNumeroValido(posicionesSeparadasString,manoDelJugador.manoJugador.size()))
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
			if(!baraja.todaRobada())
			{
			for(int x = this.manoJugador.size(); x <longitudMano; x++)
			{
				for(int y = 0; y < baraja.baraja.size(); y++)
				{
					if(baraja.baraja.get(y).robada == false)
					{
							this.meterCarta(baraja.baraja.get(y));
							baraja.baraja.get(y).robada = true;
							break;
					}
					
				}
				
			}
			}
		}
	//Robar cartas de la baraja 
	public void robarMano(Baraja baraja, int seed)
	{
		Random rand = new Random(seed);
		int cartasR = 0;
		int longitudMano = this.tamanoMano;
		//Robar una carta por cada hueco libre en la mano
		for(int x = this.manoJugador.size(); x <longitudMano; x++)
		{
			
			boolean cartaValida = false;
			boolean todasCheckeadas = false;
			Carta cartaParaRobar = new Carta(14,"picas");
			//Se selecciona una posicion random dentro de la baraja
			int posicionBaraja = rand.nextInt(0, baraja.longitud);
			//Comprobar que no se hayan repartido ya todas las cartas
			if(cartasR < baraja.baraja.size())
			{	
			//No dejar de robar hasta que una carta sea valida
			//Si no es valida la carta, se roba la siguiente
			int contador = 0;
			while(cartaValida == false && todasCheckeadas == false	)
			{

				//Si la posicion a checkear es mayor que su longitud, se resetea a 0
				if(posicionBaraja > baraja.longitud-1)
				{
					posicionBaraja = 0;

				}
				//Se selecciona una carta segun la posicion a robar
				cartaParaRobar = baraja.getCartaBaraja(baraja, posicionBaraja);
				//Si la carta no estaba robada, se da por valida y se marca como robada
				if(baraja.getCartaBaraja(baraja, posicionBaraja).robada==false)
				{
					baraja.baraja.get(posicionBaraja).robada = true;
					cartaValida = true;
					cartasR++;
				}
				//Si se ha recorrido toda la baraja y todas las cartas estaban robadas, se sale del bucle, y se marca como TODASROBADAS
				else if(contador > longitudMano)
				{
					todasCheckeadas = true;
					break;
				}
				//Se mira la siguiente carta
				posicionBaraja++;
				contador++;
			}
			//Si la carta es valida, se roba
			if(cartaValida)
			{
				this.meterCarta(cartaParaRobar);
			}
			
			}
		}
	}
	//Ordenar cartas de la mano por numero
	public static void ordenarManoNumero(Mano manoOrdenar)
	{
		manoOrdenar.manoJugador.sort(Comparator.comparingInt(Carta::obtenerNumero).reversed());
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
	//jugar mano seleccionada
	public void jugarMano(Scoring scoring, ComprobadorManoSeleccionada comprobadorMano)
	{
		TipoDeMano tipoManoJugada  = comprobadorMano.seleccionarManoJugada(this);
		Mano manoScored = comprobadorMano.sacarManoValida(this,tipoManoJugada);
		
		scoring.calcularScore(manoScored, tipoManoJugada, manoScored);
		scoring.reiniciarScoreMano();
		
	}
}
