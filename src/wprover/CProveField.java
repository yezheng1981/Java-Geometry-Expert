package wprover;

import gprover.cond;

import javax.swing.*;
import java.util.Vector;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-8-25
 * Time: 19:19:11
 * To change this template use File | Settings | File Templates.
 */
public class CProveField {
    private boolean HEAD = false;
    CProveText cpname;
    Vector clist;
    Vector vlist;

    CProveText pselect;
    CProveText pundo = null;
    CProveText pex = null;

    Point pt;
    int rstep = -1;
    int rmid = 0;

    public CProveField() {
        pt = new Point(20, 20);
        clist = new Vector();
        vlist = new Vector();
    }

    public void setXY(int x, int y) {
        pt.setLocation(x, y);
    }

    public void drag(double dx, double dy) {
        if (pselect == null) return;
        pt.setLocation((int) (pt.getX() + dx), (int) (pt.getY() + dy));
    }

    public void setCaptain(String sname) {
        if (sname == null) return;
        if (sname.endsWith(".gex"))
            sname = sname.substring(0, sname.length() - 4);
        cpname.setMessage(sname);
    }

    public CProveField(drawProcess dp) //undolist;
    {

        pt = new Point(20, 20);

        cpname = new CProveText("", "theorem");
        cpname.setFont(new Font("Dialog", Font.PLAIN, 18));
        cpname.setMessageColor(Color.black);


        clist = new Vector();
        vlist = new Vector();
        pselect = null;


        Vector vnlist = dp.undolist;
        if (vnlist.size() == 0) return;

        int i = 0;

        for (i = 0; i < vnlist.size(); i++) {
            UndoStruct un = (UndoStruct) vnlist.get(i);
            if (un.m_type == UndoStruct.T_TO_PROVE_NODE) {
                CProveText cp = new CProveText(un, "To Prove:  ");
                vlist.add(cp);
                break;
            }

            CProveText cp;
            if (i == 0)
                cp = new CProveText(un, "Given:  ");
            else
                cp = new CProveText(un);
            clist.add(cp);
        }
        int index = 0;


        i++;
        for (; i < vnlist.size(); i++) {
            UndoStruct un = (UndoStruct) vnlist.get(i);
            CProveText cp = new CProveText(un, index);
            vlist.add(cp);
            index++;
        }

        HEAD = true;
        expandAll();
    }

    public void reGenerateIndex() {

        if (HEAD) {
            this.pselect = null;
            if (vlist.size() == 0) return;

            CProveText cp = (CProveText) vlist.get(0);
            cp.setVisible(true);
            int index = 0;

            for (int i = 1; i < vlist.size(); i++) {
                CProveText cp1 = (CProveText) vlist.get(i);
                if (cp1.getVisible())
                    cp1.setIndex(index++);
            }
        } else {
            int index = 0;
            if (vlist.size() == 0) return;

            for (int i = 0; i < vlist.size(); i++) {
                CProveText cp1 = (CProveText) vlist.get(i);
                if (cp1.getVisible())
                    cp1.setIndex(index++);
            }
        }

    }

    public CProveField(Vector v, boolean head) {
        pt = new Point(20, 20);
        clist = new Vector();
        vlist = new Vector();
        HEAD = head;
        if (head) {
            cpname = new CProveText("", "theorem");
            cpname.setFont(new Font("Dialog", Font.PLAIN, 18));
            cpname.setMessageColor(Color.black);
            pselect = null;
        }

        CProveText ct = null;
        int size = v.size();
        if (size == 0) return;
        if (head) {
            ct = new CProveText((cond) v.get(size - 1), "To Prove:  ");
            vlist.add(ct);
            for (int i = 0; i < size; i++) {
                cond co = (cond) v.get(i);
                ct = new CProveText(v, co, i, false);
                vlist.add(ct);
            }
        } else {
            for (int i = 0; i < size; i++) {
                cond co = (cond) v.get(i);
                ct = new CProveText(v, co, -1, false);
                vlist.add(ct);
            }
            if (size == 1)
                ct.setMessage("Since " + ct.getMessage());
        }


        this.expandAll();

    }

