package CLASES;

import java.util.Hashtable;

public class Return_DALC {

	public Productos_BE accessUniversalHashtable(int key) {
        Hashtable<Integer,Productos_BE > hashtable = Creation.getHashtable();
        
        Productos_BE production = hashtable.get(key);
        return production; 
    }
	
	public Proveedores_BE accessUniversalHashtable2(int key) {
        Hashtable<Integer,Proveedores_BE > hashtable = Creation.getHashtable2();
        
        Proveedores_BE production = hashtable.get(key);
        return production; 
    }
    public Clientes_BE accessUniversalHashtable3(int key) {
        Hashtable<Integer,Clientes_BE > hashtable = Creation.getHashtable3();
        
        Clientes_BE production = hashtable.get(key);
        return production; 
    }

}
