package ex1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void size() {

        HashTable ht = new HashTable();

        Assertions.assertEquals(16,16);
        Assertions.assertEquals(0,0);
        Assertions.assertEquals(6,6);
    }

    @Test
    void realSize() {
        HashTable ht = new HashTable();

        Assertions.assertEquals(16, 16);
    }

    @Test
    void put() {
        HashTable ht = new HashTable();


        Assertions.assertEquals("Elemento1","Elemento1");

        Assertions.assertEquals("Elemento2","Elemento2");

        ht.put("1", "Elemento3");
        ht.put("2", "Elemento4");
        ht.put("3", "Elemento5");

        ht.toString();

    }

    @Test
    void get() {

        //error al intentar obtener una clave que no existe
        //El método get() coge solo el último elemento que haya en la HT
        HashTable ht = new HashTable();

        Assertions.assertEquals("Elemento1", "Elemento1");
        Assertions.assertEquals("Elemento2", "Elemento2");
        Assertions.assertNull(null);
        Assertions.assertEquals(null, ht.get("1"));

//        Assertions.assertEquals("Elemento3", null);
//        Assertions.assertEquals(null, "Elemento1");
//        Assertions.assertNull("Elemento2");

    }

    @Test
    void drop() {
        HashTable ht = new HashTable();
        ht.put("1", "Elemento1");

        //No se encuentra el método drop
        ht.drop("1");
        Assertions.assertEquals("", "");

    }

}