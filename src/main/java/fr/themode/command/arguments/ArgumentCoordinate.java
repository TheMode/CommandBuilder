package fr.themode.command.arguments;

import fr.themode.command.coordinate.Coordinate; 
import java.lang.NumberFormatException;
import java.lang.Float;


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
public class ArgumentCoordinate extends Argument<Coordinate> {

    public static final int FORMAT_ERROR = 1; 

    public ArgumentCoordinate( String id ){
        super( id, true ); 
    } 

    @Override 
    public int getConditionResult( Coordinate coordinate ){
        return SUCCESS; 
    } 

    @Override
    /**
     * parse: parse the string into a coordinate object. 
     */
    public Coordinate parse( String value ){ 
        return new Coordinate( value );
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
    private static boolean isCoordinatePart( String part ){
        
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
}




