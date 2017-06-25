package co.solinx.demo;

import co.solinx.demo.transformer.ClassTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author linx
 * @date 2017-06-25
 */
public class JavaRASPApp {

    public static void premain(String agentArgs, Instrumentation instru) throws ClassNotFoundException, UnmodifiableClassException {
        System.out.println("premain");
        instru.addTransformer(new ClassTransformer());
    }

}
