package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// MyBatis의 Mapper 인터페이스임을 나타내는  org.apache.ibatis.annotation 소속의 어노테이션.
// 이 어노테이션이 정의된 인터페이스는 MyBatis와 연동되어 SQL 매핑을 처리함.
@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenu();

    List<CategoryDTO> findAllCategory();

    void registNewMenu(MenuDTO newMenu);

    MenuDTO findMenuByCode(int code);

    void updateMenu(MenuDTO menu);

    void deleteMenu(int code);
}
