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
	 * ʹ��DES���������,�ɶ�byte[],String���ͽ��м�������� ���Ŀ�ʹ��String,byte[]�洢.
	 * 
	 * ����: void getKey(String strKey)��strKey����������һ��Key
	 * 
	 * String getEncString(String strMing)��strMing���м���,����String���� String
	 * getDesString(String strMi)��strMin���н���,����String����
	 * 
	 *byte[] getEncCode(byte[] byteS)byte[]�͵ļ��� byte[] getDesCode(byte[]
	 * byteD)byte[]�͵Ľ���
	 */

	Key key;

	/**
	 * ���ݲ�������KEY
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
	 * ����String��������,String�������
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
	 * ���� ��String��������,String�������
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
	 * ������byte[]��������,byte[]�������
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
	 * ������byte[]��������,��byte[]�������
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
		DesEncrypt des = new DesEncrypt();// ʵ����һ������
        String key = "zhx_001";
		des.getKey(key);// �����ܳ�
		System.out.println("key="+key);
		String strEnc = des.getEncString("sys009",key);// �����ַ���,����String������
		System.out.println("����=" + strEnc);
		String strDes = des.getDesString(strEnc,key);// ��String ���͵����Ľ���
		System.out.println("����=" + strDes);

       //System.out.println("У��" + des.getDesString("jS7HGEPghW1Hou9ro7pPvw==",key));
	}
}
