package com.hxr.springcloud.controller;

import com.hxr.springcloud.entities.Dept;
import com.hxr.springcloud.service.DeptService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * swagger：与controller类相关的注解：
 * 1. @Api注解 ：controller类进行描述
 * 2. @ApiOperation注解 ：对接口进行描述
 * 3. @ApiParam注解 ：对每个参数进行描述
 * 4. @ApiImplicitParams和@ApiImplicitParam注解 ：将所有参数写到注解中统一管理
 */

@RestController
@Api(value = "API - Dept",description = "用于部门的新增和查询")
public class DeptController {

    @Autowired
    private DeptService deptService;



    //TODO 使用RequestMapping注解会产生多个接口，使用GetMapping只会产生一个接口
    @ApiOperation("新增部门接口") //TODO 为swagger的接口上添加信息，与@ApiModel和@ApiModelProperty对比
    @RequestMapping(value = "/dept/add")
    public boolean add(@RequestBody @ApiParam("添加的部门信息") Dept dept) {
        return deptService.add(dept);
    }


    //TODO 接口的返回之中存在实体类，就会被扫描到swagger的model中
    @ApiOperation("按部门id查询部门详细信息")
    @ApiImplicitParams(             //TODO 可以将参数中的@ApiParam注解信息统一写到@ApiImplicitParams注解中
            //paramType表示参数放在哪个地方:header-->请求参数的获取：@RequestHeader(代码中接收注解)、query-->请求参数的获取：@RequestParam(代码中接收注解)、path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)、body-->请求参数的获取：@RequestBody(代码中接收注解)、form（不常用）
            @ApiImplicitParam(name = "id",value = "输入的部门id",defaultValue = "2",required = false,dataType = "Long",paramType = "path")
    )
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable @ApiParam("输入部门id") Long id) {     //TODO @PathVariable注解获取占位符的值;  @ApiParam注解为swagger的参数添加信息,与@ApiModel和@ApiModelProperty对比
        Dept dept = deptService.get(id);
        if (dept == null) {
            throw new RuntimeException("该id：" + id + "没有对应的信息");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable Long id) {
        return new Dept().setDeptno(id)
                .setDname("该id：" + id + "没有对应的信息,null--@HystrixCommand")
                .setDb_source("no this database in DB01"); //TODO 返回由异常信息赋值的Dept类
    }


    @ApiOperation("查询所有部门信息")
    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
//    @ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "just a kidding!")
    public List<Dept> list() {
        System.out.println(deptService.list());
        return deptService.list();
    }


}
