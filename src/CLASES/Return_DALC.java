package CLASES;

import java.util.Hashtable;

public class Return_DALC {

	public Productos_BE accessUniversalHashtable(int key) {
        Hashtable<Integer,Productos_BE > hashtable = Creation.getHashtable();
        
        Productos_BE production = hashtable.get(key);
        return production; 
    }
	
	public Personas accessUniversalHashtable2(int key) {
        Hashtable<Integer,Personas > hashtable = Creation.getHashtable2();
        
        Personas production = hashtable.get(key);
        return production; 
    }

}
