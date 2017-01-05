package io.laudoak.service.impl;


import io.laudoak.dao.SensitiveWordDao;
import io.laudoak.model.SensitiveWord;
import io.laudoak.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * generated by SQL2Q
 * on Thu Jan 05 18:24:25 CST 2017
 */

@Service
public class SensitiveWordServiceImpl implements SensitiveWordService
{

    @Autowired
    private SensitiveWordDao dao;

    @Override
    public Integer insert(SensitiveWord sensitiveWord)
    {
        return dao.insert(sensitiveWord);
    }

    @Override
    public Integer delete(SensitiveWord sensitiveWord)
    {
        return dao.delete(sensitiveWord);
    }

    @Override
    public Integer update(SensitiveWord sensitiveWord)
    {
        return dao.update(sensitiveWord);
    }

    @Override
    public SensitiveWord selectOne(Long id)
    {
        return dao.selectOne(id);
    }

    @Override
    public List<SensitiveWord> select()
    {
        return dao.select();
    }

    @Override
    public Long count()
    {
        return dao.count();
    }

}
