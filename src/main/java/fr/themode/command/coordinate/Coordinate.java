package fr.themode.command.coordinate; 

import java.lang.Float;
import java.lang.NumberFormatException;


/**
 * Coordinate: a class that represents a 3D cartesian coordinate.
 */
public class Coordinate { 

    private coordinatePart x; 
    private coordinatePart y; 
    private coordinatePart z; 

    public Coordinate( String value ) {

        String[] xyz = value.split(" ");
        this.x = new coordinatePart(xyz[0]); 
        this.y = new coordinatePart(xyz[1]); 
        this.z = new coordinatePart(xyz[2]); 
    } 


    public Float[] getCoordinate(){
        return new Float[] { this.x.getCoord(), this.y.getCoord(), this.z.getCoord() }; 
    }

    /**
     * getAbsolute: if the coordinate part is
     * marked as relative, then add to it.
     * @return Float[] - the new coords after adding the relativity. 
     */
    public Float[] getAbsolute( float x, float y, float z ){
        
        float newX = this.x.addToRelative( x ); 
        float newY = this.y.addToRelative( y ); 
        float newZ = this.z.addToRelative( z ); 

        return new Float[] { newX, newY, newZ };
    }

    /**
     * coordinatePart: represents a part of an 3D cartesian coordinate. 
     */
    private class coordinatePart { 

        private boolean isRelative = false;  
        private float coordinatePart = 0; 
        
        /** 
         * coordinatePart: Parses out the part of the positon into a float.   
         * Allows for the use of the tilda character 
         * to represent relative position.  
         *
         * the position part may utilize tilde notation 
         * to indicate a relative coordinate. 
         */
        public coordinatePart( String value ) {

            if( value.equals("~") ){
                this.isRelative = true;
                this.coordinatePart =  0; 
            } 

            if( value.charAt(0) == '~' ){
                this.isRelative = true; 
                value = value.substring( 1 ); 
            } 

           try {
               this.coordinatePart = Float.parseFloat( value ); 
           }  catch( NumberFormatException nfe ){

           }
        }

        public float getCoord(){ 
            return this.coordinatePart; 
        }

        /**
         * addToRelative: adds to the coordinatePart
         * if it was marked as relative. 
         * @param addend - value to add to the coordinate part.  
         */
        public float addToRelative( float addend ){
            if( this.isRelative ){
                return this.coordinatePart + addend; 
            } else { 
                return this.coordinatePart; 
            }
        }
    } 
}

