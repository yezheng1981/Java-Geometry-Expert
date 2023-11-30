/*
 * $Id: BoundingBox.java,v 1.2 2007/08/26 18:56:35 gil1 Exp $
 *
 * $Date: 2007/08/26 18:56:35 $
 *
 * Copyright (c) Eric Z. Beard, ericzbeard@hotmail.com 
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 */
package pdf;

import java.awt.*;
import java.util.*;

public class BoundingBox extends Rectangle
{
  /** Percent f line height to space lines */
  public static final int LINE_SPACING_PERCENTAGE = 20;

  /** Used to a align a String centered vertically */
  public static final int VERT_ALIGN_CENTER  = 0;

  /** Used to align a String at the top of the box */
  public static final int VERT_ALIGN_TOP     = 1;

  /** Used to align a String at the bottom of the box */
  public static final int VERT_ALIGN_BOTTOM  = 2;

  /** Used to align a String horizontally in the center of the box */
  public static final int HORIZ_ALIGN_CENTER = 3;

  /** Used to align a String to the left in the box */
  public static final int HORIZ_ALIGN_LEFT   = 4;

  /** Used to aling a String to the right in a box */
  public static final int HORIZ_ALIGN_RIGHT  = 5;

  /** Used to subtract a child from a box, *leaving* the top portion */
  public static final int SUBTRACT_FROM_TOP    = 6;

  /** Used to subtract a child from a box, *leaving* the bottom portion */
  public static final int SUBTRACT_FROM_BOTTOM = 7;

  /** Used to subtract a child from a box, *leaving* the left portion */
  public static final int SUBTRACT_FROM_LEFT   = 8;

  /** Used to subtract a child from a box, *leaving" the right portion */
  public static final int SUBTRACT_FROM_RIGHT  = 9;

  private static final int[] VERT_ALIGNS = {VERT_ALIGN_CENTER,
                                            VERT_ALIGN_TOP,
                                            VERT_ALIGN_BOTTOM};

  private static final int[] HORIZ_ALIGNS = {HORIZ_ALIGN_CENTER,
                                             HORIZ_ALIGN_LEFT,
                                             HORIZ_ALIGN_RIGHT};

  private static final int[] SUBTRACTS = {SUBTRACT_FROM_TOP,
                                          SUBTRACT_FROM_BOTTOM,
                                          SUBTRACT_FROM_LEFT,
                                          SUBTRACT_FROM_RIGHT};

  /** The point to use for Graphics.drawString() */
  private Point drawingPoint;

  /** The absolute, world location of the box */
  private Point absoluteLocation;

  /** Link to parent box */
  private BoundingBox parent;
  
  /** 
   * If this box was the result of a getStringBounds call, this 
   * array will hold the broken strings
   */
  private String[] stringArray;

  /** The string specified in getStringBounds */
  private String fullString;
  
  /**
   * Creates a new <code>BoundingBox</code> instance.
   *
   * @param p a <code>Point</code>, upper left coords
   * @param d a <code>Dimension</code>, used to determine height and width
   */
  public BoundingBox(Point p, Dimension d) {
    super(p, d);
    this.drawingPoint = this.getLocation();
    this.absoluteLocation = this.getLocation();
  }

  

  /**
   * <p>Returns true if this box has a parent.  The 'world', or 
   * enclosing canvas is not considered a parent</p>
   *
   * @return a <code>boolean</code> value
   */
  public boolean hasParent() {
	  return parent != null;
  }

  /**
   * <p>Get this box's parent box</p>
   *
   * @return a <code>BoundingBox</code> value
   */
  public BoundingBox getParent() {
    return parent;
  }



  /**
   * <p>Make the specified box this box's child.  Equivalent to 
   * <code>child.setParent(parent)</code> where the specified 'parent' is 
   * this instance</p>
   *
   * @param child a <code>BoundingBox</code>, any box that can fit inside 
   *              this one.  The results of calling 
   *              <code>getAbsoluteLocation()</code> on the child will be 
   *              altered after this to take into account the child's 
   *              new location in the 'world'
   *
   */
  public void add(BoundingBox child) {
    child.setParent(this);
  }



