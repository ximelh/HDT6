import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory<K,V>
{
    private Map<K,V> MyMap;

public Map<K,V> returnMap(String entry) {
   
	if (entry.equalsIgnoreCase("HM"))
      return this.MyMap = new HashMap<K,V>(); 
    else if (entry.equalsIgnoreCase("LH"))
        return this.MyMap = new LinkedHashMap<K,V>(); 
	else
      return this.MyMap = new TreeMap<K,V>(); 
   }
}

