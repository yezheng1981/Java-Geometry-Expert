package gprover;

import java.io.*;
import java.util.Vector;


/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-15
 * Time: 17:20:04
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main1(String[] args) {
        try {

            String user_directory = System.getProperty("user.dir");
            String sp = System.getProperty("file.separator");
            String dr = user_directory + sp + "ex";
            File file = new File(dr);


            Vector vm = new Vector();
            readThems(file, vm);
            for (int id = 0; id < vm.size(); id++) {
                gterm gt = (gterm) vm.get(id);
                System.out.print(id + " : " + gt.getName() + "\t\t");
                if (id % 4 == 0)
                    Cm.print("\n");
            }

            int t = 0;
            int f = 0;
            int n = 0;
            Cm.print("\n\n************************\n");

            Vector tlist = new Vector();
            long t1 = System.currentTimeMillis();

            for (int id = 0; id < vm.size(); id++) {
                gdd_bc db = new gdd_bc();
                db.init_dbase();

                gterm gt = (gterm) vm.get(id);
                db.setExample(gt);

                db.sbase();
                db.fixpoint();

                if (gt.getConc().pred == 0) {
                    n++;
                    System.out.print(id + " : " + gt.getName() + "\t\tNO\n");
                } else if (db.docc()) {
                    t++;
                    System.out.print(id + " : " + gt.getName() + "\t\ttrue\n");
                    tlist.add(gt);

                    FileOutputStream fp;
                    String drec = (dr + sp + "proved");
                    File ff = new File(drec + sp + gt.getName() + ".rtf");

                    if (ff.exists()) {
                        ff.delete();
                        fp = new FileOutputStream(ff, true);

                    } else {
                        ff.createNewFile();
                        fp = new FileOutputStream(ff, false);
                    }
                    if (fp == null) return;
                    DataOutputStream out = new DataOutputStream(fp);
                    gt.Save(out);

                    //db.setPrintToString();
                    //db.show_fproof();
                    //out.writeBytes(db.getFileProve().append("\n********************************\n").toString());
                    out.close();
                } else {
                    f++;
                    System.out.print(id + " : " + gt.getName() + "\t\tfalse\n");
                }
            }
            Cm.print("Total = " + vm.size() + ";  t =  " + t + ",  f = " + f + ", n = " + n);
            long t2 = System.currentTimeMillis();
            Cm.print("Time = " + (t2 - t1) / 1000.0);
        } catch (IOException ee) {
        }
    }

    static void proveGDD(gterm gt) {

    }

    public static void main(String[] args) {
        main1(args);
    }

    static void proveFull() {
        try {
            String user_directory = System.getProperty("user.dir");
            String sp = System.getProperty("file.separator");
            String dr = user_directory + sp + "ex";
            File file = new File(dr);


            Vector vm = new Vector();
            readThems(file, vm);
            for (int id = 0; id < vm.size(); id++) {
                gterm gt = (gterm) vm.get(id);
                System.out.print(id + " : " + gt.getName() + "\t\t");
                if (id % 4 == 0)
                    Cm.print("\n");


            }

            int t = 0;
            int f = 0;
            int n = 0;
            Cm.print("\n\n************************\n");

            Vector tlist = new Vector();
            long t1 = System.currentTimeMillis();
            Full full = new Full();
            int nt = 0;
            for (int id = 0; id < vm.size(); id++) {
                Full.set_showdetai(false);


                full.init_dbase();

                gterm gt = (gterm) vm.get(id);
                full.setExample(gt);
                full.sbase();
                full.setNoPrint();

                if (id == 482) {
                    System.gc();
                }
                full.prove_full();
                if (full.isProvedTrue()) {
                    FileOutputStream fp;
                    String drec = (dr + sp + "proved");
                    File ff = new File(drec + sp + gt.getName() + ".rtf");

                    if (ff.exists()) {
                        ff.delete();
                        fp = new FileOutputStream(ff, true);

                    } else {
                        ff.createNewFile();
                        fp = new FileOutputStream(ff, false);
                    }
                    if (fp == null) return;
                    DataOutputStream out = new DataOutputStream(fp);

                    boolean s = false;
                    gr_term gr = full.getFullAngleProofHead();
                    while (gr != null) {
                        el_term e = gr.el;
                        if (e == null) {
                            gr = gr.nx;
                            continue;
                        }
                        while (e != null) {
                            cond c = e.co;
                            full.setPrintToString();
                            while (c != null) {
                                full.setConc(c);
                                if (full.docc()) {
                                    full.show_fproof();
                                    cond conc = full.all_nd.nx;
                                    int nx = 0;
                                    while (conc != null) {
                                        nx++;
                                        conc = conc.nx;
                                    }
                                    if (nx > 1) {
                                        s = true;
                                        System.out.print("(" + nx + ")");
                                        //   full.show_fproof();
                                        out.writeBytes(full.getFileProve().append("\n********************************\n").toString());
                                    }
                                }
                                c = c.nx;
                            }
                            e = e.nx;
                        }
                        gr = gr.nx;

                    }
                    if (s) {
                        gt.Save(out);
                    }
                    out.close();
                    nt ++;
                    System.out.print(id + " : " + gt.getName() + "\t\ttrue\n");

                } else {
                    // System.out.print(id + " : " + gt.getName() + "\t\tfalse\n");
                }
            }
            Cm.print("Total :" +vm.size() +", and true: " +nt);
        } catch (IOException ee) {

        }

    }

    static void readThems(File file, Vector v) throws IOException {
        File[] sf = file.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                String nm = pathname.getName();
                return !(nm.contains("."));
            }

        });
        for (int i = 0; i < sf.length; i++) {
            if (sf[i].isDirectory())
                readThems(sf[i], v);
            else {
                BufferedReader in = new BufferedReader(new FileReader(sf[i]));

                while (true) {
                    gterm tm = new gterm();
                    if (!tm.readAterm(in)) break;
                    tm.setName(sf[i].getName());
                    v.add(tm);
                }
                in.close();
            }
        }
    }


    static int inputInt() throws IOException {
        byte[] bm = new byte[10];
        System.in.read(bm);
        String sid = new String(bm).trim();
        int dd = Integer.parseInt(sid);
        return dd;
    }
}
