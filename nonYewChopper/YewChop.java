package nonYewChopper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import nonYewChopper.utilites.TesterInterface;
import nonYewChopper.utilites.Utilis;
import nonYewChopper.workers.Gui;
import nonYewChopper.workers.StatGui;

import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;
 

@Manifest(authors = { "Nonexistent" }, 
		  description = "Includes Powerchopping & Bonfires! Version: 2.84", 
		  name = "NonYew Chopper + Banking", 
		  version = 2.84,
		  topic = 1033797
		  ) 
public class YewChop extends PollingScript implements MessageListener, PaintListener, MouseListener {
	public final long startTime = System.currentTimeMillis();
	private Gui gui;
	Rectangle open = new Rectangle(24, 113, 165, 20);
	private YewChop chop = this;
	private TesterInterface tester;
	private int startExp;
	private int yewPrice;
	private boolean scriptRun;
	private int logsCut;
	public static GameObject curTree;
	
	public YewChop() {
		getExecQueue(State.START).add(new Runnable() {
			@Override
			public void run() {
				gui = new Gui(ctx, chop);
				gui.go();
				Utilis.createInstance(ctx);
				startExp = ctx.skills.getExperience(Skills.WOODCUTTING);
				try {
					yewPrice = Utilis.getInstance().getPrice();
				} catch (Exception e) {
					System.out.println("Error getting price");
				}
			}
		});
		
		getExecQueue(State.STOP).add(new Runnable(){
			@Override
			public void run() {
				scriptRun = false;
				System.out.println("stopping");
				sleep(1000);
			}
		});
	}

	@Override
	public int poll() {
		if(scriptRun && (tester != null)){
			if(ctx.camera.getPitch() < 86){
				ctx.camera.setPitch(Random.nextInt(83, 90));
			}
			tester.check();
		}
		return 400;

	}
	
	@Override
	public void messaged(MessageEvent h) {
		tester.decideMessage(h.getMessage().toString().toLowerCase(), chop);

	}

	@Override
	public void repaint(Graphics g) {
		Graphics2D d = (Graphics2D)g;
		d.setPaint(Color.ORANGE);
		d.fill(open);
		d.setPaint(Color.RED);
		g.drawString("CLICK HERE FOR RUN INFO", 26, 128);
		d.setPaint(Color.WHITE);
		g.drawString("Current Yew Price: " + Integer.toString(yewPrice), 24, 95);
		g.drawString("General Status: " + tester.getStatus(), 24, 108);
		if(curTree != null){
			curTree.getLocation().getMatrix(ctx).draw(g, 100);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(scriptRun && open.contains(e.getPoint())){
			StatGui.getInstance().go(ctx.skills.getExperienceAt(ctx.skills.getLevel(Skills.WOODCUTTING) + 1));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteGui(final double treeExp, final TesterInterface t){
		this.tester = t;
		StatGui.createInstance(startTime, treeExp, startExp, yewPrice, chop);
		this.gui = null;
		scriptRun = true;
	}
	
	public int getLogsCut(){
		return logsCut;
	}
	
	public void setLogsCut(int logsCut){
		this.logsCut = logsCut;
	}
}