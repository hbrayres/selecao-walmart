package br.com.selecao.wallmart.util;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class KeyGeneratorImpl implements KeyGenerator {

	@Override
	public Key generateKey() {
		
		String keyString = "walmart-key";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
		
	}

}
