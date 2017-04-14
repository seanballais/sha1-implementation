package app;

import org.junit.*;
import static org.junit.Assert.*;

public class SHA1Test
{
    @Test public void SHA1HashingTest()
    {
        SHA1 sha1 = new SHA1();
        assertEquals(
            "73769065eabe2098139783639e6e438f4946eafe",
            sha1.digestMessage("Every time you smile at me, I feel weak.")
        );
        assertEquals(
            "d1becf0b8b5ce2c3a3324ed5ef2a9c69dd618ef7",
            sha1.digestMessage("One smile from you can already brighten up my day.")
        );
        assertEquals(
            "c63a4aec3db57e8c8f9f0e9046b4de4bc33e2fcf",
            sha1.digestMessage("You're the perfect combination of intellect and beauty.")
        );
        assertEquals(
            "ccef2d54d523d36d0f66eebdd1963b86d5a09b60",
            sha1.digestMessage("You're kind, beautiful, smart, and caring.")
        );
        assertEquals(
            "7ae0d467553a474265194fd90b1fb6523db1ba5f",
            sha1.digestMessage("I can't help but smile whenever I see you.")
        );
        assertEquals(
            "5553d465f435c8b040f4b5f708ef9c8715f96e6a",
            sha1.digestMessage("You're the most beautiful girl in the world.")
        );
        assertEquals(
            "4eb185fe1a349c548fab322c271eeb257f478f68",
            sha1.digestMessage("You make my dopamine levels all go silly.")
        );
        assertEquals(
            "6b92518db84b8a6cfda5f47dab38ad45a0c21b80",
            sha1.digestMessage("I glitch, struggling to keep myself at ease, whenever I'm with you.")
        );
        assertEquals(
            "71885d2450dc8cc3b728155bb80ee65103283f60",
            sha1.digestMessage("You're like the beautiful blooming flower in a field of grass.")
        );
        assertEquals(
            "daf44c8513dd414407c27b9b6002d58d1f646a8c",
            sha1.digestMessage("You make my CPU skip a bit.")
        );
        assertEquals(
            "f4443a67b7953b09cdd61a4389f7e04b34496878",
            sha1.digestMessage("Dafuq, Sean? WTF!")
        );
        assertEquals(
            "3f603532b89fc87807de250d8585e9dc04b149de",
            sha1.digestMessage("Really? Really now? When Sir Yuno sees this, he's probably gon' be making fun of this.")
        );
    }
}
