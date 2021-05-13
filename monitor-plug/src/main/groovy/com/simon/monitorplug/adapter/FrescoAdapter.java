package com.simon.monitorplug.adapter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * Created by simon on 2021/4/15.
 */

public class FrescoAdapter extends AdviceAdapter {

    /**
     * Creates a new {@link AdviceAdapter}.
     *
     *
     * @param mv     the method visitor to which this adapter delegates calls.
     * @param access the method's access flags
     * @param name   the method's name.
     * @param desc   the method's descriptor
     */
    public FrescoAdapter(MethodVisitor mv, int access, String name, String desc) {
        super(Opcodes.ASM5, mv, access, name, desc);
    }

    /**
     * 方法进入时
     * 1.调用ImageRequestBuilder的getSourceUri()
     * 2.调用getPostprocessor()
     * 3.设置进FrescoHook的process方法
     * 4.将返回的Postprocessor再设置进ImageRequestBuilder
     * builder.setPostprocessor(FrescoHook.process(builder.getSourceUri()，builder.getPostprocessor(),getResizeOptions()));
     */
    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        mv.visitVarInsn(ALOAD, 1);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/facebook/imagepipeline/request/ImageRequestBuilder", "getSourceUri", "()Landroid/net/Uri;", false);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/facebook/imagepipeline/request/ImageRequestBuilder", "getPostprocessor", "()Lcom/facebook/imagepipeline/request/Postprocessor;", false);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/facebook/imagepipeline/request/ImageRequestBuilder", "getResizeOptions", "()Lcom/facebook/imagepipeline/common/ResizeOptions;", false);
        mv.visitMethodInsn(INVOKESTATIC, "com/simon/monitorapi/fresco/FrescoHook", "process", "(Landroid/net/Uri;Lcom/facebook/imagepipeline/request/Postprocessor;Lcom/facebook/imagepipeline/common/ResizeOptions;)Lcom/facebook/imagepipeline/request/Postprocessor;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/facebook/imagepipeline/request/ImageRequestBuilder", "setPostprocessor", "(Lcom/facebook/imagepipeline/request/Postprocessor;)Lcom/facebook/imagepipeline/request/ImageRequestBuilder;", false);
    }
}