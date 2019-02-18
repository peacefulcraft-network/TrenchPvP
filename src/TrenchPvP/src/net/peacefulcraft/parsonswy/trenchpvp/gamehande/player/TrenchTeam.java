package net.peacefulcraft.parsonswy.trenchpvp.gamehande.player;
//Add get spawns from config later
public enum TrenchTeam {
	BLUE, RED;

	public static int blueCount;
	public static int redCount;
	public static TrenchPlayer[] trenchPlayers = new TrenchPlayer[24];
	public static double[] getBlueSpawn(){
		double[] blueSpawn = new double[3];
		blueSpawn[0] = -71.500;
		blueSpawn[1] = 9.00;
		blueSpawn[2] = -58.500;
		return blueSpawn;
	}
	
	public static double[] getRedSpawn(){
		double[] redSpawn = new double[3];
		redSpawn[0] = -1.500;
		redSpawn[1] = 9.00;
		redSpawn[2] = -10.500;
		return redSpawn;
	}
}

