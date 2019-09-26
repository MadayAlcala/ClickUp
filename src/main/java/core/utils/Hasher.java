package core.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;

import org.apache.commons.codec.DecoderException;
import static org.apache.commons.codec.binary.Hex.decodeHex;
import static org.apache.commons.io.FileUtils.readFileToByteArray;

/**
 * Decrypts hashed passwords.
 *
 * @author Alejandro Sánchez Luizaga
 * @version 1.0
 */
public final class Hasher {
    private static final String KEY_LOCATION = System.getProperty("user.home")
            .concat(System.getProperty("file.separator")).concat("ClickUp.key");

    /**
     * Private constructor to comply checkStyle tool suggestions.
     */
    private Hasher() {
    }

    /**
     * Decrypts a hashed passkey.
     *
     * @param string A string containing the hashed passkey.
     * @return A string with a decrypted/unhashed passkey.
     * @throws GeneralSecurityException .
     * @throws DecoderException .
     * @throws IOException .
     */
    public static String decrypt(final String string) throws GeneralSecurityException, DecoderException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = loadKey();
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    /**
     * Decode via base64 a String.
     *
     * @param property to init a Cypher instance.
     * @return an arrays of bytes.
     * @throws IOException .
     */
    private static byte[] base64Decode(final String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }

    /**
     * Loads a encryption/decryption key previously stored in the filesystem.
     *
     * @return A SecretKeySpec instance to be used in the decryption method.
     * @throws DecoderException .
     * @throws IOException .
     */
    private static SecretKeySpec loadKey() throws IOException, DecoderException {
        File file = new File(KEY_LOCATION);
        String data = new String(readFileToByteArray(file));
        char[] hex = data.toCharArray();
        byte[] encoded;
        encoded = decodeHex(hex);
        SecretKeySpec key = new SecretKeySpec(encoded, "AES");
        return key;
    }
}
