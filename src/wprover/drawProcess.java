package wprover;

import gprover.*;

import javax.swing.*;
import java.util.Vector;
import java.util.Calendar;
import java.util.Locale;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.*;
import java.io.*;

import maths.TPoly;
import maths.TMono;
import maths.param;
import maths.PolyBasic;
import maths.CharSet;


public class
        drawProcess extends drawbase implements Printable, ActionListener {


    final public static int SELECT = 0;
    final public static int MOVE = 34;
    final public static int VIEWELEMENT = 35;

    final public static int TRANSLATE = 40;
    final public static int ZOOM_IN = 41;
    final public static int ZOOM_OUT = 42;
    final public static int SETTRACK = 43;
    final public static int ANIMATION = 60;
    final public static int DEFINEPOLY = 61;
    final public static int MULSELECTSOLUTION = 62;
    final public static int MOVENAME = 63;
    final public static int AUTOSHOWSTEP = 64;
    final public static int EQMARK = 65;
    final public static int PROVE = 66;
    final public static int TRIANGLE = 67;
    final public static int HIDEOBJECT = 68;

    final public static int DRAWTRIALL = 69; //
    final public static int DRAWTRISQISO = 71;

    final public static int PARALLELOGRAM = 72;
    final public static int RECTANGLE = 73;

    final public static int TRAPEZOID = 74;
    final public static int RA_TRAPEZOID = 75;
    final public static int SETEQSIDE = 76;
    final public static int SHOWOBJECT = 77;
    final public static int SETEQANGLE3P = 78;
    final public static int SETCCTANGENT = 79;
    final public static int NTANGLE = 80;
    final public static int SANGLE = 81;
    final public static int RATIO = 82;
    final public static int RAMARK = 83;
    final public static int TRANSFORM = 84;
    final public static int EQUIVALENCE = 85;
    final public static int FREE_TRANSFORM = 86;
    final public static int LOCUS = 87;

    final public static int ARROW = 88;

    final public static int CONSTRUCT_FROM_TEXT = 100;


    Vector undolist = new Vector();
    Vector redolist = new Vector();
    UndoStruct currentUndo = new UndoStruct(1);

    //    CPoint trackPoint = null;
    protected CPoint CTrackPt = null;

    AnimateC animate = null;
    CProveField cpfield = null;
    int pfn = 0; // max 4

    protected JPanel panel;
    private CPoint pSolution = null;
    private Vector solutionlist = new Vector();
    private CPoint FirstPnt = null;
    private CPoint SecondPnt = null;
    private CPoint ThirdPnt = null;


    private int proportion = 0;
    private UndoStruct undo = null;
    private Timer timer = null;
    private int timer_type; // 1: autoundoredo , 2: prove;

    private boolean IsButtonDown = false;
    private boolean isRecal = true;
    private int v1, v2;
    private double vx1, vy1, vangle = 0;
    private double vtrx, vtry = 0;

    private int PreviousAction;
    private Vector updaterListeners = new Vector();
    private boolean needSave = false;
    private int save_id = CMisc.id_count;
    private int CAL_MODE = 0; // 0: MOVEMODE. 1. CAL

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected gterm gt;
    protected int nd = 1;

    ////////////////
    protected UndoStruct U_Obj = null;
    protected boolean status = true;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void stateChange() {
        status = !status;
    }

    public void setCalMode1() {
        CAL_MODE = 1;
    }

    public void setCalMode0() {
        CAL_MODE = 0;
    }

    public gterm gterm() {
        if (gt == null)
            gt = gxInstance.getpprove().getConstructionTerm();
        return gt;
    }

    public void clearConstruction() {
        gt = null;
        nd = 1;
    }

    public void resetUndo() {
        this.currentUndo.id = CMisc.id_count;
    }

    public String getName() {
        return this.name;
    }

    public void setRecal(boolean r) {
        isRecal = r;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void stopTrack() {
        CTrackPt = null;
    }

    public void startTrackPt(CPoint pt) {

        CTrackPt = pt;

        boolean r = false;
        for (int i = 0; i < tracelist.size(); i++) {
            CTrace tr = (CTrace) tracelist.get(i);
            if (tr.isTracePt(CTrackPt)) {
                r = true;
                break;

            }
        }
        if (!r) {
            CTrace t = new CTrace(CTrackPt);
            this.addObjectToList(t, tracelist);
            this.UndoAdded(t.toString());
        }

    }


    public param getParameterByindex(int index) {
        for (int i = 0; i < paraCounter - 1; i++) {
            if (parameter[i].xindex == index) {
                return parameter[i];
            }
        }
        return null;
    }

    public CPoint getLastConstructedPoint() {
        if (pointlist.size() <= 0)
            return null;
        return (CPoint) pointlist.get(pointlist.size() - 1);
    }

    public CPoint getPointById(int id) {
        return (CPoint) this.getObjectInListById(id, pointlist);
    }


    public Vector getAllConstraint() {
        return constraintlist;
    }

    public constraint getConstraintByid(int id) {
        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);
            if (cs.id == id) {
                return cs;
            }
        }
        return null;
    }

    public CLine getLineByid(int id) {
        return (CLine) this.getObjectInListById(id, linelist);
    }

    public Circle getCircleByid(int id) {
        return (Circle) this.getObjectInListById(id, circlelist);
    }


    public CTrace getTraceById(int id) {
        return (CTrace) this.getObjectInListById(id, tracelist);
    }


    public CAngle getAngleByid(int id) {
        return (CAngle) this.getObjectInListById(id, anglelist);
    }

    public Vector getAllSolidObj() {
        Vector v = new Vector();
        int n = CMisc.id_count + 1;
        for (int i = 1; i <= n; i++) {
            Object o = getOjbectById(i);
            if (o instanceof CText) {
                CText tt = (CText) o;
                if (tt.getType() != CText.NORMAL_TEXT)
                    continue;
            }
            if (o != null)
                v.add(o);
        }
        return v;
    }

    public CClass getOjbectById(int id) {
        CClass cc = this.getObjectInListById(id, pointlist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, linelist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, circlelist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, anglelist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, distancelist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, polygonlist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, textlist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, tracelist);
        if (cc != null) {
            return cc;
        }
        cc = this.getObjectInListById(id, otherlist);
        return cc;
    }

    public CClass getObjectInListById(int id, Vector v) {
        for (int i = 0; i < v.size(); i++) {
            CClass cc = (CClass) v.get(i);
            if (cc.m_id == id) {
                return cc;
            }
        }
        return null;

    }

    public UndoStruct getUndoById(int id) {
        for (int i = 0; i < undolist.size(); i++) {
            UndoStruct cc = (UndoStruct) undolist.get(i);
            UndoStruct c1 = cc.getUndoStructByid(id);

            if (c1 != null) {
                return c1;
            }
        }
        CMisc.print("Can not find " + id + " in undo list");
        return null;
    }

    public void SetProportionLineAction(int prop) {

        this.SetCurrentAction(drawProcess.LRATIO);
        this.proportion = prop;

    }

    public void addDiagramUpdaterListener(DiagramUpdater d) {
        if (!updaterListeners.contains(d))
            updaterListeners.add(d);
    }

    public void RemoveDiagramUpdaterListener(DiagramUpdater d) {
        updaterListeners.remove(d);
    }

    public void SetActionWithPropotion(int action, int prop) {
        this.SetCurrentAction(action);
        this.proportion = prop;
    }

    public void clearAll() {
        CurrentAction = SELECT;
        SelectList.clear();
        CatchList.clear();
        pointlist.clear();
        linelist.clear();
        circlelist.clear();
        clearAllConstraint();
        textlist.clear();
        distancelist.clear();
        tracelist.clear();
        polygonlist.clear();
        otherlist.clear();

        paraCounter = 1;
        FirstPnt = SecondPnt = null;
        IsButtonDown = false;
        polylist = null;
        pblist = null;
        anglelist.clear();
        pnameCounter = 0;
        plineCounter = 1;
        pcircleCounter = 1;
        STATUS = 0;
        pSolution = null;
        solutionlist.clear();

//        trackPoint = null;
        undolist.clear();
        redolist.clear();
        CMisc.id_count = 1;

        this.currentUndo = new UndoStruct(this.paraCounter);
        CCoBox.resetAll();
        drawData.setDefaultStatus();
        undo = null;
        animate = null;
        cpfield = null;

        this.clearFlash();
        if (gxInstance != null && gxInstance.hasAFrame()) {
            AnimatePanel ac = gxInstance.getAnimateDialog();
            if (ac != null)
                ac.stopA();
        }
        clearConstruction();
        for (int i = 0; i < parameter.length; i++) {
            parameter[i] = null;
        }
        for (int i = 0; i < paraBackup.length; i++) {
            paraBackup[i] = 0.0;
        }
        CTrackPt = null;

        file = null;
        vx1 = vy1 = 0.0;
        vtrx = vtry = 0;
        vangle = 0.0;
        CMisc.Reset();
        needSave = false;
        save_id = CMisc.id_count;
        poly.clearZeroN();
        name = "";
        CAL_MODE = 0;
        status = true;

    }

    public void setSavedTag() {
        needSave = false;
        save_id = CMisc.id_count;
    }

    public boolean isitSaved() {
        return needSave || save_id >= CMisc.id_count;
    }

    public AnimateC getAnimateC() {
        return animate;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File f) {
        file = f;
    }


    public Vector getSelectList() {
        return SelectList;
    }

    public void SetSnap(boolean snap) {
        this.SNAP = snap;

    }

    public int getStatus() {
        return STATUS;
    }

    public void setStatus(int t) {
        STATUS = t;
    }

    public boolean isSnap() {
        return this.SNAP;
    }

    public void SetGrid(boolean grid) {
        this.DRAWGRID = grid;

    }

    public boolean isDrawGrid() {
        return this.DRAWGRID;
    }

    public void setGridXY(int n) {
        if (n > 0)
            GridX = GridY = n;
    }

    public void setMeshStep(boolean add) {
        if (add) {
            this.GridX += 10;
            this.GridY += 10;
        } else {
            if (this.GridX < 20) {
                return;
            }
            this.GridX -= 10;
            this.GridY -= 10;

        }

    }

    public TPoly getCopyPolylist() {
        TPoly pl = polylist;
        TPoly plist = null;

        while (pl != null) {
            TPoly p = new TPoly();
            p.setPoly(poly.pcopy(pl.getPoly()));
            p.setNext(plist);
            plist = p;

            pl = pl.getNext();
        }
        return plist;
    }

    public TPoly getCopyPolyBacklist() {
        TPoly pl = pblist;
        TPoly plist = null;

        while (pl != null) {
            TPoly p = new TPoly();
            p.setPoly(poly.pcopy(pl.getPoly()));
            p.setNext(plist);
            plist = p;

            pl = pl.getNext();
        }
        return plist;
    }

    public TPoly getPolyList() {
        return polylist;
    }

    public Vector getPolyVector() {
        Vector v = new Vector();
        TPoly p = polylist;
        while (p != null) {
            v.add(p.getPoly());
            p = p.next;
        }

        return v;
    }

    public TPoly getPBList() {
        return pblist;
    }

    public Vector getPBMono() {
        TPoly poly = pblist;
        GeoPoly basic = GeoPoly.getPoly(); //.getInstance();
        Vector vx = new Vector();

        TMono m1, m2;
        m1 = m2 = null;

        if (poly == null)
            return vx;

        Vector v = new Vector();
        while (poly != null) {
            m1 = poly.getPoly();
            if (m1 != null)
                v.add(0, basic.p_copy(m1));
            poly = poly.next;
        }

        return v;
    }

    // Get the nondegenerate conditions from the polynomials.
    // This is the simplest nondegenerate conditions.
    public void printNDGS() {
        GeoPoly basic = GeoPoly.getPoly();
        CharSet set = CharSet.getinstance();
        basic.setRMCOEF(false);
        try {
            Vector v = getNDGS();
            Vector v1 = new Vector();
            for (int i = 0; i < v.size(); i++) {
                TMono m = (TMono) v.get(i);
                m = basic.simplify(m, parameter);
                if (m != null)
                    v1.add(m);
            }

            int n = v1.size();
            if (n == 0) {
                basic.setRMCOEF(true);
                return;
            }

            System.out.println("The polynomial of nondegenerate conditions:");
            for (int i = 0; i < n; i++) {
                TMono m = (TMono) v1.get(i);
                System.out.println("d" + i + " := " + basic.getExpandedPrint(m) + ";");
            }
            //System.out.println("The final condition after reduce is: ");
            System.out.print("\nND := ");
            for (int i = 0; i < n; i++) {
                if (i != 0)
                    System.out.print("*");
                System.out.print("d" + i);
            }
            System.out.println(";\nND := factor(ND);\n");
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        basic.setRMCOEF(true);


    }

    public Vector getNDGS() {
        TPoly poly = pblist;
        GeoPoly basic = GeoPoly.getPoly(); //.getInstance();
        Vector vx = new Vector();

        TMono m1, m2;
        m1 = m2 = null;

        if (poly == null)
            return vx;
        int nn = poly.getPoly().x;

        Vector v = new Vector();
        while (poly != null) {
            m1 = poly.getPoly();
            if (m1 != null)
                v.add(0, m1);
            poly = poly.next;
        }

        for (int n = 1; n < nn / 2 + 1; n++) {
            m1 = m2 = null;

            for (int i = 0; i < v.size(); i++) {
                TMono m = (TMono) v.get(i);
                if (m.x == 2 * n || m.x == 2 * n - 1) {
                    if (m1 == null)
                        m1 = m;
                    else m2 = m;
                }
            }

            if (m1 != null && m2 != null) {
                TMono t = basic.ll_delta(2 * n, m1, m2);
                t = reduce(t);
                if (basic.plength(t) == 1 && t.x == 0 && t.val.intValue() != 0) {
                } else
                    basic.ppush(t, vx);
            }
            if (m1 != null)
                v.remove(m1);
            if (m2 != null)
                v.remove(m2);
        }
        return vx;
    }

    public String getPolyString(int id) {
        int index = 0;
        String s = new String();
        TPoly pl = null;

        if (id == 1) {
            pl = polylist;
        } else if (id == 0) {
            pl = pblist;
        }

        while (pl != null) {
            TMono m = pl.getPoly();
            String s1 = poly.printSPoly(m);
            if (id == 1) {
                s += "f_" + index + ": ";
            } else if (id == 0) {
                s += "h_" + index + ": ";
            }

            if (s1.length() > 70) {
                s += (s1.substring(0, 60) + " +  ......\n");
            } else {
                s += s1 + "\n";
            }
            pl = pl.getNext();
            index++;
        }

        return s;
    }


    public boolean reCalculate() {

        boolean success = true;
        if (paraCounter <= 2) return true;

        double x1, y1, sin, cos;
        x1 = y1 = 0;
        sin = 0;
        cos = 1.0;

        if (CMisc.POINT_TRANS) {
            int n = pointlist.size();
            if (n >= 1) {
                CPoint p1 = (CPoint) pointlist.get(0);
                x1 = p1.x1.value;
                y1 = p1.y1.value;
                for (int i = 0; i < pointlist.size(); i++) {
                    CPoint p = (CPoint) pointlist.get(i);
                    p.x1.value = p.x1.value - x1;
                    p.y1.value = p.y1.value - y1;
                }
            }
            if (n >= 2) {
                CPoint p2 = (CPoint) pointlist.get(1);
                double t1 = p2.getx();
                double t2 = p2.gety();
                double r = Math.sqrt(t1 * t1 + t2 * t2);
                if (r == 0.0) {
                    sin = 0.0;
                    cos = 1.0;
                } else {
                    sin = t1 / r;
                    cos = t2 / r;
                    for (int i = 1; i < pointlist.size(); i++) {
                        CPoint p = (CPoint) pointlist.get(i);
                        t1 = p.getx();
                        t2 = p.gety();
                        p.setXY(t1 * cos - t2 * sin, t1 * sin + t2 * cos);
                    }
                }
            }
        }
//        for (int i = 0; i <= this.paraCounter; i++) {
//            if (parameter[i] != null) {
//                System.out.println("x" + parameter[i].xindex + " = " + parameter[i].value);
//            }
//        }
        calv_parameter();

        for (int dx = 0; dx < pointlist.size(); dx++) {
            CPoint p = (CPoint) pointlist.get(dx);
            if (!(success = calculate_a_point(p, true)))
                break;
        }

//        backup_parameter(success);
        {
            if (success == false) {
                for (int i = 0; i < paraCounter; i++) {
                    if (parameter[i] != null)
                        parameter[i].value = paraBackup[i];
                }
                x1 = pptrans[0];
                y1 = pptrans[1];
                sin = pptrans[2];
                cos = pptrans[3];
            } else {
                for (int i = 0; i < paraCounter; i++) {
                    if (parameter[i] != null)
                        paraBackup[i] = parameter[i].value;
                }
                pptrans[0] = x1;
                pptrans[1] = y1;
                pptrans[2] = sin;
                pptrans[3] = cos;
            }
        }

        translate_back(x1, y1, sin, cos);

        for (int i = 0; i < updaterListeners.size(); i++) {
            DiagramUpdater d = (DiagramUpdater) updaterListeners.get(i);
            d.UpdateDiagram();
        }
        calculate_trace();
        recal_allFlash();
        calculate_text();

        return success;
    }

    public void calculate_text() {
        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            if (t.getType() == CText.VALUE_TEXT) {
                double r = calculate(t.tvalue);
                t.tvalue.dvalue = roundn(r, t.m_dash);
                if (t.father != null) {
                    CClass c = t.father;
                    if (c instanceof CPolygon) {
                        CPolygon p1 = (CPolygon) c;
                        r = p1.getArea();
                    }
                }
                t.tvalue.dvalue = roundn(r, t.m_dash);
            }
        }
    }

    public void backup_parameter(boolean success, double x, double y, double sin, double cos) {
        if (success == false) {
            for (int i = 0; i < paraCounter; i++) {
                if (parameter[i] != null)
                    parameter[i].value = paraBackup[i];
            }
            x = pptrans[0];
            y = pptrans[1];
            sin = pptrans[2];
            cos = pptrans[3];
        } else {
            for (int i = 0; i < paraCounter; i++) {
                if (parameter[i] != null)
                    paraBackup[i] = parameter[i].value;
            }
            pptrans[0] = x;
            pptrans[1] = y;
            pptrans[2] = sin;
            pptrans[3] = cos;
        }

    }

    public void translate_back(double x1, double y1, double sin, double cos) {
        if (CMisc.POINT_TRANS) {
            double t1, t2;
            sin = -sin;
            for (int i = 1; i < pointlist.size(); i++) {
                CPoint p = (CPoint) pointlist.get(i);
                t1 = p.getx();
                t2 = p.gety();
                p.setXY(t1 * cos - t2 * sin, t1 * sin + t2 * cos);
            }

            for (int i = 0; i < pointlist.size(); i++) {
                CPoint p = (CPoint) pointlist.get(i);
                p.x1.value += x1;
                p.y1.value += y1;
            }
        }

    }

    public void recal_allFlash() {
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash f = (JFlash) flashlist.get(i);
            f.recalculate();
        }
    }

    public void calculate_trace() {

        int nt = tracelist.size();
        if (nt == 0)
            return;

        CAL_MODE = 1;


        for (int i = 0; i < nt; i++) {
            CTrace t = (CTrace) tracelist.get(i);
            CPoint p = t.getPoint();
            CPoint po = t.getonPoint();
            if (p == null || po == null)
                continue;

            CClass c = t.getOnObject();
            int n = t.getPointSize();
            double xs = po.getx();
            double ys = po.gety();

            if (c instanceof CLine) {
                CLine ln = (CLine) c;
//                double w = this.Width;
//                double h = this.Height;
//                double k = ln.getK();
                CPoint[] lpt = ln.getMaxMinPoint(false);

                double x0 = lpt[0].getx();
                double y0 = lpt[0].gety();
                double x, y, dx, dy;
//                double k1 = Math.abs(w / h);
                x = x0;
                y = y0;
                dx = (lpt[1].getx() - lpt[0].getx()) / n;
                dy = (lpt[1].gety() - lpt[0].gety()) / n;

                for (int j = 0; j < n; j++) {
                    double xt = x + dx * j;
                    double yt = y + dy * j;
                    po.setXY(xt, yt);
                    calculate_allpt(false);
                    t.addTracePoint(j, p.getx(), p.gety());
                }
            } else if (c instanceof Circle) {
                Circle cr = (Circle) c;
                double r = cr.getRadius();
                double a = Math.PI * 2 / n;
                double ox = cr.o.getx();
                double oy = cr.o.gety();
                for (int j = 0; j < n; j++) {
                    double sinx = Math.sin(a * j);
                    double cosx = Math.cos(a * j);
                    double xt = r * cosx + ox;
                    double yt = r * sinx + oy;
                    po.setXY(xt, yt);
                    calculate_allpt(false);
                    t.addTracePoint(j, p.getx(), p.gety());
                }
                t.softEdge();
            }
            po.setXY(xs, ys);
        }
        calculate_allpt(true);

        CAL_MODE = 0;
    }


    public double[] getParameter() {
        double[] r = new double[parameter.length];
        for (int i = 0; i < paraCounter; i++) {
            if (parameter[i] != null)
                r[i] = parameter[i].value;
        }
        return r;
    }

    public void setParameter(double[] r) {
        for (int i = 0; i < paraCounter; i++) {
            if (parameter[i] != null)
                parameter[i].value = r[i];
        }
    }

    public void BackupParameter(double[] rr, boolean b) {
        if (b)
            for (int i = 0; i < paraCounter; i++) {
                if (parameter[i] != null)
                    rr[i] = parameter[i].value;
            }
        else
            for (int i = 0; i < paraCounter; i++) {
                if (parameter[i] != null)
                    parameter[i].value = rr[i];
            }

    }


    public void setParameterValue(double[] dd) {
        for (int i = 0; i < dd.length; i++) {
            if (parameter[i] != null)
                parameter[i].value = dd[i];
        }

        if (CMisc.POINT_TRANS) {
            double x1 = pptrans[0];
            double y1 = pptrans[1];
            double sin = pptrans[2];
            double cos = pptrans[3];
            this.translate_back(x1, y1, sin, cos);
        }
    }


    public Vector calculate_allResults() {     // calculate all results from the polygons.
        double x1, y1, sin, cos;
        x1 = y1 = 0;
        sin = 0;
        cos = 1.0;

        if (CMisc.POINT_TRANS) {
            int n = pointlist.size();
            if (n >= 1) {
                CPoint p1 = (CPoint) pointlist.get(0);
                x1 = p1.x1.value;
                y1 = p1.y1.value;
                for (int i = 0; i < pointlist.size(); i++) {
                    CPoint p = (CPoint) pointlist.get(i);
                    p.x1.value = p.x1.value - x1;
                    p.y1.value = p.y1.value - y1;
                }
            }
            if (n >= 2) {
                CPoint p2 = (CPoint) pointlist.get(1);
                double t1 = p2.getx();
                double t2 = p2.gety();
                double r = Math.sqrt(t1 * t1 + t2 * t2);
                sin = t1 / r;
                cos = t2 / r;
                for (int i = 1; i < pointlist.size(); i++) {
                    CPoint p = (CPoint) pointlist.get(i);
                    t1 = p.getx();
                    t2 = p.gety();
                    p.setXY(t1 * cos - t2 * sin, t1 * sin + t2 * cos);
                }
            }
        }

        for (int i = 0; i < paraCounter; i++) {
            if (parameter[i] != null)
                paraBackup[i] = parameter[i].value;
        }


        Vector vlist = new Vector();
        int n = paraCounter;
        double[] rr = new double[n];
        vlist.add(rr);


        for (int t = 0; t < paraCounter; t++) {
            param pm = parameter[t];
            if (pm == null)
                continue;
            TMono m1 = pm.m;


            for (int k = 0; k < vlist.size(); k++) {
                double[] rt = (double[]) vlist.get(k);
                if (m1 == null) {
                    rt[t] = parameter[t].value;
                    continue;
                }
                for (int m = 0; m < t; m++)
                    parameter[m].value = rt[m];
                double[] result = result = calcu_m1(m1);
                if (result == null || result.length == 0) {
                    rt[t] = parameter[t].value;
                } else if (result.length == 1) {
                    rt[t] = result[0];
                } else {
                    rt[t] = result[0];
                    for (int i = 1; i < result.length; i++) {
                        double[] r2 = new double[n];
                        for (int c = 0; c < t; c++) {
                            r2[c] = rt[c];
                        }
                        r2[t] = result[i];
                        vlist.add(k, r2);
                        k++;
                    }
                }
            }
        }


        for (int i = 0; i < vlist.size(); i++) {  // remove the common point.
            double[] kk = (double[]) vlist.get(i);
            for (int j = 0; j < n; j++) {
                if (parameter[j] != null)
                    parameter[j].value = kk[j];
            }

            boolean bk = false;
            for (int m = 0; m < pointlist.size(); m++) {
                CPoint p1 = (CPoint) pointlist.get(m);
                for (int n1 = m + 1; n1 < pointlist.size(); n1++) {
                    CPoint p2 = (CPoint) pointlist.get(n1);
                    if (Math.abs(p1.x1.value - p2.x1.value) < CMisc.ZERO
                            && Math.abs(p1.y1.value - p2.y1.value) < CMisc.ZERO) {
                        bk = true;
                        break;
                    }
                }
                if (bk)
                    break;
            }
            if (bk) {
                vlist.remove(i);
                i--;
            }
        }

        for (int i = 0; i < paraCounter; i++) {     // restor the previous data.
            if (parameter[i] != null)
                parameter[i].value = paraBackup[i];
        }
        translate_back(x1, y1, sin, cos);
        return vlist;
    }

    public boolean calculate_allpt(boolean d) {
        double x1, y1, sin, cos;
        x1 = y1 = 0;
        sin = 0;
        cos = 1.0;

        if (CMisc.POINT_TRANS) {
            int n = pointlist.size();
            if (n >= 1) {
                CPoint p1 = (CPoint) pointlist.get(0);
                x1 = p1.x1.value;
                y1 = p1.y1.value;
                for (int i = 0; i < pointlist.size(); i++) {
                    CPoint p = (CPoint) pointlist.get(i);
                    p.x1.value = p.x1.value - x1;
                    p.y1.value = p.y1.value - y1;
                }
            }
            if (n >= 2) {
                CPoint p2 = (CPoint) pointlist.get(1);
                double t1 = p2.getx();
                double t2 = p2.gety();
                double r = Math.sqrt(t1 * t1 + t2 * t2);
                sin = t1 / r;
                cos = t2 / r;
                for (int i = 1; i < pointlist.size(); i++) {
                    CPoint p = (CPoint) pointlist.get(i);
                    t1 = p.getx();
                    t2 = p.gety();
                    p.setXY(t1 * cos - t2 * sin, t1 * sin + t2 * cos);
                }
            }
        }
        boolean s = true;
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            if (!(s = calculate_a_point(p, d)))
                break;
        }

        this.translate_back(x1, y1, sin, cos);
        return s;
    }

    public void popLeadingVariableDialog() {
        LeadVariableDialog dlg = new LeadVariableDialog(gxInstance);
        dlg.loadVariable(this.getPointList(), false);
        dlg.setVisible(true);
    }

    public void calv_parameter() {
        int n = 0;
        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);
            if (cs.GetConstraintType() == constraint.SPECIFIC_ANGEL) {
                n++;
            }
        }
        for (int i = 0; i < n; i++) {
            TMono m = polylist.getPoly();
            int x = poly.lv(m);
            double[] r = poly.calculv(m, parameter);
            if (r != null) {
                for (int j = 0; j < r.length; j++) {
                    if (r[j] > 0) {
                        parameter[x - 1].value = r[j];
                        continue;
                    }
                }
            }
        }


    }

    public Circle fd_pt_on_which_circle(CPoint pt) {
        for (int i = 0; i < circlelist.size(); i++) {
            Circle cr = (Circle) circlelist.get(i);
            if (cr.getSidePoint() != null) {
                CPoint o = cr.o;
                CPoint p1 = cr.getSidePoint();
                if (p1.x1.xindex < pt.x1.xindex && o.x1.xindex < pt.x1.xindex)
                    return cr;
            }
        }

        return null;
    }


    public CLine fd_pt_on_which_line(CPoint pt) {
        for (int i = 0; i < linelist.size(); i++) {
            CLine ln = (CLine) linelist.get(i);
            if (ln.containPT(pt) && ln.getPtsSize() >= 2) {
                CPoint p1 = ln.getfirstPoint();
                CPoint p2 = ln.getSecondPoint(p1);
                if (p1.x1.xindex < pt.x1.xindex && p2.x1.xindex < pt.x1.xindex)
                    return ln;
            }
        }

        return null;
    }

    public void setOnLine(CPoint pt, double[] r) {
        CLine ln = this.fd_pt_on_which_line(pt);
        if (ln != null) {
            CPoint p1 = ln.getfirstPoint();
            CPoint p2 = ln.getSecondPoint(p1);
            double x1 = paraBackup[pt.x1.xindex - 1];
            double y1 = paraBackup[pt.y1.xindex - 1];
            if (ln.pointOnLineN(pt)) {
            }
        }
    }

    public double[] calculate_ocir(CPoint pt) {
        if (this.CurrentAction == MOVE && this.SelectList.contains(pt) || CAL_MODE == 1)
            return null;

        Circle cr = this.fd_pt_on_which_circle(pt);
        if (cr != null) {
            CPoint p1 = cr.o;
            CPoint p2 = cr.getSidePoint();
            double xt = paraBackup[pt.x1.xindex - 1];
            double yt = paraBackup[pt.y1.xindex - 1];
            double x1 = paraBackup[p1.x1.xindex - 1];
            double y1 = paraBackup[p1.y1.xindex - 1];
            double x2 = paraBackup[p2.x1.xindex - 1];
            double y2 = paraBackup[p2.y1.xindex - 1];

            if (check_eqdistance(x1, y1, xt, yt, x1, y1, x2, y2)) {
                double rr = CAngle.get3pAngle(x2, y2, x1, y1, xt, yt);
                rr -= Math.PI;

//                System.out.println(" " + rr);
                double cos = Math.cos(rr);
                double sin = Math.sin(rr);
                double dx = p2.getx() - p1.getx();
                double dy = p2.gety() - p1.gety();

                double[] r = new double[2];
                r[0] = p1.getx() + dx * cos - dy * sin;
                r[1] = p1.gety() + dx * sin + dy * cos;
                return r;
            }
        }
        return null;
    }


    public double[] calculate_oline(CPoint pt) {
        if (this.CurrentAction == MOVE && this.SelectList.contains(pt) || CAL_MODE == 1)
            return null;

        CLine ln = this.fd_pt_on_which_line(pt);
        if (ln != null) {
            CPoint p1 = ln.getfirstPoint();
            CPoint p2 = ln.getSecondPoint(p1);
            double xt = paraBackup[pt.x1.xindex - 1];
            double yt = paraBackup[pt.y1.xindex - 1];
            double x1 = paraBackup[p1.x1.xindex - 1];
            double y1 = paraBackup[p1.y1.xindex - 1];
            double x2 = paraBackup[p2.x1.xindex - 1];
            double y2 = paraBackup[p2.y1.xindex - 1];

            if (check_Collinear(xt, yt, x1, y1, x2, y2)) {
                double d1 = xt - x1;
                double d2 = x2 - xt;
                if (isZero(d1) || isZero(d2) || isZero(d1 + d2)) {
                    d1 = yt - y1;
                    d2 = y2 - yt;
                }

                double d = d1 + d2;
                double x = (p1.getx() * d2 + p2.getx() * d1) / d;
                double y = (p1.gety() * d2 + p2.gety() * d1) / d;
                double[] r = new double[2];
                r[0] = x;
                r[1] = y;
                return r;
            }
        }
        return null;
    }

    public boolean calculate_lccc(CPoint cp, double[] r) {
        param pm1 = cp.x1;
        param pm2 = cp.y1;

        TMono m1 = pm1.m;
        TMono m2 = pm2.m;
        if (!(m1 != null && m2 != null && m1.deg == 2 && m2.deg == 1)) {
            return false;
        }

        int type = 0;
        constraint cs = null;

        for (int i = 0; i < constraintlist.size(); i++) {
            constraint c = (constraint) constraintlist.get(i);
            type = c.GetConstraintType();

            if (c.getelement(0) == cp && (type == constraint.INTER_LC
                    || type == constraint.INTER_CC)) {

                cs = c;
                break;
            }
        }
        if (cs == null)
            return false;
        if (this.CurrentAction == MOVE && SelectList.contains(cp)) {
            cs.proportion = 1;
            return false;
        }

        if (type == constraint.INTER_LC) {
            CLine ln = (CLine) cs.getelement(1);
            Circle cr = (Circle) cs.getelement(2);
            CPoint p1 = ln.getfirstPoint();
            CPoint p2 = ln.getSecondPoint(p1);
            if (p1 == null || p2 == null || p2 == cp)
                return false;
            CPoint o = cr.o;
            double xt = paraBackup[cp.x1.xindex - 1];
            double yt = paraBackup[cp.y1.xindex - 1];
            double x1 = paraBackup[p1.x1.xindex - 1];
            double y1 = paraBackup[p1.y1.xindex - 1];
            double x2 = paraBackup[p2.x1.xindex - 1];
            double y2 = paraBackup[p2.y1.xindex - 1];
            double xo = paraBackup[o.x1.xindex - 1];
            double yo = paraBackup[o.y1.xindex - 1];

            double k = (y2 - y1) / (x2 - x1);
            double k1 = -(x2 - x1) / (y2 - y1);

            double mx = (yo - y1 + k * x1 - k1 * xo) / (k - k1);
            double my = y1 + k * (mx - x1);

            double area = signArea(xt, yt, mx, my, xo, yo);
            double area1 = signArea(r[0], r[1], mx, my, o.getx(), o.gety());
            double area2 = signArea(r[2], r[3], mx, my, o.getx(), o.gety());

            int n = cs.proportion;
            if (n == 1) {
                if (isZero(area))
                    return false;
                if (area > 0)
                    cs.proportion = 2;
                else if (area < 0)
                    cs.proportion = 3;


            }
            if (cs.proportion == 2) {
                if (area1 > 0) {
                    cp.setXY(r[0], r[1]);
                    return true;
                } else if (area2 > 0) {
                    cp.setXY(r[2], r[3]);
                    return true;
                }
            } else if (cs.proportion == 3) {
                if (area1 < 0) {
                    cp.setXY(r[0], r[1]);
                    return true;
                } else if (area2 < 0) {
                    cp.setXY(r[2], r[3]);
                    return true;
                }
            }


        } else if (type == constraint.INTER_CC) {
            Circle cr1 = (Circle) cs.getelement(1);
            Circle cr2 = (Circle) cs.getelement(2);
            CPoint o1 = cr1.o;
            CPoint o2 = cr2.o;

            double xt = paraBackup[cp.x1.xindex - 1];
            double yt = paraBackup[cp.y1.xindex - 1];
            double x1 = paraBackup[o1.x1.xindex - 1];
            double y1 = paraBackup[o1.y1.xindex - 1];
            double x2 = paraBackup[o2.x1.xindex - 1];
            double y2 = paraBackup[o2.y1.xindex - 1];

            int n = cs.proportion;
            double area = signArea(xt, yt, x1, y1, x2, y2);
            double area1 = signArea(r[0], r[1], o1.getx(), o1.gety(), o2.getx(), o2.gety());
            double area2 = signArea(r[2], r[3], o1.getx(), o1.gety(), o2.getx(), o2.gety());

            if (n == 1) {
                if (area > 0)
                    cs.proportion = 2;
                else if (area < 0)
                    cs.proportion = 3;
                else return false;
            }
            //          System.out.println(" " + cs.proportion);

            if (cs.proportion == 2) {
                if (area1 > 0) {
                    cp.setXY(r[0], r[1]);
                    return true;
                } else if (area2 > 0) {
                    cp.setXY(r[2], r[3]);
                    return true;
                }
            } else if (cs.proportion == 3) {
                if (area1 < 0) {
                    cp.setXY(r[0], r[1]);
                    return true;
                } else if (area2 < 0) {
                    cp.setXY(r[2], r[3]);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean calculate_a_point(CPoint p, boolean d) {
        if (p == null || p.isAFreePoint())
            return true;
        CPoint cp = p;
        param pm1 = cp.x1;
        param pm2 = cp.y1;

        TMono m1 = pm1.m;
        TMono m2 = pm2.m;

        if (m1 != null && m2 == null && poly.deg(m1) == 1 || m1 == null && m2 != null && poly.deg(m2) == 1) {
            double[] r = calculate_oline(cp);
            if (r != null) {
                cp.x1.value = r[0];
                cp.y1.value = r[1];
                return true;
            }
        }
        if (m1 == null && m2 != null && poly.deg(m2) == 2) {
            double[] r = calculate_ocir(cp);
            if (r != null) {
                cp.x1.value = r[0];
                cp.y1.value = r[1];
                return true;
            }
        }


        if (m1 == null && m2 != null && d) {
            double[] r = null;
            int v = poly.deg(m2);
            if (v == 1) {
                r = poly.calculate_online(m2, parameter, cp.x1.xindex, cp.y1.xindex);
            } else if (v == 2)
                r = poly.calculate_oncr(m2, parameter, cp.x1.xindex, cp.y1.xindex);
            if (r != null) {
                cp.x1.value = r[0];
                cp.y1.value = r[1];
                return true;
            }
        }


        int va;
        int vb;
        double[] result = null;
        boolean success = true;
        if (m1 != null) {
            while (true) {
                result = calcu_m1(m1);
                va = poly.deg(m1);
                if (result == null || result.length != 0)
                    break;
                if (m1.next == null)
                    break;
                m1 = m1.next;
            }
        } else {
            va = 1;
            result = new double[1];
            result[0] = cp.x1.value;
        }

        if (m2 == null) {
            vb = 1;
        } else {
            vb = poly.deg(m2);
        }


        if (result == null) {
            success = false;
            return success;
        } else if (result.length == 1 && vb == 1) {
            double oldx = cp.x1.value;
            double oldy = cp.y1.value;

            cp.x1.value = result[0];

            if (m2 != null) {
                double[] result2 = calcu_m1(m2);
                if (result2.length == 0) {
                    result2 = calform(poly.lv(m2), parameter);
                }
                if (result2 == null) {
                    success = false;
                    return success;
                } else if (result2.length == 1) {
                    cp.y1.value = result2[0];
                } else {
                    double nx = oldy;
                    double ds = Double.MAX_VALUE;
                    for (int i = 0; i < result2.length; i++) {
                        if (p.check_xy_valid(result[0], result2[i])) {
                            double dlen = Math.pow(oldy - result2[i], 2);
                            if (dlen < ds) {
                                ds = dlen;
                                nx = result2[i];
                            }
                        }
                    }
                    cp.y1.value = nx;
                }

            }
        } else { //if (result.length > 1)
            int index = 0;
            double oldx = cp.x1.value;
            double oldy = cp.y1.value;

            double[] r = new double[va * vb * 2];
            double[] result2 = null;
            double ox, oy;
            boolean boy = false;
            ox = oy = 0.0;

            for (int i = 0; i < result.length; i++) {
                if (m2 != null) {
                    cp.x1.value = result[i];
                    ox = result[i];
                    result2 = this.calcu_m1(m2);
                    if (result2 == null || result2.length == 0) {
                        result2 = this.calform(p.y1.xindex, parameter);
                    }
                    if (result2 != null && result2.length >= 1) {
                        for (int k = 0; k < result2.length; k++) {
                            cp.y1.value = result2[k];

                            if (!boy) {
                                oy = result2[k];
                                boy = true;
                            }
                            if (isPointAlreadyExists(cp) == null) {
                                if (2 * index < r.length) {
                                    r[2 * index] = result[i];
                                    r[2 * index + 1] = result2[k];
                                    index++;
                                }
                            }
                        }
                    }

                } else {
                    r[2 * index] = result[i];
                    r[2 * index + 1] = cp.y1.value;
                    index++;
                }
            }

            if (index == 0) {
                if (boy) {
                    r[0] = ox;
                    r[1] = oy;
                } else
                    return false;
            }
            if (index == 1) {
                cp.x1.value = r[0];
                cp.y1.value = r[1];
            } else {
                if (index == 2 && this.calculate_lccc(cp, r)) {
                } else {
                    int t = -1;
                    double dis = Double.POSITIVE_INFINITY;

                    for (int i = 0; i < index; i++) {
                        if (p.check_xy_valid(r[2 * i], r[2 * i + 1])) {
                            double ts = Math.pow(oldx - r[2 * i], 2) + Math.pow(oldy - r[2 * i + 1], 2);
                            if (ts < dis) {
                                dis = ts;
                                t = i;
                            }
                        }
                    }
                    if (t >= 0) {
                        cp.x1.value = r[2 * t];
                        cp.y1.value = r[2 * t + 1];
                    }

                }
            }
        }
        return success;
    }

    public double[] calcu_m1(TMono m) {
        double[] result = poly.calculv(m, parameter);

        if (result != null && result.length == 0) {
            TMono mx = m.next;
            if (mx != null) {
                if (poly.deg(mx) != 0)
                    result = poly.calculv(mx, parameter);
            }
        }

        int lva = poly.lv(m);
        if (result == null) {
            if (lva < 1)
                return null;

            TPoly plist = pblist;
            TMono m1 = null;
            TMono m2 = null;
            int d = poly.deg(m, lva);


            while (plist != null) {
                if (poly.lv(plist.getPoly()) == lva) {
                    if (m1 == null) {
                        m1 = plist.getPoly();
                    } else {
                        m2 = plist.getPoly();
                    }
                }
                plist = plist.getNext();
            }

            if (m1 == null && m2 == null) {

            }
            if (m1 != null && m2 != null) {
                result = poly.calculv2poly(m1, m2, parameter);
            } else if (d == 1) {
                m = m1;
                if (m1 == null)
                    m = m2;

                double[] r = poly.calculv_2v(m, parameter);
                if (r != null && r.length != 0) {
                    parameter[lva - 2].value = r[0];
                }
                return null;
            }
        }
        return result;
    }

    public void pushbackup() {
        for (int i = 0; i < paraCounter; i++) {
            if (parameter[i] != null) {
                paraBackup[i] = parameter[i].value;
            }
        }
    }

    public double[] calform(int lv, param p[]) {
        TPoly plist = pblist;
        TMono m1, m2;
        m1 = m2 = null;
        int n = 0;

        while (plist != null) {
            if (poly.lv(plist.getPoly()) == lv) {
                if (m1 == null) {
                    m1 = plist.getPoly();
                } else {
                    m2 = plist.getPoly();
                }
                n++;
            }
            plist = plist.getNext();

        }
        if (m1 == null || m2 == null) {
            return null;
        }

        double[] result; //= new double[1];
        result = poly.calculv2poly(m1, m2, p);
        return result;
    }

    public void charsetAndAddPoly(boolean calcu) {
        TPoly plist = constraint.getPolyListAndSetNull();
        TPoly plist2 = plist;

        if (plist2 == null)
            return;

        while (plist2 != null) {
            pblist = poly.ppush(poly.pcopy(plist2.getPoly()), pblist);
            plist2 = plist2.getNext();
        }
        plist2 = plist;

        if (polylist != null) {
            TPoly tp = plist2;
            while (tp != null) {
                TPoly t = tp;
                tp = tp.getNext();
                t.setNext(null);
                int lva = poly.lv(t.getPoly());
                TPoly pl = polylist;

                if (poly.lv(pl.getPoly()) > lva) {
                    t.setNext(polylist);
                    polylist = t;
                } else {
                    while (pl.getNext() != null) {
                        if (poly.lv(pl.getNext().getPoly()) > lva) {
                            t.setNext(pl.getNext());
                            pl.setNext(t);
                            break;
                        }
                        pl = pl.getNext();
                    }
                    if (pl.getNext() == null) {
                        pl.setNext(t);
                    }
                }
            }
        } else {
            polylist = plist2;
        }

        try {
//        CMisc.print("----------------------");
//        this.printPoly(polylist);
//        polylist = optmizePolygonOnLine(polylist);
            polylist = charset.charset(polylist);
//        CMisc.print("======================");
//        this.printPoly(polylist);
        } catch (OutOfMemoryError ee) {
            JOptionPane.showMessageDialog(gxInstance, ee.getMessage(), ee.toString(), JOptionPane.ERROR_MESSAGE);
        }

        optmizePolynomial();
        SetVarable();

        if (!calcu) {
            pushbackup();
            this.reCalculate();
        }
    }

    public void optmizePolynomial() {
        if (!CMisc.POINT_TRANS)
            return;
        if (pointlist.size() < 2) return;
        CPoint p1 = (CPoint) pointlist.get(0);
        CPoint p2 = (CPoint) pointlist.get(1);
        int zeron[] = poly.getZeron();
        addZeron(p1.x1.xindex, zeron);
        addZeron(p1.y1.xindex, zeron);
        addZeron(p2.x1.xindex, zeron);

        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);
            int t = cs.GetConstraintType();
            if (t == constraint.PONLINE || t == constraint.INTER_LC) {
                CPoint t1 = (CPoint) cs.getelement(0);
                CLine l1 = (CLine) cs.getelement(1);
                if (l1.containPTs(p1, p2))
                    addZeron(t1.x1.xindex, zeron);
            } else if (t == constraint.INTER_LL) {
                CPoint t1 = (CPoint) cs.getelement(0);
                CLine l1 = (CLine) cs.getelement(1);
                CLine l2 = (CLine) cs.getelement(2);
                if (l1.containPTs(p1, p2) || l2.containPTs(p1, p2))
                    addZeron(t1.x1.xindex, zeron);
            }
        }
        TPoly tp = polylist;
        while (tp != null) {
            TMono m = tp.getPoly();
            if (m != null && poly.plength(m) == 1)
                addZeron(m.x, zeron);
            tp = tp.getNext();
        }
    }

    public void addZeron(int x, int[] zeron) {
        for (int i = 0; true; i++) {
            if (zeron[i] == x)
                break;
            if (zeron[i] == 0) {
                zeron[i] = x;
                break;
            }
        }
    }

    public boolean mulSolutionSelect(CPoint p) {
        pSolution = p;
        TMono m1 = p.x1.m;
        TMono m2 = p.y1.m;

        if (m1 == null || m2 == null)
            return true;
        if (m1.deg == 1 && m2.deg == 1)
            return true;


        double x1, y1, sin, cos;
        x1 = y1 = 0;
        sin = 0;
        cos = 1.0;

        if (CMisc.POINT_TRANS) {
            int n = pointlist.size();
            if (n >= 1) {
                CPoint p1 = (CPoint) pointlist.get(0);
                x1 = p1.x1.value;
                y1 = p1.y1.value;
                for (int i = 0; i < pointlist.size(); i++) {
                    CPoint px = (CPoint) pointlist.get(i);
                    px.x1.value = px.x1.value - x1;
                    px.y1.value = px.y1.value - y1;
                }
            }
            if (n >= 2) {
                CPoint p2 = (CPoint) pointlist.get(1);
                double t1 = p2.getx();
                double t2 = p2.gety();
                double r = Math.sqrt(t1 * t1 + t2 * t2);
                sin = t1 / r;
                cos = t2 / r;
                for (int i = 1; i < pointlist.size(); i++) {
                    CPoint px = (CPoint) pointlist.get(i);
                    t1 = px.getx();
                    t2 = px.gety();
                    px.setXY(t1 * cos - t2 * sin, t1 * sin + t2 * cos);
                }
            }
        }

        int lva = poly.lv(m1);
        double[] result = poly.calculv(m1, parameter);

        if (result == null) {
            result = this.calform(lva, parameter);
        }
        if (result == null)
            return false;

        lva = poly.lv(m2);
        for (int i = 0; i < result.length; i++) {
            parameter[p.x1.xindex - 1].value = result[i];
            double[] r = poly.calculv(m2, parameter);
            if (r == null)
                r = this.calform(lva, parameter);


            for (int j = 0; j < r.length; j++) {
                CPoint pt = this.CreateATempPoint(result[i], r[j]);
                solutionlist.add(pt);
            }
        }

        if (CMisc.POINT_TRANS) {
            double t1, t2;
            sin = -sin;
            for (int i = 1; i < pointlist.size(); i++) {
                CPoint px = (CPoint) pointlist.get(i);
                t1 = px.getx();
                t2 = px.gety();
                px.setXY(t1 * cos - t2 * sin, t1 * sin + t2 * cos);
            }

            for (int i = 0; i < pointlist.size(); i++) {
                CPoint px = (CPoint) pointlist.get(i);
                px.x1.value += x1;
                px.y1.value += y1;
            }
            for (int i = 0; i < solutionlist.size(); i++) {
                CPoint px = (CPoint) solutionlist.get(i);
                t1 = px.getx();
                t2 = px.gety();
                px.setXY(t1 * cos - t2 * sin, t1 * sin + t2 * cos);
            }

            for (int i = 0; i < solutionlist.size(); i++) {
                CPoint px = (CPoint) solutionlist.get(i);
                px.x1.value += x1;
                px.y1.value += y1;
            }
        }

        if (solutionlist.size() == 1) {
            solutionlist.clear();
            return true;
        }
        PreviousAction = CurrentAction;
        SetCurrentAction(MULSELECTSOLUTION);
        return true;
    }

    public void ErasedADecidedPoint(CPoint p) { //there are some problems in this function.
        int x1 = p.x1.xindex;
        int y1 = p.y1.xindex;

        if (!p.x1.Solved || !p.y1.Solved) {
            return;
        }
        TPoly plist = polylist;
        TPoly pleft = null;

        TMono m1, m2;
        if (poly.lv(plist.getPoly()) < x1) {
            while (plist.getNext() != null) {
                if (poly.lv(plist.getNext().getPoly()) == x1) {
                    break;
                }
                plist = plist.getNext();
            }
            pleft = plist.getNext();
            m1 = pleft.getPoly();
            pleft = pleft.getNext();
            m2 = pleft.getPoly();

            pleft = pleft.getNext();
            plist.setNext(pleft);

        } else {
            m1 = plist.getPoly();
            plist = plist.getNext();
            m2 = plist.getPoly();
            polylist = plist.getNext();
            pleft = polylist;
        }

        plist = pleft;
        while (plist != null) {
            TMono m = poly.prem(plist.getPoly(), poly.pcopy(m2));
            m = poly.prem(m, poly.pcopy(m1));
            poly.printpoly(m);
            plist.setPoly(m);
            plist = plist.getNext();
        }
        paraCounter -= 2;
        return;
    }


    public void SetDimension(double x, double y) {
        this.Width = x;
        this.Height = y;
    }

    public int GetCurrentAction() {
        return this.CurrentAction;
    }

    public void setParameter(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public void setCurrentDrawStartOver() {
        SetCurrentAction(CurrentAction);
    }

    public void SetCurrentAction(int type) {
        if (type != MOVE && CurrentAction == CONSTRUCT_FROM_TEXT) {
            this.clearFlash();
        }

        if (gxInstance != null)
            gxInstance.setActionPool(type);

        this.CurrentAction = type;
        SelectList.clear();

        if (type == SETTRACK) {
            CTrackPt = null;
        }
        FirstPnt = SecondPnt = null;
        STATUS = 0;
        CatchList.clear();
        vx1 = vy1 = vangle = 0;
        vtrx = vtry = 0;
        if (panel != null)
            panel.repaint();
        else if (gxInstance != null)
            panel.repaint();
        if (gxInstance != null) {
            CStyleDialog dlg = gxInstance.getStyleDialog();
            if (dlg != null && dlg.isVisible())
                dlg.setAction(this.getActionType(type));
        }

    }

    public Cedmark fd_edmark(CPoint p1, CPoint p2) {
        for (int i = 0; i < otherlist.size(); i++) {
            Object obj = otherlist.get(i);
            if (obj instanceof Cedmark) {
                Cedmark ln = (Cedmark) obj;
                if ((ln.p1 == p1 && ln.p2 == p2) || (ln.p2 == p1 && ln.p1 == p2)) {
                    return ln;
                }
            }
        }
        return null;
    }


    public void setcurrentStatus(int status) {
        STATUS = status;
    }


    public boolean saveProveText(String path) throws IOException {
        if (cpfield == null) {
            return false;
        }

        FileOutputStream fp;
        File f = new File(path);

        if (f.exists()) {
            f.delete();
            fp = new FileOutputStream(f, true);

        } else {
            f.createNewFile();
            fp = new FileOutputStream(f, false);
        }
        if (fp == null) {
            return false;
        }
        DataOutputStream out = new DataOutputStream(fp);
        return cpfield.saveText(out, 0);

    }

    public void createProveHead() {
    }

    public boolean undoProveToHead() {
        if (cpfield == null) {
            return false;
        }
        return cpfield.undo_to_head(this);
    }

    public boolean prove_run_to_prove() {
        if (cpfield != null) {
            cpfield.run_to_end(this);
            return cpfield.undo_default(this);
        } else {
            gxInstance.getpprove().m_runtobegin();
        }
        return true;
    }

    public boolean nextProveStep() {
        if (cpfield != null) {
            clearSelection();
            UndoStruct u = this.redo_step();

            if (u == null)
                this.Undo();
            else
                cpfield.setSelectedUndo(u, this);
            return true;

//            boolean r = cpfield.next_prove_step(this);

//            if (!) {
//                cpfield.undo_to_head(this); //.undo_default(this);
//            }
        } else {
//            if (gxInstance != null && gxInstance.getpprove() != null)
//                gxInstance.getpprove().mstep();
        }
        return false;
    }


    public CLine fd_line(Vector v) {
        for (int i = 0; i < linelist.size(); i++) {
            CLine ln = (CLine) linelist.get(i);
            if (ln.points.containsAll(v)) {
                return ln;
            }
        }
        return null;
    }

    public void provePlay(int num) {
        if (timer_type == 0) {
            timer = new Timer(num, this);
            timer.start();
            timer_type = 2;
        } else if (timer_type == 2) {
            timer.stop();
            timer_type = 0;
            this.redo();
        }
    }

    public void proveStop() {
        if (timer_type != 2) {
            return;
        }
        timer.stop();
        timer_type = 0;
        cpfield.run_to_end(this);
    }

    public boolean run_to_prove(UndoStruct u, UndoStruct u1) {
        this.doFlash();

        if (u1 == null && U_Obj == null)
            return false;

        if (u1 != null) {
            runto();
        } else {
            runto1(U_Obj);
            this.repaint();
            return true;
        }
        runto1(u1);

        this.repaint();
        return true;
    }

    public void runto() {
        UndoStruct u = U_Obj;
        if (u == null) return;

        UndoStruct ux;
        if (this.already_redo(u)) return;

        while (true) {
            ux = redo_step(false);
            this.doFlash();
            if (ux == null || ux == u)
                break;
        }
        U_Obj = null;
    }

    public void runto1(UndoStruct u) {
        if (u == null) return;
        UndoStruct ux;
        if (this.already_redo(u))
            return;

        while (true) {
            ux = redo_step(false);
            if (ux == null || ux == u) {
                U_Obj = null;
                return;
            } else if (!all_flash_finished()) {
                U_Obj = u;
                return;
            }
        }
    }

    public boolean all_flash_finished() {
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash f = (JFlash) flashlist.get(i);
            if (!f.isfinished())
                return false;
        }
        return true;
    }

    public boolean checkCPfieldExists() {
        return cpfield != null;
    }

    public void prove_run_to_end() {
        if (cpfield != null) {
            cpfield.run_to_end(this);
        } else {
            if (gxInstance != null && gxInstance.getpprove() != null)
                gxInstance.getpprove().m_runtoend();
        }

    }

    public void prove_run_to_begin() {
        if (cpfield != null) {
            cpfield.run_to_end(this);
            cpfield.run_to_begin(this);
        } else {
            gxInstance.getpprove().m_runtobegin();
        }
    }

    public void Regenerate_Prove_Text() {
        if (cpfield != null) {
            cpfield.reGenerateAll();
        }
    }

    public boolean removeLastProveNode() {
        if (cpfield != null) {
            boolean r = cpfield.removeLast();
            if (r == false) {
                cpfield = null;
            }
            return r;

        } else {
            return false;
        }
    }

    public double[] getSnap(double x, double y) {
        double[] r = new double[2];
        if (!this.SNAP) {
            r[0] = x;
            r[1] = y;
            return r;
        }
        int nx = (int) (0.5 + x / this.GridX);
        int ny = (int) (0.5 + y / this.GridY);
        r[0] = nx * GridX;
        r[1] = ny * GridY;
        return r;
    }

    public void DWMouseWheel(double x, double y, int n, int rt) {
        switch (this.CurrentAction) {
            case MOVE:
            case ZOOM_IN:
            case ZOOM_OUT:
                int k = Math.abs(n);
                for (int i = 0; i < k; i++) {
                    if (rt > 0)
                        zoom_in(x, y, 3);
                    else zoom_out(x, y, 3);
                }
                if (k > 0)
                    this.reCalculate();
                break;

        }
    }

    public void DWMouseDbClick(double x, double y) {
        CatchPoint.setXY(x, y);

        switch (this.CurrentAction) {
            // case SELECT:
            case MOVE: {
                if (!viewElementFromXY(x, y)) {
                }
            }
        }
    }

    public void defineSpecificAngle() {
        if (paraCounter != 1) {
            Vector v = this.getSpecificAngleList();
            if (v.size() == 0) {
                JOptionPane.showMessageDialog(gxInstance,
                        gxInstance.getLanguage(1027, "Angle Specification must be done before drawing anything"),
                        gxInstance.getLanguage(302, "Warning"),
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            SpecificAngleDialog dlg = new SpecificAngleDialog(null, 1, v);
            dlg.setVisible(true);
            return;
        }
        SpecificAngleDialog dlg = new SpecificAngleDialog(gxInstance, 0, null);
        dlg.setVisible(true);
        if (!dlg.isOkPressed()) {
            return;
        }
        Vector v = dlg.getSpecifcAngle();

        for (int i = 0; i < v.size(); i++) {
            Integer in = (Integer) v.get(i);
            int value = in.intValue();
            param p1 = parameter[paraCounter - 1] = new param(paraCounter, 0);
            p1.type = value;
            paraCounter++;
            constraint cs = new constraint(constraint.SPECIFIC_ANGEL, p1, value);
            this.addConstraintToList(cs);
            this.charsetAndAddPoly(false);
        }
        if (paraCounter % 2 != 0) {
//            paraCounter += 2;
//            parameter[paraCounter-1] = new param(0,0);
//            parameter[paraCounter-2] = new param(0,0);
            parameter[paraCounter - 1] = new param(0, 0);
            paraCounter += 1;
            parameter[paraCounter - 1] = new param(0, 0);
            paraCounter += 1;
        } else {
            parameter[paraCounter - 1] = new param(0, 0);
            paraCounter += 1;
        }

    }

    public param getParaForSpecificAngle(int ang) {
        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);
            if (cs.GetConstraintType() == constraint.SPECIFIC_ANGEL) {
                if (cs.proportion == ang) {
                    param pm = (param) cs.getelement(0);
                    return pm;
                }
            }
        }
        return null;
    }

    public Vector getSpecificAngleList() {
        Vector v = new Vector();

        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);
            if (cs.GetConstraintType() == constraint.SPECIFIC_ANGEL) {
                v.add(new Integer(cs.proportion));
            }
        }
        return v;

    }

    public boolean viewElementFromXY(double x, double y) {
        Vector v = new Vector();
        this.SelectAllFromXY(v, x, y, 0);

        CClass c = null;
        if (v.size() == 0) {
            return false;
        }

        if (v.size() > 1) {
            c = (CClass) popSelect(v, (int) x, (int) y);
        } else {
            c = (CClass) v.get(0);
        }
        if (c == null) {
            return false;
        }
        v.clear();
        v.add(c);
        this.setObjectListForFlash(v);
        this.onDBClick(c);
//        this.viewElement(c);
        return true;
    }

    public Object popSelect(Vector v, int x, int y) {
        if (v.size() == 1) {
            this.viewElement((CClass) v.get(0));
        }
        if (v.size() > 1) {
            SelectDialog sd = gxInstance.getSelectDialog();

            JPanel d = panel;
            Point p = d.getLocationOnScreen();
            sd.addItem(v);
            sd.setLocation((int) (p.getX() + x), (int) (p.getY() + y));
            sd.setVisible(true);
            Object obj = sd.getSelected();
            gxInstance.setFocusable(true);
            return obj;
        }
        return null;
    }

    public void DWMouseRightDown(double x, double y) {
        if (CurrentAction != DEFINEPOLY && CurrentAction != TRANSFORM && CurrentAction != FREE_TRANSFORM) {
            CatchPoint.setXY(x, y);
            clearSelection();
            STATUS = 0;
            this.RightMenuPopup(x, y);

        }
    }

    public void DWMouseRightClick(double x, double y) {
        CatchPoint.setXY(x, y);
        switch (CurrentAction) {
            case DEFINEPOLY: {

                if (SelectList.size() == 1 && STATUS != 0) {
                    CPolygon cp = (CPolygon) SelectList.get(0);
                    if (cp.pointlist.size() >= 3) {
                        cp.addAPoint((CPoint) cp.pointlist.get(0));
                        STATUS = 0;
                        addPolygonToList(cp);
                        this.UndoAdded(cp.getDescription());
                        clearSelection();
                    }
                    panel.repaint();
                } else {
                    CatchPoint.setXY(x, y);
                    clearSelection();
                    STATUS = 0;
                    this.RightMenuPopup(x, y);
                }

            }
            break;
            case TRANSFORM: {
                if (STATUS != 0)
                    new RightTransformPopupMenu(this).show(panel, (int) x, (int) y);
                else
                    RightMenuPopup(x, y);
            }
            break;
            case FREE_TRANSFORM: {
                if (SelectList.size() == 1) {
                    JPopupMenu m = new JPopupMenu();
                    ActionListener ls = new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if (e.getActionCommand().equals("OK"))
                                drawProcess.this.add_free_transform();
                            else {
                                CPolygon poly = (CPolygon) SelectList.get(0);
                                STATUS = 0;
                                clearSelection();
                                poly.setDraggedPointsNull();
                            }
                        }
                    };
                    JMenuItem item1 = new JMenuItem("OK");
                    item1.addActionListener(ls);
                    JMenuItem item2 = new JMenuItem("Cancel");
                    item2.addActionListener(ls);
                    m.add(item1);
                    m.add(item2);
                    m.show(panel, (int) x, (int) y);
                }
            }
            break;

        }

    }


    public void RightMenuPopup(double x, double y) {
        if (gxInstance == null) return;

        Vector v = new Vector();
        SelectAllFromXY(v, x, y, 0);
        CClass c = null;
        JPanel d = panel;

        int len = v.size();

        if (len != 0) {

            if (len > 1) {
                SelectDialog dlg = new SelectDialog(gxInstance, v);
                dlg.addItem(v);
                Point p = d.getLocationOnScreen();
                dlg.setLocation((int) (p.getX() + x), (int) (p.getY() + y));
                dlg.setVisible(true);
                c = (CClass) dlg.getSelected();
            } else
                c = (CClass) v.get(0);

            setObjectListForFlash(c);
        }
        new RightClickPopMenu(c, gxInstance).show(panel, (int) x, (int) y);

    }

    public void SelectFromAList(Vector v1, Vector v2, double x, double y) { // from v1 to v2
        for (int i = 0; i < v2.size(); i++) {
            CClass cc = (CClass) v2.get(i);
            if (cc.select(x, y)) {
                v1.add(cc);
            }
        }
    }

    public void SelectAllFromXY(Vector v, double x, double y, int type) {
        // 2: all; 1: geometry object only 0: point preferential
        //3: only point, 4:only line, 5: only circle
        //6: only angle 7: only distance  8:only polygon, 9, only text,10 only trace.

        if (type == 0) {
            this.SelectFromAList(v, pointlist, x, y);
            if (v.size() != 0) {
                return;
            }
            this.SelectNameText(v, x, y);
            if (v.size() != 0) {
                return;
            }
            this.SelectFromAList(v, linelist, x, y);
            this.SelectFromAList(v, circlelist, x, y);
            this.SelectFromAList(v, anglelist, x, y);
            this.SelectFromAList(v, distancelist, x, y);
            this.SelectFromAList(v, textlist, x, y);
            this.SelectFromAList(v, tracelist, x, y);
            this.SelectFromAList(v, otherlist, x, y);
            if (v.size() == 0) {
                this.SelectFromAList(v, polygonlist, x, y);
            }
        } else if (type == 1) {
            this.SelectFromAList(v, pointlist, x, y);
            if (v.size() != 0)
                return;
            this.SelectFromAList(v, linelist, x, y);
            this.SelectFromAList(v, circlelist, x, y);
        } else if (type == 2) {
            this.SelectFromAList(v, pointlist, x, y);
            this.SelectFromAList(v, linelist, x, y);
            this.SelectFromAList(v, circlelist, x, y);
            this.SelectFromAList(v, anglelist, x, y);
            this.SelectFromAList(v, distancelist, x, y);
            this.SelectFromAList(v, textlist, x, y);
            this.SelectFromAList(v, tracelist, x, y);
            this.SelectFromAList(v, otherlist, x, y);
            if (v.size() == 0) {
                this.SelectFromAList(v, polygonlist, x, y);
            }
        } else if (type == 3) {
            this.SelectFromAList(v, pointlist, x, y);
        } else if (type == 4) {
            this.SelectFromAList(v, linelist, x, y);
        } else if (type == 5) {
            this.SelectFromAList(v, circlelist, x, y);
        } else if (type == 6) {
            this.SelectFromAList(v, anglelist, x, y);
        } else if (type == 7) {
            this.SelectFromAList(v, distancelist, x, y);
        } else if (type == 8) {
            this.SelectFromAList(v, polygonlist, x, y);
        } else if (type == 9) {
            this.SelectFromAList(v, textlist, x, y);
        } else if (type == 10) {
            this.SelectFromAList(v, tracelist, x, y);
        } else if (type == 11) { // prove only
            this.SelectFromAList(v, otherlist, x, y);
        }

    }

    public void SelectNameText(Vector v, double x, double y) {
        for (int i = 0; i < textlist.size(); i++) {
            CText text = (CText) textlist.get(i);

            if (text.getType() == CText.NAME_TEXT && text.select(x, y)) {
                v.add(text);
            }
            if (text.getType() == CText.CNAME_TEXT && text.select(x, y)) {
                v.add(text);
            }

        }
    }

    public CClass SelectOneFromXY(double x, double y, int type) {
        Vector v = new Vector();
        this.SelectAllFromXY(v, x, y, type);
        if (v.size() == 0) {
            return null;
        }
        if (v.size() == 1) {
            return (CClass) v.get(0);
        }
        return (CClass) popSelect(v, (int) x, (int) y);

    }

    public Vector OnSelect(double x, double y) {

        Vector v = new Vector();
        SelectAllFromXY(v, x, y, 0);

        if (v.size() == 0) {
            clearSelection();
        } else {
            if (v.size() == 1) {
                if (SelectList.containsAll(v)) {
                    removeAllSelection(v);
                    return SelectList;
                } else {
                    CClass cc = (CClass) v.get(0);
                    SelectList.addAll(v);
                    v.clear();
                    if (cc.m_type == CClass.ANGLE) {
                        CAngle ag = (CAngle) cc;
                        v.add(ag.lstart);
                        v.add(ag.lend);
                        this.flashStep(v);
                    }
                }
            } else {
                Object obj = this.popSelect(v, (int) x, (int) y);
                if (obj != null) {
                    this.addObjectToList(obj, SelectList);
                }
            }

        }
        return v;
    }

    public void getSmartPV(CPoint p1, CPoint p2) {
        int x, y;
        x = y = 0;
        if (p1 == null || p2 == null) {
            return;
        }

        int x1 = (int) p1.getx();
        int y1 = (int) p1.gety();
        int x2 = (int) p2.getx();
        int y2 = (int) p2.gety();

        if (Math.abs(x2 - x1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            x = x1;

            p2.setXY(x1, y2);
        } else if (Math.abs(y2 - y1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            y = y1;

            p2.setXY(x2, y1);
        }
    }

    public CProveText SelectProveText(double x, double y) {
        if (cpfield == null) {
            return null;
        }
        return cpfield.select(x, y, false);
    }

    public void clearSelection() {
        SelectList.clear();
        if (gxInstance != null)
            gxInstance.updateActionPool(this.CurrentAction);
    }

    public void addToSelectList(Object c) {
        if (c != null) {
            SelectList.add(c);
            if (gxInstance != null)
                gxInstance.updateActionPool(this.CurrentAction);
        }

    }

    public void removeAllSelection(Vector v) {
        SelectList.removeAll(v);
        if (gxInstance != null)
            gxInstance.updateActionPool(this.CurrentAction);
    }


    public Vector OnCatch(double x, double y) {
        CatchList.clear();
        SelectAllFromXY(CatchList, x, y, 0);
        return CatchList;
    }

    public boolean check_animation(CPoint p, CLine ln) {
        if (p == null || ln == null)
            return false;
        if (p.isAFixedPoint())
            return false;

        if (p.isAFreePoint() && ln.containPT(p))
            return false;
        return true;
    }

    public void DWButtonDown(double x, double y) {
        CPoint p = null;
        CatchList.clear();
        IsButtonDown = true;
        if (SNAP && CurrentAction != SELECT) {
            double[] r = getSnap(x, y);
            x = r[0];
            y = r[1];
        }
        CatchPoint.setXY(x, y);

        switch (this.CurrentAction) {
            case SELECT: {
                CPoint t = SelectAPoint(x, y);
                boolean r = false;

                if (gxInstance.isDialogProveVisible()) {
                    clearSelection();
                    if (t != null)
                        addToSelectList(t);
                    r = true;
                    gxInstance.getDialogProve().setSelect(SelectList);
                }

                if (t == null) {
                    if (cpfield != null) {
                        CProveText ct1 = cpfield.mouseMove(x, y);
                        if (ct1 == null) {
                            r = true;
                            CProveText ct = cpfield.select(x, y, false);
                            if (ct != null) {
                                UndoStruct un = ct.getUndoStruct();
                                if (un != null) {
                                    this.setObjectListForFlash(un.getAllObjects(this));
                                }
                            }
                        } else {
                            Point pt = ct1.getPopExLocation();
                            gxInstance.showRulePanel("R1", (int) pt.getX(), (int) pt.getY());
                        }
                    }

                } else {
                    if (gxInstance.hasMannualInputBar()) {
                        PanelProve1 pp = gxInstance.getpprove();
                        r = pp.selectAPoint((CPoint) t);
                        if (r)
                            this.setObjectListForFlash(t);
                    }
                    gxInstance.selectAPoint((CPoint) t);
                }

                if (r == false) {
                    CatchList.clear();
                    this.SelectAllFromXY(CatchList, x, y, 0);
                    if (CatchList.size() == 0)
                        this.clearSelection();
                    else {
                        this.addToSelectList(CatchList.get(0));
                    }
                } else
                    this.clearSelection();
                vx1 = x;
                vy1 = y;
            }
            break;
            case MOVE: {
                FirstPnt = this.CreateATempPoint(x, y);
                Vector v = new Vector();

                this.SelectAllFromXY(v, x, y, 0);
                if (v.size() == 0) {
                    clearSelection();
                    if (cpfield != null) {
                        CProveText ct1 = cpfield.mouseMove(x, y);
                        if (ct1 == null) {
                            CProveText ct = cpfield.select(x, y, false);
                            if (ct != null) {
                                UndoStruct un = ct.getUndoStruct();
                                if (un != null) {
                                    this.setObjectListForFlash(un.getAllObjects(this));
                                }

                            }
                        } else {
                            Point pt = ct1.getPopExLocation();
                            gxInstance.showRulePanel(ct1.getRulePath(),
                                    (int) pt.getX(), (int) pt.getY());
                        }
                    }
                } else if (v.size() == 1) {
                    clearSelection();
                    SelectList.addAll(v);
                    CClass cc = (CClass) v.get(0);
                    v.clear();
                    if (cc instanceof CPoint) {
                        if (gxInstance != null) {
                            if (gxInstance.isconcVisible()) {
                                gxInstance.getConcDialog().selectAPoint((CPoint) cc);
                            }
                            if (gxInstance.hasMannualInputBar()) {
                                gxInstance.getMannalInputToolBar().selectAPoint((CPoint) cc);
                            }
                        }
                    }
                } else {
                    clearSelection();
                    addToSelectList(v.get(0));
                }

                if (SelectList.size() == 1) {
                    if (gxInstance != null) {
                        gxInstance.viewElementsAuto((CClass) SelectList.get(0));
                    }
                }
            }
            break;
            case D_POINT: {
                clearSelection();
                p = this.SmartgetApointFromXY(x, y);
                if (p != null) {
                    addToSelectList(p);
                    this.UndoAdded(p.TypeString());
                }
            }
            break;
            case TRIANGLE: {

                if (STATUS == 0) {
                    CPoint pp = (CPoint) this.CatchList(pointlist, x, y);
                    if (pp == null) {
                        pp = SmartgetApointFromXY(x, y);
                    }

                    this.addToSelectList(pp);
                    FirstPnt = pp;
                    STATUS = 1;

                } else if (STATUS == 1) {
                    CPoint pp = (CPoint) this.CatchList(pointlist, x, y);
                    if (pp == null) {
                        pp = SmartgetApointFromXY(x, y);
                    }

                    if (!SelectList.contains(pp)) {
                        addToSelectList(pp);
                        SecondPnt = pp;
                        STATUS = 2;
                    }

                } else {
                    CPoint pp = (CPoint) this.CatchList(pointlist, x, y);
                    if (pp == null) {
                        pp = SmartgetApointFromXY(x, y);
                    }

                    if (!SelectList.contains(pp)) {
                        addToSelectList(pp);
                    } else {
                        break;
                    }

                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint p3 = (CPoint) SelectList.get(2);
                    CLine line1 = new CLine(p1, p2);
                    CLine line2 = new CLine(p1, p3);
                    CLine line3 = new CLine(p2, p3);
                    this.addPointToList(p1);
                    this.addPointToList(p2);
                    this.addPointToList(p3);
                    this.addLineToList(line1);
                    this.addLineToList(line2);
                    this.addLineToList(line3);
                    constraint cs = new constraint(constraint.TRIANGLE, p1, p2, p3);
                    this.addConstraintToList(cs);
                    this.UndoAdded("Triangle " + p1.m_name + p2.m_name + p3.m_name);
                    FirstPnt = SmartgetApointFromXY(x, y);
                    SecondPnt = this.CreateATempPoint(x, y);
                    clearSelection();
                    STATUS = 0;
                }
            }
            break;
            case H_LINE:
            case V_LINE:
                if (STATUS == 0) {
                    FirstPnt = SmartgetApointFromXY(x, y);
                    SecondPnt = this.CreateATempPoint(x, y);
                    STATUS = 1;
                }
                break;
            case D_LINE: {
                if (STATUS == 0) {
                    if ((FirstPnt = SmartgetApointFromXY(x, y)) != null) {
                        STATUS = 1;
                        addPointToList(FirstPnt);
                        this.addToSelectList(FirstPnt);
                    }
                } else if (STATUS == 1) {
                    CPoint tp = FirstPnt;
                    if (this.isPointOnObject) {
                        x = mouseCatchX;
                        y = mouseCatchY;
                    }
                    CPoint pp = SmartgetApointFromXY(x, y);
//                    pp.setXY(x, y);
                    getSmartPV(FirstPnt, pp);

                    if (tp != pp && tp != null && pp != null) {
                        setSmartPVLine(tp, pp);
                        addPointToList(pp);
                        CLine ln = new CLine(pp, tp, CLine.LLine);
                        this.addLineToList(ln);
                        constraint cs = new constraint(constraint.LINE, tp, pp);
                        addConstraintToList(cs);
                        this.reCalculate();
                        this.UndoAdded(ln.getDescription());
                    }
                    clearSelection();
                    STATUS = 0;
                    FirstPnt = null;
                }
            }
            break;
            case D_POLYGON: {
                CPoint pt = SmartgetApointFromXY(x, y);
                setSmartPVLine(FirstPnt, pt);
                boolean finish = false;

                if (SelectList.size() == 0) {
                    this.addPointToList(pt);
                    addToSelectList(pt);
                    FirstPnt = pt;
                    SecondPnt = this.CreateATempPoint(x, y);
                } else if (pt == SelectList.get(0)) {
                    finish = true;
                } else if (SelectList.contains(pt)) {
                    break;
                } else {
                    this.addPointToList(pt);
                    addToSelectList(pt);
                    if (SelectList.size() == STATUS) {
                        finish = true;
                    }
                    FirstPnt = pt;
                }
                if (finish) {
                    if (SelectList.size() <= 1) {
                        clearSelection();
                        return;
                    }
                    CPoint t1 = (CPoint) SelectList.get(0);
                    CPoint tp = t1;
                    for (int i = 1; i < SelectList.size(); i++) {
                        CPoint tt = (CPoint) SelectList.get(i);
                        if (this.fd_line(tt, tp) == null) {
                            CLine ln = new CLine(tt, tp, CLine.LLine);
                            this.addLineToList(ln);
                        }
                        tp = tt;
                    }
                    if (this.fd_line(t1, tp) == null) {
                        CLine ln = new CLine(t1, tp);
                        this.addLineToList(ln);
                    }

                    String s = "";
                    int size = SelectList.size();
                    for (int i = 0; i < size; i++) {
                        CClass cc = (CClass) SelectList.get(i);
                        s += cc.m_name;

                    }
                    if (size == 3) {
                        constraint cs = new constraint(constraint.TRIANGLE, SelectList);
                        this.addConstraintToList(cs);

                        this.UndoAdded("triangle  " + s);
                    } else if (size == 4) {
                        constraint cs = new constraint(constraint.QUADRANGLE, SelectList);
                        this.addConstraintToList(cs);
                        this.UndoAdded("quadrangle  " + s);
                    } else if (size == 5) {
                        constraint cs = new constraint(constraint.PENTAGON, SelectList);
                        this.addConstraintToList(cs);
                        this.UndoAdded("pentagon  " + s);
                    } else {
                        constraint cs = new constraint(constraint.POLYGON, SelectList);
                        this.addConstraintToList(cs);
                        this.UndoAdded("polygon  " + s);
                    }
                    clearSelection();
                }
            }
            break;
            case D_PARELINE: {
                if (STATUS == 0) {
                    clearSelection();
                    CLine line = this.SmartPLine(CatchPoint);

                    if (line == null) {
                        break;
                    }
                    addToSelectList(line);
                    STATUS = 1;
                } else if (STATUS == 1) {
                    if (SelectList.size() == 0) {
                        break;
                    }
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    CLine line = (CLine) SelectList.get(0);

                    CLine line1 = new CLine(pt, CLine.PLine);
                    constraint cs = new constraint(constraint.PARALLEL, line1, line);
                    this.addConstraintToList(cs);
                    line1.addconstraint(cs);
                    clearSelection();
                    this.addLineToList(line1);
                    UndoStruct u = this.UndoAdded(line1.TypeString() + " parallel " +
                            line.getDiscription() + " passing " +
                            pt.getname());
                    u.adddOjbect(line1);
                    u.adddOjbect(line);
                    u.adddOjbect(pt);
                    clearSelection();
                    STATUS = 0;

                }

            }
            break;

            case D_PERPLINE: {
                if (STATUS == 0) {
                    clearSelection();
                    CLine line = this.SmartPLine(CatchPoint);
                    if (line == null)
                        break;

                    addToSelectList(line);
                    STATUS = 1;
                } else if (STATUS == 1) {
                    if (SelectList.size() == 0) {
                        break;
                    }
                    CLine line = (CLine) SelectList.get(0);
                    CPoint pt = this.SmartgetApointFromXY(x, y);

                    CLine line1 = new CLine(pt, CLine.TLine);
                    constraint c = new constraint(constraint.PERPENDICULAR, line1, line);
                    this.addConstraintToList(c);
                    line1.addconstraint(c);
                    addLineToList(line1);
                    addCTMark(line, line1);
                    // this.otherlist.add(m);
                    UndoStruct u = this.UndoAdded(line1.TypeString() + " perp " +
                            line.getDiscription() + " passing " +
                            pt.getname());
                    u.adddOjbect(line1);
                    u.adddOjbect(line);
                    u.adddOjbect(pt);
                    STATUS = 0;
                    clearSelection();
                }
            }
            break;
            case D_ALINE: {
                int n = SelectList.size();
                if (n < 3) {
                    CLine line = this.SmartPLine(CatchPoint);
                    if (line == null) {
                        break;
                    }
                    if (n == 1) {
                        CLine ln1 = (CLine) SelectList.get(0);
                        if (CLine.commonPoint(ln1, line) == null) {
                            JOptionPane.showMessageDialog(gxInstance, this.getLanguage(1025, "The two selected line don't" +
                                    "have any intersected point"), this.getLanguage(302, "Warning"), JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }
                    addToSelectList(line);
                } else {
                    CLine ln1 = (CLine) SelectList.get(0);
                    CLine ln2 = (CLine) SelectList.get(1);
                    CLine ln3 = (CLine) SelectList.get(2);
                    CPoint tt = null;
                    if (this.SmartPLine(CatchPoint) == ln3 || ((tt = this.SmartPoint(CatchPoint)) != null && ln3.containPT(tt))) {
                        CPoint p1 = this.SmartgetApointFromXY(x, y);
                        CLine ln = new CLine(CLine.ALine);
                        ln.addApoint(p1);
                        constraint cs = new constraint(constraint.ALINE, ln1, ln2, ln3, ln);
                        cs.setPolyGenerate(false);

                        ln.addconstraint(cs);
                        this.addLineToList(ln);
                        this.addConstraintToList(cs);
                        clearSelection();
                        this.UndoAdded("ALine " + ln.getname());
                    }
                }
            }
            break;
            case D_ABLINE: {
                int n = SelectList.size();
                if (STATUS == 0) {
                    p = this.SelectAPoint(x, y);
                    if (p != null) {
                        addToSelectList(p);
                        STATUS = 1;
                    } else {
                        CLine ln = SelectALine(x, y);
                        if (ln != null) {
                            addToSelectList(ln);
                            CatchPoint.setXY(x, y);
                            ln.pointonline(CatchPoint);
                            catchX = CatchPoint.getx();
                            catchY = CatchPoint.gety();
                        }
                        STATUS = 2;
                    }
                } else if (STATUS == 5) {
                    CLine ln = (CLine) SelectList.get(0);
                } else {

                    if (n < 3 && STATUS == 1) {
                        addSelectPoint(x, y);
                    } else if (n < 2 && STATUS == 2) {
                        CLine ln = SelectALine(x, y);
                        if (ln != null) {
                            if (SelectList.size() < 1)
                                break;
                            CLine ln0 = (CLine) SelectList.get(0);
                            if (CLine.commonPoint(ln0, ln) != null)
                                addToSelectList(ln);
                            else
                                JOptionPane.showMessageDialog(gxInstance, gxInstance.getLanguage(1025, "The selected two line don't have intersected point")
                                        , gxInstance.getLanguage(1026, "No Intersected point"), JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    n = SelectList.size();
                    {
                        CPoint p1, p2, p3;
                        boolean dd = true;
                        if (STATUS == 1 && n == 3) {
                            p1 = (CPoint) SelectList.get(0);
                            p2 = (CPoint) SelectList.get(1);
                            p3 = (CPoint) SelectList.get(2);
                        } else if (STATUS == 2 && n == 2) {
                            CLine ln1 = (CLine) SelectList.get(0);
                            CLine ln2 = (CLine) SelectList.get(1);
                            p2 = CLine.commonPoint(ln1, ln2);
                            p1 = ln1.get_Lptv(p2, catchX, catchY);
                            p3 = ln2.get_Lptv(p2, x, y);
                            dd = false;
                        } else
                            break;

                        if (p3 != null && p3 != p1 && p3 != p2) {
                            CLine ln = new CLine(CLine.ABLine);
                            ln.addApoint(p2);
                            if (dd) {
                                CPoint pt = this.CreateANewPoint(0, 0);
                                ln.addApoint(pt);
                                CLine ln1 = this.addALine(CLine.LLine, p1, p3);
                                constraint cs = new constraint(constraint.ANGLE_BISECTOR, p1, p2, p3, ln);
                                constraint cs1 = new constraint(constraint.PONLINE, pt, ln1);
                                ln.addconstraint(cs);
                                this.addPointToList(pt);
                                this.addLineToList(ln);
                                this.addConstraintToList(cs);
                                this.charsetAndAddPoly(false);
                                clearSelection();
                                STATUS = 0;
                                this.UndoAdded(ln.getSimpleName() + " is the bisector of angle " + p1 + p2 + p3, true, ln.getPtsSize() > 1);
                            } else {
                                constraint cs = new constraint(constraint.ANGLE_BISECTOR, p1, p2, p3, ln);
                                ln.addconstraint(cs);
                                this.addLineToList(ln);
                                this.addConstraintToList(cs);
                                clearSelection();
                                STATUS = 0;
                                this.UndoAdded("Angle Bisector " + ln.getname(), true, ln.getPtsSize() > 1);
                            }
                        }
                    }
                }
            }
            break;
            case D_PFOOT: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    if (SelectList.size() == 1) {
                        CPoint pa = (CPoint) SelectList.get(0);
                        this.setSmartPVLine(pa, pt);
                        if (fd_line(pa, pt) == null) {
                            CLine ln = new CLine(pa, pt);
                            this.addLineToList(ln);
                        }
                    }
                    if (!SelectList.contains(pt)) {
                        addToSelectList(pt);
                    }
                    if (SelectList.size() == 2) {
                        STATUS = 2;
                    }
                } else if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);

                    double[] r = get_pt_dmcr(p1.getx(), p1.gety(), p2.getx(), p2.gety(), x, y);
                    double xr = r[0];
                    double yr = r[1];
                    p = this.SmartgetApointFromXY(xr, yr);
                    if (p == p1 || p == p2) {
                        break;
                    }
                    CLine ln1, ln2;
                    ln1 = ln2 = null;
                    if ((ln1 = fd_line(p, p1)) == null) {
                        ln1 = new CLine(p1, p, CLine.LLine);
                        this.addLineToList(ln1);
                    }
                    if ((ln2 = fd_line(p, p2)) == null) {
                        ln2 = new CLine(p2, p, CLine.LLine);
                        this.addLineToList(ln2);
                    }
                    constraint cs = new constraint(constraint.RIGHT_ANGLED_TRIANGLE, p, p1, p2);
                    this.addConstraintToList(cs);
                    this.charsetAndAddPoly(false);
                    if (!this.isLineExists(p1, p2)) {
                        CLine lp = new CLine(p1, p2, CLine.LLine);
                        this.addLineToList(lp);
                    }
                    clearSelection();
                    STATUS = 0;
                    addCTMark(ln1, ln2);
                    //this.otherlist.add(m);
                    this.UndoAdded("right triangle " + p1.getname() + p2.getname() + p.getname());
                }
            }
            break;

            case PERPWITHFOOT: {
                if (STATUS == 0) {
                    CPoint tp = SmartgetApointFromXY(x, y);
                    FirstPnt = tp;
                    STATUS = 1;
                } else if (STATUS == 1) {
                    CPoint pt = this.SmartPoint(CatchPoint);
                    if (pt == FirstPnt) {
                        break;
                    }
                    CLine line = this.SmartPLine(CatchPoint);
                    if (line == null) {
                        break;
                    }
                    CPoint pp = this.CreateANewPoint(0, 0);
                    this.add_PFOOT(line, FirstPnt, pp);
                    STATUS = 0;
                }
            }
            break;
            case D_CIRCLE: {
                if (STATUS == 0) {
                    p = this.SmartgetApointFromXY(x, y);
                    if (p != null) {
                        FirstPnt = p;
                        addToSelectList(p);
                        addPointToList(p);
                        STATUS = 1;
                    }
                } else if (STATUS == 1) {
                    p = SmartgetApointFromXY(x, y);
                    if (p == FirstPnt)
                        break;

                    Circle c = new Circle(FirstPnt, p);
                    addCircleToList(c);
                    constraint cs = new constraint(constraint.CIRCLE, FirstPnt, p);
                    this.addConstraintToList(cs);
                    this.charsetAndAddPoly(false);
                    this.UndoAdded(c.getDescription());
                    STATUS = 0;
                    clearSelection();
                }
            }
            break;
            case D_CIR_BY_DIM: {

            }
            break;
            case D_CIRCLEBYRADIUS: {
                if (SelectList.size() < 2) {
                    p = (CPoint) this.CatchList(pointlist, x, y);
                    if (p != null) {
                        this.addObjectToList(p, SelectList);
                    }
                } else {
                    p = this.SmartgetApointFromXY(x, y);
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);

                    Circle cr = new Circle(Circle.RCircle, p);
                    constraint cs = new constraint(constraint.RCIRCLE, p1, p2, cr);
                    cr.addConstraint(cs);
                    this.addConstraintToList(cs);
                    this.addCircleToList(cr);

                    STATUS = 0;
                    clearSelection();
                    FirstPnt = SecondPnt = null;
                    this.UndoAdded(cr.getDescription());
                }
            }
            break;
            case D_PRATIO: {
                if (SelectList.size() < 2) {
                    p = this.SelectAPoint(x, y);
                    if (p != null) {
                        addObjectToList(p, SelectList);
                    } else
                        clearSelection();
                } else {
                    CPoint px = this.SmartgetApointFromXY(x, y);
                    if (px != null) {
                        CPoint p1 = (CPoint) SelectList.get(0);
                        CPoint p2 = (CPoint) SelectList.get(1);

                        p = this.CreateANewPoint(x, y);
                        constraint cs = new constraint(constraint.PRATIO, p, px, p1, p2, new Integer(v1), new Integer(v2));
                        CPoint pu = this.addADecidedPointWithUnite(p);
                        if (pu == null) {
                            this.addConstraintToList(cs);
                            this.addPointToList(p);
                            if (true) {
                                CLine ln = fd_line(p1, p2);
                                if (status && (ln == null || !ln.containPT(px))) {
                                    CLine ln1 = new CLine(px, p, CLine.LLine);
                                    this.addLineToList(ln1);
                                } else {
                                    constraint cs1 = new constraint(constraint.PONLINE);
                                    cs1.setPolyGenerate(false);
                                    cs1.addElement(p);
                                    cs1.addElement(ln);
                                    this.addConstraintToList(cs1);
                                    if (status && ln != null)
                                        ln.addApoint(p);
                                }
                            }
                            this.UndoAdded(cs.getMessage());
                        } else {
                            p = pu;
                        }
                        clearSelection();
                    } else
                        clearSelection();
                }
            }
            break;
            case D_TRATIO: {
                if (SelectList.size() < 2) {
                    p = this.SelectAPoint(x, y);
                    if (p != null) {
                        this.addObjectToList(p, SelectList);
                    }
                } else {
                    p = this.SmartgetApointFromXY(x, y);
                    if (SelectList.size() != 2) break;

                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint px = p;
                    p = this.CreateANewPoint(x, y);
                    double dx = p2.getx() - p1.getx();
                    double dy = p2.gety() - p1.gety();

                    constraint cs = new constraint(constraint.TRATIO, p, px, p1, p2, new Integer(v1), new Integer(v2));
                    CPoint pu = this.addADecidedPointWithUnite(p);
                    if (pu == null) {
                        addConstraintToList(cs);
                        addPointToList(p);
                        if (true) {
                            CLine ln = fd_line(p, px);
                            if (status && ln == null) {
                                CLine ln1 = new CLine(px, p, CLine.LLine);
                                this.addLineToList(ln1);
                            } else {
                                constraint cs1 = new constraint(constraint.PONLINE);
                                cs1.setPolyGenerate(false);
                                cs1.addElement(p);
                                cs1.addElement(ln);
                                this.addConstraintToList(cs1);
                                if (status && ln != null)
                                    ln.addApoint(p);
                            }
                        }
                    } else {
                        p = pu;
                    }
                    clearSelection();
                    STATUS = 0;
                    this.UndoAdded(cs.getMessage());
                }
            }
            break;
            case D_PTDISTANCE: {
                if (SelectList.size() < 3) {
                    CPoint pt = this.CreateATempPoint(x, y);
                    p = this.SmartPoint(pt);
//                    String s = null;
                    if (p != null) {
                        addToSelectList(p);
//                        s = (p.m_name + "  selected");
                        this.setObjectListForFlash(p);
                    }
//                    switch (SelectList.size()) {
//                        case 0:
//                            gxInstance.setTipText(s + ',' + " Please Select a Point");
//                            break;
//                        case 1:
//                            gxInstance.setTipText("first point  " + s + ',' +
//                                    "  please select the second point");
//                            break;
//                        case 2:
//                            gxInstance.setTipText("second point  " + s + ',' +
//                                    "  please select the third point");
//                            break;
//                        case 3:
//                            gxInstance.setTipText("third point  " + s + ',' +
//                                    "  select a line or a circle");
//                    }
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint p3 = (CPoint) SelectList.get(2);
                    Circle c = null;
                    CLine ln = SelectALine(x, y);
                    if (ln != null) {
                        double r = ln.distance(p3.getx(), p3.gety());
                        double r1 = sdistance(p1, p2);
                        if (r < r1) {
                            CPoint pt = this.CreateANewPoint(x, y);
                            this.AddPointToLine(pt, ln, false);
                            constraint cs = new constraint(constraint.EQDISTANCE, p1, p2, p3, pt);
                            this.charsetAndAddPoly(true);
                            if (true || this.mulSolutionSelect(pt)) {
                                this.addConstraintToList(cs);
                                this.addPointToList(pt);
                                this.UndoAdded("Take a point "
                                        + pt.m_name + " on " + ln.getDescription() +
                                        " st " + p1.m_name + p2.m_name + " = " +
                                        p3.m_name + pt.m_name);

                            } else {
                                this.ErasedADecidedPoint(pt);
                                ln.points.remove(pt);
                            }
                        } else
                            JOptionPane.showMessageDialog(gxInstance, "Can not add a point", "No Solution", JOptionPane.ERROR_MESSAGE);

                    } else if ((c = this.SelectACircle(x, y)) != null) {
                        CPoint po = c.o;
                        double d = sdistance(po, p3);
                        double r = c.getRadius();
                        double s = sdistance(p1, p2);
                        double d1 = d + r;
                        double d2 = Math.abs(d - r);
                        if (s > d1 || s < d2) {
                            JOptionPane.showMessageDialog(gxInstance, "Can not add a point", "No Solution", JOptionPane.ERROR_MESSAGE);
                        } else {
                            CPoint pt = this.CreateANewPoint(0, 0);
                            constraint cs = new constraint(constraint.EQDISTANCE, p1, p2, p3, pt);
                            constraint cs1 = new constraint(constraint.PONCIRCLE, pt, c);
                            this.charsetAndAddPoly(true);
                            if (this.mulSolutionSelect(pt)) {
                                this.addConstraintToList(cs);
                                this.addConstraintToList(cs1);
                                this.addPointToList(pt);
                                c.addPoint(pt);
                                this.UndoAdded("Take a point "
                                        + pt.m_name + "on " + c.getDescription() +
                                        " st " + p1.m_name + p2.m_name + " = " +
                                        p3.m_name + pt.m_name);
                            } else {
                                this.ErasedADecidedPoint(pt);
                                gxInstance.setTipText("Failed: can not find a point(P) on Circle " +
                                        " that satisfy |" + p1.m_name + p2.m_name +
                                        "| = |" + p3.m_name + "P|");
                            }
                        }

                    } else {
                    }
                    clearSelection();
                }
            }
            break;
            case LRATIO: {
                CPoint pt = this.CreateATempPoint(x, y);
                p = this.SmartPoint(pt);
                if (p == null) {
                    break;
                }
                if (SelectList.size() == 0) {
                    this.addObjectToList(p, SelectList);
                } else {
                    if (p == SelectList.get(0)) {
                        break;
                    }
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint pp = this.CreateANewPoint(x, y);
                    Integer t1 = new Integer(v1);
                    Integer t2 = new Integer(v2);
                    constraint cs = new constraint(constraint.LRATIO, pp, p1, p, t1, t2);
                    CPoint pu = this.addADecidedPointWithUnite(pp);
                    if (pu == null) {
                        this.addConstraintToList(cs);
                        this.addPointToList(pp);
                    } else {
                        pp = pu;
                        clearSelection();
                        this.resetUndo();
                        break;
                    }

                    CLine ln = null;
                    for (int i = 0; i < linelist.size(); i++) {
                        CLine t = (CLine) linelist.get(i);
                        if (t.sameLine(p1, p)) {
                            ln = t;
                            break;
                        }
                    }
                    if (ln != null) {
                        ln.addApoint(pp);
                    }
                    this.charsetAndAddPoly(false);
                    clearSelection();
                    this.UndoAdded(pp.TypeString() + ":  " + p1.m_name +
                            pp.m_name + " / " + pp.m_name + p.m_name + " = " + 1 + "/" +
                            this.proportion);
                }
            }
            break;
            case MEET: {
                CClass cc = this.SelectALine(x, y);
                if (cc == null)
                    cc = this.SelectACircle(x, y);

                if (cc == null) {
                    clearSelection();
                    break;
                }
                addObjectToList(cc, SelectList);

                if (SelectList.size() == 1) {
                    break;
                } else if (SelectList.size() == 2) {
                    Object obj1 = SelectList.get(0);
                    Object obj2 = SelectList.get(1);
                    meetTwoObject(obj1, obj2, false, x, y);
                    clearSelection();
                }
            }
            break;
            case MIRROR: {
                CatchPoint.setXY(x, y);
                CLine ln = null;
                p = this.SmartPoint(CatchPoint);
                if (p == null) {
                    ln = this.SmartPLine(CatchPoint);
                    if (ln != null) {
                        this.addObjectToList(ln, SelectList);
                    } else {
                        Circle c = this.SmartPCircle(CatchPoint);
                        if (c != null) {
                            this.addObjectToList(c, SelectList);
                        }
                    }
                } else {
                    this.addObjectToList(p, SelectList);
                }

                if (SelectList.size() == 2) {
                    Object obj1, obj2;
                    obj1 = SelectList.get(0);
                    obj2 = SelectList.get(1);
                    if (obj1 instanceof CPoint && obj2 instanceof CPoint) {
                        CPoint p1 = (CPoint) obj1;
                        CPoint p2 = (CPoint) obj2;
                        CPoint pp = this.CreateANewPoint(0, 0);
                        constraint cs = new constraint(constraint.PSYM, pp, p1, p2);
                        CPoint pu = this.addADecidedPointWithUnite(pp);
                        if (pu == null) {
                            this.addPointToList(pp);
                            this.addConstraintToList(cs);
                            this.UndoAdded(pp.TypeString() + " is reflection of " +
                                    p1.TypeString() + " wrpt " +
                                    p2.TypeString());

                        } else {
                            pp = pu;
                        }

                    } else if (obj1 instanceof CPoint && obj2 instanceof CLine) {
                        CPoint p1 = (CPoint) obj1;
                        CLine line = (CLine) obj2;

                        CPoint pp = this.CreateANewPoint(0, 0);
                        constraint cs = new constraint(constraint.MIRROR, pp, p1, line);
                        CPoint pu = this.addADecidedPointWithUnite(pp);
                        if (pu == null) {
                            this.addPointToList(pp);
                            this.addConstraintToList(cs);
                            this.UndoAdded(pp.TypeString() + " is reflection of " +
                                    p1.TypeString() + " wrpt " +
                                    line.getDiscription());

                        } else {
                            pp = pu;
                        }

                    } else if (obj1 instanceof CLine && obj2 instanceof CPoint) {
                        CLine line = (CLine) obj1;
                        CPoint p1 = (CPoint) obj2;

                        int exist_point_number = 0;
                        Vector vp = new Vector();

                        for (int i = 0; i < line.points.size(); i++) {
                            CPoint pu = null;
                            CPoint pp = null;
                            constraint cs = null;

                            CPoint pt = (CPoint) line.points.get(i);
                            if (pt == p1) {
                                pu = pt;
                            } else {
                                pp = this.CreateANewPoint(0, 0);
                                cs = new constraint(constraint.PSYM, pp, pt, p1);
                                pu = this.addADecidedPointWithUnite(pp);
                            }
                            if (pu == null) {
                                this.addPointToList(pp);
                                this.addConstraintToList(cs);
                            } else {
                                pp = pu;
                                exist_point_number++;
                            }
                            vp.add(pp);
                        }

                        if (exist_point_number < line.points.size()) {
                            if (line.points.contains(p1)) {
                                for (int i = 0; i < vp.size(); i++) {
                                    CPoint tt = (CPoint) vp.get(i);
                                    line.addApoint(tt);
                                }
                                this.UndoAdded("reflection");

                            } else {
                                CLine line2 = new CLine(line.type);
                                line2.m_color = line.m_color;
                                line2.m_dash = line.m_dash;
                                line2.m_width = line.m_width;

                                for (int i = 0; i < vp.size(); i++) {
                                    CPoint tt = (CPoint) vp.get(i);
                                    line2.addApoint(tt);
                                }
                                constraint cs = new constraint(constraint.LINE, vp);
                                this.addConstraintToList(cs);
                                this.addLineToList(line2);
                                this.UndoAdded(line2.TypeString() +
                                        " is reflection of " +
                                        line.getDiscription() + " wrpt " +
                                        p1.TypeString());
                            }

                        } else {
                            boolean exists = false;
                            for (int i = 0; i < linelist.size(); i++) {
                                CLine ll = (CLine) linelist.get(i);
                                if (ll.points.containsAll(vp)) {
                                    exists = true;
                                    break;
                                }
                            }
                            if (exists == false) {
                                CLine line2 = new CLine(line.type);
                                for (int i = 0; i < vp.size(); i++) {
                                    CPoint tt = (CPoint) vp.get(i);
                                    line2.addApoint(tt);
                                }
                                line2.m_color = ln.m_color;
                                line2.m_dash = ln.m_dash;
                                line2.m_width = ln.m_width;
                                constraint cs = new constraint(constraint.LINE, vp);
                                this.addConstraintToList(cs);
                                this.addLineToList(line2);
                                this.UndoAdded(line2.getDiscription() +
                                        " is reflection of " +
                                        line.getDiscription() + " wrpt " +
                                        p1.TypeString());

                            } else
                                this.UndoAdded("reflection");
                        }
                    } else if (obj1 instanceof CLine && obj2 instanceof CLine) {
                        CLine line = (CLine) obj1;
                        CLine line2 = (CLine) obj2;
                        CPoint cp = CLine.commonPoint(line, line2);

                        CLine line3 = new CLine(line.type);
                        line3.m_color = line.m_color;
                        line3.m_dash = line.m_dash;
                        line3.m_width = line.m_width;

                        int exist_point_number = 0;
                        for (int i = 0; i < line.points.size(); i++) {
                            CPoint pt = (CPoint) line.points.get(i);

                            CPoint pp;
                            if (pt == cp) {
                                pp = cp;
                                exist_point_number++;
                            } else {
                                pp = this.CreateANewPoint(0, 0);
                                constraint cs = new constraint(constraint.MIRROR, pp, pt, line2);
                                CPoint pu = this.addADecidedPointWithUnite(pp);
                                if (pu == null) {
                                    this.addPointToList(pp);
                                    this.addConstraintToList(cs);
                                } else {
                                    pp = pu;
                                    exist_point_number++;
                                }

                            }
                            line3.addApoint(pp);
                        }
                        constraint cs = new constraint(constraint.LINE, line3.points);
                        addConstraintToList(cs);

                        if (exist_point_number < line.points.size()) {
                            this.addLineToList(line3);

                            this.UndoAdded(line3.getDiscription() +
                                    " is reflection of " +
                                    line.getDiscription() + " wrpt " +
                                    line2.getDiscription());

                        } else {
                            boolean exists = false;
                            for (int i = 0; i < linelist.size(); i++) {
                                CLine ll = (CLine) linelist.get(i);
                                if (ll.sameLine(line3)) {
                                    exists = true;
                                    break;
                                }
                            }
                            if (exists == false) {
                                this.addLineToList(line3);
                                this.UndoAdded(line3.getDiscription() +
                                        " is reflection of " +
                                        line.getDiscription() + " wrpt " +
                                        line2.getDiscription());
                            }
                        }

                    } else if (obj1 instanceof Circle && obj2 instanceof CPoint) {
                        int exist_point_number = 0;

                        Circle c1 = (Circle) obj1;
                        CPoint p1 = (CPoint) obj2;
                        CPoint pp = this.CreateANewPoint(0, 0);
                        constraint cs = new constraint(constraint.PSYM, pp, c1.o, p1);
                        CPoint pu = this.addADecidedPointWithUnite(pp);
                        if (pu == null) {
                            this.addPointToList(pp);
                            this.addConstraintToList(cs);
                        } else {
                            exist_point_number++;
                            pp = pu;
                        }

                        Circle c = null;
                        for (int i = 0; i < c1.points.size(); i++) {
                            CPoint pt = (CPoint) c1.points.get(i);
                            p = this.CreateANewPoint(0, 0);
                            cs = new constraint(constraint.PSYM, p, pt, p1);
                            CPoint pu1 = this.addADecidedPointWithUnite(p);
                            if (pu1 == null) {
                                this.addPointToList(p);
                                this.addConstraintToList(cs);
                            } else {
                                p = pu1;
                                exist_point_number++;
                            }

                            if (i == 0) {
                                c = new Circle(pp, p);
                                c.m_color = c1.m_color;
                                c.m_dash = c1.m_dash;
                                c.m_width = c1.m_width;
                            } else {
                                c.addPoint(p);
                            }
                        }
                        cs = new constraint(constraint.CIRCLE, c.o);
                        cs.addElement(c.points);
                        cs.PolyGenerate();

                        addConstraintToList(cs);

                        if (exist_point_number < c1.points.size() + 1) {
                            this.addCircleToList(c);
                            this.UndoAdded(c.getDescription() +
                                    " is reflection of " + c1.getDescription() +
                                    " wrpt " + p1.TypeString());
                        }

                    } else if (obj1 instanceof Circle && obj2 instanceof CLine) {
                        int exist_point_number = 0;

                        Circle c1 = (Circle) obj1;
                        CLine line = (CLine) obj2;
                        CPoint pp = this.CreateANewPoint(0, 0);
                        constraint cs = new constraint(constraint.MIRROR, pp, c1.o,
                                line);
                        CPoint pu1 = this.addADecidedPointWithUnite(pp);
                        if (pu1 == null) {
                            this.addPointToList(pp);
                            this.addConstraintToList(cs);
                        } else {
                            pp = pu1;
                            exist_point_number++;
                        }

                        Circle c = null;
                        for (int i = 0; i < c1.points.size(); i++) {
                            CPoint pt = (CPoint) c1.points.get(i);
                            p = this.CreateANewPoint(0, 0);
                            cs = new constraint(constraint.MIRROR, p, pt, line);
                            CPoint pu2 = this.addADecidedPointWithUnite(p);
                            if (pu2 == null) {
                                this.addPointToList(p);
                                this.addConstraintToList(cs);
                            } else {
                                p = pu1;
                                exist_point_number++;
                            }
                            if (i == 0) {
                                c = new Circle(pp, p);
                                c.m_color = c1.m_color;
                                c.m_dash = c1.m_dash;
                                c.m_width = c1.m_width;
                            } else {
                                c.addPoint(p);
                            }

                        }
                        cs = new constraint(constraint.CIRCLE, c.o);
                        cs.addElement(c.points);
                        cs.PolyGenerate();
                        addConstraintToList(cs);
                        if (exist_point_number < c1.points.size() + 1) {
                            this.addCircleToList(c);
                            this.UndoAdded(c.getDescription() +
                                    " is reflection of " + c1.getDescription() +
                                    " wrpt " + line.getDescription());
                        }
                    } else {
                        CMisc.print("can not mirror by a circle");
                    }
                    clearSelection();
                }
            }
            break;
            case D_MIDPOINT: {

                CPoint tp = this.SelectAPoint(x, y);
                if (tp != null) {
                    if (SelectList.size() == 1 && tp != SelectList.get(0)) {
                        CPoint tp1 = (CPoint) SelectList.get(0);

                        CPoint po = this.CreateANewPoint(0, 0);
                        constraint cs = new constraint(constraint.MIDPOINT, po, tp, tp1);
                        CPoint pu = this.addADecidedPointWithUnite(po);
                        if (pu == null) {
                            this.addConstraintToList(cs);
                            this.addPointToList(po);
                            CLine ln = fd_line(tp, tp1);
                            if (ln != null) {
                                ln.addApoint(po);
                                constraint cs2 = new constraint(constraint.PONLINE, po, ln, false);
                                this.addConstraintToList(cs2);

                            }
                            this.UndoAdded(po.getname() + ": the midpoint of " + tp1.m_name + tp.m_name);

                        } else {
                            po = pu;
                        }
                        clearSelection();
                    } else {
                        this.addObjectToList(tp, SelectList);
                    }
                }
            }
            break;
            case D_3PCIRCLE: {
                if (STATUS == 0) { // first click
                    clearSelection();
                    p = SmartgetApointFromXY(x, y);
                    this.addObjectToList(p, SelectList);
                    STATUS = 1;

                } else if (STATUS == 1) {
                    p = SmartgetApointFromXY(x, y);
                    this.addObjectToList(p, SelectList);
                    if (SelectList.size() == 2) {
                        STATUS = 2;
                    }

                } else { //third click
                    CPoint p1, p2, p3;
                    p1 = (CPoint) SelectList.get(0);
                    p2 = (CPoint) SelectList.get(1);

                    p3 = this.SelectAPoint(x, y);
                    if (p3 != null) {
                        if (drawbase.check_Collinear(p1, p2, p3))
                            break;
                    }

                    if (p3 == null)
                        p3 = SmartgetApointFromXY(x, y);
                    if (p3 == null) {
                        break;
                    }


                    if (SelectList.contains(p3)) {
                        break;
                    }

                    p = this.CreateANewPoint(0, 0);
                    constraint cs = new constraint(constraint.CIRCLE3P, p, p1, p2, p3);
                    CPoint pu = this.addADecidedPointWithUnite(p);
                    if (pu == null) {
                        Circle c = new Circle(p, p1, p2, p3);
                        p.m_name = this.get_cir_center_name();
                        this.addPointToList(p);
                        this.addConstraintToList(cs);
                        addCircleToList(c);
                        this.UndoAdded(c.getDescription());

                    } else {
                        p = pu;
                        if (!this.isCircleExists(p1, p2, p3)) {
                            Circle c = new Circle(p, p1, p2, p3);
                            this.addCircleToList(c);
                            this.UndoAdded(c.getDescription());
                        }
                    }

                    clearSelection();
                    STATUS = 0;
                }
            }
            break;

            case TRANSLATE: {
                FirstPnt = this.CreateATempPoint(x, y);
            }
            break;
            case ZOOM_IN:
                zoom_in(x, y, 1);
                reCalculate();
                break;
            case ZOOM_OUT:
                zoom_out(x, y, 1);
                reCalculate();
                break;
            case ANIMATION: {
                CatchPoint.setXY(x, y);
                p = this.SmartPoint(CatchPoint);

                if (SelectList.size() == 0) {
                    if (p != null) {
                        addToSelectList(p);
                    }
                    break;
                }
                if (p != null)
                    break;

                p = (CPoint) SelectList.get(0);
                CLine line = SmartPLine(CatchPoint);
                if (line != null && !check_animation(p, line))
                    break;

                AnimatePanel af = gxInstance.getAnimateDialog();
                if (line != null) {
                    clearSelection();
                    animate = new AnimateC(p, line, this.Width, this.Height);
                    af.setAttribute(animate);
                    gxInstance.showAnimatePane();
                    this.SetCurrentAction(MOVE);
                } else {
                    Circle c = this.SmartPCircle(CatchPoint);
                    if (c != null) {
                        clearSelection();
                        animate = new AnimateC(p, c, this.Width, this.Height);
                        af.setAttribute(animate);
                        gxInstance.showAnimatePane();
                        this.SetCurrentAction(MOVE);
                    } else {
                        CTrace ct = (CTrace) this.SelectFromAList(tracelist, x, y);
                        if (ct != null) {
                            clearSelection();
                            animate = new AnimateC(p, ct, this.Width, this.Height);
                            af.setAttribute(animate);
                            gxInstance.showAnimatePane();
                            this.SetCurrentAction(MOVE);
                        }
                    }
                }

            }
            break;
            case D_ANGLE: {

                if (STATUS == 0 && SelectList.size() == 0) {
                    FirstPnt = this.CreateATempPoint(x, y);

                    CLine line = SmartPLine(FirstPnt);
                    if (line != null) {
                        addToSelectList(line);
                    }
                } else if (STATUS == 0 && SelectList.size() == 1) {
                    SecondPnt = this.CreateATempPoint(x, y);
                    CLine line = SmartPLine(SecondPnt);
                    if (line != null) {
                        CLine l2 = (CLine) SelectList.get(0);
                        if (line == l2) {
                            break;
                        }

                        CAngle ag = new CAngle(l2, line, FirstPnt, SecondPnt);
                        addAngleToList(ag);
                        ag.move(x, y);
                        clearSelection();
                        addToSelectList(ag);
                        STATUS = 1;
                        this.UndoAdded(ag.getDescription(), false, false);
                    }
                } else if (STATUS == 1) {
                    STATUS = 0;
                    clearSelection();
                }
            }
            break;
            case SETEQSIDE: {
                CPoint pt = (CPoint) this.CatchList(pointlist, x, y);
                if (pt == null) {
                    clearSelection();
                    break;
                }
                if (SelectList.size() == 3) {
                    CPoint pt1 = (CPoint) SelectList.get(0);
                    CPoint pt2 = (CPoint) SelectList.get(1);
                    CPoint pt3 = (CPoint) SelectList.get(2);
                    if (STATUS == 1) {
                        constraint cs = new constraint(constraint.EQDISTANCE, pt1, pt2, pt3, pt);
                        this.addConstraintToList(cs);
                        this.charsetAndAddPoly(false);
                        clearSelection();
                        this.UndoAdded(pt1.m_name + pt2.m_name + " = " + pt3.m_name +
                                pt.m_name);
                    } else {
                        constraint cs = new constraint(constraint.NRATIO, pt1, pt2, pt3, pt, new Integer(v1), new Integer(v2));
                        this.addConstraintToList(cs);
                        this.charsetAndAddPoly(false);
                        clearSelection();
                        this.UndoAdded(pt1.m_name + pt2.m_name + " = " + STATUS +
                                " " + pt3.m_name + pt.m_name);

                    }
                } else {
                    addToSelectList(pt);
                }
            }
            break;

            case SETEQANGLE: {
                if (SelectList.size() == 0) {
                    CAngle ag = CatchAngle(x, y);
                    if (ag != null) {
                        addToSelectList(ag);
                    }
                } else if (SelectList.size() == 1) {
                    CAngle ag = CatchAngle(x, y);
                    CAngle ag1 = (CAngle) SelectList.get(0);

                    if (ag == ag1) {
                        clearSelection();
                        break;
                    }

                    if (ag != null && ag != ag1) {
                        CPoint pd = CAngle.canEqual(ag, ag1);
                        if (pd == null) {
                            CMisc.print("the angle is decided,can not be set equal");
                            clearSelection();
                        } else {
                            clearSelection();
                            constraint cs = new constraint(constraint.EQANGLE, ag1, ag);
                            this.addConstraintToList(cs);
                            this.charsetAndAddPoly(false);
//                            this.mulSolutionSelect(pd);
//                            this.reCalculate();
                            this.UndoAdded(ag.getDescription() + " = " +
                                    ag1.getDescription());
                        }
                    }
                }

            }
            break;

            case SETEQANGLE3P: {
                CAngle ag = (CAngle) this.SelectFromAList(anglelist, x, y);
                if (ag == null) {
                    break;
                }
                if (SelectList.size() == 2) {
                    CAngle ag1 = (CAngle) SelectList.get(0);
                    CAngle ag2 = (CAngle) SelectList.get(1);

                    Vector alist = this.getSpecificAngleList();
                    SpecificAngleDialog dlg = new SpecificAngleDialog(gxInstance, 2, alist);
                    dlg.setLocation(400, 400);
                    dlg.setTitle("Please select an specific angle");
                    dlg.setVisible(true);

                    Vector v = dlg.getSpecifcAngle();
                    if (v.size() == 1) {
                        Integer in = (Integer) v.get(0);
                        int va = in.intValue();
                        param pm = this.getParaForSpecificAngle(va);

                        constraint cs = new constraint(constraint.EQANGLE3P, ag1, ag2, ag, pm, va);
                        this.addConstraintToList(cs);
                        this.charsetAndAddPoly(false);
                        clearSelection();
                        this.UndoAdded(ag1.getDescription() + " + " +
                                ag2.getDescription() + " + " +
                                ag.getDescription() + " = " +
                                ag.getDescription());
                    } else {
                        clearSelection();
                        break;
                    }
                } else {
                    addToSelectList(ag);
                }
            }
            break;
            case SETCCTANGENT: {
                Circle c = (Circle) this.SelectFromAList(circlelist, x, y);
                if (c == null) {
                    break;
                }
                if (SelectList.size() == 1) {
                    Circle c0 = (Circle) SelectList.get(0);
                    constraint cs = new constraint(constraint.CCTANGENT, c0, c);
                    this.charsetAndAddPoly(false);
                    this.addConstraintToList(cs);
                    this.UndoAdded(c0.getDescription() + " tangent to " +
                            c.getDescription());
                } else {
                    addToSelectList(c);
                }
            }
            break;
            case D_SQUARE: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartPoint(CatchPoint);
                    if (pt == null) {
                        if (SelectList.size() == 0) {
                            CLine line = this.SmartPLine(CatchPoint);
                            if (line != null) {
                                addToSelectList(line);
                                STATUS = 1;
                                break;
                            }
                        }
                        pt = this.SmartgetApointFromXY(x, y);
                    }

                    if (SelectList.size() == 1) {
                        CPoint pa = (CPoint) SelectList.get(0);
                        this.setSmartPVLine(pa, pt);
                    }
                    if (!SelectList.contains(pt)) {
                        addToSelectList(pt);
                    }
                    if (SelectList.size() == 2) {
                        STATUS = 2;
                    }
                } else if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    addsquare(p1, p2, CatchPoint);
                    clearSelection();
                    STATUS = 0;

                }
            }
            break;
            case D_CCLINE: {
                if (SelectList.size() == 0) {
                    Circle c = this.SmartPCircle(CatchPoint);
                    if (c != null) {
                        this.addObjectToList(c, SelectList);
                    }
                } else if (SelectList.size() == 1) {
                    Circle c = this.SmartPCircle(CatchPoint);
                    if (c != null) {
                        Circle c0 = (Circle) SelectList.get(0);
                        if (c0.o == c.o) {
                            clearSelection();
                            break;
                        }

                        CLine line = new CLine(CLine.CCLine);
                        this.addLineToList(line);

                        constraint cs = new constraint(constraint.CCLine, line, c0,
                                c);
                        this.addConstraintToList(cs);
                        line.addconstraint(cs);
                        clearSelection();
                        this.UndoAdded(line.TypeString() + ":  radical of " +
                                c0.getDescription() + " and " +
                                c.getDescription());
                    }
                }

            }
            break;
            case D_IOSTRI: {
                if (SelectList.size() < 2) {
                    CPoint pt = SmartgetApointFromXY(x, y);
                    if (SelectList.size() == 1) {
                        CPoint pa = (CPoint) SelectList.get(0);
                        setSmartPVLine(pa, pt);
                    }
                    if (SelectList.size() == 0) {
                        addToSelectList(pt);
                    } else if (pt == SelectList.get(0)) {
                        clearSelection();
                    } else {
                        addToSelectList(pt);
                    }
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    addisoAngle(p1, p2, CatchPoint, 0);
                    clearSelection();
                    STATUS = 0;
                }
            }
            break;
            case DRAWTRIALL: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    addToSelectList(pt);
                    STATUS = 1;
                } else if (STATUS == 1) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    if (pt == SelectList.get(0)) {
                        STATUS = 0;
                        clearSelection();
                        break;
                    }
                    if (SelectList.size() == 1) {
                        CPoint pa = (CPoint) SelectList.get(0);
                        this.setSmartPVLine(pa, pt);
                    }
                    CPoint p1 = (CPoint) SelectList.get(0);
                    addToSelectList(pt);
                    if (fd_line(p1, pt) == null) {
                        CLine line = new CLine(p1, pt, CLine.LLine);
                        this.addLineToList(line);
                    }
                    STATUS = 2;
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint pt = CreateANewPoint(x, y);

                    constraint cs = new constraint(constraint.PETRIANGLE, pt, p1, p2);
                    CPoint pu = this.addADecidedPointWithUnite(pt);
                    if (pu == null) {
                        addConstraintToList(cs);
                        addPointToList(pt);
                    } else {
                        pt = pu;
                    }
                    addALine(CLine.LLine, pt, p1);
                    addALine(CLine.LLine, pt, p2);
                    clearSelection();
                    STATUS = 0;
                    UndoAdded("equilateral triangle " + pt.m_name + p1.m_name + p2.m_name);
                }
            }
            break;
            case RA_TRAPEZOID: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);

                    if (SelectList.size() > 0) {
                        CPoint pa = (CPoint) SelectList.get(SelectList.size() - 1);
                        this.setSmartPVLine(pa, pt);
                    }

                    if (!SelectList.contains(pt)) {
                        addToSelectList(pt);
                    }
                    if (SelectList.size() == 2) {
                        STATUS = 1;
                    }
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint p3 = this.SmartgetApointFromXY(x, y);
                    CPoint p4 = this.CreateANewPoint(x, y);
                    constraint cs = new constraint(constraint.RIGHT_ANGLE_TRAPEZOID, p1, p2, p3, p4);
                    CPoint pu = this.addADecidedPointWithUnite(p4);
                    if (pu == null) {
                        this.addALine(CLine.LLine, p1, p2);
                        this.addALine(CLine.LLine, p2, p3);
                        this.addALine(CLine.LLine, p3, p4);
                        this.addALine(CLine.LLine, p1, p4);
                        this.addPointToList(p4);
                        this.addConstraintToList(cs);
                        this.charsetAndAddPoly(false);
                    } else
                        p4 = pu;

                    this.UndoAdded("right trapezoid " + p1.m_name + p2.m_name + p3.m_name + p4.m_name);
                    STATUS = 0;
                    clearSelection();
                }
            }
            break;
            case TRAPEZOID: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    if (SelectList.size() > 0) {
                        CPoint pa = (CPoint) SelectList.get(SelectList.size() - 1);
                        this.setSmartPVLine(pa, pt);
                    }
                    if (!SelectList.contains(pt)) {
                        addToSelectList(pt);
                    } else {
                        break;
                    }
                    if (SelectList.size() == 3) {
                        STATUS = 1;
                    }
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint p3 = (CPoint) SelectList.get(2);
                    x = CatchPoint.getx();
                    y = (p1.gety() - p2.gety()) * (x - p3.getx()) /
                            (p1.getx() - p3.getx()) + p3.gety();
                    // y = (p1.gety() - p2.gety()) * (x - p3.getx()) / (p1.getx() - p2.getx()) + p3.gety();
                    CPoint p4 = this.SmartgetApointFromXY(x, y);
                    constraint cs1 = new constraint(constraint.TRAPEZOID, p1, p2, p3, p4);
                    CPoint pu = this.addADecidedPointWithUnite(p4);
                    p4.setXY(x, y);
                    if (pu == null) {
                        this.addALine(CLine.LLine, p1, p2);
                        this.addALine(CLine.LLine, p2, p3);
                        this.addALine(CLine.LLine, p3, p4);
                        this.addALine(CLine.LLine, p1, p4);
                        this.addPointToList(p4);
                        this.addConstraintToList(cs1);
                        this.charsetAndAddPoly(false);
                    } else
                        p4 = pu;
                    this.reCalculate();
                    this.UndoAdded("trapezoid " + p1.m_name + p2.m_name + p3.m_name + p4.m_name);
                    STATUS = 0;
                    clearSelection();

                }

            }
            break;
            case PARALLELOGRAM: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    addToSelectList(pt);
                    STATUS = 1;
                } else if (STATUS == 1) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    if (pt == SelectList.get(0)) {
                        STATUS = 0;
                        clearSelection();
                        break;
                    }
                    if (SelectList.size() == 1) {
                        CPoint pa = (CPoint) SelectList.get(0);
                        this.setSmartPVLine(pa, pt);
                    }
                    addToSelectList(pt);
                    STATUS = 2;
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint p3 = this.SmartgetApointFromXY(x, y);
                    CPoint p4 = this.CreateANewPoint(x, y);
                    constraint cs = new constraint(constraint.PARALLELOGRAM, p1, p2, p3, p4);
                    CPoint pu = this.addADecidedPointWithUnite(p4);
                    if (pu == null) {
                        this.addPointToList(p4);
                        addALine(CLine.LLine, p1, p2);
                        addALine(CLine.LLine, p1, p4);
                        addALine(CLine.LLine, p2, p3);
                        addALine(CLine.LLine, p3, p4);
                        this.addConstraintToList(cs);
                        this.charsetAndAddPoly(false);
                    } else
                        p4 = pu;
                    this.UndoAdded("parallelogram " + p1.m_name + p2.m_name + p3.m_name + p4.m_name);
                    STATUS = 0;
                    clearSelection();
                }

            }
            break;
            case RECTANGLE: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);

                    addToSelectList(pt);
                    STATUS = 1;
                } else if (STATUS == 1) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    if (pt == SelectList.get(0)) {
                        STATUS = 0;
                        clearSelection();
                        break;
                    }
                    if (SelectList.size() == 1) {
                        CPoint pa = (CPoint) SelectList.get(0);
                        this.setSmartPVLine(pa, pt);
                    }
                    addToSelectList(pt);
                    STATUS = 2;
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);

                    double x1 = p1.getx();
                    double y1 = p1.gety();
                    double x2 = p2.getx();
                    double y2 = p2.gety();

                    double xc = CatchPoint.getx();
                    double yc = CatchPoint.gety();

                    double dlx = x2 - x1;
                    double dly = y2 - y1;
                    double dl = dlx * dlx + dly * dly;

                    double xx = ((y2 - yc) * dlx * dly + dly * dly * xc +
                            dlx * dlx * x2) / dl;
                    double yy = ((x2 - xc) * dlx * dly + dlx * dlx * yc +
                            dly * dly * y2) / dl;

                    CPoint p3 = this.SmartgetApointFromXY(xx, yy);
                    double xt = x + p1.getx() - p2.getx();
                    double yt = y + p1.gety() - p2.gety();
                    CPoint p4 = this.CreateANewPoint(xt, yt);
                    constraint cs1 = new constraint(constraint.RECTANGLE, p1, p2, p3, p4);
                    CPoint pu = this.addADecidedPointWithUnite(p4);
                    if (pu == null) {
                        this.addPointToList(p4);
                        CLine tl1 = addALine(CLine.LLine, p1, p2);
                        CLine tl2 = addALine(CLine.LLine, p1, p4);
                        addALine(CLine.LLine, p2, p3);
                        addALine(CLine.LLine, p3, p4);
                        addCTMark(tl1, tl2);
                        this.addConstraintToList(cs1);
                        this.charsetAndAddPoly(false);
                    } else
                        p4 = pu;
                    this.UndoAdded("rectangle " + p1.m_name + p2.m_name + p3.m_name + p4.m_name);
                    STATUS = 0;
                    clearSelection();

                }
            }
            break;
            case DRAWTRISQISO: {
                if (STATUS == 0) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    addToSelectList(pt);
                    STATUS = 1;
                } else if (STATUS == 1) {
                    CPoint pt = this.SmartgetApointFromXY(x, y);
                    if (pt == SelectList.get(0)) {
                        STATUS = 0;
                        clearSelection();
                        break;
                    }
                    CPoint p1 = (CPoint) SelectList.get(0);
                    addALine(CLine.LLine, p1, pt);
                    addToSelectList(pt);
                    STATUS = 2;
                } else {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint pt = this.CreateANewPoint(x, y);
                    CLine ln1 = new CLine(pt, p1, CLine.LLine);
                    CLine ln2 = new CLine(pt, p2, CLine.LLine);
                    constraint cs = new constraint(constraint.PERPBISECT, pt, p1, p2);
                    constraint cs1 = new constraint(constraint.PERPENDICULAR, ln1, ln2);
                    CPoint pu = this.addADecidedPointWithUnite(pt);
                    if (pu == null) {
                        this.addPointToList(pt);
                        this.charsetAndAddPoly(false);
                        this.addConstraintToList(cs1);
                        this.addConstraintToList(cs);
                        this.addLineToList(ln1);
                        this.addLineToList(ln2);
                    }

                    clearSelection();
                    STATUS = 0;
                    this.UndoAdded("isoceles-right triangle " + pt.m_name + p1.m_name + p2.m_name);
                }
            }
            break;

            case DEFINEPOLY: {

                if (this.SelectAPoint(x, y) == null && SelectList.size() == 0) {
                    Circle c = SelectACircle(x, y);
                    if (c != null) {
                        for (int i = 0; i < polygonlist.size(); i++) {
                            CPolygon px = (CPolygon) polygonlist.get(i);
                            if (px.isEqual(c)) break;
                        }
                        if (this.fd_polygon(c) == null) {
                            CPolygon px = new CPolygon(c);
                            this.addPolygonToList(px);
                            clearSelection();
                            this.UndoAdded(px.getDescription());
                        }
                        break;
                    }
                } else {
                    FirstPnt = this.CreateATempPoint(x, y);
                    p = this.SmartPoint(FirstPnt);
                    if (p != null) {
                        if (STATUS == 0) {
                            CPolygon cp = new CPolygon();
                            cp.addAPoint(p);
                            addToSelectList(cp);
                            STATUS = 1;
                        } else {
                            CPolygon cp = (CPolygon) SelectList.get(0);
                            if (cp.addAPoint(p)) {
                                STATUS = 0;
                                addPolygonToList(cp);
                                clearSelection();
                                this.UndoAdded(cp.getDescription());
                            }
                        }
                    }
                }
            }
            break;
            case D_TEXT: {
                CText tc = (CText) this.SelectFromAList(textlist, x, y);
                dialog_addText(tc, (int) x, (int) y);
            }
            break;
            case MULSELECTSOLUTION: {
                for (int i = 0; i < solutionlist.size(); i++) {
                    p = (CPoint) solutionlist.get(i);
                    if (Math.pow(p.getx() - x, 2) + Math.pow(p.gety() - y, 2) < 18 * 18) {
                        pSolution.setXY(p.getx(), p.gety());
                        solutionlist.clear();
                        pSolution = null;
                        SetCurrentAction(PreviousAction);
                    }
                }
            }
            break;

            case SETTRACK: {
                CTrackPt = this.SelectAPoint(x, y);
                boolean r = false;

                for (int i = 0; i < tracelist.size(); i++) {
                    CTrace tr = (CTrace) tracelist.get(i);
                    if (tr.isTracePt(CTrackPt)) {
                        r = true;
                        break;

                    }
                }
                if (!r) {
                    CTrace t = new CTrace(CTrackPt);
                    this.addObjectToList(t, tracelist);
                    this.UndoAdded(t.toString());
                    if (gxInstance != null)
                        gxInstance.setActionMove();
                }
                break;
            }

            case LOCUS: {
                int n = SelectList.size();
                if (n <= 1) {
                    CPoint pt = this.SelectAPoint(x, y);
                    if (pt != null) {
                        if (n == 0 && !pt.isAFixedPoint()) {
                            JOptionPane.showMessageDialog(gxInstance, "The point should be a fix point", "Warning",
                                    JOptionPane.WARNING_MESSAGE);
                        } else
                            this.addObjectToList(pt, SelectList);
                        int k = SelectList.size();
                        if (k == 1)
                            gxInstance.setTipText("Please select the second point");
                        else if (k == 2)
                            gxInstance.setTipText("Please select a line or a circle");
                    }
                } else {
                    CPoint pt = (CPoint) SelectList.get(0);
                    CPoint pt1 = (CPoint) SelectList.get(1);
                    CLine ln = this.SelectALine(x, y);

                    if (ln != null) {
                        CTrace t = new CTrace(pt, pt1, ln);
                        this.addObjectToList(t, tracelist);
                        this.UndoAdded(t.toString());
                    } else {
                        Circle c = this.SelectACircle(x, y);
                        if (c != null) {
                            CTrace t = new CTrace(pt, pt1, c);
                            this.addObjectToList(t, tracelist);
                            this.UndoAdded(t.toString());
                        } else {
                        }
                    }
                    clearSelection();
                    this.reCalculate();
                }
            }
            break;
            case INCENTER:
            case BARYCENTER:
            case ORTHOCENTER:
            case CIRCUMCENTER: {
                CPoint pt = this.CreateATempPoint(x, y);
                CPoint tp = this.SmartPoint(pt);
                if (tp != null) {
                    this.addObjectToList(tp, SelectList);
                }
                if (SelectList.size() == 3) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint p3 = (CPoint) SelectList.get(2);
                    CPoint pp = this.CreateANewPoint(x, y);
                    constraint cs = null;
                    String s = null;
                    if (CurrentAction == BARYCENTER) {
                        cs = new constraint(constraint.BARYCENTER, pp, p1, p2, p3);
                        s = "barycenter";
                    } else if (CurrentAction == CIRCUMCENTER) {
                        cs = new constraint(constraint.CIRCUMCENTER, pp, p1, p2, p3);
                        s = " circumcenter";
                    } else if (CurrentAction == ORTHOCENTER) {
                        cs = new constraint(constraint.ORTHOCENTER, pp, p1, p2, p3);
                        s = "orthocenter";
                    } else if (CurrentAction == INCENTER) {
                        cs = new constraint(constraint.INCENTER, pp, p1, p2, p3);
                        s = "incenter";
                        pp.addcstoPoint(cs);
                    } else {
                        return;
                    }

                    CPoint pu = this.addADecidedPointWithUnite(pp);
                    if (pu == null) {
                        this.addPointToList(pp);
                        this.addConstraintToList(cs);
                        this.UndoAdded(pp.TypeString() + ":  the " + s + " of " +
                                p1.m_name + " " + p2.m_name + " " +
                                p3.m_name);

                    } else {
                        p = pu;
                    }
                    clearSelection();
                }
            }
            break;

            case NTANGLE: {
                if (STATUS == 0) {
                    CLine ln = (CLine) this.SelectFromAList(linelist, x, y);
                    if (ln != null) {
                        addToSelectList(ln);
                    }
                    if (SelectList.size() == 3) {
                        STATUS = 1;
                        Vector v = new Vector();
                        v.add(ln);
                        this.flashStep(v);
                    }
                } else if (STATUS == 1) {
                    CPoint pt = this.SelectAPoint(x, y);
                    if (pt != null) {
                        CLine ln = new CLine(CLine.NTALine);
                        ln.addApoint(pt);
                        addToSelectList(ln);
                        constraint cs = new constraint(constraint.NTANGLE,
                                SelectList);
                        clearSelection();
                        constraint cs1 = new constraint(constraint.PONLINE, pt, ln, false);
                        ln.addconstraint(cs);
                        this.addLineToList(ln);
                        this.addConstraintToList(cs1);
                        this.addConstraintToList(cs);
                        this.UndoAdded("eqanle added");
                    }
                }

            }
            break;

            case VIEWELEMENT: {
                viewElementFromXY(x, y);
            }
            break;
            case ARROW: {
                CPoint pt = (CPoint) this.SmartgetApointFromXY(x, y);
                if (pt == null) {
                    break;
                }
                if (SelectList.size() == 0) {
                    if (pt != null) {
                        this.addObjectToList(pt, SelectList);
                    }
                } else {
                    CPoint tp = (CPoint) SelectList.get(0);
                    if (tp == pt) {
                        break;
                    }
                    CArrow ar = new CArrow(pt, tp);
                    otherlist.add(ar);
                    clearSelection();
                    this.UndoAdded("Arrow " + ar.getDescription());
                }
            }
            case DISTANCE: {

                CPoint pt = (CPoint) this.SelectFromAList(pointlist, x, y);
                if (pt == null) {
                    break;
                }
                if (SelectList.size() == 0) {
                    if (pt != null) {
                        this.addObjectToList(pt, SelectList);
                    }
                } else {
                    CPoint tp = (CPoint) SelectList.get(0);
                    if (tp == pt) {
                        break;
                    }
                    CDistance dis = new CDistance(pt, tp);
                    distancelist.add(dis);
                    clearSelection();
                    this.UndoAdded("measure " + dis.getDescription());
                }

            }
            break;
            case EQMARK: {
                CPoint pt = (CPoint) this.SelectFromAList(pointlist, x, y);
                if (pt == null) {
                    break;
                }
                if (SelectList.size() == 0) {
                    if (pt != null) {
                        this.addObjectToList(pt, SelectList);
                    }
                } else {
                    CPoint tp = (CPoint) SelectList.get(0);
                    if (tp == pt)
                        break;

                    Cedmark ce = new Cedmark(pt, tp, STATUS);
                    otherlist.add(ce);
                    clearSelection();
                    this.UndoAdded("mark of " + pt.m_name + tp.m_name);
                }
            }
            break;
            case RAMARK: {
                CLine ln = (CLine) this.SelectFromAList(linelist, x, y);
                if (ln == null)
                    break;
                if (SelectList.size() == 0)
                    this.addObjectToList(ln, SelectList);
                else {
                    CLine ln1 = (CLine) SelectList.get(0);
                    if (ln == ln1)
                        break;
                    addCTMark(ln, ln1);
                    // this.addObjectToList(m, otherlist);
                    clearSelection();
                    this.UndoAdded("Right Angle Mark of " + ln.getDescription() + " and " + ln1.getDescription());
                }
            }
            break;
            case HIDEOBJECT: {
                CClass cc = this.SelectOneFromXY(x, y, 0);
                if (cc != null) {
                    constraint cs = new constraint(constraint.INVISIBLE, cc);
                    this.addConstraintToList(cs);
                    cc.setVisible(false);
                    UndoStruct un = this.UndoAdded("Hide " + cc.getDescription());
                    if (un != null) {
                        un.addRelatedObject(cc);
                    }
                }
            }
            break;
            case SHOWOBJECT: {
                CClass cc = null;
                for (int i = 0; i < constraintlist.size(); i++) {
                    constraint cs = (constraint) constraintlist.get(i);
                    if (cs.GetConstraintType() != constraint.INVISIBLE) {
                        continue;
                    }
                    CClass c1 = (CClass) cs.getelement(0);
                    if (c1.visible == false) {
                        c1.setVisible(true);
                        if (c1.select(x, y)) {
                            cc = c1;
                            constraint cs1 = new constraint(constraint.VISIBLE, cc);
                            this.addConstraintToList(cs1);
                            UndoStruct un = this.UndoAdded("Show " +
                                    cc.getDescription());
                            Vector v = new Vector();
                            v.add(cc);
                            this.setObjectListForFlash(v);
                            break;
                        } else {
                            c1.setVisible(false);
                        }
                    }
                }

            }
            break;

            case SANGLE: {
                int n = SelectList.size();
                if (n == 0) {
                    CLine line = this.SmartPLine(CatchPoint);
                    if (line != null && line.points.size() >= 2) {
                        addToSelectList(line);
                    }
                } else if (n == 1) {
                    p = SelectAPoint(x, y);
                    CLine ln1 = (CLine) SelectList.get(0);
                    if (p != null && ln1.pointOnLine(p))
                        addToSelectList(p);

                } else if (n == 2) {
                    CLine ln1 = (CLine) SelectList.get(0);
                    p = (CPoint) SelectList.get(1);


                    double k = ln1.getK();
                    double k1 = constraint.get_sp_ag_value(STATUS);
                    double kx1 = (k + k1) / (1 - k * k1);
                    double kx2 = (k - k1) / (1 + k * k1);

                    double r1 = CLine.distanceToPoint(p.getx(), p.gety(), kx1, x, y);
                    double r2 = CLine.distanceToPoint(p.getx(), p.gety(), kx2, x, y);


                    Integer I = null;
                    int id = 0;

                    if (r1 <= r2) {
                        I = new Integer(-STATUS);
                        id = add_sp_angle_value(-STATUS);
                    } else {
                        I = new Integer(STATUS);
                        id = add_sp_angle_value(STATUS);
                    }
                    CLine ln = new CLine(CLine.SALine);
                    ln.addApoint(p);
                    constraint cs = new constraint(constraint.SANGLE, ln1, ln, I);
                    cs.proportion = id;

                    ln.addconstraint(cs);
                    addConstraintToList(cs);
                    this.addLineToList(ln);
                    this.UndoAdded(ln.getDescription());
                    clearSelection();
                }
            }
            break;
            case D_BLINE: {
                addSelectPoint(x, y);
                if (SelectList.size() == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    if (p1 != p2) {
                        CLine ln = new CLine(CLine.BLine);
                        constraint cs = new constraint(constraint.BLINE, ln, p1, p2);
                        ln.addconstraint(cs);
                        this.addLineToList(ln);
                        this.addConstraintToList(cs);
                        clearSelection();
                        this.UndoAdded("BLine " + ln.getDescription());
                    }
                }
            }
            break;
            case D_TCLINE: {
                CatchPoint.setXY(x, y);

                if (SelectList.size() == 0) {
                    Circle c = SmartPCircle(CatchPoint);
                    if (c != null)
                        addToSelectList(c);
                } else {
                    Circle c = (Circle) SelectList.get(0);
                    if (c.on_circle(x, y)) {
                        CPoint p1 = SmartgetApointFromXY(x, y);
                        CLine ln = new CLine(p1, CLine.TCLine);
                        constraint cs = new constraint(constraint.TCLINE, c, ln, p1);
                        this.addConstraintToList(cs);
                        ln.addconstraint(cs);
                        this.addLineToList(ln);
                    }
                }
            }
            break;
            case CCTANGENT: {
                Circle c = this.SelectACircle(x, y);
                if (c != null) {
                    int n = SelectList.size();
                    if (n == 1) {
                        Circle c1 = (Circle) SelectList.get(0);
                        if (c != c1 && c.o != c1.o) {
                            CPoint p1 = this.CreateANewPoint(0, 0);
                            CPoint p2 = this.CreateANewPoint(x, y);
                            c1.addPoint(p1);
                            c.addPoint(p2);
                            constraint cs = new constraint(constraint.CCTANGENT_LINE, p1, p2, c1, c);
                            this.addPointToList(p1);
                            this.addPointToList(p2);
                            this.addConstraintToList(cs);
                            this.charsetAndAddPoly(false);
                            this.UndoAdded("TANGENT LINE");
                        }
                    } else
                        this.addObjectToList(c, SelectList);
                }
            }
            break;
            case RATIO: {
                int n = SelectList.size();
                p = SelectAPoint(x, y);
                if (p != null) {
                    if (n % 2 != 0 && p == SelectList.get(n - 1))
                        break;
                    addToSelectList(p);
                    setObjectListForFlash(p);
                }
                if (SelectList.size() == 8) {
                    constraint cs = new constraint(constraint.RATIO, SelectList);
                    this.addConstraintToList(cs);
                    this.charsetAndAddPoly(false);
                    this.UndoAdded("RATIO");
                    clearSelection();
                }
            }
            break;
            case EQUIVALENCE: {
                if (STATUS == 0) {
                    CPolygon g = (CPolygon) this.SelectFromAList(polygonlist, x, y);
                    if (g != null) {
                        addObjectToList(g, SelectList);
                        STATUS = 1;
                    }
                } else if (STATUS == 1) {
                    CPoint pt = this.SelectAPoint(x, y);
                    if (pt != null) {
                        addToSelectList(pt);
                        STATUS = 2;
                    } else {
                        CPolygon g = (CPolygon) SelectList.get(0);
                        int n = g.getPtn();

                        for (int i = 0; i < n - 1; i++) {
                            CPoint p1 = g.getPoint(i);
                            CPoint p2 = g.getPoint(i + 1);
                            if (CLine.mouse_on_line(x, y, p1.getx(), p1.gety(), p2.getx(), p2.gety())) {
                                addToSelectList(p1);
                                addToSelectList(p2);
                                vx1 = x;
                                vy1 = y;
                                STATUS = 3;
                                break;
                            }
                        }
                    }
                    if (STATUS == 1) {
                        STATUS = 0;
                        clearSelection();
                    }

                } else if (STATUS == 2) {
                    CPolygon g = (CPolygon) SelectList.get(0);
                    CPoint p1 = (CPoint) SelectList.get(1);
                    CPoint t1 = g.getPreviousePoint(p1);
                    CPoint t2 = g.getNextPoint(p1);
                    double[] r = getPTInterSection(x, y, p1.getx(), p1.gety()
                            , t1.getx(), t1.gety(), t2.getx(), t2.gety());
                    CPoint pt = this.SelectAPoint(r[0], r[1]);

                    if (pt != null && pt != t1) {
                        CPolygon poly = new CPolygon();
                        poly.copy(g);
                        int t = g.getPtn();

                        for (int i = 0; i < t; i++) {
                            CPoint m = g.getPoint(i);
                            if (m == p1)
                                m = pt;
                            poly.addAPoint(m);
                        }
                        if (this.findPolygon(poly.pointlist) != g) {
                            g.setVisible(false);
                            constraint cs = new constraint(constraint.EQUIVALENCE1, g, poly);
                            this.addConstraintToList(cs);
                            this.addObjectToList(poly, polygonlist);
                            this.UndoAdded("Area-Preserving");//+ g.getDescription() + " transformed to " + poly.getDescription());
                        }
                    }
                    STATUS = 0;
                    clearSelection();
                    g.setDraggedPoints(null, null, 0, 0);

                } else if (STATUS == 3) {
                    CPolygon g = (CPolygon) SelectList.get(0);
                    CPoint t1 = (CPoint) SelectList.get(1);
                    CPoint t2 = (CPoint) SelectList.get(2);
                    double dx = x - vx1;
                    double dy = y - vy1;

                    CPoint pt1 = this.SelectAPoint(t1.getx() + dx, t1.gety() + dy);
                    CPoint pt2 = this.SelectAPoint(t2.getx() + dx, t2.gety() + dy);
                    if (pt1 != null && pt2 != null && (pt1 != t1 || pt2 != t2)) {
                        CPolygon poly = new CPolygon();
                        poly.copy(g);
                        int t = g.getPtn();

                        for (int i = 0; i < t; i++) {
                            CPoint m = g.getPoint(i);
                            if (m == t1)
                                m = pt1;
                            else if (m == t2)
                                m = pt2;

                            poly.addAPoint(m);
                        }
                        if (this.findPolygon(poly.pointlist) != g) {
                            g.setVisible(false);
                            constraint cs = new constraint(constraint.EQUIVALENCE2, g, poly);
                            this.addConstraintToList(cs);
                            this.addObjectToList(poly, polygonlist);
                            this.UndoAdded("Area-Preserving");//g.getDescription() + " transformed to " + poly.getDescription());
                        }
                    }

                    STATUS = 0;
                    clearSelection();
                    g.setDraggedPoints(null, null, 0, 0);
                }
            }
            break;
            case FREE_TRANSFORM: {
                if (STATUS == 0) {
                    CPolygon g = (CPolygon) this.SelectAPolygon(x, y);//SelectFromAList(polygonlist, x, y);
                    if (g != null) {
                        this.addObjectToList(g, SelectList);
                        STATUS = 1;
                    }
                } else {
                    CPoint pt = this.SelectAPoint(x, y);
                    CPolygon poly = (CPolygon) SelectList.get(0);

                    if (pt == null) {
                        STATUS = 0;
                        clearSelection();
                        poly.setDraggedPointsNull();
                    } else {
                        if (SelectList.size() == 1) {
                            Vector v = poly.getDraggedPoints();
                            boolean already = false;
                            for (int i = 0; i < v.size() / 2; i++) {
                                if (v.get(i * 2) == pt) {
                                    already = true;
                                    break;
                                }
                            }
                            if (!already)
                                addToSelectList(pt);
                        } else {
                            CPoint t1 = (CPoint) SelectList.get(1);
                            poly.addDraggedPoints(t1, pt);
                            SelectList.remove(t1);
                            if (poly.allDragged()) {
                                add_free_transform();
                            }
                        }
                    }

                }

            }
            break;
            case TRANSFORM: {
                if (STATUS == 0) {
                    CPolygon g = (CPolygon) this.SelectAPolygon(x, y); // SelectFromAList(polygonlist, x, y);
                    if (g != null) {
                        this.addObjectToList(g, SelectList);
                        catchX = x;
                        catchY = y;
                        STATUS = 1;
                        FirstPnt = SecondPnt = ThirdPnt = null;
                    }
                } else if (STATUS == 1 || STATUS == 2) {
                    if (STATUS == 2 && (FirstPnt == null || ThirdPnt == null)) {
                        CPoint pt = this.SelectAPoint(x - vx1, y - vy1);
                        if (pt != null) {
                            x = pt.getx() + vx1;
                            y = pt.gety() + vy1;
                        }
                        if (FirstPnt == null) {
                            FirstPnt = this.CreateATempPoint(x, y);
                            SecondPnt = pt;
                            catchX = x - vx1;
                            catchY = y - vy1;
                        } else {
                            ThirdPnt = this.CreateATempPoint(x - vx1, y - vy1);
                        }
                    } else {
                        CPolygon poly = (CPolygon) SelectList.get(0);
                        clearSelection();
                        STATUS = 0;

                        int n = poly.getPtn();
                        double cx = catchX + vx1;
                        double cy = catchY + vy1;
                        double sin = Math.sin(vangle);
                        double cos = Math.cos(vangle);

                        if (Math.abs(vangle) < CMisc.ZERO) {
                            PolygonTransPointsCreated(poly);
                        }

                        for (int i = 0; i < n; i++) {
                            CPoint t = poly.getPoint(i);
                            double tx = (t.getx() + vx1);
                            double ty = (t.gety() + vy1);

                            tx -= cx;
                            ty -= cy;
                            double mx = (tx) * cos - (ty) * sin;
                            double my = (tx) * sin + (ty) * cos;
                            tx = mx + cx;
                            ty = my + cy;
                            CPoint t1 = this.SelectAPoint(tx, ty);
                            if (t1 == null) {
                                clearSelection();
                                break;
                            }
                            addToSelectList(t1);
                        }
                        if (SelectList.size() != 0) {
                            CPolygon poly1 = new CPolygon();
                            poly1.setPoints(SelectList);
                            if (this.findPolygon(SelectList) != poly) {
                                constraint cs = new constraint(constraint.TRANSFORM, poly, poly1, SecondPnt);

                                int r = -1;

                                if (CMisc.TransComfirmed) {
                                    String s1 = poly.getDescription() + " is transformed to " + poly1.getDescription();
                                    String s2 = "Do you want to keep the original polygon visible?";
                                    TransformConfirmDialog dlg = new TransformConfirmDialog(gxInstance.getFrame(), s1, s2);
                                    gxInstance.centerDialog(dlg);
                                    dlg.setVisible(true);
                                    r = dlg.getResult();
                                } else
                                    r = 1;

                                if (r == 0) {//JOptionPane.YES_OPTION) {
                                    cs.proportion = 0;
                                } else if (r == 1) {
                                    poly.setVisible(false);
                                    cs.proportion = 1;
                                } else {
                                }
                                if (r != 2) {
                                    this.addObjectToList(poly1, polygonlist);
                                    poly1.copy(poly);
                                    this.addConstraintToList(cs);
//                                    String s = "Isometry Transforming";
//                                    if (Math.abs(vangle) < CMisc.ZERO)
//                                        s = "Transforming";
//                                    else if (SecondPnt != null)
//                                        s = "Rotating";

                                    String s = poly.getDescription() + " = " + poly1.getDescription();
                                    this.UndoAdded(s);//);
                                }
                            }

                        }
                        STATUS = 0;
                        clearSelection();
                        vtrx = vtry = vx1 = vy1 = vangle = 0.0;
                        FirstPnt = SecondPnt = ThirdPnt = null;
                    }
                }
            }
            break;

        }

    }

    public void add_free_transform() {
        CPolygon p = (CPolygon) SelectList.get(0);
        Vector v = p.getTransformedPoints();
        CPolygon p1 = new CPolygon();
        p1.copy(p);
        p1.setPoints(v);
        constraint cs = new constraint(constraint.TRANSFORM1, p, p1, null);
        p.setVisible(false);
        this.addConstraintToList(cs);
        this.addObjectToList(p1, polygonlist);
        clearSelection();
        STATUS = 0;
        p.setDraggedPointsNull();
        this.UndoAdded(p.getDescription() + " transformed to " + p1.getDescription());
    }

    public CPoint meetTwoObject(Object obj1, Object obj2, boolean d, double x, double y) {
        if (obj1 instanceof CLine && obj2 instanceof CLine) {
            return MeetDifineAPoint((CLine) obj1, (CLine) obj2);
        } else if (obj1 instanceof Circle && obj2 instanceof Circle) {
            return MeetCCToDefineAPoint((Circle) obj1, (Circle) obj2, d, x, y);
        } else {
            if (obj1 instanceof CLine && obj2 instanceof Circle) {
                return MeetLCToDefineAPoint((CLine) obj1, (Circle) obj2, d, x, y);
            } else if (obj1 instanceof Circle && obj2 instanceof CLine) {
                return MeetLCToDefineAPoint((CLine) obj2, (Circle) obj1, d, x, y);
            }
        }
        return null;
    }

