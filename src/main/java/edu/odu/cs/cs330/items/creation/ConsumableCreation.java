package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.items.Consumable;
import edu.odu.cs.cs330.items.Item;

@SuppressWarnings({
        "PMD.AtLeastOneConstructor"
})
public class ConsumableCreation implements ItemCreationStrategy {
    /**
     * Convenience wrapper to mirror the Rust new-returns-a-builder pattern.
     * Use "construct" since "new" is a reserved keyword in Java.
     */
    public static ConsumableCreation construct() {
        return new ConsumableCreation();
    }

    @Override
    public Item fromDefaults() {
        // Return a **Default** Consumable
        return new Consumable();
    }

    @Override
    public int requiredNumberOfValues() {
        // Replace the return value;
        return 3;
    }

    @SuppressWarnings({
            "PMD.LawOfDemeter",
            "PMD.LocalVariableCouldBeFinal"
    })
    @Override
    public Item fromTokens(final String... tokens) {
        return new Consumable(tokens[0], tokens[1], Integer.parseInt(tokens[2]));

    }

    @SuppressWarnings({
            "PMD.LawOfDemeter",
            "PMD.LocalVariableCouldBeFinal",
            "PMD.OnlyOneReturn"
    })
    @Override
    public Item fromExisting(final Item original) {
        if (!(original instanceof Consumable)) {
            return null;
        }

        Consumable theOriginal = (Consumable) original;

        return new Consumable(theOriginal.getName(), theOriginal.getEffect(), theOriginal.getNumberOfUses());
    }
}
