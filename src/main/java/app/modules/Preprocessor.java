package app.modules;

import java.math.BigInteger;

public class Preprocessor
{
    private byte[] padMessage(String message)
    {
        byte[] messageBytes = message.getBytes();
        int originalLength = messageBytes.length;
        int numMissingBytes = 64 - (originalLength % 64); // Get the number of bits required
                                                          // to reach 64 bytes.

        int padLength;
        if (numMissingBytes <= 64) {
            // Oops! Not enough bits to store the required 8 bytes for the message length.
            // Use a new 512-bit block to store the message length instead.
            padLength = 64 + numMissingBytes;
        } else {
            // Bruh, we've got enough space for the bits to be padded to the original message.
            padLength = numMissingBytes;
        }

        BigInteger paddedBits = BigInteger.valueOf(1);
        paddedBits = paddedBits.shiftLeft((padLength * 8) - 1); // The padded bits must always
                                                                // start with one since we need
                                                                // to append 1 to the original
                                                                // message. This will give us a
                                                                // value similar to 10000000000... .
                                                                // This makes generating the padding
                                                                // easier for us as you will see later.

        paddedBits = paddedBits.or(BigInteger.valueOf(messageBytes.length * 8)); // Set the remaining
                                                                                 // 64 bits to the
                                                                                 // length of the
                                                                                 // message.

        return this.concatenateByteArrays(messageBytes, paddedBits.toByteArray());
    }

    private byte[] concatenateByteArrays(byte[] first, byte[] second)
    {
        byte[] concatenated = new byte[first.length + second.length];
        for (int i = 0; i < first.length; i++) {
            concatenated[i] = first[i];
        }

        for (int i = first.length; i < concatenated.length; i++) {
            concatenated[i] = second[i];
        }

        return concatenated;
    }
}
