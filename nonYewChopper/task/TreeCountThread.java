package nonYewChopper.task;

import java.util.ArrayList;

import nonYewChopper.enums.YewEnum;

import org.powerbot.script.lang.Filter;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;

public class TreeCountThread extends MethodProvider implements Runnable {
	Searcher cl;
	ArrayList<Tile> tempList;
	private int treeAmount;
	private Filter<GameObject> CT_FILTER;

	public TreeCountThread(Searcher cl, MethodContext ctx, int amount, final YewEnum c) {
		super(ctx);
		this.cl = cl;
		this.treeAmount = amount;
		this.CT_FILTER = new Filter<GameObject>() {
			@Override
			public boolean accept(GameObject tree) {
				if (c.getTreeArea().contains(tree)) {
					if ((tree.getId() == 38755) && c.getTreeArea().contains(tree)) {
						return true;
					}
				}
				return false;
			}
		};
	}

	@Override
	public void run() {System.out.println("amount: " + treeAmount);
		while (cl.getMap().size() < treeAmount) {
			tempList = cl.getMap();
				mapper();
			cl.setMap(tempList);
			try {
				Thread.sleep(1200, 1500);
			} catch (Exception e) {
				System.out.println("Error while counting trees");
			}
		}
		System.out.println("Finished counting tree: " + tempList.size());
	}

	private void mapper() {
		for (GameObject obj : ctx.objects.select().select(this.CT_FILTER)) {
			if (!tempList.contains(obj.getLocation())) {
				tempList.add(obj.getLocation());
			}
		}
	}
}
