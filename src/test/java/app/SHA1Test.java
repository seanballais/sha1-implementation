package app;

import org.junit.*;
import static org.junit.Assert.*;

public class SHA1Test
{
    @Test public void paddedMessageLengthTest()
    {
        SHA1 sha1 = new SHA1();
        assertEquals(
            "Result should not have an excess byte",
            0,
            sha1.getPaddedMessageLengthOf("hi!") % 64
        );
    }
}
