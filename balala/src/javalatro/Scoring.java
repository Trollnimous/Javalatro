package javalatro;

public class Scoring
{
	int fichas;
	int mult;
	long scoreMano;
	long scoreTotal;
	public void reiniciarScoreTotal()
	{
		this.scoreTotal = 0;
	}
	public void reiniciarScoreMano()
	{
		this.scoreMano = 0;
	}
	//Calcular score
	public void calcularScore(Mano manoSeleccionada, TipoDeMano tipoDeMano, Mano manoValida)
	{
		this.scoreMano = 0;
		this.fichas = tipoDeMano.fichasActuales;
		this.mult = tipoDeMano.multActual;
		for(int x = 0; x < manoValida.manoJugador.size(); x++)
		{
			this.fichas = this.fichas + manoValida.getCartaMano(x).valor;
		}
		
		this.scoreMano = this.mult * this.fichas;
		this.scoreTotal =  this.scoreTotal + this.scoreMano;
		InterfazGrafica.imprimirScore(this,tipoDeMano,manoValida);
		
	}
}
