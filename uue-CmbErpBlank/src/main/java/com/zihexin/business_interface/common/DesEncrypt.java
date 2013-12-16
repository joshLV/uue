package com.zihexin.business_interface.common;

import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesEncrypt {
	private static final char HEXCHAR[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static final String HEXINDEX = "0123456789abcdefABCDEF";
	/**
	 * 
	 * 使用DES加密与解密,可对byte[],String类型进行加密与解密 密文可使用String,byte[]存储.
	 * 
	 * 方法: void getKey(String strKey)从strKey的字条生成一个Key
	 * 
	 * String getEncString(String strMing)对strMing进行加密,返回String密文 String
	 * getDesString(String strMi)对strMin进行解密,返回String明文
	 * 
	 *byte[] getEncCode(byte[] byteS)byte[]型的加密 byte[] getDesCode(byte[]
	 * byteD)byte[]型的解密
	 */

	Key key;

	/**
	 * 根据参数生成KEY
	 * 
	 * @param strKey
	 */
	public void getKey(String strKey) {
		try {
			//strKey = "MYKEY";
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			this.key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密String明文输入,String密文输出
	 * 
	 * @param strMing
	 * @return
	 */
	public String getEncString(String strMing,String strKey) {
		getKey(strKey);//getKey("MYKEY");
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		BASE64Encoder base64en = new BASE64Encoder();
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encode(byteMi);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	public static String digest(String str) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("utf-8"));
			byte[] result = messageDigest.digest();
			return DesEncrypt.byteToHex(result);
		} catch (Exception e) {
		}

		return null;
	}

	static String byteToHex(byte b[]) {
		int len = b.length;
		StringBuffer s = new StringBuffer();

		for (int i = 0; i < len; i++) {
			int c = ((int) b[i]) & 0xff;

			s.append(HEXCHAR[c >> 4 & 0xf]);
			s.append(HEXCHAR[c & 0xf]);
		}

		return s.toString();
	}

	/**
	 * 解密 以String密文输入,String明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public String getDesString(String strMi,String strKey) {
		getKey(strKey);//getKey("MYKEY");
		BASE64Decoder base64De = new BASE64Decoder();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decodeBuffer(strMi);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] getDesCode(byte[] byteD) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	public static void main(String[] args) {
		System.out.println("des demo");
		DesEncrypt des = new DesEncrypt();// 实例化一个对像
        String key = "zhx_001";
		des.getKey(key);// 生成密匙
		System.out.println("key="+key);
		String strEnc = des.getEncString("sys009",key);// 加密字符串,返回String的密文
		System.out.println("密文=" + strEnc);
		String strDes = des.getDesString(strEnc,key);// 把String 类型的密文解密
		System.out.println("明文=" + strDes);

       //System.out.println("校验" + des.getDesString("jS7HGEPghW1Hou9ro7pPvw==",key));
	}
}
