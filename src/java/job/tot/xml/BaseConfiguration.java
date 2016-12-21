/*
 * BaseConfiguration.java
 *
 * Created on 2006��7��18��, ����12:15
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package job.tot.xml;
import java.util.Iterator;

import org.apache.commons.collections.SequencedHashMap;

/**
 * Basic configuration classe. Stores the configuration data but does not
 * provide any load or save functions. If you want to load your Configuration
 * from a file use PropertiesConfiguration or XmlConfiguration.
 *
 * This class extends normal Java properties by adding the possibility
 * to use the same key many times concatenating the value strings
 * instead of overwriting them.
 *
 * @author <a href="mailto:stefano@apache.org">Stefano Mazzocchi</a>
 * @author <a href="mailto:jon@latchkey.com">Jon S. Stevens</a>
 * @author <a href="mailto:daveb@miceda-data">Dave Bryson</a>
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @author <a href="mailto:leon@opticode.co.za">Leon Messerschmidt</a>
 * @author <a href="mailto:kjohnson@transparent.com">Kent Johnson</a>
 * @author <a href="mailto:dlr@finemaltcoding.com">Daniel Rall</a>
 * @author <a href="mailto:ipriha@surfeu.fi">Ilkka Priha</a>
 * @author <a href="mailto:jvanzyl@apache.org">Jason van Zyl</a>
 * @author <a href="mailto:mpoeschl@marmot.at">Martin Poeschl</a>
 * @author <a href="mailto:hps@intermeta.de">Henning P. Schmiedehausen</a>
 * @author <a href="mailto:ksh@scand.com">Konstantin Shaposhnikov</a>
 * @author <a href="mailto:oliver.heger@t-online.de">Oliver Heger</a>
 *
 * @version $Id: BaseConfiguration.java,v 1.1 2003/12/09 08:25:30 huumai Exp $
 */
public class BaseConfiguration extends AbstractConfiguration
{
    /** stores the configuration key-value pairs */
    private SequencedHashMap store = new SequencedHashMap();

    /**
     * Empty constructor.  You must add all the values to this configuration.
     */
    public BaseConfiguration()
    {
        super();
    }

    /**
     * Creates an empty BaseConfiguration object with
     * a Super-Object which is queries for every key.
     *
     * @param defaults Configuration defaults to use if key not in file
     */
    public BaseConfiguration(Configuration defaults)
    {
        super(defaults);
    }

    /**
     * Adds a key/value pair to the map.  This routine does no magic morphing.
     * It ensures the keylist is maintained
     *
     * @param key key to use for mapping
     * @param obj object to store
     */
    protected void addPropertyDirect(String key, Object obj)
    {
        Object o = getPropertyDirect(key);
        Object objAdd = null;

        if(o == null)
        {
            objAdd = obj;
        }
        else
        {
            if (o instanceof Container)
            {
                ((Container) o).add(obj);
            }
            else
            {
                // The token key is not a container.
                Container c = new Container();

                // There is an element. Put it into the container
                // at the first position
                c.add(o);

                // Now gobble up the supplied object
                c.add(obj);

                objAdd = c;
            }
        }

        if(objAdd != null)
        {
            store.put(key, objAdd);
        }
    }

    /**
     * Read property from underlying map.
     *
     * @param key key to use for mapping
     *
     * @return object associated with the given configuration key.
     */
    protected Object getPropertyDirect(String key)
    {
        return store.get(key);
    }

    /**
     * Check if the configuration is empty
     *
     * @return <code>true</code> if Configuration is empty,
     * <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return store.isEmpty();
    }

    /**
     * check if the configuration contains the key
     *
     * @param key the configuration key
     *
     * @return <code>true</code> if Configuration contain given key,
     * <code>false</code> otherwise.
     */
    public boolean containsKey(String key)
    {
        return store.containsKey(key);
    }

    /**
     * Clear a property in the configuration.
     *
     * @param key the key to remove along with corresponding value.
     */
    public void clearProperty(String key)
    {
        if (containsKey(key))
        {
            store.remove(key);
        }
    }

    /**
     * Get the list of the keys contained in the configuration
     * repository.
     *
     * @return An Iterator.
     */
    public Iterator getKeys()
    {
        return store.iterator();
    }
}

