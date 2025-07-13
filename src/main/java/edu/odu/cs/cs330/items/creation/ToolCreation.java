package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.items.Item;
import edu.odu.cs.cs330.items.Tool;

@SuppressWarnings({
        "PMD.AtLeastOneConstructor"
})
public class ToolCreation implements ItemCreationStrategy {
    /**
     * Convenience wrapper to mirror the Rust new-returns-a-builder pattern.
     * Use "create" since "new" is a reserved keyword in Java.
     */
    public static ToolCreation construct() {
        return new ToolCreation();
    }

    @Override
    public Item fromDefaults() {
        // Return a **Default** Tool
        return new Tool();
    }

    @Override
    public int requiredNumberOfValues() {
        // Replace the return value;
        return 6;
    }

    @SuppressWarnings({
            "PMD.LawOfDemeter",
            "PMD.LocalVariableCouldBeFinal"
    })
    @Override
    public Item fromTokens(final String... tokens) {

        return new Tool(
                tokens[0],
                Integer.parseInt(tokens[2]),
                Integer.parseInt(tokens[3]),
                tokens[1],
                tokens[4],
                Integer.parseInt(tokens[5]));
    }

    @SuppressWarnings({
            "PMD.LawOfDemeter",
            "PMD.LocalVariableCouldBeFinal",
            "PMD.OnlyOneReturn"
    })
    @Override
    public Item fromExisting(final Item original) {
        if (!(original instanceof Tool)) {
            return null;
        }

        Tool theOriginal = (Tool) original;

        return new Tool(theOriginal.getName(), theOriginal.getDurability(), theOriginal.getSpeed(),
                theOriginal.getMaterial(), theOriginal.getModifier(), theOriginal.getModifierLevel());
    }
}
