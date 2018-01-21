package com.company.controller.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    private static final String ALGORITHM = "SHA-256";
    private static final int SALT = 0x100;

    public static String getHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(password.getBytes());
            StringBuilder hash = new StringBuilder();
            for (byte byt : md.digest())
                hash.append(Integer.toString((byt & 0xff) + SALT, 16)
                        .substring(1));
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
