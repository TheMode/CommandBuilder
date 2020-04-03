package fr.themode.command.arguments;

public class ArgumentWord extends Argument<String> {

    public static final int SPACE_ERROR = 1;
    public static final int RESTRICTION_ERROR = 2;

    private String[] restrictions;

    public ArgumentWord(String id) {
        super(id, false);
    }

    public ArgumentWord from(String... restrictions) {
        this.restrictions = restrictions;
        return this;
    }

    @Override
    public int getCorrectionResult(String value) {
        if (value.contains(" "))
            return SPACE_ERROR;

        return Argument.SUCCESS;
    }

    @Override
    public String parse(String value) {
        return value;
    }

    @Override
    public int getConditionResult(String value) {

        // Check restrictions
        boolean findRestriction = false;
        if (restrictions != null && restrictions.length > 0) {
            for (String r : restrictions) {
                if (value.equalsIgnoreCase(r))
                    return Argument.SUCCESS;
            }
            if (!findRestriction)
                return RESTRICTION_ERROR;
        }

        return UNDEFINED_ERROR;
    }
}
