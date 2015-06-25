package bbva.delivery.tarjetas.util;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("deliveryService")
@Transactional(propagation=Propagation.SUPPORTS)
public class AESHelper {
	
	// Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
	public final static String ALG = "AES";
	// Definición del modo de cifrado a utilizar
	public final static String CI = "AES/CBC/PKCS5Padding";
    
    public final static String KEY = "92AE31A79FEEB2A3"; //llave
    
    public final static String IV = "0123456789ABCDEF"; // vector de inicialización
    
	public static String encriptar(String key, String iv, String cleartext) throws Exception{
		
		 Cipher cipher 						= Cipher.getInstance(CI);
         SecretKeySpec skeySpec 			= new SecretKeySpec(key.getBytes(), ALG);
         IvParameterSpec ivParameterSpec 	= new IvParameterSpec(iv.getBytes());
         
         cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
         
         byte[] encrypted = cipher.doFinal(cleartext.getBytes());
         
         return new String(encodeBase64(encrypted));		
	}
	
	public static String desencriptar(String key, String iv, String encrypted) throws Exception{
		 Cipher cipher 						= Cipher.getInstance(CI);
         SecretKeySpec skeySpec 			= new SecretKeySpec(key.getBytes(), ALG);
         IvParameterSpec ivParameterSpec 	= new IvParameterSpec(iv.getBytes());
         
         byte[] enc = decodeBase64(encrypted);
         
         cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
         
         byte[] decrypted = cipher.doFinal(enc);
         
         return new String(decrypted);
	}
	
	
}