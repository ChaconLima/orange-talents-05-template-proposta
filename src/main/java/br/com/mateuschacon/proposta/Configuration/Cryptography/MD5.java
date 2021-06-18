package br.com.mateuschacon.proposta.Configuration.Cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class MD5 {
    
    public String md5(String value) {
        String md5 = null;
        
        if(null == value) return null;
        
        try{
            MessageDigest digest = MessageDigest.getInstance("MD5");
            
            digest.update(value.getBytes(), 0, value.length());
            
            md5 = new BigInteger(1,digest.digest()).toString(16);
        
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return md5;
    }
}
