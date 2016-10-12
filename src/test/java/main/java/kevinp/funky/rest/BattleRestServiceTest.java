package main.java.kevinp.funky.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

public class BattleRestServiceTest extends Mockito{
	
	private HttpServletRequest player1Request;
	private HttpServletRequest player2Request;
	private HttpServletResponse response;
	private final String player1Ip = "192.168.0.11";
	private final String player2Ip = "192.168.0.12";
	
	@InjectMocks
	private BattleRestService battleRestService;
	
	@Before
	public void setup() {
		
		player1Request = mock(HttpServletRequest.class);
		when(player1Request.getRemoteAddr()).thenReturn(player1Ip);
		
		player2Request = mock(HttpServletRequest.class);
		when(player2Request.getRemoteAddr()).thenReturn(player2Ip);
		
		response = mock(HttpServletResponse.class);
	}
	
	@Test
	public void testRestServlet() throws IOException{
		
		battleRestService.doGet(player1Request, response);
		
		
	}
	
	

}
