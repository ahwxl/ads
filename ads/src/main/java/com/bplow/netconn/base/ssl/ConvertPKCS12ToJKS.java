package com.bplow.netconn.base.ssl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class ConvertPKCS12ToJKS {

	// 证书 式
	public static final String PKCS12 = "PKCS12";
	public static final String JKS = "JKS";

	// 证书和路径
	public static final String INPUT_KEYSTORE_FILE = "c:/mykey.p12";
	public static final String KEYSTORE_PASSWORD = "12345678";
	public static final String OUTPUT_KEYSTORE_FILE = "c:/change.jks";

	// 证书别名
	public static final String CERT_ALIAS = "change";

	public static void main(String[] args) throws Exception {
		KeyStore inputKeyStore = KeyStore.getInstance(PKCS12);
		// 载P12证书
		FileInputStream fis = new FileInputStream(INPUT_KEYSTORE_FILE);
		// P12证书密
		char[] nPassword = KEYSTORE_PASSWORD.toCharArray();
		inputKeyStore.load(fis, nPassword);
		fis.close();
		System.out.println("keystore type=" + inputKeyStore.getType());

		KeyStore outputKeyStore = KeyStore.getInstance(JKS);
		outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());
		Enumeration<String> enumStrs = inputKeyStore.aliases();
		while (enumStrs.hasMoreElements()) {
			String keyAlias = enumStrs.nextElement();
			System.out.println("alias=[" + keyAlias + "]");
			if (inputKeyStore.isKeyEntry(keyAlias)) {
				Key key = inputKeyStore.getKey(keyAlias, nPassword);
				Certificate[] certChain = inputKeyStore
						.getCertificateChain(keyAlias);
				outputKeyStore.setKeyEntry(CERT_ALIAS, key,
						KEYSTORE_PASSWORD.toCharArray(), certChain);
			}
		}
		FileOutputStream out = new FileOutputStream(OUTPUT_KEYSTORE_FILE);
		outputKeyStore.store(out, nPassword);
		out.close();
	}

}