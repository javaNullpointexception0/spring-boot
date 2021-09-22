package com.lzj.service.impl;

import com.lzj.exception.OptimisticLockerException;
import com.lzj.mapper.StockMapper;
import com.lzj.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Created by luzhenjiang
 * @date 2021/9/10 19:45
 * @description
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Transactional
    @Override
    public Integer deduction(Integer id, Long quantity) {
        int updateCount = stockMapper.deduction(id, quantity);
        if (updateCount <= 0) {
            throw new OptimisticLockerException("乐观锁检测到数据冲突，数据更新条数：" + updateCount);
        }
        return updateCount;
    }
}