  /**
   * <p>Make the specified box this box's parent</p>
   *
   * @param parent a <code>BoundingBox</code> value
   */
  public void setParent(BoundingBox parent) {
    // Prevent infinite recursion
    if (this == parent) {
      return;
    }
    this.parent = parent;

    // If this box was created empty, without a String inside,
    // determine its absolute location
    if (this.getLocation().equals(this.getAbsoluteLocation())) {
      int ancestorTranslateX = 0;
      int ancestorTranslateY = 0;

      BoundingBox ancestor = this;
      while (ancestor.hasParent()) {
        BoundingBox oldRef = ancestor;
        ancestor = ancestor.getParent();
        // Prevent infinite recursion
        if (ancestor == oldRef) {
          break;
        }
        ancestorTranslateX += (int)ancestor.getLocation().getX();
        ancestorTranslateY += (int)ancestor.getLocation().getY();
      }

      this.getAbsoluteLocation().translate(ancestorTranslateX, 
                                           ancestorTranslateY);
    } // end if
  } // end setParent





  /**
   * <p>Get the wrapped strings if this box was from a call to getStringBounds,
   * otherwise this method returns null</p>
   *
   * @return a <code>String[]</code> array of strings, top to bottom in layout
   */
  public String[] getStringArray() {
    return stringArray;
  } // end getStringArray



  /**
   * <p>Set the value of the string array</p>
   *
   * @param strArray  a <code>String</code> array
   * 
   */
  public void setStringArray(String[] strArray) {
    this.stringArray = strArray;
  }


  /**
   * <p>Set the absolute upper left world location point for this box</p>
   *
   * @param point a <code>Point</code> value
   */
  public void setAbsoluteLocation(Point point) {
    this.absoluteLocation = point;
  }



  /** 
   * <p>Returns false if for any reason this box has negative dimensions</p>
   * 
   * @return true if the box has positive height and width, false otherwise.
   */
  public boolean boxExists() {
	  return (this.getHeight() > 0 && this.getWidth() > 0);
  } // end boxExists




  /**
   * <p>Get the absolute upper left location point for this box</p>
   *
   * @return a <code>Point</code> value
   */
  public Point getAbsoluteLocation() {
    return absoluteLocation;
  }


  /**
   * <p>Returns the full string associated with a call to 
   *    <code>getStringBounds</code></p>
   *    
   * @return the full string.
   */
  public String getFullString() {
    return fullString;
  }


  /**
   * <p>Sets the full string associated with <code>getStringBounds</code></p>
   *
   * @param string a <code>String</code>
   */
  public void setFullString(String string) {
    this.fullString = string;
  }
 
