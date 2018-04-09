package com.spc.rmm.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, origins = "*")
@RequestMapping(value = "/resumemana")
public class ResumeManagmentController {

    @ApiOperation(value = "根据名字获取简历列表")
    @RequestMapping(value = "/name/get", method = RequestMethod.GET)
    List<String> listResumeByName(String name) {
        return null;
    }

    @ApiOperation(value = "根据ID获取简历信息")
    @RequestMapping(value = "/id/get", method = RequestMethod.GET)
    String getResumeById(String id) {
        return null;
    }

    @ApiOperation(value = "保存简历信息")
    @RequestMapping(value = "/resume/save", method = RequestMethod.POST)
    String saveResume(String resume) {
        return null;
    }

    @ApiOperation(value = "删除简历信息")
    @RequestMapping(value = "/resume/remove", method = RequestMethod.DELETE)
    String removeResume(String resume) {
        return null;
    }

    @ApiOperation(value = "更新简历信息")
    @RequestMapping(value = "/resume/update", method = RequestMethod.PUT)
    String updateResume(String resume) {
        return null;
    }
}
