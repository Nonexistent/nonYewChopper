package nonYewChopper.Task;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

import nonYewChopper.YewChop;
import nonYewChopper.Utilites.Execute;

public class LoginFailsafe extends MethodProvider implements Execute{

	public LoginFailsafe(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public void execute() {
		YewChop.status = "Logged out, relogging in...";
		ctx.lobby.enterGame();
		
	}

}
