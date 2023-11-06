package wprover;

import java.util.Objects;

public class GgbMidpoint {
    private String labelMidpoint="";
    private String labelP1;
    private String labelP2;
    private String labelSegment;

    public GgbMidpoint(String labelMidpoint,String labelP1, String labelP2) {
        this.labelMidpoint=labelMidpoint;
        this.labelP1=labelP1;
        this.labelP2=labelP2;
    }

    public GgbMidpoint(String labelMidpoint,String labelSegment) {
        this.labelMidpoint=labelMidpoint;
        this.labelSegment=labelSegment;
    }

    public GgbMidpoint(String name) {
        this.labelMidpoint=name;
    }

    public String getName() {
        return labelMidpoint;
    }

    public String getNameP1() {
        return labelP1;
    }

    public String getNameP2() {
        return labelP2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GgbMidpoint that = (GgbMidpoint) o;
        return labelMidpoint.equals(that.labelMidpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelMidpoint);
    }
}
