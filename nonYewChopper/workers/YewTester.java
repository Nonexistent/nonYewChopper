package nonYewChopper.workers;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Area;

import nonYewChopper.Box;
import nonYewChopper.YewChop;
import nonYewChopper.enums.YewEnum;
import nonYewChopper.utilites.EnumInterface;
import nonYewChopper.utilites.TesterInterface;

public class YewTester extends MethodProvider implements TesterInterface{
	private Area bankArea;
	private Area treeArea;
	private String status = "";

	public YewTester(MethodContext ctx, EnumInterface c) {
		super(ctx);
		this.bankArea = ((YewEnum)c).getBankArea();
		this.treeArea = ((YewEnum)c).getTreeArea();
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
			Box.getInstance().getBankWalk().execute(this);
		} else if ((treecont == false) && ((morethan == false) || (isfull == false)) && (cs == 11)) {
			Box.getInstance().getTreeWalk().execute(this);
		} else if ((morethan == true) && (bankcont == true) && (cs == 11)) {
			Box.getInstance().getBanking().execute(this);
		} else if ((isfull == false) && (treecont == true) && (cs == 11) && (isThere == true)) {
			Box.getInstance().getChopper().execute(this);
		}else if((cs == 7)){
			Box.getInstance().getLogin().execute(this);
		}else{
			Box.getInstance().getSearcher().execute(this);
		}
		
	}

	@Override
	public void decideMessage(String s, YewChop y) {
		if (s.equals("you get some yew logs.")) {
			y.setLogsCut(y.getLogsCut() + 1);
		}
		
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String s) {
		this.status = s;
	}
}
