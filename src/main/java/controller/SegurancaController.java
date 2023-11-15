package controller;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class SegurancaController {
   /* Classe criada para conter as funções relacionadas a criptografia. */

   public static final String ALGORITHM = "RSA";

   /***
    * Gera um par de chaves
    * @return um array composto pela chave privada e pública em forma de byte[]
    */
   public static byte[][] generateKey() {
      try {
         final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
         keyGen.initialize(4096);
         final KeyPair keys = keyGen.generateKeyPair();

         byte[][] chaves = new byte[2][];
         chaves[0] = keys.getPrivate().getEncoded();
         chaves[1] = keys.getPublic().getEncoded();

         return chaves;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   /***
    * criptografa a string e retorna em forma de byte[]
    * @param content mensagem a ser criptografada em String
    * @param byteKey chave pública em byte[]
    * @return byte[] mensagem criptografada
    * @throws NoSuchAlgorithmException
    * @throws InvalidKeySpecException
    * @throws NoSuchPaddingException
    * @throws InvalidKeyException
    * @throws IllegalBlockSizeException
    * @throws BadPaddingException
    */
   public static byte[] encrypt(String content, byte[] byteKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

      /* transformar a chave de byte[] para PublicKey */
      X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      PublicKey key = kf.generatePublic(X509publicKey);

      /* transformar a mensagem de String para byte[] */
      byte[] contentBytes = content.getBytes();

      /* criptografar */
      final Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher.doFinal(contentBytes);
   }


   /***
    * Descriptografa o blob e retorna em formato de string
    * @param content mensagem criptografada em byte[]
    * @param byteKey chave privada em byte[]
    * @return String mensagem descriptografada
    * @throws NoSuchAlgorithmException
    * @throws NoSuchPaddingException
    * @throws InvalidKeyException
    * @throws IllegalBlockSizeException
    * @throws BadPaddingException
    * @throws InvalidKeySpecException
    */
   public static String decrypt(byte[] content, byte[] byteKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {

      /* transformar a chave de byte[] para PrivateKey */
      PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(byteKey);
      KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
      PrivateKey privateKey = keyFactory.generatePrivate(spec);

      /* descriptografar */
      final Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, privateKey);
      byte[] dectyptedText = cipher.doFinal(content);

      /* retornar em formato de String */
      return new String(dectyptedText);
   }

   /***
    * Converte a mensagem em hash hexadecimal
    * @param message mensagem a ser convertida em hash
    * @return mensagem convertida em hash
    */
   public static String toHash(String message) {
      MessageDigest md = null;
      try {
         md = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
      }
      byte[] messageInBytes = message.getBytes();
      md.update(messageInBytes);
      byte[] digest = md.digest();
      StringBuffer hexString = new StringBuffer();
      for (int i = 0; i < digest.length; i++) {
         if ((0xff & digest[i]) < 0x10) {
            hexString.append("0" + Integer.toHexString((0xFF & digest[i])));
         } else {
            hexString.append(Integer.toHexString(0xFF & digest[i]));
         }
      }
      return hexString.toString();
   }

}
