package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Clase utilitaria encargada de la gestion de archivos dentro del sistema
 * GreenBuilding Manager. Permite crear, leer y escribir archivos de texto,
 * manejar archivos serializados y exportar informacion en formato PDF.
 * 
 * Esta clase centraliza las operaciones de persistencia basadas en archivos
 * para facilitar el almacenamiento y recuperacion de informacion del sistema.
 * <br>
 * <b>pre</b> La carpeta principal debe existir o poder ser creada dentro del
 * proyecto. Las rutas de los archivos deben ser validas. <br>
 * <b>post</b> Los archivos son creados, leidos o modificados segun la operacion
 * solicitada. <br>
 * 
 * @author GreenBuilding Group
 * @version 1.0
 */
public class FileHandler {

	/** Archivo utilizado para operaciones de lectura y escritura. */
	private static File archivo;

	/** Objeto utilizado para escribir archivos de texto. */
	private static PrintWriter escritor;

	/** Flujo de salida para archivos serializados. */
	public static FileOutputStream fos;

	/** Flujo de escritura de objetos serializados. */
	public static ObjectOutputStream oos;

	/** Flujo de entrada para archivos serializados. */
	public static FileInputStream fis;

	/** Flujo de lectura de objetos serializados. */
	public static ObjectInputStream ois;

	/**
	 * Crea la carpeta principal donde se almacenaran los archivos del sistema. <br>
	 * <b>pre</b> El sistema debe tener permisos de escritura en el directorio del
	 * proyecto. <br>
	 * <b>post</b> La carpeta "files" queda creada si no existia previamente. <br>
	 */
	public static void crearCarpetaPrincipal() {
		archivo = new File("files");
		if (!archivo.isDirectory() || !archivo.exists()) {
			archivo.mkdir();
		}
	}

	/**
	 * Crea y escribe informacion dentro de un archivo de texto. <br>
	 * <b>pre</b> La ruta del archivo y el contenido no deben ser null. <br>
	 * <b>post</b> El archivo queda creado y actualizado con el contenido recibido.
	 * <br>
	 * 
	 * @param url       Nombre o ruta relativa del archivo de texto. url != null,
	 *                  url != ""
	 * @param contenido Contenido que sera escrito dentro del archivo. contenido !=
	 *                  null
	 */
	public static void crearYEscribirArchivo(String url, String contenido) {
		if (archivo.exists()) {
			try {
				archivo = new File("files/" + url);
				if (!archivo.exists()) {
					archivo.createNewFile();
				}
				escritor = new PrintWriter(archivo);

				escritor.println(contenido);
				escritor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lee el contenido de un archivo de texto. <br>
	 * <b>pre</b> La ruta del archivo no debe ser null. <br>
	 * <b>post</b> Se retorna el contenido almacenado dentro del archivo de texto.
	 * <br>
	 * 
	 * @param url Nombre o ruta relativa del archivo de texto. url != null, url !=
	 *            ""
	 * @return String con el contenido completo del archivo
	 */
	public static String crearYLeerArchivo(String url) {
		StringBuilder sb = new StringBuilder();
		try {
			archivo = new File("files/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			Scanner sc = new Scanner(archivo);
			while (sc.hasNext()) {
				sb.append(sc.nextLine() + "\n");
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

	/**
	 * Crea y escribe un archivo serializado con el objeto recibido. <br>
	 * <b>pre</b> La ruta y el objeto no deben ser null. <br>
	 * <b>post</b> El archivo serializado queda actualizado con el objeto recibido.
	 * <br>
	 * 
	 * @param url       Nombre o ruta relativa del archivo serializado. url != null,
	 *                  url != ""
	 * @param contenido Objeto que sera serializado y almacenado. contenido != null
	 */
	public static void crearYEscribirArchivoSerializado(String url, Object contenido) {
		archivo = new File("files/" + url);
		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			fos = new FileOutputStream(archivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(contenido);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Lee un archivo serializado y retorna el objeto almacenado. <br>
	 * <b>pre</b> La ruta del archivo no debe ser null. <br>
	 * <b>post</b> Se retorna el objeto deserializado o null si el archivo esta
	 * vacio. <br>
	 * 
	 * @param url Nombre o ruta relativa del archivo serializado. url != null, url
	 *            != ""
	 * @return Objeto recuperado desde el archivo serializado
	 */
	public static Object crearYLeerArchivoSerializado(String url) {
		archivo = new File("files/" + url);
		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			if (archivo.length() == 0) {
				return null;
			}
			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);
			Object contenido = ois.readObject();
			ois.close();
			fis.close();
			return contenido;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Exporta informacion en un archivo PDF. <br>
	 * <b>pre</b> La ruta, el titulo y el contenido no deben ser null. <br>
	 * <b>post</b> Se genera un archivo PDF con el titulo y contenido especificados.
	 * <br>
	 * 
	 * @param url       Nombre o ruta relativa del archivo PDF. url != null, url !=
	 *                  ""
	 * @param titulo    Titulo principal del documento PDF. titulo != null
	 * @param contenido Contenido textual del documento PDF. contenido != null
	 */
	public static void exportarPDF(String url, String titulo, String contenido) {
		try {
			archivo = new File("files/" + url);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(archivo));
			doc.open();

			Font fuenteTitulo = new Font(Font.HELVETICA, 16, Font.BOLD);

			doc.add(new Paragraph(titulo, fuenteTitulo));
			doc.add(new Paragraph(contenido));

			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}