  public BoundingBox getStringBounds(String string, 
                                     int hAlign, 
                                     int vAlign, 
                                     FontMetrics fm,
                                     int padding,
                                     boolean enforce)
    throws IllegalArgumentException, StringTooLongException {                                    
    // Check to make sure the values passed in are valid
    if (!checkHAlign(hAlign)) {
      throw new IllegalArgumentException("BoundingBox.getStringBounds, " + 
                        "hAlign invalid : " + hAlign);
    }
    if (!checkVAlign(vAlign)) {
      throw new IllegalArgumentException("BoundingBox.getStringBounds, " + 
                       "vAlign invalid : " + hAlign);
    }
    if (fm == null) {
      throw new IllegalArgumentException("BoundingBox.getStringBounds, " + 
                    "FontMetrics null");
    }
    if (string == null) {
      throw new IllegalArgumentException("BoundingBox.getStringBounds, " + 
                      "String null");
    }

    // NOTE: For this portion of the method, parent refers 
    // to this object and child refers to the object about 
    // to be created.  When the absolute point for drawing the 
    // String is determined, this object's ancestors are checked.
    Dimension parentSize = this.getSize();

    Point childLocation;
    Dimension childSize;

    // String ascent, width, height, parent, child width, height
    int sa, sw, sh, pw, ph, cw, ch;

    // Child, parent x, y coords for upper left
    int cx, cy, px, py;

    sa = fm.getMaxAscent();
    sw = fm.stringWidth(string);
    sh = sa + fm.getMaxDescent();
    pw = (int)parentSize.getWidth();
    ph = (int)parentSize.getHeight();
    if (pw < 0) {
      throw new StringTooLongException("The parent box has a negative width " + 
                          " (" + pw + ")");
    }
    if (ph < 0) {
      throw new StringTooLongException("The parent box has a negative height"+
                          " (" + ph + ")");
    }
    cw = sw + padding*2;
    ch = sh + padding*2;
    px = (int)this.getX();
    py = (int)this.getY();

    String[] childStrArray = null;

    if ((cw > pw) || (string.indexOf("\n") != -1)) {
      cw = pw - (padding * 2);
      childStrArray = createStringArray(string, fm, padding, pw);
      ch = getWrappedHeight(childStrArray, fm, padding);
      if (ch > ph) {
        // If enforce is not true, it means we want the box to 
        // be returned anyway (along with the strings in the array)
        // so we can chop them manually and try again
        if (enforce) {
          throw new StringTooLongException("The wrapped strings do not " + 
                      "fit into the parent box, pw=" + pw + 
                      ", ph=" + ph + ", ch=" + ch + ", cw=" + cw + 
                      ", string: " + string);
        }
      }
    }

    // Need to have child width and height, and string array set

    // Child location is relative to this (parent) box, not the world
    if (vAlign == VERT_ALIGN_TOP) {
      cy = 0;      
    }
    else if (vAlign == VERT_ALIGN_CENTER) {
      cy = (ph/2) - (ch/2);
    }
    else {
      cy = ph - ch;
    }

    if (hAlign == HORIZ_ALIGN_LEFT) {
      cx = 0;
    }
    else if (hAlign == HORIZ_ALIGN_CENTER) {
      cx = (pw/2) - (cw/2);
    }
    else {
      cx = pw - cw;
    }

    childLocation = new Point(cx, cy);
    childSize = new Dimension(cw, ch);
    
    // Drawing location is based on the baseline of the String, and 
    // relative to the world, not this box.  The drawing point differs 
    // from the absolute box location because of padding and ascent
    int dpx, dpy, abx, aby;

    // If this object also has a parent (maybe grandparents), iterate 
    // through them and find the absolute 'world' location
    int ancestorTranslateX = 0;
    int ancestorTranslateY = 0;

    BoundingBox ancestor = this;
    while (ancestor.hasParent()) {
      BoundingBox oldRef = ancestor;
      ancestor = ancestor.getParent();
      // Prevent infinite recursion
      if (ancestor == oldRef) {
        break;
      }
      ancestorTranslateX += (int)ancestor.getLocation().getX();
      ancestorTranslateY += (int)ancestor.getLocation().getY();
    }

    // Determine the absolute location for the box
    abx = px + cx + ancestorTranslateX;
    aby = py + cy + ancestorTranslateY;

    // Determine the absolute drawing point for the String
    dpx = abx + padding;
    dpy = aby + padding + sa;

    Point drawingPoint = new Point(dpx, dpy);
    BoundingBox returnChild = new BoundingBox(childLocation, 
                                              childSize, 
                                              drawingPoint, 
                                              new Point(abx, aby));
    this.add(returnChild);
    returnChild.setFullString(string);
    returnChild.setStringArray(childStrArray);
    return returnChild;

  } // end getStringBounds


  /**
   * <p>Gets the location of a String after it is adjusted for 
   * alignment within this box.  The point's coordinates are 
   * either within this box or within the enclosing area.</p>
   *
   * <p>By default, this method enforces string length and throws the 
   * exception if it is too long</p>
   *
   * @param string a <code>String</code>, the String to be placed
   * @param hAlign an <code>int</code>, HORIZ_ALIGN_CENTER, 
   *        HORIZ_ALIGN_LEFT, HORIX_ALIGN_RIGHT         
   * @param vAlign an <code>int</code>, VERT_ALIGN_CENTER, 
   *        VERT_ALIGN_TOP, VERT_ALIGN_BOTTOM
   * @param fm a <code>FontMetrics</code> object for this String
   * @param padding an <code>int</code>, the padding around the String
   * @return a <code>Point</code>, the coords to use in drawString()
   * @throws IllegalArgumentException if the args are invalid
   * @throws StringTooLongException if the string won't fit
   */
  public BoundingBox getStringBounds(String string, 
                                     int hAlign, 
                                     int vAlign, 
                                     FontMetrics fm,
                                     int padding) 
    throws StringTooLongException, IllegalArgumentException {
    return getStringBounds(string, hAlign, vAlign, fm, padding, true);
  } // end getStringBounds (enforce true by default)

  
  public void drawWrappedString(Graphics    g, 
                                FontMetrics fm,
                                int         padding,
                                int         hAlign)
    throws IllegalArgumentException, StringTooLongException {
    if (getStringArray() == null) {
      Point p = getDrawingPoint();
      int xx = (int)p.getX();
      int yy = (int)p.getY();
      g.drawString(getFullString(), xx, yy);
    }
    else {
      int len = stringArray.length;
      for (int i = 0; i < len; i++) {
        BoundingBox wrappedBox = null;
        wrappedBox = getStringBounds(stringArray[i],
                                         hAlign, 
                                         BoundingBox.VERT_ALIGN_TOP, 
                                         fm,
                                         0);
        Point pp = wrappedBox.getDrawingPoint();
        int xx = (int)pp.getX();
        if (hAlign == BoundingBox.HORIZ_ALIGN_RIGHT) {
          xx -= padding;
        }
        if (hAlign == BoundingBox.HORIZ_ALIGN_LEFT) {
          xx += padding;
        }
        int yy = (int)pp.getY() + padding;
        g.drawString(stringArray[i], xx, yy);
        subtract(wrappedBox, BoundingBox.SUBTRACT_FROM_BOTTOM);
      }
    }
  } // end drawWrappedString


