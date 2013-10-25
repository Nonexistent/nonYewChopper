package nonYewChopper.task;

import nonYewChopper.YewChop;
import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;
import nonYewChopper.utilites.Utilis;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Random;


public class Chopper extends MethodProvider implements Executable {
	private int f;
	
	public Chopper(MethodContext ctx, final int f) {
		super(ctx);
		this.f = f;
	}
	
	public boolean checkTree(){
		if(f == 2){
			return YewChop.curTree.equals(Utilis.getInstance().inFrontTree(f)) && (ctx.players.local().getAnimation() != -1);
		}else {
			return (YewChop.curTree.equals(Utilis.getInstance().inFrontTree(f)) || (ctx.players.local().getAnimation() != -1));
			}
	}

	@Override
	public void execute(TesterInterface ti) {
		if (!Utilis.getInstance().isFull()) {
			try {
				if (YewChop.curTree.isOnScreen()) {
						if (YewChop.curTree.interact("Chop down")) {
							sleep(1000, 1200);
							ti.setStatus("Tree avaliable, cutting said tree.");
							ctx.mouse.move(Random.nextInt(0, 798), Random.nextInt(0, 518));
							while (checkTree() && !Utilis.getInstance().isFull()) {
								sleep(500, 800);
							}
					}
				} else {
					if(ctx.players.local().getLocation().distanceTo(YewChop.curTree.getLocation()) < 5){
						if(ctx.objects.select().contains(YewChop.curTree)){
							ctx.camera.turnTo(YewChop.curTree);
						}
					}else{
						ti.setStatus("Tree avaliable, moving to said tree...");
						if(ctx.objects.select().contains(YewChop.curTree)){
							ctx.movement.stepTowards(YewChop.curTree.getLocation().randomize(1, 1));
						}
					}
				}

			} catch (Exception e) {
				System.out.println("error at chopper");
			}
		}
	}
}