    public CProveField(cond co, boolean head) {
        pt = new Point(20, 20);
        clist = new Vector();
        vlist = new Vector();
        HEAD = head;
        if (head) {
            cpname = new CProveText("", "theorem");
            cpname.setFont(new Font("Dialog", Font.PLAIN, 18));
            cpname.setMessageColor(Color.black);
            pselect = null;
        }

        int i = -1;
        CProveText ct = null;
        while (co != null) {
            if (head && i == -1)
                ct = new CProveText(co, "To Prove:  ");
            else
                ct = new CProveText(null, co, i, true);
            i++;
            vlist.add(ct);
            co = co.nx;
        }
        this.expandAll();

    }

    public void genProve(cond co) {
        int i = 0;
        CProveText ct = null;
        while (co != null) {
            ct = new CProveText(null, co, i, false);
            i++;
            vlist.add(ct);
            co = co.nx;
        }
    }

    public void genCondition(Vector v) {
        CProveText ct = null;
        for (int i = 0; i < v.size(); i++) {
            String s = (String) v.get(i);
            if (i == 0)
                ct = new CProveText("Given: ", s);
            else
                ct = new CProveText(s);
            clist.add(ct);
        }
    }

    public CProveField(Vector ulist) {
        pt = new Point(20, 20);

        clist = new Vector();
        vlist = new Vector();
        for (int i = 0; i < ulist.size(); i++) {
            UndoStruct u = (UndoStruct) ulist.get(i);
            CProveText ct = new CProveText(u, i);
            vlist.add(ct);
        }
    }

