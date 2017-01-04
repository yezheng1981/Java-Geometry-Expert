package maths;

public class TPoly {
    public TMono poly;
    public TPoly next;

    public TPoly() {
    }

    public TPoly getNext() {
        return next;
    }

    public TMono getPoly() {
        return poly;
    }

    public void setNext(TPoly next) {
        this.next = next;
    }

    public void setPoly(TMono poly) {
        this.poly = poly;
    }


    public int length() {
        TPoly tp = this;
        int i = 0;

        while (tp != null) {
            tp = tp.next;
            i++;
        }
        return i;

    }

    public long callength() {
        TPoly p = this;
        long len = 0;
        while (p != null) {
            long l = p.plength(p.getPoly()) + 1;
            len = len + l;
            p = p.getNext();
        }
        return len;
    }

    private long plength(TMono p) {
        TMono pt;
        int i;

        pt = p;
        i = -1;
        while (pt != null) {
            i = i + 1;
            pt = pt.next;
        }
        return i;
    }

}
