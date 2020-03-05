package ex4;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

import java.util.ArrayList;

/**
 * The type Hash table.
 * Una tabla de hash guarda información usando el sistema de clave-valor, en este caso se generan 16 espacios(Buckets) en los que podemos almacenar datos.
 * Para decidir donde se guardan se hace la siguiente fórmula: hashDeLaClave%INITIAL_SIZE
 * De esta forma obtenemos un número entre 0 y el último bucket de la HashTable, si 2 claves diferentes dan el mismo resultado se crea una colisión,
 * haciendo que la nueva entrada a la tabla se coloque a la "derecha" del último elemento añadido a ese bucket.
 */
public class HashTable {
    private int INITIAL_SIZE = 16;
    private int size = 0;
    private HashEntry[] entries = new HashEntry[INITIAL_SIZE];

    /**
     * Size int.
     *
     * @return the int
     *
     * El método size() nos devuelve el la cantidad actual de elementos de la HashTable, esto es la suma de todos los elementos que tenemos en ella, si tenemos 5 elementos la cantidad será 5,
     * no nos va a devolver el támaño de la HashTable, eso lo hace otro método.
     * Importante: Nos puede devolver un valor mayor al valor de INITIAL_SIZE.
     */
    public int size(){
        return this.size;
    }

    /**
     * Real size int.
     *
     * @return the int
     *
     * El método realSize() nos devuelve el valor de INITIAL_SIZE, este valor es el que usaremos para calcular el hash de un elemento antes de introducirlo a la tabla.
     * El valor no cambia independientemente de si se encuentra vacía o de la cantidad de elementos que hayan en la tabla.
     */
    public int realSize(){
        return this.INITIAL_SIZE;
    }

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     *
     *  El método put() nos permite guardar elementos en la tabla, para ellos nos pide 2 Strings, la clave y el valor del elemento.
     */
    public void put(String key, String value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);

        if(entries[hash] == null) {
            entries[hash] = hashEntry;
        }
        else {
            HashEntry temp = entries[hash];
            while(temp.next != null)
                temp = temp.next;

            temp.next = hashEntry;
            hashEntry.prev = temp;
        }
        //Modificación de código
        size++;
    }

    /**
     * Returns 'null' if the element is not found.
     *
     * @param key the key
     * @return the string
     *
     *  El método get() nos devuelve el valor del elemento que le pasemos, para utilizarlo nos pide una String que ha de ser la clave del elemento que queremos mostrar.
     *  Nos devolverá nulo si no puede encontrar el elemento que estamos buscando
     */
    public String get(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {
            HashEntry temp = entries[hash];

            //Resultado de la extracción de método (Refactorización)
            temp = getHashEntry(key, temp);

            return temp.value;
        }

        return null;
    }

    //Resultado de hacer la extracción de método, se genera el método getHashEntry() para evitar repetir código en distintos métodos.
    private HashEntry getHashEntry(String key, HashEntry temp) {
        while(!temp.key.equals(key))
            temp = temp.next;
        return temp;
    }

    /**
     * Drop.
     *
     * @param key the key
     *
     *  El método drop() nos permite eliminar elementos de la tabla, para ello hemos de pasarle una String que ha de ser la clave del elemento que deseamos borrar.
     */
    public void drop(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = entries[hash];

            //Resultado de la extracción de método (Refactorización)
            temp = getHashEntry(key, temp);

            if(temp.prev == null) entries[hash] = null;             //esborrar element únic (no col·lissió)
            else{
                if(temp.next != null) temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
            }
        }

        //Modificación de código
        size--;
    }
    /**
     * Se utiliza para calcular el hash de un elemento que vamos a introducir en la tabla,
     * para ello se le pasa la clave del elemento y realiza una operación con ella y con el valor de INITIAL_SIZE.
     */
    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return Math.abs(key.hashCode()) % INITIAL_SIZE;
    }

    private class HashEntry {
        /**
         * The Key.
         */
        String key;
        /**
         * The Value.
         */
        String value;

        /**
         * The Next.
         */
// Linked list of same hash entries.
        HashEntry next;
        /**
         * The Prev.
         */
        HashEntry prev;

        /**
         * Instantiates a new Hash entry.
         *
         * @param key   the key
         * @param value the value
         */
        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    /**
     *  Nos muestra el contenido de la HashTable con la siguiente estructura:
     *  bucket[0] = [0, Elemento0]
     *  bucket[1] = [a, Elemento1]
     *  bucket[2] = [b, Elemento2] -> [1234, Original] -> [1234, SobreEscribir]
     *  bucket[3] = [c, Elemento3]
     *  bucket[4] = [d, Elemento4]
     *  bucket[5] = [e, Elemento5]
     *  bucket[6] = [f, Elemento6] -> [17, Test]
     *  bucket[7] = [g, Elemento7]
     *  bucket[8] = [h, Elemento8]
     *  bucket[9] = [i, Elemento9]
     *  bucket[10] = [j, Elemento10]
     *  bucket[11] = [k, Elemento11]
     *  bucket[12] = [l, Elemento12]
     *  bucket[13] = [m, Elemento13]
     *  bucket[14] = [n, Elemento14]
     *  bucket[15] = [o, Elemento15]
     */
    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if(entry == null) {
                bucket++;
                continue;
            }
            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    /**
     * Gets collisions for key.
     *
     * @param key the key
     * @return the collisions for key
     */
    public ArrayList<String> getCollisionsForKey(String key) {
        return getCollisionsForKey(key, 1);
    }

    /**
     * Get collisions for key array list.
     *
     * @param key      the key
     * @param quantity the quantity
     * @return the array list
     */
    public ArrayList<String> getCollisionsForKey(String key, int quantity){
        /*
          Main idea:
          alphabet = {0, 1, 2}

          Step 1: "000"
          Step 2: "001"
          Step 3: "002"
          Step 4: "010"
          Step 5: "011"
           ...
          Step N: "222"

          All those keys will be hashed and checking if collides with the given one.
        * */

        final char[] alphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Integer> newKey = new ArrayList();
        ArrayList<String> foundKeys = new ArrayList();

        newKey.add(0);
        int collision = getHash(key);
        int current = newKey.size() -1;

        while (foundKeys.size() < quantity){
            //building current key
            String currentKey = "";
            for(int i = 0; i < newKey.size(); i++)
                currentKey += alphabet[newKey.get(i)];

            if(!currentKey.equals(key) && getHash(currentKey) == collision)
                foundKeys.add(currentKey);

            //increasing the current alphabet key
            newKey.set(current, newKey.get(current)+1);

            //overflow over the alphabet on current!
            if(newKey.get(current) == alphabet.length){
                int previous = current;
                do{
                    //increasing the previous to current alphabet key
                    previous--;
                    if(previous >= 0)  newKey.set(previous, newKey.get(previous) + 1);
                }
                while (previous >= 0 && newKey.get(previous) == alphabet.length);

                //cleaning
                for(int i = previous + 1; i < newKey.size(); i++)
                    newKey.set(i, 0);

                //increasing size on underflow over the key size
                if(previous < 0) newKey.add(0);

                current = newKey.size() -1;
            }
        }

        return  foundKeys;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        
        // Put some key values.
        for(int i=0; i<30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        log("****   HashTable  ***");
        log(hashTable.toString());
        log("\nValue for key(20) : " + hashTable.get("20") );
    }

    private static void log(String msg) {
        System.out.println(msg);
    }
}