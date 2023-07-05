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

package org.eclipse.mosaic.test;

import org.junit.BeforeClass;
import org.junit.Test;

public class PerceptionModuleSumoIndexIT extends AbstractPerceptionModuleIT {

    @BeforeClass
    public static void runSimulation() {
        simulationRule.federateConfigurationManipulator("application", (conf) -> conf.configuration = "application_config_sumo.json");
        simulationResult = simulationRule.executeTestScenario("perception-module");
    }

    @Override
    @Test
    public void rightAmountOfTrafficLightPhaseSwitches() throws Exception {
        // SUMO perception currently doesn't support traffic lights
    }
}
