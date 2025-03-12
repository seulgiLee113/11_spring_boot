package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    private final MessageSource messageSource;      // -> Bean으로 등록함.

    // 밑의 PostMaregistMenu로그 찍어보기용
    private static final Logger logger = LogManager.getLogger(MenuController.class);

    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.menuService = menuService;
        this.messageSource = messageSource;
    }



//    @Autowired
//    public MenuController(MenuService menuService) {
//        this.menuService = menuService;
//    }

    /* 전체 메뉴 조회 */
    @GetMapping("/list")
    public String findMenuList(Model model) {

        logger.info("전체메뉴조회 핸들러 메서드 호출됨");


        /* 1. 사용자로부터 요청 접수 */
        // 전체 조회이기 때문에 별다른 파라미터가 전송되지 않음.

        /* 2. 비즈니스 로직 처리를 위한 서비스 호출 */
        // 서비스의 비즈니스 로직 호출
        List<MenuDTO> menuList = menuService.findMenuList();

        // 서비스의 작업 결과를 바탕으로 요청에 대한 성공/실패 여부 판단

        /* 3. 성공/실패 여부에 따라서 반환할 뷰 결정(필요한 데이터는 모델에 추가) */
        model.addAttribute("menuList", menuList);

        return "menu/list";
    }

    @GetMapping("/detail/{code}")
    public String findMenuDetail(@PathVariable("code") int code,
                                 Model model) {

        System.out.println("사용자가 요구하는 세부 메뉴의 PK 번호 : " + code);

        MenuDTO menu = menuService.findMenuDetail(code);

        // 모델 객체에 넣어야 이제 화면에 그린다,,
        model.addAttribute("menu", menu);

        // 그러고 나서 아래 menu/detail 뷰단에 넘겨주기
        return "menu/detail";
    }


    @GetMapping("/regist")
    public void registMenu() {}

    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> findCategoryList() {

        System.out.println("JavaScript 내장 함수인 fetch 비동기 함수 도착!");

        return menuService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registMenu(MenuDTO newMenu,      // MenuDTO는 커맨드 객체! regist.html의 폼의 인풋 파라미터들을 MenuDTO 타입으로 담음!!
                             RedirectAttributes rAttr, Locale locale) {

        menuService.registMenu(newMenu);

        //Log4j로 로깅을 할 때, 이렇게 작성하면 된다~ 정도로만 익혀두자.
        logger.info("Locale : {}", locale);

//        rAttr.addFlashAttribute("successMessage", "신규메뉴 등록에 성공하셨습니다. ");
        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu", null, locale));
                                                                                            // message_ko_KR.properties의 key를 갖고옴.

        return "redirect:/menu/list";
    }



    // 수정을 하는 페이지로 가는 핸들러,,
    @GetMapping("/edit/{code}")
    public String showEditMenuForm(@PathVariable("code") int code,
                                   Model model) {

        MenuDTO menu = menuService.findMenuDetail(code);

        model.addAttribute("menu", menu);

        return "menu/edit";
    }

    // .....> postMapping으로 달아준다.
    @PostMapping("/update")
    public String updateMenu(MenuDTO menu, RedirectAttributes rAttr) {

        menuService.updateMenu(menu);

        rAttr.addFlashAttribute("successMessage", "메뉴가 성공적으로 수정되었습니다. ");

        return "redirect:/menu/detail/" + menu.getCode();
                        // -> 저 위에 있는 findMenuDetail 핸들러 작동 ㄱ
    }
}
