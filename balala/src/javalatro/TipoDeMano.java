package javalatro;


public class TipoDeMano
{
	int nivel;
	int idMano;
	int fichasBase;
	int multBase;
	int fichasActuales;
	int multActual;
	int fichasSubidaNivel;
	int multSubidaNivel;
	String nombre;
	public TipoDeMano()
	{
		this.nivel = 1;
	}
	//Subir de nivel mano
	public void subirNivelMano(int numeroVeces)
	{
		for(int x = 0; x < numeroVeces; x++)
		{
			this.nivel = this.nivel +1;
			this.fichasActuales = this.fichasActuales + this.fichasSubidaNivel;
			this.multActual = this.multActual + this.multSubidaNivel;
		}
	}
	//Bajar de nivel mano
		public void bajarNivelMano(int numeroVeces)
		{
			for(int x = 0; x < numeroVeces; x++)
			{
				this.nivel = this.nivel -1;
				this.fichasActuales = this.fichasActuales - this.fichasSubidaNivel;
				this.multActual = this.multActual - this.multSubidaNivel;
			}
		}
	
}

class Color5 extends TipoDeMano
{
	public Color5()
	{
		this.nombre = "Color 5";
		this.fichasBase = 160;
		this.multBase = 16;
		this.idMano = 1;
		this.fichasSubidaNivel = 50;
		this.multSubidaNivel = 3;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class Poker extends TipoDeMano
{
	public Poker()
	{
		this.nombre = "Poker ";
		this.fichasBase = 60;
		this.multBase = 7;
		this.idMano = 5;
		this.fichasSubidaNivel = 30;
		this.multSubidaNivel = 3;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class Trio extends TipoDeMano
{
	public Trio()
	{
		this.nombre = "Trío ";
		this.fichasBase = 30;
		this.multBase = 3;
		this.idMano = 9;
		this.fichasSubidaNivel = 20;
		this.multSubidaNivel = 2;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class Pareja extends TipoDeMano
{
	public Pareja()
	{
		this.nombre = "Pareja ";
		this.fichasBase = 10;
		this.multBase = 2;
		this.idMano = 11;
		this.fichasSubidaNivel = 15;
		this.multSubidaNivel = 1;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class CartaAlta extends TipoDeMano
{
	public CartaAlta()
	{
		this.nombre = "Carta Alta ";
		this.fichasBase = 5;
		this.multBase = 1;
		this.idMano = 12;
		this.fichasSubidaNivel = 10;
		this.multSubidaNivel = 1;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class Color extends TipoDeMano
{
	public Color()
	{
		this.nombre = "Color";
		this.fichasBase = 35;
		this.multBase = 4;
		this.idMano = 7;
		this.fichasSubidaNivel = 15;
		this.multSubidaNivel = 2;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class FullHouse extends TipoDeMano
{
	public FullHouse()
	{
		this.nombre = "Full House ";
		this.fichasBase = 40;
		this.multBase = 4;
		this.idMano = 6;
		this.fichasSubidaNivel = 25;
		this.multSubidaNivel = 2;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class DoblePareja extends TipoDeMano
{
	public DoblePareja()
	{
		this.nombre = "Doble Pareja ";
		this.fichasBase = 20;
		this.multBase = 2;
		this.idMano = 10;
		this.fichasSubidaNivel = 20;
		this.multSubidaNivel = 1;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class ColorCasa extends TipoDeMano
{
	public ColorCasa()
	{
		this.nombre = "Color Casa ";
		this.fichasBase = 140;
		this.multBase = 14;
		this.idMano = 2;
		this.fichasSubidaNivel = 40;
		this.multSubidaNivel = 4;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class Escalera extends TipoDeMano
{
	public Escalera()
	{
		this.nombre = "Escalera ";
		this.fichasBase = 30;
		this.multBase = 4;
		this.idMano = 8;
		this.fichasSubidaNivel = 30;
		this.multSubidaNivel = 3;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class EscaleraReal extends TipoDeMano
{
	public EscaleraReal()
	{
		this.nombre = "Escalera de Color de Reyes de la Realeza ";
		this.fichasBase = 100;
		this.multBase = 8;
		this.idMano = 4;
		this.fichasSubidaNivel = 40;
		this.multSubidaNivel = 4;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class EscaleraColor extends TipoDeMano
{
	public EscaleraColor()
	{
		this.nombre = "Escalera de Color";
		this.fichasBase = 100;
		this.multBase = 8;
		this.idMano = 4;
		this.fichasSubidaNivel = 40;
		this.multSubidaNivel = 4;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class Poker5 extends TipoDeMano
{
	public Poker5()
	{
		this.nombre = "Poker 5 ";
		this.fichasBase = 120;
		this.multBase = 12;
		this.idMano = 2;
		this.fichasSubidaNivel = 35;
		this.multSubidaNivel = 3;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}
class Nada extends TipoDeMano
{
	public Nada()
	{
		this.nombre = " ";
		this.fichasBase = 0;
		this.multBase = 0;
		this.idMano = 0;
		this.fichasSubidaNivel = 0;
		this.multSubidaNivel = 0;
		this.fichasActuales = this.fichasBase;
		this.multActual = this.multBase;
	}
}