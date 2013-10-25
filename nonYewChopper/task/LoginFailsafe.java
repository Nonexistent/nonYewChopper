package nonYewChopper.task;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

public class LoginFailsafe extends MethodProvider implements Executable{

	public LoginFailsafe(MethodContext arg0) {
		super(arg0);
	}

	@Override
	public void execute(TesterInterface ti) {
		ti.setStatus("Logged out, relogging in...");
		ctx.lobby.enterGame();
		
	}

}
