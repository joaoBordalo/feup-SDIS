import static org.junit.Assert.*;

import org.junit.Test;


public class ClientTest {

	Client test= new Client();
	
	
	@Test
	public void testGetHost_name() {
		test.setHost_name(12345);
		int host = 12345;
		
		assertEquals(host, test.getHost_name());
	}

	@Test
	public void testSetHost_name() {
		test.setHost_name(12345);
		int host = 12345;
		
		assertEquals(host, test.getHost_name());
	}

	@Test
	public void testGetPort_number() {
		test.setPort_number(8000);
		int port = 8000;
		
		assertEquals(port, test.getPort_number());
	}

	@Test
	public void testSetPort_number() {
		test.setPort_number(8000);
		int port = 8000;
		
		assertEquals(port, test.getPort_number());
	}

	@Test
	public void testGetOper() {
		test.setOper("Register");
		assertEquals("Register", test.getOper());
		
		test.setOper("Lookup");
		assertEquals("Lookup", test.getOper());
		
		test.setOper("failoperator");
		assertNotEquals("failoperator", test.getOper());
		
	}

	@Test
	public void testSetOper() {
		test.setOper("Register");
		assertEquals("Register", test.getOper());
		
		test.setOper("Lookup");
		assertEquals("Lookup", test.getOper());
		
		test.setOper("failoperator");
		assertNotEquals("failoperator", test.getOper());
	}

	@Test
	public void testGetOpnd() {
		String[] testArray = {"um", "dois", "tres"};
		test.setOpnd(testArray);
		
		assertArrayEquals(testArray, test.getOpnd());
	}

	@Test
	public void testSetOpnd() {
		String[] testArray = {"um", "dois", "tres"};
		test.setOpnd(testArray);
		
		assertArrayEquals(testArray, test.getOpnd());
	}

}
