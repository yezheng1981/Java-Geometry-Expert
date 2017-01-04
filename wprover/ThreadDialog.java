package wprover;

/**
 * Created by IntelliJ IDEA.
 * User: ye
 * Date: 2008-4-1
 * Time: 15:12:55
 * To change this template use File | Settings | File Templates.
 */

import java.awt.*;
import javax.swing.*;

public class ThreadDialog extends Thread {
    public Thread currentThread = null;//ʵ�ʵ���ʱ����TestThread�������߳�
    private JFrame parentFrame = null;//��ʾ��ĸ�����
    private JDialog clueDiag = null;//   ���߳��������С���ʾ��
    private Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = dimensions.width / 4, height = 60;
    private int left = 0, top = 0;

    boolean pleaseWait = false;


    public void setWaitFinished() {
        pleaseWait = false;
        //      currentThread.notify();

    }

    public ThreadDialog(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        this.currentThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    // Do work
                    synchronized (this) {
                        while (pleaseWait) {
                            try {
                                wait();
                            } catch (Exception e) {
                            }
                        }

                    }

                }
            }
        });

        initDiag();//��ʼ����ʾ��
    }

    protected void initDiag() {
        clueDiag = new JDialog(parentFrame, "���ڼ��أ���ȴ�...", false);

        clueDiag.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        JPanel testPanel = new JPanel();
        testPanel.add(new JLabel("aaaaaaaaaaaaaaa\naaaaaaaaaaaa\naaaaaaaaaaa\naaaa\na\na\na\na"));
        clueDiag.getContentPane().add(testPanel);
        clueDiag.setSize(400, 300);

//       clueDiag.setVisible(true);
//       (new DisposeDiag()).start();//�����ر���ʾ���߳�
    }

    //
    public void run() {
        clueDiag.setVisible(true);
        
        try {
            Thread.sleep(100);
            clueDiag.getContentPane().repaint();

        } catch (Exception e) {
        }

    }

    class DisposeDiag extends Thread {
        public void run() {
            try {
                currentThread.join();//�ȴ��������߳̽���
            }
            catch (InterruptedException e) {
                System.out.println("Exception:" + e);
            }
            clueDiag.dispose();//�ر���ʾ��
        }

    }

//    Thread dd;
//    JDialog dlg;
//
//    public ThreadDialog(Thread d, JDialog dlg) {
//        this.dd = d;
//        this.dlg = dlg;
//
//    }
//
//    public void run() {
//        try {
//            dd.join();//
//        }
//        catch (InterruptedException e) {
//            System.out.println("Exception:" + e);
//        }
//        dlg.dispose();//
//    }

//    JDialog dlg;

    // This method is called when the thread runs
//    public void run
//            () {
//        while (true) {
//            // Do work
//            synchronized (this) {
//                while (pleaseWait) {
//                    try {
//                        wait();
//                    } catch (Exception e) {
//                    }
//                }
//            }
//            dlg.setVisible(false);
//            dlg.dispose();
//            // Do work
//        }
//    }
}