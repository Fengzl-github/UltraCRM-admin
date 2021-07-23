package com.cn.admin.api.gg.vo.menu;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *@Author fengzhilong
 *@Date 2021/7/23 15:26
 *@Desc
 **/
@Data
public class MenuEditVO {

    //拖拽中节点id
    @NotBlank(message = "缺少拖拽节点id")
    private String sourceId;
    //目标节点id
    @NotBlank(message="缺少目标节点id")
    private String targetId;
    //拖拽类型
    //'before'、'inner' 和 'after'，分别表示放置在目标节点前、插入至目标节点和放置在目标节点后
    @NotBlank(message = "缺少拖拽类型")
    private String dropType;
}
