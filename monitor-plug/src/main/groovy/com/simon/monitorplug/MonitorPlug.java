
package com.simon.monitorplug;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by simon on 2021/3/9.
 */

class MonitorPlug  implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("========= MonitorPlug    ======MonitorPlug");
    }
}
