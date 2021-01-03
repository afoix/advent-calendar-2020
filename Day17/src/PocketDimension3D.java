import java.util.HashSet;
import java.util.Iterator;

public class PocketDimension3D {

    private final HashSet<Coordinate3D> activeCubes = new HashSet<>();

    public void setCubeActive(Coordinate3D position, boolean isActive) {
        if (isActive) {
            activeCubes.add(position);
        } else {
            activeCubes.remove(position);
        }
    }

    public boolean isCubeActive(Coordinate3D position) {
        return activeCubes.contains(position);
    }

    public long totalCubesActive() {
        return activeCubes.size();
    }

    public Coordinate3D getMinimumActivePosition() {
        Iterator<Coordinate3D> iterator = activeCubes.iterator();
        Coordinate3D result = iterator.next();
        while (iterator.hasNext()) {
            result = Coordinate3D.min(result, iterator.next());
        }
        return result;

    }

    public Coordinate3D getMaximumActivePosition() {
        Iterator<Coordinate3D> iterator = activeCubes.iterator();
        Coordinate3D result = iterator.next();
        while (iterator.hasNext()) {
            result = Coordinate3D.max(result, iterator.next());
        }
        return result;
    }

    public int countActiveNeighbors(Coordinate3D position) {
        int result = 0;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Coordinate3D neighbor = new Coordinate3D(
                            position.getX() + x,
                            position.getY() + y,
                            position.getZ() + z);
                    if (neighbor.equals(position)) {
                        continue;
                    }
                    if (activeCubes.contains(neighbor)) {
                        result++;
                    }
                }
            }
        }

        return result;
    }
}
