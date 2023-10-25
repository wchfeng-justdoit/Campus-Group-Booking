package com.cf.CampusGroupBooking.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface userMapper {
    @Insert(value = "insert into user_tb values (#{username},#{password})")
    int assign(String username,String password);

}
