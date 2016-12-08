package gprover;

/**
 * Created by IntelliJ IDEA.
 * User: yezheng
 * Date: 2006-5-18
 * Time: 14:12:16
 * To change this template use File | Settings | File Templates.
 */
public class term
{
      var v;
      long t1, t2;

      public term(var v, int t1, int t2)
      {
            this.v = v;
            this.t1 = t1;
            this.t2 = t2;
      }

      public void simplify()
      {
            long l;
            long l1 = t1;
            long l2 = t2;

            if (l1 < 0L)
                  l1 = -l1;
            if (l2 < 0L)
                  l2 = -l2;
            if (l1 > l2)
            {
                  l = l1;
                  l1 = l2;
                  l2 = l;
            }
            while (l1 != 0L)
            {
                  l = l2 % l1;
                  l2 = l1;
                  l1 = l;
            }
            t1 = t1 / l2;
            t2 = t2 / l2;
      }
}
