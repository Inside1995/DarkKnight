package ru.artur.darkknight.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public static String getTimeBetween(Date start, Date end) {
        long timeDiff = end.getTime() - start.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(timeDiff);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeDiff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeDiff));

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
