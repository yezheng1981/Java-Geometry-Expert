package gprover;

public class rule extends cclass{

    public final static int SPLIT_ANGLE = 0;
    public final static int P_ANGLE = 1;
    public final static int T_ANGLE = 2;
    public final static int EX_ANGLE = 3;
    public final static int EQ_ANGLE = 4;


    int type;
    int no;
    public mnde[] mr1 = new mnde[5];
    public mnde mr;


    rule nx;
    public rule(int t) {
        type = t;
        nx = null;
        mr = null;
    }

    public void cp_rule(rule r)
    {
        type = r.type;
        no = r.no;
        for(int i=0; i <5 ; i++)
            mr1[i] = r.mr1[i];
    }

    public String toString()
    {
        return "   because " + text;
    }

}
