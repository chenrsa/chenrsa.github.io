package com.example.demo.constant;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author chenrunzheng
 * @since 2020/7/10 13:56
 */
@Slf4j
public class AesUtil {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 在AES ECB模式下，使用【utf-8的key】将【utf-8的value】加密成【Base64的字符】。
     */
    public static String encodeAes(String value, String key)  {
        if (StringUtils.isBlank(value)) {
            return value;
        }

        byte[] out = encodeECB(value.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeBase64String(out);
    }

    private static byte[] encodeECB(byte[] value, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            return cipher.doFinal(value);
        } catch (GeneralSecurityException e) {
            log.error("encodeECB error", e);
            return null;
        }
    }

}
