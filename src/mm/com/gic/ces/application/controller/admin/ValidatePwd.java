/**
 * CV_A_071_権限設定画面
 * 作成履歴：09/04/2019 Cho Cho Lwin
 * 作成概要：新規作成　Encryptパスワード処理
 * 
 * 更新履歴：dd/mm/yyyy name
 * 更新概要：XXXXXXXX	
 */

package mm.com.gic.ces.application.controller.admin;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * パスワードをEncryptする
 */
public class ValidatePwd {

	/**
	 * ...パスワードチェック...
	 * @param originalPassword
	 * @param storedPassword
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		if(!storedPassword.contains(":")){
		    return false;
		} else {
		    String[] parts = storedPassword.split(":");
		    int iterations = Integer.parseInt(parts[0]);
		    byte[] salt = fromHex(parts[1]);
		    byte[] hash = fromHex(parts[2]);
		    PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		    byte[] testHash = skf.generateSecret(spec).getEncoded();
		    int diff = hash.length ^ testHash.length;
		    for (int i = 0; i < hash.length && i < testHash.length; i++) {
			    diff |= hash[i] ^ testHash[i];
		    }
		    return diff == 0;
		}
	}

	/**
	 * ...強いパスワードを出力する...
	 * @param password パスワード
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt().getBytes();
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	/**
	 * ...Salt値を取得する...
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}

	/**
	 * ...Hex値を取得する...
	 * @param array
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	/**
	 * ...Hex値をByteとして変わる...
	 * @param hex
	 * @return bytes
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
