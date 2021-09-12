package com.company;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HMACGenerator {
    private static final String HMAC_ALGO = "HmacSHA256";
    public byte[] generateBytes() {
        final int KEY_SIZE = 128 / 8;
        byte []bytes = new byte[KEY_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }
    public String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b: bytes) {

            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    public byte[] generateHMAC(String message, byte[] bytes) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac signer  = Mac.getInstance(HMAC_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(bytes, HMAC_ALGO);
        signer.init(keySpec);

        return signer.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }
}