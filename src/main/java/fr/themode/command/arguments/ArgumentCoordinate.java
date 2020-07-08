package fr.themode.command.arguments;

import java.lang.Float;
import java.lang.NumberFormatException;

/**
 * ArgumentPosition: an Argument that represents a 3D xyz coordinate.
 *
 * <p> Format: x y z 
 * <br> 
 * Where x, y, and z can be either a float, or a string containing "~"
 * The tilda is an added syntax meaning that the position is relative to something else. 
 * The absolute position can be retrieved at a later point. 
 * </p> 
 *
 */
public class ArgumentCoordinate extends Argument<Float[]> {

    public static final int FORMAT_ERROR = 1; 

    private coordinatePart x; 
    private coordinatePart y; 
    private coordinatePart z; 

    public ArgumentCoordinate( String id ){
        super( id, true ); 
    } 

    @Override 
    public int getConditionResult( Float[] coords ){
        return SUCCESS; 
    } 

    @Override
    /**
     * parse: parse out the string into 3 coordinate parts. 
     */
    public Float[] parse( String value ){ 

        String[] xyz = value.split(" ");
        this.x = new coordinatePart(xyz[0]); 
        this.y = new coordinatePart(xyz[1]); 
        this.z = new coordinatePart(xyz[2]); 

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
     * getCorrectionResult: whether the argument is formated correctly. 
     * A little tricky because of the format of the position. 
     * Each part of argument can be either a string ("~", "~-12") or just a float. 
     * And there needs to be three parts. 
     */
    @Override 
    public int getCorrectionResult(String value ){

        String[] parts = value.split(" ");
        if( parts.length != 3 ){
            return FORMAT_ERROR;   
        } else { 
            boolean isCoordPart = true; 
            for( int i = 0; i < parts.length && isCoordPart; i++   ){
                isCoordPart = isCoordinatePart( parts[i] ); 
            }
            if( isCoordPart ) {
                return SUCCESS; 
            } else { 
                return FORMAT_ERROR; 
            }
        }
    }

    /**
     * isCoordinatePart: checks if the string is a valid part of a 3D cordinate. 
     */
    private boolean isCoordinatePart( String part ){
        
        if( part == null || part.length() == 0 ){
            return false;  
        }

        if( part.equals( '~' ) ){
            return true;  
        }
        else if( part.charAt(0) == '~' ){

            try{
                Float.parseFloat(part); 
                return true; 
            } catch( NumberFormatException nfe ){
                return false; 
            }
        } else {
            try{
                Float.parseFloat(part); 
                return true; 
            } catch( NumberFormatException nfe ){
                return false; 
            }
        }
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




