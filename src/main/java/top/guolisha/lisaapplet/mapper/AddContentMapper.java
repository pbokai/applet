package top.guolisha.lisaapplet.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.guolisha.lisaapplet.bean.AddContentReq;

@Mapper
public interface AddContentMapper {

     int addContentText(AddContentReq req);


}
