package app;

public class SHA1
{
    public int getPaddedMessageLengthOf(String message)
    {
        // Temporary function
        return this.padMessage(message).length;
    }

    public String digestMessage(String message)
    {
        byte[] messageBlocks = this.padMessage(message);
        int[] hashes = { 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0 };
        int[] constantWords = { 0x5A827999, 0x6ED9EBA1, 0x8F1BBCDC, 0xCA62C1D6 };

        int numPasses = messageBlocks.length / 64;
        byte[] messageBlock = new byte[64];

        for (int pass = 0; pass < numPasses; pass++) {
            System.arraycopy(messageBlocks, pass * 64, messageBlock, 0, 64);
            this.processBlock(messageBlock, hashes, constantWords);
        }


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

    private void processBlock(byte[] block, int[] h, int[] k)
    {
        int[] words = new int[80];
        for (int outer = 0; outer < 16; outer++) {
            for (int inner = 0; inner < 4; inner++) {
                words[outer] = words[outer] | (block[outer * 4 + inner] & 0xFF) << (24 - inner * 8);
            }
        }

        // Extend the 16 32-bit words into 80 32-bit words.
        for (int i = 16; i < 80; i++) {
            words[i] = this.rotateLeft(
                words[i - 3] ^ words[i - 8] ^ words[i - 14] ^ words[i - 16],
                1
            );
        }

        // Initialize hash value for this block.
        int a = h[0];
        int b = h[1];
        int c = h[2];
        int d = h[3];
        int e = h[4];
        int f = 0;

        for (int i = 0; i < 80; i++) {
            if (i >= 0 && i <= 19) {
                f = (b & c) | (~b & d);
            } else if (i >= 20 && i <= 39) {
                f = b ^ c ^ d;
            } else if (i >= 40 && i <= 59) {
                f = (b & c) | (b & d) | (c & d);
            } else if (i >= 60 && i <= 79) {
                f = b ^ c ^ d;
            }

            int newAVal = this.rotateLeft(a, 5) + f + e + k[Math.floorDiv(i, 20)] + words[i];
            e = d;
            d = c;
            c = this.rotateLeft(b, 30);
            b = a;
            a = newAVal;
        }

        h[0] += a;
        h[1] += b;
        h[2] += c;
        h[3] += d;
        h[4] += e;
    }

    private byte[] concatenateByteArrays(byte[] first, byte[] second)
    {
        byte[] concatenated = new byte[first.length + second.length];

        System.arraycopy(first, 0, concatenated, 0, first.length);
        System.arraycopy(second, 0, concatenated, first.length, second.length);

        return concatenated;
    }

    private int rotateLeft(int value, int offset)
    {
        return (value << offset) | (value >>> (32 - offset));
    }

    private String stringifyHash(int[] hash)
    {
        byte[] finalHash = new byte[20];
        for (int i = 0; i < hash.length; i++) {
            for (int j = 0; j < 4; j++) {
                finalHash[]
            }
        }
    }
}
