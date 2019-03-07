package net.peacefulcraft.trenchpvp.gameclasses.specials;

public class SugarRush
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
