package net.peacefulcraft.trenchpvp.gamehandle.arena;

import java.util.Iterator;

import net.peacefulcraft.trenchpvp.gamehandle.MapCycle;
import net.peacefulcraft.trenchpvp.gamehandle.MapPosition;
import net.peacefulcraft.trenchpvp.gamehandle.TrenchArena;

public class MapCycleIterator implements Iterator<TrenchArena>{

	private MapPosition first;
	private MapPosition current;
	
	public MapCycleIterator(MapCycle cycle) {
		first = cycle.getFirstMapPosition();
		current = first;
	}
	
	@Override
	public boolean hasNext() {
		
		if(current == null)
			return false;
		
		return (current.getNext() != first)? true : false;
	}

	@Override
	public TrenchArena next() {
		current = current.getNext();
		return current.getArena();
	}
	
}
