package com.simon.monitorplug.transform;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.quinn.hunter.transform.HunterTransform;
import com.quinn.hunter.transform.RunVariant;
import com.simon.monitorplug.weaver.ImageWeaver;

import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by simon on 2021/3/10.
 */

public class LargeImgTransform extends HunterTransform {
    public LargeImgTransform(Project project) {
        super(project);
        this.bytecodeWeaver = new ImageWeaver();
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
    }

    @Override
    protected RunVariant getRunVariant() {
        return super.getRunVariant();
    }
}
