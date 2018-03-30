package testjava;

import java.math.BigInteger;

/**
 * Created by plundahl.
 */
public class regx {
    public static void main(String[] args) {
        String IP1 = "000.12.12.034";
        System.out.println(IP1.matches(new MyRegex().pattern));
    }


}

class MyRegex {
    String pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
BigInteger a = BigInteger.valueOf(1);
BigInteger b = BigInteger.valueOf(1);
    BigInteger c = a.add(b);
}