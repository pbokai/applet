package top.guolisha.lisaapplet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.guolisha.lisaapplet.bean.AddContentReq;
import top.guolisha.lisaapplet.service.AddContentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AddContentController {

    private static Logger logger = LoggerFactory.getLogger(AddContentController.class);
    @Autowired
    AddContentService addContentService;

    @RequestMapping("/addContent")
    public String addContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("jkjk");
        return null;
    }

    @RequestMapping("/addContentText")
    public Map addContentText(AddContentReq addContentReqeq){
        logger.info("AddContentController_addContentText_AddContentReq"+addContentReqeq);
        Map resultMap = new HashMap<>();
        System.out.println(addContentReqeq.getContent());
        long contentid = addContentService.addContentText(addContentReqeq);
        resultMap.put("contentid",contentid);
        return resultMap;
    }

    @RequestMapping("/addUpload")
    public String addUpload(HttpServletRequest request,int contentid,@RequestParam(value = "file", required = false) MultipartFile file) throws IOException{
        logger.info("AddContentController_addContentText_AddContentReq: 执行upload");
        request.setCharacterEncoding("UTF-8");
        if(!file.isEmpty()){
            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    path = realPath + "/uploads/" + trueFileName;
                    logger.info("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));
                    logger.info("文件成功上传到指定目录下");
                }else {
                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                    return "error";
                }
            }else {
                logger.info("文件类型为空");
                return "error";
            }
        }else {
            logger.info("没有找到相对应的文件");
            return "error";
        }
        return "success";
    }

}
