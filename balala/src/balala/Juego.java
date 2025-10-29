package balala;	
import java.util.Scanner;

public class Juego
{
	public static void main(String[] args)
	{
		Scanner entrada = new Scanner(System.in);
		//Menu
		Menu menuJuego = new Menu();	
		menuJuego.menuPrincipal(entrada);
		entrada.close();
		
		
	}

}
