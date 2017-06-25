package co.solinx.demo.visitor;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * @author linxin
 * @version v1.0
 *          Copyright (c) 2015 by linx
 * @date 2017/6/23.
 */
public class TestVisitorAdapter extends AdviceAdapter {



    protected TestVisitorAdapter(int api, MethodVisitor mv, int access, String name, String desc) {
        super(api, mv, access, name, desc);
    }

    @Override
    protected void onMethodEnter() {
//        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitLdcInsn("Already in sql");
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        mv.visitTypeInsn(NEW,"co/solinx/demo/filter/SqlFilter");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL,"co/solinx/demo/filter/SqlFilter","<init>","()V",false);
        mv.visitVarInsn(ASTORE,3);
        mv.visitVarInsn(ALOAD,3);
        mv.visitVarInsn(ALOAD,1);
        mv.visitMethodInsn(INVOKEVIRTUAL,"co/solinx/demo/filter/SqlFilter", "filter","(Ljava/lang/Object;)Z",false);


//        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
////        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
//
//        mv.visitLdcInsn(String.format("时间 %s",new Date().toString()));
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V",false);

//        mv.visitVarInsn(ISTORE, 5); //返回值
//        mv.visitFieldInsn(GETSTATIC, "java/lang/System","out", "Ljava/io/PrintStream;");
//
//                mv.visitVarInsn(ILOAD, 5);
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V"); //output 10


        Label l92 = new Label();

        /**
         * IFEQ filter返回值也就是栈顶int型数值等于true时跳转，抛出异常
         */
        mv.visitJumpInsn(IFEQ, l92);
        mv.visitTypeInsn(NEW, "java/sql/SQLException");
        mv.visitInsn(DUP);
        mv.visitLdcInsn("invalid sql because of security check");
        mv.visitMethodInsn(INVOKESPECIAL, "java/sql/SQLException", "<init>", "(Ljava/lang/String;)V", false);
        mv.visitInsn(ATHROW);
        mv.visitLabel(l92);
        /**
         * 必须要调该方法，手动设置Stack Map Table，否则会有 java.lang.VerifyError: Expecting a stackmap frame at branch target 26异常
         * 在jdk1.7中可以使用JVM参数-UseSplitVerifier，关掉class验证，但jdk1.8中该参数已经去掉，所以要在1.8中运行必须调用该方法；
         */
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
    }

    @Override
    public void visitParameter(String name, int access) {

        super.visitParameter(name, access);
    }
}
