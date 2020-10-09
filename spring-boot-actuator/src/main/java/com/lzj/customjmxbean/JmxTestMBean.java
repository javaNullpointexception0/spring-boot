package com.lzj.customjmxbean;

/**
 * 自定义MBean接口
 */
public interface JmxTestMBean {
    public String getNmae();
    public void setName(String name);
    public String printHello();
    public String pringHello(String whoName);
}
