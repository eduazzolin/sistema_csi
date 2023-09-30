package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Classe criada para conter as funções relacionadas a criptografia.
 */
public class SegurancaController {
	
	/*
	 * O hash vem em um índice de alguma tabela de caracteres
	 * e depois é convertido para hex e retornado.
	 */
	public static String toHash(String message) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {}
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
