package com.entity.model;

import com.entity.RongyuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 荣誉
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class RongyuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 学生
     */
    private Integer xueshengId;


    /**
     * 荣誉名称
     */
    private String rongyuName;


    /**
     * 荣誉类型
     */
    private Integer rongyuTypes;


    /**
     * 荣誉照片
     */
    private String rongyuPhoto;


    /**
     * 相关文件
     */
    private String rongyuFile;


    /**
     * 获取时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date huojiangTime;


    /**
     * 荣誉详情
     */
    private String rongyuContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 设置：学生
	 */
    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 获取：荣誉名称
	 */
    public String getRongyuName() {
        return rongyuName;
    }


    /**
	 * 设置：荣誉名称
	 */
    public void setRongyuName(String rongyuName) {
        this.rongyuName = rongyuName;
    }
    /**
	 * 获取：荣誉类型
	 */
    public Integer getRongyuTypes() {
        return rongyuTypes;
    }


    /**
	 * 设置：荣誉类型
	 */
    public void setRongyuTypes(Integer rongyuTypes) {
        this.rongyuTypes = rongyuTypes;
    }
    /**
	 * 获取：荣誉照片
	 */
    public String getRongyuPhoto() {
        return rongyuPhoto;
    }


    /**
	 * 设置：荣誉照片
	 */
    public void setRongyuPhoto(String rongyuPhoto) {
        this.rongyuPhoto = rongyuPhoto;
    }
    /**
	 * 获取：相关文件
	 */
    public String getRongyuFile() {
        return rongyuFile;
    }


    /**
	 * 设置：相关文件
	 */
    public void setRongyuFile(String rongyuFile) {
        this.rongyuFile = rongyuFile;
    }
    /**
	 * 获取：获取时间
	 */
    public Date getHuojiangTime() {
        return huojiangTime;
    }


    /**
	 * 设置：获取时间
	 */
    public void setHuojiangTime(Date huojiangTime) {
        this.huojiangTime = huojiangTime;
    }
    /**
	 * 获取：荣誉详情
	 */
    public String getRongyuContent() {
        return rongyuContent;
    }


    /**
	 * 设置：荣誉详情
	 */
    public void setRongyuContent(String rongyuContent) {
        this.rongyuContent = rongyuContent;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
