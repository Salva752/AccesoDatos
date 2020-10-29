package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionDatos {

	public GestionDatos() {

	}

	public static BufferedReader[] abrirFicheros (String fichero1, String fichero2) throws FileNotFoundException {
		BufferedReader[] br = new BufferedReader[2];
		FileReader fr1 = new FileReader(fichero1);
		BufferedReader br1 = new BufferedReader(fr1);
		br[0] = br1;
		FileReader fr2 = new FileReader(fichero2);
		BufferedReader br2 = new BufferedReader(fr2);
		br[1] = br2;
		return br;
	}

	public static void cerrarFicheros (BufferedReader br1, BufferedReader br2) throws IOException {
		br1.close();
		br2.close();
	}

	public static boolean compararContenido (String fichero1, String fichero2) throws IOException{
		BufferedReader[] br = new BufferedReader[2];
		br = abrirFicheros(fichero1, fichero2);			//Abre los ficheros
		String texto1 = br[0].readLine();				//Se definen los strings que cojeran los valores de las lineas que leen los BufferedReader
		String texto2 = br[1].readLine();
		while (texto1 != null && texto2 != null) {
			if (!texto1.equals(texto2)) {				//Si las dos lineas no son iguales devuelve falso
				return false;
			}else {
				texto1 = br[0].readLine();				//Si las dos lineas son iguales sigue a las siguientes dos lineas
				texto2 = br[1].readLine();
			}
		}
		cerrarFicheros(br[0], br[1]);					//Cierra los ficheros
		return true;

	}

	public static int buscarPalabra (String fichero1, String palabra, boolean primera_aparicion) throws IOException{
		BufferedReader[] br = new BufferedReader[1];			//Abre los ficheros
		br = abrirFicheros(fichero1, fichero1);
		String texto1 = br[0].readLine();
		int linea = 1, lineafinal = -1;
		List<String> palabras = new ArrayList<String>();
		if(primera_aparicion) {									//Si el checkbox esta encendido
			while (texto1 != null) {							//Mientras que no se termine el documento
				palabras = Arrays.asList(texto1.split(" "));	//Esto divide las palabras de la frase
				if(palabras.contains(palabra)) {				//Si contiene la palabra devuelve la linea en la que esta
					return linea;
				}
				texto1 = br[0].readLine();
				linea++;
			}
		}else {													//Si el checkbox no esta encendido
			while (texto1 != null) {							//Mientras que no se termien el documento
				palabras = Arrays.asList(texto1.split(" "));	//Esto divide las palabras de la frase
				if(palabras.contains(palabra)) {				//Si contiene la palabra guarda la linea en la que se encuentra,
					lineafinal = linea;							//haciendo que si en las proximas lineas tambien esta, 
				}												//se sigue actualizando hasta que no vuelva a estar
				texto1 = br[0].readLine();
				linea++;
			}
			return lineafinal;									//Devuelve la ultima linea guardada
		}
		cerrarFicheros(br[0], br[1]);							//Cierra los ficheros
		return -1;
	}	

}
