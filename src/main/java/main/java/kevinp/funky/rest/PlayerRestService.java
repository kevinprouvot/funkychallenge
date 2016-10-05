package main.java.kevinp.funky.rest;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This end point allows the user to move his hero. We will be using GET
 * parameter to specify the orientation.
 * 
 * @author knprouvot
 */
@WebServlet("/v1/player/move")
public class PlayerRestService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.getWriter().println("Player end point");
	}
}
