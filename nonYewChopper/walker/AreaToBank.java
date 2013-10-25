package nonYewChopper.walker;

import nonYewChopper.enums.YewEnum;
import nonYewChopper.utilites.EnumInterface;
import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

public class AreaToBank extends MethodProvider implements Executable {
	YewEnum choice;

	public AreaToBank(MethodContext ctx, EnumInterface c) {
		super(ctx);
		this.choice = (YewEnum)c;
	}

	@Override
	public void execute(TesterInterface ti) {
		ti.setStatus("Walking to bank...");
			if (!ctx.players.local().isInMotion() || (ctx.players.local().getLocation().distanceTo(ctx.movement.getDestination()) < 5)) {
				ctx.movement.findPath(choice.getBankArea().getRandomTile()).traverse();
					}
	}
}
