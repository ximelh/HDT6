import java.util.Map;

public class Association<K,V> implements Map.Entry<K,V>
{

    protected K theKey; // the key of the key-value pair
   
    protected V theValue; // the value of the key-value pair

    
    public Association(K key, V value)
    {
        
        theKey = key;
        theValue = value;
    }

    
    public Association(K key)
    {
        this(key,null);
    }

    
    public boolean equals(Object other)
    {
        Association otherAssoc = (Association)other;
        return getKey().equals(otherAssoc.getKey());
    }
    
    
    public int hashCode()
    {
        return getKey().hashCode();
    }
    
    
    public V getValue()
    {
        return theValue;
    }

    
    public K getKey()
    {
        return theKey;
    }

    
    public V setValue(V value)
    {
        V oldValue = theValue;
        theValue = value;
        return oldValue;
    }

    
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Association: "+getKey()+"="+getValue()+">");
        return s.toString();
    }
}