//    public void addToSelectList(Object p) {
//        if (p != null && !SelectList.contains(p))
//            addToSelectList(p);
//    }

    public void addSelectPoint(double x, double y) {
        CPoint p = this.SelectAPoint(x, y);
        if (p != null && !SelectList.contains(p)) {
            addToSelectList(p);
        }
    }

    public CLine addALine(int t, CPoint p1, CPoint p2) {
        CLine ln1 = this.fd_line(p1, p2);
        if (ln1 != null) {
            return ln1;
        }
        CLine ln = new CLine(p1, p2, t);
        this.addLineToList(ln);
        return ln;
    }

    public Vector printStep(String cc) {
        CMisc.print("***************************");

        Vector v = this.getConstructionFromDraw();
        v.add(cc);

        for (int i = 0; i < v.size(); i++) {
            String st = (String) v.get(i);
            CMisc.print(st);
        }
        return v;
    }

    public Vector getConstructionFromDraw() {

        Vector alist = new Vector();
        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);
            if (cs.csd != null)
                alist.add(cs.csd);
            if (cs.csd1 != null)
                alist.add(cs.csd1);
        }
        cons st = new cons(gib.C_POINT, pointlist.size());
        for (int i = 0; i < pointlist.size(); i++)
            st.add_pt(pointlist.get(i));
        alist.add(0, st);
        return alist;
    }

    public CPolygon findPolygon(Vector v) {
        for (int i = 0; i < polygonlist.size(); i++) {
            CPolygon p = (CPolygon) polygonlist.get(i);
            if (p.check_eq(v))
                return p;
        }
        return null;
    }

    public CPolygon findPolygon1(Vector v) {
        for (int i = 0; i < polygonlist.size(); i++) {
            CPolygon p = (CPolygon) polygonlist.get(i);
            if (p.check_rdeq(v))
                return p;
        }
        return null;
    }

    public boolean canAutoAnimate() {
        if (animate != null) {
            return true;
        }
        return false;
    }

    public boolean autoAnimate() {
        if (canAutoAnimate()) {
            AnimatePanel af = gxInstance.getAnimateDialog();

            if (af.isRunning()) {
                af.stopA();
                gxInstance.anButton.setSelected(false);
                return false;
            } else {
                af.setAttribute(this.animate);
                af.startA();
                gxInstance.anButton.setSelected(true);
                return true;
            }
        } else {
            gxInstance.anButton.setEnabled(false);
        }
        return false;
    }

    public void autoShowstep() {
        this.autoUndoRedo();
    }

    public void autoUndoRedo() {
        if (timer_type == 1) {
            timer.stop();
            this.redo();
            timer_type = 0;

        } else if (timer_type == 0) {
            if (this.undolist.size() == 0 && this.redolist.size() == 0) {
                return;
            }

            if (timer != null) {
                timer.stop();
            }
            timer = new Timer(700, this);
            timer.setInitialDelay(700 * 2);
            timer.start();
            timer_type = 1;
        }
    }


    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == timer) {
            if (timer_type == 1) {
                if (this.redolist.size() == 0) {
                    if (timer.getDelay() == 1400) {
                        timer.setDelay(1200);
                        return;
                    }
                    timer.setDelay(1200);
                    this.Undo();
                    this.setUndoStructForDisPlay(null, false);
                } else {
                    if (isFlashFinished()) {
                        UndoStruct undo = (UndoStruct) redolist.get(redolist.size() - 1);
                        this.redo_step();
                        this.setUndoStructForDisPlay(undo, false);
                    }
                }
            } else if (timer_type == 2) {
                if (cpfield == null) {
                    return;
                }
                if (!this.nextProveStep()) {
                    this.proveStop();
                }
            } else if (timer_type == 3) {
            }
        }
        panel.repaint();
    }

    public void updateFlashDelay() {
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash f = (JFlash) flashlist.get(i);
            f.updateTimer();
        }
    }

    public void setTimerDelay(int delay) {
        if (timer == null) {
            return;
        }
        timer.setDelay(delay);
    }

    public void viewElement(CClass obj) {
        if (obj == null) {
            return;
        }
        CClass cc = (CClass) obj;

        if (gxInstance != null) {
            gxInstance.getDialogProperty().setVisible(true);
            gxInstance.cp.SetPanelType(cc);
        }
    }


    public void animationStart() {
        animate.startAnimate();
    }

    public void animationStop() {
        if (animate != null) {
            animate.stopAnimate();
        }
        this.reCalculate();
    }

    public void animationOntime() {
        animate.onTimer();
        this.reCalculate();

    }

    public CClass CatchList(Vector v, double x, double y) {
        for (int i = 0; i < v.size(); i++) {
            CClass cc = (CClass) v.get(i);
            if (cc.select(x, y)) {
                return cc;
            }
        }
        return null;

    }

    public CAngle CatchAngle(double x, double y) {
        for (int i = 0; i < anglelist.size(); i++) {
            CAngle ag = (CAngle) anglelist.get(i);
            if (ag.select(x, y)) {
                return ag;
            }
        }
        return null;
    }

    public void dialog_addText(CText tc, int x, int y) {
        TextFrame tf = new TextFrame(gxInstance, x, y);
        tf.setText(tc);
        gxInstance.centerDialog(tf);
        tf.setVisible(true);
    }

    public CPoint SmartgetApointFromXY(double x, double y) {
        CPoint pt = SmartAddPoint(x, y);
        return pt;
    }

    public void addPointToList(CPoint p) {
        if (p == null)
            return;

        if (pointlist.contains(p)) {
            return;
        }

        while (true && !p.hasNameSet()) {
            String s = getPointNameByCount(this.pnameCounter);
            if (null == this.findPoint(s)) {
                p.m_name = s;
                pnameCounter++;
                break;
            }
            pnameCounter++;
        }
        p.setColorDefault();
        pointlist.add(p);
        textlist.add(p.getPText());
        if (pointlist.size() == 2)
            optmizePolynomial();
        this.reCalculate();
    }

    public String getPointNameByCount(int n) {
        int in = (n) / 26;
        int number = n - in * 26;
        String s = "";
        if (in == 0) {
            char[] c = new char[1];
            c[0] = (char) (number + 'A');
            s = new String(c);
        } else {
            char[] c = new char[2];
            c[0] = (char) (number + 'A');
            c[1] = (char) ('0' + in);
            s = new String(c);
        }
        return s;
    }

    public void addAngleToList(CAngle ag) {
        if (anglelist.contains(ag)) {
            return;
        }
        anglelist.add(ag);
        textlist.add(ag.getText());
    }

    public void addLineToList(CLine line) {
        if (linelist.contains(line)) {
            return;
        }

//        int in = (this.plineCounter) / 26;
//        int number = this.plineCounter - in * 26;
//        if (in == 0) {
//            char[] c = new char[1];
//            c[0] = (char) (number + 'a');
//            line.m_name = new String(c);
//        } else {
//            char[] c = new char[2];
//            c[0] = (char) (number + 'a');
//            c[1] = (char) ('0' + in);
//            line.m_name = new String(c);
//        }
        String str = new String("l");
        str += new Integer(this.plineCounter).toString();
//        plineCounter++;
        line.m_name = str;
        this.plineCounter++;
        linelist.add(line);
    }


    public void addPolygonToList(CPolygon p) {
        if (p == null || polygonlist.contains(p))
            return;
        String s = "poly";
        int i = 0;

        while (true) {
            String s1 = s + i;
            boolean fd = false;
            for (int j = 0; j < polygonlist.size(); j++) {
                CPolygon p1 = (CPolygon) polygonlist.get(j);
                if (s1.equalsIgnoreCase(p1.m_name)) {
                    fd = true;
                    break;
                }
            }
            if (!fd) {
                s = s1;
                break;
            }
            i++;
        }

        p.m_name = s;
        this.polygonlist.add(p);
    }

    public void drawLineAndAdd(CPoint p1, CPoint p2) {
        if (p1 == null || p2 == null || p1 == p2) return;

        if (fd_line(p1, p2) == null) {
            CLine ln = new CLine(p1, p2);
            this.addLineToList(ln);
        }
    }

    public void addCircleToList(Circle c) {
        if (circlelist.contains(c)) {
            return;
        }
        String str = new String("c");
        str += new Integer(this.pcircleCounter).toString();
        pcircleCounter++;
        c.m_name = str;
        circlelist.add(c);
    }

    public boolean addObjectToList(Object obj, Vector list) {
        if (obj == null) {
            return false;
        }

        if (list.contains(obj))
            return false;
        if (list == SelectList) {
            this.addToSelectList(obj);
        } else
            list.add(obj);
        return true;
    }

    public void addConstraintToList(constraint cs) {
        if (cs != null && !constraintlist.contains(cs)) {
            constraintlist.add(cs);
        }
    }

    public void removeConstraintFromList(constraint cs) {
        constraintlist.remove(cs);
    }

    public void clearAllConstraint() {
        constraintlist.clear();
    }

    private boolean isLineExists(CPoint p1, CPoint p2) {
        for (int i = 0; i < linelist.size(); i++) {
            CLine ln = (CLine) linelist.get(i);
            if (ln.points.contains(p1) && ln.points.contains(p2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCircleExists(CPoint p1, CPoint p2, CPoint p3) {
        for (int i = 0; i < circlelist.size(); i++) {
            Circle ln = (Circle) circlelist.get(i);
            if (ln.points.contains(p1) && ln.points.contains(p2) &&
                    ln.points.contains(p3)) {
                return true;
            }
        }
        return false;
    }

    public void SelectByRect(double x1, double y1, double x2, double y2) {
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            double x = p.getx();
            double y = p.gety();
            if ((x - x1) * (x - x2) < 0 && (y - y1) * (y - y2) < 0) {
                addToSelectList(p);
            }
        }
        for (int i = 0; i < linelist.size(); i++) {
            CLine ln = (CLine) linelist.get(i);
            if (SelectList.containsAll(ln.points))
                addToSelectList(ln);
        }
        for (int i = 0; i < circlelist.size(); i++) {
            Circle ln = (Circle) circlelist.get(i);
            if (SelectList.contains(ln.o) && SelectList.containsAll(ln.points))
                this.addToSelectList(ln);
        }

        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            if (t.getType() != CText.NAME_TEXT && t.inRect(x1, y1, x2, y2))
                this.addToSelectList(t);
        }

    }

    public void DWButtonUp(double x, double y) {
        this.IsButtonDown = false;

        if (SNAP && CurrentAction != SELECT) {
            double[] r = getSnap(x, y);
            x = r[0];
            y = r[1];
        }
        CatchPoint.setXY(x, y);

        switch (this.CurrentAction) {
            case SELECT: {
                vx1 = x;
                vy1 = y;
            }
            break;
            case MOVE:
                break;
            case D_POINT:
                clearSelection();
                break;
            case H_LINE: {

                CPoint p = null;
                if (STATUS == 2) {
                    STATUS = 0;
                    break;
                }
                CPoint pt = this.CreateATempPoint(x, y);
                p = this.SmartPoint(pt);
                if (p == FirstPnt) {
                    STATUS = 0;
                    break;
                }
                if (p == null) {
                    p = SmartgetApointFromXY(x, y);
                }
                constraint cs = new constraint(constraint.HORIZONAL, FirstPnt, p);
                this.charsetAndAddPoly(false);
                this.addPointToList(p);
                this.addConstraintToList(cs);
                CLine ln = new CLine(FirstPnt, p);
                this.addLineToList(ln);
                this.UndoAdded(ln.getDescription() + " is a horizonal line");
                FirstPnt = null;
                STATUS = 0;
                break;
            }
            case V_LINE: {
                CPoint p = null;
                if (STATUS == 2) {
                    STATUS = 0;
                    break;
                }
                CPoint pt = this.CreateATempPoint(x, y);
                p = this.SmartPoint(pt);
                if (p == FirstPnt) {
                    STATUS = 0;
                    break;
                }
                if (p == null) {
                    p = SmartgetApointFromXY(x, y);
                }
                constraint cs = new constraint(constraint.VERTICAL, FirstPnt, p);
                this.charsetAndAddPoly(false);
                this.addPointToList(p);
                this.addConstraintToList(cs);
                CLine ln = new CLine(FirstPnt, p);
                this.addLineToList(ln);
                this.UndoAdded(ln.getDescription() + " is a vertical line");
                FirstPnt = null;
                STATUS = 0;
                break;
            }
            case D_LINE:
                break;
            case D_PARELINE:
                break;
            case D_PERPLINE:
                break;
            case PERPWITHFOOT: {

                if (STATUS == 1) {
                    CPoint p1 = FirstPnt;
                    CPoint pt = this.SmartPoint(CatchPoint);
                    if (pt == p1) {
                        break;
                    }

                    CLine line = this.SmartPLine(CatchPoint);
                    if (line == null) {
                        break;
                    }
                    CPoint p = this.CreateANewPoint(0, 0);
                    add_PFOOT(line, p1, p);
                    FirstPnt = null;
                    STATUS = 0;
                }
            }
            break;
            case D_PFOOT:
                break;
            case D_CIRCLE:
                break;
            case D_CIRCLEBYRADIUS:
                break;
            case D_PRATIO: {
            }
            break;
            case D_TRATIO: {

            }
            break;

            case D_MIDPOINT:
                break;
            case D_3PCIRCLE:
                break;
            case TRANSLATE:
                break;
            case D_SQUARE: {
                if (STATUS == 1) {
                    CLine line = (CLine) SelectList.get(0);
                    CPoint[] pl = line.getTowSideOfLine();
                    if (pl == null) {
                        break;
                    }
                    addsquare(pl[0], pl[1], CatchPoint);
                    clearSelection();
                    STATUS = 0;
                }
            }
            break;
            case D_IOSTRI:
                break;
        }
        IsButtonDown = false;
    }

    public void smartPVDragLine() {
        if (SelectList.size() != 1)
            return;
        CClass c = (CClass) SelectList.get(0);
        if (c.get_type() != CClass.POINT)
            return;
        CPoint pt = (CPoint) c;
        if (pt.isAFixedPoint())
            return;

        for (int i = 0; i < linelist.size(); i++) {
            CLine ln = (CLine) linelist.get(i);
            if (!ln.containPT(pt))
                continue;
            CPoint pt2 = ln.getSecondPoint(pt);
            if (pt2 != null && pt2.isAFreePoint()) {
                double r1 = Math.abs(pt.getx() - pt2.getx());
                double r2 = Math.abs(pt.gety() - pt2.gety());

                if (pt.isAFreePoint()) {
                    if (r1 < CMisc.PIXEPS && r2 < CMisc.PIXEPS) {
                        break;
                    } else if (r1 < CMisc.PIXEPS) {
                        pt.setXY(pt2.getx(), pt.gety());
                        break;
                    } else if (r2 < CMisc.PIXEPS) {
                        pt.setXY(pt.getx(), pt2.gety());
                        break;
                    }
                } else {
//                    if (r1 < CMisc.PIXEPS && r2 < CMisc.PIXEPS) {
//                        break;
//                    } else if (r1 < CMisc.PIXEPS) {
//                        pt.setXY(pt2.getx(), pt.gety());
//                        break;
//                    }
                }
            }

        }

    }

    public void DWMouseDrag(double x, double y) {

        if (SNAP && CurrentAction != SELECT) {
            double[] r = getSnap(x, y);
            x = r[0];
            y = r[1];
        }
        this.isPointOnObject = false;
        CatchPoint.setXY(x, y);

        switch (this.CurrentAction) {
            case SELECT: {
                if (Math.abs(vx1 - x) > 15 || Math.abs(vy1 - y) > 15) {
                    this.clearSelection();
                    SelectByRect(vx1, vy1, x, y);
                }
            }
            break;
            case MOVE: {
                if (FirstPnt == null) {
                    break;
                }

                ObjectLocationChanged(SelectList, FirstPnt, x, y);
                FirstPnt.setXY(x, y);
                smartPVDragLine();
                if (this.isRecal) {
                    this.reCalculate();
                }
            }
            break;

            case D_POINT: {
                if (this.IsButtonDown && SelectList.size() != 0) {
                    CPoint p = (CPoint) SelectList.get(0);
                    p.setXY(x, y);
                    this.reCalculate();
                }
            }
            break;
            case H_LINE:
                SecondPnt.setXY(x, FirstPnt.gety());
                break;
            case V_LINE:
                SecondPnt.setXY(FirstPnt.getx(), y);
                break;
            case D_LINE:
                if (FirstPnt != null) {
                    isPointOnObject = Smart(CatchPoint, x, y);
                    if (!isPointOnObject) {
//                        isSmartPoint = SmartLineType(FirstPnt.getx(), FirstPnt.gety(), CatchPoint);
                    } else {
//                        isSmartPoint = 0;
                    }
                }

                break;
            case D_PARELINE:
            case D_PERPLINE:
            case D_ALINE:
            case D_CIRCLE:
            case PERPWITHFOOT:
                this.isPointOnObject = Smart(CatchPoint, x, y);
                break;
            case TRANSLATE: {
                double dx = x - FirstPnt.getx();
                double dy = y - FirstPnt.gety();
                FirstPnt.setXY(x, y);
                translate(dx, dy);
            }
            break;
            case D_CIRCLEBYRADIUS: {
                if (STATUS == 1) {
                    SecondPnt.setXY(x, y);
                }
            }
            break;
            case D_PRATIO:
            case D_TRATIO:
            case D_3PCIRCLE:
            case D_SQUARE:
            case D_IOSTRI:
                break;

        }
    }

    private int SmartLineType(double x1, double y1, CPoint p2) {
        double x2 = p2.getx();
        double y2 = p2.gety();

        if (Math.abs(x2 - x1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            return 1; //vertical
        } else if (Math.abs(y2 - y1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            return 2; //horizonal
        } else {
            return 0;
        }

    }

    private int setSmartPVPointLocation(double x1, double y1, CPoint p2) {
        double x2 = p2.getx();
        double y2 = p2.gety();

        if (Math.abs(x2 - x1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            p2.setXY(x1, y2);
            return 1; //vertical
        } else if (Math.abs(y2 - y1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            p2.setXY(x2, y1);
            return 2; //horizonal
        } else {
            return 0;
        }

    }

    public void add_PFOOT(CLine line, CPoint p1, CPoint p) {
        CLine line1 = new CLine(p1, p);
//        constraint c1 = new constraint(constraint.PONLINE, p, line, false);
//        constraint c2 = new constraint(constraint.PONLINE, p, line1, false);
        CPoint[] pl = line.getTowSideOfLine();
        constraint cs = null;
        if (pl != null) {
            cs = new constraint(constraint.PFOOT, p, p1, pl[0], pl[1]);
            CPoint pu = this.addADecidedPointWithUnite(p);
            if (pu == null) {
                this.addPointToList(p);
                addLineToList(line1);
//                this.addConstraintToList(c1);
//                this.addConstraintToList(c2);
                this.addConstraintToList(cs);
                this.AddPointToLineX(p, line);
//                line.addApoint(p);
                addCTMark(line, line1);
                // this.addObjectToList(m, otherlist);
                line1.addconstraint(cs);
                this.UndoAdded(line1.getSimpleName() + " perp " +
                        line.getSimpleName() + " with foot " + p.m_name);

            } else {
                p = pu;
            }
        } else {
            AddPointToLine(p, line, false);
            cs = new constraint(constraint.PERPENDICULAR, line, line1);
            CPoint pu = this.addADecidedPointWithUnite(p);
            if (pu == null) {
                this.addPointToList(p);
                addLineToList(line1);
//                this.addConstraintToList(c1);
//                this.addConstraintToList(c2);
                addCTMark(line, line1);
                //  this.addObjectToList(m, otherlist);
                this.AddPointToLineX(p, line);
                this.addConstraintToList(cs);
                line1.addconstraint(cs);
                this.UndoAdded(line1.getSimpleName() + " perp " +
                        line.getSimpleName() + " with foot " + p.m_name);
            }
        }

    }

    private void translate(double dx, double dy) {

        if (isFreezed())
            return;

        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            p.setXY(p.getx() + dx, p.gety() + dy);
        }
        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            t.move(dx, dy);

        }
        this.reCalculate();

    }

    private void smartFreePointMoved(CPoint pt) {

    }

    private void ObjectLocationChanged(Vector list, CPoint old, double x, double y) {
        double x0 = FirstPnt.getx();
        double y0 = FirstPnt.gety();
        double dx = x - x0;
        double dy = y - y0;
        int n = list.size();
        if (n == 0)
            return;

        if (cpfield != null && list.size() == 0) {
            cpfield.drag(dx, dy);
        }

        if (n == 1) {
            CClass c = (CClass) list.get(0);
            int t = c.get_type();
            switch (t) {
                case CClass.POINT:
                    CPoint p = (CPoint) c;
                    if (!p.isFreezed())
                        p.setXY(x, y);
                    return;
                case CClass.LINE:
                    CLine ln = (CLine) c;
                    if (ln.isTwoEndFreePoints()) {
                        objectsListMoved(ln.points, dx, dy);
                    }
                    return;
                case CClass.CIRCLE:
                    circleLocationChanged((Circle) c, dx, dy);
                    return;
                case CClass.ANGLE:
                    CAngle ag = (CAngle) c;
                    ag.move(old.getx(), old.gety());
                    return;
                case CClass.TEXT:
                    CText ct = (CText) c;
                    ct.drag(x0, y0, dx, dy);
                    return;
                case CClass.TMARK:
                    CTMark m = (CTMark) c;
                    m.move(x0, y0);
                    return;
                case CClass.DISTANCE:
                    CDistance dis = (CDistance) c;
                    dis.drag(x, y);
                    return;
                case CClass.POLYGON: {
                    CPolygon cp = (CPolygon) c;
                    if (cp.ftype == 1) {
                        Circle cx = fd_circleOR((CPoint) cp.getElement(0), (CPoint) cp.getElement(1), (CPoint) cp.getElement(2));
                        circleLocationChanged(cx, dx, dy);
                    } else {
                        CPoint p1 = (CPoint) cp.getElement(0);
                        double xx = p1.getx();
                        double yy = p1.gety();
                        if (cp.isAllPointsFree()) {
                            objectsListMoved(cp.pointlist, dx, dy);
                            p1.setXY(xx + dx, yy + dy);
                        }
                    }
                }
            }
            return;
        }

        if (isFreezed())
            return;


        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            p.setXY(p.getx() + dx, p.gety() + dy);
        }
    }

    private void circleLocationChanged(Circle c, double dx, double dy) {
        Circle c1 = (Circle) c;
        CPoint p1 = c1.o;
        if (!p1.isFreezed())
            p1.setXY(p1.getx() + dx, p1.gety() + dy);
        objectsListMoved(c1.points, dx, dy);
        return;
    }

    public void objectsListMoved(Vector list, double dx, double dy) {
        for (int i = 0; i < list.size(); i++) {
            CClass c = (CClass) list.get(i);
            int t = c.get_type();
            switch (t) {
                case CClass.POINT:
                    CPoint p = (CPoint) c;
                    if (!p.isFreezed())
                        p.setXY(p.getx() + dx, p.gety() + dy);
                    break;
            }
        }


    }

    public void DWMouseMove(double x, double y) {
        if (SNAP && CurrentAction != SELECT) {
            double[] r = getSnap(x, y);
            x = r[0];
            y = r[1];
        }

        MouseX = (int) x;
        MouseY = (int) y;

        CatchPoint.setXY(x, y);
        isPointOnObject = false;
        isPointOnIntersection = false;

        switch (this.CurrentAction) {
            case SELECT:

            case MOVE:
                if (cpfield != null) {
                    cpfield.mouseMove(x, y);
                }
                break;
            case HIDEOBJECT: {
                int n1 = CatchList.size();
                OnCatch(x, y);
                if (CatchList.size() != 0 || n1 != 0) {
                    if (panel != null)
                        panel.repaint();
                }
            }
            break;

            case D_LINE:
                if (STATUS == 1) {
                    SmartmoveCatch(x, y);
                    if (!isPointOnObject) {
                    } else {
                    }
                } else if (STATUS == 0)
                    SmartmoveCatch(x, y);
                break;

            case D_POLYGON:
                if (SecondPnt != null && !Smart(SecondPnt, x, y)) {
                    SmartmoveCatch(x, y);
                } else
                    SmartmoveCatch(x, y);
                if (SecondPnt != null)
                    SecondPnt.setXY(x, y);
                break;
            case D_POINT:
            case TRIANGLE:
            case D_PARELINE:
            case D_PERPLINE:
            case D_CIRCLE:

            case D_ALINE:
            case D_MIDPOINT:
            case D_3PCIRCLE:
            case D_PRATIO:
            case D_TRATIO:
            case D_SQUARE:
            case RECTANGLE:
            case DRAWTRIALL:
            case TRAPEZOID:
            case RA_TRAPEZOID:
            case PARALLELOGRAM:
            case DRAWTRISQISO:
            case SANGLE:
                SmartmoveCatch(x, y);
                break;
            case PERPWITHFOOT:
                if (STATUS == 0)
                    SmartmoveCatch(x, y);
                else
                    this.SmartmoveCatchLine(x, y);
                break;
            case D_IOSTRI:
                if (SelectList.size() == 2)
                    moveCatch(catchX, catchY);
                else
                    SmartmoveCatch(x, y);
                break;
            case D_PFOOT: {
                if (SelectList.size() == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);

                    double[] r = get_pt_dmcr(p1.getx(), p1.gety(), p2.getx(), p2.gety(), x, y);
                    x = r[0];
                    y = r[1];
                }
                SmartmoveCatch(x, y);
            }
            break;
            case D_PTDISTANCE:
                if (SelectList.size() < 3)
                    this.SmartmoveCatchPt(x, y);
                else
                    this.SmartmoveCatch(x, y, 6);
                break;

            case D_ANGLE:
                if (STATUS == 1) {
                    CAngle ag = (CAngle) SelectList.get(0);
                    ag.move(x, y);
                }
                break;
            case DEFINEPOLY: {
                if (STATUS == 1)
                    FirstPnt.setXY(x, y);
                SmartmoveCatchPt(x, y);
            }
            case INCENTER:
            case BARYCENTER:
            case ORTHOCENTER:
            case CIRCUMCENTER:
                SmartmoveCatchPt(x, y);
                break;
            case D_TEXT: {
                CatchPoint.setXY(x, y);
                CClass cc = this.SelectFromAList(textlist, x, y);
                CatchList.clear();
                if (cc != null) {
                    CatchList.add(cc);
                }
            }
            break;
            case D_CCLINE: {
                CatchPoint.setXY(x, y);
                CatchList.clear();
                this.SelectFromAList(CatchList, circlelist, x, y);
            }
            break;
            case EQUIVALENCE: {
                if (STATUS == 2) {
                    CPolygon p = (CPolygon) SelectList.get(0);
                    CPoint p1 = (CPoint) SelectList.get(1);
                    CPoint t1 = p.getPreviousePoint(p1);
                    CPoint t2 = p.getNextPoint(p1);
                    double[] r = getPTInterSection(x, y, p1.getx(), p1.gety()
                            , t1.getx(), t1.gety(), t2.getx(), t2.gety());
                    p.setDraggedPoints(p1, null, r[0] - p1.getx(), r[1] - p1.gety());

                } else if (STATUS == 3) {
                    CPolygon p = (CPolygon) SelectList.get(0);
                    CPoint p1 = (CPoint) SelectList.get(1);
                    CPoint p2 = (CPoint) SelectList.get(2);
                    p.setDraggedPoints(p1, p2, x - vx1, y - vy1);
                }
            }
            break;
            case FREE_TRANSFORM: {
                if (STATUS == 1) {
                    if (SelectList.size() == 2) {
                        CPolygon poly = (CPolygon) SelectList.get(0);
                        CPoint pt = (CPoint) SelectList.get(1);
                        poly.setDraggedPoints(pt, null, CatchPoint.getx() - pt.getx(), CatchPoint.gety() - pt.gety());
                    }
                }
            }
            break;
            case TRANSFORM: {
                if (STATUS == 1) {
                    x = x - vtrx;
                    y = y - vtry;
                    vx1 = x - catchX;
                    vy1 = y - catchY;
                } else if (STATUS == 2) {
                    if (FirstPnt != null && ThirdPnt != null) {
                        vangle = Math.PI + CAngle.get3pAngle(ThirdPnt.getx(), ThirdPnt.gety(),
                                FirstPnt.getx() - vx1, FirstPnt.gety() - vy1, x - vx1, y - vy1);

                    }
                }

            }
            break;
        }

        if (this.CurrentAction != MOVE && this.CurrentAction != SELECT)
            panel.repaint();
    }


    public void SmartmoveCatchPt(double x, double y) {
        CPoint pt = this.SelectAPoint(x, y);
        CatchList.clear();
        if (pt != null) {
            isPointOnObject = true;
            CatchList.add(pt);
            CatchPoint.setXY(pt.getx(), pt.gety());
        }
    }

    public void SmartmoveCatchLine(double x, double y) {
        CLine pt = this.SelectALine(x, y);
        CatchList.clear();
        if (pt != null) {
            isPointOnObject = true;
            CatchList.add(pt);
            pt.pointonline(CatchPoint);
        }
    }

    public void SmartmoveCatch(double x, double y) {
        SmartmoveCatch(x, y, 0);
    }

    public void SmartmoveCatch(double x, double y, int type) { // 0. All.  1. Point Only.
        // 2. Line Only . 3. Circle Only.  4. P and L 5. P and C . 6. L and C.
        CatchList.clear();
        CatchType = 0;
        SelectAllFromXY(CatchList, x, y, 1);
        int n = CatchList.size();
        if (n > 0) {
            isPointOnObject = true;
            if (n == 1) {
                CClass c = (CClass) CatchList.get(0);
                if (c instanceof CLine) {
                    CLine ln = (CLine) c;
                    if (type == 0 || type == 2 || type == 4 || type == 6) {
                        ln.pointonline(CatchPoint);
                        if (ln.pointonMiddle(CatchPoint)) {
                            CatchType = 1;
                        }
                    }
                } else if (c instanceof Circle) {
                    Circle cr = (Circle) c;
                    if (type == 0 || type == 3 || type == 5 || type == 6)
                        cr.pointStickToCircle(CatchPoint);
                } else if (c instanceof CPoint) {
                    CPoint p = (CPoint) c;
                    if (type == 0 || type == 1 || type == 4 || type == 5)
                        CatchPoint.setXY(p.getx(), p.gety());
                }
            } else {
                if (type == 0 || type == 1 || type == 4 || type == 5)
                    get_Catch_Intersection(x, y);
            }
        } else {
            if (type == 0 || type == 4 || type == 5) {
                hvCatchPoint();
            }
        }
        mouseCatchX = (int) CatchPoint.getx();
        mouseCatchY = (int) CatchPoint.gety();
    }


    public void moveCatch(double x, double y) {
        int n = CatchList.size();
        CatchList.clear();
        Object obj = null;
        obj = SelectAPoint(x, y);
        if (obj == null)
            obj = this.SelectALine(x, y);
        if (obj == null)
            obj = this.SelectACircle(x, y);
        if (obj != null) {
            CatchList.add(obj);
            isPointOnObject = true;
        }
        if (n != 0)
            panel.repaint();
    }

    public void get_Catch_Intersection(double x, double y) {
        int k = 0;
        CLine ln = null;
        Circle c = null;
        Object o1, o2;
        o1 = o2 = null;
        for (int i = 0; i < CatchList.size(); i++) {
            Object o = CatchList.get(i);
            if (!(o instanceof CPoint)) {
                if (o1 == null)
                    o1 = o;
                else if (o2 == null)
                    o2 = o;
                k++;
            }
        }
        if (k >= 2) {
            double[] r = null;
            if (o1 instanceof CLine && o2 instanceof CLine) {
                r = intersect_ll((CLine) o1, (CLine) o2);
            } else if (o1 instanceof Circle && o2 instanceof Circle) {
                r = intersect_cc((Circle) o1, (Circle) o2);
            } else {
                if (o1 instanceof CLine && o2 instanceof Circle) {
                    r = intersect_lc((CLine) o1, (Circle) o2);
                } else if (o1 instanceof Circle && o2 instanceof CLine) {
                    r = intersect_lc((CLine) o2, (Circle) o1);
                }
            }
            if (r != null && r.length > 0) {
                int d = -1;
                double len = Double.MAX_VALUE;
                int l = r.length;
                int j = 0;
                for (j = 0; j < l / 2; j++) {
                    double s = Math.pow(r[j * 2] - x, 2) + Math.pow(r[j * 2 + 1] - y, 2);
                    if (s < len) {
                        d = j;
                        len = s;
                    }
                }
                if (d >= 0) {
                    x = r[d * 2];
                    y = r[d * 2 + 1];
                    if (SelectAPoint(x, y) == null) {
                        CatchPoint.setXY(x, y);
                        isPointOnIntersection = true;
                    }
                }
            } else
                isPointOnIntersection = true;
        }
    }

    public boolean Smart(CPoint p, double x, double y) { // set p to a point on obj and near x,y
        p.setXY(x, y);
        CPoint pt = SmartPoint(p);
        if (pt != null) {
            return true;
        }
        CLine line = SmartPLine(p);
        if (line != null) {
            return true;
        }
        Circle c = SmartPCircle(p);
        if (c != null) {
            return true;
        }
        return false;
    }

    public CPoint SmartAddPoint(double x, double y) { // add a new point to drawing with x,y
        CPoint pt = SelectAPoint(x, y);
        if (pt != null)
            return pt;


        Vector v = new Vector();
        SelectFromAList(v, linelist, x, y);
        SelectFromAList(v, circlelist, x, y);
        if (v.size() >= 2) {
            return meetTwoObject(v.get(0), v.get(1), true, x, y);
        }

        CPoint p = this.CreateANewPoint(x, y);
        this.addPointToList(p);
        p.hasSetColor = false;
        int n = v.size();
        if (n == 0) {
            setCatchHVPoint(p);
        } else if (n == 1) {
            Object obj = v.get(0);
            if (obj instanceof CLine) {
                CLine ln = (CLine) obj;
                ln.pointOnLine(p);
                ln.pointonMiddle(p);
                AddPointToLine(p, (CLine) obj, false);
            } else if (obj instanceof Circle)
                AddPointToCircle(p, (Circle) obj, false);
            charsetAndAddPoly(false);
        }
        return p;
    }

    public Object SmartPointOnWhich(CPoint p) {
        CPoint pt = SmartPoint(p);
        if (pt != null) {
            return pt;
        }
        CLine line = SmartPLine(p);
        if (line != null) {
            return line;
        }
        Circle c = SmartPCircle(p);
        if (c != null) {
            return c;
        }
        return null;
    }


    public CLine SelectALine(double x, double y) {
        return SmartPointOnLine(x, y);
    }

    public Circle SelectACircle(double x, double y) {
        for (int i = 0; i < circlelist.size(); i++) {
            Circle c = (Circle) circlelist.get(i);
            if (c.nearcircle(x, y, CMisc.PIXEPS)) {
                return c;
            }
        }
        return null;
    }

    public CPoint SelectAPoint(double x, double y) {
        CPoint pt = null;
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            if (p.select(x, y)) {
                pt = p;
                break;
            }
        }
        return pt;
    }


    public void clearFlashAndAdd(Vector v) {
        clearFlash();
        flashlist.addAll(v);
    }

    public void clear_but_angle() {
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash ff = (JFlash) flashlist.get(i);
            if (ff instanceof JAngleFlash) {
                continue;
            }
            ff.stop();
            flashlist.remove(i);
            i--;
        }
    }

    public void doFlash() {
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash ff = (JFlash) flashlist.get(i);
            if (!ff.isfinished()) {
                ff.start();
                ff.stop();
            }
            if (ff.getvisibleType()) {
                flashlist.remove(ff);
                i--;
            }
        }
    }

    public void clearFlash() {
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash ff = (JFlash) flashlist.get(i);
            ff.stop();
        }
        flashlist.clear();
    }

    public void addFlash(JFlash f) {
        if (f == null)
            return;

        clearFlash();
        flashlist.add(f);
        f.start();
    }

    public CPoint getCommonPoint(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        CLine ln1 = this.fd_line(p1, p2);
        CLine ln2 = this.fd_line(p3, p4);
        if (ln1 == null) {
            ln1 = ln2;
            ln2 = null;
            CPoint p = p1;
            p1 = p3;
            p3 = p;
            p = p2;
            p2 = p4;
            p4 = p;
        }

        if (ln1 != null) {
            Vector v = ln1.points;
            if (ln2 == null) {
                if (v.contains(p3)) {
                    return p3;
                }
                if (v.contains(p4)) {
                    return p4;
                }
            } else {
                Vector v2 = ln2.points;
                for (int i = 0; i < v2.size(); i++) {
                    if (v.contains(v2.get(i))) {
                        return (CPoint) v2.get(i);
                    }
                }
            }
        } else {
            if (p1 == p3 || p1 == p4) {
                return p1;
            }
            if (p2 == p3 || p2 == p4) {
                return p2;
            }
        }
        return null;

    }

    public void addCgFlash(JCgFlash f1, JCgFlash f2, JFlash f) {
        int size = flashlist.size();
        int n = 0;
        for (int j = 0; j < size; j++) {
            JFlash fx = (JFlash) flashlist.get(j);
            if (fx instanceof JCgFlash)
                n++;
        }

        int i = 1;
        for (; true; i++) {
            int j = 0;
            for (j = 0; j < size; j++) {
                JFlash fx = (JFlash) flashlist.get(j);
                if (fx instanceof JCgFlash) {
                    JCgFlash fx1 = (JCgFlash) fx;
                    if (i == fx1.getDNum()) {
                        break;
                    }
                }
            }
            if (j == size)
                break;
        }

        if (n == 0) {
            f1.setDNum(2);
            f2.setDNum(2);
        } else {
            f1.setDNum(i);
            f2.setDNum(i);
        }
        addFlashx(f1);
        addFlashx(f);
        addFlashx(f2);
    }

    public boolean isInAction() {
        return STATUS != 0 || SelectList.size() != 0;
    }

    public void startFlash() {
        for (int j = 0; j < flashlist.size(); j++) {
            JFlash fx = (JFlash) flashlist.get(j);
            if (fx.isrRunning())
                return;
            if (!fx.isfinished() && !fx.isrRunning()) {
                fx.start();
                return;
            }
        }
    }

    public void addFlash2(JFlash f) {
        for (int i = 0; i < flashlist.size(); i++) {
            if (flashlist.get(i) instanceof JRedoStepFlash) {
                flashlist.add(i, f);
                return;
            }
        }
        addFlash1(f);
    }

    public void addFlash1(JFlash f) {

        addFlashx(f);
        if (flashlist.size() == 1)
            f.start();
    }

    public void addFlashx(JFlash f) {
        if (f == null)
            return;

        if (f instanceof JAngleFlash) {
            JAngleFlash tf = (JAngleFlash) f;
            int num = 0;

            CPoint pt = getCommonPoint(tf.p1, tf.p2, tf.p3, tf.p4);
            if (pt != null) {
                for (int i = 0; i < flashlist.size(); i++) {
                    Object obj = flashlist.get(i);
                    if (obj instanceof JAngleFlash) {
                        JAngleFlash ff = (JAngleFlash) obj;
                        if (pt == getCommonPoint(ff.p1, ff.p2, ff.p3, ff.p4)) {
                            num++;
                        }
                    }
                }
            }
            int d = 0;
            d = num * 10;
            tf.setRadius(tf.getRadius() + d);
        }
        if (!flashlist.contains(f))
            flashlist.add(f);
    }

    public boolean isFlashFinished() {
        int n = flashlist.size();
        if (n == 0) return true;
        if (n > 1) return false;
        JFlash f = (JFlash) flashlist.get(0);
        return (f.isfinished());

    }

    public void drawFlash(Graphics2D g2) {
        if (flashlist.size() == 0)
            return;

        boolean r = false;
        for (int i = 0; i < flashlist.size(); i++) {
            JFlash f = (JFlash) flashlist.get(i);
            if (f == null) {
                flashlist.remove(f);
            }
            if (r == false && !f.isrRunning() && !f.isfinished()) {
                f.start();
                f.draw(g2);
                r = true;
            } else if (f.isrRunning()) {
                f.draw(g2);
                r = true;
            }
            if (f.isfinished()) {
                if (f.getvisibleType()) {
                    flashlist.remove(i);
                    i--;
                } else
                    f.draw(g2);

            }
        }
        if (all_flash_finished()) {
            this.run_to_prove(null, null);// (UndoStruct) U_Obj);
        }
    }

    public void paintPoint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        drawGrid(g2);
        this.setAntiAlias(g2);
        if (undo != null) {
            undo.draw(g2);
        }

        drawList(polygonlist, g2);
        drawSelect(SelectList, g2);
        drawList(anglelist, g2);
        drawPerpFoot(g2, null, 0);
        drawList(tracelist, g2);
        drawList(distancelist, g2);
