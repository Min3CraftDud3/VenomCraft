package com.sinfulpixel.Merchants;

/**
 * Created by Min3 on 2/10/2015.
 */

public class SignData
{
    private String line0;
    private String line1;
    private String line2;
    private String line3;

    public SignData(String line0, String line1, String line2, String line3)
    {
        this.line0 = line0;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }

    public static String getLine0(SignData signData) {
        return signData.line0;
    }

    public static String getLine1(SignData signData) {
        return signData.line1;
    }

    public static String getLine2(SignData signData) {
        return signData.line2;
    }

    public static void setLine2(SignData signData, String line2) {
        signData.line2 = line2;
    }

    public static String getLine3(SignData signData) {
        return signData.line3;
    }
}