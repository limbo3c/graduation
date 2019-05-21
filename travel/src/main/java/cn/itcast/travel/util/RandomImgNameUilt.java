package cn.itcast.travel.util;

import java.util.Random;

public class RandomImgNameUilt {
    public static String generateRandom() {
        String resultStr ="";
        String strData = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] arr =strData.toCharArray();
        for (int i=0;i<31;i++){
            int index =(int) (Math.random()*arr.length);
            resultStr += arr[index];
        }
        return resultStr;
    }
    public static void main(String args[]){
        System.out.println(generateRandom());
    }

}
