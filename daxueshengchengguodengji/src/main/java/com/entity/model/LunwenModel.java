package com.entity.model;

import com.entity.LunwenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 论文
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LunwenModel implements Serializable {
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
     * 论文编号
     */
    private String lunwenUuidNumber;


    /**
     * 论文标题
     */
    private String lunwenName;


    /**
     * 论文类型
     */
    private Integer lunwenTypes;


    /**
     * 论文文件
     */
    private String lunwenFile;


    /**
     * 论文摘要
     */
    private String lunwenContent;


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
	 * 获取：论文编号
	 */
    public String getLunwenUuidNumber() {
        return lunwenUuidNumber;
    }


    /**
	 * 设置：论文编号
	 */
    public void setLunwenUuidNumber(String lunwenUuidNumber) {
        this.lunwenUuidNumber = lunwenUuidNumber;
    }
    /**
	 * 获取：论文标题
	 */
    public String getLunwenName() {
        return lunwenName;
    }


    /**
	 * 设置：论文标题
	 */
    public void setLunwenName(String lunwenName) {
        this.lunwenName = lunwenName;
    }
    /**
	 * 获取：论文类型
	 */
    public Integer getLunwenTypes() {
        return lunwenTypes;
    }


    /**
	 * 设置：论文类型
	 */
    public void setLunwenTypes(Integer lunwenTypes) {
        this.lunwenTypes = lunwenTypes;
    }
    /**
	 * 获取：论文文件
	 */
    public String getLunwenFile() {
        return lunwenFile;
    }


    /**
	 * 设置：论文文件
	 */
    public void setLunwenFile(String lunwenFile) {
        this.lunwenFile = lunwenFile;
    }
    /**
	 * 获取：论文摘要
	 */
    public String getLunwenContent() {
        return lunwenContent;
    }


    /**
	 * 设置：论文摘要
	 */
    public void setLunwenContent(String lunwenContent) {
        this.lunwenContent = lunwenContent;
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
