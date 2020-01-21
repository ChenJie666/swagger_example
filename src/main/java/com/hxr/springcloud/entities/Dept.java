package com.hxr.springcloud.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * swagger：和model类相关的注解
 * 1. @ApiModel ：model类进行描述
 * 2. @ApiModelProperty ：对属性进行描述
 */

@SuppressWarnings("serial")
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //空参构造器
@Data   //生成get/set、toString等函数
@Accessors(chain=true) //可以链式调用方法
@ApiModel("部门实体类")  //TODO 用于swagger的model中展示的类信息，与@ApiOperation、@ApiParam对比
public class Dept implements Serializable {

    @ApiModelProperty("部门号")    //TODO swagger的model中展示的字段信息，与@ApiOperation、@ApiParam对比
    private Long deptno;    //主键
    @ApiModelProperty("部门名称")
    private String dname;   //部门名称
    @ApiModelProperty("数据库名称")
    private String db_source;   //数据库名称

}