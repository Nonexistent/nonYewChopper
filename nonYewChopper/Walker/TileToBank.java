package nonYewChopper.Walker;

import nonYewChopper.YewChop;
import nonYewChopper.Utilites.EnumInter;
import nonYewChopper.Utilites.Execute;
import nonYewChopper.Utilites.VarsE;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public class TileToBank extends MethodProvider implements Execute{
	VarsE choice;
	public TileToBank(MethodContext ctx, EnumInter e) {
		super(ctx);
		this.choice = (VarsE)e;
	}

	@Override
	public void execute() {
		YewChop.status = "Walking to bank...";
		ctx.movement.stepTowards(choice.getBankTile().randomize(3, 3));
		sleep(1400, 1600);
	}
}