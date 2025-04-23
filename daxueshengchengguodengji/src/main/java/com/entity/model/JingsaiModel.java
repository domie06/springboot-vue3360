package com.entity.model;

import com.entity.JingsaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 竞赛获奖
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JingsaiModel implements Serializable {
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
     * 竞赛名称
     */
    private String jingsaiName;


    /**
     * 竞赛类型
     */
    private Integer jingsaiTypes;


    /**
     * 竞赛地址
     */
    private String jingsaiAddress;


    /**
     * 名次
     */
    private String jingsaiMingci;


    /**
     * 获奖照片
     */
    private String jingsaiPhoto;


    /**
     * 相关文件
     */
    private String jingsaiFile;


    /**
     * 获奖时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date huojiangTime;


    /**
     * 竞赛获奖详情
     */
    private String jingsaiContent;


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
	 * 获取：竞赛名称
	 */
    public String getJingsaiName() {
        return jingsaiName;
    }


    /**
	 * 设置：竞赛名称
	 */
    public void setJingsaiName(String jingsaiName) {
        this.jingsaiName = jingsaiName;
    }
    /**
	 * 获取：竞赛类型
	 */
    public Integer getJingsaiTypes() {
        return jingsaiTypes;
    }


    /**
	 * 设置：竞赛类型
	 */
    public void setJingsaiTypes(Integer jingsaiTypes) {
        this.jingsaiTypes = jingsaiTypes;
    }
    /**
	 * 获取：竞赛地址
	 */
    public String getJingsaiAddress() {
        return jingsaiAddress;
    }


    /**
	 * 设置：竞赛地址
	 */
    public void setJingsaiAddress(String jingsaiAddress) {
        this.jingsaiAddress = jingsaiAddress;
    }
    /**
	 * 获取：名次
	 */
    public String getJingsaiMingci() {
        return jingsaiMingci;
    }


    /**
	 * 设置：名次
	 */
    public void setJingsaiMingci(String jingsaiMingci) {
        this.jingsaiMingci = jingsaiMingci;
    }
    /**
	 * 获取：获奖照片
	 */
    public String getJingsaiPhoto() {
        return jingsaiPhoto;
    }


    /**
	 * 设置：获奖照片
	 */
    public void setJingsaiPhoto(String jingsaiPhoto) {
        this.jingsaiPhoto = jingsaiPhoto;
    }
    /**
	 * 获取：相关文件
	 */
    public String getJingsaiFile() {
        return jingsaiFile;
    }


    /**
	 * 设置：相关文件
	 */
    public void setJingsaiFile(String jingsaiFile) {
        this.jingsaiFile = jingsaiFile;
    }
    /**
	 * 获取：获奖时间
	 */
    public Date getHuojiangTime() {
        return huojiangTime;
    }


    /**
	 * 设置：获奖时间
	 */
    public void setHuojiangTime(Date huojiangTime) {
        this.huojiangTime = huojiangTime;
    }
    /**
	 * 获取：竞赛获奖详情
	 */
    public String getJingsaiContent() {
        return jingsaiContent;
    }


    /**
	 * 设置：竞赛获奖详情
	 */
    public void setJingsaiContent(String jingsaiContent) {
        this.jingsaiContent = jingsaiContent;
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
