package fr.themode.command.arguments;

import java.util.regex.Pattern;

public class ArgumentStringArray extends Argument<String[]> {

    public ArgumentStringArray(String id) {
        super(id, true, true);
    }

    @Override
    public int getCorrectionResult(String value) {
        return SUCCESS;
    }

    @Override
    public String[] parse(String value) {
        return value.split(Pattern.quote(" "));
    }

    @Override
    public int getConditionResult(String[] value) {
        return SUCCESS;
    }
}
