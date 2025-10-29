package javalatro;
import java.util.ArrayList;
public class Baraja
{
	public int idBaraja = 0;
	public ArrayList<Carta> baraja = new ArrayList<Carta>();
	public int longitud = 0;
	public String nombre = "";
	//Devuelve true si el atributo robada de todas las cartas es true
	public boolean todaRobada()
	{
		for(int x = 0; x < this.baraja.size(); x++)
		{
			if(this.baraja.get(x).robada == false)
			{
				return false;
			}
		}
		return true;
	}
	//Constructor baraja
	public Baraja(int indexBaraja)
	{
		this.idBaraja = indexBaraja;
		int contadorId = 0;
		switch(indexBaraja)
		{
			//Baraja roja
			case 1:				
			this.nombre = "Baraja Roja";
			//Iniciar valores posibles
			String[] palosPosiblesRoja = {"♠","♥","♣","♦"};
			int[]valoresPosiblesRoja = {2,3,4,5,6,7,8,9,10,11,12,13,14};
			for(int i = 0; i < palosPosiblesRoja.length;i++)
			{
				for(int x = 0; x < valoresPosiblesRoja.length; x++)
				{
					this.baraja.add(new Carta(valoresPosiblesRoja[x],palosPosiblesRoja[i]));
					this.baraja.getLast().idCarta = contadorId;
					this.longitud++;
					contadorId++;
				}
			}
			break;
			case 2:				
				this.nombre = "Baraja Azul";
				//Iniciar valores posibles
				String[] palosPosiblesAzul = {"♠","♥","♣","♦"};
				int[]valoresPosiblesAzul = {2,3,4,5,6,7,8,9,10,11,12,13,14};
				for(int i = 0; i < palosPosiblesAzul.length;i++)
				{
					for(int x = 0; x < valoresPosiblesAzul.length; x++)
					{
						this.baraja.add(new Carta(valoresPosiblesAzul[x],palosPosiblesAzul[i]));
						this.baraja.getLast().idCarta = contadorId;
						this.longitud++;
						contadorId++;
					}
				}
				break;
			//Baraja abandonada
			case 3:
				String[] palosPosiblesAbandonada = {"♠","♥","♣","♦"};
				int[]valoresPosiblesAbandonada = {2,3,4,5,6,7,8,9,10,14};
				this.nombre ="Baraja abandonada";
				for(int i = 0; i < palosPosiblesAbandonada.length;i++)
				{
					for(int x = 0; x < valoresPosiblesAbandonada.length; x++)
					{
						this.baraja.add(new Carta(valoresPosiblesAbandonada[x],palosPosiblesAbandonada[i]));
						this.baraja.getLast().idCarta = contadorId;
						this.longitud++;
						contadorId++;
					}
				}
				break;
			//Baraja bicolor
			case 4:
				String[] palosPosiblesBi = {"♠","♥","♠","♥"};
				int[]valoresPosiblesBi = {2,3,4,5,6,7,8,9,10,14};
				this.nombre ="Baraja abandonada";
				for(int i = 0; i < palosPosiblesBi.length;i++)
				{
					for(int x = 0; x < valoresPosiblesBi.length; x++)
					{
						this.baraja.add(new Carta(valoresPosiblesBi[x],palosPosiblesBi[i]));
						this.baraja.getLast().idCarta = contadorId;
						this.longitud++;
						contadorId++;
					}
				}
				break;
			//Baraja chiquitita
			case 5:
				String[] palosPosiblesChiqui = {"♠","♠"};
				int[]valoresPosiblesChiqui = {2,3,4,5,6};
				this.nombre ="Baraja chiquita (debug)";
				for(int i = 0; i < palosPosiblesChiqui.length;i++)
				{
					for(int x = 0; x < valoresPosiblesChiqui.length; x++)
					{
						this.baraja.add(new Carta(valoresPosiblesChiqui[x],palosPosiblesChiqui[i]));
						this.baraja.getLast().idCarta = contadorId;
						this.longitud++;
						contadorId++;
					}
				}
				break;
			case 0:
				this.nombre = "Baraja Vacía";
				break;
			
		}
	}
	//Debuelve la carta en una posicion (contando desde 0)
	public Carta getCartaBaraja(Baraja baraja, int posicion)
	{
		
		if(Comprobador.numeroEntreValorGyP(posicion, this.longitud-1, 0))
		{
			return this.baraja.get(posicion);
		}
		else
		{
			System.err.println("ERROR: getCartaBaraja carta a comprobar "+posicion);
		}
		return baraja.baraja.get(0);
	}
	//Borra una carta de la baraja
	public void destruirCarta(int index)
	{
		if(Comprobador.numeroEntreValorGyP(index, this.longitud-1, 0))
		{
		this.baraja.remove(index);
		this.longitud--;
		}
		else
		{
			System.err.println("ERROR");
		}
	}
	//Cambiar las cartas a no robadas
	public void devolverCartasABaraja()
	{
		for(int x = 0; x< this.baraja.size(); x++)
		{
			this.baraja.get(x).robada = false;
		}
	}
	//Mete una carta especificada
	public void meterCarta(int valor, String palo)
	{
		Carta cartaMeter = new Carta(valor, palo);
		int nuevoId = this.baraja.getLast().idCarta;
		cartaMeter.idCarta = nuevoId+1;
		this.baraja.add(cartaMeter);
		this.longitud++;
	}
}
