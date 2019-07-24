package com.mbyte.easy.rest.goodsImg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.GoodsImg;
import com.mbyte.easy.recycle.service.IGoodsImgService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
* <p>
* 前端控制器
* </p>
* @author Author
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/goodsImg")
public class RestGoodsImgController extends BaseController  {

    @Autowired
    private IGoodsImgService goodsImgService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param goodsImg
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, GoodsImg goodsImg) {
        Page<GoodsImg> page = new Page<GoodsImg>(pageNo, pageSize);
        QueryWrapper<GoodsImg> queryWrapper = new QueryWrapper<GoodsImg>();

        if(goodsImg.getGoodsId() != null  && !"".equals(goodsImg.getGoodsId() + "")) {
            queryWrapper = queryWrapper.like("goods_id",goodsImg.getGoodsId());
         }


        if(goodsImg.getPic() != null  && !"".equals(goodsImg.getPic() + "")) {
            queryWrapper = queryWrapper.like("pic",goodsImg.getPic());
         }


        if(goodsImg.getIsDel() != null  && !"".equals(goodsImg.getIsDel() + "")) {
            queryWrapper = queryWrapper.like("is_del",goodsImg.getIsDel());
         }

        IPage<GoodsImg> pageInfo = goodsImgService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  goodsImg);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }

    /**
     * 查询所有的图片地址
     */
    @RequestMapping("viewAll")
    public AjaxResult viewAll(Long goodsId)
    {
        QueryWrapper<GoodsImg> queryWrapper = new QueryWrapper<GoodsImg>();
        queryWrapper.eq("goods_id",goodsId);
        List<GoodsImg> goodsImgList=goodsImgService.list(queryWrapper);
        return this.success(goodsImgList);
    }

    /**
    * 添加
    * @param goodsImg
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(GoodsImg goodsImg){
        return toAjax(goodsImgService.save(goodsImg));
    }

    /**
    * 添加
    * @param goodsImg
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(GoodsImg goodsImg){
        return toAjax(goodsImgService.updateById(goodsImg));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(goodsImgService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(goodsImgService.removeByIds(ids));
    }

}

