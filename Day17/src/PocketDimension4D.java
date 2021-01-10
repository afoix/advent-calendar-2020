import java.util.HashSet;
import java.util.Iterator;

public class PocketDimension4D {

    private final HashSet<Coordinate4D> activeCubes = new HashSet<>();

    public void setCubeActive(Coordinate4D position, boolean isActive) {
        if (isActive) {
            activeCubes.add(position);
        } else {
            activeCubes.remove(position);
        }
    }

    public boolean isCubeActive(Coordinate4D position) {
        return activeCubes.contains(position);
    }

    public long totalCubesActive() {
        return activeCubes.size();
    }

    public Coordinate4D getMinimumActivePosition() {
        Iterator<Coordinate4D> iterator = activeCubes.iterator();
        Coordinate4D result = iterator.next();
        while (iterator.hasNext()) {
            result = Coordinate4D.min(result, iterator.next());
        }
        return result;

    }

    public Coordinate4D getMaximumActivePosition() {
        Iterator<Coordinate4D> iterator = activeCubes.iterator();
        Coordinate4D result = iterator.next();
        while (iterator.hasNext()) {
            result = Coordinate4D.max(result, iterator.next());
        }
        return result;
    }

    public int countActiveNeighbors(Coordinate4D position) {
        int result = 0;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    for (int w = -1; w <= 1; w++) {
                        Coordinate4D neighbor = new Coordinate4D(
                                position.getX() + x,
                                position.getY() + y,
                                position.getZ() + z,
                                position.getW() + w);
                        if (neighbor.equals(position)) {
                            continue;
                        }
                        if (activeCubes.contains(neighbor)) {
                            result++;
                    }

                    }
                }
            }
        }

        return result;
    }
}
