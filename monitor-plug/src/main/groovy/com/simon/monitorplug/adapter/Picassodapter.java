package com.simon.monitorplug.adapter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Created by simon on 2021/4/15.
 */

public class Picassodapter extends AdviceAdapter {
    /**
     * Creates a new {@link AdviceAdapter}.
     *
     * @param mv     the method visitor to which this adapter delegates calls.
     * @param access the method's access flags .
     * @param name   the method's name.
     * @param desc   the method's descriptor.
     */
    public Picassodapter(MethodVisitor mv, int access, String name, String desc) {
        super(Opcodes.ASM5, mv, access, name, desc);
    }

    /**
     * 方法进入时
     * 1.拿到方法第一个参数Uri
     * 2.拿到方法第四个参数 List<Transformation> transformations
     * 3.把它们传入hook方法
     * 4.在方法中加入我们自己的Transformation
     * 5.将设置好以后的 List<Transformation> transformations返回给第四个参数
     *  transformations = PicassoHook.process(uri,transformations,resourceId,targetWidth,targetHeight);
     */
    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ALOAD, 4);
        mv.visitVarInsn(ILOAD, 2);
        mv.visitVarInsn(ILOAD, 5);
        mv.visitVarInsn(ILOAD, 6);
        mv.visitMethodInsn(INVOKESTATIC, "com/simon/monitorapi/picasso/PicassoHook", "process", "(Landroid/net/Uri;Ljava/util/List;III)Ljava/util/List;", false);
        mv.visitVarInsn(ASTORE, 4);
    }
}


