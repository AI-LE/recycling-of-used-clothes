package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.GoodsImg;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.mbyte.easy.recycle.service.IGoodsImgService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.FileUtil;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* <p>
* 前端控制器
* </p>
* @author Author
* @since 2019-03-11
*/
@Controller
@RequestMapping("/recycle/goodsImg")
public class GoodsImgController extends BaseController  {

    private String prefix = "recycle/goodsImg/";

    @Autowired
    private IGoodsImgService goodsImgService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                        @RequestParam(value = "goodsName", required = false, defaultValue = "") String goodsName

                        ) {
        GoodsImg goodImg = new GoodsImg();

        if(StringUtils.isNoneBlank(goodsName)){
            goodImg.setGoodsName(goodsName);
        }

        Page<GoodsImg> page = new Page<GoodsImg>(pageNo, pageSize);

        IPage<GoodsImg> pageInfo = goodsImgService.selectGoodName(goodImg,page);


        model.addAttribute("searchInfo", goodImg);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";
    }

    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("addBefore")
    public String addBefore( Model model){
        List<Goods> goodsList = goodsImgService.selectGoodsName();
        model.addAttribute("goodsImgList",goodsList);
        return prefix+"add";
    }
    /**
    * 添加
    * @param goodsImg
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(GoodsImg goodsImg,@PathParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        goodsImg.setPic("../images/" + FileUtil.uploadFile(file));
        return toAjax(goodsImgService.save(goodsImg));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("goodsImg",goodsImgService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param goodsImg
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(GoodsImg goodsImg,@PathParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        goodsImg.setPic("../images/" + FileUtil.uploadFile(file));
        return toAjax(goodsImgService.updateById(goodsImg));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(goodsImgService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(goodsImgService.removeByIds(ids));
    }

}

