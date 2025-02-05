package org.amhe;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;

import java.util.TimeZone;

@Singleton
public class TimezoneSettings {
    public void setTimezone(@Observes StartupEvent startupEvent) {
        TimeZone.setDefault(TimeZone.getTimeZone("France"));
    }
}