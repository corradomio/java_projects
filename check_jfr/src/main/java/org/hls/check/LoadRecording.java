package org.hls.check;

import jext.jfr.JfrEvents;
import org.openjdk.jmc.common.item.IItem;
import org.openjdk.jmc.common.item.IItemCollection;
import org.openjdk.jmc.common.item.IItemIterable;
import org.openjdk.jmc.common.item.IType;
import org.openjdk.jmc.flightrecorder.CouldNotLoadRecordingException;
import org.openjdk.jmc.flightrecorder.JfrLoaderToolkit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Finds out the standard deviation for the java monitor enter events.
 */
public class LoadRecording {

    public static void main(String[] args) throws Exception {
        // main2(args);
        main3(args);
    }

    public static void main3(String[] args) throws Exception {
        JfrEvents events = JfrEvents.loadEvents(
            new File("jfr_files/flight_recording_180241orgapachecatalinastartupBootstrapstart11876.jfr")
        );

        System.out.println(events.getThreadDumps().size());

        // events.getThreadDumps()
        //     .getDumps().forEach(dump -> {
        //         System.out.printf("%s: %d\n", dump.getName(), dump.getCallStack().size());
        //     });
        events.getThreadDumps().getEntryPoints(new ArrayList<String>() {{
            add("ae.ebtic");
        }}).forEach(ep -> {
            System.out.println(ep);
        });
    }

    public static void main2(String[] args) throws Exception {

        IItemCollection events = JfrLoaderToolkit.loadEvents(
            new File("jfr_files/flight_recording_180241orgapachecatalinastartupBootstrapstart15784_1.jfr")
        );

        // Set<IType<IItem>> skip = new HashSet<>();

        for (IItemIterable event : events) {
            IType<IItem> eventType = event.getType();

            // if (skip.contains(eventType))
            //     continue;
            // else
            //     skip.add(eventType);

            if (event.getItemCount() > 0)
                System.out.printf("%s: %s\n", eventType, event.getItemCount());

            // int count = 0;
            // for (IItem item : event) {
            //     IType<?> itemType = item.getType();
            //     System.out.printf("  %s\n", item);
            //     if (count++ > 2)
            //         break;
            // }
        }

        // IQuantity aggregate = events.apply(ItemFilters.type(JdkTypeIDs.MONITOR_ENTER))
        //     .getAggregate(Aggregators.stddev(JfrAttributes.DURATION));
        //
        // System.out.println("The standard deviation for the Java monitor enter events was "
        //     + aggregate.displayUsing(IDisplayable.AUTO));
    }
}