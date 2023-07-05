/*
 * Copyright (c) 2022 Fraunhofer FOKUS and others. All rights reserved.
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

package org.eclipse.mosaic.fed.application.ambassador.simulation.perception.index.providers;

import org.eclipse.mosaic.fed.application.ambassador.simulation.perception.PerceptionModel;
import org.eclipse.mosaic.fed.application.ambassador.simulation.perception.PerceptionModuleOwner;
import org.eclipse.mosaic.fed.application.ambassador.simulation.perception.SimplePerceptionConfiguration;
import org.eclipse.mosaic.fed.application.ambassador.simulation.perception.SimplePerceptionModule;
import org.eclipse.mosaic.fed.application.ambassador.simulation.perception.index.TrafficObjectIndex;
import org.eclipse.mosaic.fed.application.ambassador.simulation.perception.index.objects.VehicleObject;
import org.eclipse.mosaic.fed.application.app.api.perception.PerceptionModule;
import org.eclipse.mosaic.lib.database.Database;

import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Trivial implementation of {@link TrafficObjectIndex}, which uses a for loop to solve the range query.
 */
public class VehicleMap extends VehicleIndex {

    @Override
    public void initialize() {
        // nothing to initialize
    }

    @Override
    public List<VehicleObject> getVehiclesInRange(PerceptionModel searchRange) {
        return indexedVehicles.values().stream()
                .filter(searchRange::isInRange)
                .collect(Collectors.toList());
    }

    @Override
    void onVehicleAdded(VehicleObject vehicleObject) {
        // do nothing
    }

    @Override
    void onIndexUpdate() {
        // do nothing
    }

    @Override
    void onVehicleRemoved(VehicleObject vehicleObject) {
        // do nothing
    }

    @Override
    public PerceptionModule<SimplePerceptionConfiguration> createPerceptionModule(PerceptionModuleOwner owner, Database database, Logger log) {
        return new SimplePerceptionModule(owner, database, log);
    }
}
