package nonYewChopper.Walker;

import nonYewChopper.YewChop;
import nonYewChopper.Utilites.EnumInter;
import nonYewChopper.Utilites.Execute;
import nonYewChopper.Utilites.VarsE;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public class AreaToTree extends MethodProvider implements Execute{
	VarsE choice;
	
	public AreaToTree(MethodContext ctx, EnumInter c) {
		super(ctx);
		this.choice = (VarsE)c;
	}

	@Override
	public void execute() {
		YewChop.status = "Walking to trees...";
			if (!ctx.players.local().isInMotion() || (ctx.players.local().getLocation().distanceTo(ctx.movement.getDestination()) < 5)) {
				ctx.movement.findPath(choice.getTreeArea().getRandomTile()).traverse();
					}
	}
}
