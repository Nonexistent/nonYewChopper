package nonYewChopper.task;

import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public class Banking extends MethodProvider implements Executable {
	
	public Banking(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public void execute(TesterInterface ti) {
		if (!ctx.players.local().isInMotion()) {
			ti.setStatus("Banking items...");
			if (ctx.bank.open()) {
				ctx.bank.depositInventory();
			}
			ctx.bank.close();
		}
	}
}
