package ch.frickler.jass;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.frickler.jass.logic.Game;
import ch.frickler.jass.logic.Player;

@ManagedBean
@SessionScoped
public class WaitingRoomBean {
	long gameId = 0L;

	public long getGameId() {
		// load the gameId from the session
		if (gameId == 0L) {
			gameId = (Long) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap()
					.get(GameManager.GAME_ID_KEY);
		}
		return gameId;
	}

	public Game getGame() {
		return GameManager.getInstance().getGame(getGameId());
	}

	public List<Player> getPlayers() {
		return GameManager.getInstance().getPlayers(getGameId());
	}
	
	public String start(){
		GameManager.getInstance().startGame(getGameId());
		return "play?faces-redirect=true";
	}
}
