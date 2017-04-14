package app;

import java.math.BigInteger;

public class SHA1
{
    public int getPaddedMessageLengthOf(String message)
    {
        // Temporary function
        return this.padMessage(message).length;
    }

    private byte[] padMessage(String message)
    {
        byte[] messageBytes = message.getBytes(); // I doubt anyone will have a 2 exabyte file anytime
                                                  // soon.
        int originalLength = messageBytes.length;
        int numMissingBytes = 64 - (originalLength % 64); // Get the number of bits required
                                                          // to reach 64 bytes.

        int padLength;
        if (numMissingBytes <= 8) {
            // Oops! Not enough bytes to store the required 8 bytes for the message length.
            // Use a new 512-bit block to store the message length instead.
            padLength = 64 + numMissingBytes;
        } else {
            // Nigga, we've got enough space for the bits to be padded to the original message.
            padLength = numMissingBytes;
        }

        byte[] paddedBytes = new byte[padLength];
        paddedBytes[0] = (byte) 0x80; // 0x80 = 128 = 1000 0000
        long bitLength = originalLength * 8;

        for (int i = 0; i < 8; i++) {
            paddedBytes[paddedBytes.length - 1 - i] = (byte) ((bitLength >> (8 * i)) & 0xFF);
        }

        return this.concatenateByteArrays(messageBytes, paddedBytes);
    }

    private byte[] concatenateByteArrays(byte[] first, byte[] second)
    {
        byte[] concatenated = new byte[first.length + second.length];

        System.arraycopy(first, 0, concatenated, 0, first.length);
        System.arraycopy(second, 0, concatenated, first.length, second.length);

        return concatenated;
    }
}
