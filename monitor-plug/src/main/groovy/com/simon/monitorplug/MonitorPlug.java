
package com.simon.monitorplug;

import com.android.build.gradle.AppExtension;
import com.simon.monitorplug.transform.LargeImgTransform;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;
import java.util.List;

/**
 * Created by simon on 2021/3/9.
 */

class MonitorPlug  implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("========= MonitorPlug    ======MonitorPlug");

        List<String> taskNames = project.getGradle().getStartParameter().getTaskNames();
        //如果是Release版本，则不进行字节码替换
        for(String taskName : taskNames){
            if(taskName.contains("Release")){
                return;
            }
        }

        AppExtension appExtension = (AppExtension)project.getProperties().get("android");


        //创建自定义扩展
        project.getExtensions().create("largeImageMonitor",LargeImageExtension.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                LargeImageExtension extension = project.getExtensions().getByType(LargeImageExtension.class);
                Config.getInstance().init(extension);
            }
        });

        //将自定义Transform添加到编译流程中
        appExtension.registerTransform(new LargeImgTransform(project), Collections.EMPTY_LIST);
    }
}
