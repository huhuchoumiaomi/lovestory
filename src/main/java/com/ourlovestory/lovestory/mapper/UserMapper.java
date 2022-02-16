package com.ourlovestory.lovestory.mapper;

import com.ourlovestory.lovestory.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into USER (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert (User user);

    @Select("select * from USER where token=#{token}")
    User findByToken(String token);

    @Select("select * from USER where id=#{creator}")
    User findById(Integer creator);
}