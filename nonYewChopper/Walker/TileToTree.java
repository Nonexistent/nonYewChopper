package nonYewChopper.Walker;

import nonYewChopper.YewChop;
import nonYewChopper.Utilites.EnumInter;
import nonYewChopper.Utilites.Execute;
import nonYewChopper.Utilites.VarsE;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public class TileToTree extends MethodProvider implements Execute{
	VarsE choice;
	
	public TileToTree(MethodContext ctx, EnumInter v) {
		super(ctx);
		this.choice = (VarsE)v;
	}

	@Override
	public void execute() {
		YewChop.status = "Walking to trees.";
			ctx.movement.stepTowards(choice.getTreeTile().randomize(5, 5));
			sleep(1300, 1800);
	}
}
