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

        ht.put(null, "NULO");

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

         Por eso no podemos pasarle un elemento con clave nula, pero si con valor nulo.
        */

        // Con la clave: Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa da el siguiente error: java.lang.ArrayIndexOutOfBoundsException: Index -15 out of bounds for length 16

        /*
         Se puede introducir cualquier caracter, esto incluye caracteres como ♣, ⊗, ℜ o ψ, incluso funciona con emoticonos. Tambíen podemos escapar unas comillas dobles ", simplemente ponemos \"
         y la clave pasará a ser ". Se puede poner un salto de linea como clave \n y seguirá funcionando.
        */

        //REALMENTE FUNCIONA CON CUALQUIER METODO QUE REQUIERA UNA STRING

        String test = "\uD83D\uDE00";
        System.out.println(test.hashCode());

        int hash = test.hashCode();
        int mod = hash%ht.realSize();

        System.out.println(mod);

        ht.put(test, "Test");

        System.out.println(ht.toString());

    }

    @Test
    void get() {

        //Si ejecutamos el metodo get() sobre un elemento que no existe o bien hemos borrado con drop() nos retornará null, no confundir con un elemento vacio, es nulo.

        Assertions.assertEquals("Elemento1", "Elemento1");
        Assertions.assertEquals("Elemento2", "Elemento2");
        Assertions.assertNull(null);
        Assertions.assertEquals(null, ht.get("1"));

//        Assertions.assertEquals("Elemento3", null);
//        Assertions.assertEquals(null, "Elemento1");
//        Assertions.assertNull("Elemento2");


        System.out.println(ht.toString());

    }

    @Test
    void drop() {
        /*
         No podemos ejecutar el método drop() sobre un elemento que no exista pero si sobre un elemento con valor nulo.
        */
        ht.put("1", null);
        ht.put("2", "Elemento2");

        ht.drop("1");
        System.out.println(ht.get("1"));
        System.out.println(ht.get("2"));

    }

}