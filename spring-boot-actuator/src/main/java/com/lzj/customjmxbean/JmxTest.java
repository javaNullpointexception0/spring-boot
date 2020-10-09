package com.lzj.customjmxbean;

import javax.annotation.ManagedBean;

@ManagedBean
public class JmxTest implements JmxTestMBean {

    private String name = "default";
    @Override
    public String getNmae() {return this.name;}
    @Override
    public void setName(String name) {this.name = name;}
    @Override
    public String printHello() { return "JmxTest:" + this.name;}
    @Override
    public String pringHello(String whoName) {return "JmxTest:" + whoName;}
}
