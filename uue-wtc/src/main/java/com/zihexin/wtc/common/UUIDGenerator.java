package com.zihexin.wtc.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: yj
 * Date: 12-4-13
 * Time: ����4:35
 * To change this template use File | Settings | File Templates.
 */
public class UUIDGenerator {
    public UUIDGenerator() {
    }

    /**
     * ���һ��UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //ȥ����-�����
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * ��ȡϵͳ����
     * @return
     */
    public static String getSysDate() {
        String strDate = "";
        Date dt = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        strDate = sdf.format(dt);
        return strDate;

    }

    public static Calendar getCalendarDate() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return now;

    }

    /**
     * ���ָ����Ŀ��UUID
     *
     * @param number int ��Ҫ��õ�UUID����
     * @return String[] UUID����
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String[] args) {
        String[] ss = getUUID(10);
        System.out.println(getCalendarDate());
        for (int i = 0; i < ss.length; i++) {
            System.out.println(ss[i]);
            System.out.println(ss[i].length());
        }
    }
}

