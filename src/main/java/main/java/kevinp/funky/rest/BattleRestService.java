package main.java.kevinp.funky.rest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
@WebServlet("/v1/battle/join")
public class BattleRestService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(BattleRestService.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		LOGGER.log(Level.INFO, "Player [{0}] attempt to join", request.getRemoteAddr());
		BattleService.getInstance().join(request.getRemoteAddr());
	}

}
