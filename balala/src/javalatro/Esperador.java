package javalatro;

public class Esperador
{

	public static void esperar(long milisegundos)
	{
		try {
		    Thread.sleep(2000); // espera 2000 ms = 2 segundos
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}

}
