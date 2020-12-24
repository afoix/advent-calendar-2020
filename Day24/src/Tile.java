import java.util.Objects;

public class Tile {

    private final Integer x;
    private final Integer y;

    public Tile(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        Tile other = (Tile) obj;
        return this.x == other.x && this.y == other.y;
    }
}
