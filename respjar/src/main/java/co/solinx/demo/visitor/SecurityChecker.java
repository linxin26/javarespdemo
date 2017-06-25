package co.solinx.demo.visitor;

/**
 * Created by xin on 2017-06-25.
 */
public class SecurityChecker {

    public static boolean checkSecurity() {
        System.out.println("SecurityChecker.checkSecurity ...");
        if((System.currentTimeMillis()&0x1)==0)
            return false;
        else
            return true;
    }
}