  /**
   * <p>Draws lines from the wrapped string until there is no more room and
   * then stops.  If there is no string or the box is too small for 
   * anything to be drawn, does nothing</p>
   *
   * @param g the <code>Graphics</code> object to draw to
   * @param fm the <code>FontMetrics</code> object to use for string sizing
   * @param padding the <code>int</code> amount of padding around the string
   * @param hAlign the <code>int</code> horizontal alignment
   * 
   */
  public void drawWrappedStringTruncate(Graphics g, 
                                        FontMetrics fm,
                                        int padding,
                                        int hAlign) {
    
    if (getStringArray() == null) {
      Point p = getDrawingPoint();
      int xx = (int)p.getX();
      int yy = (int)p.getY();
      if (getFullString() != null) {
        g.drawString(getFullString(), xx, yy);
      }
      else {
        System.err.println("getStringArray and getFullString are null");
      }
    }
    else {
      int totalHeight = 0;
      int len = stringArray.length;
      for (int i = 0; i < len; i++) {
        BoundingBox wrappedBox = null;
        try {
          wrappedBox = getStringBounds(stringArray[i],
                                         hAlign, 
                                         BoundingBox.VERT_ALIGN_TOP, 
                                         fm,
                                         0,
                                         false);
          totalHeight += (int)wrappedBox.getHeight();
          if (getParent() != null) {
            if (totalHeight > (int)(getParent().getHeight())) {
              return;
            }
          }
        }
        catch (StringTooLongException stle) {
          stle.printStackTrace();
          return;
        }
        wrappedBox.drawChoppedString(g, fm, padding, hAlign);
        subtract(wrappedBox, BoundingBox.SUBTRACT_FROM_BOTTOM);
      }
    }
  } // end drawWrappedStringTruncate


  /**
   * <p>Take the first line of the string (if it is wrapped, otherwise just 
   * take the whole string) and chop the end of it off to make it fit in the 
   * box.  If the box is smaller than one letter, draw nothing</p>
   *
   * @param g the <code>Graphics</code> object to draw to
   * @param fm the <code>FontMetrics</code> object to use for string sizing
   * @param padding the <code>int</code> amount of padding around the string
   * @param hAlign the <code>int</code> horizontal alignment
   */
  public void drawChoppedString(Graphics g,
                                FontMetrics fm,
                                int padding,
                                int hAlign) {

    String string = "";
    if (getStringArray() != null) {
      string = new String(getStringArray()[0]);
    }
    else {
      string = new String(getFullString());
    }
    BoundingBox choppedBox = null;
    try {
      choppedBox = getStringBounds(string,
                                   hAlign,
                                   VERT_ALIGN_TOP,
                                   fm,
                                   padding);
      Point p = choppedBox.getDrawingPoint();
      int x = (int)p.getX();
      int y = (int)p.getY();
      g.drawString(string, x, y);
    }
    catch (StringTooLongException stle) {
      // Doesn't fit - start cutting from the end until it does
      StringBuffer buf = new StringBuffer().append(string);
      if (buf.length() == 0) {
        System.out.println("BoundingBox.drawChoppedString, buf len 0 ??");
        //return;
        throw new RuntimeException();
      }
      buf.deleteCharAt(buf.length()-1);
      while ((fm.stringWidth(buf.toString()) > (int)getWidth()) && 
             (buf.length() > 0)) {
        buf.deleteCharAt(buf.length()-1);
      }
      
      try {
        choppedBox = getStringBounds(buf.toString(),
                                     hAlign,
                                     VERT_ALIGN_TOP,
                                     fm,
                                     padding);
      Point pp = choppedBox.getDrawingPoint();
      int xx = (int)pp.getX();
      int yy = (int)pp.getY();
      g.drawString(string, xx, yy);
      }
      catch (StringTooLongException sstle) {
        // Must be a really small box!
        sstle.printStackTrace();
      }
    }
  } // end drawChoppedString