//        drawList(anglelist, g2);

        drawList(circlelist, g2);
        drawList(linelist, g2);
        drawList(otherlist, g2);

        drawFlash(g2);


        drawList(pointlist, g2);
        drawList(textlist, g2);

        drawCurrentAct(g2);
        drawCatch(g2);

        if (cpfield != null) {
            cpfield.draw(g2);
        }
        drawTrackpt(g2);
    }

    public void drawTrackpt(Graphics2D g2) {
        if (CTrackPt == null)
            return;
        CTrackPt.draw_ct(g2);

        for (int i = 0; i < tracelist.size(); i++) {
            CTrace tr = (CTrace) tracelist.get(i);
            if (tr.isTracePt(CTrackPt)) {
                tr.addTracePoint((int) CTrackPt.getx(), (int) CTrackPt.gety());
                return;
            }
        }
    }

    public CTrace getTraceByPt(CPoint pt) {
        for (int i = 0; i < tracelist.size(); i++) {
            CTrace tr = (CTrace) tracelist.get(i);
            if (tr.isTracePt(CTrackPt))
                return tr;
        }
        return null;
    }

    public void setSmartPVLine(CPoint p1, CPoint p2) {
        if (p1 == null || p2 == null) {
            return;
        }

        double x1 = p1.getx();
        double y1 = p1.gety();
        double x2 = p2.getx();
        double y2 = p2.gety();

        if (Math.abs(x2 - x1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            p2.setXY(x1, y2);
        } else if (Math.abs(y2 - y1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            p2.setXY(x2, y1);
        }

    }

    public void drawSmartPVLine(CPoint p1, CPoint p2, Graphics2D g2) {
        int x, y;
        x = y = 0;
        if (p1 == null || p2 == null) {
            return;
        }

        g2.setColor(Color.red);

        int x1 = (int) p1.getx();
        int y1 = (int) p1.gety();
        int x2 = (int) p2.getx();
        int y2 = (int) p2.gety();

        if (Math.abs(x2 - x1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            x = x1;
            if (y2 > y1) {
                y = (int) this.Height;
            } else {
                y = 0;
            }
            p2.setXY(x1, y2);
        } else if (Math.abs(y2 - y1) < CMisc.PIXEPS &&
                Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) >
                        4 * CMisc.PIXEPS * CMisc.PIXEPS) {
            y = y1;
            if (x2 > x1) {
                x = (int) this.Width;
            } else {
                x = 0;
            }
            p2.setXY(x2, y1);
        } else {
            g2.drawLine(x1, y1, x2, y2);
            return;
        }

        float dash[] = {2.0f};
        g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 5.0f, dash, 0.0f));
        g2.drawLine((int) p1.getx(), (int) p1.gety(), x, y);
    }

    public void drawCurrentAct(Graphics2D g2) {

//        if (trackPoint != null) {
//        }

        if (SHOWOBJECT == this.CurrentAction) {
            for (int i = 0; i < constraintlist.size(); i++) {
                constraint cs = (constraint) constraintlist.get(i);
                if (cs.GetConstraintType() != constraint.INVISIBLE) {
                    continue;
                }
                CClass c1 = (CClass) cs.getelement(0);
                if (c1.visible == false) {
                    c1.setVisible(true);
                    c1.draw(g2, true);
                    c1.draw(g2);
                    c1.setVisible(false);
                }
            }
        }
        setCurrentDrawEnvironment(g2);

        switch (this.CurrentAction) {
            case SELECT: {
                if (this.IsButtonDown) {
                    g2.setColor(Color.black);
                    g2.setStroke(CMisc.DashedStroke);
                    this.drawRect((int) vx1, (int) vy1, (int) CatchPoint.getx(), (int) CatchPoint.gety(), g2);
                }
            }
            break;
            case D_POINT: {
                drawCatchRect(g2);
                if (IsButtonDown)
                    for (int i = 0; i < SelectList.size(); i++) {
                        CPoint p = (CPoint) SelectList.get(i);
                        drawPointNameLocation(p, g2);
                    }
            }
            break;
            case H_LINE:
            case V_LINE:
                if (STATUS == 1) {
                    SecondPnt.draw(g2);
                    this.drawSmartPVLine(FirstPnt, SecondPnt, g2);
                } else
                    drawCatchRect(g2);
                break;
            case D_LINE: {
                this.drawSmartPVLine(FirstPnt, CatchPoint, g2);
                drawCatchRect(g2);
            }

            case D_TEXT: {
                if (gxInstance != null) {
//                      if(gxInstance.)
                }
            }
            break;
            case D_PARELINE:
                if (SelectList.size() == 0) {
                    drawCatchRect(g2);
                    break;
                } else {
                    CLine line = (CLine) SelectList.get(0);
                    CLine.drawPParaLine(line, CatchPoint, g2);
                    drawPointOrCross(g2);
                }
                break;
            case D_PERPLINE:
                if (SelectList.size() == 0) {
                    drawCatchRect(g2);
                    break;
                } else {
                    CLine line = (CLine) SelectList.get(0);
                    CLine.drawTPerpLine(line, CatchPoint, g2);
                    drawPointOrCross(g2);
                }
                break;
            case D_ALINE: {
                int n = SelectList.size();
                if (n == 3) {
                    this.drawPointOrCross(g2);
                }
                if (!IsButtonDown) {
                    n = SelectList.size();
                    if (n == 3) {
                        CLine ln1 = (CLine) SelectList.get(0);
                        CLine ln2 = (CLine) SelectList.get(1);
                        CLine ln3 = (CLine) SelectList.get(2);
                        double k = CLine.getALineK(ln1, ln2, ln3);
                        this.drawAuxLine((int) CatchPoint.getx(), (int) CatchPoint.gety(), k, g2);
                        this.drawPointOrCross(g2);
                    }
                }
            }
            break;
            case PERPWITHFOOT: {
                if (STATUS == 1) {
                    if (FirstPnt == null)
                        break;
                    g2.drawLine((int) FirstPnt.getx(), (int) FirstPnt.gety(), (int) CatchPoint.getx(), (int) CatchPoint.gety());
                    drawPointOrCross(g2);
                    if (CatchList.size() > 0) {
                        CLine ln = (CLine) CatchList.get(0);
                        double k0 = ln.getK();
                        CPoint pt = ln.getfirstPoint();
                        if (ln != null) {
                            double x, y;
                            double x0 = pt.getx();
                            double y0 = pt.gety();
                            double x1 = FirstPnt.getx();
                            double y1 = FirstPnt.gety();

                            if (Math.abs(k0) > CMisc.MAX_SLOPE) {
                                x = x0;
                                y = y1;
                            } else {
                                x = (k0 * (y1 - y0) + k0 * k0 * x0 + x1) / (1 + k0 * k0);
                                y = y0 + k0 * (x - x0);
                            }
                            g2.setColor(Color.red);
                            g2.setStroke(CMisc.DashedStroke);
                            g2.drawLine((int) x1, (int) y1, (int) x, (int) y);

                            if (ln.getExtent() != CLine.ET_ENDLESS) {
                                CPoint[] spt = ln.getTowSideOfLine();
                                if (spt != null && spt.length == 2) {
                                    double r1 = Math.pow(spt[0].getx() - x, 2) + Math.pow(spt[0].gety() - y, 2);
                                    double r2 = Math.pow(spt[1].getx() - x, 2) + Math.pow(spt[1].gety() - y, 2);
                                    double r = Math.pow(spt[1].getx() - spt[0].getx(), 2) + Math.pow(spt[1].gety() - spt[0].gety(), 2);
                                    if (r1 < r && r2 < r) {
                                    } else if (r1 > r2) {
                                        g2.drawLine((int) spt[1].getx(), (int) spt[1].gety(), (int) x, (int) y);
                                    } else {
                                        g2.drawLine((int) spt[0].getx(), (int) spt[0].gety(), (int) x, (int) y);
                                    }

                                }
                            }
                            g2.setStroke(CMisc.NormalLineStroke);
                            this.drawCross((int) x, (int) y, 5, g2);
                        }
                    }
                } else
                    drawCatchRect(g2);
            }
            break;
            case D_PTDISTANCE: {
                int n = SelectList.size();

                if (n >= 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    CPoint p3 = null;
                    if (n == 2) {
//                        if (CatchList.size() == 1) {
//                            CClass c = (CClass) CatchList.get(0);
//                            if (c instanceof CPoint)
//                                p3 = (CPoint) c;
//                        }
                    } else if (n == 3)
                        p3 = (CPoint) SelectList.get(2);
                    if (p3 != null) {
                        double radius = sdistance(p1, p2);
                        int x = (int) p3.getx();
                        int y = (int) p3.gety();
                        g2.setStroke(CMisc.DashedStroke);
                        g2.setColor(Color.red);
                        g2.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
                    }
                }
                this.drawCatchRect(g2);
            }
            break;
            case D_CIRCLE: {
                if (STATUS == 1) {
                    drawcircle2p(FirstPnt.getx(), FirstPnt.gety(), CatchPoint.getx(), CatchPoint.gety(), g2);
                    drawPointOrCross(g2);
                } else
                    this.drawCatchRect(g2);
                break;
            }
            case D_SQUARE: {
                if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    this.drawTipSquare(p1, p2, CatchPoint, g2);
                } else if (STATUS == 0) {
                    if (SelectList.size() > 0) {
                        CPoint p1 = (CPoint) SelectList.get(0);
                        this.drawSmartPVLine(p1, CatchPoint, g2);
                    }
                } else if (STATUS == 1) {
                    CLine line = (CLine) SelectList.get(0);
                    CPoint[] pl = line.getTowSideOfLine();
                    if (pl == null) {
                        break;
                    }
                    drawTipSquare(pl[0], pl[1], CatchPoint, g2);
                }
            }
            break;

            case D_PRATIO: {
                if (SelectList.size() == 2) {
                    drawCatchRect(g2);
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    double dx = p2.getx() - p1.getx();
                    double dy = p2.gety() - p1.gety();
                    double ratio = 0;
                    ratio = v1 * 1.00 / v2;
                    dx = dx * ratio;
                    dy = dy * ratio;
                    double x = CatchPoint.getx();
                    double y = CatchPoint.gety();
                    g2.setColor(Color.red);
                    g2.drawLine((int) x, (int) y, (int) (x + dx), (int) (y + dy));
                    this.drawCross((int) (x + dx), (int) (y + dy), 3, g2);
                }
            }
            break;
            case D_TRATIO: {
                if (SelectList.size() != 2) {
                    break;
                }
                CPoint p1 = (CPoint) SelectList.get(0);
                CPoint p2 = (CPoint) SelectList.get(1);

                double dx = p2.getx() - p1.getx();
                double dy = p2.gety() - p1.gety();

                double ratio = 0;
                ratio = v1 * 1.0 / v2;
                double x1 = CatchPoint.getx() + dy * ratio;
                double y1 = CatchPoint.gety() - dx * ratio;
//                double x2 = CatchPoint.getx() + dy * ratio;
//                double y2 = CatchPoint.gety() - dx * ratio;

//                double xx = SecondPnt.getx();
//                double yy = SecondPnt.gety();
//                double r1 = Math.pow(xx - x1, 2) + Math.pow(yy - y1, 2);
//                double r2 = Math.pow(xx - x2, 2) + Math.pow(yy - y2, 2);
                g2.setColor(Color.red);
//                if (r1 < r2) {
                g2.drawLine((int) x1, (int) y1, (int) CatchPoint.getx(), (int) CatchPoint.gety());
                this.drawCross((int) (x1), (int) (y1), 3, g2);

//                } else {
//                    g2.drawLine((int) x2, (int) y2, (int) FirstPnt.getx(), (int) FirstPnt.gety());
//                }
            }
            break;
/////////////////////////////////////////////////down;

            case DEFINEPOLY: {
                if (STATUS == 1 && SelectList.size() >= 1) {
                    CPolygon cp = (CPolygon) SelectList.get(0);
                    cp.draw(g2, FirstPnt);
                }
                this.drawCatchRect(g2);
            }
            break;
            case D_PFOOT: {
                int n = SelectList.size();
                if (n == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    double x = CatchPoint.getx();
                    double y = CatchPoint.gety();

                    double[] r = get_pt_dmcr(p1.getx(), p1.gety(), p2.getx(), p2.gety(), x, y);
                    double xr = r[0];
                    double yr = r[1];
                    double xx = (p1.getx() + p2.getx()) / 2;
                    double yy = (p1.gety() + p2.gety()) / 2;
                    double dis = sdistance(p1, p2);

                    CatchPoint.setXY(xr, yr);
                    drawCatchRect(g2);
                    g2.setColor(Color.red);
                    g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) p2.getx(), (int) p2.gety());
                    g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) xr, (int) yr);
                    g2.drawLine((int) xr, (int) yr, (int) p2.getx(), (int) p2.gety());
                    g2.setStroke(CMisc.DashedStroke);
                    g2.drawOval((int) (xx - dis / 2), (int) (yy - dis / 2), (int) dis, (int) dis);
                } else {
                    if (n == 1) {
                        CPoint pt = (CPoint) SelectList.get(0);
                        this.drawSmartPVLine(pt, CatchPoint, g2);
                    }
                    this.drawCatchInterCross(g2);

                }
            }
            break;

            case MULSELECTSOLUTION: {
                for (int i = 0; i < solutionlist.size(); i++) {
                    CPoint p = (CPoint) solutionlist.get(i);
                    g2.setColor(Color.red);
                    g2.drawOval((int) p.getx() - 18, (int) p.gety() - 18, 36, 36);
                    p.draw(g2);
                }
            }
            break;
            case D_POLYGON: {
                if (SelectList.size() >= 1) {
                    drawSmartPVLine(FirstPnt, SecondPnt, g2);
                    if (SelectList.size() == STATUS - 1) {
                        CPoint t1 = (CPoint) (SelectList.get(0));
                        g2.drawLine((int) t1.getx(), (int) t1.gety(), (int) SecondPnt.getx(), (int) SecondPnt.gety());
                    }
                    if (SelectList.size() >= 2) {
                        CPoint t1 = (CPoint) SelectList.get(0);

                        drawTipRect((int) t1.getx(), (int) t1.gety(), g2);

                        for (int i = 1; i < SelectList.size(); i++) {
                            CPoint tp = (CPoint) SelectList.get(i);
                            g2.drawLine((int) t1.getx(), (int) t1.gety(), (int) tp.getx(), (int) tp.gety());
                            t1 = tp;
                        }
                    }
//                    drawPointOrCross(g2);
                } //else
//                    drawCatchInterCross(g2);
                this.drawCatchRect(g2);
            }
            break;
            case DRAWTRIALL: {
                if (STATUS == 1) {
                    CPoint pt = (CPoint) SelectList.get(0);
                    drawSmartPVLine(pt, CatchPoint, g2);
                } else if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    double x1 = p1.getx();
                    double y1 = p1.gety();
                    double x2 = p2.getx();
                    double y2 = p2.gety();
                    double xt = (x1 + x2) / 2;
                    double yt = (y1 + y2) / 2;
                    double dx = xt - x1;
                    double dy = yt - y1;

                    double xf = xt - Math.sqrt(3) * dy;
                    double yf = yt + Math.sqrt(3) * dx;

                    double xs = xt + Math.sqrt(3) * dy;
                    double ys = yt - Math.sqrt(3) * dx;

                    double xc = CatchPoint.getx();
                    double yc = CatchPoint.gety();
                    g2.setColor(Color.red);

                    if ((xc - xf) * (xc - xf) + (yc - yf) * (yc - yf) <
                            (xc - xs) * (xc - xs) + (yc - ys) * (yc - ys)) {
                        g2.drawLine((int) x1, (int) y1, (int) xf, (int) yf);
                        g2.drawLine((int) x2, (int) y2, (int) xf, (int) yf);
                    } else {
                        g2.drawLine((int) x1, (int) y1, (int) xs, (int) ys);
                        g2.drawLine((int) x2, (int) y2, (int) xs, (int) ys);
                    }

                }
            }
            break;
            case RA_TRAPEZOID: {
                if (STATUS == 0 && SelectList.size() == 1) {
                    CPoint pt = (CPoint) SelectList.get(0);
                    this.drawSmartPVLine(pt, CatchPoint, g2);
                    drawPointOrCross(g2);
                } else if (STATUS == 1) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    double xt = CatchPoint.getx();
                    double yt = CatchPoint.gety();
                    double x1 = p1.getx();
                    double y1 = p1.gety();
                    double x2 = p2.getx();
                    double y2 = p2.gety();
                    double x, y;
                    if (Math.abs(x2 - x1) < CMisc.ZERO) {
                        x = x1;
                        y = yt;
                    } else {
                        double k = (y2 - y1) / (x2 - x1);
                        x = (k * k * xt + x1 + k * y1 - k * yt) / (k * k + 1);
                        y = (k * k * y1 + yt - k * xt + k * x1) / (k * k + 1);
                    }

                    drawPointOrCross(g2);
                    g2.setColor(Color.red);
                    g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
                    g2.drawLine((int) x1, (int) y1, (int) x, (int) y);
                    g2.drawLine((int) xt, (int) yt, (int) x, (int) y);
                    g2.drawLine((int) xt, (int) yt, (int) x2, (int) y2);
                } else
                    this.drawCatchRect(g2);
            }
            break;
            case TRAPEZOID: {
                if (STATUS == 0) {
                    if (SelectList.size() == 1) {
                        CPoint pt = (CPoint) SelectList.get(0);
                        drawSmartPVLine(pt, CatchPoint, g2);
                        this.drawPointOrCross(g2);
                    } else if (SelectList.size() == 2) {
                        CPoint pt = (CPoint) SelectList.get(0);
                        CPoint pt1 = (CPoint) SelectList.get(1);
                        g2.setColor(Color.red);
                        g2.drawLine((int) pt.getx(), (int) pt.gety(),
                                (int) pt1.getx(), (int) pt1.gety());
                        this.drawSmartPVLine(pt1, CatchPoint, g2);
                        this.drawPointOrCross(g2);
                    } else
                        this.drawCatchRect(g2);
                } else { //1
                    CPoint pt = (CPoint) SelectList.get(0);
                    CPoint pt1 = (CPoint) SelectList.get(1);
                    CPoint pt2 = (CPoint) SelectList.get(2);
                    double x = CatchPoint.getx();
                    double y = (pt.gety() - pt1.gety()) * (x - pt2.getx()) /
                            (pt.getx() - pt1.getx()) + pt2.gety();

                    g2.setColor(Color.red);
                    g2.drawLine((int) pt.getx(), (int) pt.gety(), (int) pt1.getx(), (int) pt1.gety());
                    g2.drawLine((int) pt2.getx(), (int) pt2.gety(), (int) pt1.getx(), (int) pt1.gety());
                    g2.drawLine((int) pt.getx(), (int) pt.gety(), (int) x, (int) y);
                    g2.drawLine((int) pt2.getx(), (int) pt2.gety(), (int) x, (int) y);
                }
            }
            break;


            case PARALLELOGRAM: {
                if (STATUS == 1) {
                    CPoint pt = (CPoint) SelectList.get(0);
                    drawSmartPVLine(pt, CatchPoint, g2);
                } else if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    drawSmartPVLine(p2, CatchPoint, g2);
                    double xt = p1.getx() + CatchPoint.getx() - p2.getx();
                    double yt = p1.gety() + CatchPoint.gety() - p2.gety();
                    g2.drawLine((int) xt, (int) yt, (int) p1.getx(), (int) p1.gety());
                    g2.drawLine((int) xt, (int) yt, (int) CatchPoint.getx(), (int) CatchPoint.gety());
                    g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) p2.getx(), (int) p2.gety());
                    drawPointOrCross(g2);
                }
            }
            break;
            case RECTANGLE: {
                if (STATUS == 1) {
                    CPoint pt = (CPoint) SelectList.get(0);
                    drawSmartPVLine(pt, CatchPoint, g2);
                } else if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    double x1 = p1.getx();
                    double y1 = p1.gety();
                    double x2 = p2.getx();
                    double y2 = p2.gety();

                    double xc = CatchPoint.getx();
                    double yc = CatchPoint.gety();

                    double dlx = x2 - x1;
                    double dly = y2 - y1;
                    double dl = dlx * dlx + dly * dly;

                    double x = ((y2 - yc) * dlx * dly + dly * dly * xc +
                            dlx * dlx * x2) / dl;
                    double y = ((x2 - xc) * dlx * dly + dlx * dlx * yc +
                            dly * dly * y2) / dl;

                    g2.setColor(Color.red);
                    double xt = x + p1.getx() - p2.getx();
                    double yt = y + p1.gety() - p2.gety();

                    g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) p2.getx(),
                            (int) p2.gety());
                    g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) xt,
                            (int) yt);
                    g2.drawLine((int) p2.getx(), (int) p2.gety(), (int) x, (int) y);
                    g2.drawLine((int) xt, (int) yt, (int) x, (int) y);

                }
            }
            break;
            case DRAWTRISQISO: {
                if (STATUS == 1) {
                    CPoint pt = (CPoint) SelectList.get(0);
                    drawSmartPVLine(pt, CatchPoint, g2);
                } else if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    double x1 = p1.getx();
                    double y1 = p1.gety();
                    double x2 = p2.getx();
                    double y2 = p2.gety();
                    double xt = (x1 + x2) / 2;
                    double yt = (y1 + y2) / 2;
                    double dx = xt - x1;
                    double dy = yt - y1;

                    double xf = xt - dy;
                    double yf = yt + dx;

                    double xs = xt + dy;
                    double ys = yt - dx;

                    double xc = CatchPoint.getx();
                    double yc = CatchPoint.gety();
                    g2.setColor(Color.red);

                    if ((xc - xf) * (xc - xf) + (yc - yf) * (yc - yf) <
                            (xc - xs) * (xc - xs) + (yc - ys) * (yc - ys)) {
                        g2.drawLine((int) x1, (int) y1, (int) xf, (int) yf);
                        g2.drawLine((int) x2, (int) y2, (int) xf, (int) yf);
                    } else {
                        g2.drawLine((int) x1, (int) y1, (int) xs, (int) ys);
                        g2.drawLine((int) x2, (int) y2, (int) xs, (int) ys);
                    }
                }
            }
            break;
            case D_IOSTRI: {
                if (SelectList.size() == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);
                    drawTipTirangle(p1, p2, CatchPoint, g2);
                } else {
                    if (SelectList.size() == 1) {
                        CPoint p = (CPoint) SelectList.get(0);
                        drawSmartPVLine(p, CatchPoint, g2);
                    }
                    drawCatchRect(g2);
                }
            }
            break;
            case TRIANGLE: {
                drawPointOrCross(g2);
                if (STATUS == 1 && SelectList.size() == 1) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CatchPoint.draw(g2);
                    g2.setColor(Color.red);
                    g2.drawLine((int) CatchPoint.getx(), (int) CatchPoint.gety(),
                            (int) p1.getx(), (int) p1.gety());
                } else if (STATUS == 2) {
                    if (SelectList.size() == 2) {
                        CPoint p1 = (CPoint) SelectList.get(0);
                        CPoint p2 = (CPoint) SelectList.get(1);

                        CatchPoint.draw(g2);
                        g2.setColor(Color.red);
                        g2.drawLine((int) CatchPoint.getx(), (int) CatchPoint.gety(),
                                (int) p1.getx(), (int) p1.gety());
                        g2.drawLine((int) CatchPoint.getx(), (int) CatchPoint.gety(),
                                (int) p2.getx(), (int) p2.gety());
                        g2.drawLine((int) p1.getx(), (int) p1.gety(), (int) p2.getx(),
                                (int) p2.gety());
                    }

                }
            }
            break;
            case D_3PCIRCLE: {

                if (STATUS == 2) {
                    CPoint p1 = (CPoint) SelectList.get(0);
                    CPoint p2 = (CPoint) SelectList.get(1);

                    double x_1 = p1.getx();
                    double x_2 = p1.gety();
                    double x_3 = p2.getx();
                    double x_4 = p2.gety();
                    double x_5 = CatchPoint.getx();
                    double x_6 = CatchPoint.gety();

                    double m = (2 * (x_3 - x_1) * x_6 + (-2 * x_4 + 2 * x_2) * x_5 +
                            2 * x_1 * x_4 - 2 * x_2 * x_3);

                    double x = (x_4 - x_2) * x_6 * x_6
                            +
                            (-1 * x_4 * x_4 - x_3 * x_3 + x_2 * x_2 + x_1 * x_1) *
                                    x_6
                            + (x_4 - x_2) * x_5 * x_5 + x_2 * x_4 * x_4
                            + (-1 * x_2 * x_2 - x_1 * x_1) * x_4 +
                            x_2 * x_3 * x_3;

                    x = (-1) * x / m;

                    double y = (-1) * ((2 * x_5 - 2 * x_1) * x
                            - x_6 * x_6 - x_5 * x_5 + x_2 * x_2 +
                            x_1 * x_1) / ((2 * x_6 - 2 * x_2));

                    double radius = Math.sqrt(Math.pow(x - x_1, 2) + Math.pow(y - x_2, 2));

                    g2.setStroke(CMisc.DashedStroke);
                    g2.setColor(Color.red);
                    if (Math.abs(x) < CMisc.MAX_DRAW_LEN && Math.abs(y) < CMisc.MAX_DRAW_LEN &&
                            radius < CMisc.MAX_DRAW_LEN) {
                        g2.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
                    }
                    this.drawPointOrCross(g2);
                } else
                    drawCatchRect(g2);


            }
            break;
            case SANGLE: {
                if (SelectList.size() == 2) {
                    CLine ln = (CLine) SelectList.get(0);
                    CPoint p = (CPoint) SelectList.get(1);
                    double k = ln.getK();
                    double k1 = constraint.get_sp_ag_value(STATUS);
                    double kx1 = (k + k1) / (1 - k * k1);
                    double kx2 = (k - k1) / (1 + k * k1);
                    if (ln.isVertical()) {
                        kx1 = -1 / k1;
                        kx2 = 1 / k1;
                    }

                    double x = CatchPoint.getx();
                    double y = CatchPoint.gety();

                    double r1 = CLine.distanceToPoint(p.getx(), p.gety(), kx1, x, y);
                    double r2 = CLine.distanceToPoint(p.getx(), p.gety(), kx2, x, y);
                    g2.setColor(Color.red);

                    if (r1 <= r2)
                        CLine.drawXLine(p.getx(), p.gety(), kx1, g2);
                    else
                        CLine.drawXLine(p.getx(), p.gety(), kx2, g2);
                }
            }
            break;
            case ZOOM_OUT:
            case ZOOM_IN: {
                if (mouseInside) {
                    g2.setStroke(CMisc.DashedStroke);
                    g2.setColor(Color.red);
                    int x = (int) CatchPoint.getx();
                    int y = (int) CatchPoint.gety();
                    g2.drawLine(x, 0, x, (int) Height);
                    g2.drawLine(0, y, (int) Width, y);
                }
            }
            break;
            case EQUIVALENCE: {
                if (STATUS == 2) {
                    CPolygon p = (CPolygon) SelectList.get(0);
                    CPoint p1 = (CPoint) SelectList.get(1);
                    CPoint t1 = p.getPreviousePoint(p1);
                    CPoint t2 = p.getNextPoint(p1);
                    if (p1 != null && t1 != null && t2 != null) {
                        this.drawAuxLine((int) p1.getx(), (int) p1.gety(), (t2.gety() - t1.gety()) / (t2.getx() - t1.getx()), g2);
                        double[] r = getPTInterSection(CatchPoint.getx(), CatchPoint.gety(), p1.getx(), p1.gety()
                                , t1.getx(), t1.gety(), t2.getx(), t2.gety());
                        this.drawCross((int) r[0], (int) r[1], 2, g2);
                    }
                } else if (STATUS == 3) {

                }
            }
            break;
            case TRANSFORM: {
                if (STATUS == 1 || STATUS == 2 || STATUS == 3) {
                    CPolygon p = (CPolygon) SelectList.get(0);
                    double x1 = catchX + vx1;
                    double y1 = catchY + vy1;
                    p.draw(g2, false, false, vx1, vy1, true, x1, y1, vangle);
                    if (STATUS == 2 && FirstPnt != null) {
                        g2.setColor(Color.red);
                        g2.drawLine((int) CatchPoint.getx(), (int) CatchPoint.gety(), (int) x1, (int) y1);
                        drawCross((int) FirstPnt.getx(), (int) FirstPnt.gety(), 2, g2);
                        if (ThirdPnt != null)
                            drawCross((int) (ThirdPnt.getx() + vx1), (int) (ThirdPnt.gety() + vy1), 2, g2);
                    }
                }
            }
            break;
            case MOVE: {

            }
            break;

            default:
                if (this.isPointOnObject) {
                    drawCatchRect(g2);
                }
                break;
        }
        drawCatchObjName(g2);
    }


    public void setFirstPnt(double x, double y) {
        if (FirstPnt != null) {
            vtrx = x - FirstPnt.getx();
            vtry = y - FirstPnt.gety();
        }
    }

    public void setTransformStatus(int t) {

        if (t == 0) {
            clearSelection();
            FirstPnt = SecondPnt = ThirdPnt = null;
        } else if (t == 1) {

        } else if (t == 2) {
            if (FirstPnt != null) {
                CPoint t1 = FirstPnt;
                t1.setXY(catchX + vx1, catchY + vy1);
                repaint();
            }
        }

        STATUS = t;
    }

    public void repaint() {
        panel.repaint();
    }

    double[] getFootPosition(double xc, double yc, double x1, double y1, double x2, double y2) {
        double dlx = x2 - x1;
        double dly = y2 - y1;
        double dl = dlx * dlx + dly * dly;

        double x = ((y2 - yc) * dlx * dly + dly * dly * xc + dlx * dlx * x2) / dl;
        double y = ((x2 - xc) * dlx * dly + dlx * dlx * yc + dly * dly * y2) / dl;
        double[] n = new double[2];
        n[0] = x;
        n[1] = y;
        return n;
    }

    double[] getPTInterSection(double x, double y, double xa, double ya, double x1, double y1, double x2, double y2) {
        double k = (y2 - y1) / (x2 - x1);
        double xt = (y - ya + k * xa + x / k) / (k + 1 / k);
        double yt = (x - xa + ya / k + k * y) / (k + 1 / k);
        if (Math.abs(1 / k) > CMisc.MAX_SLOPE) {
            xt = x;
            yt = ya;
        }
        double[] n = new double[2];
        n[0] = xt;
        n[1] = yt;
        return n;

    }

    public void addFlashPolygon(CPolygon p1, CPolygon p2, int t, boolean ct, double xc, double yc) {
        int n = p1.getPtn();
        if (n != p2.getPtn()) return;
        JPolygonFlash f = new JPolygonFlash(panel, p1, p2, ct, xc, yc, p1.getColorIndex(), p2.getColorIndex(), t);
        this.addFlash2(f);
    }

    public void drawAuxLine(int x, int y, double k, Graphics2D g2) {
        g2.setColor(Color.red);
        g2.setStroke(CMisc.DashedStroke);
        double max = CMisc.MAX_DRAW_LEN;
        if (Math.abs(k) > CMisc.MAX_K) {
            g2.drawLine(x, 0, x, (int) max);
        } else {
            if (k < 1 && k > -1) {
                g2.drawLine(0, (int) (y - k * x), (int) max, (int) (y + k * (max - x)));
            } else {
                g2.drawLine((int) (x - y / k), 0, (int) (x + (max - y) / k), (int) max);
            }
        }
    }

    public CClass getFirstCatchObject() {
        if (CatchList.size() == 0) return null;
        return (CClass) CatchList.get(0);
    }

    //./public double get
    public boolean addisoAngle(CPoint p1, CPoint p2, CPoint p, int type) {


        double x0 = p1.getx();
        double y0 = p1.gety();
        double rx = p2.getx() - x0;
        double ry = p2.gety() - y0;
        double dx = p.getx() - x0;
        double dy = p.gety() - y0;
        double rr = Math.sqrt(rx * rx + ry * ry);
        double cy = ry / rr;
        double cx = rx / rr;
        double r;
        boolean isleft = false;

        if (Math.abs(rx) < CMisc.ZERO) {
            if (ry * (p1.getx() - p.getx()) > 0) {
                isleft = false;
            } else {
                isleft = true;
            }
            r = Math.abs(p1.getx() - p.getx());
        } else {
            double k = ry / rx;
            r = Math.abs((p.gety() - k * p.getx() + k * p1.getx() - p1.gety())) /
                    Math.sqrt(1 + k * k);
            isleft = (rx * dy - ry * dx < 0);
        }

        double x1 = (x0 + p2.getx()) / 2;
        double y1 = (y0 + p2.gety()) / 2;

        double x2, y2;
        if (isleft) {
            x2 = (x1 + r * cy);
            y2 = (y1 - r * cx);
        } else {
            x2 = x1 - r * cy;
            y2 = y1 + r * cx;
        }

        CLine ln = fd_line(p1, p2);
        p.setXY(x2, y2);
        if (ln != null && ln.nearline(x2, y2)) return false;

        CPoint pt = SmartgetApointFromXY(x2, y2);
        CLine line = null;

        if (type == 0) { // iso
            constraint cs = new constraint(constraint.ISO_TRIANGLE, pt, p1, p2);
            this.addConstraintToList(cs);
            line = new CLine(pt, p1, CLine.LLine);
            this.addLineToList(line);
            line = new CLine(pt, p2, CLine.LLine);
            this.addLineToList(line);
            if (!isLineExists(p1, p2)) {
                CLine lp = new CLine(p1, p2, CLine.LLine);
                this.addLineToList(lp);
            }
            this.charsetAndAddPoly(false);
            this.UndoAdded("isoceles triangle " + p1.m_name + p2.m_name + pt.m_name);
        }

        return true;
    }


    public boolean addsquare(CPoint p1, CPoint p2, CPoint p) {
        CPoint t1, t2;
        t1 = p1;
        t2 = p2;

        double x0 = p1.getx();
        double y0 = p1.gety();

        double rx = p2.getx() - x0;
        double ry = p2.gety() - y0;
        double dx = p.getx() - x0;
        double dy = p.gety() - y0;
        double rr = Math.sqrt(rx * rx + ry * ry);
        double r;
        boolean isleft = false;
        if (Math.abs(rx) < CMisc.ZERO) {
            if (ry * (p1.getx() - p.getx()) > 0) {
                isleft = false;
            } else {
                isleft = true;
            }
            r = Math.abs(p1.getx() - p.getx());
        } else {
            double k = ry / rx;
            r = Math.abs((p.gety() - k * p.getx() + k * p1.getx() - p1.gety())) /
                    Math.sqrt(1 + k * k);
            isleft = (rx * dy - ry * dx < 0); //((ry * dx / rx - dy > 0 && ry / rx > 0) || (ry * dx / rx - dy < 0 && ry / rx < 0));
        }

        int n = (int) (0.5 + r / rr);
        if (Math.abs(n * rr - r) > 2 * CMisc.PIXEPS) {
            return false;
        }
        if (n == 0) {
            return false;
        }

        if (!this.isLineExists(p1, p2)) {
            CLine lp = new CLine(p1, p2, CLine.LLine);
            this.addLineToList(lp);
        }

        CPoint pa1, pa2;
        pa1 = pa2 = null;

//        CLine line1 = new CLine(p1, CLine.LLine);
//        CLine line2 = new CLine(p2, CLine.LLine);

        constraint cs1, cs2;
        for (int i = 0; i < n; i++) {
            pa1 = this.CreateANewPoint(0, 0);
            pa2 = this.CreateANewPoint(0, 0);
            CPoint tp1, tp2;
            if (isleft) {
                cs2 = new constraint(constraint.NSQUARE, pa2, p2, p1);
                tp2 = this.addADecidedPointWithUnite(pa2);
                if (tp2 == null) {
                    this.addConstraintToList(cs2);
                    this.addPointToList(pa2);
                } else {
                    pa2 = tp2;
                }

                cs1 = new constraint(constraint.PSQUARE, pa1, p1, p2);
                tp1 = this.addADecidedPointWithUnite(pa1);
                if (tp1 == null) {
                    this.addConstraintToList(cs1);
                    this.addPointToList(pa1);
                } else {
                    pa1 = tp1;
                }

                addCTMark(p1, pa1, p2, pa1);
                constraint cs = new constraint(constraint.SQUARE, p1, p2, pa2, pa1);
                this.addConstraintToList(cs);

            } else {
                cs2 = new constraint(constraint.PSQUARE, pa2, p2, p1);
                tp2 = this.addADecidedPointWithUnite(pa2);
                if (tp2 == null) {
                    this.addConstraintToList(cs2);
                    this.addPointToList(pa2);
                } else {
                    pa2 = tp2;
                }
                cs1 = new constraint(constraint.NSQUARE, pa1, p1, p2);
                tp1 = this.addADecidedPointWithUnite(pa1);
                if (tp1 == null) {
                    this.addConstraintToList(cs1);
                    this.addPointToList(pa1);
                } else {
                    pa1 = tp1;
                }
                addCTMark(p1, pa1, p2, pa1);
                constraint cs = new constraint(constraint.SQUARE, p1, p2, pa2, pa1);
                this.addConstraintToList(cs);
            }

//            AddPointToLineX(pa1,line1);
//            AddPointToLineX(pa2,line2);
            add_line(p1, pa1);
            add_line(p2, pa2);
            add_line(pa1, pa2);
            this.addCTMark(this.fd_line(p1, p2), this.fd_line(p1, pa1));
            this.UndoAdded("SQUARE " + p1 + p2 + pa2 + pa1);
            p1 = pa1;
            p2 = pa2;
        }
//        this.addLineToList(line1);
//        this.addLineToList(line2);
        if (pa1 != null && pa2 != null && fd_line(pa1, pa2) == null) {
            CLine line = new CLine(pa1, pa2, CLine.LLine);
            this.addLineToList(line);
        }
        this.UndoAdded("square " + t1.m_name + t2.m_name + pa2.m_name + pa1.m_name);

        return true;
    }

    public void add_line(CPoint p1, CPoint p2) {
        CLine ln = null;
        if ((ln = this.fd_line(p1, p2)) != null) {

            this.AddPointToLine(p1, ln);
            this.AddPointToLine(p2, ln);
            return;
        }
        ln = new CLine(p1, p2);
        this.addLineToList(ln);
    }

    public void drawTrackPoint(CPoint p, Graphics2D g2) {
        int x = (int) p.getx();
        int y = (int) p.gety();
        g2.setStroke(CMisc.NormalLineStroke);
        g2.setColor(Color.white);
        g2.fillOval(x - 5, y - 5, 10, 10);
        g2.setColor(Color.black);
        g2.drawOval(x - 5, y - 5, 10, 10);
        p.setDraw(g2);

        g2.fillOval(x - 3, y - 3, 6, 6);

    }

    public void drawselectPoint(CPoint p, Graphics2D g2) {
        int x = (int) p.getx();
        int y = (int) p.gety();

        g2.setColor(CMisc.SelectObjectColor);
        g2.fillOval(x - 7, y - 7, 14, 14);
    }

    public CPoint SmartPoint(CPoint p) {
        CPoint pt = SelectAPoint((int) p.getx(), (int) p.gety());
        if (pt != null) {
            p.setXY(pt.getx(), pt.gety());
            return pt;
        }
        return null;
    }

    public CLine SmartPLine(CPoint p) {
        CLine line = SmartPointOnLine(p.getx(), p.gety());
        if (line != null) {
            line.pointonline(p);
            return line;
        }
        return null;
    }

    public Circle SmartPCircle(CPoint p) {
        for (int i = 0; i < circlelist.size(); i++) {
            Circle c = (Circle) circlelist.get(i);
            if (c.visible() && c.nearcircle(p.getx(), p.gety(), CMisc.PIXEPS)) {
                c.SmartPonc(p);
                return c;
            }
        }
        return null;
    }

    public CLine SmartPointOnLine(double x, double y) {
        double dis = Double.MAX_VALUE;
        CLine ln = null;
        for (int i = 0; i < linelist.size(); i++) {
            CLine line = (CLine) linelist.get(i);
            double d;
            if (line.visible() && line.inside(x, y, CMisc.PIXEPS)) {
                d = CLine.distanceToPoint(line, x, y);
                if (d < dis) {
                    dis = d;
                    ln = line;
                }
            }

        }
        if (dis < CMisc.PIXEPS) {
            return ln;
        } else {
            return null;
        }
    }


    public CPoint CreateANewPoint(double x, double y) {
        if (paraCounter > 1023) {
            CMisc.print("point overflow.");
            return null;
        }

        param p1 = parameter[paraCounter -
                1] = new param(paraCounter++, x);
        param p2 = parameter[paraCounter -
                1] = new param(paraCounter++, y);
        CPoint p = new CPoint(p1, p2);
 //       this.setTextPositionAutomatically(p.ptext);
        return p;
    }


    public CPoint MeetDifineAPoint(CLine line1, CLine line2) {
        if (CLine.commonPoint(line1, line2) != null)
            return null;
        if (check_para(line1, line2)) {
            JOptionPane.showMessageDialog(gxInstance, gxInstance.getLanguage(1028, "The two lines you selected are parallel" +
                    ", don't have any intersection!")
                    , gxInstance.getLanguage(1029, "No intersection"), JOptionPane.ERROR_MESSAGE);
            return null;
        }

        int size1 = line1.points.size();
        int size2 = line2.points.size();

        if (size1 <= 1 || size2 <= 1) {
            CPoint p = this.CreateANewPoint(0, 0);

            AddPointToLine(p, line1, false);
            AddPointToLine(p, line2, false);

            charsetAndAddPoly(false);

            CPoint tp = this.addADecidedPointWithUnite(p);
            if (tp != null) {
                line2.points.remove(p);
                line1.points.remove(p);
                line2.addApoint(tp);
                line1.addApoint(tp);
                p = tp;
            } else {
                addPointToList(p);
            }
            this.reCalculate();
            this.UndoAdded(p.m_name + ": intersection of " +
                    line1.getDiscription() + " and " +
                    line2.getDiscription());
            return p;
        } else if (size1 > 1 && size2 > 1) {
            CPoint p = this.CreateANewPoint(0, 0);
            constraint cs1 = new constraint(constraint.INTER_LL, p, line1, line2);
            CPoint tp = this.addADecidedPointWithUnite(p);
//            poly.printpoly(p.x1.m);
//            poly.printpoly(p.y1.m);
            if (tp != null) {
                line2.addApoint(tp);
                line1.addApoint(tp);
                p = tp;
            } else {
                addPointToList(p);
                addConstraintToList(cs1);
                line1.addApoint(p);
                line2.addApoint(p);
//                this.charsetAndAddPoly(false);
            }
            this.UndoAdded(p.m_name + ": intersection of " +
                    line1.getDiscription() + " and " +
                    line2.getDiscription());
            return p;
        }
        return null;
    }

    public CPoint MeetLCToDefineAPoint(CLine line, Circle c, boolean m, double x, double y) {
        CPoint p = null;
        CPoint p1 = null;

        if (!check_lc_inter(line, c)) {
            JOptionPane.showMessageDialog(gxInstance, gxInstance.getLanguage(1030, "The line and the circle you selected don't have any intersection"),
                    gxInstance.getLanguage(1029, "No intersection"), JOptionPane.ERROR_MESSAGE);
            return null;
        }
        for (int i = 0; i < line.points.size(); i++) {
            Object obj = line.points.get(i);
            if (c.p_on_circle((CPoint) obj)) {
                if (p == null) {
                    p = (CPoint) obj;
                } else {
                    p1 = (CPoint) obj;
                }
            }
        }
        if (p1 != null && p != null) {
            JOptionPane.showMessageDialog(gxInstance, gxInstance.getLanguage(1031,
                    "The two objects you selected already have two points as their intersections"),
                    gxInstance.getLanguage(1032, "intersection already defined"), JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (line.getPtsSize() < 2 || c.points.size() == 0) {
            CPoint pt = this.CreateANewPoint(0, 0);
            constraint cs1 = new constraint(constraint.PONCIRCLE, pt, c);
            this.AddPointToLine(pt, line, false);
            if (m)
                pt.setXY(x, y);
            this.charsetAndAddPoly(true);
            if (m || mulSolutionSelect(pt)) {
                this.addConstraintToList(cs1);
                c.addPoint(pt);
                this.addPointToList(pt);
                this.UndoAdded(pt.m_name + ": intersection of " +
                        line.getDiscription() + " and " +
                        c.getDescription());
            } else {
                this.ErasedADecidedPoint(pt);
                line.points.remove(pt);
                gxInstance.setTipText("Line " + line.m_name + "  and Circle " +
                        c.m_name + "  can not intersect");
            }
            return pt;
        }


        CPoint pout = this.CreateANewPoint(0, 0);
        this.addPointToList(pout);
        constraint css = new constraint(constraint.INTER_LC, pout, line, c);
        this.charsetAndAddPoly(false);
        if (m)
            pout.setXY(x, y);

//        constraint cs1 = new constraint(constraint.PONLINE, pout, line, false);
//        constraint cs2 = new constraint(constraint.PONCIRCLE, pout, c, false);
        line.addApoint(pout);
        c.addPoint(pout);
        this.addPointToList(pout);
        this.addConstraintToList(css);
//        this.addConstraintToList(cs1);
//        this.addConstraintToList(cs2);

        this.UndoAdded(pout.m_name + ": intersection of " + line.getDiscription() +
                " and " + c.getDescription());
        this.reCalculate();
        return pout;
    }

    public CPoint MeetCCToDefineAPoint(Circle c1, Circle c2, boolean m, double x, double y) {
        CPoint p = null;
        CPoint p1 = null;


        if (!check_cc_inter(c1, c2)) {
            JOptionPane.showMessageDialog(gxInstance, gxInstance.getLanguage(1033, "The circles you selected don't have any intersection"),
                    gxInstance.getLanguage(1029, "No intersection"),
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        for (int i = 0; i < c1.points.size(); i++) {
            Object obj = c1.points.get(i);
            if (c2.p_on_circle((CPoint) obj)) {
                if (p == null) {
                    p = (CPoint) obj;
                } else {
                    p1 = (CPoint) obj;
                }
            }
        }
        if (p1 != null && p != null) {
            JOptionPane.showMessageDialog(gxInstance, gxInstance.getLanguage(1034, "The two circles you selected already have two points as their intersections"),
                    gxInstance.getLanguage(1032, "intersection already defined"), JOptionPane.WARNING_MESSAGE);
            return null;
        }

        if (p == null) {
            CPoint pt = this.CreateANewPoint(0, 0);
//            constraint cs = new constraint(constraint.PONCIRCLE, pt, c1);
//            constraint cs1 = new constraint(constraint.PONCIRCLE, pt, c2);
            constraint cs = new constraint(constraint.INTER_CC, pt, c1, c2);
            if (m)
                pt.setXY(x, y);
            this.charsetAndAddPoly(true);
            if (m || mulSolutionSelect(pt)) {
                this.addConstraintToList(cs);
//                this.addConstraintToList(cs1);
                c1.addPoint(pt);
                c2.addPoint(pt);
                this.addPointToList(pt);
                this.UndoAdded(pt.m_name + ": intersection of " +
                        c1.getDescription() + " and " +
                        c2.getDescription());
            } else {
                this.ErasedADecidedPoint(pt);
                gxInstance.setTipText("Circle " + c1.m_name + "  and Circle " +
                        c2.m_name + "  can not intersect");
            }

            return pt;
        }
        CPoint pt = this.CreateANewPoint(0, 0);
//        constraint cs = new constraint(constraint.INTER_CC1, pt, p, c1, c2);

//        constraint cs2 = new constraint(constraint.PONCIRCLE, pt, c1, false);
//        constraint cs3 = new constraint(constraint.PONCIRCLE, pt, c2, false);
        constraint cs = new constraint(constraint.INTER_CC, pt, c1, c2);

        CPoint pu = this.addADecidedPointWithUnite(pt);
        if (pu == null) {
            this.addConstraintToList(cs);
//            this.addConstraintToList(cs2);
//            this.addConstraintToList(cs3);
            this.addPointToList(pt);
            c1.addPoint(pt);
            c2.addPoint(pt);
            this.charsetAndAddPoly(false);
            this.reCalculate();
            this.UndoAdded(pt.m_name + ": intersection of " + c1.getDescription() +
                    " and " + c2.getDescription());
        } else {
            resetUndo();
        }
        return pt;
    }

    public void AddPointToLineX(CPoint p, CLine ln) {
        if (ln.containPT(p))
            return;
        ln.addApoint(p);
        constraint cs = new constraint(constraint.PONLINE, p, ln, false);
        p.addcstoPoint(cs);
        this.addConstraintToList(cs);
    }

    public void AddPointToCircle(CPoint p, Circle c, boolean un) {
        c.addPoint(p);
        constraint cs = new constraint(constraint.PONCIRCLE, p, c);
        this.addConstraintToList(cs);
        p.addcstoPoint(cs);
        if (un) this.UndoAdded(p.TypeString() + " on " + c.getDescription());
    }

    public void AddPointToCircle(CPoint p, Circle c) {
        AddPointToCircle(p, c, true);
    }

    public void AddPointToLine(CPoint p, CLine line) {
        AddPointToLine(p, line, true);
    }

    public void AddPointToLine(CPoint p, CLine line, boolean un) {

        if (line.containPT(p)) return;

        line.addApoint(p);
        if (line.points.size() > 2 || line.type == CLine.CCLine) {
            constraint cs = new constraint(constraint.PONLINE, p, line);
            this.addConstraintToList(cs);
            line.addconstraint(cs);
            p.addcstoPoint(cs);
            if (un) {
                this.UndoAdded(p.getDescription());
            }

        } else {
            switch (line.type) {
                case CLine.PLine: {
                    constraint cs = null;
                    cs = new constraint(constraint.PONLINE, p, line, false);
                    this.addConstraintToList(cs);

                    constraint cs1 = line.getconsByType(constraint.PARALLEL);
                    if (cs1 == null)
                        break;
                    cs1.PolyGenerate();
                    p.addcstoPoint(cs);
                    if (un)
                        this.UndoAdded(p.getDescription());
                }
                break;
                case CLine.TLine: {
                    constraint cs = new constraint(constraint.PONLINE, p, line, false);
                    this.addConstraintToList(cs);

                    constraint cs1 = line.getconsByType(constraint.PERPENDICULAR);
                    if (cs1 == null)
                        break;
                    cs1.PolyGenerate();
                    p.addcstoPoint(cs);
                    if (un)
                        this.UndoAdded(p.getDescription());
                }
                break;
                case CLine.BLine: {
                    constraint cs = null;
                    cs = new constraint(constraint.PONLINE, p, line, false);
                    this.addConstraintToList(cs);

                    constraint cs1 = line.getconsByType(constraint.BLINE);
                    if (cs1 == null) break;
                    cs1.PolyGenerate();
                    if (un) {
                        this.UndoAdded(p.getDescription());
                    }
                }
                break;
                case CLine.ALine: {
                    constraint cs = new constraint(constraint.PONLINE, p, line, false);
                    this.addConstraintToList(cs);

                    constraint cs1 = line.getconsByType(constraint.ALINE);
                    if (cs1 == null)
                        break;
                    cs1.setPolyGenerate(true);
                    cs1.PolyGenerate();
                    if (un)
                        this.UndoAdded(p.getDescription());
                    break;
                }
                case CLine.NTALine: {
                    constraint cs1 = line.getconsByType(constraint.NTANGLE);
                    if (cs1 == null)
                        break;
                    Vector v = cs1.getAllElements();
                    v.add(p);
                    constraint cs = new constraint(constraint.NTANGLE, v);
                    cs.PolyGenerate();
                    this.addConstraintToList(cs);
                    if (un) {
                        this.UndoAdded(p.getDescription());
                    }
                    break;
                }
                case CLine.SALine: {
                    constraint cs = new constraint(constraint.PONLINE, p, line, false);
                    this.addConstraintToList(cs);

                    constraint cs1 = line.getconsByType(constraint.SANGLE);
                    if (cs1 == null)
                        break;
                    line.addApoint(p);
                    cs1.PolyGenerate();
                    this.addConstraintToList(cs1);
                    if (un)
                        this.UndoAdded(p.getDescription());
                }
                break;
                case CLine.ABLine: {
                    constraint cs1 = new constraint(constraint.PONLINE, p, line, false);
                    this.addConstraintToList(cs1);

                    constraint cs = line.getcons(0);
                    if (cs == null)
                        break;
                    line.addApoint(p);
                    cs.PolyGenerate();
                    p.addcstoPoint(cs);
                    this.addConstraintToList(cs);
                    if (un)
                        this.UndoAdded(p.getDescription());
                }
                break;
                case CLine.TCLine: {
                    constraint cs1 = new constraint(constraint.PONLINE, p, line, false);
                    this.addConstraintToList(cs1);

                    constraint cs = line.getcons(0);
                    if (cs == null)
                        break;
                    line.addApoint(p);
                    cs.PolyGenerate();
                    p.addcstoPoint(cs);
                    this.addConstraintToList(cs);
                    if (un)
                        this.UndoAdded(p.getDescription());
                }
                break;

                default:
                    break;
            }
        }

    }

    public void GeneratePoly(constraint cs) {
        if (cs == null) {
            return;
        }
        cs.PolyGenerate();
        TPoly pl = constraint.getPolyListAndSetNull();
        TPoly tp = pl;
        while (tp.getNext() != null) {
            tp = tp.getNext();
        }
        tp.setNext(polylist);
        polylist = pl;
    }

    public CPoint addADecidedPointWithUnite(CPoint p) {
        boolean r = pointlist.add(p);
        this.charsetAndAddPoly(false);
        if (r)
            pointlist.remove(pointlist.size() - 1);

        CPoint tp = this.CheckCommonPoint(p);

        if (tp == null)
            return null;
        else this.SetVarable();
        eraseAPoly(p.x1.m);
        eraseAPoly(p.y1.m);
        CMisc.showMessage("Point " + tp.m_name + " already exists");
        return tp;

    }

    public void eraseAPoly(TMono m) {
        TPoly t1 = null;
        TPoly t = polylist;
        while (t != null) {
            if (t.poly == m) {
                if (t1 == null)
                    polylist = polylist.next;
                else
                    t1.next = t.next;
            }
            t1 = t;
            t = t.getNext();
        }

    }

    public CPoint CheckCommonPoint(CPoint p) { //gometry coincident.

        TPoly plist = polylist;
        while (plist != null) {
            TMono mm = plist.getPoly();
            int v = poly.lv(mm);
            if (p.x1.xindex == v) {
                p.x1.m = mm;
            } else if (p.y1.xindex == v) {
                p.y1.m = mm;
            }
            plist = plist.getNext();
        }

        if (p.x1.m == null || p.y1.m == null) {
            return null;
        }

        for (int i = 0; i < pointlist.size(); i++) {
            CPoint t = (CPoint) pointlist.get(i);
            if (t == p) {
                continue;
            }
            if (p.isSame_Location(t.getx(), t.gety()) && this.decide_wu_identical(p, t)) {
                return t;
            }
        }
        return null;
    }

    public CPoint isPointAlreadyExists(CPoint p) { // phisical coincident
        for (int i = 0; i < pointlist.size(); i++) {
            CPoint t = (CPoint) pointlist.get(i);
            if (t == p) {
                continue;
            }
            if (p.isSame_Location(t.getx(), t.gety())) {
                return t;
            }
        }
        return null;
    }

    public void SetVarable() {
        TPoly plist = polylist;
        while (plist != null) {
            int v = poly.lv(plist.getPoly());
            for (int i = 1; i < this.paraCounter; i++) {
                if ((parameter[i] != null && parameter[i].xindex == v)) {
                    parameter[i].Solved = true;
                    parameter[i].m = plist.getPoly();
                    break;
                }
            }
            plist = plist.getNext();
        }

        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            p.setColorDefault();
        }
    }

    public TPoly add_cons_or_conclution(int type, Object obj1, Object obj2,
                                        Object obj3, Object obj4) {

        if (type == constraint.PARALLEL || type == constraint.PERPENDICULAR) {
            CPoint p1 = (CPoint) obj1;
            CPoint p2 = (CPoint) obj2;
            CPoint p3 = (CPoint) obj3;
            CPoint p4 = (CPoint) obj4;
            CLine line1, line2;
            line1 = line2 = null;
            for (int i = 0; i < linelist.size(); i++) {
                CLine ln = (CLine) linelist.get(i);
                if (ln.points.contains(p1) && ln.points.contains(p2)) {
                    if (line1 == null) {
                        line1 = ln;
                    } else {
                        line2 = ln;
                    }
                }
                if (ln.points.contains(p3) && ln.points.contains(p4)) {
                    if (line1 == null) {
                        line1 = ln;
                    } else {
                        line2 = ln;
                    }
                }
            }
            constraint cs = new constraint(type, line1, line2, null, null);
        } else {
            constraint cs = new constraint(type, obj1, obj2, obj3, obj4);
        }

        TPoly plist = constraint.getPolyListAndSetNull();
        return plist;

    }


    public CPolygon SelectAPolygon(double x, double y) {
        Vector v = new Vector();
        SelectFromAList(v, polygonlist, x, y);
        if (v.size() > 1) {
            return (CPolygon) this.popSelect(v, (int) x, (int) y);
        } else if (v.size() == 1)
            return (CPolygon) v.get(0);
        else return null;

    }

    public CClass SelectFromAList(Vector list, double x, double y) {
        if (list == null) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            CClass cc = (CClass) list.get(i);
            if (cc.select(x, y)) {
                return cc;
            }
        }
        return null;
    }

    public void NameFontSizeChange(int n) {
        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            if (t.getType() == CText.NAME_TEXT && t.getFontSize() <= 5)
                return;
        }


        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            if (t.getType() == CText.NAME_TEXT)
                t.fontsizeChange(n);
        }
        panel.repaint();
    }

    public void re_generate_all_poly() {
        polylist = pblist = null;
        Vector v = new Vector();
        v.addAll(constraintlist);
        constraintlist.clear();
        GeoPoly.clearZeroN();
        this.optmizePolynomial();
        for (int i = 0; i < v.size(); i++) {
            constraint cs = (constraint) v.get(i);
            if (cs.is_poly_genereate) {
                cs.clear_all_cons();
                cs.PolyGenerate();
                charsetAndAddPoly(true);
            }
            this.addConstraintToList(cs);
        }
    }

    public DataOutputStream openOutputFile(String path) throws IOException {
        FileOutputStream fp;
        File f = new File(path);

        if (f.exists()) {
            f.delete();
            fp = new FileOutputStream(f, true);

        } else {
            f.createNewFile();
            fp = new FileOutputStream(f, false);
        }
        if (fp == null) {
            return null;
        }
        DataOutputStream out = new DataOutputStream(fp);
        return out;
    }

    boolean Save(String name) throws IOException {
        boolean r = Save(openOutputFile(name));
        file = new File(name);
        needSave = false;
        return r;
    }

    boolean Save(DataOutputStream out) throws IOException {
        if (cpfield != null) {
            cpfield.run_to_end(this);
        }

        String title = "GE";
        out.write(title.getBytes(), 0, title.length());
        out.writeDouble(CMisc.version);

        Save_global(out);

        out.writeInt(CMisc.id_count);
        out.writeInt(GridX);
        out.writeInt(GridY);
        out.writeBoolean(DRAWGRID);
        out.writeBoolean(SNAP);

        out.writeInt(this.CurrentAction);

        out.writeInt(pnameCounter);
        out.writeInt(plineCounter);
        out.writeInt(pcircleCounter);

        out.writeInt(this.paraCounter);

        for (int i = 0; i < this.paraCounter - 1; i++) {
            parameter[i].Save(out);
        }

        for (int i = 0; i < this.paraCounter - 1; i++) {
            out.writeDouble(paraBackup[i]);
        }

        int size;

        size = constraintlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            constraint cs = (constraint) constraintlist.get(i);
            out.writeInt(cs.id);
        }

        size = pointlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CPoint p = (CPoint) pointlist.get(i);
            p.Save(out);
        }

        size = linelist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CLine ln = (CLine) linelist.get(i);
            ln.Save(out);
        }
        size = circlelist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            Circle c = (Circle) circlelist.get(i);
            c.Save(out);
        }

        size = anglelist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CAngle ag = (CAngle) anglelist.get(i);
            ag.Save(out);
        }

        size = distancelist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CDistance dis = (CDistance) distancelist.get(i);
            dis.Save(out);
        }
        size = polygonlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CPolygon poly = (CPolygon) polygonlist.get(i);
            poly.Save(out);
        }
        size = textlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CText tx = (CText) textlist.get(i);
            tx.Save(out);
        }

        size = tracelist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CTrace tr = (CTrace) tracelist.get(i);
            tr.Save(out);
        }

        size = otherlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CClass tr = (CClass) otherlist.get(i);
            out.writeInt(tr.m_type);
            tr.Save(out);
        }

        size = constraintlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            constraint cs = (constraint) constraintlist.get(i);
            cs.Save(out);
        }

        size = undolist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            UndoStruct undo = (UndoStruct) undolist.get(i);
            undo.Save(out);
        }

        currentUndo.Save(out);
