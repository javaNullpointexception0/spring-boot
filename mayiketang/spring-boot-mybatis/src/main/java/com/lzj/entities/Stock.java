package com.lzj.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;

/**
 * @author Created by luzhenjiang
 * @date 2021/9/10 19:46
 * @description
 */
@TableName(value = "tbStock")
public class Stock {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 库存量
     */
    private Long inventory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }
}
