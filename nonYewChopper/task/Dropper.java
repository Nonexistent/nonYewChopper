package nonYewChopper.task;

import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Item;

public class Dropper extends MethodProvider implements Executable {
	private int logId;

	public Dropper(MethodContext ctx, int id) {
		super(ctx);
		this.logId = id;
	}

	@Override
	public void execute(TesterInterface ti) {
		ti.setStatus("Dropping logs...");
		if (!ctx.hud.isOpen(Window.BACKPACK)) {
			ctx.hud.open(Window.BACKPACK);
		}
		if (!ctx.hud.isVisible(Window.BACKPACK)) {
			ctx.hud.view(Window.BACKPACK);
		}
		for (Item i : ctx.backpack.select().id(logId)) {
			if (i.isOnScreen()) {
				i.interact("Drop");
			} else {
				ctx.backpack.scroll(i);
				i.interact("Drop");
			}
		}

	}

}
