package net.peacefulcraft.trenchpvp.gamehandle.arena;

public class MapPosition {

	private TrenchArena map;
		public TrenchArena getArena() { return map; }
	
	private MapPosition next;
		public MapPosition getNext() { return next; }
		public void setNext(MapPosition next) { this.next = next; }
	
	public MapPosition(TrenchArena arena) {
		map = arena;
	}
	
}
