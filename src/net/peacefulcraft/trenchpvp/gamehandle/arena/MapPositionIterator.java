package net.peacefulcraft.trenchpvp.gamehandle.arena;

import java.util.Iterator;

public class MapPositionIterator implements Iterator<MapPosition>{

	private MapPosition first;
	private MapPosition current;
	
	public MapPositionIterator(MapCycle cycle) {
		first = cycle.getFirstMapPosition();
		current = first;
	}
	
	@Override
	public boolean hasNext() {
		
		if(current == null)
			return false;
		
		return (current.getNext() == first)? false : true;
	}

	@Override
	public MapPosition next() {
		MapPosition ret = current;
		current = current.getNext();
		return ret;
	}

}
