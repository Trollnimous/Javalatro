package javalatro;
import java.util.Comparator;

public class InterfazGrafica
{
	//Le das el espacio a llenar y la longitud variablea comprobar y te llena los espacios justos para que quepa la variable
	public static void imprimirEspaciosVariable(int numeroDeEspacios, int variableComprobar)
	{
		int longitudVariable = Comprobador.longitudNumero(variableComprobar);
		for(int x = 0; x < numeroDeEspacios-longitudVariable; x++)
		{
			System.out.print(" ");
		}
	}
	//Imprimir datos del jugador
	public static void imprimirPerfilJugador(PerfilJugador estadoJugador)
	{
		System.out.println("---------------------------------");
		System.out.println("Manos: "+estadoJugador.turnosManosActuales);
		System.out.println("Descartes: "+estadoJugador.descartesActuales);
		System.out.println("Oro: "+estadoJugador.dinero);
		System.out.println("---------------------------------");
	}
	//Imprimir nombre de la mano
	public static void imprimirNombreManoMenu(String nombreMano, int espacioTotal)
	{
		int espaciosImprimir = (espacioTotal-nombreMano.length())/2;
		for(int x = 0; x < espaciosImprimir; x++)
		{
			System.out.print(" ");
		}
		System.out.print(nombreMano);
		for(int x = 0; x < espaciosImprimir; x++)
		{
			System.out.print(" ");
		}
	}
	//Imprime el score requerido
	public static void imprimirScoreRequerido(ControladorJuego controlador)
	{
		System.out.println("--------------------------------\n");
		System.out.println("Score requerido: "+controlador.scoreRequerido());
		System.out.println("\n--------------------------------\n");
	}
	//Imprime los creditos
	public static void imprimirCreditos()
	{
		System.out.println("--------------------------------\n"
						  +"            CRÉDITOS            \n"
						  +"--------------------------------\n"
						  +"                                \n"
						  +" Programación: Hugo Castillejos \n"
						  +"                                \n"
						  +"   Idea tréboles: Aarón Pérez   \n"
						  +"                                \n"
						  +"--------------------------------\n"
						  
	
				);
		
	}
	//Imprimir estadísticas del jugador un @ indica donde poner la funcion imprimirEspacios
	/*	┌───────────────┬───────────────────────────────────────────────────┬─────────────┐
		│ Manos: 4@     │                                                   │@    Ante: 1 │
		├───────────────┼───────────────────────────────────────────────────┼─────────────┤
		│ Descartes: 4@ │@                      0 X 0@                      │@   Ronda: 1 │
		└───────────────┴───────────────────────────────────────────────────┴─────────────┘
	 * 
	 */
	public static void imprimirEstadisticas(PerfilJugador estadoJugador, Mano manoSeleccionada, ControladorJuego contadorAntes,ComprobadorManoSeleccionada comprobadorMano )
	{
		TipoDeMano tipoDeMano = comprobadorMano.seleccionarManoJugada(manoSeleccionada);
		System.out.println("┌───────────────┬───────────────────────────────────────────────────┬─────────────┐");
		System.out.printf( "│ Manos: %d",estadoJugador.turnosManosActuales);imprimirEspaciosVariable(7,estadoJugador.turnosManosActuales);
		
		System.out.print("│");
		imprimirNombreManoMenu(tipoDeMano.nombre, 51);
		System.out.print("│ ");
		imprimirEspaciosVariable(5,contadorAntes.ante);
		System.out.printf("Ante: %d │\n",contadorAntes.ante);
		System.out.println("├───────────────┼───────────────────────────────────────────────────┼─────────────┤");
		System.out.printf( "│ Descartes: %d",estadoJugador.descartesActuales);   
		imprimirEspaciosVariable(3,estadoJugador.descartesActuales);
		System.out.print("│");
		imprimirEspaciosVariable(24, tipoDeMano.fichasActuales);
		System.out.print(tipoDeMano.fichasActuales);
		System.out.print(" X ");
		System.out.print(tipoDeMano.multActual);
		imprimirEspaciosVariable(24, tipoDeMano.multActual);
		System.out.print("│");
		imprimirEspaciosVariable(5, contadorAntes.ronda);
		System.out.printf("Ronda: %d │\n",contadorAntes.ronda);
		System.out.println("└───────────────┴───────────────────────────────────────────────────┴─────────────┘");
	}
	//Imprime la baraja seleccionada
	public static void imprimirBaraja(Baraja baraja)
	{
		Baraja barajaDummy = baraja;
		barajaDummy.baraja.sort(Comparator.comparingInt(Carta::obtenerNumero).reversed());
		for(int i = 0; i < barajaDummy.longitud; i++)
		{
			System.out.println(barajaDummy.getCartaBaraja(barajaDummy, i).nombre);
		}
		System.out.println(baraja.baraja.size());
	}
	//Imprime las carta de la varaja resaltando las que estan robadas
		public static void imprimirBarajaRobadas(Baraja baraja)
		{
			Baraja barajaDummy = baraja;
			int contador = 0;
			barajaDummy.baraja.sort(Comparator.comparingInt(Carta::obtenerNumero).reversed());
			for(int i = 0; i < barajaDummy.longitud; i++)
			{
				if(barajaDummy.baraja.get(i).robada == true)
				{
					System.out.println("*"+barajaDummy.getCartaBaraja(barajaDummy, i).nombre+"*");
					contador++;
				}
				else 
				{
					System.out.println(" "+barajaDummy.getCartaBaraja(barajaDummy, i).nombre+" ");
				}
				
			}
			System.out.println("\n"+(baraja.baraja.size()-contador)+"/"+baraja.baraja.size());
			System.out.println();
		}
	
