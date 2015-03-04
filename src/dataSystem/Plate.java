package dataSystem;

public class Plate {
	private String plate;


	public Plate(String plate) {
		this.plate=plate;
	}
	
	

	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}



	public char[] getField(int nField){
		char field[]= new char[2];
		if(nField>=1 && nField<=3)
		{
			int index = nField -1;

			//field[0]=plate[3*index];
			//field[1]=plate[3*index+1];
			
			return field;

		}
		return null;
	}




}
