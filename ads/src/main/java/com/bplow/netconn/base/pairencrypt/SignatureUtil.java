package com.bplow.netconn.base.pairencrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.lang.StringUtils;

public class SignatureUtil {

	/**
     * 使用私钥进行签名的方法
     * 
     * @param text
     *            待签名的数据
     * @param privateKeyData
     *            私钥数据
     * @param algorithm
     *            签名算法,目前KMI支持NONEwithRSA, MD2withRSA, MD5withRSA, SHA1withRSA, SHA256withRSA,
     *            SHA384withRSA, SHA512withRSA , SHA1withDSA
     * @return 签名后的数据
     * @throws GeneralSecurityException
     */
    public static byte[] sign(final byte[] text, final byte[] privateKeyData, final String algorithm)
                                                                                                     throws GeneralSecurityException {
        final PrivateKey privateKey = getPrivateKey(privateKeyData, algorithm);
        final Signature signatureChecker = Signature.getInstance(algorithm);
        signatureChecker.initSign(privateKey);
        signatureChecker.update(text);
        return signatureChecker.sign();
    }

    /**
     * 使用公钥进行验签的方法
     * 
     * @param text
     *            原始数据数据
     * @param signedText
     *            签名过的数据
     * @param publicKeyData
     *            公钥数据
     * @param algorithm
     *            签名算法,目前KMI支持NONEwithRSA, MD2withRSA, MD5withRSA, SHA1withRSA, SHA256withRSA,
     *            SHA384withRSA, SHA512withRSA , SHA1withDSA
     * @return 如果验签成功,返回true,验签失败,返回false
     * @throws GeneralSecurityException
     */
    public static boolean verify(final byte[] text, final byte[] signedText,
                                 final byte[] publicKeyData, final String algorithm)
                                                                                    throws GeneralSecurityException {
        final PublicKey publicKey = getPublicKey(publicKeyData, algorithm);
        final Signature signatureChecker = Signature.getInstance(algorithm);
        signatureChecker.initVerify(publicKey);
        signatureChecker.update(text);
        return signatureChecker.verify(signedText);
    }

    /**
     * 使用证书进行验签的方法
     * 
     * @param text
     *            原始数据数据
     * @param signedText
     *            签名过的数据
     * @param cert
     *            证书文件
     * @param algorithm
     *            签名算法,目前KMI支持NONEwithRSA, MD2withRSA, MD5withRSA, SHA1withRSA, SHA256withRSA,
     *            SHA384withRSA, SHA512withRSA , SHA1withDSA
     * @return 如果验签成功,返回true,验签失败,返回false
     * @throws GeneralSecurityException
     * @throws IOException 
     */
    public static boolean verify(final byte[] text, final byte[] signedText, final File cert,
                                 final String algorithm) throws GeneralSecurityException,
                                                        IOException {
        final PublicKey publicKey = getPublicKey(cert);
        final Signature signatureChecker = Signature.getInstance(algorithm);
        signatureChecker.initVerify(publicKey);
        signatureChecker.update(text);
        return signatureChecker.verify(signedText);
    }

    /**
     * 得到公钥
     * 
     * @param keyData
     *            密钥数据
     * @throws GeneralSecurityException
     */
    private static PublicKey getPublicKey(final byte[] keyData, final String algorithm)
                                                                                       throws GeneralSecurityException {
        final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyData);
        final KeyFactory keyFactory = KeyFactory.getInstance(StringUtils.substringAfter(algorithm,
            "with"));
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 得到公钥
     * 
     * @param cert
     *            证书文件
     * @throws GeneralSecurityException
     * @throws IOException 
     */
    private static PublicKey getPublicKey(final File cert) throws GeneralSecurityException,
                                                          IOException {
        final InputStream certIn = new FileInputStream(cert);
        final CertificateFactory cf1 = CertificateFactory.getInstance("X.509");
        final X509Certificate certificate = (X509Certificate) cf1.generateCertificate(certIn);
        return certificate.getPublicKey();
    }

    /**
     * 得到私钥
     * 
     * @param keyData
     *            密钥数据
     * @throws GeneralSecurityException
     */
    private static PrivateKey getPrivateKey(final byte[] keyData, final String algorithm)
                                                                                         throws GeneralSecurityException {
        final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyData);
        final KeyFactory keyFactory = KeyFactory.getInstance(StringUtils.substringAfter(algorithm,
            "with"));
        return keyFactory.generatePrivate(keySpec);
    }
}
