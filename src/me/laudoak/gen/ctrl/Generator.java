package me.laudoak.gen.ctrl;

import me.laudoak.db.DBStarter;
import me.laudoak.db.TypeMapper;
import me.laudoak.model.Bean;
import me.laudoak.model.Table;
import me.laudoak.name.Naming;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laudoak on 17/1/1.
 */
public abstract class Generator
{
    protected String projectName;
    protected String packageName;
    protected List<Table> tables;
    protected Naming naming;
    protected TypeMapper typeMapper;
    protected List<Bean> beans;
    protected DBStarter dbStarter;
    protected StructureGenerator structure;

    public abstract void export();

    public Generator(Naming naming, TypeMapper typeMapper, String projectName, String packageName, List<Table> tables, DBStarter dbStarter)
    {
        this.naming = naming;
        this.typeMapper = typeMapper;
        this.projectName = projectName;
        this.packageName = packageName;
        this.tables = tables;
        this.dbStarter = dbStarter;
        init();
    }

    private void init()
    {
        structure = new StructureGenerator(projectName, packageName);
        beans = new ArrayList<>();
        for (Table table : tables)
        {
            beans.add(table.convertToBean(naming, typeMapper));
        }
    }

    protected void write(File path, String fName, String value)
    {
        ByteBuffer bb;
        FileOutputStream fos = null;
        FileChannel fc = null;

        try
        {
            File beanFile = new File(path, fName);
            if (!beanFile.exists())
            {
                beanFile.createNewFile();
            }
            fos = new FileOutputStream(beanFile);
            fc = fos.getChannel();
            bb = ByteBuffer.wrap(value.getBytes());
            fc.write(bb);

        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            if (null != fc)
            {
                try
                {
                    fc.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (null != fos)
            {
                try
                {
                    fos.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
/*
├── pom.xml
└── src
    └── main
        ├── java
        │   └── io
        │       └── laudoak
        │           ├── BlogApplication.java
        │           ├── dao
        │           │   ├── AdminDao.java
        │           │   ├── AdminGroupDao.java
        │           │   ├── BlogDao.java
        │           │   ├── CommentDao.java
        │           │   ├── Dao.java
        │           │   ├── GalleryDao.java
        │           │   ├── MemberDao.java
        │           │   ├── MemberGroupDao.java
        │           │   ├── NotificationDao.java
        │           │   ├── PictureDao.java
        │           │   ├── PointDetailDao.java
        │           │   ├── PointGroupDao.java
        │           │   └── SensitiveWordDao.java
        │           ├── model
        │           │   ├── Admin.java
        │           │   ├── AdminGroup.java
        │           │   ├── Blog.java
        │           │   ├── Comment.java
        │           │   ├── Gallery.java
        │           │   ├── Member.java
        │           │   ├── MemberGroup.java
        │           │   ├── Notification.java
        │           │   ├── Picture.java
        │           │   ├── PointDetail.java
        │           │   ├── PointGroup.java
        │           │   └── SensitiveWord.java
        │           └── service
        │               ├── AdminGroupService.java
        │               ├── AdminService.java
        │               ├── BlogService.java
        │               ├── CommentService.java
        │               ├── DashService.java
        │               ├── GalleryService.java
        │               ├── MemberGroupService.java
        │               ├── MemberService.java
        │               ├── NotificationService.java
        │               ├── PictureService.java
        │               ├── PointDetailService.java
        │               ├── PointGroupService.java
        │               ├── SensitiveWordService.java
        │               └── impl
        │                   ├── AdminGroupServiceImpl.java
        │                   ├── AdminServiceImpl.java
        │                   ├── BlogServiceImpl.java
        │                   ├── CommentServiceImpl.java
        │                   ├── DashServiceImpl.java
        │                   ├── GalleryServiceImpl.java
        │                   ├── MemberGroupServiceImpl.java
        │                   ├── MemberServiceImpl.java
        │                   ├── NotificationServiceImpl.java
        │                   ├── PictureServiceImpl.java
        │                   ├── PointDetailServiceImpl.java
        │                   ├── PointGroupServiceImpl.java
        │                   └── SensitiveWordServiceImpl.java
        └── resources
            ├── application.properties
            ├── mapper
            │   ├── AdminGroupMapper.xml
            │   ├── AdminMapper.xml
            │   ├── BlogMapper.xml
            │   ├── CommentMapper.xml
            │   ├── GalleryMapper.xml
            │   ├── MemberGroupMapper.xml
            │   ├── MemberMapper.xml
            │   ├── NotificationMapper.xml
            │   ├── PictureMapper.xml
            │   ├── PointDetailMapper.xml
            │   ├── PointGroupMapper.xml
            │   └── SensitiveWordMapper.xml
            ├── mybatis-config.xml
            └── schema.sql

 */
