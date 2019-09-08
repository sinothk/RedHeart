package com.sinothk.redheart.controller;

import com.alibaba.fastjson.JSON;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.keyValue.Constant;
import com.sinothk.base.utils.JWTUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.FileCoverEntity;
import com.sinothk.redheart.domain.FileEntity;
import com.sinothk.redheart.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "文件系统")
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource(name = "fileService")
    private FileService fileService;

    private ArrayList<FileEntity> saveFilesIntoDisk(MultipartFile[] fileList, String fileInfo, String sysType, boolean neesCover) {
        FileEntity fileEntity = JSON.parseObject(fileInfo, FileEntity.class);
        return fileService.saveFiles(fileList, fileEntity, sysType, neesCover);
    }

    @ApiOperation(value = "新增：保存文件不需要封面到Linux", notes = "新增：保存文件不需要封面到Linux")
    @PostMapping("/saveFilesByLinux")
    public ResultData<ArrayList<FileEntity>> saveFilesByLinux(@ApiParam(value = "文件信息", required = true) @RequestParam("fileInfo") String fileInfo,
                                                              @ApiParam(value = "文件对象列表", required = true) @RequestParam("files") MultipartFile[] fileList) {
        //http://localhost:8086/file/saveFilesByLinux

        if (fileList == null || fileList.length == 0) {
            return ResultData.error("文件对象不能为空");
        }

        if (StringUtil.isEmpty(fileInfo)) {
            return ResultData.error("未填写文件信息");
        }

        ArrayList<FileEntity> fileEntities = saveFilesIntoDisk(fileList, fileInfo, Constant.LINUX, false);
        if (fileEntities == null) {
            return ResultData.error("文件新增失败");
        } else {
            return ResultData.success(fileEntities);
        }
    }

    @ApiOperation(value = "新增：保存文件需要封面到Win", notes = "新增：保存文件需要封面到Win")
    @PostMapping("/saveFilesWhitCoverByLinux")
    public ResultData<ArrayList<FileEntity>> saveFilesWhitCoverByLinux(@ApiParam(value = "文件信息", required = true) @RequestParam("fileInfo") String fileInfo,
                                                                     @ApiParam(value = "文件对象列表", required = true) @RequestParam("files") MultipartFile[] fileList) {
        if (fileList == null || fileList.length == 0) {
            return ResultData.error("文件对象不能为空");
        }

        if (StringUtil.isEmpty(fileInfo)) {
            return ResultData.error("未填写文件信息");
        }

        ArrayList<FileEntity> fileEntities = saveFilesIntoDisk(fileList, fileInfo, Constant.LINUX, true);
        if (fileEntities == null) {
            return ResultData.error("文件新增失败");
        } else {
            return ResultData.success(fileEntities);
        }
    }

    @ApiOperation(value = "新增：保存文件不需要封面到Win", notes = "新增：保存文件不需要封面到Win")
    @PostMapping("/saveFilesByWin")
    public ResultData<ArrayList<FileEntity>> saveFilesByWin(@ApiParam(value = "文件信息", required = true) @RequestParam("fileInfo") String fileInfo,
                                                            @ApiParam(value = "文件对象列表", required = true) @RequestParam("files") MultipartFile[] fileList) {
        //http://localhost:8086/file/saveFilesByWin

        if (fileList == null || fileList.length == 0) {
            return ResultData.error("文件对象不能为空");
        }

        if (StringUtil.isEmpty(fileInfo)) {
            return ResultData.error("未填写文件信息");
        }

        ArrayList<FileEntity> fileEntities = saveFilesIntoDisk(fileList, fileInfo, Constant.WIN, false);
        if (fileEntities == null) {
            return ResultData.error("文件新增失败");
        } else {
            return ResultData.success(fileEntities);
        }
    }

    @ApiOperation(value = "新增：保存文件需要封面到Win", notes = "新增：保存文件需要封面到Win")
    @PostMapping("/saveFilesWhitCoverByWin")
    public ResultData<ArrayList<FileEntity>> saveFilesWhitCoverByWin(@ApiParam(value = "文件信息", required = true) @RequestParam("fileInfo") String fileInfo,
                                                                     @ApiParam(value = "文件对象列表", required = true) @RequestParam("files") MultipartFile[] fileList) {
        //http://localhost:8086/file/saveFilesWhitCoverByWin

        if (fileList == null || fileList.length == 0) {
            return ResultData.error("文件对象不能为空");
        }

        if (StringUtil.isEmpty(fileInfo)) {
            return ResultData.error("未填写文件信息");
        }

        ArrayList<FileEntity> fileEntities = saveFilesIntoDisk(fileList, fileInfo, Constant.WIN, true);
        if (fileEntities == null) {
            return ResultData.error("文件新增失败");
        } else {
            return ResultData.success(fileEntities);
        }
    }

