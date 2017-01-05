package io.laudoak.service;

import io.laudoak.model.Picture;

import java.util.List;

/**
 * generated by SQL2Q
 * on Thu Jan 05 18:24:25 CST 2017
 */
public interface PictureService
{
    Integer insert(Picture picture);

    Integer delete(Picture picture);

    Integer update(Picture picture);

    Picture selectOne(Long id);

    List<Picture> select();

    Long count();

}