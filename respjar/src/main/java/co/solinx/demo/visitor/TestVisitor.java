package co.solinx.demo.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

/**
 * @author linxin
 * @version v1.0
 *          Copyright (c) 2015 by e_trans
 * @date 2017/6/23.
 */
public class TestVisitor extends ClassVisitor {


    public TestVisitor(ClassVisitor classVisitor,String className) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if ("test".equals(name)) {
            mv = new TestVisitorAdapter(Opcodes.ASM5, mv, access, name, desc);
        }
        return mv;

    }
}
