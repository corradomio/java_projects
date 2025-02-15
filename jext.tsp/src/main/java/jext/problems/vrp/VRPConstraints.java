package jext.problems.vrp;

public class VRPConstraints {

    public double maxDistance = Double.MAX_VALUE;
    public int maxVehicleCapacity = Integer.MAX_VALUE;

    public boolean hasDistanceConstraint() {
        return maxDistance != Double.MAX_VALUE;
    }

    public boolean hasVehicleCapacityConstraint() {
        return maxVehicleCapacity != Integer.MAX_VALUE;
    }
}
