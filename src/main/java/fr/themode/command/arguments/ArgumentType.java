package fr.themode.command.arguments;

import fr.themode.command.arguments.number.ArgumentDouble;
import fr.themode.command.arguments.number.ArgumentFloat;
import fr.themode.command.arguments.number.ArgumentInteger;
import fr.themode.command.arguments.number.ArgumentLong;

public class ArgumentType {

    public static ArgumentStructure Structure(String id) {
        return new ArgumentStructure(id);
    }

    public static ArgumentBoolean Boolean(String id) {
        return new ArgumentBoolean(id);
    }

    public static ArgumentLong Long(String id) {
        return new ArgumentLong(id);
    }

    public static ArgumentInteger Integer(String id) {
        return new ArgumentInteger(id);
    }

    public static ArgumentDouble Double(String id) {
        return new ArgumentDouble(id);
    }

    public static ArgumentFloat Float(String id) {
        return new ArgumentFloat(id);
    }

    public static ArgumentString String(String id) {
        return new ArgumentString(id);
    }

    public static ArgumentWord Word(String id) {
        return new ArgumentWord(id);
    }

    public static ArgumentCoordinate Coordinate(String id){
        return new ArgumentCoordinate(id);
    }

    public static ArgumentStringArray StringArray(String id) {
        return new ArgumentStringArray(id);
    }

}
