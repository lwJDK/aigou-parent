package org.li.common.controller;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.li.AjaxResult;
import org.li.FastDfsApiOpr;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FastDfsController {

    /**
     * 文件上传
     * @return
     */
    @PostMapping("/fastdfs")
    public AjaxResult upload(MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            String name = fileName.substring(fileName.lastIndexOf(".") + 1);
            String upload = FastDfsApiOpr.upload(file.getBytes(), name);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("上传成功!!").setObj(upload);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("上传失败!!");
        }
    }


    /**
     * 文件删除
     * @return
     */
    @DeleteMapping("/fastdfs")
    public AjaxResult delete(String fileId){
        // fileId = /group1/M00/00/01/wKgruF1EozWAZzmcAADyEmD2Gvo059.jpg
        if(fileId==null||fileId.length()<=0){
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("请选择要上传的文件!!");
        }
        fileId = fileId.substring(1);
        String gropName = fileId.substring(0, fileId.indexOf("/"));
        String fileName = fileId.substring(fileId.indexOf("/")+1);
        try {
            FastDfsApiOpr.delete(gropName, fileName);
            return AjaxResult.getAjaxResult().setSuccess(true).setMessage("删除文件成功!!");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setSuccess(false).setMessage("删除文件失败!!");
        }
    }
}
