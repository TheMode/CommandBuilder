package fr.themode.command.arguments.number;

public class ArgumentInteger extends ArgumentNumber<Integer> {

    public ArgumentInteger(String id) {
        super(id);
        this.min = Integer.MIN_VALUE;
        this.max = Integer.MAX_VALUE;
    }

    @Override
    public int getCorrectionResult(String value) {
        try {
            Integer.parseInt(parseValue(value), getRadix(value));
            return SUCCESS;
        } catch (NumberFormatException | NullPointerException e) {
            return NOT_NUMBER_ERROR;
        }
    }

    @Override
    public Integer parse(String value) {
        return Integer.parseInt(parseValue(value), getRadix(value));
    }

    @Override
    public int getConditionResult(Integer value) {
        // Check range
        if (value < this.min || value > this.max)
            return RANGE_ERROR;

        return SUCCESS;
    }

}
