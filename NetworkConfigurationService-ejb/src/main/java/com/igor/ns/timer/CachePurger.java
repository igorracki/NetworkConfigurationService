package com.igor.ns.timer;

import com.igor.ns.event.CachePurgeEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Startup
@Singleton
public class CachePurger {

    private static final long INTERVAL = 60_000;

    @Resource
    private TimerService timerService;

    @Inject
    private Event<CachePurgeEvent> cachePurgeEvent;

    @PostConstruct
    public void setupTimer() {
        timerService.createTimer(0, INTERVAL, "Purging the cache.");
    }

    @PreDestroy
    public void stopTimer() {
        timerService.getTimers().forEach(Timer::cancel);
    }

    @Timeout
    public void timeoutHandler(final Timer timer) {
        cachePurgeEvent.fire(new CachePurgeEvent("Purge the cache!"));
    }
}
