package net.peacefulcraft.trenchpvp.gamehandle;

import java.util.Iterator;

import net.peacefulcraft.trenchpvp.gamehandle.arena.MapCycleIterator;

/**
 * This is a glorified, circular linked list
 */
public class MapCycle implements Iterable<TrenchArena>{
	
	private MapPosition first;
		public MapPosition getFirstMapPosition() { return first; }
		
	private MapPosition tail;
	private MapPosition currentMap;
		public TrenchArena getCurrentMap() { return currentMap.getArena(); }
	
	public void addArena(TrenchArena arena) {
		
		MapPosition map = new MapPosition(arena);
		
		if(first == null) {
			first = map;
			tail = map;
			map.setNext(map);
		}else {
			tail.setNext(map);
			map.setNext(first);
		}
	}

	public TrenchArena nextMap() {
		currentMap = currentMap.getNext();
		return currentMap.getArena();
	}
	
/*	We need another iterator for MapPositions if we want to be able to do this
 *  It's not something we strictly need right now so.. TODO: MapPosition iterator, probably private
	public void removeArena(TrenchArena arena) {
		
	}
*/
	
	@Override
	public Iterator<TrenchArena> iterator() {
		return (Iterator<TrenchArena>) new MapCycleIterator(this);
	}
	
}

