package nonYewChopper.enums;

import nonYewChopper.utilites.EnumInterface;


public enum PowerEnum implements EnumInterface{
		Normal("logs.", new String[]{"Tree", "Dead tree"}, "Tree stump", 1511, new int[]{1276, 1277, 1278, 1280, 1282, 1283, 1284, 1285, 1286, 1289, 1291, 1330, 1331, 1332, 1365, 1383, 1384, 2410, 2411, 3033, 3034, 3036, 3879, 3881, 3882, 3883, 5904, 14308, 14309, 16265, 32294, 37477, 37478, 37481, 37482, 37483, 37652, 38760, 38782, 38783, 38784, 38785, 38786, 38787, 38788, 38789, 39328, 41713, 47594, 47596, 47598, 47600, 65253, 69139, 69141, 69142, 69144, 70060, 70063, 70099, 75891, 77095, 79813}, new String[]{"Tree", "Dead tree", "Tree stump"}, 25),
		Oak("oak logs.", new String[]{"Oak"}, "Oak", 1521, new int[]{1281, 3037, 8467, 11999, 38731, 38732}, new String[]{"Oak", "Oak"}, 37.5),
		Willow("willow logs.", new String[]{"Willow"}, "Willow", 1519, new int[]{139, 142, 2210, 2372, 37480, 38616, 38627, 58006}, new String[]{"Willow", "Willow"}, 67.5),
		Maple("maple logs.", new String[]{"Maple"}, "Maple", 1517, new int[]{1307, 4674, 46277, 51843}, new String[]{"Maple", "Maple"}, 100);
		// NEED TO FINISH MAPLE TREES
		int[] treeId;
		String[] treeName;
		String[] all;
		String stumpName;
		int logId;
		double exp;
		String logName;
		//normal ids: 1276, 1277, 1278, 1280, 1282, 1283, 1284, 1285, 1286, 1289, 1291, 1330, 1331, 1332, 1365, 1383, 1384, 2410, 2411, 3033, 3034, 3036, 3879, 3881, 3882, 3883, 5904, 14308, 14309, 16265, 32294, 37477, 37478, 37481, 37482, 37483, 37652, 38760, 38782, 38783, 38784, 38785, 38786, 38787, 38788, 38789, 39328, 41713, 47594, 47596, 47598, 47600, 65253, 69139, 69141, 69142, 69144, 70060, 70063, 70099, 75891, 77095, 79813, 
		//oak ids: 1281, 3037, 8467, 11999, 38731, 38732, 
		//willow ids: 139, 142, 2210, 2372, 37480, 38616, 38627, 58006, 
		
		private PowerEnum(String lName, String[] name, String stump, int log, int[] tree, String[] all, double tExp){
			this.logName = lName;
			this.treeName = name;
			this.stumpName = stump;
			this.logId = log;
			this.treeId = tree;
			this.all = all;
			this.exp = tExp;
		}
		
		@Override
		public String[] getTreeName(){
			return treeName;
		}
		
		public String getStumpName(){
			return stumpName;
		}
		
		public String[] getAll(){
			return all;
		}
		
		public int getLogId(){
			return logId;
		}
		
		public int[] getTreeId(){
			return treeId;
		}

		@Override
		public double getExpAmount() {
			return exp;
		}
		
		public String getLogName(){
			return logName;
		}
}
