package javalatro;
import java.util.Scanner;
import java.util.Random;
public class Menu
{
	int numeroDeOpciones;
	int eleccion;
	public Menu()
	{
		
	}
	public String tomarEleccion(Scanner entrada)
	{
		String eleccion = entrada.nextLine();
		return eleccion;
	}
	public boolean eleccionValida(String eleccion)
	{
		if(eleccion.length()==1)
		{
			if(Character.isDigit(eleccion.charAt(0)))
			{
				if(Integer.decode(eleccion) >= 0 && Integer.decode(eleccion) < this.numeroDeOpciones)
				{
					return true;
				}
			}
			
		}
		
		System.out.println("Introduce una opcion valida:");
		return false;
	}
	public int menuSeleccionBaraja(Scanner entrada)
	{
		this.numeroDeOpciones=6;
		String eleccionMenuString = "";

		System.out.println("\nSELECCIONA LA BARAJA:");
		System.out.println("---------------------");
		System.out.println("[1] Baraja Roja");
		System.out.println("[2] Baraja Azul");
		System.out.println("[3] Baraja Abandonada");
		System.out.println("[4] Baraja Bicolor");
		System.out.println("[5] Baraja Chiqui");
		System.out.println("[0] Volver\n");
		do {
			eleccionMenuString = tomarEleccion(entrada);
		}while(!eleccionValida(eleccionMenuString)); 
		int eleccionMenu = Integer.decode(eleccionMenuString);
		return eleccionMenu;
		
	}
	public void menuPrincipal(Scanner entrada)
	{
		int eleccion = -1;
		do {
		this.numeroDeOpciones = 3;
		String eleccionString = "";
		
		//Si se elige nueva partida se pide baraja directamente
		if(eleccion != 1)
		{
		System.out.println("     ____   ____  __ __   ____  _       ____  ______  ____   ___  \r\n"
				+ "    |    | /    ||  |  | /    || |     /    ||      ||    \\ /   \\ \r\n"
				+ "    |__  ||  o  ||  |  ||  o  || |    |  o  ||      ||  D  )     |\r\n"
				+ "    __|  ||     ||  |  ||     || |___ |     ||_|  |_||    /|  O  |\r\n"
				+ "   /  |  ||  _  ||  :  ||  _  ||     ||  _  |  |  |  |    \\|     |\r\n"
				+ "   \\  `  ||  |  | \\   / |  |  ||     ||  |  |  |  |  |  .  \\     |\r\n"
				+ "    \\____j|__|__|  \\_/  |__|__||_____||__|__|  |__|  |__|\\_|\\___/ \r\n"
				+ "\n                                                         "
				+ "v.0.25.10"
				);
		System.out.println();
		System.out.println("[1] Iniciar Juego");
		System.out.println("[2] Créditos");
		System.out.println("[0] Salir");
		System.out.println();
		do {
			eleccionString = tomarEleccion(entrada);
			
		}while(!eleccionValida(eleccionString));
		eleccion = Integer.decode(eleccionString);
		}
		switch(eleccion)
		{
			case 1:
				int eleccionBaraja = menuSeleccionBaraja(entrada);
				//Setear juego
				if(eleccionBaraja!=0)
				{
					//Seed 
					int seed = Comprobador.setearSeed(entrada);
					Random random = new Random(seed);
					//Crear baraja elegida
					Baraja baraja = new Baraja(eleccionBaraja);
					//Controlador del juego
					ControladorJuego controladorJuego = new ControladorJuego();
					//Perfil Jugador
					PerfilJugador estadoJugador = new PerfilJugador();
					//Iniciar Manos
					Mano manoJugador = new Mano(estadoJugador.tamanoManoInicial);
					Mano manoSeleccionada = new Mano(5);
					//Comprobador de manos y objetos manos
					ComprobadorManoSeleccionada comprobadorMano = new ComprobadorManoSeleccionada();
					switch(eleccionBaraja)
					{
						//Baraja roja
						case 1:
							estadoJugador.descartesIniciales = estadoJugador.descartesIniciales +1;
							break;
						//Baraja azul
						case 2:
							estadoJugador.turnosManosIniciales = estadoJugador.turnosManosIniciales +1;
							break;
						default:
							break;
					}
					eleccion = menuTurno(seed,entrada, manoJugador, manoSeleccionada, random, estadoJugador, baraja, controladorJuego, comprobadorMano);
				}
				else
				{
					eleccion = 0;
				}
				break;
			case 2 :
				InterfazGrafica.imprimirCreditos();
				break;
			case 0:
				System.out.println("\n------------------------------");
				System.out.println("CERRANDO JUEGO, BUEN DÍA PAPU");
				System.out.println("------------------------------");
				return;
			default:
				System.out.println("ERROR: eleccion no reconocida");
				break;
		}
		}while(eleccion != 3);
	
	
	}
	//imprime el menú de perder
	public int menuPerder(Scanner entrada, long seed )
	{
		this.numeroDeOpciones = 2;
		String eleccionString = "";
			
		System.out.println("           __      __   ___  __   __     __   __  \r\n"
				+ "|__|  /\\  /__`    |__) |__  |__) |  \\ | |  \\ /  \\ \r\n"
				+ "|  | /~~\\ .__/    |    |___ |  \\ |__/ | |__/ \\__/ \r\n"
				+ "                                                  ");
		System.out.println("\nSemilla de la partida: "+seed);
		System.out.println("\n[1]Nueva partida"
				+ "			  [0]Menú principal\n");
		do {
			eleccionString = tomarEleccion(entrada);
			
		}while(!eleccionValida(eleccionString));
		int eleccion = Integer.decode(eleccionString);
		return eleccion;
		
	}
	//imprime la mano de la ronda
	public void imprirManoRonda(Mano manoJugador, Mano manoSeleccionada, PerfilJugador estadoJugador, ControladorJuego controladorJuego, ComprobadorManoSeleccionada comprobadorMano)
	{
		for(Carta carta : manoJugador.manoJugador)
		{
			System.out.printf("Carta %d %s\n", carta.numero, carta.palo);
		}
		InterfazGrafica.imprimirManoSeleccionada(manoJugador,manoSeleccionada);
		System.out.printf(" %d/%d\n",manoJugador.manoJugador.size(), manoJugador.tamanoMano);
		InterfazGrafica.imprimirEstadisticas(estadoJugador, manoSeleccionada, controladorJuego, comprobadorMano);
		
	}
	public int menuTurno(int seed, Scanner entrada, Mano manoJugador,Mano manoSeleccionada, Random random, PerfilJugador estadoJugador, Baraja baraja, ControladorJuego controladorJuego,ComprobadorManoSeleccionada comprobadorMano )
	{
		this.numeroDeOpciones = 7;
		boolean manoJugada = false;
		estadoJugador.iniciarRonda();
		//Iniciar scoring
		Scoring scoring = new Scoring();
		//Seed
		int seedRelativa = random.nextInt();
		boolean salirMenu = false;
		boolean imprimirMano = true;
		//Vaciar seleccion
		manoSeleccionada.vaciarMano();
		manoJugador.robarMano2(baraja, seedRelativa);
		manoJugador = Mano.ordenarManoNumero(manoJugador);
		String eleccionString = "";
		
		do {
			//Si se ha llegado a la puntuación, se pasa de ronda
			if(Comprobador.seHaLlegadoAlScore(scoring, controladorJuego))
			{
				controladorJuego.rondaGanada(estadoJugador, scoring, baraja, manoJugador, seedRelativa);
			}
			//Si no quedan cartas para jugar o no tienes manos
			if(Comprobador.noQuedanCartasJugar(manoJugador, baraja)||Comprobador.sinManosRestantes(estadoJugador.turnosManosActuales))
			{
				return menuPerder(entrada, (long)seed);
			}
			InterfazGrafica.imprimirScoreRequerido(controladorJuego);
			int eleccion = 1;
			manoJugador.robarMano2(baraja, seedRelativa);
			if(manoJugada)
			{
				manoJugador = Mano.ordenarManoNumero(manoJugador);
				manoJugada = false;
			}
			if(imprimirMano)
			{
				imprirManoRonda(manoJugador, manoSeleccionada, estadoJugador,controladorJuego, comprobadorMano);
				imprimirMano = false;
			}
			System.out.println("\n[1] Seleccionar Cartas");
			System.out.println("[2] Jugar Mano Seleccionada");
			System.out.println("[3] Descartar Selección");
			System.out.println("[4] Quitar Selección");
			System.out.println("[5] Mover Cartas");
			System.out.println("[6] Mostrar Baraja");
			System.out.println("[0] Volver al menú principal");
			System.out.println();
			do {
				eleccionString = tomarEleccion(entrada);
				
			}while(!eleccionValida(eleccionString));
			eleccion = Integer.decode(eleccionString);
			switch(eleccion)
			{
				case 1:
					manoSeleccionada.vaciarMano();
					manoSeleccionada.seleccionarMano(manoJugador, entrada);

					imprimirMano = true;
					break;
				case 2 :
					if(manoSeleccionada.manoJugador.size()!=0)
					{
						System.out.println("Quitando cartas jugadas: ");
						manoJugador.quitarCartasJugadas(manoSeleccionada);
						InterfazGrafica.imprimirManoSeleccionada(manoSeleccionada, comprobadorMano.sacarManoValida(manoSeleccionada,comprobadorMano.seleccionarManoJugada(manoSeleccionada)));

						InterfazGrafica.imprimirManoSeleccionada(manoJugador,manoSeleccionada);
						System.out.printf(" %d/%d\n",manoJugador.manoJugador.size(), manoJugador.tamanoMano);
						manoSeleccionada.jugarMano(scoring,comprobadorMano);
						manoSeleccionada.vaciarMano();
						estadoJugador.turnosManosActuales = estadoJugador.turnosManosActuales-1;
						manoJugada = true;
						
					}
					else
					{
						System.out.println("\nSelecciona cartas para jugar\n");
					}
					imprimirMano = true;
					break;
				case 3 :
					if(estadoJugador.descartesActuales > 0)
					{
						if(manoSeleccionada.manoJugador.size()!=0)
						{
							Mano.descartarSeleccion(manoJugador, manoSeleccionada);
							estadoJugador.descartesActuales = estadoJugador.descartesActuales -1;
							System.out.println("\nDescartes disponibles: "+estadoJugador.descartesActuales);
							manoSeleccionada.vaciarMano();
							
						}
						else
						{
							System.out.println("\nSelecciona cartas a descartar\n");
						}
						imprimirMano = true;
						manoJugada = true;
					}
					else
					{
						System.out.println("\nNo quedan descartes disponibles\n");
						imprimirMano = true;
					}
					break;
				case 4 :

					manoSeleccionada.vaciarMano();
					imprimirMano = true;
					break;
			
				case 5 :
					manoJugador = Mano.moverCarta(manoJugador, entrada);
					System.out.println();
					for(Carta carta : manoJugador.manoJugador)
					{
						System.out.printf("Carta %d %s\n", carta.numero, carta.palo);
					}
					imprimirMano = true;

					break;
				case 6 :
					System.out.println("\n"+baraja.nombre+":\n");
					InterfazGrafica.imprimirBarajaRobadas(baraja);
					System.out.println();
					imprimirMano = true;
					break;

				case 0:
					boolean valido = false;
					System.out.println("\n¿Seguro que quieres salir al menú principal? [Y/N]\n");
					do {
					String salir = entrada.nextLine();
					salir = salir.toUpperCase();
					if(salir.contains("Y"))
					{
						System.out.println();
					valido = true;
					salirMenu = true;
					}else if(salir.contains("N"))
					{
						InterfazGrafica.imprimirManoSeleccionada(manoJugador,manoSeleccionada);
						valido = true;
					}
					else
					{
						System.out.println("Introduce Y o N");
					}
					}while(!valido);
					break;
				default:
					System.out.println("ERROR: eleccion no reconocida");
					break;
			}
		}while(!salirMenu);
		return 0;
	}
}
