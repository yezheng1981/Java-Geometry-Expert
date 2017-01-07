package wprover;

import UI.EntityButtonUI;
import UI.GBevelBorder;
import UI.GifEncoder;
import gprover.gib;
import gprover.gterm;
import pdf.PDFJob;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;


public class GExpert extends JFrame implements ActionListener, KeyListener, DropTargetListener, WindowListener {    // APPLET ONLY.

    private JLabel label;
    private JLabel label2;
    private JPanel tipanel;
    private Vector vpoolist = new Vector();
    private JToggleButton show_button;


    private static Language language;

    private Group group = new Group();
    private Group menugroup = new Group();

    private JToggleButton buttonMove, buttonSelect;

    public DPanel d;
    public drawTextProcess dp;
    public CProperty cp;
    public ListTree lp;


    private AnimatePanel aframe;
    private FloatableToolBar afpane;

    private DialogProperty propt;
    private SelectDialog sdialog;
    private UndoEditDialog udialog;
    private CDialogProve pdialog;
    private concDialog cdialog;
    private RulerDialog rdialog;
    private NumCheckDialog ndialog;
    private AboutDialog adialog;


    public JScrollPane scroll;
    private CProveBarPanel provePanelbar;
    private CStyleDialog styleDialog;
    private JPopExView rview;
    JToggleButton anButton;
    private mproveInputPanel inputm;

    private PanelProve1 pprove;
    private JPanel ppanel;
    private JSplitPane contentPane;
    private JFileChooser filechooser;

    private JToggleButton BK1, BK2, BK3, BK4;
    private Vector iconPool = new Vector();
    public String _command;

    public GExpert() {
        super();  //GAPPLET.
        if (CMisc.isApplication())
            init();
    }

    public void init() {

        this.setIconImage(GExpert.createImageIcon("images/gexicon.gif").getImage());    //GAPPLET
//        setLocal();
//        showWelcome();
        CMisc.initFont();
        loadRules();
        loadPreference();
        loadLanguage();
        initAttribute();
        setLookAndFeel();
        initKeyMap();

        d = new DPanel(this);
        dp = d.dp;
        dp.setCurrentInstance(this);
        dp.setLanguage(language);
        createTipLabel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        updateTitle();
        scroll = new JScrollPane(d, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(null);
        scroll.setAutoscrolls(true);
        pprove = new PanelProve1(this, d, dp, true, -1);
        inputm = pprove.getmInputPanel();
        ppanel = new JPanel();
        ppanel.setLayout(new BoxLayout(ppanel, BoxLayout.Y_AXIS));
        JToolBar ptoolbar = pprove.createToolBar();
        ppanel.add(ptoolbar);
        ppanel.add(pprove);
        panel.add(scroll);
        ppanel.setBorder(null);
        contentPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ppanel, panel);
        contentPane.setContinuousLayout(true);
        addSplitListener();

        provePanelbar = new CProveBarPanel(this);
        styleDialog = new CStyleDialog(this, d);
        addMenueToolBar();
        loadCursor();
        new DropTarget(this, this);
        addWindowListener(this);

        this.getContentPane().add(contentPane, BorderLayout.CENTER);

    }


    public void loadRules() {
        RuleList.loadRulers();
        gib.initRulers();
    }

    public JComponent getContent() {
        return contentPane;
    }

    public void addSplitListener() {
        d.addComponentListener(new ComponentListener()

        {
            public void componentResized(ComponentEvent e) {
                if (provePanelbar != null && provePanelbar.isVisible())
                    provePanelbar.movetoDxy(0, 0);
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
            }

            public void componentHidden(ComponentEvent e) {
            }
        });
    }

    public void loadPreference() {
        if (CMisc.isApplet())
            return;

        String u = getUserDir();
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(u + "/Property.x"), "UTF-8");//
            BufferedReader reader = new BufferedReader(read);
            CMisc.LoadProperty(reader);
        } catch (IOException ee) {
//            CMisc.print(ee.getMessage());
        }

    }

    public void loadLanguage() {
        if (CMisc.isApplet())
            return;

        language = new Language();
        String user_directory = getUserDir();
        File f = new File(user_directory + "/language/" + CMisc.lan + ".lan");
        language.load(f);
        Language.setLanugage(language);
    }

    public void initAttribute() {
        if (CMisc.isApplet())
            return;

        if (language != null && !language.isEnglish()) {
            Font f = language.getFont();

            if (f != null) {
                {
                    setUIFont(new FontUIResource(f));
                    CMisc.setFont(f.getName());
                }
            }
        }
    }

    public void setLocal() {
        Locale.setDefault(Locale.ENGLISH);
        if (language != null)
            language.setLocal();
//        Locale l = new Locale("en", "US");
//        this.setLocale(l);
    }

    public void showWelcome() {
    }

    public Font getDefaultFont() {
        if (language == null)
            return null;
        return language.getFont();
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

    public void loadCursor() {
    }

    public void createCursor(Toolkit kit, String file, String name) {
    }

    public Cursor getDefinedCursor(String name) {
        return null;
    }


    public boolean isPPanelVisible() {
        return ppanel.isVisible();
    }

    public void showppanel(boolean t) {
        show_button.setSelected(t);
        ppanel.setVisible(!t);
        contentPane.resetToPreferredSizes();
//        if (t == false) {
//            Dimension dm = contentPane.getMinimumSize();
//            contentPane.setSize(dm);
//        }
    }

    public JSplitPane getSplitContentPane() {
        return contentPane;
    }

    public void showRulePanel(String s, int x, int y) {
        if (rview == null) {
            rview = new JPopExView(this);
        }
        if (rview.loadRule(s)) {
            rview.setLocationRelativeTo(d);
            rview.setLocation(x, y);
            rview.setVisible(true);
        }
    }

    public PanelProve1 getpprove() {
        return pprove;
    }

    public boolean hasAFrame() {
        return aframe != null;
    }

    public AnimatePanel getAnimateDialog() {
        if (aframe == null) {
            aframe = new AnimatePanel(this, d, dp);
        }
        return aframe;
    }

    public boolean isAframeShown() {
        return afpane.isVisible() && aframe != null && aframe.isVisible();
    }

    public void showAnimatePane() {

        if (aframe == null)
            this.getAnimateDialog();


        Rectangle rc = scroll.getVisibleRect();
        if (afpane == null)
            afpane = new FloatableToolBar();
        aframe.setEnableAll();
        afpane.add(aframe);

        Dimension dm = afpane.getPreferredSize();
        int w = (int) dm.getWidth();
        int h = (int) dm.getHeight();

        afpane.show(d, (int) rc.getWidth() - w, (int) rc.getHeight() - h);
        aframe.repaint();
    }

    public RulerDialog getRulerDialog(int n) {
        if (rdialog == null) {
            rdialog = new RulerDialog(this);
            int w = rdialog.getWidth();
            int x = getX() - w;
            if (x < 0) x = 0;
            rdialog.setLocation(x, getY());
        }
        rdialog.setSelected(n);
        rdialog.setVisible(true);
        return rdialog;
    }


    public void viewElementsAuto(CClass c) {
        if (c == null)
            return;
        if (propt == null || cp == null)
            return;
        cp.SetPanelType(c);
    }

    public DialogProperty getDialogProperty() {
        if (propt == null) {
            cp = new CProperty(d, language);
            propt = new DialogProperty(this, cp);
            propt.getContentPane().add(cp);
            propt.setVisible(false);
            propt.setTitle(getLanguage("Property"));
            centerDialog(propt);
        }
        return propt;
    }

    public void centerDialog(JDialog dlg) {
        dlg.setLocation(this.getX() + this.getWidth() / 2 - dlg.getWidth() / 2,
                this.getY() + this.getHeight() / 2 -
                        dlg.getHeight() / 2);
    }

    public SelectDialog getSelectDialog() {
        if (sdialog == null) {
            sdialog = new SelectDialog(this, new Vector());
        }
        return sdialog;
    }

    public boolean isconcVisible() {
        if (cdialog != null && cdialog.isVisible()) {
            return true;
        }
        return false;
    }

    public concDialog getConcDialog() {
        if (cdialog == null) {
            cdialog = new concDialog(this, "");
            centerDialog(cdialog);
        }
        return cdialog;
    }

    public void showNumDialog() {
        if (ndialog != null && ndialog.isVisible())
            return;
        if (dp.getPointSize() == 0)
            return;
        ndialog = new NumCheckDialog(this);
        this.centerDialog(ndialog);
        ndialog.setVisible(true);
    }

    public void selectAPoint(CPoint p) {
        if (ndialog != null && ndialog.isVisible())
            ndialog.addSelectPoint(p);
    }

    public UndoEditDialog getUndoEditDialog() {
        if (udialog == null) {
            udialog = new UndoEditDialog(this);
            this.lp = udialog.getTreePanel();
        }
        return udialog;
    }

    public boolean isDialogProveVisible() {
        return pdialog != null && pdialog.isVisible();
    }

    public CDialogProve getDialogProve() {
        if (pdialog == null) {
            pdialog = new CDialogProve(this);
        }
        return pdialog;
    }

    public JFileChooser getFileChooser() {
        if (filechooser == null) {
            filechooser = new JFileChooser();
            String dr = getUserDir();
            filechooser.setCurrentDirectory(new File(dr));
            filechooser.setFileFilter(new JFileFilter("gex"));
        }
        filechooser.setSelectedFile(null);
        filechooser.setSelectedFiles(null);
        return filechooser;
    }

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    public boolean hasMannualInputBar() {
        return inputm != null;
    }

    public int getProveStatus() {
        return pprove.getSelectedIndex();
    }

    public CStyleDialog getStyleDialog() {
        return styleDialog;
    }

    public mproveInputPanel getMannalInputToolBar() {
        return inputm;
    }

    public void addButtonToDrawGroup(JToggleButton b) {
        group.add(b);
    }

    public CProveBarPanel getPProveBar() {
        return provePanelbar;
    }

    public void switchProveBarVisibility(boolean r) {
        if (r == false) {
            if (provePanelbar == null)
                return;
            showProveBar(false);
        } else
            showProveBar(true);
    }

    public void showProveBar(boolean show) {
        if (provePanelbar == null) {
            provePanelbar = new CProveBarPanel(this);
        }
        if (show) {
            Dimension dm = provePanelbar.getPreferredSize();
            int w = (int) dm.getWidth();
            int h = (int) dm.getHeight();
            Rectangle rc = scroll.getVisibleRect();
            provePanelbar.show(d, 0, (int) rc.getHeight() - h);
            provePanelbar.repaint();

            provePanelbar.setValue(-1);
        } else {
            provePanelbar.setVisible(false);
        }
    }

    public void showStyleDialog() {
        if (styleDialog == null) {
            styleDialog = new CStyleDialog(this, d);
        }
        if (true) {
            Dimension dm = styleDialog.getPreferredSize();
            int w = (int) dm.getWidth();
            int h = (int) dm.getHeight();
            Rectangle rc = scroll.getVisibleRect();
            styleDialog.show(d, 0, 0);
            styleDialog.repaint();
        } else {
            styleDialog.setVisible(false);
        }
    }

    public void createTipLabel() {

        label = new JLabel() {
            public Dimension getPreferredSize() {
                Dimension dm = new Dimension(210, 20);
                return dm;
            }

            public Dimension getMaximumSize() {
                Dimension dm = new Dimension(500, 20);
                return dm;
            }
        };

        label2 = new JLabel("") {
            public Dimension getMaximumSize() {
                Dimension dm = new Dimension(Integer.MAX_VALUE, 20);
                return dm;
            }
        };
        Font f = CMisc.button_label_font;
        label2.setFont(f);
        label.setFont(f);

        GBevelBorder border = new GBevelBorder(GBevelBorder.RAISED, 1);

        label.setBorder(border); //BorderFactory.createBevelBorder(BevelBorder.RAISED));
        label2.setBorder(border); //BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(CMisc.frameColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        tipanel = panel;


        show_button = new TStateButton(GExpert.createImageIcon("images/ticon/show.gif"),
                GExpert.createImageIcon("images/ticon/hide.gif"));

        show_button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JToggleButton b = (JToggleButton) e.getSource();
                showppanel(b.isSelected());
            }
        });

        EntityButtonUI ui = new EntityButtonUI();
        show_button.setUI(ui);
        panel.add(show_button);
        panel.add(label);
        panel.add(label2);
        panel.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
        getContentPane().add("South", panel);

    }

    public void setLocation(int x, int y) {
        super.setLocation(x, y);
    }

    void addDirectoryIcons(File f) {
        if (f.isDirectory()) {
            File contents[] = f.listFiles();
            for (int i = 0; i < contents.length; i++) {
                if (contents[i].isDirectory()) {
                    continue;
                }
                iconPool.add(GExpert.createImageIcon(contents[i].getPath()));
            }

            for (int i = 0; i < contents.length; i++) {
                String s = contents[i].getName();
                String t = s;
                if (contents[i].isDirectory()) {
                    this.addDirectoryIcons(contents[i]);
                }
            }

        }
    }

    void addAllExamples(JMenu menu) {
        String user_directory = getUserDir();
        String sp = GExpert.getFileSeparator();
        String dr = user_directory + sp + "examples";
        File dir = new File(dr);
        this.addDirectory(dir, menu, dr);
    }