//        if (trackPoint != null) {
//            out.writeInt(trackPoint.m_id);
//        } else {
        out.writeInt(-1);
//        }

        if (this.animate != null && gxInstance.getAnimateDialog().isVisible()) {
            out.writeBoolean(true);
            this.animate.Save(out);
        } else {
            out.writeBoolean(false);
        }

        if (cpfield == null) {
            out.writeBoolean(false);
        } else {
            out.writeBoolean(true);
            cpfield.Save(out);
        }
        setSavedTag();
        return true;
    }

    DataInputStream openInputFile(String path) throws IOException {
        File f = new File(path);
        FileInputStream fp;
        if (f.exists()) {
            fp = new FileInputStream(f);
        } else {
            return null;
        }
        if (f == null) {
            return null;
        }
        file = f;
        DataInputStream in = new DataInputStream(fp);
        return in;
    }

    boolean Load(String name) throws IOException {
        File f = new File(name);
        return Load(f);
    }

    boolean Load(File f) throws IOException {
        FileInputStream fp;
        if (f.exists()) {
            fp = new FileInputStream(f);
        } else {
            return false;
        }

        if (f == null) {
            return false;
        }

        DataInputStream in = new DataInputStream(fp);
        boolean n = Load(in);
        in.close();
        return n;
    }

    boolean Load(DataInputStream in) throws IOException {
        byte[] tl = new byte[2];
        in.read(tl, 0, tl.length);
        String title = new String(tl);
        if (title.compareTo("GE") != 0) {
            return false;
        }

        double version = in.readDouble();
        CMisc.version_load_now = version;
        if (version < 0.006) {
            CMisc.eprint(panel, "Error version" + version);
            return false;
        }
        Load_global(in);

        int idcount = CMisc.id_count = in.readInt();
        poly.clearZeroN();


        GridX = in.readInt();
        GridY = in.readInt();
        DRAWGRID = in.readBoolean();
        SNAP = in.readBoolean();
        CurrentAction = in.readInt();
        pnameCounter = in.readInt();
        plineCounter = in.readInt();
        pcircleCounter = in.readInt();
        paraCounter = in.readInt();

        for (int i = 0; i < this.paraCounter - 1; i++) {
            param pm = new param();
            pm.Load(in);
            this.parameter[i] = pm;
        }

        for (int i = 0; i < this.paraCounter - 1; i++) {
            paraBackup[i] = in.readDouble();
        }

        int size;

        if (CMisc.version_load_now < 0.01) {
            size = in.readInt();
            int trackCounter = size;
            if (CMisc.version_load_now >= 0.008) {
                for (int i = 0; i < 2 * trackCounter; i++) {
                    in.readInt();
                }
            } else {
                for (int i = 0; i < trackCounter; i++) {
                    in.readInt();
                }
            }
        } else if (CMisc.version_load_now < 0.012) {
            size = in.readInt();
            for (int i = 0; i < size; i++) {
                CTrace ct = new CTrace(null);
                ct.Load(in, this);
                tracelist.add(ct);
            }
        }

        size = in.readInt();
        for (int i = 0; i < size; i++) {
            int d = in.readInt();
            addConstraintToList(new constraint(d));
        }

        size = in.readInt();
        for (int i = 0; i < size; i++) {
            CPoint p = new CPoint();
            p.Load(in, this);
            pointlist.add(p);
        }
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            CLine ln = new CLine(0);
            ln.Load(in, this);
            linelist.add(ln);
        }
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            Circle c = new Circle();
            c.Load(in, this);
            circlelist.add(c);
        }
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            CAngle ag = new CAngle();
            ag.Load(in, this);
            anglelist.add(ag);
        }
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            CDistance dis = new CDistance();
            dis.Load(in, this);
            distancelist.add(dis);
        }
        size = in.readInt();
        for (int i = 0; i < size; i++) {

            CPolygon poly = new CPolygon();
            poly.Load(in, this);
            addPolygonToList(poly);
        }
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            CText ct = new CText();
            ct.Load(in, this);
            textlist.add(ct);
        }

        if (CMisc.version_load_now >= 0.012) {
            size = in.readInt();
            for (int i = 0; i < size; i++) {
                CTrace ct = new CTrace(null);
                ct.Load(in, this);
                tracelist.add(ct);
            }
        }

        if (CMisc.version_load_now >= 0.017) {
            size = in.readInt();
            if (CMisc.version_load_now <= 0.040)
                for (int i = 0; i < size; i++) {
                    Cedmark ce = new Cedmark();
                    ce.Load(in, this);
                    otherlist.add(ce);
                }
            else {
                for (int i = 0; i < size; i++) {
                    int t = in.readInt();
                    switch (t) {

                        case CClass.TMARK: {
                            CTMark mt = new CTMark();
                            mt.Load(in, this);
                            otherlist.add(mt);
                        }
                        break;
                        case CClass.ARROW: {
                            CArrow ar = new CArrow(null, null);
                            ar.Load(in, this);
                            otherlist.add(ar);
                            break;
                        }
                        case CClass.EQMARK:
                        case 0: {
                            Cedmark ce = new Cedmark();
                            ce.Load(in, this);
                            otherlist.add(ce);
                        }
                        break;
                        default:
                            CMisc.eprint(panel, "Mark unidentified!");
                            break;
                    }
                }
            }

        }


        this.optmizePolynomial();

        size = in.readInt();
        for (int i = 0; i < size; i++) {
            constraint cs = (constraint) constraintlist.get(i);
            cs.Load(in, this);
            if (cs.is_poly_genereate) {
                cs.PolyGenerate();
                this.charsetAndAddPoly(true);
            }

        }

