package com.example.deepinfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {

    Utility() {}

    public String md5(String s){
        try{
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i< s.length(); i++){
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
                return hexString.toString();
            }
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
}
