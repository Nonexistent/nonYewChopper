package nonYewChopper.workers;

import nonYewChopper.Box;
import nonYewChopper.YewChop;
import nonYewChopper.enums.PowerEnum;
import nonYewChopper.utilites.EnumInterface;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

public class PowerTester extends MethodProvider implements TesterInterface{
	private String logName;
	private String status = "";
	
	public PowerTester(MethodContext ctx, EnumInterface c) {
		super(ctx);
		this.logName = ((PowerEnum)c).getLogName();
	}

	@Override
	public void check() {
		powerChop((ctx.game.getClientState()), 
				 (ctx.backpack.select().count() == 28), 
				 (ctx.objects.select().contains(YewChop.curTree)));
	}

	public void powerChop(int cs, boolean isfull, boolean isThere){
		if((isfull == true) && (cs == 11)){
			Box.getInstance().getFullManager().execute(this);
		}else if((isfull == false) && (cs == 11) && (isThere == true)){
			Box.getInstance().getChopper().execute(this);
		}else if((cs == 7)){ 
			Box.getInstance().getLogin().execute(this);
		}else{
		Box.getInstance().getSearcher().execute(this);
		}
	}
	
	@Override
	public void decideMessage(String s, YewChop y){
		if (s.equals("you get some " + logName)) {
			y.setLogsCut(y.getLogsCut() + 1);
	}
}

	@Override
	public String getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(String s){
		this.status = s;
	}
	
}
