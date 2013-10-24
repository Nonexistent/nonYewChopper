package nonYewChopper.Workers;

import nonYewChopper.Box;
import nonYewChopper.YewChop;
import nonYewChopper.Utilites.EnumInter;
import nonYewChopper.Utilites.PowerE;
import nonYewChopper.Utilites.TesterInter;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

public class PowerTester extends MethodProvider implements TesterInter{
	private String logName;
	
	public PowerTester(MethodContext ctx, EnumInter c) {
		super(ctx);
		this.logName = ((PowerE)c).getLogName();
	}

	@Override
	public void check() {
		powerChop((ctx.game.getClientState()), 
				 (ctx.backpack.select().count() == 28), 
				 (ctx.objects.select().contains(YewChop.curTree)));
	}

	public void powerChop(int cs, boolean isfull, boolean isThere){
		if((isfull == true) && (cs == 11)){
			Box.getInstance().getFullManager().execute();
		}else if((isfull == false) && (cs == 11) && (isThere == true)){
			Box.getInstance().getChopper().execute();
		}else if((cs == 7)){ 
			Box.getInstance().getLogin().execute();
		}else{
		Box.getInstance().getSearcher().execute();
		}
	}
	
	@Override
	public void decideMessage(String s){
		if (s.equals("you get some " + logName)) {
			YewChop.logsCut++;
	}
}
	
}
