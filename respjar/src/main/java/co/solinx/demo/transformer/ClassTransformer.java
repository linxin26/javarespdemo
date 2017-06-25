package co.solinx.demo.transformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;

/**
 * @author linxin
 * @version v1.0
 *          Copyright (c) 2017 by linx
 * @date 2017/6/23.
 */
public class ClassTransformer implements ClassFileTransformer {


    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer){

        byte[] transformeredByteCode = classfileBuffer;
        try {

            if (className.equals("co/solinx/demo/Test")) {
                System.out.println(String.format("transform start %s",className));
                ClassReader reader = new ClassReader(classfileBuffer);
                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                ClassVisitor classVisitor = (ClassVisitor) createVisitorIns("co.solinx.demo.visitor.TestVisitor", writer, className);
                reader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
                transformeredByteCode = writer.toByteArray();
                // 生成字节码形式的类
                byte[] code =transformeredByteCode;
                FileOutputStream fos = new FileOutputStream("Example.class");
                //写文件
                fos.write(code);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }catch (Throwable t){
            t.printStackTrace();
        }
        return transformeredByteCode;
    }

    public Object createVisitorIns(final String name, ClassVisitor cv, String className)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Constructor<?> ctor = Class.forName(name).getDeclaredConstructor(new Class[]{ClassVisitor.class, String.class});
        ctor.setAccessible(true);
        return  ctor.newInstance(new Object[]{cv, className});
    }
}
