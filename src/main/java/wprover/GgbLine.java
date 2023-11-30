package wprover;

import java.util.Objects;

public class GgbLine {
    private String name;
    private String nameP1;
    private String nameP2;

    public GgbLine(String name, String nameP1, String nameP2) {
        this.name = name;
        this.nameP1 = nameP1;
        this.nameP2 = nameP2;
    }

    public GgbLine(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getNameP1() {
        return nameP1;
    }

    public String getNameP2() {
        return nameP2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GgbLine ggbLine = (GgbLine) o;
        return Objects.equals(name, ggbLine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
