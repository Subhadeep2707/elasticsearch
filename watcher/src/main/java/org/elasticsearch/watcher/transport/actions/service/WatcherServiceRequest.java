/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.watcher.transport.actions.service;

import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.ValidateActions;
import org.elasticsearch.action.support.master.MasterNodeRequest;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;
import java.util.Locale;

/**
 */
public class WatcherServiceRequest extends MasterNodeRequest<WatcherServiceRequest> {

    enum Command { START, STOP, RESTART }

    private Command command;

    public WatcherServiceRequest() {
    }

    /**
     * Starts the watcher service if not already started.
     */
    public WatcherServiceRequest start() {
        command = Command.START;
        return this;
    }

    /**
     * Stops the watcher service if not already stopped.
     */
    public WatcherServiceRequest stop() {
        command = Command.STOP;
        return this;
    }

    /**
     * Starts and stops the watcher.
     */
    public WatcherServiceRequest restart() {
        command = Command.RESTART;
        return this;
    }

    Command getCommand() {
        return command;
    }

    @Override
    public ActionRequestValidationException validate() {
        if (command == null) {
            return ValidateActions.addValidationError("no command specified", null);
        } else {
            return null;
        }
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        command = Command.valueOf(in.readString().toUpperCase(Locale.ROOT));
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeString(command.name().toLowerCase(Locale.ROOT));
    }
}
