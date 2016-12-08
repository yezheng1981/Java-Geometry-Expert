package wprover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/*
* Command line program to download data from URLs and save
* it to local files. Run like this:
*/
public class Update implements ActionListener, Runnable {

    final public static String srclg = "http://www.cs.wichita.edu/~ye/install/log.txt";
    final public static String srcgx = "http://www.cs.wichita.edu/~ye/install/jgex.zip";

    GExpert gxInstance;
    ProgressDialog dlg;
    float version = 0;
    int size = 0;
    String localFile = null;


    public Update(GExpert gx) {
        gxInstance = gx;
    }

    public boolean download(String address, String localFileName) {
        OutputStream out = null;
        URLConnection conn = null;
        InputStream in = null;
        localFile = localFileName;

        try {
            URL url = new URL(address);
            out = new BufferedOutputStream(
                    new FileOutputStream(localFileName));
            conn = url.openConnection();
            in = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int numRead;
            int nread = 0;
            int percent = 0;

            dlg = new ProgressDialog(gxInstance.getFrame());
            gxInstance.centerDialog(dlg);
            dlg.setVisible(true);

            while ((numRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, numRead);
                nread += numRead;
                if (percent < nread * 100 / size) {
                    percent = nread * 100 / size;
                    dlg.setValue(percent);
                }
                if (!isRunning) {
                    in.close();
                    out.close();
                    return false;
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(gxInstance,
                    GExpert.getLanguage(1055, "Connection failed: Please check your network connection and try again."),
                    GExpert.getLanguage(1054, "Connection Failed"),
                    JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ioe) {
            }
        }
        return true;
    }

    public boolean download(String address) {
        int lastSlashIndex = address.lastIndexOf('/');
        if (lastSlashIndex >= 0 &&
                lastSlashIndex < address.length() - 1) {
            return download(address, address.substring(lastSlashIndex + 1));
        } else {
            JOptionPane.showMessageDialog(gxInstance, "Could not figure out local file name for " +
                    address);
        }
        return false;
    }

    public int ck_version_available() {
        try {
            URL ul = new URL(srclg);
            URLConnection urlc = ul.openConnection();
            urlc.connect();
            InputStream instream = urlc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(instream));
            String s = br.readLine();
            Float f = Float.parseFloat(s);
            float f1 = f.floatValue();
            s = br.readLine();
            Integer it = Integer.parseInt(s);
            size = it.intValue();
            version = f1;

            if (Version.getVersionf() < f1)
                return 1;    //true, need to update

        } catch (IOException ee) {
            JOptionPane.showMessageDialog(gxInstance,
                    GExpert.getLanguage(1055, "Connection failed: Please check your network connection and try again."),
                    GExpert.getLanguage(1054, "Connection Failed"),
                    JOptionPane.WARNING_MESSAGE);
            return -1;

        } catch (Exception ee) {
            JOptionPane.showMessageDialog(gxInstance,
                    ee.getMessage(), GExpert.getLanguage(302, "Warning"),
                    JOptionPane.WARNING_MESSAGE);
            return -1;

        }
        return 0;  //Already uptodate.
    }

    public boolean updateJGEX() {
        int rs = ck_version_available();
        if (0 == rs) {
            JOptionPane.showConfirmDialog(gxInstance, "You version of JGEX is already up to date."
                    ,
                    GExpert.getLanguage(1060, "Update Information"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (-1 == rs)
            return false;

        int n = JOptionPane.showConfirmDialog(gxInstance,
                GExpert.getLanguage(1056, "A New version is available on the server, \ndo you want to update?") + "\n\n" +
                        GExpert.getLanguage(1057, "Your current JGEX version") + ":  " + Version.getVersionf() + "\n"
                        + GExpert.getLanguage(1058, "Available JGEX version") + ":    " + version + "\n\n"
                        + GExpert.getLanguage(1059, "Press OK to update."),
                GExpert.getLanguage(1060, "Update Information"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (n == JOptionPane.OK_OPTION) {
            start();
        } else return false;

        return false;
    }


    class ProgressDialog extends JBaseDialog {
        private JProgressBar bar;
        private JButton b1, b2, b3;
        private JLabel label;
        private String title;
        private long time;

        public ProgressDialog(Frame frame) {
            super(frame, false);
            title = GExpert.getLanguage(1061, "JGEX Update");
            this.setTitle(title);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
            bar.setBorderPainted(true);
            bar.setStringPainted(true);
            panel.add(Box.createVerticalStrut(8));
            //bar.setBorder(BorderFactory.createLineBorder(new Color(128, 128, 255), 3));
            panel.add(bar);
            bar.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            label = new JLabel();
            panel.add(label);
            panel.add(Box.createVerticalStrut(8));


            JPanel panel1 = new JPanel();
            panel1.setLayout(new FlowLayout());
            b1 = new JButton(GExpert.getLanguage(3205, "Cancel"));
            b2 = new JButton(GExpert.getLanguage(3204, "OK"));
            b3 = new JButton(GExpert.getLanguage("Hide"));
            b1.setActionCommand("Cancel");
            b2.setActionCommand("OK");
            b3.setActionCommand("Hide");

            b2.setEnabled(false);
            panel1.add(b3);
            panel1.add(Box.createHorizontalGlue());
            panel1.add(b1);
            //   b3.setEnabled(false);
            panel1.add(b2);
            b1.addActionListener(Update.this);
            b2.addActionListener(Update.this);
            b3.addActionListener(Update.this);
            panel.add(panel1);
            this.getContentPane().add(panel);
            this.setSize(400, 125);
            time = System.currentTimeMillis();
            setValue(0);
        }


        public void setValue(int v) {
            bar.setValue(v);
            long t = System.currentTimeMillis() - time;
            t = t / 1000;

//            if (v == 0)
//                t = 0;
//            else
//                t = (int) (1.0 * t * (100 - v) / (v));


            if (v == 100) {
                b1.setEnabled(false);
                b2.setEnabled(true);
                b3.setEnabled(false);
                this.setVisible(true);
            }
            label.setText("File Downloaded" + ": " + v + "%.  Time : " + t + " seconds");
            this.repaint();
        }

        public void setStart() {
            b3.setEnabled(true);
        }
    }


    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equalsIgnoreCase("Cancel")) {
            isRunning = false;
            dlg.setVisible(false);
        } else if (s.equalsIgnoreCase("OK")) {
            dlg.setVisible(false);
            dlg.setStart();
            int n = JOptionPane.showConfirmDialog(gxInstance, GExpert.getLanguage(1063, "File downloaded! \nTo update JGEX, " +
                    "the program will be exited and restarted"),
                    GExpert.getLanguage(1061, "JGEX Update"), JOptionPane.OK_CANCEL_OPTION);
            if (n == JOptionPane.OK_OPTION) {

                try {
                    if (gxInstance.saveBeforeExit()) {
                        String sp = GExpert.getFileSeparator();
                        String command = "java -jar " + GExpert.getUserDir() + sp + "update.jar";
                        Process p = Runtime.getRuntime().exec(command);
                        System.exit(0);
                    }
                } catch (Exception ee) {

                }
            } else
                return;
        } else if (s.equalsIgnoreCase("Hide")) {
            dlg.setVisible(false);
        }
    }


    public void run() {
        isRunning = true;
        download(srcgx);
        isRunning = false;

    }

    boolean isRunning = false;

    public void start() {
        if (isRunning) return;
        new Thread(this, "Download").start();
    }
}

