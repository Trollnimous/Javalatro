package javalatro;
import java.util.ArrayList;
import java.util.Collections;
public class ComprobadorManoSeleccionada
{
	TipoDeMano color5; 
	TipoDeMano colorCasa;
	TipoDeMano poker5;
	TipoDeMano escaleraReal; 
	TipoDeMano escaleraColor; 
	TipoDeMano poker; 
	TipoDeMano fullHouseObj; 
	TipoDeMano color;
	TipoDeMano escaleraObj;
	TipoDeMano trio; 
	TipoDeMano doblePareja;
	TipoDeMano pareja;
	TipoDeMano cartaAlta ;
	TipoDeMano nada;
	public ComprobadorManoSeleccionada()
	{
	//Inicializar manos
		this.color5 = new Color5();
		this.colorCasa = new ColorCasa();
		this.poker5 = new Poker5();
		this.escaleraReal = new EscaleraReal();
		this.escaleraColor = new EscaleraColor();
		this.poker = new Poker();
		this.fullHouseObj = new FullHouse();
		this.color = new Color();
		this.escaleraObj = new Escalera();
		this.trio = new Trio();
		this.doblePareja = new DoblePareja();
		this.pareja = new Pareja();
		this.cartaAlta = new CartaAlta();
		this.nada = new Nada();
	}
	
