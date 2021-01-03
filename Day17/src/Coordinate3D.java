import java.util.Objects;

public class Coordinate3D {
    private final int x;
    private final int y;
    private final int z;

    public Coordinate3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate3D other = (Coordinate3D) obj;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    public static Coordinate3D min(Coordinate3D coordinateA, Coordinate3D coordinateB) {
        return new Coordinate3D(coordinateA.x < coordinateB.x ? coordinateA.x : coordinateB.x,
                coordinateA.y < coordinateB.y ? coordinateA.y : coordinateB.y,
                coordinateA.z < coordinateB.z ? coordinateA.z : coordinateB.z);
    }

    public static Coordinate3D max(Coordinate3D coordinateA, Coordinate3D coordinateB) {
        return new Coordinate3D(coordinateA.x > coordinateB.x ? coordinateA.x : coordinateB.x,
                coordinateA.y > coordinateB.y ? coordinateA.y : coordinateB.y,
                coordinateA.z > coordinateB.z ? coordinateA.z : coordinateB.z);
    }
}
