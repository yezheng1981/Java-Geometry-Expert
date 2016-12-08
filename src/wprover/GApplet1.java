package wprover;

import UI.EntityButtonUI;
import gprover.gib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class GApplet1 extends JApplet {

    private boolean hasProof = false;
    private boolean hasProofPane = false;
    private int PWIDTH = 300;
//    private boolean freezept = false;


    public DPanel d;
    public drawTextProcess dp;
    public PanelProve1 pproof;


    public void init() {

        try {
            this.setAppletGridColor();
            CMisc.initFont();
            msymbol.createAllIcons();
            RuleList.loadRulers();
            gib.initRulers();
            CMisc.homedir = this.getDocumentBase();


            d = new DPanel(null);
            dp = new drawTextProcess();
            d.dp = dp;


            this.setSize(this.getAppletWidth(), this.getAppletHeight());
            d.setBackground(this.getAppletBackGround());


            getPPWidth();
            addProof();

            if (hasProof) {
                pproof = new PanelProve1(null, d, dp, false, 1) {
                    public Dimension getMaximumSize() {
                        Dimension dm = super.getMaximumSize();
//                        double w = dm.getWidth() < PWIDTH ? dm.getWidth() : PWIDTH;
                        dm.setSize(PWIDTH, dm.getHeight());
                        return dm;
                    }

                };
                pproof.setMember(d, dp);
                pproof.setApplet1(this);
                dp.setCurrentDrawPanel(d);
                addProveStepBar();
            }

            this.loadfile();
            if (pproof != null)
                dp.gt = pproof.getConstructionTerm();
            addAnimation();
            this.addProofStatus();
            setAppletGrid();

            javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        addResetButton();
        dp.SetCurrentAction(drawProcess.MOVE);
//        d.repaint();

    }

    private void createGUI() {
        if (!hasProofPane)
            getContentPane().add(d, BorderLayout.CENTER);
        else {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.add(pproof);
            panel.add(d);
//            JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pproof,d);
//            panel.resetToPreferredSizes();
            getContentPane().add(panel, BorderLayout.CENTER);

        }
    }

    public void start() {
    }

    public void stop() {
    }

    public void destroy() {
        dp.clearAll();
        d.removeAll();
        super.destroy();
    }

    public boolean isActive() {
        return super.isActive();
    }

    /////////////////////////////////////////

    private void addResetButton() {
        String s = this.getParameter("reset_button");
        if (s == null)
            return;
        if (!Boolean.parseBoolean(s))
            return;
        BReset br = new BReset();
        Dimension dm = br.getPreferredSize();
        d.add(br);
        br.setBounds(0, 0, (int) dm.getWidth(), (int) dm.getHeight());
    }

    private Color getAppletBackGround() {
        String s = this.getParameter("background");
        if (s == null)
            return null;
        return new Color(Integer.parseInt(s));

    }

    private int getAppletWidth() {
        String s = this.getParameter("Width");
        if (s == null)
            return 1000;
        return Integer.parseInt(s);
    }

    private int getAppletHeight() {
        String s = this.getParameter("Height");
        if (s == null)
            return 750;
        return Integer.parseInt(s);
    }

    private void getPPWidth() {
        String s = this.getParameter("pwidth");
        if (s != null) {
            PWIDTH = Integer.parseInt(s);
        }
    }

    private String getAppletFilename() {
        String s = this.getParameter("filename");
        return s;
    }

    private boolean getAppletAnimated() {
        String s = this.getParameter("animated");
        return Boolean.parseBoolean(s);
    }

    private boolean getAnimationTool() {
        String s = this.getParameter("animationbar");
        return Boolean.parseBoolean(s);
    }

    private void addAnimation() {
        if (this.getAnimationTool()) {
            AnimateC c = dp.getAnimateC();
            if (c != null) {
                AnimatePanel bar = new AnimatePanel(null, d, dp); //null, false);
                bar.setAttribute(c);
                Dimension dm = bar.getPreferredSize();
                bar.setEnableAll();
                d.add(bar);
                bar.setBounds(0, this.getHeight() - (int) dm.getHeight(), (int) dm.getWidth(), (int) dm.getHeight());
            }
        }

    }

    private void addProveStepBar() {
        String s = this.getParameter("provestepbar");
        if (Boolean.parseBoolean(s)) {
            ProveBar bar = new ProveBar(null, d, dp, pproof);
            Dimension dm = bar.getPreferredSize();
            d.add(bar);
            bar.setBounds(0, this.getHeight() - (int) dm.getHeight(), (int) dm.getWidth(), (int) dm.getHeight());
        }
    }

    private void addProof() {
        String s1 = this.getParameter("has_proof");
        String s2 = this.getParameter("ppane");
        if (hasProof = Boolean.parseBoolean(s1)) {

            hasProofPane = Boolean.parseBoolean(s2);
        }
    }

    private void addProofStatus() {
        String s = this.getParameter("pindex");
        if (hasProof && hasProofPane && s != null) {
            int n = Integer.parseInt(s);
            pproof.setProofStatus(n);
        }
    }


    private boolean loadfile() {
        String filename = this.getAppletFilename();

        try {
            URL ul = new URL(this.getDocumentBase(), filename);
            URLConnection urlc = ul.openConnection();
            urlc.connect();
            InputStream instream = urlc.getInputStream();
            DataInputStream in = new DataInputStream(instream);
            dp.Load(in);
            if (CMisc.version_load_now >= 0.036 && pproof != null) {
                pproof.LoadProve(in);
            }
//            in.close();
            instream.close();
            dp.SetCurrentAction(drawProcess.MOVE);
            dp.reCalculate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public void setAppletGrid() {
        String sx = this.getParameter("showgrid");
        if (sx != null && Boolean.parseBoolean(sx)) {
            String s = this.getParameter("grid");
            if (s != null) {
                int n = Integer.parseInt(s);
                dp.SetGrid(true);
                dp.setGridXY(n);
            }
        }
    }

    public void setAppletGridColor() {
        String s = this.getParameter("gridcolor");
        if (s != null) {
            int n = Integer.parseInt(s);
            CMisc.setGridColor(new Color(n));
        }
    }

    public void setPointFreezed() {
        String s = this.getParameter("freezeAll");
        if (s != null) {
            if (Boolean.parseBoolean(s)) {
                String s1 = this.getParameter("freept");

            }
        }
    }

    protected JToggleButton makeAButton(String imageName,
                                        String actionCommand,
                                        String toolTipText,
                                        String altText) {
        String imgLocation = "images/" + imageName + ".gif";
        URL imageURL = GExpert.class.getResource(imgLocation);
        Icon co = null;
        if (imageURL != null) {
            co = (new ImageIcon(imageURL, altText));
        }
        JToggleButton button = new ActionButton(co);
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.setText(null);
        return button;
    }

    public Frame getFrame() {
        Container c = this;
        while (c != null) {
            if (c instanceof Frame)
                return (Frame) c;
            c = c.getParent();
        }
        return (Frame) null;
    }


    class BReset extends JToolBar implements ActionListener {
        public BReset() {
            this.setFloatable(false);
            this.setBorder(null);
            JButton b = new JButton(GExpert.createImageIcon("images/other/reset.gif"));
            b.setToolTipText("Reset");
            b.setUI(new EntityButtonUI());
            b.addActionListener(this);
            this.add(b);
        }

        public void actionPerformed(ActionEvent e) {
            dp.clearAll();
            loadfile();
            d.repaint();
        }
    }
}
