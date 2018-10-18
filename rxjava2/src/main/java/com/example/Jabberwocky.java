package com.example;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;

public class Jabberwocky {

    public static BufferedReader getReader() {
        return new BufferedReader(new StringReader(
            "’Twas brillig, and the slithy toves\n"+
            "Did gyre and gimble in the wabe;\n"+
            "All mimsy were the borogoves,\n"+
            "And the mome raths outgrabe.\n"+
            "\n"+
            "“Beware the Jabberwock, my son!\n"+
            "The jaws that bite, the claws that catch!\n"+
            "Beware the Jubjub bird, and shun\n"+
            "The frumious Bandersnatch!”\n"+
            "\n"+
            "He took his vorpal sword in hand:\n"+
            "Long time the manxome foe he sought—\n"+
            "So rested he by the Tumtum tree,\n"+
            "And stood awhile in thought.\n"+
            "\n"+
            "And as in uffish thought he stood,\n"+
            "The Jabberwock, with eyes of flame,\n"+
            "Came whiffling through the tulgey wood,\n"+
            "And burbled as it came!\n"+
            "\n"+
            "One, two! One, two! And through and through\n"+
            "The vorpal blade went snicker-snack!\n"+
            "He left it dead, and with its head\n"+
            "He went galumphing back.\n"+
            "\n"+
            "“And hast thou slain the Jabberwock?\n"+
            "Come to my arms, my beamish boy!\n"+
            "O frabjous day! Callooh! Callay!”\n"+
            "He chortled in his joy.\n"+
            "\n"+
            "’Twas brillig, and the slithy toves\n"+
            "Did gyre and gimble in the wabe;\n"+
            "All mimsy were the borogoves,\n"+
            "And the mome raths outgrabe.\n"));
    }

    public static void tryToClose(Closeable c) {
        if ( c != null ) {
            try {
                c.close();
            } catch(IOException e) {}
        }
    }
}
