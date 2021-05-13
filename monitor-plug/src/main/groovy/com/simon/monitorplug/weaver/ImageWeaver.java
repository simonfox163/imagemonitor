package com.simon.monitorplug.weaver;

import com.quinn.hunter.transform.asm.BaseWeaver;
import com.simon.monitorplug.visitor.ImgVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * Created by simon on 2021/3/10.
 */

public class ImageWeaver  extends BaseWeaver {
    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new ImgVisitor(classWriter);
    }
}
