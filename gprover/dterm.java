package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-4
 * Time: 11:32:44
 * To change this template use File | Settings | File Templates.
 */

public class dterm
{
      public int deg;          //degree
      public xterm p;         //A term
      public dterm nx;       // All next terms.

      public String text;

      public dterm()
      {
            deg = 0;
            p = null;
            nx = null;
            text = null;
      }

      public String toString()
      {
            return text;
      }
}
