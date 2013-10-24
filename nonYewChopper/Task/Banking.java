package nonYewChopper.Task;

import nonYewChopper.YewChop;
import nonYewChopper.Utilites.Execute;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public class Banking extends MethodProvider implements Execute {
	
	public Banking(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public void execute() {
		if (!ctx.players.local().isInMotion()) {
			YewChop.status = "Banking items...";
			if (ctx.bank.open()) {
				ctx.bank.depositInventory();
			}
			ctx.bank.close();
		}
	}
}
