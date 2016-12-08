package wprover;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.URL;


class JExPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, ComponentListener
{
      drawProcess dp;
      static ImageIcon icoa;
      static ImageIcon icos;
      JToggleButton button;
      Timer timer = null;

      public void paint(Graphics g)
      {
          super.paint(g);
          if(dp != null)
              dp.paintPoint(g);
      }
      public JExPanel()
      {
            super();
            this.setLayout(null);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.addComponentListener(this);
            this.setBackground(new Color(228, 236, 252));
            URL imageURL = GExpert.class.getResource("images/animate_start.gif");
            ImageIcon image = new ImageIcon(imageURL);
            URL imageURL1 = GExpert.class.getResource("images/animate_stop.gif");
            ImageIcon image1 = new ImageIcon(imageURL1);

            button = new JToggleButton(image)
            {
                  public Dimension getPreferredSize()
                  {
                        return new Dimension(30, 28);
                  }
            };
            this.add(button);
            button.addActionListener(this);
            Dimension dm = this.getSize();
            button.setBounds(new Rectangle(0, (int) dm.getHeight() - 28, 32, 28));
            //JExPanel.setAinimateIcon(image, image1);
            button.setSelectedIcon(image1);
      }

      public static void setAinimateIcon(ImageIcon ico1, ImageIcon ico2)
      {
            icoa = ico1;
            icos = ico2;
      }

      public Dimension getPreferredSize()
      {
            return new Dimension(600, 400);
      }

      public void setdrawP(drawProcess dp)
      {
            this.dp = dp;
            if (timer != null) timer.stop();
            if (dp.animate != null)
            {
                  AnimateC ant = dp.animate;
                  if (timer == null)
                        timer = new Timer(ant.getInitValue(), this);
                  else
                        timer.setDelay(ant.getInitValue());
                  timer.start();
                  button.setIcon(icos);
                  button.setVisible(true);
            } else
                  button.setVisible(false);

      }

      public void actionPerformed(ActionEvent e)
      {
            if (e.getSource() == button)
            {
                  if (!timer.isRunning())
                  {
                        dp.animationStart();
                        button.setIcon(icos);
                        timer.start();
                  } else
                  {
                        dp.animationStop();
                        button.setIcon(icoa);
                        timer.stop();
                  }
            } else if (e.getSource() == timer)
            {
                  dp.animationOntime();
                  this.repaint();
            }
      }

      public void mouseClicked(MouseEvent e)
      {
            if (e.getClickCount() > 1)
                  dp.DWMouseDbClick(e.getX(), e.getY());
            if (e.getButton() == MouseEvent.BUTTON3)
                  dp.DWMouseRightClick(e.getX(), e.getY());
      }

      public void mousePressed(MouseEvent e)
      {
            dp.DWButtonDown(e.getX(), e.getY());
            repaint();
      }

      public void mouseReleased(MouseEvent e)
      {
            dp.DWButtonUp(e.getX(), e.getY());
            repaint();
      }

      public void mouseEntered(MouseEvent e)
      {
      }

      public void mouseExited(MouseEvent e)
      {
      }

      public void mouseDragged(MouseEvent e)
      {
            dp.DWMouseDrag(e.getX(), e.getY());
            this.repaint();
      }

      public void mouseMoved(MouseEvent e)
      {
            dp.DWMouseMove(e.getX(), e.getY());
            this.repaint();
      }

      public void paintComponent(Graphics g)
      {
            super.paintComponent(g);
            dp.paintPoint(g);


      }

      public void componentResized(ComponentEvent e)
      {
            Dimension dm = this.getSize();
            button.setBounds(new Rectangle(0, (int) dm.getHeight() - 28, 32, 28));
      }

      public void componentMoved(ComponentEvent e)
      {
      }

      public void componentShown(ComponentEvent e)
      {
      }

      public void componentHidden(ComponentEvent e)
      {
            timer.stop();
      }

}
