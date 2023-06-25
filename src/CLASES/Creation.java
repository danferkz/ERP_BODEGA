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
	
	private static Hashtable<Integer,Personas> p2 = new Hashtable<>();
	
	public static Hashtable<Integer,Personas> getHashtable2(){
		return p2; 
	}
	
	public static void addinHashtable2(int key, Personas product){
		p2.put(key, product);
	}
	
	public static void removefromHahstable2(int key){
		p2.remove(key);
	}
	
	
	
}
