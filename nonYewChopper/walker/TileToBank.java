package nonYewChopper.walker;

import nonYewChopper.enums.YewEnum;
import nonYewChopper.utilites.EnumInterface;
import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public class TileToBank extends MethodProvider implements Executable{
	YewEnum choice;
	public TileToBank(MethodContext ctx, EnumInterface e) {
		super(ctx);
		this.choice = (YewEnum)e;
	}

	@Override
	public void execute(TesterInterface ti) {
		ti.setStatus("Walking to bank...");
		ctx.movement.stepTowards(choice.getBankTile().randomize(3, 3));
		sleep(1400, 1600);
	}
}