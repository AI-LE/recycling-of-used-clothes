package com.mbyte.easy.recycle.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.GoodsType;
import com.mbyte.easy.recycle.mapper.GoodsMapper;
import com.mbyte.easy.recycle.service.IGoodsService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.service.IGoodsTypeService;
import com.mbyte.easy.util.FileUtil;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
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
@RequestMapping("/recycle/goods")
public class GoodsController extends BaseController  {

    private String prefix = "recycle/goods/";

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsTypeService goodsTypeService;
    @Resource
    private GoodsMapper goodsMapper;


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
                                    @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                    @RequestParam(value = "sales", required = false, defaultValue = "") Integer sales,
                                    @RequestParam(value = "price", required = false, defaultValue = "") BigDecimal price,
                                    @RequestParam(value = "createtimeSpace", required = false, defaultValue = "") String createtimeSpace
                                  ) {


        Goods good = new Goods();

        if (name != null && !"".equals(name.trim())) {
            good.setName(name.trim());   //搜索时候设置值

        }

        if(sales != null){
            good.setSales(sales);
        }
        if (price != null) {
            good.setPrice(price);
        }


        Page<Goods> page = new Page<Goods>(pageNo, pageSize);


        IPage<Goods> pageInfo = goodsService.selectAll(page,createtimeSpace,good);
        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", good);
        model.addAttribute("pageInfo", new PageInfo<Goods>(pageInfo));
        return prefix+"list";
    }

    /**
    * 添加跳转页面-
    * @return
    */
    @GetMapping("addBefore")
    public String addBefore(Model model){
        List<GoodsType> type = goodsTypeService.selectType();
        model.addAttribute("type",type);
        return prefix+"add";
    }
    /**
    * 添加
    * @param goods
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Goods goods  , @PathParam("file") MultipartFile file){
        goods.setCreatetime(LocalDateTime.now());
        goods.setUpdatetime(LocalDateTime.now());
        String fileName = file.getOriginalFilename();
        goods.setPic("../images/" + FileUtil.uploadFile(file)  );

        return toAjax(goodsService.save(goods));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        List<GoodsType> type = goodsTypeService.selectType();
        model.addAttribute("type",type);

        model.addAttribute("goods",goodsMapper.selectType(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param goods
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Goods goods ,@PathParam("file") MultipartFile file){
        goods.setUpdatetime(LocalDateTime.now());
        goods.setCreatetime(LocalDateTime.now());
        if(goods.getPic() == null) {
            String fileName = file.getOriginalFilename();
        }
        goods.setPic("../images/" + FileUtil.uploadFile(file));
        return toAjax(goodsService.updateById(goods));
    }


    /**
     * 添加跳转页面(商品订单详情)
     * @return
     */
    @GetMapping("detailBefore/{id}")
    public String detailBefore(Model model,@PathVariable("id")Long id){
        Goods byId = goodsService.getById(id);
        model.addAttribute("goods",byId);
        Goods goods = goodsService.selectDetail(id);
        model.addAttribute("goods",goods);
        return prefix+"details";
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(goodsService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(goodsService.removeByIds(ids));
    }

    /**
     * 查询分类名
     * @return
             */
    @PostMapping("type")
    @ResponseBody
    public AjaxResult selectType(){
        List<GoodsType> goodsTypes = goodsTypeService.selectType();
        System.out.println(goodsTypes);
        return success(goodsTypes);

    }

//    /**
//     *
//     * @return
//     */
//    public AjaxResult selectAll(){
//        Goods goodsAll = goodsService.selectAll();
//        return success(goodsAll);
//
//    }


}

