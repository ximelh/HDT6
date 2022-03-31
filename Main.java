import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Main.java
 * Clase principal
 */
public class Main {
    public static void main(String args[])
    {
        Scanner keyboard = new Scanner(System.in); // Variable que obtiene le input
        boolean exit = false; // Variable de control para salir del menú
        int map = 0;

        Map<String, Card> cards = null;
        Map<String, ArrayList<Card>> collection = null;

        while (!exit) {
            System.out.println("Ingrese el tipo de stack que desea utilizar. \n1. HashMap" +
                    "\n2. TreeMap \n3. LinkedHashMap \n4. Salir");
            MapFactory<String, ArrayList<Card>> mapFactory = new MapFactory<>();
            map = Integer.parseInt(getNumber(keyboard));

            switch (map) {
                case 1:
                    cards = getCards("HM");
                    collection = mapFactory.returnMap("HM");
                    exit = true;
                    break;
                case 2:
                    cards = getCards("TM");
                    collection = mapFactory.returnMap("TM");
                    exit = true;
                    break;
                case 3:
                    cards = getCards("LH");
                    collection = mapFactory.returnMap("LH");
                    exit = true;
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("La opción ingresadad no está definida.");
            }
        }

        int option = 0;
        while (exit) {
            System.out.println("Ingrese la acción a realizar. \nACCIONES DEL USUARIO \n1. Agregar una carta a la colección" +
                    "\n2. Mostrar el tipo de una carta \n3. Mostrar nombre, tipo y cantidad de cartas" +
                    "\n4. Mostrar nombre, tipo y cantidad de cartas ordenadas por tipo" +
                    "\nOPCIONES GLOBALES \n5. Mostrar cartas existentes \n6. Mostrar cartas ordenadas por tipo \n7. Salir \n8.Medir tiempos");
            option = Integer.parseInt(getNumber(keyboard));

            switch (option) {
                case 1:
                    System.out.println("Ingrese el nombre de la carta>");
                    String name = keyboard.nextLine();
                    System.out.println("Ingrese el tipo de la carta>");
                    String type = keyboard.nextLine();

                    Card card = new Card(name.toUpperCase(), type.toUpperCase());

                    if (cards.containsKey(card.getName())) {
                        if (collection.containsKey(card.getName())) {
                            collection.get(card.getName()).add(card);
                        } else {
                            ArrayList<Card> col = new ArrayList<>();
                            col.add(card);
                            collection.put(card.getName(), col);
                        }

                        System.out.println("\t\tCarta agregada con éxito \n");
                    } else {
                        System.out.println("\t\t¡¡ERROR!! Carta no encontrada en el sistema\n");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de la carta a buscar>");
                    String name1 = keyboard.nextLine().toUpperCase();

                    try {
                        Card locatedCard = cards.get(name1);
                        System.out.println("\t\t" + locatedCard.toString() + "\n");
                    } catch (Exception e) {
                        System.out.println("\t\t¡¡ERROR!! Carta no encontrada en el sistema\n");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el nombre de la carta a buscar>");
                    String name2 = keyboard.nextLine().toUpperCase();

                    try {
                        ArrayList<Card> located = collection.get(name2);
                        System.out.println("\t\t" + located.get(0).toString() + " | Está " + located.size() + " veces" + "\n");
                    } catch (Exception e) {
                        System.out.println("\t\t¡¡ERROR!! Carta no encontrada en el sistema\n");
                    }
                    break;
                case 4:
                    try {
                        collection.entrySet().stream().sorted(Map.Entry.comparingByValue(new Comparator<ArrayList<Card>>() {
                            @Override
                            public int compare(ArrayList<Card> o1, ArrayList<Card> o2) {
                                return o1.get(0).getType().compareTo(o2.get(0).getType());
                            }
                        })).forEach(item -> {
                            System.out.println("\t\t" + item.getValue().get(0) + " | Está " + item.getValue().size() + " veces");
                        });
                    } catch (Exception e) {
                        System.out.println("\t\t¡¡ERROR!!\n");
                    }
                    break;
                case 5:
                    cards.entrySet().forEach(entry -> {
                        System.out.println("\t\t" + entry.getValue().toString());
                    });
                    break;
                case 6:
                    try {
                        cards.entrySet().stream().sorted(Map.Entry.comparingByValue(new Comparator<Card>() {
                            @Override
                            public int compare(Card o1, Card o2) {
                                return o1.getType().compareTo(o2.getType());
                            }
                        })).forEach(item -> {
                            System.out.println("\t\t" + item.toString());
                        });
                    } catch (Exception e) {
                        System.out.println("\t\t¡¡ERROR!!\n");
                    }
                    break;
                case 7:
                    exit = false;
                    break;
                case 8:
                    String s="";
                    long tiempo = 0;
                    for(int i=0;i<20;i++);{
                        long t1 = System.nanoTime();
                        for (String cardString: cards.keySet()){
                            cards.get(cardString);
                        }
                    }
                    s=tiempo/20+"";   
                    System.out.println(s);  
                    
                break;
                default:
                    System.out.println("La opción ingresada no está definida.");
            }
        }
        System.exit(0);
    }

    /**
     * @param keyboard
     * @return
     */
    public static String getNumber(Scanner keyboard) {
        String number = keyboard.nextLine(); // Obtiene el input
        boolean isNumber = false;

        while (!isNumber) { // Vuelve a pedir input hasta que este sea un número
            try {
                int nm = Integer.parseInt(number); // Verifica que el input sea un número
                isNumber = true;
            } catch (NumberFormatException nft) {
                System.out.println("ERROR. Verifique que el valor ingresado sea numérico e intente de nuevo.");
                number = keyboard.nextLine();
            }
        }

        return number;
    }

    public static Map<String, Card> getCards(String option) {
        FileDialog dialog = new FileDialog((Frame) null, "Seleccion el archivo con las operaciones");
        dialog.setFile("*.txt"); // Solo permite que se seleccionen archivos .txt
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String dir = dialog.getDirectory() + dialog.getFile(); // Obtiene el directorio del archivo seleccionado

        MapFactory<String, Card> mapFactory = new MapFactory<>();
        Map<String, Card> data = mapFactory.returnMap(option);

        BufferedReader buffer = null; // Lee el archivo

        try {
            buffer = new BufferedReader(new FileReader(dir));
            String line = buffer.readLine();

            while (line != null) { // Cada línea del archivo la agrega al arraylist de datos
                String[] info = line.split("\\|");
                Card card = new Card(info[0].toUpperCase(), info[1].toUpperCase());
                data.put(card.getName(), card);

                line = buffer.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
