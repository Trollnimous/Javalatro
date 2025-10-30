package javalatro;	
import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Juego
{
	public static void main(String[] args)  throws Exception 
	{	//Setear formato UTF8 en la consola
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
		Scanner entrada = new Scanner(System.in);
		//Menu
		Menu menuJuego = new Menu();	
		menuJuego.menuPrincipal(entrada);
		entrada.close();
		
		
	}

}
