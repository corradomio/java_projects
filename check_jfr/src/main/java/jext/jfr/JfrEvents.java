package jext.jfr;

import org.openjdk.jmc.common.item.IItemCollection;
import org.openjdk.jmc.common.item.IItemIterable;
import org.openjdk.jmc.flightrecorder.CouldNotLoadRecordingException;
import org.openjdk.jmc.flightrecorder.JfrLoaderToolkit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JfrEvents {

    // ----------------------------------------------------------------------
    // Static methods
    // ----------------------------------------------------------------------

    public static JfrEvents loadEvents(File eventsFile) throws IOException, CouldNotLoadRecordingException {
        IItemCollection events = JfrLoaderToolkit.loadEvents(eventsFile);

        return new JfrEvents(events);
    }

    // ----------------------------------------------------------------------
    // Provate fields
    // ----------------------------------------------------------------------

    private IItemCollection events;

    // ----------------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------------

    public JfrEvents(IItemCollection events) {
        this.events = events;
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    private static final String THREAD_DUMP = "jdk.ThreadDump";

    public ThreadDumps getThreadDumps() {

        List<IItemIterable> threadDumps = new ArrayList<>();

        for (IItemIterable iterableEvent : events) {
            String id = iterableEvent.getType().getIdentifier();
            if (THREAD_DUMP.equals(id))
                threadDumps.add(iterableEvent);
        }

        return new ThreadDumps(threadDumps);
    }

    // ----------------------------------------------------------------------
    // End
    // ----------------------------------------------------------------------

}
