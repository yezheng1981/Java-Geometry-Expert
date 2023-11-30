package wprover;

import java.util.Objects;

public class GgbCircle {
    private String name;

    public GgbCircle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GgbCircle ggbCircle = (GgbCircle) o;
        return Objects.equals(name, ggbCircle.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
