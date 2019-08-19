package com.boot.security.server.utils;


import java.util.Date;
import java.util.List;

/**
 * <p>
 * 中信易家后端类目
 * </p>
 *
 * @author ${author}
 * @since 2019-08-10
 */
//@Data
//@Table(name="zxyj_cate")
public class Cate  {

    private static final long serialVersionUID = 1L;

    /**
     * 后端类目ID
     */
    //@Id
    private String id;
    /**
     * 属性组ID
     */
   //@Column(name ="product_type_id")
    private String productTypeId;
    /**
     * 父后端类目ID，一级类目该值为0
     */
    private String pid;
    /**
     * 后端类目名称
     */
    private String name;
    /**
     * 上级后端类目路径，如: /1/2/
     */
    private String path;
    /**
     * 创建时间
     */
  // @Column(name ="create_time")
    private Date createTime;
    /**
     * 更新时间
     */
   //@Column(name ="update_time")
    private Date updateTime;
    /**
     * 创建者ID
     */
  // @Column(name ="create_user_id")
    private String createUserId;
    /**
     * 更新者ID
     */
  // @Column(name ="update_user_id")
    private String updateUserId;
   
   List<Cate> cates;


public String getId() {
    return id;
}


public void setId(String id) {
    this.id = id;
}


public String getProductTypeId() {
    return productTypeId;
}


public void setProductTypeId(String productTypeId) {
    this.productTypeId = productTypeId;
}


public String getPid() {
    return pid;
}


public void setPid(String pid) {
    this.pid = pid;
}


public String getName() {
    return name;
}


public void setName(String name) {
    this.name = name;
}


public String getPath() {
    return path;
}


public void setPath(String path) {
    this.path = path;
}


public Date getCreateTime() {
    return createTime;
}


public void setCreateTime(Date createTime) {
    this.createTime = createTime;
}


public Date getUpdateTime() {
    return updateTime;
}


public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
}


public String getCreateUserId() {
    return createUserId;
}


public void setCreateUserId(String createUserId) {
    this.createUserId = createUserId;
}


public String getUpdateUserId() {
    return updateUserId;
}


public void setUpdateUserId(String updateUserId) {
    this.updateUserId = updateUserId;
}


public List<Cate> getCates() {
    return cates;
}


public void setCates(List<Cate> cates) {
    this.cates = cates;
}


public static long getSerialversionuid() {
    return serialVersionUID;
}


}
