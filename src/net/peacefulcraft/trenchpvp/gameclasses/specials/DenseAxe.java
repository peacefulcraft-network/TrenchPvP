package net.peacefulcraft.trenchpvp.gameclasses.specials;

public class DenseAxe
{
	private long lastClick;
	
	public void updateClick()
	{
		lastClick = System.currentTimeMillis();
	}
	
	public long getClick()
	{
		return lastClick;
	}
	
	
}