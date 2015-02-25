
public class Plate {
	private char plate[];
	private int maxLength;


	Plate(char plate []) {
		maxLength=8;
		int fieldLetter=0;
		int fieldNumber=0;
		if(plate.length==maxLength)
		{
			if(plate[2]=='-'&& plate[5]=='-')
			{

				for(int i= 0; i < maxLength; i=i+3)
				{
					if('A'<=plate[i] && 'Z'>=plate[i])// for first char of the field
						if('A'<=plate[i+1] && 'Z'>=plate[i+1]){//for second char of the field
							fieldLetter++;
						}
						else if('0'<=plate[i] && '9'>=plate[i])
							if('0'<=plate[i+1] && '9'>=plate[i+1])
							{
								fieldNumber++;
							}		

				}
				if(fieldLetter == 1 && fieldNumber==2)
				{
					System.out.println("Valid Plate!");	
				}

			}
			else{
				System.out.println("Wrong sintax!!");
			}
		}else{
			System.out.println("Wrong plate length");
		}

	}

	public char[] getPlate() {
		return plate;
	}

	public void setPlate(char[] plate) {
		this.plate = plate;
	}

	public char[] getField(int nField){
		char field[]= new char[2];
		if(nField>=1 && nField<=3)
		{
			int index = nField -1;

			field[0]=plate[3*index];
			field[0]=plate[3*index+1];
			return field;

		}
		return null;
	}




}
