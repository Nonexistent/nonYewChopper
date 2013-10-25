package nonYewChopper.task;

import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.methods.Hud.Window;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;

public class Bonfire extends MethodProvider implements Executable{
	private final int[] fireIds = {70755, 70756, 70757, 70758, 70759, 70760, 70761, 70762, 70763, 70764, 70765, 70766, 70767, 70768, 70769, 70770, 70771};
	private int logId;
	public Bonfire(MethodContext ctx, final int i) {
		super(ctx);
		this.logId = i;
	}

	@Override
	public void execute(TesterInterface ti) {
		ti.setStatus("Burning logs...");
		if (!ctx.hud.isOpen(Window.BACKPACK)) {
			ctx.hud.open(Window.BACKPACK);
		}
		if (!ctx.hud.isVisible(Window.BACKPACK)) {
			ctx.hud.view(Window.BACKPACK); 
		}
		if(!ctx.objects.select().id(fireIds).isEmpty()){
			for(GameObject p : ctx.objects.id(fireIds).nearest().first()){
				if(p.isOnScreen()){
					bon(p);
				}else{
					burn();
					bon(getFire());
				}
			}
		}else{
			burn();
			bon(getFire());
		}
	}
	
	private void burn(){
		for(Item i : ctx.backpack.select().id(logId).first()){
			if(!i.isOnScreen()){
				ctx.backpack.scroll(i);
			}
			if(i.interact("Light")){
				sleep(1600, 2000);
				while(ctx.players.local().getAnimation() == 16700){
					sleep(700, 850);
				}
			}
		}
		sleep(1400, 1800);
	}

	private void bon(GameObject p){
		if(p.equals(ctx.objects.getNil())){
			return;
		}
		for(Item i : ctx.backpack.select().id(logId).first()){
			if(!i.isOnScreen()){
				ctx.backpack.scroll(i);
			}
			if(i.interact("Use")){
				ctx.mouse.move(Random.nextInt(0, 798), Random.nextInt(0, 518));
				sleep(900, 1300);
				p.click(true);
				sleep(1900, 2100);
				while((ctx.players.local().getStance() == 16701) || (ctx.players.local().isInMotion())){
					sleep(1500, 1800);
				}
			}
		}
	}
	
	private GameObject getFire(){
		GameObject o = ctx.objects.getNil();
		for(GameObject p : ctx.objects.select().id(fireIds).nearest().first()){
			o = p;
		}
		return o;
	}
}