//    public URL getDocumentBase() {
//        if (CMisc.isApplet()) {
//            Object o = (Object)this;
//            JApplet a = (JApplet)o;
//            return a.getDocumentBase();
//        }
//        return null;
//    }

    void addAllOnLineExamples(JMenu menu) {     // APPLICATION ONLY

        if (!CMisc.isApplet())
            return;

        try {
            Object o = (Object) this;
            JApplet a = (JApplet) o;
            URL base = a.getDocumentBase();

            URL ul = new URL(base, "example_list.txt");
            URLConnection urlc = ul.openConnection();
            urlc.connect();
            InputStream instream = urlc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(instream));
            br.readLine();
            this.addDirectory(br, menu, new String(""));
        } catch (IOException e) {
            CMisc.eprint(this, "Error in read example list.");
        }
    }

    void addDirectory(File f, JMenu menu, String path) {

        String sp = GExpert.getFileSeparator();

        if (f.isDirectory()) {
            File contents[] = f.listFiles();
            int n = contents.length - 1;
            for (int i = n; i >= 0; i--) {
                if (contents[i].isDirectory()) {
                    continue;
                }
                String s = contents[i].getName();
                String t = s;

                if (s.endsWith(".gex")) {
                    int size = s.length();
                    t = s.substring(0, size - 4);
                }
                JMenuItem mt = new JMenuItem(t);
                mt.setToolTipText(s);
                mt.setName(path);
                mt.setActionCommand("example");
                mt.addActionListener(this);
                addMenu(menu, mt);
            }

            for (int i = 0; i < contents.length; i++) {
                String s = contents[i].getName();
                String t = s;
                if (contents[i].isDirectory()) {
                    JMenu m = new JMenu(s);
                    this.addDirectory(contents[i], m, path + sp + s);
                    menu.add(m);
                }
            }

        }

    }

    void addDirectory(BufferedReader bf, JMenu menu, String path) throws IOException {

        String s1 = bf.readLine();
        s1.trim();

        while (s1.length() != 0) {
            if (s1.length() == 1 && s1.charAt(0) == '(') {
                s1 = bf.readLine();
                s1.trim();
                JMenu m = new JMenu(s1);
                addMenu(menu, m);
                if (path.length() == 0)
                    addDirectory(bf, m, s1);
                else
                    addDirectory(bf, m, path + "/" + s1);
            } else if (s1.length() == 1 && s1.charAt(0) == ')') {
                return;
            } else {
                if (s1.endsWith(".gex")) {
                    s1 = s1.substring(0, s1.length() - 4);
                    JMenuItem mt = new JMenuItem(s1);
                    mt.setName(path);
                    mt.setToolTipText(s1);
                    mt.setActionCommand("example");
                    mt.addActionListener(this);
                    addMenu(menu, mt);
                }

            }
            s1 = bf.readLine();

            if (s1 != null)
                s1.trim();
            else
                return;
        }
    }

    void addMenu(JMenu menu, JMenuItem item) {
        String name = item.getText();
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem m = (JMenuItem) menu.getItem(i);
            if (m == null) {
                continue;
            }
            if (name.compareTo(m.getText()) < 0) {
                menu.add(item, i);
                return;
            }
        }
        menu.add(item);
    }


    public void addMenueToolBar() {
        JToolBar toolBar = new JToolBar("Toolbar");
        toolBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        toolBar.setBackground(CMisc.frameColor);
        toolBar.setOpaque(false);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        addButtons(toolBar);

        toolBar.setFloatable(false);
        JToolBar toolBarRight = new JToolBar("Toolbar", JToolBar.VERTICAL);
        toolBarRight.setBorder(new GBevelBorder(GBevelBorder.RAISED, 1));//(EtchedBorder.LOWERED));
        toolBarRight.setBackground(CMisc.frameColor);
        addRightButtons(toolBarRight);
        toolBarRight.setFloatable(false);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu;

        menu = new JMenu(getLanguage("File"));
        menuBar.add(menu);

        JMenuItem item = addAMenu(menu, "New", null, 'N', this);
        addImageToItem(item, "new");
        menu.addSeparator();

        item = addAMenu(menu, "Open", null, 'O', this);
        addImageToItem(item, "open");
        menu.addSeparator();
        item = addAMenu(menu, "Save", null, 'S', this);
        addImageToItem(item, "save");
        JMenuItem item1 = addAMenu(menu, "Save as...", "null", this);
        KeyStroke ctrlP = KeyStroke.getKeyStroke('S', InputEvent.ALT_MASK);
        item1.setAccelerator(ctrlP);
        addImageToItem(item1);
//        addImageToItem(item1, "saveas");
        item = addAMenu(menu, "Save as Text", null, 'T', this);
        addImageToItem(item);

        item = addAMenu(menu, "Save as PS", null, this);
        addImageToItem(item);
        item = addAMenu(menu, "Save as PDF", null, this);
        addImageToItem(item);

        item = addAMenu(menu, "Save as Image", null, this);
        addImageToItem(item, "image");
        item = addAMenu(menu, "Save as Animated Image", null, this);
        addImageToItem(item);
        item = addAMenu(menu, "Save Proof as Animated Image", null, this);
        addImageToItem(item);

        menu.addSeparator();
        item = addAMenu(menu, "Print", "Print the client Aream", 'P', this);
        addImageToItem(item, "print");
        menu.addSeparator();
        item = addAMenu(menu, "Exit", "Exit", 'X', this);
        addImageToItem(item);


        menu = new JMenu(getLanguage("Examples"));
        menuBar.add(menu);

        if (CMisc.isApplet())
            addAllOnLineExamples(menu); // APPLET ONLY.
        else
            addAllExamples(menu);

//        menu = new JMenu("Edit");
//        menuBar.add(menu);
//        addAMenu(menu,"Redo","Redo a step",this);
//        addAMenu(menu,"Uedo","Undo a step",this);
//        addAMenu(menu,"Forward to End","Undo a step",this);
//        addAMenu(menu,"Back to Begin","Undo a step",this);
//        menu.addSeparator();
//        addAMenu(menu,"Translate","Translate View",this);
//        addAMenu(menu,"Zoom-in","Zoom in",this);
//        addAMenu(menu,"Zoom-out","Zoom out",this);
//        


        menu = new JMenu(getLanguage("Construct"));
        menuBar.add(menu);
        addRadioButtonMenuItem(menu, "Point by Point and Segment", "Click three points A B C, then click a point D on an object AB = CD", this);
        addRadioButtonMenuItem(menu, "Radical of Two Circles", "Click two circles to construct their radical axis", this);
        menu.addSeparator();

        addRadioButtonMenuItem(menu, "Oriented Segment", "Click two points A B then  a point C to get AB //= CD", this);
        JRadioButtonMenuItem it = addRadioButtonMenuItem(menu, "Oriented T Segment", "Click two points A B then point C to get CD equal and perpendicular to +- AB", this, "o_t_segment");

        JMenu s1 = new JMenu(getLanguage("Oriented Segment * Ratio"));

        addRadioButtonMenuItem(s1, "1 : 2", null, this, "Oriented Segment");
        addRadioButtonMenuItem(s1, "2 : 1", null, this, "Oriented Segment");
        addRadioButtonMenuItem(s1, "Other..", "Input your own ratio", this, "Oriented Segment");
        menu.add(s1);

        JMenu s2 = new JMenu(getLanguage("Oriented T Segment * Ratio"));
        addRadioButtonMenuItem(s2, "1 : 2", null, this, "o_t_segment");
        addRadioButtonMenuItem(s2, "2 : 1", null, this, "o_t_segment");
        addRadioButtonMenuItem(s2, "Other..", "Input your own ratio", this, "o_t_segment");
        menu.add(s2);

        menu.addSeparator();
        JMenu sub = new JMenu(getLanguage("Proportional Segment"));
//        addRadioButtonMenuItem(sub, "1 : -1", "Click two points to get a point with ratio 1:1", this, "propline");
        addRadioButtonMenuItem(sub, "1 : 1", "Click two points to get a point with ratio 1:1", this, "propline");
        addRadioButtonMenuItem(sub, "1 : 2", "Click two points to get a point with ratio 1:2", this, "propline");
        addRadioButtonMenuItem(sub, "1 : 3", "Click two points to get a point with ratio 1:3", this, "propline");
        addRadioButtonMenuItem(sub, "1 : 4", "Click two points to get a point with ratio 1:4", this, "propline");
        addRadioButtonMenuItem(sub, "1 : 5", "Click two points to get a point with ratio 1:5", this, "propline");
        addRadioButtonMenuItem(sub, "Other..", "Input your own ratio", this, "propline");
        menu.add(sub);
        menu.addSeparator();
        sub = new JMenu(getLanguage("Point"));
        addRadioButtonMenuItem(sub, "Point", "Add a single point", this);
        addRadioButtonMenuItem(sub, "Midpoint", "Click two points to get their midpoint", this);
        sub.addSeparator();
        addRadioButtonMenuItem(sub, "Circumcenter", "Construct the circumcenter by select three points", this);
        addRadioButtonMenuItem(sub, "Centroid", "Construct the centroid by clicking three points", this);
        addRadioButtonMenuItem(sub, "Orthocenter", "Construct the orthocenter by clicking three points", this);
        addRadioButtonMenuItem(sub, "Incenter", "Construct the incenter by clicking three points", this);
        sub.addSeparator();
        addRadioButtonMenuItem(sub, "Foot", "Click a point then drag to a line to construct the foot", this);
        menu.add(sub);

        sub = new JMenu(getLanguage("Line"));
        addRadioButtonMenuItem(sub, "Line", "Draw a lines by connecting two points", this);
        addRadioButtonMenuItem(sub, "Parallel", "Parallel Line", "Draw a line which is parallel to another line", this);
        addRadioButtonMenuItem(sub, "Perpendicular", "Perpendicular Line", "Draw a line which is perpendicular to another line", this);
        addRadioButtonMenuItem(sub, "Angle Bisector", "Draw an angle bisector", this);
        addRadioButtonMenuItem(sub, "Aline", "Draw Aline", this);
        addRadioButtonMenuItem(sub, "Bline", "Perp-Bisect Line", "Draw a line which is perp-bisect to another line", this);
        addRadioButtonMenuItem(sub, "TCline", "Tangent Line", "Draw line which is tangent to a circle", this);
        menu.add(sub);

        sub = new JMenu(getLanguage("Circle"));
        addRadioButtonMenuItem(sub, "Circle", "Draw a circle by a center point and a point on circle", this);
        addRadioButtonMenuItem(sub, "Circle by Three Points", "Circle by Three Points", "Draw a circle by three points", this);
        addRadioButtonMenuItem(sub, "Circler", "Circle by Radius", "Draw a circle with center and radius", this);
        menu.add(sub);

        sub = new JMenu(getLanguage("Action"));
        addRadioButtonMenuItem(sub, "Intersect", "Intersect to decide a point", this);
        addRadioButtonMenuItem(sub, "Mirror", "Mirror an element with respect to a line or a point", this);
        menu.add(sub);
        sub = new JMenu(getLanguage("Polygon"));
        addRadioButtonMenuItem(sub, "Triangle", "Draw a triangle", this, "triangle");
        addRadioButtonMenuItem(sub, "Isosceles Triangle", "Draw a isosceles triangl", this, "isosceles triangle");
        addRadioButtonMenuItem(sub, "Equilateral Triangle", "Draw a equilateral triangle", this, "equilateral triangle");
        addRadioButtonMenuItem(sub, "Right-angled Triangle", "Draw a right-angled triangle", this, "Tri_perp");
        addRadioButtonMenuItem(sub, "Isosceles Right-angled Triangle", "Draw a isosceles right-angled triangle", this, "Tri_sq_iso");
        addRadioButtonMenuItem(sub, "Quadrangle", "Draw a  quadrangle", this, "quadrangle");
        addRadioButtonMenuItem(sub, "Parallelogram", "Draw a parallelogram", this, "parallelogram");
        addRadioButtonMenuItem(sub, "Trapezoid", "Draw a trapezoid", this, "trapezoid");
        addRadioButtonMenuItem(sub, "Right-angled Trapezoid", "Draw a right angle trapezoid", this, "ra_trapezoid");
        addRadioButtonMenuItem(sub, "Rectangle", "Draw a rectangle", this, "rectangle");
        addRadioButtonMenuItem(sub, "Square", "Draw a square", this, "square");
        addRadioButtonMenuItem(sub, "Pentagon", "Draw a pentagon", this, "pentagon");
        addRadioButtonMenuItem(sub, "Polygon", "Draw a polygon", this, "polygon");
        menu.add(sub);
        sub = new JMenu(getLanguage("Special Angles"));
        addRadioButtonMenuItem(sub, "15", "Draw an angle of 15 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "30", "Draw an angle of 30 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "45", "Draw an angle of 45 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "60", "Draw an angle of 60 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "75", "Draw an angle of 75 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "90", "Draw an angle of 90 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "115", "Draw an angle of 115 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "120", "Draw an angle of 120 degree", this, "sangle");
        addRadioButtonMenuItem(sub, "Other..", "Draw an angle of 120 degree", this, "sangle");
        menu.add(sub);
//        menu.addSeparator();


        menu = new JMenu(getLanguage("Constraint"));
        addRadioButtonMenuItem(menu, "Eqangle", "Set two angles equal", this);
//        addRadioButtonMenuItem(menu, "Nteqangle", "Draw line with two angles equal", this);
        addRadioButtonMenuItem(menu, "Eqangle3p", "Set the sum of three angles equal to one", this);
        addRadioButtonMenuItem(menu, "Angle Specification", "Set specific angles of system", this);
        menu.addSeparator();

        addRadioButtonMenuItem(menu, "Equal Ratio", "Select four segments to set their equal ratio", this);

        addRadioButtonMenuItem(menu, "Equal Distance", "Set two segments to be equal", this, "Equal Distance");

        JMenu sub2 = new JMenu(getLanguage(getLanguage("Ratio Distance")));
        addRadioButtonMenuItem(sub2, "1 : 1", "Set two segments to have ratio: 1 : 1", this, "ra_side");
        addRadioButtonMenuItem(sub2, "1 : 2", "Set two segments to have ratio: 1 : 2", this, "ra_side");
        addRadioButtonMenuItem(sub2, "1 : 3", "Set two segments to have ratio: 1 : 3", this, "ra_side");
        addRadioButtonMenuItem(sub2, "Other..", "Set two segments to have specified ratio", this, "ra_side");
        menu.add(sub2);
        addRadioButtonMenuItem(menu, "CCtangent", "Set two circle to be tangent", this);
        menuBar.add(menu);
        menu = new JMenu(getLanguage("Action"));
        menuBar.add(menu);
        addRadioButtonMenuItem(menu, "Trace", "Select a point to trace its locus (in combination with move or animation)", this);
        addRadioButtonMenuItem(menu, "Locus", "The locus of a point", this);
        addRadioButtonMenuItem(menu, "Animation", "Click a point then an object to animate", this);
        menu.addSeparator();

        addRadioButtonMenuItem(menu, "Fill Polygon", "select a close segment path to fill the polygon", this);
        addRadioButtonMenuItem(menu, "Measure Distance", "Select two angle to set equal", this);
        addRadioButtonMenuItem(menu, "Arrow", "Select two points to construct an arrow", this);


        JMenu sub1 = new JMenu(getLanguage("Equal Mark"));
        addRadioButtonMenuItem(sub1, "1", "Mark for equal with one line", this, "eqmark");
        addRadioButtonMenuItem(sub1, "2", "Mark for equal with two line", this, "eqmark");
        addRadioButtonMenuItem(sub1, "3", "Mark for equal with three line", this, "eqmark");
        addRadioButtonMenuItem(sub1, "4", "Mark for equal with four line", this, "eqmark");
        menu.add(sub1);
        addRadioButtonMenuItem(menu, "Right-angle Mark", "Draw an right angle mark", this, "RAMark");
        addRadioButtonMenuItem(menu, "Calculation", "Calculation", this, "Calculation");
        menuBar.add(menu);
        menu.addSeparator();
        addRadioButtonMenuItem(menu, "Hide Object", "Hide objects", this);
        addRadioButtonMenuItem(menu, "Show Object", "Show objects that is hiden", this);
        menu.addSeparator();
        addRadioButtonMenuItem(menu, "Transform", "Transform polygon", this);
        addRadioButtonMenuItem(menu, "Equivalence", "Equivalence transform polygon", this);
        addRadioButtonMenuItem(menu, "Free Transform", "Transform polygon freely", this);


        menu = pprove.getProveMenu();
//        item = addAMenu(menu, "All Solutions", "All Solutions", this);
//        addImageToItem(item);
        menuBar.add(menu);

        menu = new JMenu(getLanguage("Lemmas"));
        menuBar.add(menu);
        addAMenu(menu, "Rulers for Full Angle", "Rulers for Full Angle", this);
        addAMenu(menu, "Rulers for GDD", "Rulers for GDD", this);


        menu = new JMenu(getLanguage("Option"));
        menuBar.add(menu);
        item = addAMenu(menu, "Preference", "Set the default Property", this);
        addImageToItem(item, "preference");

        item = addAMenu(menu, "Construct History", "Edit construct history", this);
        addImageToItem(item);
        item = addAMenu(menu, "Show Step Bar", "Show Step Bar for prove ", this);
        addImageToItem(item, "step");
        item = addAMenu(menu, "Style Dialog", "Show Draw Style Dialog", this);
        addImageToItem(item);

        menu = new JMenu(getLanguage("Help"));
        item = addAMenu(menu, "Help", "Help", KeyEvent.VK_F1, this);
        item.setAccelerator(KeyStroke.getKeyStroke("F1"));

        this.addImageToItem(item, "help");
        item = addAMenu(menu, "Online Help", "Online Help", this);
        addImageToItem(item);

        item = addAMenu(menu, "Help on Mode", "Help on Mode", this);
        addImageToItem(item);

        item = addAMenu(menu, "JGEX Homepage", "JGEX Homepage", this);
        addImageToItem(item);
        item = addAMenu(menu, "Contact Us", "Contact Us", this);
        addImageToItem(item);
        menu.addSeparator();
        item = addAMenu(menu, "Check for Update", "Check for Update", this);
        addImageToItem(item);
        item = addAMenu(menu, "About JGEX", "About Java Geometry Expert", this);
        addImageToItem(item, "infor");

        menuBar.add(menu);
        toolBarRight.add(Box.createVerticalBox());

        getContentPane().add(toolBar, BorderLayout.PAGE_START);
        getContentPane().add(toolBarRight, BorderLayout.EAST);
    }

    public JRadioButtonMenuItem addRadioButtonMenuItem(JMenu bar, String name,
                                                       String tooltip, ActionListener listener, String command) {
        JRadioButtonMenuItem item = addRadioButtonMenuItem(bar, name, tooltip, listener);
        item.setActionCommand(command);
        return item;
    }

    public JRadioButtonMenuItem addRadioButtonMenuItem(JMenu bar, String name, String text,
                                                       String tooltip, ActionListener listener) {
        JRadioButtonMenuItem item = addRadioButtonMenuItem(bar, name, tooltip, listener);
        item.setText(getLanguage(text));
        return item;
    }

    public JRadioButtonMenuItem addRadioButtonMenuItem(JMenu bar, String name,
                                                       String tooltip, ActionListener listener) {
        JRadioButtonMenuItem miten;
        miten = new JRadioButtonMenuItem(name);

        miten.setActionCommand(name);
        miten.setText(getLanguage(name));
        String s1 = getLanguageTip(name);

        if (tooltip != null)
            miten.setToolTipText(tooltip);

        if (s1 != null && s1.length() > 0)
            miten.setToolTipText(s1);

        miten.addActionListener(listener);
        miten.setActionCommand(name);
        bar.add(miten);
        menugroup.add(miten);
        return miten;
    }

    public JMenuItem addAMenu(JMenu bar, String name, String tooltip,
                              ActionListener listener, String command) {
        JMenuItem item = addAMenu(bar, name, tooltip, listener);
        item.setActionCommand(command);
        return item;
    }

    public JMenuItem addAMenu(JMenu bar, String command, String text, String tip, ActionListener listener) {
        JMenuItem item = addAMenu(bar, text, tip, listener);
        item.setActionCommand(command);
        return item;
    }

    public JMenuItem addAMenu(JMenu bar, String name, String tooltip, int ne, ActionListener listener) {
        JMenuItem item = addAMenu(bar, name, tooltip, listener);
        item.setMnemonic(ne);
        KeyStroke ctrlP = KeyStroke.getKeyStroke(ne, InputEvent.CTRL_MASK);
        item.setAccelerator(ctrlP);
        return item;
    }

    public void addImageToItem(JMenuItem item) {
        ImageIcon m = GExpert.createImageIcon("images/small/" + "blank.gif");
        item.setIcon(m);
    }

    public void addImageToItem(JMenu item) {
        ImageIcon m = GExpert.createImageIcon("images/small/" + "blank.gif");
        item.setIcon(m);
    }

    public void addImageToItem(JMenuItem item, String name) {
        ImageIcon m = GExpert.createImageIcon("images/small/" + name + ".gif");
        if (m == null)
            m = GExpert.createImageIcon("images/small/" + "blank.gif");
        item.setIcon(m);
    }

    public JMenuItem addAMenu(JMenu bar, String name, String tooltip, int ne, ActionListener listener, String image) {
        JMenuItem item = addAMenu(bar, name, tooltip, listener);
        item.setMnemonic(ne);
        KeyStroke ctrlP = KeyStroke.getKeyStroke(ne, InputEvent.CTRL_MASK);
        item.setAccelerator(ctrlP);
        ImageIcon m = GExpert.createImageIcon("images/small/" + image + ".gif");
        item.setIcon(m);
        return item;
    }

    public JMenuItem addAMenu(JMenu bar, String name, String tooltip, ActionListener listener) {
        JMenuItem miten;
        miten = new JMenuItem(name);
        if (tooltip != null) {
            miten.setToolTipText(tooltip);
        }
        miten.setActionCommand(name);
        miten.setText(this.getLanguage(name));
        miten.addActionListener(listener);
        bar.add(miten);
        return miten;
    }

    public Language getLan() {
        return language;
    }

    public static String getLanguage(String s1) {
        if (language == null)
            return s1;

        String s2 = language.getString(s1);
        if (s2 == null || s2.length() == 0)
            return s1;
        return s2;
    }

    public static String getLanguageTip(String s1) {
        if (language == null)
            return s1;

        String s2 = language.getString1(s1);
        return s2;
    }

    public static String getLanguage(int n) {
        if (language == null)
            return "";

        String s1 = language.getString(n);
        return s1;
    }

    public static String getLanguage(int n, String s) {
        if (language == null)
            return s;

        String s1 = language.getString(n);
        if (s1 != null && s1.length() > 0)
            return s1;
        return s;
    }

    public void setActionMove() {
        sendAction("Move", buttonMove);
        buttonMove.setSelected(true);
    }

    public void setActionSelect() {
        sendAction("Select", buttonSelect);
        buttonSelect.setSelected(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        Object src = e.getSource();
        sendAction(command, src);
    }

    public void sendAction(String command, Object src) {


        String tip = null;
        String ps = null;
        String pname = null;
        JToggleButton button = null;
        JMenuItem item = null;
        boolean select = true;

        if (src instanceof JMenuItem) {
            item = (JMenuItem) src;
            ps = item.getText();
            tip = item.getToolTipText();
            pname = item.getName();
            select = item.isSelected();

        } else if (src instanceof JToggleButton) {
            button = (JToggleButton) src;
            ps = button.getText();
            tip = button.getToolTipText();
            select = button.isSelected();
        }

        d.setCursor(Cursor.getDefaultCursor());

        if (command.equals("example")) {
//            try {
            if (!CMisc.isApplet())
                this.openAFile(new File(pname + "/" + tip));
            else
                openAOnlineFile(pname, ps);

        } else if (command.equals("Save as PS")) {
            if (!need_save())
                return;

            DialogPsProperty dlg = new DialogPsProperty(this);
            this.centerDialog(dlg);
            dlg.setVisible(true);
            int r = dlg.getSavePsType();
            boolean ptf = dlg.getPointfilled();
//            boolean pts = dlg.getisProveTextSaved();

            if (r == 0 || r == 1 || r == 2) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileFilter() {
                    public boolean accept(File f) {
                        return f.isDirectory() || f.getName().endsWith("ps");
                    }

                    public String getDescription() {
                        return "PostScript (*.ps)";
                    }
                });
                String dr = getUserDir();
                chooser.setCurrentDirectory(new File(dr));

                int result = chooser.showSaveDialog(this);
                if (result == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                try {
                    File file = chooser.getSelectedFile();
                    String path = file.getPath();
                    if (!path.endsWith(".ps")) {
                        path += ".ps";
                    }
                    if (file.exists() && get_User_Overwrite_Option(file.getName())) {
                        return;
                    }
                    dp.write_ps(path, r, ptf, true);
                } catch (Exception ee) {
                    CMisc.print(ee.toString() + "\n" + ee.getStackTrace());
                }
            }

        } else if (command.equalsIgnoreCase("Save as PDF")) {
            this.saveAsPDF();
        } else if (command.equals("Save as Image")) {
            this.saveAsImage();
        } else if (command.equals("Save as Animated Image")) {
            this.saveAsGIF();
        } else if (command.equalsIgnoreCase("Save Proof as Animated Image")) {
            this.saveProofAsGIF();
        } else if (command.equals("Save") || command.equals("Save as...")) {
            if (command.equals("Save"))
                this.saveAFile(false);
            else this.saveAFile(true);

        } else if (command.equals("Save as Text")) {
            if (!need_save())
                return;

            gterm gt = pprove.getConstructionTerm();
            if (gt != null) {
                JFileChooser filechooser1 = new JFileChooser();
                String dr = getUserDir();
                filechooser1.setCurrentDirectory(new File(dr));

                int result = filechooser1.showDialog(this, "Save");
                if (result == JFileChooser.APPROVE_OPTION) {
                    File f = filechooser1.getSelectedFile();
                    FileOutputStream fp;
                    try {
                        if (f.exists()) {
                            f.delete();
                            fp = new FileOutputStream(f, true);
                            fp.write("\n\n".getBytes());
                        } else {
                            f.createNewFile();
                            fp = new FileOutputStream(f, false);
                        }
                        if (fp == null) {
                            return;
                        }
                        gt.writeAterm(fp);
                        dp.writePointPosition(fp);
                        fp.close();
                    } catch (IOException ee) {
                        JOptionPane.showMessageDialog(this, ee.getMessage(),
                                "Save Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        } else if (command.equals("Open")) {

            JFileChooser chooser = getFileChooser();
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    openAFile(file);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        } else if (command.equals("Exit")) {
            if (saveBeforeExit())
                System.exit(0);
        } else if (command.equals("New")) {
            this.Clear();
        } else if (command.equals("Print")) {
            dp.PrintContent();
        } else if (command.equals("undo")) {
            dp.Undo_step();
            if (lp != null) {
                lp.reload();
            }
            setBKState();
            d.repaint();
        } else if (command.equals("redo")) {
            dp.redo_step();
            if (lp != null) {
                lp.reload();
            }
            setBKState();
            d.repaint();
        } else if (command.equals("Online Help")) {
            openURL(("http://woody.cs.wichita.edu/help/index.html"));
        } else if (command.equals("JGEX Homepage")) {
            openURL(("http://woody.cs.wichita.edu"));
        } else if (command.equals("Contact Us")) {
            openURL(("mailto:yezheng@gmail.com"));
        } else if (command.equals("ff")) {
            dp.redo();
            setBKState();
            d.repaint();
        } else if (command.equalsIgnoreCase("Check for Update")) {
            updateJGEX();
        } else if (command.equals("fr")) {
            dp.Undo();
            setBKState();
            d.repaint();
        } else if (command.equals("autoanimate")) {
            boolean b = dp.autoAnimate();

        } else if (command.equals("autoshowstep")) {
            dp.autoShowstep();
        } else if (command.equals("Preference")) {
            JDialog dlg = new MiscDialog(this);
            this.centerDialog(dlg);
            dlg.setVisible(true);
        } else if (command.equals("Show Step Bar")) {
            this.showProveBar(true);
        } else if (command.equals("Style Dialog")) {
            this.showStyleDialog();
        } else if (command.equals("About JGEX")) {
            if (adialog == null)
                adialog = new AboutDialog(this);
            adialog.initLocation();
            adialog.setVisible(true);
        } else if (command.equals("Help")) {
            String dr = getUserDir();
            String sp = getFileSeparator();
            openURL("file:///" + dr + sp + "help" + sp + "index.html");
        } else if (command.equals("grid")) {
            dp.SetGrid(select);
            repaint();
        } else if (command.equals("snap")) {
            dp.SetSnap(!dp.isSnap());
            d.repaint();
        } else if (command.equals("view")) {
            this.dp.SetCurrentAction(drawProcess.VIEWELEMENT);
        } else if (command.equals("lessgrid")) {
            dp.setMeshStep(true);
            button.setSelected(false);
            repaint();
        } else if (command.equals("moregrid")) {
            dp.setMeshStep(false);
            button.setSelected(false);
            repaint();
        } else if (command.equals("Construct History")) {
            this.getUndoEditDialog().showDialog();
        } else if (command.equals("Help on Mode")) {
            String path = HelpMode.getHelpMode(_command);
            if (path != null) {
                String dr = getUserDir();
                String sp = getFileSeparator();
                openURL("file:///" + dr + sp + "help" + sp + path);
            }
        } else {
            _command = command;
            String sx1 = GExpert.getLanguage(command);
            String sx2 = GExpert.getLanguageTip(command);
            if (sx2 == null && tip != null)
                sx2 = tip;

            setActionTip(sx1, sx2);
            if (button != null) {
                JRadioButtonMenuItem t = (JRadioButtonMenuItem) menugroup.getButton(command);
                if (t != null)
                    t.setSelected(true);
                else {
                    ButtonModel m = menugroup.getSelection();
                    if (m != null) {
                        m.setGroup(null);
                        m.setSelected(false);
                        m.setGroup(menugroup);
                    }
                }
            } else if (item != null) {
                JToggleButton b = (JToggleButton) group.getButton(command);
                if (b != null)
                    b.setSelected(true);
                else {
                    ButtonModel b1 = group.getSelection();
                    if (b1 != null) {
                        b1.setGroup(null);
                        b1.setSelected(false);
                        b1.setGroup(group);
                    }
                }
            }


            if (command.equalsIgnoreCase("select")) {
                dp.SetCurrentAction(drawProcess.SELECT);
            } else if (command.equalsIgnoreCase("point")) {
                dp.SetCurrentAction(drawProcess.D_POINT);
            } else if (command.equalsIgnoreCase("line")) {
                dp.SetCurrentAction(drawProcess.D_LINE);
            } else if (command.equalsIgnoreCase("circle")) {
                dp.SetCurrentAction(drawProcess.D_CIRCLE);

            } else if (command.equalsIgnoreCase("oriented segment")) {
                dp.SetCurrentAction(drawProcess.D_PRATIO);
                String s = ((JMenuItem) src).getText();
                int n1 = 1;
                int n2 = 1;
                ps = language.getEnglish(ps);
                if (ps.equalsIgnoreCase("Other..")) {
                    RatioSelectDialog dlg = new RatioSelectDialog(this);
                    dlg.setVisible(true);
                    dp.setParameter(dlg.getValue1(), dlg.getValue2());
                } else {
                    String s1 = ((JMenuItem) src).getText();
                    int[] t = this.parse2Int(s1);
                    dp.setParameter(t[0], t[1]);
                }
            } else if (command.equalsIgnoreCase("circler")) {
                dp.SetCurrentAction(drawProcess.D_CIRCLEBYRADIUS);
            } else if (command.equalsIgnoreCase("parallel")) {
                dp.SetCurrentAction(drawProcess.D_PARELINE);
            } else if (command.equalsIgnoreCase("perpendicular")) {
                dp.SetCurrentAction(drawProcess.D_PERPLINE);
            } else if (command.equalsIgnoreCase("aline")) {
                dp.SetCurrentAction(drawProcess.D_ALINE);
            } else if (command.equalsIgnoreCase("Angle Bisector")) {
                dp.SetCurrentAction(drawProcess.D_ABLINE);
            } else if (command.equalsIgnoreCase("bline")) {
                dp.SetCurrentAction(drawProcess.D_BLINE);
            } else if (command.equalsIgnoreCase("tcline")) {
                dp.SetCurrentAction(drawProcess.D_TCLINE); //cctangent
            }
//            else if (command.equalsIgnoreCase("cctangent")) {
//                dp.SetCurrentAction(drawProcess.CCTANGENT);
//            }
            else if (command.equalsIgnoreCase("intersect")) {
                dp.SetCurrentAction(drawProcess.MEET);
            } else if (command.equalsIgnoreCase("middle")) {
                dp.SetCurrentAction(drawProcess.D_MIDPOINT);
            } else if (command.equalsIgnoreCase("Circle by Three Points")) {
                dp.SetCurrentAction(drawProcess.D_3PCIRCLE);
            } else if (command.equalsIgnoreCase("translate")) {
                this.setDrawCursor(Cursor.HAND_CURSOR);
                dp.SetCurrentAction(drawProcess.TRANSLATE);
            } else if (command.equalsIgnoreCase("foot")) {
                dp.SetCurrentAction(drawProcess.PERPWITHFOOT);
            } else if (command.equalsIgnoreCase("angle")) {
                dp.SetCurrentAction(drawProcess.D_ANGLE);
            } else if (command.equalsIgnoreCase("zoom-in")) {
                //setDrawCursor("ZOOM_IN");
                dp.SetCurrentAction(drawProcess.ZOOM_IN);
            } else if (command.equalsIgnoreCase("zoom-out")) {
                //setDrawCursor("ZOOM_OUT");
                dp.SetCurrentAction(drawProcess.ZOOM_OUT);
            } else if (command.equalsIgnoreCase("animation")) {
                dp.SetCurrentAction(drawProcess.ANIMATION);
            } else if (command.equalsIgnoreCase("eqangle")) {
                dp.SetCurrentAction(drawProcess.SETEQANGLE);
            } else if (command.equalsIgnoreCase("nteqangle")) {
                dp.SetCurrentAction(drawProcess.NTANGLE);
            } else if (command.equalsIgnoreCase("eqangle3p")) {
                dp.SetCurrentAction(drawProcess.SETEQANGLE3P);
            } else if (command.equalsIgnoreCase("cctangent")) {
                dp.SetCurrentAction(drawProcess.SETCCTANGENT);
            } else if (command.equalsIgnoreCase("angle specification")) {
                dp.defineSpecificAngle();
            } else if (command.equalsIgnoreCase("ra_side")) {
                dp.SetCurrentAction(drawProcess.SETEQSIDE);
                dp.setcurrentStatus(0);
                ps = language.getEnglish(ps);
                if (ps.equalsIgnoreCase("Other..")) {
                    RatioSelectDialog dlg = new RatioSelectDialog(this);
                    dlg.setVisible(true);
                    dp.setParameter(dlg.getValue1(), dlg.getValue2());
                } else {
                    String s1 = ((JMenuItem) src).getText();
                    int[] t = this.parse2Int(s1);
                    dp.setParameter(t[0], t[1]);
                }

//                int status = Integer.parseInt(ps);
//                dp.setcurrentStatus(status);
            } else if (command.equalsIgnoreCase("equal distance")) {
                dp.SetCurrentAction(drawProcess.SETEQSIDE);
                dp.setcurrentStatus(1);
                dp.setParameter(1, 1);
            } else if (command.equalsIgnoreCase("fillpolygon")) {
                dp.SetCurrentAction(drawProcess.DEFINEPOLY);
            } else if (command.equalsIgnoreCase("polygon")) {
                dp.SetCurrentAction(drawProcess.D_POLYGON);
            } else if (command.equalsIgnoreCase("square")) {
                dp.SetCurrentAction(drawProcess.D_SQUARE);
            } else if (command.equalsIgnoreCase("radical of two circles")) {
                dp.SetCurrentAction(drawProcess.D_CCLINE);
            } else if (command.equalsIgnoreCase("isosceles triangle")) {
                dp.SetCurrentAction(drawProcess.D_IOSTRI);
            } else if (command.equalsIgnoreCase("fill polygon")) {
                dp.SetCurrentAction(drawProcess.DEFINEPOLY);
            } else if (command.equalsIgnoreCase("text")) {
                dp.SetCurrentAction(drawProcess.D_TEXT);
            } else if (command.equalsIgnoreCase("mirror")) {
                dp.SetCurrentAction(drawProcess.MIRROR);
            } else if (command.equalsIgnoreCase("circle by diameter")) {
                dp.SetCurrentAction(drawProcess.D_PFOOT);
            } else if (command.equalsIgnoreCase("Trace")) {
                dp.SetCurrentAction(drawProcess.SETTRACK);
            } else if (command.equalsIgnoreCase("Locus")) {
                dp.SetCurrentAction(drawProcess.LOCUS);
            } else if (command.equalsIgnoreCase("point by point and segment")) {
                dp.SetCurrentAction(drawProcess.D_PTDISTANCE);
            } else if (command.equalsIgnoreCase("propline")) {
                String s = ((JMenuItem) src).getText();
                ps = language.getEnglish(ps);
                if (ps.equalsIgnoreCase("Other..")) {
                    dp.SetCurrentAction(drawProcess.LRATIO);
                    RatioSelectDialog dlg = new RatioSelectDialog(this);
                    dlg.setVisible(true);
                    dp.setParameter(dlg.getValue1(), dlg.getValue2());
                    this.setTipText(dlg.getValue1() + ":" + dlg.getValue2());
                } else {
                    dp.SetCurrentAction(drawProcess.LRATIO);
                    int[] t = this.parse2Int(s);
                    dp.setParameter(t[0], t[1]);
                    this.setTipText(s);
                }
            } else if (command.equalsIgnoreCase("midpoint")) {
                dp.SetCurrentAction(drawProcess.D_MIDPOINT);
            } else if (command.equalsIgnoreCase("circumcenter")) {
                dp.SetCurrentAction(drawProcess.CIRCUMCENTER);
            } else if (command.equalsIgnoreCase("centroid")) {
                dp.SetCurrentAction(drawProcess.BARYCENTER);
            } else if (command.equalsIgnoreCase("orthocenter")) {
                dp.SetCurrentAction(drawProcess.ORTHOCENTER);
            } else if (command.equalsIgnoreCase("incenter")) {
                dp.SetCurrentAction(drawProcess.INCENTER);
            } else if (command.equalsIgnoreCase("move")) {
                dp.SetCurrentAction(drawProcess.MOVE);
            } else if (command.equalsIgnoreCase("o_t_segment")) {
                dp.SetCurrentAction(drawProcess.D_TRATIO);
                String s = ((JMenuItem) src).getText();
                ps = language.getEnglish(ps);
                if (ps.equalsIgnoreCase("Other..")) {
                    RatioSelectDialog dlg = new RatioSelectDialog(this);
                    dlg.setVisible(true);
                    dp.setParameter(dlg.getValue1(), dlg.getValue2());
                } else {
                    String s1 = ((JMenuItem) src).getText();
                    int[] t = this.parse2Int(s1);
                    dp.setParameter(t[0], t[1]);
                }
            } else if (command.equalsIgnoreCase("measure distance")) {
                dp.SetCurrentAction(drawProcess.DISTANCE);
            } else if (command.equalsIgnoreCase("Arrow")) {
                dp.SetCurrentAction(drawProcess.ARROW);
            } else if (command.equalsIgnoreCase("horizonal")) {
                dp.SetCurrentAction(drawProcess.H_LINE);
            } else if (command.equalsIgnoreCase("vertical")) {
                dp.SetCurrentAction(drawProcess.V_LINE);
            } else if (command.equalsIgnoreCase("eqmark")) {
                dp.SetCurrentAction(drawProcess.EQMARK);
                int status = Integer.parseInt(ps);
                dp.setcurrentStatus(status);
            } else if (command.equalsIgnoreCase("triangle")) {
                dp.setcurrentStatus(3);
                dp.SetCurrentAction(drawProcess.D_POLYGON);
                dp.setcurrentStatus(3);
            } else if (command.equalsIgnoreCase("equilateral triangle")) {
                dp.SetCurrentAction(drawProcess.DRAWTRIALL);
            } else if (command.equalsIgnoreCase("Tri_perp")) {
                dp.SetCurrentAction(drawProcess.D_PFOOT);

            } else if (command.equalsIgnoreCase("Tri_sq_iso")) {
                dp.SetCurrentAction(drawProcess.DRAWTRISQISO);
            } else if (command.equalsIgnoreCase("quadrangle")) {
                dp.setcurrentStatus(4);
                dp.SetCurrentAction(drawProcess.D_POLYGON);
                dp.setcurrentStatus(4);
            } else if (command.equalsIgnoreCase("parallelogram")) {
                dp.SetCurrentAction(drawProcess.PARALLELOGRAM);
            } else if (command.equalsIgnoreCase("ra_trapezoid")) {
                dp.SetCurrentAction(drawProcess.RA_TRAPEZOID);
            } else if (command.equalsIgnoreCase("trapezoid")) {
                dp.SetCurrentAction(drawProcess.TRAPEZOID);
            } else if (command.equalsIgnoreCase("rectangle")) {
                dp.SetCurrentAction(drawProcess.RECTANGLE);
            } else if (command.equalsIgnoreCase("pentagon")) {
                dp.setcurrentStatus(5);
                dp.SetCurrentAction(drawProcess.D_POLYGON);
                dp.setcurrentStatus(5);
            } else if (command.equalsIgnoreCase("polygon")) {
                dp.SetCurrentAction(drawProcess.D_POLYGON);
                dp.setcurrentStatus(9999);
            } else if (command.equalsIgnoreCase("hide object")) {
                dp.SetCurrentAction(drawProcess.HIDEOBJECT);
            } else if (command.equalsIgnoreCase("show object")) {
                dp.SetCurrentAction(drawProcess.SHOWOBJECT);
            } else if (command.equalsIgnoreCase("Rulers for Full Angle")) {
                getRulerDialog(1).setVisible(true);
            } else if (command.equalsIgnoreCase("Rulers for GDD")) {
                getRulerDialog(0).setVisible(true);
            } else if (command.equalsIgnoreCase("sangle")) {
                dp.SetCurrentAction(drawProcess.SANGLE);
                try {
                    int n = 0;
                    ps = language.getEnglish(ps);
                    if (ps.equalsIgnoreCase("Other..")) {
                        String s = JOptionPane.showInputDialog(this, this.getLanguage(1053, "Please input the value of the angle"));
                        if (s == null)
                            s = "0";
                        n = Integer.parseInt(s);
                    } else
                        n = Integer.parseInt(ps);
                    dp.setcurrentStatus(n);
                } catch (NumberFormatException ee) {
                    JOptionPane.showMessageDialog(this, ee.getMessage(), "Information", JOptionPane.WARNING_MESSAGE);
                }
            } else if (command.equalsIgnoreCase("equal ratio")) {
                dp.SetCurrentAction(drawProcess.RATIO);
            } else if (command.equalsIgnoreCase("RAMark"))
                dp.SetCurrentAction(drawProcess.RAMARK);
            else if (command.equalsIgnoreCase("Transform"))
                dp.SetCurrentAction(drawProcess.TRANSFORM);
            else if (command.equalsIgnoreCase("Equivalence"))
                dp.SetCurrentAction(drawProcess.EQUIVALENCE);
            else if (command.equalsIgnoreCase("Free Transform"))
                dp.SetCurrentAction(drawProcess.FREE_TRANSFORM);
            else if (command.equalsIgnoreCase("Calculation")) {
                TextValueEditor dlg = new TextValueEditor(this);
                this.centerDialog(dlg);
                dlg.setVisible(true);
            }

        }
    }

    int[] parse2Int(String s) {
        String[] sl = s.split(":");
        int[] t = new int[2];
        try {
            t[0] = Integer.parseInt(sl[0].trim());
            t[1] = Integer.parseInt(sl[1].trim());
        } catch (NumberFormatException ee) {
            t[0] = 1;
            t[1] = 1;
        }
        return t;
    }

    public void setBKState() {
        int n1 = dp.getUndolistSize();
        int n2 = dp.getRedolistSize();
        if (n1 == 0 && n2 == 0) {
            BK2.setEnabled(true);
            BK4.setEnabled(true);
            BK1.setEnabled(true);
            BK3.setEnabled(true);
        } else {
            BK2.setEnabled(n1 != 0);
            BK4.setEnabled(n1 != 0);
            BK1.setEnabled(n2 != 0);
            BK3.setEnabled(n2 != 0);
        }
    }

    public boolean saveBeforeExit() {
        if (dp.need_save() && CMisc.needSave()) {
            int n = JOptionPane.showConfirmDialog(this, "The diagram has been changed, do you want to save?",
                    "Save", JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == JOptionPane.OK_OPTION) {
                boolean r = saveAFile(false);
                return r;
            } else if (n == JOptionPane.NO_OPTION)
                return true;
            else
                return false;
        }
        return true;
    }

    public boolean need_save() {
        if (!dp.need_save()) {
            this.setTipText(getLanguage(1050, "Nothing to be saved."));
            return false;
        }
        return true;
    }

    public boolean saveAFile(boolean n) {
        File file = dp.getFile();
        int result = 0;

        if (need_save()) {
            if (file == null || n) { //command.equals("Save as...")
                JFileChooser chooser = this.getFileChooser();

                try {
                    if (file != null && file.exists())
                        chooser.setSelectedFile(file);
                    result = chooser.showSaveDialog(this);
                } catch (Exception ee) {
                    filechooser = null;
                    chooser = this.getFileChooser();
                    result = chooser.showSaveDialog(this);
                }

                if (result == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                } else
                    file = null;
            }
            if (file != null)
                try {
                    String path = file.getPath();
                    if (!path.endsWith(".gex")) {
                        path += ".gex";
                    }
                    File f = new File(path);
                    if (f.exists() && get_User_Overwrite_Option(file.getName())) {
                        return false;
                    }
                    saveAFile(path);
                    updateTitle();
                    CMisc.onFileSavedOrLoaded();
                    return true;

                } catch (Exception ee) {
                    ee.printStackTrace();
                    CMisc.print(ee.getMessage() + "\n" + ee.getStackTrace());
                }
        }
        return false;
    }

    public int Clear() {
        int n = 0;
        if (CMisc.isApplication() && !dp.isitSaved()) {
            n = JOptionPane.showConfirmDialog(this, getLanguage(1000, "The diagram has been changed, do you want to save it?"),
                    getLanguage("Save"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (n == JOptionPane.YES_OPTION) {
                if (!saveAFile(false))
                    return 2;
            } else if (n == JOptionPane.NO_OPTION) {
//                if (!saveAFile(true))
//                    return 2;
            } else {
                return 2;
            }
        }
        this.resetAllButtonStatus();
        CCoBox.resetAll();
        this.setActionMove();
        dp.clearAll();
        d.clearAll();
        if (pprove != null) {
            pprove.finishedDrawing();
        }
        updateTitle();
        scroll.revalidate();
        d.repaint();
        provePanelbar.setVisible(false);
        closeAllDialogs();

        return 0;
    }

    public void closeAllDialogs() {
        if (propt != null)
            propt.setVisible(false);
        if (sdialog != null)
            sdialog.setVisible(false);
        if (udialog != null)
            udialog.setVisible(false);
        if (pdialog != null)
            pdialog.setVisible(false);
        if (cdialog != null)
            cdialog.setVisible(false);
        if (rdialog != null)
            rdialog.setVisible(false);
        if (ndialog != null)
            ndialog.setVisible(false);
        if (adialog != null)
            adialog.setVisible(false);
//        removeAllDependentDialogs();
    }

    public void setDrawCursor(int t) {
        d.setCursor(Cursor.getPredefinedCursor(t));
    }

    public void setDrawCursor(String s) {
        d.setCursor(this.getDefinedCursor(s));
    }

    public void reloadLP() {
        if (lp != null && udialog != null && udialog.isVisible())
            lp.reload();
    }

    public boolean get_User_Overwrite_Option(String name) {
        if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(this,
                name + getLanguage(1002, " already exists, do you want to overwrite it?"),
                getLanguage(1001, "File exists"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)) {
            return true;
        }
        return false;
    }

    public void saveAFile(String path) throws IOException {
        DataOutputStream out = dp.openOutputFile(path);
        dp.Save(out);
        pprove.SaveProve(out);
        out.close();
    }

    public void saveProofAsGIF() {
        if (provePanelbar == null)
            return;
        if (!need_save())
            return;

        RectChooser1 r1 = new RectChooser1(this);
        this.centerDialog(r1);
        r1.setVisible(true);
        if (!r1.getResult())
            return;
        Rectangle rc = r1.getRectangle();


        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new JFileFilter("GIF"));

        String dr1 = getUserDir();
        chooser.setCurrentDirectory(new File(dr1));

        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }
        String dr = getUserDir();
        chooser.setCurrentDirectory(new File(dr));

        File ff = chooser.getSelectedFile();
        String p = ff.getPath();
        if (!p.endsWith("gif") && !p.endsWith("GIF")) {
            p = p + ".gif";
            ff = new File(p);
        }
        try {
            DataOutputStream out = dp.openOutputFile(ff.getPath());
            GifEncoder e = new GifEncoder();
            e.setQuality(20);
            e.start(out);
            e.setRepeat(0);
            e.setDelay(200);   // 1 frame per sec

            ImageTimer t = new ImageTimer(this);
            t.setEncorder(e);
            t.setRectangle(rc);

            t.setProveBar(provePanelbar);
            t.setDelay(200);
            t.setVisible(true);
            e.finish();
            out.close();

        } catch (Exception ee) {
            ee.printStackTrace();
        }


    }

    public void saveAsGIF() {

        if (!need_save())
            return;
        AnimateC am = dp.getAnimateC();
        if (am == null) {
            JOptionPane.showMessageDialog(this, getLanguage(2301, "No animation has been defined.") + "\n"
                    + getLanguage(2302, "Please use the menu \" Action -> Animation \" to define an animation first."), "GIF", JOptionPane.WARNING_MESSAGE);
            return;
        }
        am = new AnimateC(am);


        GIFOptionDialog dlg = new GIFOptionDialog(this, getLanguage(2303, "GIF Option"));
        this.centerDialog(dlg);
        dlg.setDefaultValue(20);
        dlg.setVisible(true);
        if (!dlg.getReturnResult())
            return;
        int q = dlg.getQuality();

        Rectangle rect = null;
        RectChooser rchoose = new RectChooser(this);
        if (rchoose.getReturnResult()) {
            rect = rchoose.getSelectedRectangle();
        } else
            return;

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new JFileFilter("GIF"));
        String dr = getUserDir();
        chooser.setCurrentDirectory(new File(dr));

        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }


        File ff = chooser.getSelectedFile();
        String p = ff.getPath();
        if (!p.endsWith("gif") && !p.endsWith("GIF")) {
            p = p + ".gif";
            ff = new File(p);
        }


        am.reClaclulate();
        int n = am.getRounds();
        if (n == 0) return;

        int v = 1000 / am.getInitValue();

        GifProcessDialog dlg1 = new GifProcessDialog(this.getFrame());
        this.centerDialog(dlg1);
        dlg1.setTotal(n);

        int k = 0;

        try {
            DataOutputStream out = dp.openOutputFile(ff.getPath());
            GifEncoder e = new GifEncoder();
            e.setQuality(q);
            e.start(out);
            e.setRepeat(0);
            e.setDelay(v);   // 1 frame per sec
            dlg1.en = e;
            dlg1.dp = dp;
            dlg1.rect = rect;
            dlg1.am = am;
            dlg1.gxInstance = this;
            dlg1.out = out;
            dlg1.setVisible(true);
            dlg1.setRun();

//            while (n >= 0) {
//                am.onTimer();
//                if (!dp.reCalculate()) {
//                    am.resetXY();
//                }
//                e.addFrame(this.getBufferedImage(rect));
//                n--;
//            }
//            e.finish();
//            out.close();

        } catch (IOException ee) {
            System.out.println(ee.getMessage());
        }


    }


    public void saveAsImage() {

        if (!need_save())
            return;

        Rectangle rect = null;
        RectChooser rchoose = new RectChooser(this);
        if (rchoose.getReturnResult()) {
            rect = rchoose.getSelectedRectangle();
        } else
            return;

        JFileChooser chooser = new JFileChooser();
        String[] s = ImageIO.getWriterFormatNames();
        String[] s1 = new String[s.length + 1];
        for (int i = 0; i < s.length; i++)
            s1[i] = s[i];
        s1[s.length] = "gif";
        s = s1;

        if (s.length > 0) {
            FileFilter t = chooser.getFileFilter();
            chooser.removeChoosableFileFilter(t);

            JFileFilter selected = null;
            for (int i = 0; i < s.length; i++) {
                JFileFilter f = new JFileFilter(s[i]);
                chooser.addChoosableFileFilter(f);

                if (s[i].equalsIgnoreCase("JPG"))
                    selected = f;
                if (selected == null && s[i].equalsIgnoreCase("JPEG"))
                    selected = f;
            }
            chooser.setFileFilter(selected);
        }
        String dr = getUserDir();
        chooser.setCurrentDirectory(new File(dr));

        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File ff = chooser.getSelectedFile();
        FileFilter f = chooser.getFileFilter();
        String endfix = f.getDescription();
        if (endfix == null)
            return;

        String p = ff.getPath();
        if (!p.endsWith(endfix)) {
            p = p + "." + endfix;
            ff = new File(p);
        }

        if (endfix.equals("gif")) {
            try {
                DataOutputStream out = dp.openOutputFile(ff.getPath());
                GifEncoder e = new GifEncoder();
                e.setQuality(1);
                e.start(out);
                e.setRepeat(0);
                e.setDelay(0);
                e.addFrame(this.getBufferedImage(rect));
                e.finish();
                out.close();
            } catch (IOException ee) {
                if (CMisc.isDebug())
                    ee.printStackTrace();
                else JOptionPane.showMessageDialog(this, ee.getMessage(), "Information", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            BufferedImage image = getBufferedImage(rect);
            Iterator iter = ImageIO.getImageWritersByFormatName(endfix);
            ImageWriter writer = (ImageWriter) iter.next();
            try {
                ImageOutputStream imageOut = ImageIO.createImageOutputStream(ff);
                writer.setOutput(imageOut);

                writer.write(new IIOImage(image, null, null));
                IIOImage iioImage = new IIOImage(image, null, null);
                if (writer.canInsertImage(0))
                    writer.writeInsert(0, iioImage, null);
                imageOut.close();
            }
            catch (IOException exception) {
                if (CMisc.isDebug())
                    exception.printStackTrace();
                else
                    JOptionPane.showMessageDialog(this, exception.getMessage(), "Information", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    public BufferedImage getBufferedImage(Rectangle rc) {
        BufferedImage image = new BufferedImage((int) rc.getWidth(), (int) rc.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(-rc.getX(), -rc.getY());
        d.paintComponent(g2);
        return image;
    }

    public BufferedImage getBufferedImage2(Rectangle rc) {
        BufferedImage image = new BufferedImage((int) rc.getWidth(), (int) rc.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(-rc.getX(), -rc.getY());
        contentPane.paint(g2);
        return image;
    }


    public boolean openAOnlineFile(String path, String pname) {

        if (2 == this.Clear()) return false;  // cancel option.

        if (CMisc.isApplication())
            return false;

        Object o = (Object) this;
        JApplet a = (JApplet) o;

        URL uc = a.getDocumentBase();

        URL ul;
        try {
            if (pname.length() == 0)
                ul = new URL(uc, "examples/" + path + ".gex");
            else
                ul = new URL(uc, "examples/" + path + '/' + pname + ".gex");//s + pname + System.getProperty("file.separator") + ps) ;

            URLConnection urlc = ul.openConnection();
            urlc.connect();
            InputStream instream = urlc.getInputStream();
            DataInputStream in = (new DataInputStream(instream));
            dp.Load(in);

            if (CMisc.version_load_now < 0.035) {
                this.showppanel(true);
            } else if (CMisc.version_load_now == 0.035) {
                mnode n = new mnode();
                n.Load(in, dp);
                pprove.loadMTree(n);
                this.showppanel(false);
            } else if (CMisc.version_load_now >= 0.036) {
                pprove.LoadProve(in);
            }
            in.close();
            dp.stopUndoFlash();
        } catch (IOException ee) {
            //CMisc.eprint(this, ee.toString() + "\n" + ee.getStackTrace());
            StackTraceElement[] tt = ee.getStackTrace();

            String s = ee.toString();
            for (int i = 0; i < tt.length; i++) {
                if (tt[i] != null)
                    s += tt[i].toString() + "\n";
            }
            JOptionPane.showMessageDialog(this, s);
            System.out.println(s);
        }
        return false;
    }


    public DataOutputStream openOutputFile(String s) {

        String s1 = getUserDir();
        String s2 = getFileSeparator();

        try {
            FileOutputStream fp;
            File f = new File(s1 + s2 + s);

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
        } catch (IOException e) {
        }
        return null;
    }

    public boolean openAFile(File file) {
        String path = file.getPath();
        File f = new File(path);
        if (!f.exists() && !path.endsWith(".gex"))
            path += ".gex";
        f = new File(path);


        try {
            if (f.exists()) {
                boolean r = true;
                if (2 == this.Clear()) // cancel option.
                    return false;

                if (f.getName().endsWith("gex")) {
                    dp.clearAll();
                    dp.setFile(f);
                    DataInputStream in = dp.openInputFile(f.getPath());
                    r = dp.Load(in);

                    if (CMisc.version_load_now < 0.035) {
                        this.showppanel(true);
                    } else if (CMisc.version_load_now == 0.035) {
                        mnode n = new mnode();
                        n.Load(in, dp);
                        pprove.loadMTree(n);
                        this.showppanel(false);
                    } else if (CMisc.version_load_now >= 0.036) {
                        pprove.LoadProve(in);
                    }
                    in.close();
                    dp.stopUndoFlash();
                    dp.reCalculate();
                } else {
                    r = pprove.load(f);
                    if (r)
                        showppanel(false);
                    else return r;
                }
                dp.setName(file.getName());
                CMisc.version_load_now = 0;
                CMisc.onFileSavedOrLoaded();
                updateTitle();
                return r;
            } else return false;
        } catch (IOException ee) {
            StackTraceElement[] tt = ee.getStackTrace();

            String s = ee.toString();
            for (int i = 0; i < tt.length; i++) {
                if (tt[i] != null)
                    s += tt[i].toString() + "\n";
            }
            System.out.println(s);
//            CMisc.print(ee.toString() + "\n" + ee.getStackTrace());
//            JDialog dlg = new JDialog(this.getFrame());
//            JTextPane t= new JTextPane();
//            t.setText(ee.getStackTrace().toString());
//            ee.printStackTrace();
        }
        return false;
    }

    protected void addRightButtons(JToolBar toolBar) {
        JToggleButton button = null;

        button = makeAButton("construct_history", "Construct History",
                "construct history", "construct history", true);
        toolBar.add(button);

        button = makeAButton("translate", "translate", "translate view", "Translate");
        toolBar.add(button);
        group.add(button);
        button = makeAButton("zoom-in", "zoom-in", "Zoom in view", "Zoom-in");
        toolBar.add(button);
        group.add(button);
        button = makeAButton("zoom-out", "zoom-out", "Zoom out view", "Zoom-out");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("snap", "snap", "snap to grid", "snap");
        toolBar.add(button);
        button = makeAButton("grid", "grid", "draw the rectangle grid", "grid");
        toolBar.add(button);
        button = makeAButton("lessGrid", "lessgrid", "make the grid less dense", "lessGrid");
        toolBar.add(button);
        button = makeAButton("moreGrid", "moregrid", "make the grid more dense", "moreGrid");
        toolBar.add(button);

        BK1 = button = makeAButton("redo", "redo", "redo a step", "redo", true);
        toolBar.add(button);
//       // KeyStroke ctrlP = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK);
        //  button.setAccelerator(ctrlP);

        BK2 = button = makeAButton("undo", "undo", "undo a step", "undo", true);
        toolBar.add(button);
        BK3 = button = makeAButton("ff", "ff", "forward to end", "ff", true);
        toolBar.add(button);
        BK4 = button = makeAButton("fr", "fr", "back to start", "fr", true);
        toolBar.add(button);

        button = makeAButton("autoshowstep", "autoshowstep", "auto show draw step by step", "play");
        toolBar.add(button);

        anButton = button =
                makeAButtonWith2ICon("animate_start", "animate_stop", "autoanimate", "start to animate", "play");
        anButton.setToolTipText(this.getLanguageTip("animate_start"));
        toolBar.add(button);
        button.setEnabled(false);
    }

    protected void addButtons(JToolBar toolBar) {
        JToggleButton button = null;
        //ButtonGroup group = new ButtonGroup();

        button = makeAButton("new", "New", "create a new view", "new", true);
        toolBar.add(button);
        //group.add(button);
        toolBar.add(Box.createHorizontalStrut(1));

        button = makeAButton("open", "Open", "Open a file", "open", true);
        //group.add(button);
        toolBar.add(button);

        button = makeAButton("save", "Save", "Save to a file", "save", true);
        //group.add(button);
        toolBar.add(button);

        button = makeAButton("select", "Select", "Select mode", "Select");
        toolBar.add(button);
        buttonSelect = button;
        group.add(button);

        button = makeAButton("drag", "Move", "Move", "move");
        toolBar.add(button);
        group.add(button);
        buttonMove = button;

        button = makeAButton("point", "Point", "Add a single point", "point");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("line", "Line", "Select two points to construct a line", "line");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("parallel", "Parallel", "Draw a line which is parallel to another line", "parallel");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("perp", "Perpendicular", "Draw a line which is perpdicular to another line", "perpendicular");
        toolBar.add(button);
        group.add(button);
//        button = makeAButton("abline", "Abline", "Select two lines to construct the bisector of an angle", "abline");
//        toolBar.add(button);
//        group.add(button);

        button = makeAButton("foot", "Foot",
                "Select a point and a line to construct the foot", "foot");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("circle", "Circle", "Draw two points and a circle", "circle");
        button.setToolTipText("Click a point then drag to construct a circle");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("circle3p", "Circle by Three Points",
                "Select three points to construct the circle passing through them", "circle3p");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("circler", "Circler", "Draw a circle with center and radius", "circler");
        button.setToolTipText("Construct a circle by clicking two points as radius and another point as center");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("fillpolygon", "Fill Polygon", "define an polygon", "polygon");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("angle", "Angle", "Select two lines to define their full-angle with a label", "angle");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("text", "Text", "Add text", "text");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("intersect", "Intersect", "Take the intersection of two objects (circle or line)", "intersect");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("mirror", "Mirror", "Mirror a object by clicking and then click a reflection axis or point", "reflectr");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("iso", "Isosceles Triangle", "Select two points or drag a segment to construct an isosceles triangle", "isosceles triangle");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("midpoint", "Midpoint", "Click two points to get their midpoint", "midpoint");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("square", "Square", "Select two points or drag a segment to construct a square", "square");
        toolBar.add(button);
        group.add(button);

        button = makeAButton("triangle", "Triangle", null, "triangle");
        toolBar.add(button);
        group.add(button);


        button = makeAButton("polygon", "Polygon", null, "polygon");
        toolBar.add(button);
        button.removeActionListener(this);
        group.add(button);


        JToggleButton b1 = null;
        {
            String imgLocation = "images/dselect.gif";
            URL imageURL = GExpert.class.getResource(imgLocation);
            Icon co = null;
            if (imageURL != null) {
                co = (new ImageIcon(imageURL, ""));
            }
            b1 = new JToggleButton(co) {

                public Dimension getPreferredSize() {
                    return new Dimension(10, 28);
                }
            };
            b1.setUI(new EntityButtonUI(1));
            toolBar.add(b1);
        }


        JPopButtonsPanel p = new JPopButtonsPanel(button, b1);
        button = makeAButton("triangle", "Triangle", null, "triangle");
        p.add(button);
        group.add(button);

        button = makeAButton("triangle_iso", "Isosceles Triangle", null, "isosceles triangle");
        p.add(button);
        group.add(button);

        button = makeAButton("triangle_all", "Equilateral Triangle", null, "equilateral triangle");
        p.add(button);
        group.add(button);

        button = makeAButton("triangle_perp", "Tri_perp", null, "triangle");
        p.add(button);
        group.add(button);

        button = makeAButton("quadrangle", "Quadrangle", null, "quadrangle");
        p.add(button);
        group.add(button);
        button = makeAButton("parallelogram", "Parallelogram", null, "pentagon");
        p.add(button);
        group.add(button);
        button = makeAButton("trapezoid", "Trapezoid", null, "trapezoid");
        p.add(button);
        group.add(button);
        button = makeAButton("ra_trapezoid", "RA_trapezoid", null, "right angle trapezoid");
        p.add(button);
        group.add(button);
        button = makeAButton("rectangle", "Rectangle", null, "rectangle");
        p.add(button);
        group.add(button);
        button = makeAButton("quadrangle_square", "Square", null, "square");
        p.add(button);
        group.add(button);

        button = makeAButton("pentagon", "Pentagon", null, "pentagon");
        p.add(button);
        group.add(button);
        button = makeAButton("polygon", "Polygon", null, "polygon");
        p.add(button);
        group.add(button);
        p.setSelectedButton(button);
    }


    private ActionListener listener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            JToggleButton button = (JToggleButton) e.getSource();
            button.getModel().setSelected(false);
        }
    };

    protected JToggleButton makeAButton(String imageName,
                                        String actionCommand,
                                        String toolTipText,
                                        String altText, boolean t) {
        JToggleButton button = makeAButton(imageName, actionCommand,
                toolTipText, altText);
        if (t) {
            button.addActionListener(listener);
        }
        return button;
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


        String s2 = getLanguageTip(actionCommand);
        if (s2 != null && s2.length() != 0)
            button.setToolTipText(s2);
        else {
            String s1 = getLanguage(actionCommand);
            if (toolTipText == null && s1 != null && s1.length() != 0)
                button.setToolTipText(s1);
        }
        button.addActionListener(this);

        button.setText(null);
//        button.setBorder(BorderFactory.createEmptyBorder(0,2,0,2));
        return button;
    }


    protected JToggleButton makeAButtonWith2ICon(String imageName,
                                                 String imageNameSelected,
                                                 String actionCommand,
                                                 String toolTipText,
                                                 String altText) {

        String imgLocation = "images/" + imageName + ".gif";
        URL imageURL = GExpert.class.getResource(imgLocation);

        Icon icon1, icon2;
        icon1 = icon2 = null;

        if (imageURL != null) {
            icon1 = (new ImageIcon(imageURL, altText));
        }

        imgLocation = "images/" + imageNameSelected + ".gif";
        imageURL = GExpert.class.getResource(imgLocation);

        if (imageURL != null) {
            icon2 = (new ImageIcon(imageURL, altText));
        }

        DActionButton button = new DActionButton(icon1);
        button.set2StatusIcons(icon1, icon2);
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        return button;
    }


    public void resetAllButtonStatus() {

        if (lp != null) {
            lp.clearAllTrees();
        }

        if (afpane != null)
            afpane.setVisible(false);

        if (aframe != null) {
            aframe.stopA();
        }
        if (anButton != null) {
            anButton.setEnabled(false);
        }
        restorScroll();
        pprove.clearAll();
        d.setCursor(Cursor.getDefaultCursor());
        BK1.setEnabled(true);
        BK2.setEnabled(true);
        BK3.setEnabled(true);
        BK4.setEnabled(true);
    }

//    public void setTitle(String title) {
//        if (CMisc.isApplet()) {
//        } else {
//            JFrame f = (JFrame) (Object) this;
//            f.setTitle(title);
//        }
//    }


    public void updateTitle() { // APPLET ONLY.
        if (!CMisc.isApplication())
            return;

        String s = dp.getName();
        JFrame frame = (JFrame) (Object) this;

        String v = Version.getProject();
        String d = Version.getData();

        v = this.getLanguage(v);

        if (s != null && s.length() != 0)
            frame.setTitle(s + "  -  " + v);
        else
            frame.setTitle(v);

    }

    public void restorScroll() {
        Rectangle rc = new Rectangle(0, 0, 0, 0);
        scroll.scrollRectToVisible(rc);
        d.setPreferredSize(new Dimension(100, 100));
        d.revalidate();
    }

    public void setActionTip(String name, String tip) {
        if (pprove.isProverRunning())
            return;

        label.setText(" " + name);
        if (tip != null)
            label2.setText(" " + tip);
        else
            label2.setText("");
    }

    private Timer timer;
    private int n = 4;
    private static Color fcolor = new Color(128, 0, 0);

    public void setTextLabel2(String s, int n) {
        setTextLabel2(s);
        this.n = n;
    }

    public void setLabelText2(String s) {
        label2.setText(" " + s);
    }

    public void stopTimer() {
        if (timer != null)
            timer.stop();
        n = 0;
        label2.setForeground(fcolor);
    }

    public void setTextLabel2(String s) {
        label2.setText(" " + s);
        if (timer == null && s != null && s.length() != 0) {
            timer = new Timer(200, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (n % 2 == 0) {
                        label2.setForeground(fcolor);
                    } else {
                        label2.setForeground(Color.lightGray);
                    }
                    if (n == 0) {
                        timer.stop();
                        timer = null;
                        label2.setForeground(Color.black);
                    }
                    n--;

                }
            });
            n = 8;
            timer.start();
        }

    }

    public void setTipText(String text) {
        this.setTextLabel2(text);
    }

    public void Animate(int type) {
        d.onAnimate(type);
    }


    public void dragEnter(DropTargetDragEvent dtde) {
        // System.out.println("Drag Enter");
    }

    public void dragExit(DropTargetEvent dte) {
        //System.out.println("Drag Exit");
    }

    public void dragOver(DropTargetDragEvent dtde) {
        // System.out.println("Drag Over");
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        // System.out.println("Drop Action Changed");
    }

    public void drop(DropTargetDropEvent dtde) {
        try {
            // Ok, get the dropped object and try to figure out what it is
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                // Check for file lists specifically
                if (flavors[i].isFlavorJavaFileListType()) {
                    // Great!  Accept copy drops...
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

                    // And add the list of file names to our text area
                    java.util.List list = (java.util.List) tr.getTransferData(flavors[i]);

                    if (list.size() == 0)
                        continue;
                    String path = list.get(0).toString();
                    // If we made it this far, everything worked.
                    dtde.dropComplete(true);

                    // Open the target file.
                    File file = new File(path);
                    if (file.isDirectory())
                        continue;
                    this.openAFile(file);

                    //Open the first file for JGEX and return.
                    return;
                }
                // Ok, is it another Java object? Currently not implemented for this. 
                else if (flavors[i].isFlavorSerializedObjectType()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    Object o = tr.getTransferData(flavors[i]);
                    dtde.dropComplete(true);
                    return;
                }
                // How about an input stream?  Currently not implemented for this. 
                else if (flavors[i].isRepresentationClassInputStream()) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    dtde.dropComplete(true);
                    return;
                }
            }
            // Hmm, the user must not have dropped a file list
            dtde.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        if (saveBeforeExit())
            System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {

    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////


    class JPopButtonsPanel extends JPopupMenu implements ActionListener, MouseListener, ItemListener, PopupMenuListener {
        JToggleButton button, b2, bselect;
        Vector vlist = new Vector();
        boolean entered = false;

        public JPopButtonsPanel(JToggleButton button, JToggleButton b2) {
            this.setLayout(new GridLayout(4, 3, 2, 2));
            this.button = button;
            this.b2 = b2;


            button.addActionListener(this);
            b2.addActionListener(this);
            button.addItemListener(JPopButtonsPanel.this);
            button.addMouseListener(JPopButtonsPanel.this);
            b2.addMouseListener(JPopButtonsPanel.this);
            this.addMouseListener(JPopButtonsPanel.this);
            this.addPopupMenuListener(this);
        }

        public void setSelectedButton(JToggleButton b) {
            bselect = b;
        }

        public void itemStateChanged(ItemEvent e) {
            int n = e.getStateChange();
            if (n == ItemEvent.SELECTED) {
                b2.setSelected(true);
            } else {
                b2.setSelected(false);
                b2.getModel().setRollover(false);
                button.getModel().setRollover(false);
            }
        }

        public void add(JToggleButton b) {
            b.addActionListener(this);
            vlist.add(b);
            super.add(b);
        }

        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            b2.getModel().setRollover(true);
            b2.getModel().setSelected(true);
        }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        public void popupMenuCanceled(PopupMenuEvent e) {
            b2.getModel().setRollover(button.getModel().isRollover());
            b2.getModel().setSelected(button.getModel().isSelected());
        }


        public void actionPerformed(ActionEvent e) {
            JToggleButton bt = (JToggleButton) e.getSource();

            Object o = e.getSource();
            if (o == b2) {
                for (int i = 0; i < vlist.size(); i++) {
                    JToggleButton b = (JToggleButton) vlist.get(i);
                    if (b != bselect)
                        b.getModel().setRollover(false);
                }
                this.show(button, 0, button.getHeight());
                b2.setSelected(true);
            } else if (o == button) {
                button.setSelected(true);
                if (bselect != null)
                    GExpert.this.sendAction(bselect.getActionCommand(), bselect);
            } else {
                JToggleButton b = (JToggleButton) e.getSource();
                this.setVisible(false);
                button.setIcon(b.getIcon());
                button.setSelected(true);
                bselect = b;
            }
            bt.repaint();
        }

        public void mouseClicked(MouseEvent e) {
        }


        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
            Object o = e.getSource();
            if (o == b2 || o == button) {
                b2.getModel().setRollover(true);
                button.getModel().setRollover(true);
            }
        }

        public void mouseExited(MouseEvent e) {
            Object o = e.getSource();
            if (o == b2 || o == button) {
                if (!b2.isSelected() && !button.isSelected()) {
                    b2.getModel().setRollover(false);
                    button.getModel().setRollover(false);
                }
            }
        }
    }


    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GExpert.class.getResource(path);
        if (imgURL == null) {
            return null;
        }
        return new ImageIcon(imgURL);
    }

    public static URL getResourceURL(String path) {
        return GExpert.class.getResource(path);
    }

    private static void createAndShowGUI() { // APPLET ONLY.

        Locale.setDefault(Locale.ENGLISH);

        GExpert exp = new GExpert();
        if (!CMisc.isApplet()) {


            JFrame frame = (JFrame) (Object) exp;
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.pack();


            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setSize(1200, 900);

            frame.setLocation((int) (screenSize.getWidth() - 1200) / 2,
                    (int) (screenSize.getHeight() - 900) / 2); //center
            frame.setVisible(true);
        }
    }

    public static void setLookAndFeel() {

        if (CMisc.isApplet())
            return;

        try {
            String f = CMisc.lookAndFeel;
            if (f != null && f.length() != 0 && !f.equals("Default")) {
                UIManager.LookAndFeelInfo[] ff = UIManager.getInstalledLookAndFeels();
                for (int i = 0; i < ff.length; i++) {
                    if (ff[i].getName().equals(f)) {
                        UIManager.setLookAndFeel(ff[i].getClassName());
                        break;
                    }
                }
            }
        } catch (Exception evt) {
        }

    }

    public static void main(String[] args) {
//        args[0] = "AAAAAAAAAAAAA";
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    public static void openURL(String url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Mac OS")) {
                Class fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL",
                        new Class[]{String.class});
                openURL.invoke(null, new Object[]{url});
            } else if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else { //assume Unix or Linux
                String[] browsers = {
                        "firefox", "opera", "konqueror", "epiphany",
                        "mozilla", "netscape"};
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null;
                     count++) {
                    if (Runtime.getRuntime().exec(new String[]{"which", browsers[count]}).waitFor() == 0) {
                        browser = browsers[count];
                    }
                }
                if (browser == null) {
                    throw new Exception("Could not find web browser");
                } else {
                    Runtime.getRuntime().exec(new String[]{browser, url});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not open Link " + url + "\n" +
                    e.getMessage());
        }
    }

    public void saveAsPDF() {
        if (!need_save())
            return;

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;

                String s = f.getName();
                if (s.endsWith("pdf") || s.endsWith("PDF"))
                    return true;
                return false;
            }

            public String getDescription() {
                return "Adobe PDF File (*.pdf)";
            }
        });
        String dr = getUserDir();
        chooser.setCurrentDirectory(new File(dr));
        int n = chooser.showOpenDialog(this);
        if (n != JFileChooser.OPEN_DIALOG)
            return;

        try {
            File file = chooser.getSelectedFile();
            String path = file.getPath();
            if (path.endsWith("PDF") || path.endsWith("pdf")) {
            } else {
                file = new File(path + ".pdf");
            }
            if (file.exists()) {
                int n2 = JOptionPane.showConfirmDialog(this, file.getName()
                        + " already exists, do you want to overwrite?",
                        "File Exists", JOptionPane.YES_NO_CANCEL_OPTION);
                if (n2 != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);


            Graphics pdfGraphics = null;
            PDFJob job = new PDFJob(fileOutputStream);
            pdfGraphics = job.getGraphics();
            d.paintAll(pdfGraphics);
            pdfGraphics.dispose();
            job.end();
            fileOutputStream.close();
        } catch (IOException ee) {
            JOptionPane.showMessageDialog(this, ee.getMessage());
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int k = 0;
    }

    public void keyReleased(KeyEvent e) {
    }


    class Group extends ButtonGroup {
        public Group() {
            super();
        }

        public AbstractButton getButton(String s) {
            if (s == null || s.length() == 0)
                return null;

            int n = buttons.size();
            for (int i = 0; i < buttons.size(); i++) {
                AbstractButton b = (AbstractButton) buttons.get(i);
                String s1 = b.getActionCommand();
                if (s.equals(s1))
                    return b;
            }
            return null;
        }
    }

//    public void addDependentDialog(JDialog dlg) {
//        if (dlg != null && !depdlglist.contains(dlg))
//            depdlglist.add(dlg);
//    }

//    private void removeAllDependentDialogs() {
//        for (int i = 0; i < depdlglist.size(); i++) {
//            JDialog dlg = (JDialog) depdlglist.get(i);
//            dlg.setVisible(false);
//        }
//        depdlglist.clear();
//    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {

        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    public void start() {
    }

    public void stop() {
    }

    public void destroy() {
    }


    public void updateActionPool(int n) {
        Vector v = dp.getSelectList();
        int nx = vpoolist.size();

        if (v.size() != 0) {
            for (int i = 0; i < v.size() && i < nx; i++) {
                OPoolabel lb = (OPoolabel) vpoolist.get(i);
                lb.setObject((CClass) v.get(i));
            }
        } else setActionPool(n);

    }

    public void setActionPool(int a) {
        int n = dp.getPooln(a);
        int sz = vpoolist.size();
        if (n <= 0) {
            for (int i = 0; i < sz; i++) {
                JLabel label = (JLabel) vpoolist.get(i);
                label.setVisible(false);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i < sz) {
                OPoolabel lb = (OPoolabel) vpoolist.get(i);
                lb.setType(dp.getPoolA(a, i + 1));
                lb.setVisible(true);
            } else {
                OPoolabel lb = new OPoolabel();
                vpoolist.add(lb);
                tipanel.add(lb);
                lb.setType(dp.getPoolA(a, i + 1));
            }
        }

        if (n > 0 && n < sz) {
            for (int i = n; i < sz; i++) {
                OPoolabel lb = (OPoolabel) vpoolist.get(i);
                lb.setVisible(false);
            }
        }
    }

    public void updateJGEX() {
        Update up = new Update(this);
        if (up.updateJGEX()) {
        }
    }


    class OPoolabel extends JLabel implements MouseListener {
        private int otype = -1;
        private Object obj;
        private Color bc = this.getForeground();


        public OPoolabel() {
            this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Font f = this.getFont();
            f = new Font(f.getName(), Font.BOLD, f.getSize());
            this.setFont(f);
        }

        public void setType(int t) {
            otype = t;

            if (t == 1) {
                this.setText("P ");
                this.setToolTipText("Point");
            } else if (t == 2) {
                this.setText("L ");
                this.setToolTipText("Line");
            } else if (t == 3) {
                this.setText("C ");
                this.setToolTipText("Circle");
            } else if (t == 4) {
                this.setText("LC");
                this.setToolTipText("Line or Circle");
            } else {
                this.setText("?");
                this.setToolTipText("Anything");
            }
            this.setForeground(bc);
        }

        public void setObject(CClass cc) {
            if (cc == null /*|| !(cc instanceof CPoint)*/) {
                return;
            }
            String na = cc.getname();
            if (na == null)
                na = " ";
            if (na.length() == 1)
                na += " ";
            setText(na);
            obj = cc;
            this.setToolTipText(cc.TypeString());
            this.setForeground(new Color(0, 128, 192));
        }

        public void clear() {
            this.setText("");
            this.setToolTipText("");
            obj = null;
        }

        public void mouseClicked(MouseEvent e) {
            if (obj != null) {
                GExpert.this.dp.setObjectListForFlash((CClass) obj);
            }

        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

    }

    public void onKeyCancel() {
        dp.cancelCurrentAction();
        //this.setActionMove();
//        closeAllDialogs();
    }

    public void initKeyMap() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(new KeyProcessor());
    }

    public static long HotKeyTimer = 0;

    class KeyProcessor implements KeyEventPostProcessor {
        public boolean postProcessKeyEvent(KeyEvent event) {
            int key = event.getKeyCode();
            long t = System.currentTimeMillis() - HotKeyTimer;
            HotKeyTimer = System.currentTimeMillis();
            if (t < 100)   // Capture in case it run to fast
                return true;

            switch (key) {
                case KeyEvent.VK_ESCAPE:
                    onKeyCancel();
                    break;
                case KeyEvent.VK_S:
                    if (event.isShiftDown())
                        dp.stateChange();
                    break;
                case KeyEvent.VK_Z:
                    if (event.isShiftDown() && event.isControlDown())
                        dp.redo_step();
                    else if (event.isControlDown())
                        dp.Undo_step();
                    d.repaint();
                    break;
                case KeyEvent.VK_PLUS:
                case KeyEvent.VK_EQUALS:
                    if (event.isControlDown()) {
                        dp.zoom_in(d.getWidth() / 2, d.getHeight() / 2, 1);
                        d.repaint();
                    }
                    break;
                case KeyEvent.VK_MINUS:
                    if (event.isControlDown()) {
                        dp.zoom_out(d.getWidth() / 2, d.getHeight() / 2, 1);
                        d.repaint();
                    }
                    break;
                case KeyEvent.VK_G:
                    if (event.isControlDown()) {
                        dp.DRAWGRID = !dp.DRAWGRID;
                        d.repaint();
                    }
                    break;
                case KeyEvent.VK_D:  // For ndgs.
                    if (event.isAltDown()) {
                        dp.printNDGS();
                    }
                    break;
                default:
                    break;
            }

            return true;
        }
    }


}

////////////////////////////////////////////////
////// End of GExpert.java.
///////////////////////////////////////////////


class DActionButton extends ActionButton {
    private Icon ico1, ico2;

    public DActionButton(Icon co) {
        super(co);
    }

    public void set2StatusIcons(Icon ico1, Icon ico2) {
        this.ico1 = ico1;
        this.ico2 = ico2;
    }

    public void setSelected(boolean b) {
        super.setSelected(b);
        if (b) {
            this.setIcon(ico2);
        } else {
            this.setIcon(ico1);
        }

    }

    public void setEnabled(boolean b) {
        super.setEnabled(b);
        this.setIcon(ico1);
        this.setSelected(false);
    }
}


class ActionButton extends JToggleButton {

    private static EntityButtonUI ui = new EntityButtonUI();
    Dimension dm;

    public ActionButton(Icon co) {
        super(co);
        setRolloverEnabled(true);
        this.setOpaque(false);
        this.setUI(ui);
        dm = new Dimension(32, 28);
    }

    public void setButtonSize(int x, int y) {
        dm.setSize(x, y);
    }

    public Dimension getPreferredSize() {
        return dm;
    }

    public Dimension getMaximumSize() {
        return dm;
    }

    //////////////////////////////////////////////////////////////////////


}

class TStateButton extends JToggleButton {
    ImageIcon icon1, icon2;

    public TStateButton(ImageIcon m1, ImageIcon m2) {
        super(m1, false);

        icon1 = m1;
        icon2 = m2;
    }

    public void setSelected(boolean b) {
        if (b) {
            this.setIcon(icon2);
        } else {
            this.setIcon(icon1);
        }

        super.setSelected(b);
    }
}

