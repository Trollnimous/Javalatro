package balala;

public class PerfilJugador
{
	int costeReroll;
	int turnosManosIniciales;
	int descartesIniciales;
	int descartesActuales;
	int turnosManosActuales;
	int dinero;
	int espacioInventario;
	int espacioJokers;
	int probabilidades;
	int tamanoManoInicial;
	int bancarrota;
	public void iniciarRonda()
	{
		this.turnosManosActuales = turnosManosIniciales;
		this.descartesActuales = descartesIniciales;
	}
	public PerfilJugador()
	{
		this.bancarrota = 0;
		this.probabilidades = 1;
		this.costeReroll = 5;
		this.turnosManosIniciales = 4;
		this.descartesIniciales = 3;
		this.dinero = 4;
		this.espacioInventario = 2;
		this.espacioJokers = 5;
		this.tamanoManoInicial = 8;
	}
}
