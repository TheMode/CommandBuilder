package fr.themode.command.arguments.number;

import fr.themode.command.arguments.Argument;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public abstract class ArgumentNumber<T> extends Argument<T> {

    public static final int NOT_NUMBER_ERROR = 1;
    public static final int RANGE_ERROR = 2;

    public T min, max;

    public ArgumentNumber(String id) {
        super(id, false);
    }

    public ArgumentNumber min(T value) {
        this.min = value;
        return this;
    }

    public ArgumentNumber max(T value) {
        this.max = value;
        return this;
    }

    public ArgumentNumber between(T min, T max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public String parseValue(String value) {
        if (value.startsWith("0b")) {
            value = value.replaceFirst(Pattern.quote("0b"), "");
        } else if (value.startsWith("0x")) {
            value = value.replaceFirst(Pattern.quote("0x"), "");
        } else if (value.toLowerCase().contains("e")) {
            value = removeScientificNotation(value);
        }
        // TODO number suffix support (k,m,b,t)
        return value;
    }

    public int getRadix(String value) {
        if (value.startsWith("0b")) {
            return 2;
        } else if (value.startsWith("0x")) {
            return 16;
        }
        return 10;
    }

    public String removeScientificNotation(String value) {
        try {
            return new BigDecimal(value).toPlainString();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
