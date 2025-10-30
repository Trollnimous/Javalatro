package javalatro;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Comprobador
{
	public static boolean seHaLlegadoAlScore(Scoring scoring, ControladorJuego controlador)
	{
		if(scoring.scoreTotal >=controlador.scoreRequerido())
		{
			return true;
		}
		return false;
	}
	//Comprueba si te has quedado sin manos
	public static boolean sinManosRestantes(int manos)
	{
		if(manos < 1)
		{
			return true;
		}
		return false;
	}
	//Devuelve true si no hay cartas para robar y te has quedado sin cartas
	public static boolean noQuedanCartasJugar(Mano manoJugador, Baraja baraja)
	{
		if(manoJugador.manoJugador.size() == 0 && baraja.todaRobada() == true)
		{
			return true;
		}
		return false;
	}
	//Obtiene la longitud de un numero
	public static int longitudNumero(int num)
	{
		int contador = 0;
		if(num == 0)
		{
			return 1;
		}
		for(int x = num; x > 0; x/=10)
		{
			contador++;
		}
		return contador;
	}
	//devulevle true si el array esta vacio
	public static boolean stringVacio(String comprobar)
	{
		if(!comprobar.isEmpty())
		{
			return false;
		}
		System.out.println("\nERROR: No pongas espacios al principio\n");
		return true;
	}
	//Devuelve un array de numeros con las posiciones
	public static int[] posicionSeleccionarTransformar(String[] posicionesString)
	{
		int[] posicionSeleccionar = new int[posicionesString.length];
		for(int x = 0; x < posicionesString.length; x++)
		{
			String numeroStringMeter = posicionesString[x];
			int numeroIntMeter = Math.abs(Integer.valueOf(numeroStringMeter));
			posicionSeleccionar[x] = numeroIntMeter;
		}
		return posicionSeleccionar;
	}
	//devuevle true si el array es menor que el numero especificado
	public static boolean arrayMenorQue(String[] array, int numero)
	{
		if(array.length< numero)
		{
			return true;
		}
		System.out.println("\nERROR: no se pueden seleccionar más de 5 cartas\n");
		return false;
	}
	//Comprobar formato valido de seleccion de mano
	public static boolean formatoSeleccionNumeroValido(String[] posicionesSeparadas, int tamanoMano)
	{
		//Comprueba que sea menor que 6 la longitud y que no tenga un espacio al principio
		if(!stringVacio(posicionesSeparadas[0])&&arrayMenorQue(posicionesSeparadas,6))
		{
			boolean posicionEnMano = true;
			boolean repetidas = false;
			boolean todosNumeros = true;
			for(int x = 0; x < posicionesSeparadas.length; x++)
			{
				//Comprueba que solo haya numeros en el string
				String stringComprobar = posicionesSeparadas[x];
				for(int y = 0; y < stringComprobar.length();y++)
				{
					if(!Character.isDigit(posicionesSeparadas[x].charAt(y)))
					{
						System.out.println("\nFORMATO INVALIDO: no todos son numeros");
						todosNumeros = false;
					}
				}
			}
			//Comprueba que no haya cartas repetidas
			if(todosNumeros) {
			String[] posicionesSeparadasR = posicionesSeparadas;
			for(int x = 0; x<posicionesSeparadasR.length;x++)
			{
				for(int y= 0; y<posicionesSeparadasR.length;y++)
				{
					if(Math.abs(Integer.valueOf(posicionesSeparadas[x]))==Math.abs(Integer.valueOf(posicionesSeparadas[y]))&&x!=y)
					{
						System.out.println("ERROR: Hay repetidas");
						repetidas = true;
						break;
					}
				}
				if(repetidas)
				{
					break;
				}
			}
			}
			//Comprueba que no haya ceros
			if(todosNumeros)
			{
				for(int x = 0; x < posicionesSeparadas.length; x++)
				{
					if(Math.abs(Integer.valueOf(posicionesSeparadas[x]))==0||!Comprobador.numeroEntreValorGyP(Math.abs(Integer.valueOf(posicionesSeparadas[x])), tamanoMano, 1))
					{
						posicionEnMano = false;
						System.out.println("ERROR: hay un numero fuera del rango de tu mano");
					}
				}
			}	
			if(todosNumeros&&!repetidas&&posicionEnMano)
			{
				return true;
			}
		}
		
		return false;
	}
	
	//Devuelve true si la carta está en la mano seleccionada
	public static boolean cartaEnMano(Carta cartaComprobar, Mano manoComprobar)
	{
		int idCarta = cartaComprobar.idCarta;
		for(int x = 0; x < manoComprobar.manoJugador.size(); x++)
		{
			if(manoComprobar.manoJugador.get(x).idCarta == idCarta)
			{
				return true;
			}
		}
		return false;
	}
	//Setear seed
	public static int setearSeed(Scanner entrada)
	{
		Random rand = new Random();
		boolean respuestaValida = false;
		String respuesta = "";
		System.out.println("\n¿Quieres usar seed? [Y/N]\n");
		do {
			respuesta = entrada.nextLine();
			respuesta = respuesta.toUpperCase();
			if(respuesta.contains("Y")||respuesta.contains("N"))
			{
				respuestaValida = true;
			}
			else
			{
				System.err.println("ERROR: introduce formato válido");
			}
			}while(respuestaValida == false);
		if(respuesta.contains("Y"))
		{
			System.out.print("\nIntroduce la semilla: ");
			int seed = entrada.nextInt();
			entrada.nextLine();
			return seed;
		}
		return rand.nextInt(0000001, 2000000);
	}
	//Devuelve true si el numero está entre los dos numeros y false si no lo está
	public static boolean numeroEntreValorGyP(int num, int topeGrande, int topePeque)
	{
		if(topePeque<=num && num<=topeGrande)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Devuelve true si el numero es mayor que 0
	public static boolean numeroMayorCero(int num)
	{
		if(num > 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//Devuleve los numeros jugados de una mano
	public static ArrayList<Integer> numerosEnMano(Mano mano)
	{
		ArrayList<Integer> numerosJugados = new ArrayList<Integer>();
		for(int x = 0; x<mano.manoJugador.size(); x++)
		{
			numerosJugados.add(mano.manoJugador.get(x).numero);
		}
		return numerosJugados;
	}
	
}
