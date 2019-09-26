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
    private static final String keyLocation = System.getProperty("user.home")
            .concat(System.getProperty("file.separator")).concat("ClickUp.key");

    /**
     *
     * @param string
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public static String decrypt(final String string) throws GeneralSecurityException, IOException {
        String iv = string.split(":")[0];
        String property = string.split(":")[1];
        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = loadKey();
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(final String property) throws IOException {
        return Base64.getDecoder().decode(property);
    }

    private static SecretKeySpec loadKey() throws IOException
    {
        File file = new File(keyLocation);
        String data = new String(readFileToByteArray(file));
        char[] hex = data.toCharArray();
        byte[] encoded;
        try
        {
            encoded = decodeHex(hex);
        }
        catch (DecoderException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        SecretKeySpec key = new SecretKeySpec(encoded, "AES");
        return key;
    }

}
