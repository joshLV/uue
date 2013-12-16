package com.zihexin.business_interface.common;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-5-3
 * Time: обнГ1:09
 * To change this template use File | Settings | File Templates.
 */
public class CharacterUtils {

    public static String bcd2Str(byte[] bytes)
    {
        char temp[] = new char[bytes.length*2], val;

        for(int i = 0; i < bytes.length; i++){
            val = (char)(((bytes[i]& 0xf0) >> 4)&0x0f);
            temp[i * 2] = (char)(val > 9 ? val + 'A' - 10 : val + '0');

            val = (char)(bytes[i]& 0x0f);
            temp[i * 2 + 1] = (char)(val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }
}
