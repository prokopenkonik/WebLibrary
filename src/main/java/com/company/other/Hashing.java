package com.company.other;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static void main(String[] args) {
        try {
            String pass = "123";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());
            StringBuffer result = new StringBuffer();
            for (byte byt : md.digest())
                result.append(Integer.toString((byt & 0xff) + 0x100, 16)
                        .substring(1));
            System.out.println(result.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
