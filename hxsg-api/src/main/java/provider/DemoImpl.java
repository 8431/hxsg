package provider;

import DaoApi.Demo;

/**
 * Created by dlf on 2016/5/12.
 */
public class DemoImpl implements Demo{
    @Override
    public String say(String name) {
        return name+":hello world!";
    }
}
