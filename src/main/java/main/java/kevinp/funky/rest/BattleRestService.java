package main.java.kevinp.funky.rest;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kevinp.funky.core.BattleService;

/**
 * This end point allows the user to join a random battle. As soon as the number
 * of expected players is reach. It will start the battle.
 * 
 * @author knprouvot
 */
@WebServlet("/v1/battle/start")
public class BattleRestService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		BattleService.getInstance().join(request.getRemoteAddr());
	}

}
