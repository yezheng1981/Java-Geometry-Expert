/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2006-2-18
 * Time: 21:26:51
 * To change this template use File | Settings | File Templates.
 */
package gprover;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;

public class Main2 {
    public static void main(String[] args) {

//    final public static int CM_EX_PARA = 1;
//    final public static int CM_EX_ORTH = 2;
//    final public static int CM_EX_SIMSON = 3;
//    final public static int CM_EX_SQ = 4;
//    final public static int CM_EX_PAPPUS = 5;
//    final public static int CM_EX_PEDAL = 6;
//    final public static int CM_EX_MIQ1 = 7;

        String user_directory = System.getProperty("user.dir");
        String sp = System.getProperty("file.separator");
        String dr = user_directory + sp + "ex";
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(dr));
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) return;
        gterm gt = new gterm();
        try {
            gt.readAterm(new BufferedReader(new FileReader(chooser.getSelectedFile())));
            gdd_bc db = new gdd_bc();
            db.init_dbase();
//            db.proc_exam(gdd.CM_EX_PARA);

            db.setExample(gt);
            db.sbase();
//            do {
//                fixpoint();
//                last_nd = &all_nd;
//                all_nd.nx = NULL;
//                if (!conc_xtrue()) {
//                    if (print_geo) gprint("\n\n Beginning make auxillary points\n");
//                    add_aux();
//                    if (print_geo) {
//                        show_cons();
//                        show_nds();
//                    }
//                }
//            } while (all_nd.nx != NULL);

            db.fixpoint();
            //db.show_dbase((char)0);
//            db.size_of_dbase('c');
            db.show_fproof();


//            if (true)
//            {
//                FileOutputStream fp;
//                String drec = (dr + sp);
//                File ff = new File(drec + sp + gt.name + ".txt");
//
//                if (ff.exists())
//                {
//                    fp = new FileOutputStream(ff, true);
//
//                } else
//                {
//                    ff.createNewFile();
//                    fp = new FileOutputStream(ff, false);
//                }
//                if (fp == null) return;
//                DataOutputStream out = new DataOutputStream(fp);
//                gt.Save(out);
//
////                db.setPrintToFile();
//                db.show_fproof((char) 1);
//                db.show_dbase((char) 0);
//  //              out.writeBytes(db.getFileProve() + "\n********************************\n");
//                out.close();
//
////                gt.print();
////                db.show_fproof((char) 1);
//                CMisc.print(gt.name + " " + "is true");
//            } else
//            {   db.show_dbase((char)0);
//                CMisc.print(gt.name + " " + "is false");
//            }

            //db.show_dbase((char) 0);
        } catch (IOException ee) {
        }

        //CMisc.print(Cm.s2077);
    }
}
