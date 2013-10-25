package nonYewChopper.walker;

import nonYewChopper.enums.YewEnum;
import nonYewChopper.utilites.EnumInterface;
import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public class TileToTree extends MethodProvider implements Executable{
	YewEnum choice;
	
	public TileToTree(MethodContext ctx, EnumInterface v) {
		super(ctx);
		this.choice = (YewEnum)v;
	}

	@Override
	public void execute(TesterInterface ti) {
		ti.setStatus("Walking to trees.");
			ctx.movement.stepTowards(choice.getTreeTile().randomize(5, 5));
			sleep(1300, 1800);
	}
}
