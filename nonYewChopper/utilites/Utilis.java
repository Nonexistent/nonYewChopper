package nonYewChopper.utilites;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Player;
import org.powerbot.script.wrappers.Tile;

public class Utilis extends MethodProvider{
		private static Utilis u;
	private Utilis(MethodContext ctx) {
		super(ctx);
	}
	
	public static Utilis getInstance(){
		return u;
	}
	
	public static void createInstance(MethodContext c){
		if(u == null){
			u = new Utilis(c);
		}
	}

	// Deposit all except for pickaxe
	public void depositExcept(final Item item) {
		if (!ctx.backpack.contains(item)) {
			ctx.bank.depositInventory();
		} else {
			for (Item k : ctx.backpack.getAllItems()) {
				if (item.getId() != k.getId()) {
					ctx.bank.deposit(k.getId(), 0);
					//Timer t = new Timer(2000); TIMER CLASS DEPRECATION
					while (/*t.isRunning() &&*/ ctx.backpack.contains(item)) {
						sleep(200, 300);
					}
				}
			}
		}
	}

	// Gets price of clay
	public int getPrice() throws IOException {
		String price;
		URL url = new URL("http://services.runescape.com/m=itemdb_rs/Soft_clay/viewitem.ws?obj=1515");
		URLConnection connect = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			if (line.contains("<td>")) {
				price = line.substring(line.indexOf(">") + 1,line.indexOf("/") - 1);
				price = price.replace(",", "");
				try {
					return Integer.parseInt(price);
				} catch (NumberFormatException e) {
					return 0;
				}
			}
		}
		return -1;
	}

	public Tile getFront(final Player player, final int f) {
		final double o = Math.toRadians(player.getOrientation());
		return player.getLocation().derive((int) Math.round(f*Math.cos(o)),(int) Math.round(f*Math.sin(o)));
	}
	
	public boolean isFull(){
		return ctx.backpack.select().count() == 28;
	}
	
	public GameObject inFrontTree(final int f){
		GameObject t = null;
		for(GameObject tree : ctx.objects.select().at(getFront(ctx.players.local(), f)).first()){
			t = tree;
		}
		return t;
	}
}