	//Imprimir carta
	public static void imprimirCarta(Carta carta)
	{
		System.out.print( "┌─────────┐");System.out.println();
		System.out.printf("│%s 	  │",carta.palo);System.out.println();
		System.out.print( "│  	  │");System.out.println();
		System.out.printf("│    %s    │",carta.simboloValor);System.out.println();
		System.out.print( "│  	  │");System.out.println();
		System.out.printf("│  	 %s│",carta.palo);System.out.println();
		System.out.print( "└─────────┘");System.out.println();
				   
		
	}
	//Imprimir mano 
	public static void imprimirMano(Mano mano)
	{

		int longitudMano = mano.manoJugador.size();
		if(longitudMano > 0) {
		for(int x = 0; x < longitudMano; x++)
		{
			System.out.print("┌─────────┐");
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			System.out.printf("│%s        │",mano.manoJugador.get(x).palo);
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			System.out.print("│         │");
		}System.out.println();
		
		for(int x = 0; x < longitudMano; x++)
		{
			if(!mano.manoJugador.get(x).simboloValor.contains("10"))
			{
				System.out.printf("│    %s    │",mano.manoJugador.get(x).simboloValor);
			}
			else
			{
				System.out.printf("│   %s    │",mano.manoJugador.get(x).simboloValor);
			}
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			System.out.print("│         │");
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			System.out.printf("│        %s│",mano.manoJugador.get(x).palo);
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			System.out.print("└─────────┘");
		}System.out.println();
	}else
	{
		System.out.println("Mano vacía");
	}
	}
	//Imprimir scoring
	public static void imprimirScore(Scoring scoring,TipoDeMano tipoMano, Mano manoValida)
	{
		int espaciosARestar = Comprobador.longitudNumero(tipoMano.fichasActuales);
		int espaciosARestarFichas = Comprobador.longitudNumero(scoring.fichas);
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.print("\n       Fichas:");for(int x = espaciosARestar; x < 30+espaciosARestar; x++) {System.out.print(" ");}         System.out.print(  "Mult:\n");
		System.out.printf("       Base: %d",tipoMano.fichasActuales);for(int x = 0; x <31-espaciosARestar; x++) {System.out.print(" ");} System.out.printf("Base: %d\n",  tipoMano.multActual);
		for(int x = 0; x < manoValida.manoJugador.size(); x++)
		{
			System.out.printf("            +%d\n",manoValida.manoJugador.get(x).valor);
		}
		System.out.print("       ───────────");for(int x = 0; x < 15-espaciosARestar; x++) {System.out.print("─");}for(int x = 0; x < 15-espaciosARestar; x++) {System.out.print("─");}System.out.println("───────");
		System.out.printf("             %d",scoring.fichas);for(int x = 0; x <15; x++) {System.out.print(" ");}System.out.print("*");for(int x = 0; x <21-espaciosARestarFichas; x++) {System.out.print(" ");}System.out.printf("%d    =   %d\n",scoring.mult,scoring.scoreMano);
		System.out.println("\n---------------------------------------------------------------------------\n");
	}


	//Imprimir mano con las cartas jugadas destacadas
	public static void imprimirManoSeleccionada(Mano mano, Mano manoJugada)
	{

		int longitudMano = mano.manoJugador.size();
		if(longitudMano > 0) {
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				
				System.out.print("┌─────────┐");
			}
			else
			{
				System.out.print("           ");
		
			}
			
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				System.out.printf("│%s        │",mano.manoJugador.get(x).palo);

			}
			else
			{
				System.out.print("┌─────────┐");
			}
			
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				System.out.print("│         │");

			}
			else
			{
				System.out.printf("│%s        │",mano.manoJugador.get(x).palo);
			}
			
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				if(!mano.manoJugador.get(x).simboloValor.contains("10"))
				{
					System.out.printf("│    %s    │",mano.manoJugador.get(x).simboloValor);
				}
				else
				{
					System.out.printf("│   %s    │",mano.manoJugador.get(x).simboloValor);
				}

			}
			else
			{
				System.out.printf("│         │",mano.manoJugador.get(x).palo);
			}
		}System.out.println();
		
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				System.out.printf("│         │",mano.manoJugador.get(x).palo);
				

			}
			else
			{
				if(!mano.manoJugador.get(x).simboloValor.contains("10"))
				{
					System.out.printf("│    %s    │",mano.manoJugador.get(x).simboloValor);
				}
				else
				{
					System.out.printf("│   %s    │",mano.manoJugador.get(x).simboloValor);
				}
			}
		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				System.out.printf("│        %s│",mano.manoJugador.get(x).palo);

			}
			else
			{
				System.out.printf("│         │",mano.manoJugador.get(x).palo);
			}

		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				System.out.print("└─────────┘");

			}
			else
			{
				System.out.printf("│        %s│",mano.manoJugador.get(x).palo);
			}

		}System.out.println();
		for(int x = 0; x < longitudMano; x++)
		{
			if(Comprobador.cartaEnMano(mano.manoJugador.get(x), manoJugada))
			{
				System.out.print("           ");

			}
			else
			{
				System.out.printf("└─────────┘",mano.manoJugador.get(x).palo);
			}
		}System.out.println();
	}else
	{
		System.out.println("Mano vacía");
	}
	}
	
}
