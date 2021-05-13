package com.simon.monitorplug.visitor;
import com.simon.monitorplug.adapter.FrescoAdapter;
import com.simon.monitorplug.adapter.GlidMethodAdapter;
import com.simon.monitorplug.adapter.Picassodapter;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by simon on 2021/3/10.
 */

public class ImgVisitor extends ClassVisitor {
    private String className;

    public ImgVisitor(ClassVisitor classWriter) {
        super(Opcodes.ASM5, classWriter);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
    }


    @Override
    public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, methodName, desc, signature, exceptions);
        //System.out.println("==========visitMethod ==========");

        //对Glide4.11版本的SingleRequest类的构造方法进行字节码修改
        if(className.equals("com/bumptech/glide/request/SingleRequest") && methodName.equals("<init>") && desc!=null){
            log(className,access,methodName,desc,signature);
            return mv == null ? null : new GlidMethodAdapter(mv,access,methodName,desc);
        }

        //对picasso的Request类的构造方法进行字节码修改
        if(className.equals("com/squareup/picasso/Request") && methodName.equals("<init>") && desc!=null){
            return mv == null ? null : new Picassodapter(mv,access,methodName,desc);
        }


        //对Fresco的ImageRequest类的构造方法进行字节码修改
        if(className.equals("com/facebook/imagepipeline/request/ImageRequest") && methodName.equals("<init>") && desc!=null){
            return mv == null ? null : new FrescoAdapter(mv,access,methodName,desc);
        }

        return mv;
    }

    private void log(String className, int access, String name, String desc, String signature) {
        System.out.println("LargeImageClassAdapter===matched====>" + "  className===" + className + "   access===" + access + "   methodName===" + name + "   desc===" + desc + "   signature===" + signature);
    }
}
