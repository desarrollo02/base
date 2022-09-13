/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ping.utils;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 *
 * @author rudy
 */
public class SimpleKeyGenerator implements KeyGenerator {
    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public Key generateKey() {
    //        String keyString = "simplekey";
    //        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "SHA-256");
        Key key = MacProvider.generateKey();
        return key;
    }

}
