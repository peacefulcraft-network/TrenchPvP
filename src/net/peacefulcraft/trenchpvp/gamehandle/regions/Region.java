package net.peacefulcraft.trenchpvp.gamehandle.regions;

import org.bukkit.Location;

public class Region {
    private Integer x1;

    private Integer x2;

    private Integer z1;

    private Integer z2;

    private Integer y1;

    private Integer y2;

    private String name;
        public String getName() { return this.name; }

    /**
     * Creating region without y coord boundaries
     * @param name Name of region
     */
    public Region(String name, Integer x1, Integer x2, Integer z1, Integer z2) {
        this(name, x1, x2, null, null, z1, z2);
    }

    /**
     * Creating region with y coord boundaries
     * @param name Name of region
     */
    public Region(String name, Integer x1, Integer x2, Integer y1, Integer y2, Integer z1, Integer z2) {
        this.x1 = x1;
        this.x2 = x2;
        this.z1 = z1;
        this.z2 = z2;
        this.y1 = y1;
        this.y2 = y2;

        this.name = name; 
    }

    /**
     * @param loc Location to be checked in this region
     * @return True if location is inside region
     */
    public boolean isInRegion(Location loc) {
        int locX = loc.getBlockX();
        int locZ = loc.getBlockZ();

        //(x1,y1) -> (x2,y1)
        boolean test1 = _isInRegion(x1, x2, z1, z1, locX, locZ);
        //(x2,y1) -> (x2,y2)
        boolean test2 = _isInRegion(x2, x2, z1, z2, locX, locZ);
        //(x2,y2) -> (x1,y2)
        boolean test3 = _isInRegion(x2, x1, z2, z2, locX, locZ);
        //(x1,y2) -> (x1,y1)
        boolean test4 = _isInRegion(x1, x1, z2, z1, locX, locZ);

        if(test1 && test2 && test3 && test4) { return true; }
        return false;
    }

    /**Helper function used to check sides of rectangle bounds */
    private boolean _isInRegion(int x1, int x2, int z1, int z2, int pointX, int pointY) {
        double A = -(z2 - z1);
        double B = x2 - x1;
        double C = -(A * x1 + B * z1);

        double D = (A * pointX) + (B * pointY) + C;
        if(D > 0) { return false; }
        return true;
    }
}