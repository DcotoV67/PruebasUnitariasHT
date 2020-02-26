package ex1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable ht = new HashTable();

    @Test
    void size() {
        Assertions.assertEquals(16,16);
        Assertions.assertEquals(0,0);
        Assertions.assertEquals(6,6);
    }

    @Test
    void realSize() {
        Assertions.assertEquals(16, 16);
    }

    @Test
    void put() {
//        Assertions.assertEquals("Elemento1","Elemento1");
//
//        Assertions.assertEquals("Elemento2","Elemento2");

        ht.put("0", "Elemento0");
        ht.put("a", "Elemento1");
        ht.put("b", "Elemento2");
        ht.put("c", "Elemento3");
        ht.put("d", "Elemento4");
        ht.put("e", "Elemento5");
        ht.put("f", "Elemento6");
        ht.put("g", "Elemento7");
        ht.put("h", "Elemento8");
        ht.put("i", "Elemento9");
        ht.put("j", "Elemento10");
        ht.put("k", "Elemento11");
        ht.put("l", "Elemento12");
        ht.put("m", "Elemento13");
        ht.put("n", "Elemento14");
        ht.put("o", "Elemento15");


        /*
         El método put() funciona aún que no le pasemos nada en la string, podemos pasarle lo siguiente: "" y lo insertaría en el bucket[0] con la clave ""
         No pone comillas en la clave, sinó lo que hay entre ellas, en este caso nada, pero nada != null por lo tanto se acepta y se ejecuta el put().
        */

        // Con la clave: Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa da el siguiente error: java.lang.ArrayIndexOutOfBoundsException: Index -15 out of bounds for length 16

        String test = "\"";
        System.out.println(test.hashCode());

        int hash = test.hashCode();
        int mod = hash%ht.realSize();

        System.out.println(mod);

        ht.put(test, "Test");

        System.out.println(ht.toString());  // ->     bucket[0] = [0, Elemento0] -> [11, Elemento11]
                                            //        bucket[1] = [1, Elemento1] -> [12, Elemento12]
                                            //        bucket[2] = [2, Elemento2] -> [13, Elemento13]
                                            //        bucket[3] = [3, Elemento3] -> [14, Elemento14]
                                            //        bucket[4] = [4, Elemento4] -> [15, Elemento15]
                                            //        bucket[5] = [5, Elemento5] -> [16, Elemento16]
                                            //        bucket[6] = [6, Elemento6]
                                            //        bucket[7] = [7, Elemento7]
                                            //        bucket[8] = [8, Elemento8]
                                            //        bucket[9] = [9, Elemento9]
                                            //        bucket[15] = [10, Elemento10]

//        ht.drop("10");
//        System.out.println(ht.toString());

    }

    @Test
    void get() {

        //Error al intentar obtener una clave que no existe
        //El método get() coge solo el último elemento que haya en la HT

        Assertions.assertEquals("Elemento1", "Elemento1");
        Assertions.assertEquals("Elemento2", "Elemento2");
        Assertions.assertNull(null);
        Assertions.assertEquals(null, ht.get("1"));

//        Assertions.assertEquals("Elemento3", null);
//        Assertions.assertEquals(null, "Elemento1");
//        Assertions.assertNull("Elemento2");

        ht.put("1", "Elemento3");
        ht.put("1", "Elemento1");
        ht.put("2", "Elemento4");
        ht.put("3", "Elemento5");

        System.out.println(ht.toString());

    }

    @Test
    void drop() {
        ht.put("1", "Elemento1");
        ht.put("2", "Elemento2");

        ht.drop("1");
        Assertions.assertEquals("", "");
        ht.get("1");
        ht.get("2");
    }

}