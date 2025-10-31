package com.niit.esports.mapper;

import com.niit.esports.entity.Hero;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface HeroMapper {
    List<Hero> selectAllHeroes();
    int selectHeroCount();
}