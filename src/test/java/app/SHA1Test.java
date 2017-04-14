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
    }
}
