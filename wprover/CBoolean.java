package wprover;

/**
 * Created by IntelliJ IDEA.
 * User: Ye
 * Date: 2005-9-20
 * Time: 11:16:25
 * To change this template use File | Settings | File Templates.
 */                 
public class CBoolean
{
    public boolean bl = false;

    public CBoolean(boolean b)
    {
        bl = b;
    }

    public boolean getValue()
    {
        return bl;
    }
    public void setValue(boolean v)
    {
        bl = v;
    }
}