//        for (int i = 0; i < size; i++) {
//            constraint cs = (constraint) constraintlist.get(i);
//
//        }

        size = in.readInt();
        for (int i = 0; i < size; i++) {
            UndoStruct ud = new UndoStruct(0);
            ud.Load(in, this);
            undolist.add(ud);
            if (ud.m_type == UndoStruct.T_TO_PROVE_NODE) {
                drawData.setProveStatus();
            }
        }

        currentUndo = new UndoStruct(0);
        currentUndo.Load(in, this);

        if (version >= 0.006) {
            int ti = in.readInt();
        }

        if (CMisc.version_load_now >= 0.009) { //version 0.009 special for web saver.
            boolean isrun = in.readBoolean();
            if (isrun) {
                this.animate = new AnimateC();
                this.animate.Load(in, this);
                if (gxInstance != null) {
                    {
                        gxInstance.anButton.setEnabled(true);
                        gxInstance.getAnimateDialog().setAttribute(animate);
                        gxInstance.showAnimatePane();
                    }
                }
            } else {
                if (gxInstance != null) {
                    {
                        gxInstance.anButton.setEnabled(false);
                    }
                }
            }
        }

        if (CMisc.version_load_now >= 0.017) {
            boolean havep = in.readBoolean();
            if (havep) {
                cpfield = new CProveField();
                cpfield.Load(in, this);
//                if (gxInstance != null) {
//                    gxInstance.showProveBar(true);
//                }
            }
        }
        CMisc.id_count = idcount;
        CurrentAction = MOVE;
        currentUndo.id = idcount;

        setSavedTag();
        return true;

    }

    public void Save_global(DataOutputStream out) throws IOException {
        drawData.Save(out);
        int index = UndoStruct.INDEX;
        out.writeInt(index);
        CMisc.Save(out);
    }

    public void Load_global(DataInputStream in) throws IOException {
        if (CMisc.version_load_now < 0.010) {
            int size = in.readInt();
            if (size > 100000) {
                return;
            }
            byte[] s = new byte[size];
            in.read(s, 0, size);
        }
        drawData.Load(in, this);
        if (CMisc.version_load_now >= 0.030) {
            UndoStruct.INDEX = in.readInt();
        }

        if (CMisc.version_load_now >= 0.040)
            CMisc.Load(in);

        footMarkShown = CMisc.isFootMarkShown(); // APPLET ONLY.
        footMarkLength = CMisc.FOOT_MARK_LENGTH;
    }

    boolean write_ps(String name, int stype, boolean ptf, boolean pts) throws
            IOException { // 0: color  1: gray ; 2: black and white

        FileOutputStream fp;
        File f = new File(name);
        if (f.exists()) {
            f.delete();
            fp = new FileOutputStream(f, true);
        } else {
            f.createNewFile();
            fp = new FileOutputStream(f, false);
        }
        if (fp == null) {
            return false;
        }

//        int y, m, d;
        Calendar c = Calendar.getInstance();
        String stime = "%Create Time: " + c.getTime().toString() + "\n";
        String sversion = "%Created By: " + Version.getNameAndVersion() +
                "\n";

        String s = "%!PS-Adobe-2.0\n" + stime + sversion + "\n" +
                "%%BoundingBox: 0 500 400 650\n" +
                "0.7 setlinewidth\n" +
                "gsave\n20 700 translate\n.5 .5 scale\n" +
                "/dash {[4 6] 0 setdash stroke [] 0 setdash} def\n" +
                "/cir {0 360 arc} def\n" +
                "/cirfill {0 360 arc 1.0 1.0 1.0 setrgbcolor [] 0 setdash} def\n" +
                "/arcfill{arc 1.0 1.0 1.0 setrgbcolor [] 0 setdash} def\n" +
                "/rm {moveto 4 4 rmoveto} def\n" +
                "/circle {0 360 arc} def\n" +
                "/black {0.0 0.0 0.0 setrgbcolor} def\n" +
                "/mf {/Times-Roman findfont 15.71 scalefont setfont 0.0 0.0 0.0 setrgbcolor} def\n" +
                "/nf {/Times-Roman findfont 11.00 scalefont setfont 0.0 0.0 0.0 setrgbcolor} def\n\n";

        fp.write(s.getBytes());
        this.SaveDrawAttr(fp, stype);

        fp.write("%define points\n".getBytes());

        for (int i = 0; i < pointlist.size(); i++) {
            CPoint p = (CPoint) pointlist.get(i);
            p.SavePS_Define_Point(fp);
        }

        this.write_list_ps(fp, polygonlist, "%-----draw polygons\n", stype);
        this.write_list_ps(fp, anglelist, "%-----draw angles\n", stype);
        this.write_list_ps(fp, distancelist, "%-----draw measures\n", stype);
        this.write_list_ps(fp, otherlist, "%-----draw marks and other\n", stype);
        this.write_list_ps(fp, tracelist, "%-----draw trace list\n", stype);

        write_perp_foot(fp, stype);
        this.write_list_ps(fp, linelist, "%-----draw lines\n", stype);
        this.write_list_ps(fp, circlelist, "%-----draw circles\n", stype);
        if (stype == 0 && ptf) {
            for (int i = 0; i < pointlist.size(); i++) {
                CPoint pt = (CPoint) pointlist.get(i);
                pt.SavePsOringinal(fp);
            }
        } else {
            this.write_list_ps(fp, pointlist, "%-----draw points\n", stype);
        }

        this.write_list_ps(fp, textlist, "%-----draw texts\n", stype);

        if (cpfield != null && pts) {
            cpfield.SavePS(fp, stype);
        }
        s = "grestore\nshowpage\n";
        fp.write(s.getBytes());
        fp.close();
        return true;
    }

    void write_perp_foot(FileOutputStream fp, int stype) throws IOException {
        Vector vlist = new Vector();
        this.drawPerpFoot(null, vlist, 1);
        if (vlist.size() == 0) {
            return;
        }

        fp.write("%----draw foot\n".getBytes());
        String s = "[] 0 setdash ";
        if (stype == 0) {
            s += "0.5 setlinewidth 1.0 0.0 0.0 setrgbcolor \n";
        } else {
            s += "0.5 setlinewidth 0.0 0.0 0.0 setrgbcolor \n";
        }
        fp.write(s.getBytes());
        for (int i = 0; i < vlist.size() / 2; i++) {
            Point p1 = (Point) vlist.get(2 * i);
            Point p2 = (Point) vlist.get(2 * i + 1);
            String st = p1.getX() + " " + (-p1.getY()) + " moveto " + p2.getX() +
                    " " + (-p2.getY()) + " lineto ";
            if (i % 2 == 0) {
                st += "\n";
            }
            fp.write(st.getBytes());
        }
        fp.write("stroke \n".getBytes());
    }

    void write_list_ps(FileOutputStream fp, Vector vlist, String discription,
                       int stype) throws IOException {
        if (vlist.size() != 0) {
            fp.write((discription).getBytes());
        }
        for (int i = 0; i < vlist.size(); i++) {
            CClass p = (CClass) vlist.get(i);
            p.SavePS(fp, stype);
        }
    }

    private void SaveDrawAttr(FileOutputStream fp, int stype) throws
            IOException {
        Vector vc = new Vector();
        Vector vd = new Vector();
        Vector vw = new Vector();

        getUDAFromList(vc, vd, vw, pointlist);
        getUDAFromList(vc, vd, vw, linelist);
        getUDAFromList(vc, vd, vw, circlelist);
        getUDAFromList(vc, vd, vw, anglelist);
        getUDAFromList(vc, vd, vw, distancelist);
        getUDAFromList(vc, vd, vw, polygonlist);
        getUDAFromList(vc, vd, vw, textlist);
        getUDAFromList(vc, vd, vw, tracelist);
        getUDAFromList(vc, vd, vw, otherlist);
        for (int i = 0; i < anglelist.size(); i++) {
            CAngle ag = (CAngle) anglelist.get(i);
            if (ag.getAngleType() == 3)
                addAttrToList(ag.getValue1(), vc);
        }
        drawData.SavePS(vc, vd, vw, fp, stype);
    }

    private void getUDAFromList(Vector vc, Vector vd, Vector vw, Vector vlist) {
        for (int i = 0; i < vlist.size(); i++) {
            CClass cc = (CClass) vlist.get(i);
            addAttrToList(cc.m_color, vc);
            addAttrToList(cc.m_dash, vd);
            addAttrToList(cc.m_width, vw);
        }
    }

    private void addAttrToList(int atrr, Vector v) {
        int i = 0;

        for (; i < v.size(); i++) {
            Integer In = (Integer) v.get(i);
            if (In.intValue() == atrr) {
                return;
            } else if (In.intValue() > atrr) {
                v.add(i, new Integer(atrr));
                return;
            }
        }
        v.add(i, new Integer(atrr));

    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws
            PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) graphics;

        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        double w = pageFormat.getWidth() / Width;
        double h = pageFormat.getHeight() / Height;

        if (w > h) {
            g2.scale(h, h);
        } else {
            g2.scale(w, w);
        }

        this.paintPoint(graphics);
        return 0;
    }

    public void PrintContent() {
        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat landscape = job.defaultPage();
        landscape.setOrientation(PageFormat.LANDSCAPE);

        PageFormat pf = new PageFormat();
        Paper paper = new Paper();
        double margin = 36; // half inch
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2,
                paper.getHeight() - margin * 2);

        pf.setOrientation(PageFormat.LANDSCAPE);
        pf.setPaper(paper);
        job.setPrintable(this, pf);


        if (job.printDialog()) {
            try {
                job.print();
            } catch (Exception exc) {
                CMisc.print("Print Error. ------ " + exc.toString());
            }
        }
    }


    public void addText(CText tx) {
        if (tx != null) {
            if (this.addObjectToList(tx, textlist)) {
                this.UndoAdded(tx.TypeString());
            }
        }
    }

    public UndoStruct addProveToList() {
        UndoStruct undo = new UndoStruct(UndoStruct.T_PROVE_NODE, 0);
        this.undolist.add(undo);
        return undo;
    }

    public UndoStruct addToProveToList() {
        UndoStruct undo = new UndoStruct(UndoStruct.T_TO_PROVE_NODE, 0);
        undo.msg = "";
        this.undolist.add(undo);
        return undo;
    }

    public void addNodeToUndoList(UndoStruct un) {
        this.undolist.add(un);

//        if (undolist.size() == 0) {
//            if (gxInstance.getUndoEditDialog().isVisible()) {
//            } else {
//            }
//        } else {
//            if (gxInstance != null && !gxInstance.getUndoEditDialog().isVisible()) {
//                UndoStruct u = (UndoStruct) undolist.get(undolist.size() - 1);
//                if (u.m_type == UndoStruct.T_UNDO_NODE) {
//                    undolist.add(un);
//                } else {
//                    u.childundolist.add(un);
//                }
//            } else {
//            }
//        }
    }


    public UndoStruct UndoAdded(Object tip) {
        return UndoAdded(tip, true);

    }

    public UndoStruct UndoAdded(Object tip, boolean gr) {
        return UndoAdded(tip, gr, true);
    }

    public UndoStruct UndoAdded(Object tip, boolean gr, boolean m) {

        if (CMisc.id_count == this.currentUndo.id) {
            return null;
        }


        String message = tip.toString();
        if (message.length() != 0) {
            char c = message.charAt(0);
            if (c >= 'a' && c <= 'z') {
                c += 'A' - 'a';
                message = c + message.substring(1);
            }
        } // upper case first char of the mesage.

        this.redolist.clear();
        UndoStruct Undo = this.currentUndo;
        Undo.action = this.CurrentAction;
        if (message != null && message.length() >= 1) {

            char c = message.charAt(0);
            if (c >= 'a' && c <= 'z') {
                c = (char) (c + 'A' - 'a');
                message = c + message.substring(1);
            }

            Undo.msg = message;
        }
        Undo.id_b = CMisc.id_count;
        Undo.paraCounter_b = this.paraCounter;
        Undo.pnameCounter_b = this.pnameCounter;
        Undo.plineCounter_b = this.plineCounter;
        Undo.pcircleCounter_b = this.pcircleCounter;

        this.addNodeToUndoList(Undo);
        this.currentUndo = new UndoStruct(this.paraCounter);
        currentUndo.pnameCounter = this.pnameCounter;
        currentUndo.plineCounter = this.plineCounter;
        currentUndo.pcircleCounter = this.pcircleCounter;

        if (gxInstance != null) {
            if (m && gxInstance.hasMannualInputBar() && gxInstance.getProveStatus() == 4) {
                mproveInputPanel input = gxInstance.getMannalInputToolBar();
                if (input != null)
                    input.addUndo(Undo, message);
            }
            if (gr)
                gxInstance.getpprove().generate();
            gxInstance.reloadLP();
            gxInstance.setBKState();
        }


        return Undo;

    }


    public void UndoAdded() {
        UndoAdded("Not yet added ");
    }

    public void UndoAdded(CClass cc) {
        UndoAdded(cc.TypeString() + ":  " + cc.getDescription());
    }


    public void UndoPure() {
        this.doFlash();
        while (true) {
            if (false == Undo_stepPure()) {
                this.re_generate_all_poly();
                this.doFlash();
                return;
            }
        }
    }

    public void Undo() {
        while (true) {
            if (!Undo_step()) {
                return;
            }
        }
    }

    public void redo() {
        while (true) {
            if (null == redo_step()) {
                return;
            }
        }
    }

    public boolean undo_step(UndoStruct Undo) {
        return undo_step(Undo, true);
    }

    public boolean undo_step(UndoStruct Undo, boolean rg) {

        if (Undo.m_type == UndoStruct.T_COMBINED_NODE ||
                Undo.m_type == UndoStruct.T_PROVE_NODE) {
            for (int i = 0; i < Undo.childundolist.size(); i++) {
                UndoStruct u = (UndoStruct) Undo.childundolist.get(i);
                this.undo_step(u);
            }
            return true;
        }

        int pc = Undo.id;
        int pcb = Undo.id_b;
        int para = Undo.paraCounter;
        int parab = Undo.paraCounter_b;
        Undo.clear();
//        TPoly pl, tp;
//        pl = this.polylist;
//        tp = null;
//        while (pl != null) {
//            TMono m = pl.getPoly();
//            int tid = poly.lv(m);
//
//            if (tid >= para && tid < parab) {
//                if (tp == null) {
//                    this.polylist = null;
//                } else {
//                    tp.setNext(null);
//                }
//                Undo.polylist = pl;
//                break;
//            }
//            tp = pl;
//            pl = pl.getNext();
//        }
//        pl = pblist;
//        tp = null;
//
//        while (pl != null) {
//            TMono m = pl.getPoly();
//            int tid = poly.lv(m);
//
//            if (tid >= para && tid < parab) {
//                if (tp == null) {
//                    this.pblist = null;
//                } else {
//                    tp.setNext(null);
//                }
//                Undo.pblist = pl;
//                break;
//            }
//            tp = pl;
//            pl = pl.getNext();
//        }

        moveUndoObjectFromList(Undo.pointlist, pointlist, pc, pcb);
        moveUndoObjectFromList(Undo.linelist, linelist, pc, pcb);
        moveUndoObjectFromList(Undo.circlelist, circlelist, pc, pcb);
        moveUndoObjectFromList(Undo.anglelist, anglelist, pc, pcb);
        moveUndoObjectFromList(Undo.distancelist, distancelist, pc, pcb);
        moveUndoObjectFromList(Undo.polygonlist, polygonlist, pc, pcb);
        moveUndoObjectFromList(Undo.textlist, textlist, pc, pcb);
        moveUndoObjectFromList(Undo.tracklist, tracelist, pc, pcb);
        moveUndoObjectFromList(Undo.otherlist, otherlist, pc, pcb);

        for (int i = 0; i < constraintlist.size(); i++) {
            constraint cs = (constraint) constraintlist.get(i);

            if (cs.id >= pc && cs.id < pcb) {
                Undo.constraintlist.add(cs);
                removeConstraintFromList(cs);
                i--;
                int type = cs.GetConstraintType();
                switch (type) {
                    case constraint.PONLINE: {
                        CPoint p = (CPoint) cs.getelement(0);
                        CLine ln = (CLine) cs.getelement(1);
                        ln.points.remove(p);
                    }
                    break;
                    case constraint.PONCIRCLE: {
                        CPoint p = (CPoint) cs.getelement(0);
                        Circle c = (Circle) cs.getelement(1);
                        c.points.remove(p);
                    }
                    break;
                    case constraint.VISIBLE: {
                        CClass cc = (CClass) cs.getelement(0);
                        cc.setVisible(false);
                    }
                    break;
                    case constraint.INVISIBLE: {
                        CClass cc = (CClass) cs.getelement(0);
                        cc.setVisible(true);
                    }
                    break;
                    case constraint.INTER_LL: {
                        CPoint p = (CPoint) cs.getelement(0);
                        CLine ln1 = (CLine) cs.getelement(1);
                        CLine ln2 = (CLine) cs.getelement(2);
                        ln1.points.remove(p);
                        ln2.points.remove(p);
                    }
                    break;
                    case constraint.INTER_LC: {
                        CPoint p = (CPoint) cs.getelement(0);
                        CLine ln1 = (CLine) cs.getelement(1);
                        Circle c2 = (Circle) cs.getelement(2);
                        ln1.points.remove(p);
                        c2.points.remove(p);
                    }
                    break;
                    case constraint.INTER_CC: {
                        CPoint p = (CPoint) cs.getelement(0);
                        Circle c1 = (Circle) cs.getelement(1);
                        Circle c2 = (Circle) cs.getelement(2);
                        c1.points.remove(p);
                        c2.points.remove(p);
                    }
                    break;
                    case constraint.EQUIVALENCE1:
                    case constraint.EQUIVALENCE2:
                    case constraint.TRANSFORM:
                    case constraint.TRANSFORM1: {
                        CPolygon p1 = (CPolygon) cs.getelement(0);
                        CPolygon p2 = (CPolygon) cs.getelement(1);
                        p1.setVisible(true);

                    }
                    break;

                }
            }
        }
        if (rg) {
            re_generate_all_poly();
            this.reCalculate();
        }
        this.currentUndo.id = Undo.id;
        this.currentUndo.msg = Undo.msg;
        this.currentUndo.paraCounter = Undo.paraCounter;
        this.currentUndo.pnameCounter = Undo.pnameCounter;
        this.currentUndo.plineCounter = Undo.plineCounter;
        this.currentUndo.pcircleCounter = Undo.pcircleCounter;

        CMisc.id_count = Undo.id;
        this.paraCounter = Undo.paraCounter;
        this.pnameCounter = Undo.pnameCounter;
        this.plineCounter = Undo.plineCounter;
        this.pcircleCounter = Undo.pcircleCounter;
        return true;
    }

    public boolean isRedoAtEnd() {
        return redolist.size() == 0;
    }


    public boolean Undo_stepPure() {
        this.undo = null;
        if (CMisc.id_count != this.currentUndo.id) {
            UndoAdded();
        }
        int size = this.undolist.size();
        if (size == 0) {
            return false;
        }
        clearSelection();
        CatchList.clear();
        UndoStruct Undo = (UndoStruct) this.undolist.get(size - 1);
        this.undolist.remove(Undo);
        this.undo_step(Undo, false);
        this.redolist.add(Undo);
        return true;

    }

    public boolean Undo_step() {

//        clearSelection();
//        STATUS = 0;
//        FirstPnt = SecondPnt = null;
        this.cancelCurrentAction();


        this.undo = null;
        if (CMisc.id_count != this.currentUndo.id) {
            UndoAdded();
        }
        int size = this.undolist.size();
        if (size == 0) {
            return false;
        }
        clearSelection();
        CatchList.clear();
        UndoStruct Undo = (UndoStruct) this.undolist.get(size - 1);
        this.undolist.remove(Undo);
        this.undo_step(Undo);
        this.redolist.add(Undo);
        this.clearFlash();

        if (gxInstance != null && gxInstance.hasMannualInputBar() && gxInstance.getProveStatus() == 4) {
            mproveInputPanel input = gxInstance.getMannalInputToolBar();
            if (input != null)
                input.undoStep(Undo);
        }

        if (gxInstance != null)
            gxInstance.getpprove().generate();
        return true;

    }

    public int getUndolistSize() {
        return undolist.size();
    }

    public int getRedolistSize() {
        return redolist.size();
    }

    public UndoStruct getNextRedoStruct() {
        if (redolist.size() == 0) {
            return null;
        }
        return (UndoStruct) redolist.get(redolist.size() - 1);
    }


    public void moveUndoObjectFromList(Vector v1, Vector v2, int pc1, int pc2) {
        for (int i = 0; i < v2.size(); i++) {
            CClass cc = (CClass) v2.get(i);
            if (cc.m_id >= pc1 && cc.m_id < pc2) {
                v1.add(cc);
                v2.remove(i);
                i--;
            }
        }
    }

    public void selectUndoObjectFromList(Vector v1, Vector v2, int pc1, int pc2) {
        for (int i = 0; i < v2.size(); i++) {
            CClass cc = (CClass) v2.get(i);
            if (cc.m_id >= pc1 && cc.m_id < pc2) {
                v1.add(cc);
            }
        }
    }


    public void setUndoStructForDisPlay(UndoStruct u, boolean compulsory_flash) {
        if (u == null)
            return;

        undo = u;
        if ((u != null && u.flash) || compulsory_flash) {
            Vector v = u.getAllObjects(this);
            JObjectFlash f = new JObjectFlash(panel);
            f.setAt(panel, v);
            this.addFlash(f);
        }
    }

    public void flash_node_by_id(int id) {
        for (int i = 0; i < undolist.size(); i++) {
            UndoStruct u = (UndoStruct) undolist.get(i);
            if (id >= u.id && id < u.id_b) {
                setUndoStructForDisPlay(u, true);
                break;
            }
        }
    }

    public JObjectFlash getObjectFlash(CClass cc) {
        JObjectFlash f = new JObjectFlash(panel);
        f.addFlashObject(cc);
        return f;
    }

    public void setObjectListForFlash(CClass cc) {
        Vector v = new Vector();
        v.add(cc);
        setObjectListForFlash(v);
    }

    public void setObjectListForFlash(Vector list, JPanel p) {

        JObjectFlash f = new JObjectFlash(panel);
        f.setAt(p, list);
        this.addFlash(f);
    }

    public void setObjectListForFlash(Vector list) {
        setObjectListForFlash(list, panel);
    }

    public void setUndoListForFlash(Vector list) {
        Vector v = new Vector();
        for (int i = 0; i < list.size(); i++) {
            UndoStruct u = (UndoStruct) list.get(i);
            v.addAll(u.getAllObjects(this));
        }
        setObjectListForFlash(v);
    }

    public void setUndoListForFlash1(Vector list) {
        Vector v = new Vector();
        for (int i = 0; i < list.size(); i++) {
            UndoStruct u = (UndoStruct) list.get(i);
            v.addAll(u.getAllObjects(this));
        }
        JObjectFlash f = new JObjectFlash(panel);
        f.setAt(panel, v);
        this.addFlash1(f);
    }

    public void stopUndoFlash() {
        this.clearFlash();
    }

    public boolean redo_till_specified_idcount(int id) {
        UndoStruct Undo;
        if (redolist.size() == 0) {
            return false;
        }
        while (true) {
            Undo = (UndoStruct) redolist.get(redolist.size() - 1);
            if (Undo.id_b <= id) {
                this.redo_step();
            } else {
                break;
            }
            if (redolist.size() == 0) {
                break;
            }
        }
        return true;
    }

    public boolean redo_step(UndoStruct Undo) {

        if (Undo.m_type == UndoStruct.T_COMBINED_NODE ||
                Undo.m_type == UndoStruct.T_PROVE_NODE) {
            for (int i = 0; i < Undo.childundolist.size(); i++) {
                UndoStruct u = (UndoStruct) Undo.childundolist.get(i);
                this.redo_step(u);
            }
            return true;
        }

        for (int i = 0; i < Undo.pointlist.size(); i++) {
            Object o = Undo.pointlist.get(i);
            this.pointlist.add(o);
        }

        for (int i = 0; i < Undo.linelist.size(); i++) {
            CLine ln = (CLine) Undo.linelist.get(i);
            this.linelist.add(ln);
        }
        for (int i = 0; i < Undo.circlelist.size(); i++) {
            Circle c = (Circle) Undo.circlelist.get(i);
            this.circlelist.add(c);
        }
        for (int i = 0; i < Undo.anglelist.size(); i++) {
            CAngle ca = (CAngle) Undo.anglelist.get(i);
            this.anglelist.add(ca);
        }
        for (int i = 0; i < Undo.constraintlist.size(); i++) {
            constraint cs = (constraint) Undo.constraintlist.get(i);
            addConstraintToList(cs);

            int type = cs.GetConstraintType();
            switch (type) {
                case constraint.PONLINE: {
                    CPoint p = (CPoint) cs.getelement(0);
                    CLine ln = (CLine) cs.getelement(1);
                    ln.addApoint(p);
                }
                break;
                case constraint.PONCIRCLE: {
                    CPoint p = (CPoint) cs.getelement(0);
                    Circle c = (Circle) cs.getelement(1);
                    c.addPoint(p);
                }
                break;
                case constraint.VISIBLE: {
                    CClass cc = (CClass) cs.getelement(0);
                    cc.setVisible(true);
                }
                break;
                case constraint.INVISIBLE: {
                    CClass cc = (CClass) cs.getelement(0);
                    cc.setVisible(false);
                }
                break;
                case constraint.INTER_LL: {
                    CPoint p = (CPoint) cs.getelement(0);
                    CLine ln1 = (CLine) cs.getelement(1);
                    CLine ln2 = (CLine) cs.getelement(2);
                    ln1.addApoint(p);
                    ln2.addApoint(p);
                }
                break;
                case constraint.INTER_LC: {
                    CPoint p = (CPoint) cs.getelement(0);
                    CLine ln1 = (CLine) cs.getelement(1);
                    Circle c2 = (Circle) cs.getelement(2);
                    ln1.addApoint(p);
                    c2.addPoint(p);
                }
                break;
                case constraint.INTER_CC: {
                    CPoint p = (CPoint) cs.getelement(0);
                    Circle c1 = (Circle) cs.getelement(1);
                    Circle c2 = (Circle) cs.getelement(2);
                    c1.addPoint(p);
                    c2.addPoint(p);
                }
                break;
                case constraint.TRANSFORM: {
                    CPolygon p1 = (CPolygon) cs.getelement(0);
                    CPolygon p2 = (CPolygon) cs.getelement(1);
                    CPoint p = (CPoint) cs.getelement(2);
                    if (p == null)
                        addFlashPolygon(p1, p2, 0, false, 0, 0);
                    else
                        addFlashPolygon(p1, p2, 0, true, p.getx(), p.gety());

                    if (cs.proportion == 1)
                        p1.setVisible(false);
                }
                break;
                case constraint.TRANSFORM1: {
                    CPolygon p1 = (CPolygon) cs.getelement(0);
                    CPolygon p2 = (CPolygon) cs.getelement(1);
                    addFlashPolygon(p1, p2, 1, false, 0, 0);
                    p1.setVisible(false);

                }
                break;
                case constraint.EQUIVALENCE1:
                case constraint.EQUIVALENCE2: {
                    CPolygon p1 = (CPolygon) cs.getelement(0);
                    CPolygon p2 = (CPolygon) cs.getelement(1);
                    CPoint p = (CPoint) cs.getelement(2);
                    if (p == null)
                        addFlashPolygon(p1, p2, 1, false, 0, 0);
                    else
                        addFlashPolygon(p1, p2, 1, true, p.getx(), p.gety());
                    p1.setVisible(false);

                }
                break;
                case constraint.TRATIO:
                case constraint.PRATIO: {
//                        CPoint p1 = (CPoint) cs.getelement(0);
//                        CPoint p2 = (CPoint) cs.getelement(1);
//                        CPoint p3 = (CPoint) cs.getelement(2);
//                        CPoint p4 = (CPoint) cs.getelement(3);
//                        JSegmentMoveingFlash f = new JSegmentMoveingFlash(panel, p1, p2, p4, p3, 0, 0);
//                        this.addFlash1(f);
                }
                break;
            }
        }
        for (int i = 0; i < Undo.distancelist.size(); i++) {
            CClass cd = (CClass) Undo.distancelist.get(i);
            this.distancelist.add(cd);

        }
        for (int i = 0; i < Undo.polygonlist.size(); i++) {
            CPolygon cp = (CPolygon) Undo.polygonlist.get(i);
            this.addPolygonToList(cp);
        }
        for (int i = 0; i < Undo.textlist.size(); i++) {
            CText ct = (CText) Undo.textlist.get(i);
            this.textlist.add(ct);
        }
        for (int i = 0; i < Undo.otherlist.size(); i++) {
            CClass ct = (CClass) Undo.otherlist.get(i);
            this.otherlist.add(ct);
        }
        this.re_generate_all_poly();
        this.reCalculate();

        this.currentUndo.id = Undo.id_b;
        this.currentUndo.paraCounter = Undo.paraCounter_b;
        this.currentUndo.pnameCounter = Undo.pnameCounter_b;
        this.currentUndo.plineCounter = Undo.plineCounter_b;
        this.currentUndo.pcircleCounter = Undo.pcircleCounter_b;

        CMisc.id_count = Undo.id_b;
        this.paraCounter = Undo.paraCounter_b;
        this.pnameCounter = Undo.pnameCounter_b;
        this.plineCounter = Undo.plineCounter_b;
        this.pcircleCounter = Undo.pcircleCounter_b;
        return true;
    }

    public boolean already_redo(UndoStruct u) {
        return !redolist.contains(u);
    }

    public UndoStruct redo_step(boolean cf) {
        if (redolist.size() == 0)
            return null;

        if (cf)
            clearFlash();
        clearSelection();
        CatchList.clear();

        UndoStruct Undo = (UndoStruct) redolist.get(redolist.size() - 1);
        this.redolist.remove(Undo);
        this.redo_step(Undo);
        this.undolist.add(Undo);
        if (gxInstance != null)
            gxInstance.getpprove().generate();
        return Undo;
    }

    public UndoStruct redo_step() {
        return redo_step(true);
    }


    public CPoint fd_point(int index) {
        if (index <= 0) {
            return null;
        }
        Pro_point p = gterm().getProPoint(index);
        if (p != null) {
            String s = p.name;
            for (int i = 0; i < pointlist.size(); i++) {
                CPoint t = (CPoint) pointlist.get(i);
                if (t.equals(s))
                    return t;
            }
        }
        if (index >= 1 && index <= pointlist.size())
            return (CPoint) pointlist.get(index - 1);

        return null;
    }

    public CAngle fd_angle_4p(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        for (int i = 0; i < anglelist.size(); i++) {
            CAngle ag = (CAngle) anglelist.get(i);
            if (ag.isSame(p1, p2, p3, p4)) {
                return ag;
            }
        }
        return null;
    }

    public CAngle fd_angle_m(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        for (int i = 0; i < anglelist.size(); i++) {
            CAngle ag = (CAngle) anglelist.get(i);
            if (ag.isSame(p1, p2, p3, p4)) {
                return ag;
            }
        }
        return null;
    }

    public CAngle fd_angle(CAngle ag) {
        for (int i = 0; i < anglelist.size(); i++) {
            CAngle g = (CAngle) anglelist.get(i);
            if (g.sameAngle(ag)) {
                return g;
            }
        }
        return null;
    }

    public Circle add_rcircle(int o, int a, int b) {

        if (o == 0 || a == 0 || b == 0) {
            return null;
        }
        if (fd_rcircle(o, a, b) != null)
            return fd_rcircle(o, a, b);

        int op = a;
        if (o == a)
            op = b;

        return this.addCr(o, op);
    }

    public Circle addCr(int o, int a) {
        CPoint p1 = this.fd_point(o);
        Circle c = null;

        if ((c = this.fd_circle(o, a)) != null) {
            return c;
        }
        c = new Circle(p1, fd_point(a));
        this.addCircleToList(c);
        return c;
    }

    public Circle fd_rcircle(int o, int a, int b) {
        if (o == 0 || a == 0 || b == 0) {
            return null;
        }
        CPoint p1 = this.fd_point(o);
        CPoint p2 = this.fd_point(a);
        CPoint p3 = this.fd_point(b);
        if (p1 == null || p2 == null || p3 == null) {
            return null;
        }
        CPoint op = p2;
        if (o == a)
            op = p3;

        for (int i = 0; i < circlelist.size(); i++) {
            Circle cc = (Circle) circlelist.get(i);
            if (cc.points.contains(op) && cc.o == p1) {
                return cc;
            }
        }
        return null;
    }

    public Circle fd_circle(int o, int a) {
        if (o == 0 || a == 0) {
            return null;
        }
        CPoint p1 = this.fd_point(o);
        CPoint p2 = this.fd_point(a);
        if (p1 == null || p2 == null) {
            return null;
        }

        for (int i = 0; i < circlelist.size(); i++) {
            Circle cc = (Circle) circlelist.get(i);
            if (cc.points.contains(p2) && cc.o == p1) {
                return cc;
            }
        }
        return null;
    }

    public Circle fd_circleOR(CPoint o, CPoint p1, CPoint p2) {
        for (int i = 0; i < circlelist.size(); i++) {
            Circle cc = (Circle) circlelist.get(i);
            if (cc.o != o)
                continue;
            CPoint[] pp = cc.getRadiusPoint();
            if (pp[0] == p1 && pp[1] == p2) {
                return cc;
            }
        }
        return null;
    }

    public Circle fd_circle(int a, int b, int c) {
        CPoint p1 = this.fd_point(a);
        CPoint p2 = this.fd_point(b);
        CPoint p3 = this.fd_point(c);
        for (int i = 0; i < circlelist.size(); i++) {
            Circle cc = (Circle) circlelist.get(i);
            if (cc.points.contains(p1) && cc.points.contains(p2) &&
                    cc.points.contains(p3)) {
                return cc;
            }
        }
        return null;

    }


    public Cedmark addedMark(int a, int b) {
        CPoint p1 = this.fd_point(a);
        CPoint p2 = this.fd_point(b);
        if (p1 != null && p2 != null && this.fd_edmark(p1, p2) == null) {
            Cedmark ed = new Cedmark(p1, p2);
            otherlist.add(ed);
            return ed;
        }
        return null;
    }

    public Cedmark addedMark(CPoint p1, CPoint p2) {
        if (p1 != null && p2 != null && this.fd_edmark(p1, p2) == null) {
            Cedmark ed = new Cedmark(p1, p2);
            otherlist.add(ed);
            return ed;
        }
        return null;
    }

    public void addedMarks(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {

        Cedmark e1 = addedMark(p1, p2);
        Cedmark e2 = addedMark(p3, p4);
        int n = this.getEMarkNum();
        n = n / 2;
        if (e1 != null) {
            e1.setdnum(n);
        }
        if (e2 != null) {
            e2.setdnum(n);
        }
    }

    int aux_angle = 0;
    int aux_polygon = 0;
    int aux_mark = 0;

    public void resetAux() {
        this.UndoAdded();
        this.Undo_step();
        aux_angle = 0;
        aux_polygon = 0;
        aux_mark = 0;
    }

    public void flashStep(Vector v) {
        this.setUndoListForFlash(v);
    }

    public String getAngleSimpleName() {
        int n = anglelist.size() + 1;
        String sn;
        char[] ch = new char[1];
        ch[0] = (char) ('A' + n - 10);
        if (n >= 10) {
            sn = new String(ch);
        } else {
            sn = new Integer(n).toString();
        }
        return sn;
    }

    public void setCurrentDrawPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setCurrentInstance(GExpert gx) {
        gxInstance = gx;
        this.panel = gx.d;
    }

    public param getANewParam() {
        int n = paraCounter;
        param p1 = parameter[paraCounter - 1] = new param(paraCounter++, 0);
        return p1;
    }


    public int add_sp_angle_value(int v) {
        int n = paraCounter;
        param p1 = parameter[paraCounter - 1] = new param(paraCounter++, 0);
        p1.value = constraint.get_sp_ag_value(v);
        p1.setParameterStatic();
        return p1.xindex;
    }


    String get_cir_center_name() {
        int k = 0;
        while (true) {
            String s = "O";
            if (k != 0)
                s += k;
            boolean e = false;
            for (int i = 0; i < pointlist.size(); i++) {
                CPoint pt = (CPoint) pointlist.get(i);
                String st = pt.getname();
                if (st != null && st.equalsIgnoreCase(s)) {
                    e = true;
                    break;
                }
            }
            if (e == false) {
                break;
            }
            k++;
        }
        if (k == 0)
            return "O";
        else return "O" + k;
    }


    public void add_to_undolist(UndoStruct u) {
        if (!undolist.contains(u))
            undolist.add(u);

    }

    public void remove_from_undolist(UndoStruct u) {
        undolist.remove(u);
    }

    public void add_to_redolist(UndoStruct u) {
        if (!redolist.contains(u))
            redolist.add(u);

    }

    public void remove_from_redolist(UndoStruct u) {
        redolist.remove(u);
    }

    public boolean need_save() {
        return pointlist.size() > 0
                || textlist.size() > 0
                || otherlist.size() > 0
                || paraCounter > 1;
    }

//////////////////////////////////////////////////////////////////////////////////

    public CPolygon fd_polygon(Circle c) {
        for (int i = 0; i < polygonlist.size(); i++) {
            CPolygon cp = (CPolygon) polygonlist.get(i);
            if (cp.ftype != 1)
                continue;

            CClass x = cp.getElement(0);
            if (x != c.o)
                continue;

            CPoint[] pp = c.getRadiusPoint();
            if (pp[0] == cp.getElement(1) && pp[1] == cp.getElement(2))
                return cp;
        }
        return null;
    }

/////////////////////////////////////////////////////////////////////

    public TMono reduce(TMono m) {
        PolyBasic basic = GeoPoly.getInstance();
        return basic.reduce(basic.p_copy(m), parameter);
    }

    public void onDBClick(CClass c) {
        if (c == null)
            return;
        int t = c.get_type();
        switch (t) {
            case CClass.TEXT:
                CText tx = (CText) c;
                if (tx.getType() == CText.VALUE_TEXT) {
                    TextValueEditor dlg = new TextValueEditor(gxInstance);
                    gxInstance.centerDialog(dlg);
                    dlg.setText(tx);
                    dlg.setVisible(true);
                    break;

                } else {
                    dialog_addText(tx, tx.getX(), tx.getY());
                    break;
                }

            default:
                this.viewElement(c);
        }

    }

    public double roundn(double r, int n) {
        if (n <= 0)
            return r;

        double d = r;
        double k = 1.0;
        while (n-- > 0) {
            k *= 10;
        }

        return (int) (r * k) / k;
    }

    public double calculate(CTextValue ct) {
        if (ct == null) return 0.0;

        switch (ct.TYPE) {
            case CTextValue.PLUS:
                return calculate(ct.left) + calculate(ct.right);
            case CTextValue.MINUS:
                return calculate(ct.left) - calculate(ct.right);
            case CTextValue.MUL:
                return calculate(ct.left) * calculate(ct.right);
            case CTextValue.DIV:
                return calculate(ct.left) / calculate(ct.right);
            case CTextValue.SQRT:
                return Math.sqrt(calculate(ct.left));
            case CTextValue.SIN:
                return Math.sin(calculate(ct.left));
            case CTextValue.COS:
                return Math.cos(calculate(ct.left));
            case CTextValue.EXP:
                return Math.pow(calculate(ct.left), calculate(ct.right));
            case CTextValue.NODE:
                return parameter[ct.index - 1].value;
            case CTextValue.VALUE:
                return ct.dvalue;
            case CTextValue.PI:
                return Math.PI;
            case CTextValue.E:
                return Math.E;
            case CTextValue.FUNC:
                return CTextValue.cal_func(ct.value, calculate(ct.left));
            case CTextValue.PARAM: {
                CTextValue t = fd_para(ct.sname);
                if (t == null)
                    return 0.0;
                else return t.dvalue;
            }

        }
        return 0.0;

    }

    CTextValue fd_para(String s) {
        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            if (t.getType() == CText.VALUE_TEXT && t.getname().equalsIgnoreCase(s))
                return t.tvalue;
        }
        return null;
    }


    final public void addCalculationPX(CPoint p) {
        if (p == null)
            return;
        CText tx = new CText(5, 2, "x" + p.x1.xindex);
        tx.setTextType(CText.VALUE_TEXT);
        tx.m_name = p.m_name + ".x";
        tx.m_width = 2;
        tx.m_dash = 3;

        if (tx.tvalue != null)
            tx.tvalue.calculate(this);
        getTextLocation(tx);
        addText(tx);

    }

    final public void addCalculationPY(CPoint p) {
        if (p == null)
            return;
        CText tx = new CText(5, 2, "x" + p.y1.xindex);
        tx.setTextType(CText.VALUE_TEXT);
        tx.m_name = p.m_name + ".x";
        tx.m_width = 2;
        tx.m_dash = 3;

        if (tx.tvalue != null)
            tx.tvalue.calculate(this);
        getTextLocation(tx);
        addText(tx);
    }

    final public void addCalculationPolygon(CPolygon poly) {
        if (poly == null)
            return;
        String area = this.getLanguage(461, "Area") + " ";
        int n = poly.getPtn();
        for (int i = 0; i < n - 1; i++)
            area += poly.getElement(i);

        CText tx = new CText(5, 2, "");
        tx.setTextType(CText.VALUE_TEXT);
        tx.m_name = area;
        tx.m_width = 1;
        tx.m_dash = 3;
        tx.father = poly;

        if (tx.tvalue != null)
            tx.tvalue.calculate(this);
        getTextLocation(tx);
        addText(tx);
    }

    final public void addLineSlope(CLine ln) {
        if (ln == null)
            return;
        CPoint[] pp = ln.getTowSideOfLine();
        if (pp == null)
            return;
        if (pp.length != 2)
            return;
        String s = "(x" + pp[0].x1.xindex + " - x" + pp[1].x1.xindex + ") / (x" +
                pp[0].y1.xindex + " - x" + pp[1].y1.xindex + ")";
        CText tx = new CText(5, 2, s);
        tx.setTextType(CText.VALUE_TEXT);
        tx.m_name = "slope_" + ln.getname();
        tx.m_width = 1;
        tx.m_dash = 3;
        tx.father = ln;

        if (tx.tvalue != null)
            tx.tvalue.calculate(this);
        getTextLocation(tx);
        addText(tx);
    }

    final public void addCalculationCircle(Circle c, int t) {
        if (c == null)
            return;
        CPoint[] pp = c.getRadiusPoint();
        if (pp == null || pp.length == 0)
            return;
        String s, sname;
        if (t == 0) {
            s = CTextValue.SPI + " * " +
                    "((x" + pp[0].x1.xindex + " - x" + pp[1].x1.xindex + ")^2 + (x" +
                    pp[0].y1.xindex + " - x" + pp[1].y1.xindex + ")^2)";
            sname = this.getLanguage(461, "Area") + " " + c.m_name;
        } else if (t == 1) {
            s = "2 * " + CTextValue.SPI + " * sqrt((x" + pp[0].x1.xindex + " - x" + pp[1].x1.xindex + ")^2 + (x" +
                    pp[0].y1.xindex + " - x" + pp[1].y1.xindex + ")^2)";
            sname = this.getLanguage(460, "Girth") + " " + c.m_name;
        } else {
            s = "sqrt((x" + pp[0].x1.xindex + " - x" + pp[1].x1.xindex + ")^2 + (x" +
                    pp[0].y1.xindex + " - x" + pp[1].y1.xindex + ")^2)";
            sname = this.getLanguage(4004, "Radius") + " " + c.m_name;
        }

        CText tx = new CText(5, 2, s);
        tx.setTextType(CText.VALUE_TEXT);
        tx.m_name = sname;
        tx.m_width = 1;
        tx.m_dash = 3;
        tx.father = c;

        if (tx.tvalue != null)
            tx.tvalue.calculate(this);
        getTextLocation(tx);
        addText(tx);
    }

    public void getTextLocation(CText t1) {
        int n = 0;
        int n1 = 5;
        for (int i = 0; i < textlist.size(); i++) {
            CText t = (CText) textlist.get(i);
            if (t.getType() == 3) {
                n = (int) (t.getY() + t.height + 3);
                n1 = t.getX();
            }
        }
        t1.setXY(n1, n);
    }

    public void flashAllNonVisibleObjects() {
    }

    public void cancelCurrentAction() {

        int type = CurrentAction;
        if (type != MOVE && CurrentAction == CONSTRUCT_FROM_TEXT) {
            this.clearFlash();
        }

        this.CurrentAction = type;
        clearSelection();


        if (type == SETTRACK)
            CTrackPt = null;

        FirstPnt = SecondPnt = null;
        if (type != D_POLYGON)
            STATUS = 0;
        CatchList.clear();
        vtrx = vtry = vx1 = vy1 = vangle = 0;
        if (panel != null && gxInstance != null)
            panel.repaint();


        if (null != this.UndoAdded("", false, false))
            this.Undo_stepPure();
    }


    public void hightlightAllInvisibleObject() {
        Vector v = this.getAllSolidObj();
        for (int i = 0; i < v.size(); i++) {
            CClass c = (CClass) v.get(i);
            if (c == null || c.visible()) {
                v.remove(i);
                i--;
            }
        }
        SelectList.clear();
        SelectList.addAll(v);
    }


    public int getActionType(int ac) //-1. ByPass Action;     0. defalut;
    //  1. Draw Action + point; 2: draw action line + circle
    // 3: fill action 4: angle 5: move/select/intersect   
    {
        switch (ac) {
            case D_POINT:
                return 1;
            case D_LINE:
            case D_PARELINE:
            case D_PERPLINE:
            case PERPWITHFOOT:
            case D_POLYGON:
            case D_CIRCLE:
            case D_3PCIRCLE:
                return 2;
            case D_MIDPOINT:
                return 1;
            case D_PSQUARE:
                return 2;
            case D_TEXT:
                return 0;
            case D_PFOOT:
                return 2;
            case D_CIRCLEBYRADIUS:
            case D_PTDISTANCE:
            case D_CCLINE:
            case D_SQUARE:
                return 1;
            case LRATIO:
            case D_PRATIO:
                return 2;
            case CIRCUMCENTER:
            case BARYCENTER:
            case ORTHOCENTER:
            case INCENTER:
                return 1;
            case D_TRATIO:
                return 2;
            case D_ANGLE:
                return 4;
            case SETEQANGLE:
            case MEET:
                return 5;
            case D_IOSTRI:
                return 2;
            case MIRROR:
                return 5;
            case DISTANCE:
                return 0;
            case H_LINE:
            case V_LINE:
            case D_ALINE:
            case D_ABLINE:
            case D_BLINE:
            case D_CIR_BY_DIM:
            case D_TCLINE:
                return 2;
            case CCTANGENT:
            case SELECT:
            case MOVE:
            case VIEWELEMENT:
            case TRANSLATE:
            case ZOOM_IN:
            case ZOOM_OUT:
            case SETTRACK:
            case ANIMATION:
                return 5;
            case DEFINEPOLY:
                return 3;
            case MULSELECTSOLUTION:
            case MOVENAME:
            case AUTOSHOWSTEP:
                return 5;
            case EQMARK:
            case PROVE:
                return 3;
            case TRIANGLE:
                return 2;
            case HIDEOBJECT:
                return 5;
            case DRAWTRIALL:
            case DRAWTRISQISO:
            case PARALLELOGRAM:
            case RECTANGLE:
            case TRAPEZOID:
            case RA_TRAPEZOID:
                return 2;
            case SETEQSIDE:
            case SHOWOBJECT:
            case SETEQANGLE3P:
            case SETCCTANGENT:
            case NTANGLE:
                return 5;
            case SANGLE:
            case RATIO:
                return 5;
            case RAMARK:
                return 2;
            case TRANSFORM:
            case EQUIVALENCE:
            case FREE_TRANSFORM:
                return 5;
            case LOCUS:
                return 2;
            case ARROW:
                return 2;
            case CONSTRUCT_FROM_TEXT:
                return 5;
        }
        return -1;
    }

    public void addCTMark(CLine ln1, CLine ln2) {
        if (ln1 == null || ln2 == null)
            return;
        if (!CLine.isPerp(ln1, ln2)) {
            JOptionPane.showMessageDialog(gxInstance, "The selected two line is not perpendicular");
            return;
        }

        CTMark m = new CTMark(ln1, ln2);
        this.otherlist.add(m);
    }

    public void addCTMark(CPoint p1, CPoint p2, CPoint p3, CPoint p4) {
        addCTMark(fd_line(p1, p2), fd_line(p3, p4));
    }


    public void PolygonTransPointsCreated(CPolygon poly) {
        CPoint pt0, pt1;
        pt0 = pt1 = null;

        int n = poly.getPtn();
        double cx = catchX + vx1;
        double cy = catchY + vy1;
        double sin = Math.sin(vangle);
        double cos = Math.cos(vangle);


        for (int i = 0; i < n; i++) {
            CPoint t = poly.getPoint(i);
            double tx = (t.getx() + vx1);
            double ty = (t.gety() + vy1);

            tx -= cx;
            ty -= cy;
            double mx = (tx) * cos - (ty) * sin;
            double my = (tx) * sin + (ty) * cos;
            tx = mx + cx;
            ty = my + cy;

            CPoint t1 = this.SelectAPoint(tx, ty);
            if (t1 != null && t1 != t) {
                pt0 = t;
                pt1 = t1;
            }
        }
        if (pt0 == null || pt1 == null)
            return;

        for (int i = 0; i < n; i++) {
            CPoint t = poly.getPoint(i);
            double tx = (t.getx() + vx1);
            double ty = (t.gety() + vy1);

            tx -= cx;
            ty -= cy;
            double mx = (tx) * cos - (ty) * sin;
            double my = (tx) * sin + (ty) * cos;
            tx = mx + cx;
            ty = my + cy;

            addOrientedSegment(pt0,pt1,t,tx,ty);
            //CPoint t1 = this.SmartgetApointFromXY(tx, ty);
        }
    }

    public void addOrientedSegment(CPoint p1, CPoint p2, CPoint px, double x, double y) {
        CPoint p = this.CreateANewPoint(x, y);

        constraint cs = new constraint(constraint.PRATIO, p, px, p1, p2, new Integer(1), new Integer(1));
        CPoint pu = this.addADecidedPointWithUnite(p);
        if (pu == null) {
            this.addConstraintToList(cs);
            this.addPointToList(p);
        }
    }

    public void setTextPositionAutomatically(CText tex)
    {
        if( tex.getType() != CText.NAME_TEXT )
            return;

        Point o = tex.getLocation();
        double w = tex.w;
        double h = tex.height;
        double r = 15;

        CClass c = tex.father;
        if(c != null && c.get_type() == CClass.POINT)
        {
            CPoint px = (CPoint)c;
            double x = tex.getX();
            double y = tex.getY();
            double dx = x ;//- px.getx();
            double dy = y ;//- px.gety();
            double dpi = Math.PI / 16;
            boolean bfound = false;
            double sx, sy;
            sx = sy = 0;
            for(int i = 0; i < 16; i ++)
            {
                if(bfound)
                   break;
                double sta = i * dpi;
                for(int j = 0; j < 2; j ++)
                {
                    sx = dx * Math.cos(sta) - dy * Math.sin(sta);
                    sy = dx * Math.sin(sta) + dy * Math.cos(sta);
                    if(!intsWithCircle(sx + px.getx(),sy + px.gety(),r))
                    {
                        bfound = true;
                        break;
                    }
                    if(bfound)
                        break;
                    sta *= -1;
                }
            }
         if(bfound)
            {
                tex.setXY((int)(sx  ), (int)(sy  ));
            }
        }

    }

    public boolean intsWithCircle(double ptx, double pty, double r)
    {

        boolean bat = false;
        int size = pointlist.size();
        for (int i = 0; i < size -1; i++) {
            CPoint p = (CPoint) pointlist.get(i);
            double ds = Math.pow(p.getx() - ptx, 2) + Math.pow(p.gety() - pty, 2);
            ds = Math.sqrt(ds);
            if(ds < r){
                return true;
            }
        }

        size = linelist.size();
        for (int i = 0; i < size; i++) {
            CLine ln = (CLine) linelist.get(i);
            double d = ln.distance(ptx,pty);
            if(d < r)
            {
                if( ln.isOnMiddle(ptx, pty))
                    return true;
            }
        }
        size = circlelist.size();
        for (int i = 0; i < size; i++) {
            Circle c = (Circle) circlelist.get(i);
            double d = Math.sqrt(Math.pow(c.o.getx() - ptx, 2) + Math.pow(c.o.gety() - pty, 2));
            if( Math.abs(d - c.getRadius()) < r)
                return true;
        }
        return false;
    }
}


