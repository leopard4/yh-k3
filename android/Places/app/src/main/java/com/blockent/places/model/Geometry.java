package com.blockent.places.model;

import java.io.Serializable;

public class Geometry implements Serializable {

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
