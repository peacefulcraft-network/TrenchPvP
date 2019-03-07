package net.peacefulcraft.trenchpvp.gameclasses.specials;

public class ArmadilloShell
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