package dataSystem;

public class Owner {
	
	private String name;
	private Plate plate;
	
	public Owner(String name, Plate plate)
	{
		this.name=name;
		this.plate=plate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Plate getPlate() {
		return plate;
	}
	public void setPlate(Plate plate) {
		this.plate = plate;
	}
	

}