    public void expandAll() {

        for (int i = 0; i < clist.size(); i++) {
            CProveText cp = (CProveText) clist.get(i);
            cp.expand();
        }
        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);
            cp.expand();
        }


    }

    public CProveText getFirstProveNode() {
        return (CProveText) clist.get(0);
    }


    public CProveText createANewCommentNode() {
        CProveText cp = new CProveText("", "Click to edit here");
        return cp;
    }

    public void draw(Graphics2D g2) {
        Point p = new Point((int) pt.getX(), (int) pt.getY());
        draw(g2, p);
    }

    public int getFontSize() {
        if (clist.size() == 0) return 14;

        CProveText cp = (CProveText) clist.get(0);
        return cp.getFont().getSize();
    }

    public void setFontSize(int size) {
        for (int i = 0; i < clist.size(); i++) {
            CProveText cp = (CProveText) clist.get(i);
            cp.setFontSize(size);
        }
        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);
            cp.setFontSize(size);
        }
        this.pselect = null;
    }

    public CProveText getSelect() {
        return pselect;
    }

    public boolean undo_to_head(drawProcess dp) {
        if (HEAD) {
            if (vlist.size() == 0) return false;

            pselect = (CProveText) clist.get(0);
            pundo = pselect;

            int index = vlist.size() - 1;
            if (index < 0) return false;

            for (int i = index; i >= 0; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.undo_to_head(dp);
            }
            index = clist.size() - 1;
            if (index < 0) return false;
            for (int i = index; i >= 1; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.undo_to_head(dp);
            }

            dp.setUndoStructForDisPlay(pundo.getUndoStruct(), true);

        } else {
            int index = vlist.size() - 1;
            if (index < 0) return false;

            for (int i = index; i >= 0; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.undo_to_head(dp);
            }

            index = clist.size() - 1;
            if (index < 0) return false;
            for (int i = index; i >= 1; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.undo_to_head(dp);
            }
        }
        return true;
    }

    public boolean run_to_begin(drawProcess dp) {
        if (HEAD) {
            // if (vlist.size() == 0) return false;

            pselect = (CProveText) clist.get(0);
            pundo = pselect;

            int index = vlist.size() - 1;
            for (int i = index; i >= 0; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.run_to_begin(dp);
            }

            index = clist.size() - 1;
            if (index < 0) return false;
            for (int i = index; i >= 1; i--) {
                CProveText cpt = (CProveText) clist.get(i);
                cpt.run_to_begin(dp);
            }
            dp.setUndoStructForDisPlay(pselect.getUndoStruct(), true);

        } else {
            int index = vlist.size() - 1;

            for (int i = index; i >= 0; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.run_to_begin(dp);
            }
            index = clist.size() - 1;

            for (int i = index; i >= 0; i--) {
                CProveText cpt = (CProveText) clist.get(i);
                cpt.run_to_begin(dp);
            }
        }
        return true;
    }

    public boolean undo_default(drawProcess dp) {
        if (HEAD) {
            if (vlist.size() == 0) return false;

            pselect = (CProveText) vlist.get(0);
            pundo = pselect;
            int index = vlist.size() - 1;
            if (index < 0) return false;
            for (int i = index; i >= 1; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.undo_default(dp);
            }
            CProveText cpt = (CProveText) vlist.get(0);

            dp.setUndoStructForDisPlay(cpt.getUndoStruct(), true);

        } else {
            int index = vlist.size() - 1;
            if (index < 0) return false;

            for (int i = index; i >= 0; i--) {
                CProveText cpt = (CProveText) vlist.get(i);
                cpt.undo_default(dp);
            }
        }
        return true;
    }

    public boolean run_to_end(drawProcess dp) {
        while (true) {
            if (!this.next_prove_step(dp)) {
                return true;
            }
        }
    }


    public void reGenerateAll() {
        if (HEAD)
            this.reGenerateIndex();

        for (int i = 0; i < clist.size(); i++) {
            CProveText cp = (CProveText) clist.get(i);
            cp.regenerateAll();
        }

        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);
            cp.regenerateAll();
        }
    }

    public CProveText redo_invisible_head(drawProcess dp) {
        if (vlist.size() == 0) return null;
        CProveText ct = (CProveText) vlist.get(0);
        if (ct.getVisible() == false) {
            dp.redo_step(ct.getUndoStruct());
            return ct;
        } else
            return null;
    }

    public void resetStep() {
        rstep = rmid;
    }

    public boolean next(drawProcess dp) {
        CProveText ct = fd_text(++this.rstep);
        if (ct != null) {
            this.pselect = ct;
            ((drawTextProcess) dp).addaux(ct);
        } else {
            dp.resetAux();
            this.pselect = null;
            rstep = rmid;
        }

        return true;
    }

    public CProveText fd_text(int index) {
        for (int i = 0; i < clist.size(); i++) {
            CProveText cp = (CProveText) clist.get(i);
            CProveText ct = cp.fd_text(index);
            if (ct != null) return ct;
        }

        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);
            CProveText ct = cp.fd_text(index);
            if (ct != null) return ct;
        }
        return null;
    }

    public void setStepRowDefault() {
        for (int i = 0; i < clist.size(); i++) {
            CProveText cp = (CProveText) clist.get(i);
            cp.setStepRowDefault();
        }

        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);
            cp.setStepRowDefault();
        }
    }

    public boolean next_prove_step(drawProcess dp) {
        if (HEAD) {
            CBoolean find = new CBoolean(false);
            Vector vl = new Vector();

            CProveText ct = next_prove_step(dp, pundo, find);
            if (ct != null) {
                pselect = ct;
                ct.getFlashObjectList(vl, dp);
                pundo = ct.redo_invisible_head(dp);
                if (pselect != pundo)
                    vl.addAll(pundo.getUndoStruct().getAllObjects(dp));
                dp.setObjectListForFlash(vl);
            } else {
                return false;
            }
        }
        return true;
    }


    public void setSelectedUndo(UndoStruct u, drawProcess dp) {
        CProveText ct = pselect = findPText(u);
        Vector vl = new Vector();
        if (ct != null) {

            ct.getFlashObjectList(vl, dp);
            pundo = ct.redo_invisible_head(dp);
            if (pselect != pundo)
                vl.addAll(pundo.getUndoStruct().getAllObjects(dp));
            dp.setObjectListForFlash(vl);
        }
    }

    public CProveText findPText(UndoStruct un) {
        if (un == null)
            return null;


        for (int i = 0; i < clist.size(); i++) {
            CProveText cp = (CProveText) clist.get(i);
            {
                CProveText k = cp.findPText(un);
                if (k != null)
                    return k;
            }

        }

        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);
            {
                CProveText k = cp.findPText(un);
                if (k != null)
                    return k;
            }

        }
        return null;
    }


    public CProveText next_prove_step(drawProcess dp, CProveText cpt, CBoolean find) {

        for (int i = 0; i < clist.size(); i++) {
            CProveText cp = (CProveText) clist.get(i);

            CProveText t = cp.next_prove_step(dp, cpt, find);
            if (t != null) return t;
        }

        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);

            CProveText t = cp.next_prove_step(dp, cpt, find);
            if (t != null) return t;
        }
        return null;
    }


    public void draw(Graphics2D g2, Point p) {
        int dx = (int) p.getX();
        int dy = (int) p.getY();

        double wd = 0;

        if (HEAD && pselect != null) {
            pselect.draw(g2, true);
        }


        if (HEAD) {
            CProveText.resetRow();
            setStepRowDefault();

            this.drawAStep(cpname, p, g2);
            {
                double tw = cpname.getWidth();
                if (tw > wd)
                    wd = tw;
            }

            p.setLocation(p.getX(), p.getY() + 5);
            //p.setY(p.getY() + 5);

            for (int i = 0; i < clist.size(); i++) {
                CProveText cp = (CProveText) clist.get(i);
                this.drawAStep(cp, p, g2);
                double tw = cp.getWidth();
                if (tw > wd)
                    wd = tw;
            }

            rmid = CProveText.getRow();
            if (rstep < 0) rstep = rmid;

            p.setLocation(dx, p.getY());
            for (int i = 0; i < vlist.size(); i++) {
                CProveText cp = (CProveText) vlist.get(i);
                this.drawAStep(cp, p, g2);
                double tw = cp.getWidth();
                if (tw > wd)
                    wd = tw;
                if (i == 0)
                    p.setLocation(p.getX(), p.getY() + 8);
            }
        }
        // p.setY(p.getY() + 10);

        else {
            for (int i = 0; i < clist.size(); i++) {
                CProveText cp = (CProveText) clist.get(i);
                this.drawAStep(cp, p, g2);
                double tw = cp.getWidth();
                if (tw > wd)
                    wd = tw;
            }

            for (int i = 0; i < vlist.size(); i++) {
                CProveText cp = (CProveText) vlist.get(i);
                this.drawAStep(cp, p, g2);
                double tw = cp.getWidth();
                if (tw > wd)
                    wd = tw;
            }
        }

        wd += 5;
        if (HEAD) {
            cpname.setWidth(wd);
            for (int i = 0; i < clist.size(); i++) {
                CProveText cp = (CProveText) clist.get(i);
                cp.setWidth(wd);
            }
        }
        for (int i = 0; i < vlist.size(); i++) {
            CProveText cp = (CProveText) vlist.get(i);
            cp.setWidth(wd);
        }
    }

    public void move(double x, double y) {
        pt.setLocation(pt.getX() + (int) x, pt.getY() + (int) y);
    }

    public CProveText mouseMove(double x, double y) {
        CProveText fd = null;
        for (int i = 0; i < clist.size(); i++) {
            CProveText ct = (CProveText) clist.get(i);
            CProveText cpt = ct.mouseMove(x, y);
            if (cpt != null)
                fd = cpt;
        }
        for (int i = 0; i < vlist.size(); i++) {
            CProveText ct = (CProveText) vlist.get(i);
            CProveText cpt = ct.mouseMove(x, y);
            if (cpt != null)
                fd = cpt;
        }

        pex = fd;
        return fd;
    }

    public CProveText select(double x, double y, boolean on_select) {
        CProveText sel = null;

        if (HEAD) {
            if (cpname.select(x, y))
                sel = cpname;
        }

        CProveText ts;
        for (int i = 0; i < clist.size(); i++) {
            CProveText ct = (CProveText) clist.get(i);
            if ((ts = ct.selectAll(x, y)) != null)
                sel = ts;


        }

        for (int i = 0; i < vlist.size(); i++) {
            CProveText ct = (CProveText) vlist.get(i);
            if ((ts = ct.selectAll(x, y)) != null)
                sel = ts;

        }

        if (HEAD)
            pselect = sel;
        return sel;
    }

    public void expandProveNode(double x, double y) {
        CProveText cpt = this.select(x, y, true);

        if (cpt != null) {
            cpt.expand();
        }
    }

    public void clearSelection() {
        for (int i = 0; i < clist.size(); i++) {
            CProveText ct = (CProveText) clist.get(i);
            ct.clearSelection();
        }

        for (int i = 0; i < vlist.size(); i++) {
            CProveText ct = (CProveText) vlist.get(i);
            ct.clearSelection();
        }

    }

    public void drawAStep(CProveText cp, Point p, Graphics2D g2) {

        if (!cp.getVisible()) return;

        cp.setCurrentPosition(p);
        if (cp == pselect)
            cp.draw(g2, true);

        cp.draw(g2);
        cp.getNextPosition(p);

        if (cp.isExpanded()) {
            int x = (int) p.getX();
            p.setLocation(x + 45, p.getY());
            cp.drawChild(g2, p);
            p.setLocation(x, p.getY());
        }
    }

    public void removeNode(Vector v) {
    }

    public boolean removeLast() {
        return true;
    }

    public void SavePS(FileOutputStream fp, int stype) throws IOException {
        if (HEAD) {
            fp.write("%draw proof text\n".getBytes());
            fp.write("-60 -100 translate\n/ystep -8 def   /yoff 0 def  /fzoff 10 def\n ".getBytes());
            cpname.SavePS(fp, stype, 0);
            for (int i = 0; i < clist.size(); i++) {
                CProveText ct = (CProveText) clist.get(i);
                if (i == 0)
                    ct.SavePS(fp, stype, 1);
                else
                    ct.SavePS(fp, stype, 0);
            }

            for (int i = 0; i < vlist.size(); i++) {
                CProveText ct = (CProveText) vlist.get(i);
                if (i == 0)
                    ct.SavePS(fp, stype, 2);
                else
                    ct.SavePS(fp, stype, 0);
            }
        } else {
            for (int i = 0; i < clist.size(); i++) {
                CProveText ct = (CProveText) clist.get(i);
                ct.SavePS(fp, stype, 0);
            }

            for (int i = 0; i < vlist.size(); i++) {
                CProveText ct = (CProveText) vlist.get(i);
                ct.SavePS(fp, stype, 0);
            }
        }


    }

    ///////////////////////////////////////////////////////////
    public boolean saveText(DataOutputStream out, int space) throws IOException {
        if (HEAD) {
            for (int i = 0; i < vlist.size(); i++) {
                CProveText ct = (CProveText) vlist.get(i);
                ct.saveText(out, space);
            }
            out.close();
        } else {
            for (int i = 0; i < vlist.size(); i++) {
                CProveText ct = (CProveText) vlist.get(i);
                ct.saveText(out, space);
            }
        }
        return true;
    }

    public void Save(DataOutputStream out) throws IOException {

        out.writeBoolean(HEAD);
        if (HEAD) cpname.Save(out);

        int size = clist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CProveText ct = (CProveText) clist.get(i);
            ct.Save(out);
        }

        size = vlist.size();
        out.writeInt(size);
        for (int i = 0; i < size; i++) {
            CProveText ct = (CProveText) vlist.get(i);
            ct.Save(out);
        }

        out.writeInt((int) pt.getX());
        out.writeInt((int) pt.getY());

    }

    public void Load(DataInputStream in, drawProcess dp) throws IOException {

        HEAD = in.readBoolean();
        if (HEAD) {
            cpname = new CProveText();
            cpname.Load(in, dp);
        }

        int size = in.readInt();
        clist = new Vector();
        for (int i = 0; i < size; i++) {
            CProveText ct = new CProveText();
            ct.Load(in, dp);
            clist.add(ct);
        }

        size = in.readInt();
        vlist = new Vector();

        for (int i = 0; i < size; i++) {
            CProveText ct = new CProveText();
            ct.Load(in, dp);
            vlist.add(ct);
        }

        int px = in.readInt();
        int py = in.readInt();
        pt = new Point(px, py);
    }


}
