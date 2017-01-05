package io.laudoak.service.impl;


import io.laudoak.dao.MemberGroupDao;
import io.laudoak.model.MemberGroup;
import io.laudoak.service.MemberGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * generated by SQL2Q
 * on Thu Jan 05 18:24:25 CST 2017
 */

@Service
public class MemberGroupServiceImpl implements MemberGroupService
{

    @Autowired
    private MemberGroupDao dao;

    @Override
    public Integer insert(MemberGroup memberGroup)
    {
        return dao.insert(memberGroup);
    }

    @Override
    public Integer delete(MemberGroup memberGroup)
    {
        return dao.delete(memberGroup);
    }

    @Override
    public Integer update(MemberGroup memberGroup)
    {
        return dao.update(memberGroup);
    }

    @Override
    public MemberGroup selectOne(Long id)
    {
        return dao.selectOne(id);
    }

    @Override
    public List<MemberGroup> select()
    {
        return dao.select();
    }

    @Override
    public Long count()
    {
        return dao.count();
    }

}