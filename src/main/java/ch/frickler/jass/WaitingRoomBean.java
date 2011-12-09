package ch.frickler.jass;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import ch.frickler.jass.db.entity.Game;
import ch.frickler.jass.db.entity.User;
import ch.frickler.jass.service.UserService;

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

	public List<User> getPlayers() {
		return GameManager.getInstance().getGameService(getGameId())
				.getAllSpieler();
	}

	public String start() {
		GameManager.getInstance().startGame(getGameId());
		return "play?faces-redirect=true";
	}

	public void addBot() {
		GameManager.getInstance().addUserToGame(new UserService().createBot(),
				getGameId());
	}

	public String refresh() {
		if (GameManager.getInstance().gameIsReady(getGameId())) {
			return "play?faces-redirect=true";
		}
		return null; // stay on this page and wait
	}
}
