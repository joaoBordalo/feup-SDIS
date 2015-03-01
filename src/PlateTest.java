import static org.junit.Assert.*;

import org.junit.Test;


public class PlateTest {

	@Test
	public void testPlate() {
		

		char[] plate = {'A','A','-','0','0','-','0','0'};
		Plate test= new Plate(plate);
		
		char[] plateFail = {'A','A','-','0','0','-','A','A'};
		Plate testFail =new Plate(plateFail);
		
		
		
		
		assertArrayEquals("assertplateTest",plate, test.getPlate());
		assertNotEquals("failplateTest",plateFail, testFail.getPlate());
		
	}

	@Test
	public void testGetPlate() {
		
				
		char[] plate = {'A','A','-','0','0','-','0','0'};
		Plate test= new Plate(plate);
		assertArrayEquals("getplatetest",plate, test.getPlate());
		
	}

	@Test
	public void testSetPlate() {
		char[] plate = {'A','A','-','0','0','-','0','0'};
		Plate test= new Plate(plate);
		
		assertArrayEquals(plate, test.getPlate());
		
		char[] plateNew = {'A','A','-','0','0','-','0','1'};
		
		test.setPlate(plateNew);
		
		assertArrayEquals(plateNew, test.getPlate());
		
		
		
	}

	@Test
	public void testGetField() {
		char[] plate = {'A','A','-','0','0','-','0','0'};
		Plate test= new Plate(plate);
		
		char[] field1={'A','A'};
		char[] field2={'0','0'};
		char[] field3={'0','0'};
		
		assertArrayEquals(field1, test.getField(1));
		assertArrayEquals(field2, test.getField(2));
		assertArrayEquals(field3, test.getField(3));
	}

}
