package com.zihexin.business_interface.common;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-13
 * Time: ����1:17
 * To change this template use File | Settings | File Templates.
 */
public class KeyCheck {
    /**
     * У��У��λ
     * @param src Դϵͳ����
     * @param check У��λ
     * @return true:У��ͨ�� false:У��δͨ��
     */
    public static boolean check(String src, String check) {
        boolean isValid;
        try {
            DesEncrypt des = new DesEncrypt();// ʵ����һ������
            String encryKey = Loader.getItem("ENCRY_KEY");//����KEY
            des.getKey(encryKey);// �����ܳ�
            String strEnc = des.getEncString(src, encryKey);// �����ַ���,����String������
            if (!check.equals(strEnc)) {
                isValid = false;
            } else {
                isValid = true;
            }
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
}
