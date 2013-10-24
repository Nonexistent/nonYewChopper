package nonYewChopper.Workers;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Area;

import nonYewChopper.Box;
import nonYewChopper.YewChop;
import nonYewChopper.Utilites.EnumInter;
import nonYewChopper.Utilites.TesterInter;
import nonYewChopper.Utilites.VarsE;

public class YewTester extends MethodProvider implements TesterInter{
	private Area bankArea;
	private Area treeArea;

	public YewTester(MethodContext ctx, EnumInter c) {
		super(ctx);
		this.bankArea = ((VarsE)c).getBankArea();
		this.treeArea = ((VarsE)c).getTreeArea();
	}
	
	@Override
	public void check() {
		set((ctx.game.getClientState()), 
				(ctx.backpack.select().count() == 28),
				(ctx.backpack.select().count() > 2),
				bankArea.contains(ctx.players.local()),
				treeArea.contains(ctx.players.local()),
				(ctx.objects.select().contains(YewChop.curTree)));
	}
	
	public void set(int cs, boolean isfull, boolean morethan,
			boolean bankcont, boolean treecont,boolean isThere) {
		if ((isfull == true) && (bankcont == false) && (cs == 11)) {
			Box.getInstance().getBankWalk().execute();
		} else if ((treecont == false) && ((morethan == false) || (isfull == false)) && (cs == 11)) {
			Box.getInstance().getTreeWalk().execute();
		} else if ((morethan == true) && (bankcont == true) && (cs == 11)) {
			Box.getInstance().getBanking().execute();
		} else if ((isfull == false) && (treecont == true) && (cs == 11) && (isThere == true)) {
			Box.getInstance().getChopper().execute();
		}else if((cs == 7)){
			Box.getInstance().getLogin().execute();
		}else{
			Box.getInstance().getSearcher().execute();
		}
		
	}

	@Override
	public void decideMessage(String s) {
		if (s.equals("you get some yew logs.")) {
			YewChop.logsCut++;
		}
		
	}
}
