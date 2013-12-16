package com.zihexin.business_interface.common;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 *
 */
public class AmountUtils {

    /** 将分为单位的转换为元并返回金额格式的字符串 （除100）*/
    public static String changeF2Y(Long amount){
        int flag = 0;
        String amString = amount.toString();
        if(amString.charAt(0)=='-'){
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if(amString.length()==1){
            result.append("0.0").append(amString);
        }else if(amString.length() == 2){
            result.append("0.").append(amString);
        }else{
            String intString = amString.substring(0,amString.length()-2);
            for(int i=1; i<=intString.length();i++){
                if( (i-1)%3 == 0 && i !=1){
                    result.append(",");
                }
                result.append(intString.substring(intString.length()-i,intString.length()-i+1));
            }
            result.reverse().append(".").append(amString.substring(amString.length()-2));
        }
        if(flag == 1){
            return "-"+result.toString();
        }else{
            return result.toString();
        }
    }

    /** 将分为单位的转换为元 （除100）*/
    public static String changeF2Y(String amount){
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }

    /** 将分为单位的转换为元,保留两位小数 （除100）*/
    public static BigDecimal changeF2YRound(BigDecimal amount){
        return amount.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /** 将元为单位的转换为分 （乘100） */
    public static String changeY2F(Long amount){
        return BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).toString();
    }

    /** 将元为单位的转换为分 （乘100） */
    public static String changeY2F(String amount){
        int index = amount.indexOf(".");
        int length = amount.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(amount+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((amount.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((amount.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((amount.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }

    /** 判断金额是否超过2000000000分 */
    public static Boolean isAmount(String amount){
        Long amLong = 0l;
        amLong = Long.valueOf(changeY2F(amount));
        System.out.println(amLong);
        if(amLong > 2000000000L){

            return true;

        }else{

            return false;

        }
    }

    public static double subDouble(double d) {
        return (int)(d*100)/100.0;
    }

    public static  String splitNumber(double d) {
        String str = String.valueOf(d);
        System.out.println(str);
        String[] arr = str.split("\\.");
        System.out.println(arr.length);
        String ret = arr[0] + ".";
        if(arr[1].length() == 1) {
            ret += arr[1].substring(0,1) + "0";
        }
        else {
            ret += arr[1].substring(0,2);
        }
        return ret;
    }

}
