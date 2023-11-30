package wprover;

import maths.TPoly;

import java.util.Vector;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-8-11
 * Time: 9:34:44
 * To change this template use File | Settings | File Templates.
 */

class UndoStruct {
    static public int INDEX = 0;

    final public static int T_UNDO_NODE = 0;
    final public static int T_TO_PROVE_NODE = 1;
    final public static int T_PROVE_NODE = 2;

    final public static int T_COMBINED_NODE = 3;

    final public static int T_ROOT = 98;
    final public static int T_PLAIN_TEXT = 99;


    public UndoStruct() {
    }

    public UndoStruct(int pc) {
        m_id = INDEX++;

        m_type = T_UNDO_NODE;
        paraCounter = pc;
        id = CMisc.id_count;
    }

    public UndoStruct(int type, int pc) {
        m_id = INDEX++;
        m_type = type;
        paraCounter = pc;
        id = CMisc.id_count;
    }

    int m_id;

    int m_type;
    boolean done = false;
    boolean flash = false;
    int action;
    int id = 0;
    int current_id = 0;
    int paraCounter = 1;
    int pnameCounter = 0;
    int plineCounter = 0;
    int pcircleCounter = 0;

    String msg = new String(); // information about this step;

    int id_b = 0;
    int paraCounter_b = 1;
    int pnameCounter_b = 0;
    int plineCounter_b = 0;
    int pcircleCounter_b = 0;


    //////////////////////////////////////////////////////
    TPoly polylist = null;
    TPoly pblist = null;

    Vector pointlist = new Vector();
    Vector linelist = new Vector();
    Vector circlelist = new Vector();
    Vector anglelist = new Vector();
    Vector constraintlist = new Vector();
    Vector distancelist = new Vector();
    Vector polygonlist = new Vector();
    Vector textlist = new Vector();
    Vector tracklist = new Vector();
    Vector otherlist = new Vector();
    ////////////////////////////
    Vector objectlist = new Vector(); // object related to this node.
    Vector childundolist = new Vector();

    ////////////////////////////////////////////// added 2006.7.8.
    Vector dlist = new Vector();

    public void adddOjbect(Object obj) {
        dlist.add(obj);
    }

    public String toString() {
        if (dlist.size() == 0) {
            return msg;
        } else {
            if (action == drawbase.D_PARELINE) {
                CLine ln1 = ((CLine) dlist.get(0));
                CLine ln2 = ((CLine) dlist.get(1));
                CPoint p = ((CPoint) dlist.get(2));
                return ln1.getDiscription() + " paral " + ln2.getDiscription() +
                        " passing " + p.getname();

            } else if (action == drawbase.D_PERPLINE) {
                CLine ln1 = ((CLine) dlist.get(0));
                CLine ln2 = ((CLine) dlist.get(1));
                CPoint p = ((CPoint) dlist.get(2));
                return ln1.getDiscription() + " perp " + ln2.getDiscription() +
                        " passing " + p.getname();
            } else {
                return msg;
            }
        }
    }

    public void clear() {
        polylist = pblist = null;
        pointlist.clear();
        linelist.clear();
        circlelist.clear();
        anglelist.clear();
        distancelist.clear();
        constraintlist.clear();
        textlist.clear();
        polygonlist.clear();
        otherlist.clear();

    }

    public void draw(Graphics2D g2) {
        drawlist(polygonlist, g2);
        drawlist(tracklist, g2);
        drawlist(distancelist, g2);
        drawlist(anglelist, g2);
        drawlist(linelist, g2);
        drawlist(circlelist, g2);

        drawlist(pointlist, g2);

        drawlist(textlist, g2);
        drawlist(otherlist, g2);

    }

    public UndoStruct getUndoStructByid(int id) {
        if (this.m_id == id) {
            return this;
        } else {
            for (int i = 0; i < childundolist.size(); i++) {
                UndoStruct un = (UndoStruct) childundolist.get(i);
                UndoStruct u1 = un.getUndoStructByid(id);
                if (u1 != null) {
                    return u1;
                }
            }
            return null;
        }
    }

    public void setInFlashing(boolean inflash) {
        setListInFlashing(objectlist, inflash);
    }

    private void setListInFlashing(Vector v, boolean inflash) {
        for (int i = 0; i < v.size(); i++) {
            CClass cc = (CClass) v.get(i);
            cc.setInFlashing(inflash);

        }
    }

    private void drawlist(Vector v, Graphics2D g2) {
        for (int i = 0; i < v.size(); i++) {
            CClass c = (CClass) v.get(i);
            c.draw(g2);
        }
    }

    public void addRelatedObject(CClass cc) {
        if (!objectlist.contains(cc)) {
            objectlist.add(cc);
        }
    }

    public boolean isNodeValued() {
        // return pointlist.size() != 0 ;
        if (m_id - id_b != 0) return true;

        return pnameCounter - pnameCounter_b != 0
                || plineCounter_b - plineCounter != 0
                || plineCounter_b - plineCounter != 0;
    }


    public void merge(UndoStruct undo1, UndoStruct undo2) {

        action = -1; // combined.
        msg = "cb";

        id = undo1.id;
        current_id = undo1.current_id;
        paraCounter = undo1.paraCounter;
        pnameCounter = undo1.pnameCounter;
        plineCounter = undo1.plineCounter;
        pcircleCounter = undo1.pcircleCounter;

        id_b = undo2.id_b;
        paraCounter_b = undo2.paraCounter_b;
        pnameCounter_b = undo2.pnameCounter_b;
        plineCounter_b = undo2.plineCounter_b;
        pcircleCounter_b = undo2.pcircleCounter_b;

    }

