package GestionFicherosApp;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros {

	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		this.carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	private void actualiza() {
		String[] ficheros = carpetaDeTrabajo.list();
		filas = ficheros.length / columnas;
		if (filas * columnas < ficheros.length) {
			filas++;
		}

		contenido = new String[filas][columnas];
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {
		if(carpetaDeTrabajo.getParent() != null) {
			carpetaDeTrabajo = new File(carpetaDeTrabajo.getParent());
			actualiza();
		}
	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File d = new File(carpetaDeTrabajo, arg0);
		//Excepciones
		if (!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No se ha podido crear la carpeta: no tienes permisos");
		}else if(!carpetaDeTrabajo.exists()) {
			throw new GestionFicherosException("No se ha podido crear la carpeta: no existe la carpeta padre");
		//Accion
		}else {
			d.mkdir();
			actualiza();
		}

	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		File f = new File(carpetaDeTrabajo, arg0);
		//Accion
		try {
			f.createNewFile();
			actualiza();
		//Excepciones
		} catch (IOException e) {
			if (!carpetaDeTrabajo.canWrite()) {
				throw new GestionFicherosException("No se ha podido crear el archivo: no tienes permisos");
			}else if(!carpetaDeTrabajo.exists()) {
				throw new GestionFicherosException("No se ha podido crear el archivo: no existe la carpeta padre");
			}

		}

	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		File d = new File(carpetaDeTrabajo, arg0);
		//Excepciones
		if (!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No se ha podido eliminar el Archivo: no tienes permisos");
		}else if(!carpetaDeTrabajo.exists()) {
			throw new GestionFicherosException("No se ha podido eliminar el Archivo: no existe la carpeta padre");
		}else if(!d.exists()) {
			throw new GestionFicherosException("No se ha podido eliminar el Archivo: no existe el archivo");
		//Accion
		}else {
			d.delete();
			actualiza();
		}

	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File f = new File(carpetaDeTrabajo, arg0);
		if(!f.exists()) {
			f = new File(carpetaDeTrabajo, arg0);
			throw new GestionFicherosException("ERROR: "+f+" no existe");
		}
		if(!f.isDirectory()) {
			f = new File(carpetaDeTrabajo, arg0);
			throw new GestionFicherosException("ERROR: "+f+" no es un directorio");
		}
		if(!f.canRead()) {
			f = new File(carpetaDeTrabajo, arg0);
			throw new GestionFicherosException("ERROR: No tienes acceso a "+f);
		}
		carpetaDeTrabajo = f;
		actualiza();

	}

	@Override
	public int getColumnas() {
		return this.columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO getEspacioDisponibleCarpetaTrabajo
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO getEspacioTotalCarpetaTrabajo
		return null;
	}

	@Override
	public int getFilas() {
		return this.filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		File f = new File(carpetaDeTrabajo, arg0);
		if (!f.exists()) {																	//Excepciones Si:
			throw new GestionFicherosException("ERROR: El archivo no existe");				//El archivo no existe
		}
		if (!f.canRead()) {
			throw new GestionFicherosException("ERROR: No tienes permisos para hacer eso");	//El usuario no tiene permisos
		}
		StringBuilder sb = new StringBuilder();
		Date d = new Date(f.lastModified());												//Se crea una variable Date con la ultima vez que se modifico el fichero
		sb.append("Nombre: "+f.getName()+"\n");
		if (f.isFile()) {
			sb.append("Tipo: Fichero \n");
			sb.append("Informacion unica de Ficheros:\n");
			sb.append("  |Tamaño: "+(f.getTotalSpace()/8)+" bytes\n");
		}else {
			sb.append("Tipo: Directorio \n");
			sb.append("Informacion unica de Directorios:\n");
			sb.append("  |Numero de Elementos: "+f.list().length+"\n");
			sb.append("  |Espacio libre: "+(f.getFreeSpace()/1000000000)+"GB\n");
			sb.append("  |Espacio disponible: "+(f.getUsableSpace()/1000000000)+"GB\n");
			sb.append("  |Espacio total: "+(f.getTotalSpace()/1000000000)+"GB\n");
		}
		sb.append("Ubicacion: "+f.getAbsoluteFile()+"\n");
		sb.append("Ultima vez modificado: "+d.toString()+"\n");								//Se usa el Date creado anteriormente para imprimir la ultima modificacion
		sb.append("Esta oculto? "+(f.isHidden() ? "Si" : "No")+"\n");						//Se usa un If corto para decir si el archivo esta oculto o no
		return sb.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO getMostrarOcultos
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO getTituloColumnas
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0) throws GestionFicherosException {
		// TODO getUltimaModificacion
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO nomRaiz
		return null;
	}

	@Override
	public int numRaices() {
		// TODO numRaices
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		File d = new File(carpetaDeTrabajo, arg0);
		//Excepciones
		if (!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("No se ha podido cambiar el nombre del Archivo: no tienes permisos");
		}else if(!carpetaDeTrabajo.exists()) {
			throw new GestionFicherosException("No se ha podido cambiar el nombre del Archivo: no existe la carpeta padre");
		//Accion
		}else {
			File f = new File(carpetaDeTrabajo, arg1);
			d.renameTo(f);
			actualiza();
		}

	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO sePuedeEjecutar
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO sePuedeEscribir
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO sePuedeLeer
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		this.columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File f = new File(arg0);
		if(!f.exists()) {
			throw new GestionFicherosException("ERROR: "+f+" no existe");
		}
		if(!f.isDirectory()) {
			throw new GestionFicherosException("ERROR: "+f+" no es un directorio");
		}
		if(!f.canRead()) {
			throw new GestionFicherosException("ERROR: No tienes acceso a "+f);
		}
		carpetaDeTrabajo = f;
		actualiza();
	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO setFormatoContenido

	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO setMostrarOcultos

	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO setOrdenado

	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO setSePuedeEjecutar

	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO setSePuedeEscribir

	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1) throws GestionFicherosException {
		// TODO setSePuedeLeer

	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1) throws GestionFicherosException {
		// TODO setUltimaModificacion

	}

}
