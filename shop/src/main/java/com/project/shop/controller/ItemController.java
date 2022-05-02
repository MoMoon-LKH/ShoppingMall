package com.project.shop.controller;

import com.project.shop.domain.Category;
import com.project.shop.domain.Item;
import com.project.shop.domain.Member;
import com.project.shop.domain.dto.ItemDto;
import com.project.shop.repository.CategoryRepository;
import com.project.shop.service.CategoryService;
import com.project.shop.service.ItemService;
import com.project.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.util.ClassPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sell")
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final MemberService memberService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${imagePath}")
    private String imgPath;

    @GetMapping("/my")
    public String sellMain() {
        return "/seller/mySell";
    }

    @GetMapping("/new")
    public String registerPage(Model model) {

        model.addAttribute("itemDto", new ItemDto());
        model.addAttribute("category", categoryService.findAll());
        return "/seller/registerItem";
    }

    @PostMapping("/new")
    public String registerItem(@Valid ItemDto itemDto) throws IOException {

        String imgUrl = " ";
        String descriptionImg = " ";

        if(!itemDto.getImgUrl().isEmpty())
            imgUrl = transferImg(itemDto.getImgUrl(), 0);

        if(!itemDto.getDescriptionUrl().isEmpty())
            descriptionImg = transferImg(itemDto.getDescriptionUrl(), 1);

        Member member = memberService.findById(itemDto.getMemberId());
        Category category = categoryService.findById(itemDto.getCategoryId());

        Item item = Item.createItem(itemDto, member, category, imgUrl, descriptionImg);

        itemService.save(item);

        return "redirect:/sell/my";
    }

    public String transferImg(MultipartFile file, int type) throws IOException {
        String originName = file.getOriginalFilename();
        String ext = originName.substring(originName.lastIndexOf("."));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        int ran = (int) (Math.random()*10000);

        String fname = "";


        if (type == 0) {
            fname = dateFormat.format(new Date()) + "_" + ran + ext;
        } else{
            fname = dateFormat.format(new Date()) + "_" + ran + "d" + ext;
        }
        file.transferTo(new File( imgPath+ "/" + fname));

        return fname;
    }
}
