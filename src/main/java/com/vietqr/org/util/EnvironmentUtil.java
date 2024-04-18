package com.vietqr.org.util;

public class EnvironmentUtil {
    private static boolean IS_PRODUCTION = false;
    private static final String DEFAULT_SERVICE_FEE = "Phần mềm VietQR";
    public static String getDefaultServiceFee() {
        return DEFAULT_SERVICE_FEE;
    }

    public static boolean isProduction() {
        return IS_PRODUCTION;
    }
}
