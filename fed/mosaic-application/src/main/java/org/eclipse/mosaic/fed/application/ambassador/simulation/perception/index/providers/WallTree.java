/*
 * Copyright (c) 2023 Fraunhofer FOKUS and others. All rights reserved.
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
import org.eclipse.mosaic.lib.database.building.Building;
import org.eclipse.mosaic.lib.database.building.Wall;
import org.eclipse.mosaic.lib.math.Vector3d;
import org.eclipse.mosaic.lib.spatial.Edge;
import org.eclipse.mosaic.lib.spatial.KdTree;
import org.eclipse.mosaic.lib.spatial.SpatialItemAdapter;
import org.eclipse.mosaic.lib.spatial.SpatialTreeTraverser;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WallTree extends WallIndex {

    @Expose
    public int bucketSize = 20;

    private KdTree<Edge<Vector3d>> wallTree;
    private SpatialTreeTraverser.InRadius<Edge<Vector3d>> wallTraverser;


    @Override
    public void initialize() {
        List<Edge<Vector3d>> walls = new ArrayList<>();
        for (Building building : super.getDatabase().getBuildings()) {
            for (Wall wall : building.getWalls()) {
                walls.add(new Edge<>(
                        wall.getFromCorner().getCartesianPosition().toVector3d(),
                        wall.getToCorner().getCartesianPosition().toVector3d()
                ));
            }
        }
        wallTree = new KdTree<>(new SpatialItemAdapter.EdgeAdapter<>(), walls, bucketSize);
        wallTraverser = new org.eclipse.mosaic.lib.database.spatial.Edge.InRadius<>();
    }

    @Override
    public Collection<Edge<Vector3d>> getSurroundingWalls(PerceptionModel perceptionModel) {
        wallTraverser.setup(perceptionModel.getBoundingBox().center,
                perceptionModel.getBoundingBox().center.distanceSqrTo(perceptionModel.getBoundingBox().min)); // overestimating distance
        wallTraverser.traverse(wallTree);
        return wallTraverser.getResult();
    }
}
