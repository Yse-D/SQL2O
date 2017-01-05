package io.laudoak.service.impl;


import io.laudoak.dao.PointGroupDao;
import io.laudoak.model.PointGroup;
import io.laudoak.service.PointGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * generated by SQL2Q
 * on Thu Jan 05 18:24:25 CST 2017
 */

@Service
public class PointGroupServiceImpl implements PointGroupService
{

    @Autowired
    private PointGroupDao dao;

    @Override
    public Integer insert(PointGroup pointGroup)
    {
        return dao.insert(pointGroup);
    }

    @Override
    public Integer delete(PointGroup pointGroup)
    {
        return dao.delete(pointGroup);
    }

    @Override
    public Integer update(PointGroup pointGroup)
    {
        return dao.update(pointGroup);
    }

    @Override
    public PointGroup selectOne(Long id)
    {
        return dao.selectOne(id);
    }

    @Override
    public List<PointGroup> select()
    {
        return dao.select();
    }

    @Override
    public Long count()
    {
        return dao.count();
    }

}