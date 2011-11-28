package ch.frickler.jass.logic.definitions;

import ch.frickler.jass.entity.User;
import ch.frickler.jass.logic.Spiel;

public abstract class IUserAction {
	
	ISpieler user;
	
	public IUserAction(ISpieler user){
		user = this.user;
	}
	
	public abstract boolean doAction(Spiel spiel);

	public abstract boolean isActionPossible(Spiel game);

}
