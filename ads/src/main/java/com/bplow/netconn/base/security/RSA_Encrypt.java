package com.bplow.netconn.base.security;

import javax.crypto.Cipher;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class RSA_Encrypt {
	
	Logger log = LoggerFactory.getLogger(Base64Encode.class);
	
	/** 指定加密算法为DESede */
	private static String ALGORITHM = "RSA";
	/** 指定key的大小 */
	private static int KEYSIZE = 1024;
	/** 指定公钥存放文件 */
	private static String PUBLIC_KEY_FILE = "d:/PublicKey";
	/** 指定私钥存放文件 */
	private static String PRIVATE_KEY_FILE = "d:/PrivateKey";

	/**
	 * 生成密钥对
	 */
	public void generateKeyPair() throws Exception {
		/** RSA算法要求有一个可信任的随机数源 */
		SecureRandom sr = new SecureRandom();
		/** 为RSA算法创建一个KeyPairGenerator对象 */
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		/** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
		kpg.initialize(KEYSIZE, sr);
		/** 生成密匙对 */
		KeyPair kp = kpg.generateKeyPair();
		/** 得到公钥 */
		Key publicKey = kp.getPublic();
		/** 得到私钥 */
		Key privateKey = kp.getPrivate();
		/** 用对象流将生成的密钥写入文件 */
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(
				PUBLIC_KEY_FILE));
		ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(
				PRIVATE_KEY_FILE));
		oos1.writeObject(publicKey);
		oos2.writeObject(privateKey);
		
		sun.misc.BASE64Encoder  b64 =  new sun.misc.BASE64Encoder();
		log.info("before encode:",new String (publicKey.getEncoded()));
        String pkStr = b64.encode(publicKey.getEncoded());
		FileUtils.writeStringToFile(new File("D:/pub.dat"), pkStr);
		
		log.info("after encode:"+pkStr);
		
		/** 清空缓存，关闭文件输出流 */
		oos1.close();
		oos2.close();
	}
	
	/**
	 * 
	 * @param in
	 * @param address
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public void generateKeyPair(int in, String address) throws IOException, NoSuchAlgorithmException{
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA"); //创建‘密匙对’生成器
        kpg.initialize(in); //指定密匙长度（取值范围：512～2048）
        KeyPair kp = kpg.genKeyPair(); //生成‘密匙对’，其中包含着一个公匙和一个私匙的信息
        Key public_key = kp.getPublic(); //获得公匙
        Key private_key = kp.getPrivate(); //获得私匙
       
        sun.misc.BASE64Encoder  b64 =  new sun.misc.BASE64Encoder();
        String pkStr = b64.encode(public_key.getEncoded());
        String prStr = b64.encode(private_key.getEncoded());
        System.out.print("pkStr length:" +pkStr.length() +pkStr);

        FileWriter fw=new FileWriter(address + "/private_key.dat");
        fw.write(prStr);
        fw.close();
       
        FileWriter  fw2 = new FileWriter(address + "/public_key.dat");
        fw2.write(pkStr);
        fw2.close();
	}

	/**
	 * 加密方法 source： 源数据
	 */
	public String encrypt(String source) throws Exception {
		this.generateKeyPair();
		/** 将文件中的公钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				PUBLIC_KEY_FILE));
		Key key = (Key) ois.readObject();
		ois.close();
		/** 得到Cipher对象来实现对源数据的RSA加密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] b = source.getBytes();
		/** 执行加密操作 */
		byte[] b1 = cipher.doFinal(b);
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(b1);
	}

	/**
	 * 解密算法 cryptograph:密文
	 */
	public static String decrypt(String cryptograph) throws Exception {
		/** 将文件中的私钥对象读出 */
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				PRIVATE_KEY_FILE));
		Key key = (Key) ois.readObject();
		/** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b1 = decoder.decodeBuffer(cryptograph);
		/** 执行解密操作 */
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

}