package com.orbit.data.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kotcrab.vis.ui.util.adapter.ArrayListAdapter;

import java.util.ArrayList;

/**
 * Created by Fran on 6/3/2017.
 */
public class Orbit{
    public Planet planet;
    public ArrayList<Vector2> points;

    public Orbit(Planet planet){
        this.planet = planet;
    }


    public void act() {
        points.add(new Vector2((float)planet.xPos, (float)planet.yPos));
    }

    public boolean isActive(){
        return planet.getStage()!=null;
    }
}
