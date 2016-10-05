package main.java.kevinp.funky.rest;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This end point allows a third party to get battle status based on his id. it
 * will also provide battle result if the battle is over
 * 
 * @author knprouvot
 */
@WebServlet("/v1/battle/status/get")
public class BattleStatusRestService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.getWriter().println("Battle Status end point");
	}

}
