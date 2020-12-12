public class Vector2D {
    private final Integer x;
    private final Integer y;

    public Vector2D(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D multiply(Integer distance) {
        return new Vector2D(this.x * distance, this.y * distance);
    }

    public Vector2D rotate90DegreesClockwiseAroundOrigin() {
        return new Vector2D(-this.y, this.x);
    }

    public Vector2D rotate90DegreesAnticlockwiseAroundOrigin() {
        return new Vector2D(this.y, -this.x);
    }
}
