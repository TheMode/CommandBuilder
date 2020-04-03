package fr.themode.command.arguments.number;

public class ArgumentDouble extends ArgumentNumber<Double> {

    public ArgumentDouble(String id) {
        super(id);
        this.min = Double.MIN_VALUE;
        this.max = Double.MAX_VALUE;
    }

    @Override
    public int getCorrectionResult(String value) {
        try {
            String parsed = parseValue(value);
            int radix = getRadix(value);
            if (radix != 10) {
                Long.parseLong(parsed, radix);
            } else {
                Double.parseDouble(parsed);
            }
            return SUCCESS;
        } catch (NumberFormatException | NullPointerException e) {
            return NOT_NUMBER_ERROR;
        }
    }

    @Override
    public int getConditionResult(Double value) {
        // Check range
        if (value < this.min || value > this.max)
            return RANGE_ERROR;

        return SUCCESS;
    }

    @Override
    public Double parse(String value) {
        String parsed = parseValue(value);
        int radix = getRadix(value);
        if (radix != 10) {
            return (double) Long.parseLong(parsed, radix);
        }
        return Double.parseDouble(parsed);
    }

}
