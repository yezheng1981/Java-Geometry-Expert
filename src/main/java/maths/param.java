package maths;

import maths.TMono;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;

public class param {
    public static int VARIABLE = 0;
    public static int STATIC = 1;

    public int type = 0;
    public int xindex;
    public double value;

    public boolean Solved = false;
    public TMono m = null;


    public param() {
    }

    public param(int index, double val) {
        xindex = index;
        value = val;
    }

    public void setParameterStatic() {
        type = STATIC;
    }

    public String toString() {
        String s = "x" + xindex;
        if (m != null && m.deg != 1)
            s += "^" + m.deg;
        return s;
    }

    public String getString() {
        String s = "x" + xindex;
        return s;
    }

    public void Save(DataOutputStream out) throws IOException {
        out.writeInt(type);
        out.writeInt(xindex);
        out.writeDouble(value);
        out.writeBoolean(Solved);
    }

    public void Load(DataInputStream in) throws IOException {
        type = in.readInt();
        xindex = in.readInt();
        value = in.readDouble();
        Solved = in.readBoolean();
    }

}
