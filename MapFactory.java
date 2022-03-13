import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * MapFactory.java
 * @author Ximena Loarca
 * Clase Factory que devuelve el Map con la implementacion deseada
 */


public class MapFactory<K,V>
{
    private Map<K,V> MyMap;
/**
 * Devuelve el Map con la implementacion deseada
 * @param entry
 * @return
 */
public Map<K,V> returnMap(String entry) {
   //Seleccion de la implementacion
	if (entry.equalsIgnoreCase("HM"))
      return this.MyMap = new HashMap<K,V>(); //Devuelve HashMap
    else if (entry.equalsIgnoreCase("LH"))
        return this.MyMap = new LinkedHashMap<K,V>(); //Devuelve LinkedHashMap
	else
      return this.MyMap = new TreeMap<K,V>(); //Devuelve TreeMap
   }
}

