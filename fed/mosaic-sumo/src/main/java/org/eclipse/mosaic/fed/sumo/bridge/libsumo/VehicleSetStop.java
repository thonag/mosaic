/*
 * Copyright (c) 2021 Fraunhofer FOKUS and others. All rights reserved.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contact: mosaic@fokus.fraunhofer.de
 */

package org.eclipse.mosaic.fed.sumo.bridge.libsumo;

import org.eclipse.mosaic.fed.sumo.bridge.Bridge;
import org.eclipse.mosaic.rti.TIME;

import org.eclipse.sumo.libsumo.Vehicle;

public class VehicleSetStop implements org.eclipse.mosaic.fed.sumo.bridge.api.VehicleSetStop {

    /**
     * This method executes the command with the given arguments in order to set the stop mode for the vehicle.
     *
     * @param bridge    Connection to SUMO.
     * @param vehicleId The Id of the vehicle to change the route.
     * @param edgeId    The Id of the edge on which to stop.
     * @param position  The position of the stop.
     * @param laneIndex The index of the lane on which to stop.
     * @param duration  The duration for stop in [ns].
     * @param stopFlag  The flag indicating the type of the stop.
     */
    public void execute(Bridge bridge, String vehicleId, String edgeId, double position, int laneIndex, long duration, int stopFlag) {
        Vehicle.setStop(
                Bridge.VEHICLE_ID_TRANSFORMER.toExternalId(vehicleId),
                edgeId,
                position,
                laneIndex,
                duration / (double) TIME.SECOND,
                stopFlag
        );
    }

}
