package CLASES;

import java.util.Hashtable;

public class Creation {

	private static Hashtable<Integer,Productos_BE> p1 = new Hashtable<>();
	
	public static Hashtable<Integer,Productos_BE> getHashtable(){
		return p1; 
	}
	
	public static void addinHashtable(int key, Productos_BE product){
		p1.put(key, product);
	}
	
	public static void removefromHahstable(int key){
		p1.remove(key);
	}
	//----------------------------------------------------------------
	private static Hashtable<Integer,Proveedores_BE> p2 = new Hashtable<>();
	
	public static Hashtable<Integer,Proveedores_BE> getHashtable2(){
		return p2; 
	}
	
	public static void addinHashtable2(int key, Proveedores_BE product){
		p2.put(key, product);
	}
	
	public static void removefromHahstable2(int key){
		p2.remove(key);
	}
	//-------------------------------------------------------------------

	private static Hashtable<Integer,Clientes_BE> p3 = new Hashtable<>();
	
	public static Hashtable<Integer,Clientes_BE> getHashtable3(){
		return p3; 
	}
	
	public static void addinHashtable3(int key, Clientes_BE product){
		p3.put(key, product);
	}
	
	public static void removefromHahstable3(int key){
		p3.remove(key);
	}
	
	
	
}
