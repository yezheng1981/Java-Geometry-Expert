package wprover;

import java.util.Objects;

public class GgbPoint {
    private String name;

    public GgbPoint(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GgbPoint ggbPoint = (GgbPoint) o;
        return Objects.equals(name, ggbPoint.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
