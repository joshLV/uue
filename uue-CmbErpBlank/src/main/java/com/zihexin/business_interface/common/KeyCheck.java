package com.zihexin.business_interface.common;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-13
 * Time: 下午1:17
 * To change this template use File | Settings | File Templates.
 */
public class KeyCheck {
    /**
     * 校验校验位
     * @param src 源系统编码
     * @param check 校验位
     * @return true:校验通过 false:校验未通过
     */
    public static boolean check(String src, String check) {
        boolean isValid;
        try {
            DesEncrypt des = new DesEncrypt();// 实例化一个对像
            String encryKey = Loader.getItem("ENCRY_KEY");//库内KEY
            des.getKey(encryKey);// 生成密匙
            String strEnc = des.getEncString(src, encryKey);// 加密字符串,返回String的密文
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
