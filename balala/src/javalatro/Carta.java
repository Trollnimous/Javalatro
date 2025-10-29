package javalatro;

public class Carta
{
	public int idCarta;
	public int valor;
	public int numero;
	public boolean robada;
	public String palo;
	public String nombre;
	public String simboloValor;
	//Constructor de carta
	public Carta(int numero, String palo)
	{
		this.robada = false;
		this.palo = palo;
		this.numero = numero;
		//Comprueba si es un as o figura y da nombre especial
		if(Comprobador.numeroEntreValorGyP(numero, 10, 2))
		{
		this.nombre = Integer.toString(numero)+" de "+palo;
		}
		else
		{
			switch (numero)
			{
				case 11:
					this.nombre = "Jota de "+palo;
					break;
				case 12:
					this.nombre = "Reina de "+palo;
					break;
				case 13:
					this.nombre = "Rey de "+palo;
					break;
				case 14:
					this.nombre = "As de "+palo;
					break;
				default:
					System.err.println("ERROR: comprobando numero "+numero);
					break;
			}
		}
		//Comprobar si el valor es figura o as y da valor especial
		if(Comprobador.numeroEntreValorGyP(numero, 10, 2))
		{
		this.valor = numero;
		}
		else
		{
			switch (numero)
			{
				case 11:
					this.valor = 10;
					break;
				case 12:
					this.valor = 10;
					break;
				case 13:
					this.valor = 10;
					break;
				case 14:
					this.valor = 11;
					break;
				default:
					System.err.println("ERROR: comprobando numero "+numero);
					break;
			}
		}
		//Comprobar si el valor es figura o as y da valor especial
				if(Comprobador.numeroEntreValorGyP(numero, 10, 2))
				{
				this.simboloValor = Integer.toString(numero);
				}
				else
				{
					switch (numero)
					{
						case 11:
							this.simboloValor = "J";
							break;
						case 12:
							this.simboloValor = "Q";
							break;
						case 13:
							this.simboloValor = "K";
							break;
						case 14:
							this.simboloValor = "A";
							break;
						default:
							System.err.println("ERROR: comprobando numero "+numero);
							break;
					}
				}
		
		
	}
	public int obtenerValor()
	{
		return this.valor;
	}
	public int obtenerNumero()
	{
		return this.numero;
	}
	
}
