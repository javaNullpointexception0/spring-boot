package com.lzj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.entities.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author Created by luzhenjiang
 * @date 2021/9/10 19:45
 * @description
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

    @Update("update tbStock set quantity=quantity - #{quantity} where id=#{id} and quantity>=#{quantity}")
    Integer deduction(@Param("id") Integer id, @Param("quantity") Long quantity);
}
