package top.guolisha.lisaapplet.service;

import org.springframework.stereotype.Service;
import top.guolisha.lisaapplet.bean.AddContentReq;
import top.guolisha.lisaapplet.mapper.AddContentMapper;

import javax.annotation.Resource;

@Service
public class AddContentService {

    @Resource
    AddContentMapper addContentMapper;

    public long addContentText(AddContentReq req){
        addContentMapper.addContentText(req);
        long id = req.getId();
        return id;
    }
}
