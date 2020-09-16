package org.hls.check;

import org.openjdk.jmc.common.item.IItem;
import org.openjdk.jmc.common.item.IItemCollection;
import org.openjdk.jmc.common.item.IItemIterable;
import org.openjdk.jmc.common.item.IType;
import org.openjdk.jmc.flightrecorder.JfrLoaderToolkit;

import java.io.File;

/**
 * Finds out the standard deviation for the java monitor enter events.
 */
public class LoadRecording {
    public static void main(String[] args) throws Exception {
        IItemCollection events = JfrLoaderToolkit.loadEvents(
            new File("jfr_files/flight_recording_180241orgapachecatalinastartupBootstrapstart15784.jfr")
        );

        for (IItemIterable event : events) {
            IType<IItem> eventType = event.getType();
            System.out.printf("%s: %s\n", eventType, event.getItemCount());

            int count = 0;
            for (IItem item : event) {
                IType<?> itemType = item.getType();
                System.out.printf("  %s\n", item);
                if (count++ > 10)
                    break;
            }
        }

        // IQuantity aggregate = events.apply(ItemFilters.type(JdkTypeIDs.MONITOR_ENTER))
        //     .getAggregate(Aggregators.stddev(JfrAttributes.DURATION));
        //
        // System.out.println("The standard deviation for the Java monitor enter events was "
        //     + aggregate.displayUsing(IDisplayable.AUTO));
    }
}