    public void addchild(UndoStruct u) {
        if (!childundolist.contains(u)) {
            childundolist.add(u);
        }
    }

    public void addObjectRelatedList(Vector v) {
        for (int i = 0; i < v.size(); i++) {
            Object obj = v.get(i);
            if (!objectlist.contains(obj)) {
                objectlist.add(obj);
            }
        }
    }

    public Vector getAllObjects(drawProcess dp) {
        Vector v = new Vector();

        if (this.m_type == T_UNDO_NODE) {
            dp.selectUndoObjectFromList(v, dp.pointlist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.linelist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.circlelist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.anglelist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.distancelist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.polygonlist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.textlist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.tracelist, id, id_b);
            dp.selectUndoObjectFromList(v, dp.otherlist, id, id_b);

        } else if (this.m_type == UndoStruct.T_COMBINED_NODE) {
            for (int i = 0; i < childundolist.size(); i++) {
                UndoStruct un = (UndoStruct) childundolist.get(i);
                Vector vt = un.getAllObjects(dp);
                v.addAll(vt);
            }
        } else if (this.m_type == UndoStruct.T_PROVE_NODE) {
            for (int i = 0; i < childundolist.size(); i++) {
                UndoStruct un = (UndoStruct) childundolist.get(i);
                Vector vt = un.getAllObjects(dp);
                v.addAll(vt);
            }
        }

        v.addAll(objectlist);
        return v;
    }

    public void SaveList(DataOutputStream out, Vector v) throws IOException {
        int n = v.size();
        if (n > 999) {
            int k = 0;
        }
        out.writeInt(n);
        for (int i = 0; i < v.size(); i++) {
            CClass cc = (CClass) v.get(i);
            out.writeInt(cc.m_id);
        }
    }

    public Vector ReadList(DataInputStream in, drawProcess dp) throws
            IOException {
        int size = in.readInt();
        Vector v = new Vector();

        for (int i = 0; i < size; i++) {
            int id = in.readInt();
            Object obj = dp.getOjbectById(id);
            if (obj != null) {
                v.add(obj);
            }
        }
        return v;
    }


    public void Save(DataOutputStream out) throws IOException {

        out.writeInt(m_id);
        out.writeInt(m_type);
        out.writeBoolean(done);
        out.writeBoolean(flash);
        out.writeInt(action);

        out.writeInt(id);
        out.writeInt(current_id);
        out.writeInt(paraCounter);
        out.writeInt(pnameCounter);
        out.writeInt(plineCounter);
        out.writeInt(pcircleCounter);

        // for 0.11
        int n = 0;
        if (msg != null) {
            byte[] b = msg.getBytes();
            n = b.length;
            out.writeInt(n);
            if (n > 0)
                out.write(b);
        } else
            out.writeInt(n);

//        if (msg != null && n > 0) {
//            byte[] b = msg.getBytes();
//            out.write(b);
//        }

        out.writeInt(id_b);
        out.writeInt(paraCounter_b);
        out.writeInt(pnameCounter_b);
        out.writeInt(plineCounter_b);
        out.writeInt(pcircleCounter_b);

        SaveList(out, objectlist);

        out.writeInt(childundolist.size());

        if (childundolist.size() != 0) {
            for (int i = 0; i < childundolist.size(); i++) {
                UndoStruct u = (UndoStruct) childundolist.get(i);
                u.Save(out);
            }

        }
    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {
        if (CMisc.version_load_now >= 0.019) {
            m_id = in.readInt();

        } else {
            m_id = CMisc.id_count++;
        }

        if (CMisc.version_load_now >= 0.015) {
            m_type = in.readInt();
        }
        if (CMisc.version_load_now > 0.01) {
            this.done = in.readBoolean();
            this.flash = in.readBoolean();
            action = in.readInt();
        }

        if (CMisc.version_load_now < 0.01) {
            int size = in.readInt();
            byte[] str = new byte[size];
            in.read(str, 0, size);
        }
        id = in.readInt();
        current_id = in.readInt();
        paraCounter = in.readInt();
        pnameCounter = in.readInt();
        plineCounter = in.readInt();
        pcircleCounter = in.readInt();

        // for 0.01
        if (CMisc.version_load_now > 0.010) {
            int size = in.readInt();
            if (size > 0) {
                byte[] str = new byte[size];
                in.read(str, 0, str.length);
                msg = new String(str);
            }
        }

        id_b = in.readInt();
        paraCounter_b = in.readInt();
        pnameCounter_b = in.readInt();
        plineCounter_b = in.readInt();
        pcircleCounter_b = in.readInt();

        if (CMisc.version_load_now >= 0.016) {
            objectlist.addAll(this.ReadList(in, dp));
        }

        if (CMisc.version_load_now >= 0.012) {
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                UndoStruct u = new UndoStruct(-1, -1);
                u.Load(in, dp);
                childundolist.add(u);
            }
            if (m_type == T_UNDO_NODE && childundolist.size() > 0) {
                m_type = T_COMBINED_NODE;
            }
        }
    }
}