//    @ApiOperation(value = "新增：保存文件需要封面到Win", notes = "新增：保存文件需要封面到Win")
//    @PostMapping("/add")
//    public ResultData<ArrayList<FileEntity>> add(@ApiParam(value = "文件信息", required = true) @RequestParam("fileInfo") String fileInfo,
//                                                                     @ApiParam(value = "文件对象列表", required = true) @RequestParam("files") MultipartFile[] fileList) {
//        //http://localhost:8086/file/saveFilesWhitCoverByWin
//        if (fileList == null || fileList.length == 0) {
//            return ResultData.error("文件对象不能为空");
//        }
//
//        if (StringUtil.isEmpty(fileInfo)) {
//            return ResultData.error("未填写文件信息");
//        }
//
//        FileEntity fileEntity = JSON.parseObject(fileInfo, FileEntity.class);
//        ArrayList<FileEntity> fileEntities = fileService.saveFilesWhitCoverByWin(fileList, fileEntity.getOwnerUser(), fileEntity.getFileType(), fileEntity.getFileName(), fileEntity.getBizType());
//
//        if (fileEntities == null) {
//            return ResultData.error("文件新增失败");
//        } else {
//            return ResultData.success(fileEntities);
//        }
//    }

    @ApiOperation(value = "删除：根据Id删除文件", notes = "删除文件")
    @DeleteMapping("/delById/{id}")
    public ResultData<Boolean> delFileById(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token
            , @PathVariable String id) {

//        http://192.168.124.12:10002/file/delById/111

        return fileService.delFile(id);
    }

    @ApiOperation(value = "删除：根据fileCode删除业务文件", notes = "删除业务文件")
    @DeleteMapping("/delByCode/{fileCode}")
    @Transactional// 事务回滚
    public ResultData<Boolean> delFileByFileCode(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token
            , @ApiParam(value = "fileCode", required = true) @PathVariable String fileCode) {

        String username = JWTUtil.getUsername(token);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileCode(fileCode);
        fileEntity.setOwnerUser(username);

        return fileService.delFileByFileCode8Owner(fileEntity);
    }

    @ApiOperation(value = "查找业务文件", notes = "查找业务文件")
    @GetMapping("/findFilesByFileCode")
    public ResultData<List<FileEntity>> findFilesByFileCode(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token
            , @RequestParam("fileCode") String fileCode) {

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileCode(fileCode);
        fileEntity.setOwnerUser(JWTUtil.getUsername(token));

        return fileService.findFileByFileCodeAndOwner(fileEntity);
    }

    @ApiOperation(value = "查找单个业务封面文件", notes = "查找单个业务封面文件")
    @GetMapping("/findFileCover")
    public ResultData<FileCoverEntity> findFileCover(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token
            , @RequestParam("fileCode") String fileCode) {

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileCode(fileCode);
        fileEntity.setOwnerUser(JWTUtil.getUsername(token));
        return fileService.findFileCover(fileEntity);
    }

    @ApiOperation(value = "查找用户的业务封面文件", notes = "查找用户的业务封面文件")
    @GetMapping("/findFileCoverByOwner")
    public ResultData<List<FileCoverEntity>> findFileCoverByOwner(
            @ApiParam(value = "验证Token", type = "header", required = true) @RequestHeader(value = "token") String token) {

        return fileService.findFileCoverByOwner(JWTUtil.getUsername(token));
    }
}
