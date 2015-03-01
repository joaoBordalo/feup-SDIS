
public class Plate {
	private char plate[]=new char[8];
	private int maxLength;


	Plate(char plate []) {
		maxLength=8;
		int fieldLetter=0;
		int fieldNumber=0;
	
		if(plate.length==maxLength)
		{
			
			if(plate[2]=='-'&& plate[5]=='-')
			{
				this.plate[2]='-';
				this.plate[5]='-';
				
				
				for(int i= 0; i < maxLength; i=i+3)
				{
					if('A'<=plate[i] && 'Z'>=plate[i])
					{// for first char of the field
						
						this.plate[i]= plate[i] ;
						if('A'<=plate[i+1] && 'Z'>=plate[i+1])
						{//for second char of the field
							this.plate[i+1]=plate[i+1];
							fieldLetter++;
						}
					}
					else if('0'<=plate[i] && '9'>=plate[i])
					{
						this.plate[i]= plate[i];
						if('0'<=plate[i+1] && '9'>=plate[i+1])
						{
							this.plate[i+1]= plate[i+1] ;
							fieldNumber++;
						}		
					}
				}
				
				if(fieldLetter == 1 && fieldNumber==2)
				{
					System.out.println("Valid Plate!");	
					System.out.println(this.getPlate());
				}
				else
				{
					System.out.println("ups!");
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
			field[1]=plate[3*index+1];
			
			return field;

		}
		return null;
	}




}
