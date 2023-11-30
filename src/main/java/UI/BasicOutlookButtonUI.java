/**
 * $ $ License.
 *
 * Copyright $ L2FProd.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package UI;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Mimics Outlook button look. <br>
 */
public class BasicOutlookButtonUI extends BasicButtonUI
{

      public static ComponentUI createUI(JComponent c)
      {
            return new BasicOutlookButtonUI();
      }

      protected void installDefaults(AbstractButton b)
      {
            super.installDefaults(b);
            b.setRolloverEnabled(true);
            b.setOpaque(false);
            b.setHorizontalTextPosition(JButton.CENTER);
            b.setVerticalTextPosition(JButton.BOTTOM);
            b.setBorder(new  EtchedBorder(EtchedBorder.LOWERED) );
      }

      protected void paintButtonPressed(Graphics g, AbstractButton b)
      {
            setTextShiftOffset();
      }

}