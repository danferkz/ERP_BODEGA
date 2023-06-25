package FRAMES_VENTANAS;

import java.util.Hashtable;

public class Productos {
	
	int cod, cant;
	String nombre;
	double price;
	private Hashtable<Integer, Productos> listaproducts;
	
	public Productos(int cod, int cant, String nombre, double price) {
		this.cod = cod;
		this.cant = cant;
		this.nombre = nombre;
		this.price = price;
	} 
	
	public Productos() {
		listaproducts = new Hashtable<>();
		listaproducts.put(1, new Productos(01,10,"Manzana",2.5));
	}
	
	public Hashtable<Integer, Productos> getHashTable(){
		return listaproducts; 
	}
	
	
}
	
	

