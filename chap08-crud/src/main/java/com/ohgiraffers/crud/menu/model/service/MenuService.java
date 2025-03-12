package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findMenuList() {

        return menuMapper.findAllMenu();
    }


    public MenuDTO findMenuDetail(int code) {

        return menuMapper.findMenuByCode(code);
    }

    public List<CategoryDTO> findAllCategory() {
        return menuMapper.findAllCategory();
    }


    /* @Transactional 어노테이션은 클래스 레벨 또는 메서드 레벨에 정의할 수 있다.
    *   이 어노테이션은 데이터베이스 트랜잭션과 관련된 일련의 작업을 하나의 논리적 단위로 묶는데 사용된다.
    *   따라서 이 어노테이션이 데이터베이스 트랜잭션에 대한 commit, rollback 작업등을 모두 자동으로 처리해준다.
    * */
    @Transactional
    public void registMenu(MenuDTO newMenu) {

        menuMapper.registMenu(newMenu);
    }

    @Transactional
    public void updateMenu(MenuDTO menu) {

        menuMapper.updateMenu(menu);
    }
}
