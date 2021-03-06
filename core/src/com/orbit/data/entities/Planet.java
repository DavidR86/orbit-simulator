package com.orbit.data.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.orbit.data.GameScreen;
import com.orbit.data.helpers.Units;

import java.util.ArrayList;
import java.util.Vector;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.actor;


/**
 * Created by Fran on 5/22/2017.
 */
public class Planet extends Actor {
    double radius, mass, speed, angle;
    public volatile double  xPos, yPos, zPos,  vX, vY, vZ, currAccelX=Double.NaN, currAccelY = Double.NaN, currAccelZ = Double.NaN;
    public boolean accelInit = false;
    double AURadius;
    Texture texture;
    Color color;
    String name;

    boolean magnify = true;
    public boolean lockCamera;
    float magnificationAmount;

    private boolean useColor;

    public Planet(String name, double radius, double mass, double speed, double velocityAngle, Texture texture, Color color, double x, double y){
        this.name = name;

        this.radius = radius; //km
        this.radius*=1000;//Convert to m
        this.AURadius = Units.mToAU(this.radius);

        this.mass = mass; //Earth masses
        this.mass*=5.9723 * Math.pow(10,24); //Convert to kg

        this.speed = speed; //km/s
        this.speed *= 1000;//convert to m/s

        this.angle = velocityAngle; //degrees

        this.vX = this.speed*Math.cos(Math.toRadians(this.angle));
        this.vY = this.speed*Math.sin(Math.toRadians(this.angle));
        // calculate x and y speeds

        xPos = x;
        yPos = y;
        setPosition((float)(xPos - AURadius), (float)(yPos - AURadius));

        double diameter = AURadius * 2;

        this.setWidth((float)diameter);
        this.setHeight((float)diameter);

        this.texture = texture;

        this.color = color.cpy();

        useColor = false;
    }

    public Planet(String name, double radius, double mass, double speed, double velocityAngle, Color color, double x, double y) {
        this(name, radius, mass, speed, velocityAngle, (Texture)null, color, x, y);


        Pixmap pixmap = new Pixmap(512, 512, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE.cpy());
        pixmap.fillCircle(256,256,256);
        Texture genTexture = new Texture(pixmap);
        pixmap.dispose();

        texture = genTexture;

        this.color = color.cpy();

        useColor = true;
    }



    public Planet(String name, double radius, double mass, double vX, double vY, double vZ, Color color, double x, double y, double z){
        this(name, radius, mass, 0, 0, color, x, y);
        this.vX = vX;
        this.vY = vY;
        this.vZ = vZ;
        this.zPos = z;

        useColor = true;
    }

    public Planet(String name, double radius, double mass, double vX, double vY, double vZ, Texture texture, Color color, double x, double y, double z){
        this(name, radius, mass, vX, vY, vZ, color, x, y, z);

        this.texture = texture;

        useColor = false;
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        synchronized (this) {
            setPosition((float) (xPos - getAURadius()), (float) (yPos - getAURadius()));


            if(useColor) {
                batch.setColor(color.cpy());
            } else {
                batch.setColor(Color.WHITE.cpy());
            }

            if (magnify) {
                batch.draw(texture, (float) (xPos - AURadius - getMultiplier() * 0.5), (float) (yPos - AURadius - getMultiplier() * 0.5),
                        getWidth() + (float) getMultiplier(), getHeight() + (float) getMultiplier());
            } else {
                batch.draw(texture, (float) (xPos - AURadius), (float) (yPos - AURadius), getWidth(), getHeight());
            }
            if (lockCamera) {
                centerCamera();
            }
        }
    }



    private double getMultiplier(){
        return 0.01*GameScreen.sizeMultVar * magnificationAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setMagnificationAmount(float magnificationAmount) {
        this.magnificationAmount = magnificationAmount;
    }

    public double getMass() {
        return mass;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public boolean equalsP(Planet p){
        if(xPos==p.getxPos()&&yPos==p.getyPos()&&zPos==p.zPos){
            return true;
        }
        return  false;
    }

    public double getAngle(){
        double angle = Math.toDegrees(Math.atan2(vY, vX));

        if(angle<0){
            angle+=360;
        }

        return angle;
    }

    public double getSpeed(){
        return (Math.sqrt(Math.pow(vX, 2)+Math.pow(vY, 2)))/1000;
    }

    public double getOrigRadius(){
        return this.radius/1000;
    }

    public double getOrigMass(){
        return this.mass/= (5.9723 * Math.pow(10,24));
    }

    public Color getCurrColor(){
        return this.color;
    }

    public double getAURadius(){
        return  AURadius;
    }

    public void zoomCamera(){
        OrthographicCamera camera = (OrthographicCamera) this.getStage().getCamera();
        camera.zoom = (float)(AURadius*2/camera.viewportHeight);
    }

    public synchronized  void centerCamera(){
        centerCamera((float)xPos, (float)yPos);
    }

    public synchronized void centerCamera(float x, float y){
        OrthographicCamera camera = (OrthographicCamera) this.getStage().getCamera();
        camera.translate(-1*camera.position.x,-1*camera.position.y);
        camera.translate((float)x, (float)y);
    }

    public boolean getLockCamera(){
        return lockCamera;
    }

    public void setLockCamera(boolean lockCamera){
        this.lockCamera = lockCamera;
    }

}
