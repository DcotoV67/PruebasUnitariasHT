package ex4;

import ex1.HashTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HashTableTest {
    HashTable ht = new HashTable();

    @Test
    void size() {

        /*
            El código fuente no aumenta el valor de la variable size cuando se añade un elemento a la hashtable con el método put().
            Por lo tanto he modificado el método put() para que cada vez que se ejecute sume 1 a la variable size.

            Tampoco reduce el valor de size cuando se elimina un elemento con el método drop(), he modificado el método drop() para que
            cuando se elimine un elemento reste 1 a size.
        */

        //Size = 0, probamos el método size() sin añadir nada a la hashtable.
        Assertions.assertEquals(ht.size(), 0);
        Assertions.assertEquals(0, ht.size());

        //Size = 1, al añadir un elemento a la hashtable size debería ser 1 (0+1).
        ht.put("1","Elemento1");
        Assertions.assertEquals(ht.size(), 1);
        Assertions.assertEquals(1, ht.size());

        //Size = 2, al añadir otro elemento size debería aumentar en 1 pasando a ser 2 (1+1).
        ht.put("2","Elemento2");
        Assertions.assertEquals(ht.size(), 2);
        Assertions.assertEquals(2, ht.size());
        System.out.println(ht.size());

        /*
            Size = 3, al añadir un elemento en un bucket que no esté vacío este también aumenta el valor de size en 1,
            no sobreescribe el valor que se encontraba antes en el bucket, por lo tanto pasa a ser 3 (2+1).
         */
        ht.put("2","Elemento3");
        Assertions.assertEquals(ht.size(), 3);
        Assertions.assertEquals(3, ht.size());

        //Size = 2, Al borrar un elemento el tamaño también ha de reducirse, por lo tanto pasa a ser 2 (3-1)
        ht.drop("1");
        Assertions.assertEquals(ht.size(), 2);
        System.out.println(ht.toString());


    }

    @Test
    void realSize() {

        Assertions.assertEquals(ht.realSize(), 16);

    }

    @Test
    void put() {

//        Assertions.assertEquals("Elemento1","Elemento1");

//        Assertions.assertEquals("Elemento2","Elemento2");

//        ht.put(null, "NULO"); // -> Clave  Nula

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

        // Con las claves: Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        //                 Ttttttttttttttttttttttttttttttttttt
        //                 Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb
        //                 Ccccccccccccccccccccccccccccccccccc
        //                 Rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr

        // Da el siguiente error: java.lang.ArrayIndexOutOfBoundsException: Index -15 out of bounds for length 16  --- Esto ya ha sido solucionado por Fer

        // En cambio, con la clave: Lllllllllllllllllllllllllllllllllll no da errores. Todas estas claves tienen una longitud de 35 caracteres

        /*
         Se puede introducir cualquier caracter, esto incluye caracteres como ♣, ⊗, ℜ o ψ, incluso funciona con emoticonos. Tambíen podemos escapar unas comillas dobles ", simplemente ponemos \"
         y la clave pasará a ser ". Se puede poner un salto de linea como clave \n y seguirá funcionando.
        */

        String test = "17";
        System.out.println(test.hashCode());


        int hash = test.hashCode();
        int mod = hash%ht.realSize();

        System.out.println(mod);

        ht.put(test, "Test");


        //Como se puede observar a continuación no sobreescribe elementos con la misma clave, simplemente crea una colisión entre ellos, si en vez de usar put() usaramos drop() eliminaria ambos
        ht.put("1234","Original");
        System.out.println(ht.toString());

        ht.put("1234", "SobreEscribir");
        System.out.println(ht.toString());


    }

    @Test
    void get() {

        //Si ejecutamos el metodo get() sobre un elemento que no existe o bien hemos borrado con drop() nos retornará null, no confundir con un elemento vacio, es nulo.

        ht.put("1", "Elemento1");
        ht.put("12", "Elemento12");
        ht.put("a", "ElementoA");
        ht.put("3", null);

        System.out.println(ht.toString());


        //El método get() funciona con un elemento aún estando en colisión, podemos coger tanto el primer como el último elemento pasando por cualquiera que se encuentre en medio.

        Assertions.assertEquals("Elemento1", ht.get("1"));
        Assertions.assertEquals("Elemento12", ht.get("12"));
        Assertions.assertEquals("ElementoA", ht.get("a"));

        //El método get() también funciona si el valor de un elemento es nulo.

        Assertions.assertEquals(null, ht.get("3"));

        //Pero en cambio no funciona si lo ejecutamos sobre elementos que no existen, tanto si esperamos que nos devuelva null o nada ("").

        Assertions.assertEquals(null, ht.get("Fer"));
        Assertions.assertEquals("", ht.get("reF"));



    }

    @Test
    void drop() {
        /*
            Borrar elemento en bucket vacio -> el programa no hace nada, puede dar una excepción al borrar algo que no existe, pero no null pointer, stack overflow, o excepciones asi
            Borrar elemento que no existe pero está en un bucket con colisiones -> igual que el anterior
            Borrar elemento que existe y no colisiona en el final de la lista -> solo ha de borrar ese elemento
            Borrar elemento en el medio de una colision en la lista -> next.prev = next.prev.prev / prev.next = prev.next.next
            Borrar el ultimo elemento de una colision -> prev.next = null
       ***  Borrar el primer elemento de una colision -> Da error
        */

        ht.put("1", null);
        ht.put("12", "HOLA");
        ht.put("a", "ADIOS");
        ht.put("q", "HEY");
        ht.put("2", "Elemento2");

        ht.put("o", "FINAL");

        ht.put("f", "MITAD");
        ht.put("17", "BORRAR");

        System.out.println(ht.toString());

        //Borrar el primer elemento de un bucket en el que hay colisión: Esto hace que se borre el bucket entero en vez del primer elemento.
        ht.drop("1");

        //En cambio podemos borrar un elemento que no sea el primero, probando con un elemento en mitad de la colisión no hay problemas, solo borra ese elemento
        ht.drop("12");

        //Lo mismo pasa cuando se borra el último elemento de una colisión, tan solo se borra ese elemento
        ht.drop("q");

        //No nos da ningún error al eliminar un elemento que no existe
        ht.drop("4");

        //También funciona correctamente al borrar un elemento en colisión en mitad de la HashTable
        ht.drop("17");

        //Y tampoco tenemos problemas al borrar el último elemento de la HashTable
        ht.drop("o");

        System.out.println(ht.toString());

    }

}