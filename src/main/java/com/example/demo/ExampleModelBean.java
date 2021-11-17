package com.example.demo;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
@ManagedResource
public class ExampleModelBean {
    private int lineLength;
    private int delay;
    private List<Consumer<Boolean>> stopListeners=new ArrayList<>();

    @ManagedAttribute
    public int getLineLength() {
        return lineLength;
    }

    @ManagedAttribute
    public int getDelay() {
        return delay;
    }

    @ManagedOperation
    public int setLineLength(int lineLength) {
        this.lineLength = lineLength;
        return this.lineLength;
    }

    @ManagedOperation
    public int setDelay(int delay) {
        this.delay = delay;
        return this.delay;
    }

    @ManagedOperation
    public void stop() {
        stopListeners.forEach(c->c.accept(true));
    }

    @ManagedOperation
    public void start() {
        stopListeners.forEach(c->c.accept(false));
    }

    public void addStopListener(Consumer<Boolean> stopListener) {
        stopListeners.add(stopListener);
    }
}