  /**
   * <p>Get the total height of the box needed to contain the strings in 
   * the specified array</p>
   */
  private int getWrappedHeight(String[] strings, FontMetrics fm, int padding) {
    int ma = fm.getMaxAscent();
    int md = fm.getMaxDescent();
    int sh = ma + md;
    int hPad = sh / LINE_SPACING_PERCENTAGE;
    sh += hPad;
    int total = sh * strings.length; 

    return total + (padding*2);
  } // end getWrappedHeight



  /**
   *
   * <p>Make a string array from a string, wrapped to fit the box</p> 
   *
   * <p>If the line width is too short, the array is just a 
   * tokenized version of the string</p>
   *
   * @param string - the <code>String</code> to convert to an array
   * @param
   */
  private String[] createStringArray(String      string, 
                                     FontMetrics fm, 
                                     int         padding,
                                     int         pw) {
    if (string == null) {
      System.err.println("Tried createStringArray with null String");
      return null;
    }
    if (fm == null) {
      System.err.println("Tried createStringArray with null FontMetrics");
    }

    int lw = pw - (padding*2);

   

    Vector<String> returnVector = new Vector<String>();
    // Return delimiters as tokens
    StringTokenizer st = new StringTokenizer(string, " \t\n\r\f", true);
    StringBuffer tempBuffer = new StringBuffer();
    StringBuffer finalBuffer = new StringBuffer();

    while(st.hasMoreTokens()) {
      // Get the next word and add a space after it 
      String tempString = st.nextToken();
      tempBuffer.append(tempString);

      // If we haven't reached the width with our current 
      // line, keep adding tokens.  Also, check for hard returns
      if ((fm.stringWidth(tempBuffer.toString()) < lw) && 
          (tempBuffer.toString()
              .charAt(tempBuffer.toString().length() - 1) != '\n') && 
          (tempBuffer.toString()
              .charAt(tempBuffer.toString().length() - 1) != '\r')) {
       finalBuffer.append(tempString);
       continue;
      } 
        returnVector.addElement(finalBuffer.toString());
        finalBuffer.delete(0, finalBuffer.length());
        tempBuffer.delete(0, tempBuffer.length());
        if ((tempString.charAt(0) != '\n') && 
            (tempString.charAt(0) != '\r')) {
          tempBuffer.append(tempString);
          finalBuffer.append(tempString);
        }
        continue;
                           
    } // end while
    returnVector.addElement(finalBuffer.toString());

    int len = returnVector.size();
    // Init the class member field stringArray
    String[] childStrArray = new String[len];
    for (int i = 0; i < len; i++) {
      String curStr = (String)returnVector.get(i);
      childStrArray[i] = curStr; 
    }

    return childStrArray;
    
  } // end createStringArray 



