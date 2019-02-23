package es.upm.etsisi;

import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.upm.etsisi.entities.omjson.ObservationCollecionTraza;
import es.upm.etsisi.services.GestorOmCollections;

@SpringBootApplication
public class OmJsonAzureApplication implements CommandLineRunner {
	private GestorOmCollections gestorOmCollections;
	private static Scanner sc;
	
	static {
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OmJsonAzureApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String opcionStr;
		do {
			mostrarMenu();
			opcionStr = sc.nextLine();
			switch (opcionStr) {
			case "1":
				generarTrazas();
				break;
			case "2":
				altaTraza();
				break;
			case "3":
				bajaTraza();
				break;
			case "4":
				buscarTraza();
				break;
			case "5":
				listarTrazas();
				break;
			case "0":
				System.out.println("Fin del programa.");
				break;
			default:
				System.out.println("Entrada no valida.");
			}	
		} while(!opcionStr.equals("0"));
	}
	
	/**
	 * Muestra el menú de operaciones por consola.
	 */
	private void mostrarMenu() {
		System.out.println("\nSeleccione una opcion...");
		System.out.println("1. Generar trazas de motas de fichero (motaMeasures.json) a OM-Collections.");
		System.out.println("2. Alta en Azure Cosmos DB de una nueva traza OM-Collection.");
		System.out.println("3. Baja de Azure Cosmos DB de una traza OM-Collection.");
		System.out.println("4. Buscar una traza de OM-Collection en Azure Cosmos DB.");
		System.out.println("5. Listar todas las trazas de OM-Collections de Azure Cosmos DB.");
		System.out.println("0. Salir.");
	}
	
	/**
	 * @throws ParseException
	 * @throws IOException
	 */
	private void generarTrazas() throws ParseException, IOException {
		gestorOmCollections.generateOMJsonFromFile();
	}
	
	/**
	 * Operación para dar de alta una OM-Collection.
	 */
	private void altaTraza() {
		ObservationCollecionTraza omTraza = new ObservationCollecionTraza();
		if(gestorOmCollections.altaOmCollection(omTraza)) {
			System.out.println("Traza dada de alta en la base de datos.");
		} else {
			System.out.println("No se ha podido dar de alta la traza en la base de datos.");
		}
	}
	
	/**
	 * Operación para dar de baja una OM-Collection.
	 */
	private void bajaTraza() {
		System.out.println("Introduzca el id a buscar para dar de baja: ");
		String id = sc.nextLine();
		if(gestorOmCollections.bajaOmCollection(id)) {
			System.out.println("Traza dada de baja de la base de datos.");
		} else {
			System.out.println("No se ha podido dar de baja a la traza en la base de datos.");
		}
	}
	
	/**
	 * Operación para buscar una OM-Collection.
	 */
	private void buscarTraza() {
		System.out.println("Introduzca el id a buscar: ");
		String id = sc.nextLine();
		Optional<ObservationCollecionTraza> omTraza = gestorOmCollections.getOmCollection(id);
		if(omTraza != null) {
			System.out.println("Traza encontrada:");
			System.out.println(omTraza.toString());
		} else {
			System.out.println("La traza con el id " + id + " no se encuentra en la base de datos.");
		}
	}
	
	/**
	 * Operación para listar todas las OM-Collection.
	 */
	private void listarTrazas() {
		Iterable<ObservationCollecionTraza> listaOmCollections = gestorOmCollections.getListaOmCollections();
		if(listaOmCollections != null) {
			System.out.println(listaOmCollections.toString());
		} else {
			System.out.println("Lista de trazas vacia.");
		}
	}

}
