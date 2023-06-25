package CLASES;

public class Proveedores_BE {

	String name, direction;
	int ID;
	
	public Proveedores_BE(String name, String direction, int iD) {
		this.name = name;
		this.direction = direction;
		ID = iD;
	
	}

	public String getName() {
		return name;
	}

	public String getDirection() {
		return direction;
	}

	public int getID() {
		return ID;
	}


	
	
}
