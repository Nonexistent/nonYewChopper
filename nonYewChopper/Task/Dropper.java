package nonYewChopper.Task;

import nonYewChopper.YewChop;
import nonYewChopper.Utilites.Execute;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Item;

public class Dropper extends MethodProvider implements Execute {
	private int logId;

	public Dropper(MethodContext ctx, int id) {
		super(ctx);
		this.logId = id;
	}

	@Override
	public void execute() {
		YewChop.status = "Dropping logs...";
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
