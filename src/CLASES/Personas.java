package CLASES;

public class Personas {

	String name, direction;
	int ID,RUC;
	
	public Personas(String name, String direction, int iD, int rUC) {
		this.name = name;
		this.direction = direction;
		ID = iD;
		RUC = rUC;
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

	public int getRUC() {
		return RUC;
	}
	
	
	
}
