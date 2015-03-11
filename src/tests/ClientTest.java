package tests;
import dataSystem.*;
import static org.junit.Assert.*;

import org.junit.Test;

import dataSystem.Client;


public class ClientTest {

	Client test= new Client();
	
	


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





}
