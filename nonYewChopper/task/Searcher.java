package nonYewChopper.task;

import java.util.ArrayList;

import nonYewChopper.YewChop;
import nonYewChopper.enums.PowerEnum;
import nonYewChopper.enums.YewEnum;
import nonYewChopper.utilites.EnumInterface;
import nonYewChopper.utilites.Executable;
import nonYewChopper.utilites.TesterInterface;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;

public class Searcher extends MethodProvider implements Executable {
	private ArrayList<Tile> tileList;
	private ArrayList<GameObject> l = new ArrayList<GameObject>();
	private int[] treeId;

	public Searcher(MethodContext ctx, int amount, EnumInterface i) {
		super(ctx);
		YewEnum c = (YewEnum)i;
		this.treeId = c.getTreeId();
		this.tileList = new ArrayList<Tile>();
		Thread t = new Thread(new TreeCountThread(this, ctx, amount, c));
		t.start();
		c = null;
	}
	
	public Searcher(MethodContext ctx, PowerEnum c){
		super(ctx);
		this.treeId = c.getTreeId();
		this.tileList = new ArrayList<Tile>();
		for(GameObject g : ctx.objects.select().name(c.getAll()).within(20)){
			this.tileList.add(g.getLocation());
		}
		System.out.println(tileList.size());
	}
	
	@Override
	public void execute(TesterInterface ti) {
		ti.setStatus("All trees unavaliable, waiting for respawn...");
		l.clear();
		exist();
		if (!l.isEmpty()) {
			for (GameObject h : ctx.objects.select(l).nearest().first()) {
				YewChop.curTree = h;
			}
		} else {
			YewChop.curTree = ctx.objects.getNil();
			int num = Random.nextInt(0, 34);
			if (num < 15) {
				ctx.camera.setAngle(Random.nextInt(1, 160));
			}
			sleep(1000, 1500);
		}
	}
	
	public void powerExecute(){
		
	}

	private void exist() {
		for (Tile t : tileList) {
			for (GameObject k : ctx.objects.select().at(t).first()) {
				for(int h : treeId){
					if(k.getId() == h){
						l.add(k);
					}
				}
			}
		}
	}

	public synchronized ArrayList<Tile> getMap() {
		return tileList;
	}

	public synchronized void setMap(ArrayList<Tile> k) {
		this.tileList = k;
	}
}
