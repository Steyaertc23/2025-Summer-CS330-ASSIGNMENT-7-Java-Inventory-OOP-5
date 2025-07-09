package edu.odu.cs.cs330.util;

import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.joining;


/**
 * Record all of Inventory build events and the corresponding items
 */
@SuppressWarnings({
    "PMD.BeanMembersShouldSerialize",
})
public class EventLog<E extends Enum>
{
    /**
     * A record of all attempted operations.
     */
    private final List<Entry<E>> updateRecord;

    /**
     * A single entry in the log. It consists of an Event and a short description.
     */
    public class Entry<E> extends Pair<E, String>
    {
        /**
         * Create a new entry.
         *
         * @param event type of event
         * @param description human readable description
         */
        public Entry(final E event, final String description)
        {
            super(event, description);
        }

        /**
         * Get the event.
         */
        public E event()
        {
            return super.first;
        }

        /**
         * Get the description.
         */
        public String description()
        {
            return super.second;
        }

        /**
         * Generate a printable event string.
         *
         * @param renderer callable that accepts an event type and description
         *     and yields a human readable string.
         */
        @SuppressWarnings({
            "PMD.LawOfDemeter"
        })
        public String render(final BiFunction<E, String, String> renderer)
        {
            return renderer.apply(this.event(), this.description());
        }
    }

    /**
     * Create a new empty event log.
     */
    public EventLog()
    {
        this.updateRecord = new ArrayList<>();
    }

    /**
     * Record a new event.
     *
     * @param event type of event
     * @param description human readable description
     */
    public void record(final E event, final String description)
    {
        updateRecord.add(new Entry(event, description));
    }

    /**
     * Retrieve all recorded events.
     *
     * @return all recorded events
     */
    public List<Entry<E>> getRecords()
    {
        return this.updateRecord;
    }

    /**
     * Generate a printable log that contains a description of every
     * recorded event.
     *
     * @param renderer callable that accepts an event type and description
     *     and yields a human readable string.
     */
    public String renderRecords(final BiFunction<E, String, String> renderer)
    {
        return this.getRecords()
            .stream()
            .map(entry -> entry.render(renderer))
            .collect(
                joining(System.lineSeparator(), "", System.lineSeparator())
            );
    }
}
