import java.util.HashSet;
import java.util.Set;

public class Instruction {
    private final Integer position;
    private final String type;
    private final Integer value;
    private Set<Integer> color;

    public Instruction(Integer position, String type, Integer value) {
        this.position = position;
        this.type = type;
        this.value = value;
        this.color = new HashSet<Integer>();
    }

    public Integer getPosition() { return position; }

    public String getType() { return type; }

    public Integer getValue() { return value; }

    public Integer positionAfter(Integer positionBefore) {
        switch(type) {
            case "jmp":
                return positionBefore + value;
            default:
                return positionBefore + 1;
        }
    }

    @Override
    public String toString() {
        return type + ' ' + value + "( color " + color + ")";
    }

    public boolean hasAnyColors() {
        return !color.isEmpty();
    }

    public boolean hasColor(Integer color) { return this.color.contains(color); }

    public void addColor(Integer color) {
        this.color.add(color);
    }

    public Set<Integer> getColors() { return this.color; }
}
