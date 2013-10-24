package nonYewChopper;

import org.powerbot.script.methods.MethodContext;

import nonYewChopper.Task.Banking;
import nonYewChopper.Task.Bonfire;
import nonYewChopper.Task.Chopper;
import nonYewChopper.Task.Dropper;
import nonYewChopper.Task.LoginFailsafe;
import nonYewChopper.Task.Searcher;
import nonYewChopper.Utilites.EnumInter;
import nonYewChopper.Utilites.Execute;
import nonYewChopper.Utilites.PowerE;
import nonYewChopper.Walker.AreaToBank;
import nonYewChopper.Walker.AreaToTree;
import nonYewChopper.Walker.TileToBank;
import nonYewChopper.Walker.TileToTree;

public class Box {
	private static Box instance;
	private final Execute bw;
	private final Execute tw;
	private final Chopper c;
	private final Banking b;
	private final Execute invFull;
	private final LoginFailsafe l;
	private final Searcher s;
	
	private Box(Builder b){
		this.bw = b.bw;
		this.tw = b.tw;
		this.c = b.ch;
		this.b = b.b;
		this.invFull = b.invFull;
		this.l = b.l;
		this.s = b.s;
	}
	
	public Execute getBankWalk(){
		return bw;
	}
	
	public Execute getTreeWalk(){
		return tw;
	}
	
	public Chopper getChopper(){
		return c;
	}
	
	public Banking getBanking(){
		return b;
	}
	
	public Execute getFullManager(){
		return invFull;
	}
	
	public LoginFailsafe getLogin(){
		return l;
	}
	
	public Searcher getSearcher(){
		return s;
	}
	public static Box getInstance(){
		if(instance != null){
		return instance;
		}else{
			return null;
		}
		
	}
	
	private static void create(Builder b){
		if(instance == null){
		instance = new Box(b);
		}
	}
	
	public static class Builder{
		private final int f;
		private final MethodContext c;
		private final EnumInter e;
		
		private Execute bw = null;
		private Execute tw = null;
		private Chopper ch = null;
		private Banking b = null;
		private Execute invFull = null;
		private LoginFailsafe l = null;
		private Searcher s = null;
		
		public Builder(int f, MethodContext c, EnumInter e){
			this.f = f;
			this.c = c;
			this.e = e;
		}
		 
		public Builder bankTileWalker() { bw = new TileToBank(c, e); return this;}
		public Builder bankAreaWalker() { bw = new AreaToBank(c, e); return this; }
		public Builder treeTileWalker() { tw = new TileToTree(c, e); return this; }
		public Builder treeAreaWalker() { tw = new AreaToTree(c, e); return this; }
		public Builder banker() { b = new Banking(c); return this; }
		public Builder chopper(){ ch = new Chopper(c, f); return this; }
		public Builder dropper(int id) { invFull = new Dropper(c, id); return this; }
		public Builder login() { l = new LoginFailsafe(c); return this; }
		public Builder bonfire(int id) {invFull = new Bonfire(c, id); return this; }
		public Builder yewSearcher(int amount) {
			if(amount != 0){
				s = new Searcher(c, amount, e); return this;
			}else{
				s = new Searcher(c, (PowerE)e); return this;
			}
		}
		
		public void build(){
			create(this);
		}
	}

}
