package net.peacefulcraft.trenchpvp.gamehandle.arena;

import java.util.Iterator;

/**
 * This is a glorified, circular, doubly-linked list
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
			map.setPrev(map);
			currentMap = map;
		}else {
			map.setPrev(tail);
			tail.setNext(map);
			first.setPrev(map);
			map.setNext(first);
		}
	}

	public TrenchArena nextMap() {
		currentMap = currentMap.getNext();
		return currentMap.getArena();
	}
	
	public void removeArena(TrenchArena arena) {
		MapPositionIterator i = new MapPositionIterator(this);
		MapPosition target = null;
		while(i.hasNext()) {
			MapPosition mp = i.next();
			if(mp.getArena() == arena) { target = mp; break; }
		}
		
		if(target == null) { return; }
		
		target.getPrev().setNext(target.getNext());
		target.getNext().setPrev(target.getPrev());
	}
	
	@Override
	public Iterator<TrenchArena> iterator() {
		return (Iterator<TrenchArena>) new MapCycleIterator(this);
	}
	
}

