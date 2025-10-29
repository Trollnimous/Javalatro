package javalatro;

public class ControladorJuego
{
	int situacionAnte;
	public long[] scoreRequerido = {100,300,800,2000,5000,11000,20000,35000,50000,110000,560000,7200000};
	int ante;
	int ronda;
	public ControladorJuego()
	{
		this.situacionAnte = 1;
		this.ante = 1;
		this.ronda = 1;
	}
	public float multiplicadorCiega(int situacionAnte)
	{
		switch (situacionAnte)
		{
			case 1:
				return 1f;
			case 2: 
				return 1.5f;
			case 3:
				return 2f;
				
		}
		return 1;
	}
	public long scoreRequerido()
	{
		long scoreRequerido = (long)(this.scoreRequerido[this.ante] * multiplicadorCiega(this.situacionAnte));
		return scoreRequerido;
	}
	public void subirRonda()
	{
		this.ronda = this.ronda+1;
	}
	public void subirAnte()
	{
		this.ante = this.ante +1;
		System.out.printf("\nPasando a Ante %d\n\n",this.ante);
	}
	public void seguirEnAnte()
	{
		this.situacionAnte = this.situacionAnte+1;
		if(this.situacionAnte > 3)
		{
			this.situacionAnte = 1;
			subirAnte();
		}
	}
}