	public TipoDeMano seleccionarManoJugada(Mano manoSeleccionada)
	{
		
		if(manoSeleccionada.manoJugador.size()==0)
		{
			return this.nada;
		}
		ArrayList<String> palosJugados = new ArrayList<String>();
		ArrayList<Integer> numerosJugados = new ArrayList<Integer>();
		ArrayList<Integer> frecuenciasNumeros = new ArrayList<Integer>();

		
		
		//Iniciar valores jugados
		for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
		{
			palosJugados.add(manoSeleccionada.getCartaMano(x).palo);
		}
		for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
		{
			numerosJugados.add(manoSeleccionada.getCartaMano(x).numero);
		}
		
		

		//Ordenarlos
		Mano manoSeleccionadaDummy = new Mano(manoSeleccionada.manoJugador.size());
		for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
		{
			manoSeleccionadaDummy.manoJugador.add(manoSeleccionada.getCartaMano(x));
		}
		Mano.ordenarManoNumero(manoSeleccionadaDummy);

		
		//Comprobacion carta alta
		boolean numerosDiferentes = true;
		int cartaAltaUltimoNumero= numerosJugados.get(0);						
		for(int x = 1; x < numerosJugados.size(); x++)
		{
			if(cartaAltaUltimoNumero == numerosJugados.get(x))
			{
				numerosDiferentes = false;
			}
			cartaAltaUltimoNumero = numerosJugados.get(x);
		}
		//Comprobar escalera
		boolean escalera = false;
		if(numerosDiferentes && numerosJugados.size() == 5)
		{
			Collections.sort(numerosJugados);
			int tamano = numerosJugados.size();
			boolean breakeado = false;
			
			for(int x = 0; x < tamano-1; x++)
			{
				int numeroActual = numerosJugados.get(x);
				int numeroSiguiente = numerosJugados.get(x+1);

				if(numeroActual+1 != numeroSiguiente)
				{
					breakeado = true;
					break;
				}
			}
			if(!breakeado)
			{
				escalera = true;
			}
			if(numerosJugados.get(0)==2&&numerosJugados.get(1)==3&&numerosJugados.get(2)==4&&numerosJugados.get(3)==5&&numerosJugados.get(4)==14)
			{
				escalera = true;
			}
			
		}
		
		//Comprobar palo
		boolean mismoPalo = true;
		String ultimoPalo = palosJugados.get(0);
		String paloComprobar = "";
		for(int x = 0; x < manoSeleccionadaDummy.manoJugador.size(); x++)
		{
			paloComprobar = palosJugados.get(x);
			if(!ultimoPalo.contains(paloComprobar))
			{
				mismoPalo = false;
				break;
			}
			ultimoPalo = paloComprobar;
		}
		
		//Comprobacion escalera real
				if(mismoPalo && numerosJugados.size()==5)
				{
					Collections.sort(numerosJugados);
					if(numerosJugados.get(0)==10&&numerosJugados.get(1)==11&&numerosJugados.get(2)==12&&numerosJugados.get(3)==13&&numerosJugados.get(4)==14)
					{
						return this.escaleraReal;
					}
				}
		
		//Comprobar frecuencia de cada numero
		boolean fullHouse = false;
		boolean doublePair = false;
		for(int x = 0; x < numerosJugados.size(); x++)
		{
		int frecuencia = Collections.frequency(numerosJugados, numerosJugados.get(x));
		frecuenciasNumeros.add(frecuencia);
		}
		
		//Ver frecuencia maxima
		int frecMax = 0;
		for(int x = 0; x < frecuenciasNumeros.size(); x++)
		{
			if(frecuenciasNumeros.get(x)> frecMax)
			{
				frecMax = frecuenciasNumeros.get(x);
			}
		}
		//Comprobar fullHouse o Doble pareja
		if(frecMax != 5 && frecMax != 4)
		{
			if(frecMax == 3&&Collections.frequency(frecuenciasNumeros,2)==2)
			{
				fullHouse= true;
			}
			if(frecMax == 2&&Collections.frequency(frecuenciasNumeros, 2)==4)
			{
				doublePair = true;
			}
		}
		
		
		
		
		
		//Color 5
		if(mismoPalo && frecMax == 5)
		{
			return this.color5;
		}
		//Flush house
		if(mismoPalo && fullHouse)
		{
			return this.colorCasa;
		}
		
		//Poker 5
		if(frecMax == 5 && !mismoPalo)
		{
			return this.poker5;
		}
		//Escalera de color
		if(mismoPalo && escalera)
		{
			return this.escaleraColor;
		}
		//Poker
		if(frecMax == 4)
		{
			return this.poker;
		}
		//Full house
		if(fullHouse && !mismoPalo)
		{
			return this.fullHouseObj;
		}
		//Color
		if(mismoPalo &&manoSeleccionadaDummy.manoJugador.size()==5)
		{
			return this.color;
		}
		//Escalera
		if(escalera && !mismoPalo)
		{
			return this.escaleraObj;
		}
		//Trio
		if(frecMax == 3&&!fullHouse)
		{
			return this.trio;
		}
		//Double pair
		if(frecMax == 2 && doublePair)
		{
			return this.doblePareja;
		}
		//Pareja
		if(frecMax == 2 && !doublePair)
		{
			return this.pareja;
		}
		//Por defecto se devuelve carta
		else
		{
			return this.cartaAlta;
		}
		
	}
	public Mano sacarManoValida(Mano manoSeleccionada, TipoDeMano tipoDeMano)
	{
		//Las manos de cinco cartas devuelven la mano entera
		if(tipoDeMano instanceof EscaleraColor || tipoDeMano instanceof Color5||tipoDeMano instanceof ColorCasa||tipoDeMano instanceof Poker5||tipoDeMano instanceof EscaleraReal|| tipoDeMano instanceof FullHouse|| tipoDeMano instanceof Color || tipoDeMano instanceof Escalera)
		{
			
			return manoSeleccionada;
		}
		//Devolver poker
		if(tipoDeMano instanceof Poker)
		{
			int valor1 = 0;
			int valor2 = 0;
			int valorCartaPoker = 0;
			//Obtenemos los dos valores
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(valor1 != manoSeleccionada.getCartaMano(x).numero && valor1 == 0)
				{
					valor1 = manoSeleccionada.getCartaMano(x).numero;
				}
				if(valor2 != manoSeleccionada.getCartaMano(x).numero && valor2 == 0)
				{
					valor2 = manoSeleccionada.getCartaMano(x).numero;
				}
				
			}
			ArrayList<Integer> numerosJugados = Comprobador.numerosEnMano(manoSeleccionada);

			int frecuenciaVal1 = Collections.frequency(numerosJugados, valor1);
			int frecuenciaVal2 = Collections.frequency(numerosJugados, valor2);
			
			if(frecuenciaVal1 > frecuenciaVal2)
			{
				valorCartaPoker = valor1;
			}
			else
			{
				valorCartaPoker = valor2;
			}
			Mano manoDevolver = new Mano(4);
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(manoSeleccionada.getCartaMano(x).numero == valorCartaPoker)
				{
					manoDevolver.manoJugador.add(manoSeleccionada.getCartaMano(x));
				}
			}
			return manoDevolver;
		}
		//Devolver trio
		if(tipoDeMano instanceof Trio)
		{
			int valor1 = 0;
			int valor2 = 0;
			int valor3 = 0;
			int valorCartaTrio = 0;
			//Obtenemos los tres valores
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(valor1 == 0)
				{
					valor1 = manoSeleccionada.getCartaMano(x).numero;
				}
				else
				if(manoSeleccionada.getCartaMano(x).numero!=valor1&&valor2 == 0)
				{
					valor2 = manoSeleccionada.getCartaMano(x).numero;
				}
				if(manoSeleccionada.getCartaMano(x).numero!=valor1&&manoSeleccionada.manoJugador.get(x).numero != valor2&&valor3 == 0)
				{
					valor3 = manoSeleccionada.getCartaMano(x).numero;
				}
				
			}
			ArrayList<Integer> numerosJugados = Comprobador.numerosEnMano(manoSeleccionada);

			int frecuenciaVal1 = Collections.frequency(numerosJugados, valor1);
			int frecuenciaVal2 = Collections.frequency(numerosJugados, valor2);
			int frecuenciaVal3 = Collections.frequency(numerosJugados, valor3);

			
			if(frecuenciaVal1 > frecuenciaVal2&&frecuenciaVal1 > frecuenciaVal3)
			{
				valorCartaTrio = valor1;
			}
			else if(frecuenciaVal2 > frecuenciaVal1&&frecuenciaVal2 > frecuenciaVal3)
			{
				valorCartaTrio = valor2;
			}
			else if(frecuenciaVal3 > frecuenciaVal1&&frecuenciaVal3 > frecuenciaVal1)
			{
				valorCartaTrio = valor3;
			}
			Mano manoDevolver = new Mano(3);
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(manoSeleccionada.getCartaMano(x).numero == valorCartaTrio)
				{
					manoDevolver.manoJugador.add(manoSeleccionada.getCartaMano(x));
				}
			}
			return manoDevolver;
		}
		//Devolver doble pareja
		if(tipoDeMano instanceof DoblePareja)
		{
			int valor1 = 0;
			int valor2 = 0;
			int valor3 = 0;
			int valorCartaDoble1 = 0;
			int valorCartaDoble2 = 0;
			//Obtenemos los cuatro valores
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(valor1 != manoSeleccionada.getCartaMano(x).numero&&valor1 == 0)
				{
					valor1 = manoSeleccionada.getCartaMano(x).numero;
				}
				if(valor1 !=manoSeleccionada.getCartaMano(x).numero&&valor2 == 0)
				{
					valor2 = manoSeleccionada.getCartaMano(x).numero;
				}
				if(valor1 != manoSeleccionada.getCartaMano(x).numero&&valor2 !=manoSeleccionada.getCartaMano(x).numero&&valor3 == 0)
				{
					valor3 = manoSeleccionada.getCartaMano(x).numero;
				}		
			}
			ArrayList<Integer> numerosJugados = Comprobador.numerosEnMano(manoSeleccionada);
			
			int frecuenciaVal1 = Collections.frequency(numerosJugados, valor1);
			int frecuenciaVal2 = Collections.frequency(numerosJugados, valor2);
			int frecuenciaVal3 = Collections.frequency(numerosJugados, valor3);
			if(frecuenciaVal1 == frecuenciaVal2)
			{
				valorCartaDoble1 = valor1;
				valorCartaDoble2 = valor2;
			}
			if(frecuenciaVal1 == frecuenciaVal3)
			{
				valorCartaDoble1 = valor1;
				valorCartaDoble2 = valor3;
			}
			if(frecuenciaVal2 == frecuenciaVal3)
			{
				valorCartaDoble1 = valor3;
				valorCartaDoble2 = valor2;
			}
			
			Mano manoDevolver = new Mano(4);
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(manoSeleccionada.getCartaMano(x).numero == valorCartaDoble1||manoSeleccionada.getCartaMano(x).numero == valorCartaDoble2)
				{
					manoDevolver.manoJugador.add(manoSeleccionada.getCartaMano(x));
				}
			}
			return manoDevolver;
		}
		//Devolver pareja
		if(tipoDeMano instanceof Pareja)
		{
			int valor1 = 0;
			int valor2 = 0;
			int valor3 = 0;
			int valor4 = 0;
			int valorCartaPareja = 0;
			//Obtenemos los cuatro valores
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(valor1 != manoSeleccionada.getCartaMano(x).numero&&valor1 == 0)
				{
					valor1 = manoSeleccionada.getCartaMano(x).numero;
				}
				if(valor1 !=manoSeleccionada.getCartaMano(x).numero&&valor2 == 0)
				{
					valor2 = manoSeleccionada.getCartaMano(x).numero;
				}
				if(valor1 != manoSeleccionada.getCartaMano(x).numero&&valor2 !=manoSeleccionada.getCartaMano(x).numero&&valor3 == 0)
				{
					valor3 = manoSeleccionada.getCartaMano(x).numero;
				}
				if(valor1 != manoSeleccionada.getCartaMano(x).numero&&valor2 !=manoSeleccionada.getCartaMano(x).numero&&manoSeleccionada.manoJugador.get(x).numero != valor3&& valor4 == 0)
				{
					valor4 = manoSeleccionada.getCartaMano(x).numero;
				}
						
			}
			ArrayList<Integer> numerosJugados = Comprobador.numerosEnMano(manoSeleccionada);
			int frecuenciaVal1 = Collections.frequency(numerosJugados, valor1);
			int frecuenciaVal2 = Collections.frequency(numerosJugados, valor2);
			int frecuenciaVal3 = Collections.frequency(numerosJugados, valor3);
			int frecuenciaVal4 = Collections.frequency(numerosJugados, valor4);
					
			if(frecuenciaVal1 > frecuenciaVal2&&frecuenciaVal1 > frecuenciaVal3&&frecuenciaVal1 > frecuenciaVal4)
			{
				valorCartaPareja = valor1;
			}
			else if(frecuenciaVal2 > frecuenciaVal1&&frecuenciaVal2 > frecuenciaVal3&&frecuenciaVal2 > frecuenciaVal4)
			{
				valorCartaPareja = valor2;
			}
			else if(frecuenciaVal3 > frecuenciaVal1&&frecuenciaVal3 > frecuenciaVal1&&frecuenciaVal3 > frecuenciaVal4)
			{
				valorCartaPareja = valor3;
			}
			else if(frecuenciaVal4 > frecuenciaVal1&&frecuenciaVal4 > frecuenciaVal2&&frecuenciaVal4 > frecuenciaVal3)
			{
				valorCartaPareja = valor4;
			}
			Mano manoDevolver = new Mano(2);
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(manoSeleccionada.getCartaMano(x).numero == valorCartaPareja)
				{
					manoDevolver.manoJugador.add(manoSeleccionada.getCartaMano(x));
				}
			}
			return manoDevolver;
		}
		//Devolver carta alta
		if(tipoDeMano instanceof CartaAlta)
		{
			int numGrande = 0;
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(manoSeleccionada.manoJugador.get(x).numero > numGrande)
				{
					numGrande = manoSeleccionada.getCartaMano(x).numero;
				}
			}
			Mano manoDevolver = new Mano(1);
			for(int x = 0; x < manoSeleccionada.manoJugador.size(); x++)
			{
				if(manoSeleccionada.manoJugador.get(x).numero == numGrande)
				{
					manoDevolver.manoJugador.add(manoSeleccionada.manoJugador.get(x));
					return manoDevolver;
				}
			}
		}
		//Devolver error
		System.err.println("ERROR: mano no devuelta correctamente");
		return manoSeleccionada;
		
	}
}
