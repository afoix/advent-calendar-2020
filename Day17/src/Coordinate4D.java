import java.util.Objects;

public class Coordinate4D {
    private final int x;
    private final int y;
    private final int z;
    private final int w;

    public Coordinate4D(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public int getW() { return w; }


    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate4D other = (Coordinate4D) obj;
        return this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w;
    }

    public static Coordinate4D min(Coordinate4D coordinateA, Coordinate4D coordinateB) {
        return new Coordinate4D(coordinateA.x < coordinateB.x ? coordinateA.x : coordinateB.x,
                coordinateA.y < coordinateB.y ? coordinateA.y : coordinateB.y,
                coordinateA.z < coordinateB.z ? coordinateA.z : coordinateB.z,
                coordinateA.w < coordinateB.w ? coordinateA.w : coordinateB.w);
    }

    public static Coordinate4D max(Coordinate4D coordinateA, Coordinate4D coordinateB) {
        return new Coordinate4D(coordinateA.x > coordinateB.x ? coordinateA.x : coordinateB.x,
                coordinateA.y > coordinateB.y ? coordinateA.y : coordinateB.y,
                coordinateA.z > coordinateB.z ? coordinateA.z : coordinateB.z,
                coordinateA.w > coordinateB.w ? coordinateA.w : coordinateB.w);
    }
}
