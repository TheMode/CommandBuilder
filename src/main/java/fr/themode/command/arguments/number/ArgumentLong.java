package fr.themode.command.arguments.number;

public class ArgumentLong extends ArgumentNumber<Long> {

    public ArgumentLong(String id) {
        super(id);
        this.min = Long.MIN_VALUE;
        this.max = Long.MAX_VALUE;
    }

    @Override
    public int getCorrectionResult(String value) {
        try {
            Long.parseLong(parseValue(value), getRadix(value));
            return SUCCESS;
        } catch (NumberFormatException | NullPointerException e) {
            return NOT_NUMBER_ERROR;
        }
    }

    @Override
    public Long parse(String value) {
        return Long.parseLong(parseValue(value), getRadix(value));
    }

    @Override
    public int getConditionResult(Long value) {
        // Check range
        if (value < this.min || value > this.max)
            return RANGE_ERROR;

        return SUCCESS;
    }

}
