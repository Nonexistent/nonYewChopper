package nonYewChopper.enums;

import nonYewChopper.utilites.EnumInterface;

import org.powerbot.script.wrappers.Area;
import org.powerbot.script.wrappers.Tile;

public enum YewEnum implements EnumInterface{
			Fala(new Area(new Tile(2996, 3326, 0),new Tile(3051, 3308, 0)),
				 new Area(new Tile(3008, 3354, 0),new Tile(3019, 3359, 0)),
			     new Tile(3006, 3315, 0),
			     new Tile(3013, 3356, 0), null
					),
			Edge(new Area(new Tile(3090, 3468, 0), new Tile(3085, 3482, 0)),
				 new Area(new Tile(3097, 3499, 0), new Tile(3091, 3488, 0)),
				 null, null, null){
				@Override
				public Area getWaitArea(){
					return treeArea;
				}
			},
			VarP(new Area(new Tile(3225, 3497, 0),new Tile(3201, 3507, 0)),
				 new Area(new Tile(3185, 3507, 0), new Tile(3176, 3501, 0)),
				 null, null, null){
				@Override
				public Area getWaitArea(){
					return treeArea;
				}
			},
			Draynor(new Area(new Tile(3143, 3259, 0), new Tile(3189, 3213, 0)),
					new Area(new Tile(3097, 3240, 0), new Tile(3090, 3247, 0)),
					new Tile(3176, 3235, 0),
					new Tile(3092, 3243, 0),
					new Area(new Tile(3173, 3232, 0), new Tile(3181, 3240, 0))
					);
			
			int[] yewTreeId = {1309, 38755, 12000};
			String[] yewName = {"Yew"};
			String notArrayYewName = "Yew";
			Area treeArea;
			Area bankArea;
			Tile treeTile;
			Tile bankTile;
			Area waitTreeArea;
			
			private YewEnum(Area treeArea, Area bankArea, Tile treeTile, Tile bankTile,Area waitA){
				this.treeArea = treeArea;
				this.bankArea = bankArea;
				this.treeTile = treeTile;
				this.bankTile = bankTile;
				this.waitTreeArea = waitA;
			}
			
			public Area getTreeArea(){
				return treeArea;
			}
			
			public Area getBankArea(){
				return bankArea;
			}
			
			public Tile getTreeTile(){
				return treeTile;
			}
			
			public Tile getBankTile(){
				return bankTile;
			}
			
			public Area getWaitArea(){
				return waitTreeArea;
			}
			
			@Override
			public String[] getTreeName(){
				return yewName;
			}
			
			public String getName(){
				return notArrayYewName;
			}
			
			public int[] getTreeId(){
				return yewTreeId;
			}

			@Override
			public double getExpAmount() {
				return 175;
			}
}
