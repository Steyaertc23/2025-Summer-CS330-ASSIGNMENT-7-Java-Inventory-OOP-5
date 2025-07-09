package edu.odu.cs.cs330.items;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.lang.reflect.Constructor;


/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@SuppressWarnings({
    "PMD.AtLeastOneConstructor",
    "PMD.AvoidDuplicateLiterals",
    "PMD.BeanMembersShouldSerialize",
    "PMD.JUnitAssertionsShouldIncludeMessage",
    "PMD.JUnitTestContainsTooManyAsserts",
    "PMD.LocalVariableCouldBeFinal",
    "PMD.MethodArgumentCouldBeFinal",
    "PMD.LawOfDemeter"
})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestConsumable
{
    private Consumable tea;

    @BeforeEach
    public void setUp()
    {
        tea = new Consumable("Green Tea", "Wake Up", 10);
    }

    @Test
    public void testDefaultConstructor()
    {
        Consumable imagination = new Consumable();

        Item  genericRef = (Item) imagination;

        assertTrue(imagination.isStackable());
        assertTrue(genericRef.isStackable());

        assertThat(imagination.getName(), is("[Placeholder]"));
        assertThat(imagination.getEffect(), is(""));
        assertThat(imagination.getNumberOfUses(), is(0));
    }

    @Test
    public void testToString()
    {
        String expected = String.join(
            System.lineSeparator(),
            "  Nme: Green Tea",
            "  Eft: Wake Up",
            "  Use: 10",
            ""
        );

        assertThat(tea.toString(), equalTo(expected));
    }

    @Test
    public void testEquals()
    {
        Consumable generic = new Consumable();
        Consumable moreTea = new Consumable("Green Tea", "Wake Up", 12);

        assertThat(tea, not(equalTo(generic)));
        assertThat(tea, is(equalTo(moreTea)));

        moreTea = new Consumable("Green Tea", "Relax", 10);
        assertThat(tea, is(not(equalTo(moreTea))));

        // Test Consumable vs non-Consumable object
        assertThat(moreTea, not(equalTo("")));
    }

    @Test
    public void testHashCode()
    {
        Consumable moreTea = new Consumable("Green Tea", "Wake Up", 12);
        assertThat(tea.hashCode(), equalTo(moreTea.hashCode()));

        moreTea = new Consumable("Earl Grey", "Wake Up", 10);
        assertThat(tea.hashCode(), not(equalTo(moreTea.hashCode())));

        moreTea = new Consumable("Green Tea", "Relax", 10);
        assertThat(tea.hashCode(), not(equalTo(moreTea.hashCode())));
    }

    @Test
    public void testCopyConstructorDoesNotExist()
    {
        Class<?> clazz = Consumable.class;

        assertThrows(
            NoSuchMethodException.class,
            () -> {
                Constructor<?> constructor = clazz.getDeclaredConstructor(clazz);
            }
        );

    }
}
