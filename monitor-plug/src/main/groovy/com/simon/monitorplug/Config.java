package com.simon.monitorplug;

/**
 * Created by simon on 2021/3/31.
 */

public class Config {
    public static String TAG = "LargeImageMonitor";
    /**
     * 大图检测插件的开关
     */
    private boolean largeImagePluginSwitch = true;

    private Config(){}

    private static class Holder{
        private static Config INSTANCE = new Config();
    }

    public static Config getInstance(){
        return Holder.INSTANCE;
    }

    public boolean largeImagePluginSwitch() {
        return largeImagePluginSwitch;
    }

    public void init(LargeImageExtension extension){
        if(null != extension){
            this.largeImagePluginSwitch = extension.largeImagePluginSwitch;
        }
    }
}