  /**
   * <p>Removes the child box from this parent box.  The child must 
   * have this object as its parent or the method does nothing.  
   * The BoundingBox returned will be cut by an area equal to 
   * the child area plus the horizontal or vertical strip in 
   * which it sits, depending on the 'subtractFrom' value passed
   * in</p>
   *
   * @param child a <code>BoundingBox</code> value
   * @param subtractFrom an <code>int</code>, SUBTRACT_FROM_LEFT, 
            SUBTRACT_FROM_RIGHT, SUBTRACT_FROM_TOP,
            SUBTRACT_FROM_BOTTOM
   * @return a <code>BoundingBox</code> value
   * @see #SUBTRACT_FROM_LEFT
   * @see #SUBTRACT_FROM_RIGHT
   * @see #SUBTRACT_FROM_TOP
   * @see #SUBTRACT_FROM_BOTTOM
   */
  public BoundingBox subtract(BoundingBox child, int subtractFrom) {
		// First, check to see if the params are valid
		if (child == null) {
			throw new IllegalArgumentException("BoundingBox.subtract, "
					+ "BoundingBox child is null");
		}
		if (!child.hasParent()) {
			throw new IllegalArgumentException("BoundingBox.subtract, "
					+ "BoundingBox child has no parent");
		}
		if (!(child.getParent() == this)) {
			throw new IllegalArgumentException("BoundingBox.subtract, "
					+ "this is not BoundingBox child's parent");
		}
		// Now that we know the child is this object's child, we continue
		// and check the subtractFrom param
		int len = SUBTRACTS.length;
		boolean valid = false;
		for (int i = 0; i < len; i++) {
			if (subtractFrom == SUBTRACTS[i]) {
				valid = true;
			}
		}
		if (!valid) {
			throw new IllegalArgumentException("BoundingBox.subtract, "
					+ "subtractFrom invalid: " + subtractFrom);
		}

		// Now we know the child is valid, and if the subtractFrom
		// preference was invalid, we subtract from the bottom

		// The child should no longer be used, since the parent
		// reference will be invalid
		child.setParent(null);

		int cx = (int) child.getLocation().getX();
		int cy = (int) child.getLocation().getY();
		int cw = (int) child.getSize().getWidth();
		int ch = (int) child.getSize().getHeight();
		int px = (int) this.getLocation().getX();
		int py = (int) this.getLocation().getY();
		int pw = (int) this.getSize().getWidth();
		int ph = (int) this.getSize().getHeight();

		switch (subtractFrom) {
		case SUBTRACT_FROM_LEFT:
			// This will be useful for right-justified Strings in tables
			pw = cx;
			this.setSize(new Dimension(pw, ph));
			return this;

		case SUBTRACT_FROM_RIGHT:
			// This will be useful for left justified Strings in tables
			px = px + cw + cx;
			pw = pw - cw - cx;
			this.setLocation(new Point(px, py));
			this.setSize(new Dimension(pw, ph));
			return this;

		case SUBTRACT_FROM_BOTTOM:
			py = py + ch + cy;
			ph = ph - ch - cy;
			this.setLocation(new Point(px, py));
			this.setSize(new Dimension(pw, ph));
			return this;

		case SUBTRACT_FROM_TOP:
			ph = cy;
			this.setSize(new Dimension(pw, ph));
			return this;

		default: // Should never happen
			break;
		} // end switch
		return this;
	} // end subtract




  /**
	 * <p>
	 * Gets the drawing point to use in Graphics drawing methods. After getting
	 * a new BoundingBox with getStringBounds(), calling this method will give
	 * you an absolute point, accounting for alignment and padding, etc, from
	 * which to start drawing the String
	 * </p>
	 * 
	 * <p>
	 * If getStringBounds was not called (this is a parent box), the upper left
	 * coordinates will be returned (this.getLocation())
	 * </p>
	 * 
	 * @return a <code>Point</code>
	 */
  public Point getDrawingPoint() {
    return drawingPoint;
  }




  private BoundingBox(Point p, 
                      Dimension d, 
                      Point drawingPoint,
                      Point absolute) {
    super(p, d);
    this.drawingPoint = drawingPoint;
    this.absoluteLocation = absolute;
  }


  /**
   * <p>Checks the horizontal alignment passed into a 
   * method to make sure it is one of the valid values</p>
   *
   * @param hAlign an <code>int</code> value
   * @return a <code>boolean</code> value
   */
  private boolean checkHAlign(int hAlign) {
    int len = HORIZ_ALIGNS.length;
    for (int i = 0; i < len; i++) {
      if (hAlign == HORIZ_ALIGNS[i]) {
        return true;
      }
    }
    return false;
  }



  /**
   * <p>Checks the vertical alignment passed into a 
   * method to make sure it is one of the valid values</p>
   *
   * @param vAlign an <code>int</code> value
   * @return a <code>boolean</code> value
   */
  private boolean checkVAlign(int vAlign) {
    int len = VERT_ALIGNS.length;
    for (int i = 0; i < len; i++) {
      if (vAlign == VERT_ALIGNS[i]) {
        return true;
      }
    }
    return false;
  }

} // end class BoundingBox




