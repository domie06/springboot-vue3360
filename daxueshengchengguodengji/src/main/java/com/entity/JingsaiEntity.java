package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 竞赛获奖
 *
 * @author 
 * @email
 */
@TableName("jingsai")
public class JingsaiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JingsaiEntity() {

	}

	public JingsaiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 学生
     */
    @TableField(value = "xuesheng_id")

    private Integer xueshengId;


    /**
     * 竞赛名称
     */
    @TableField(value = "jingsai_name")

    private String jingsaiName;


    /**
     * 竞赛类型
     */
    @TableField(value = "jingsai_types")

    private Integer jingsaiTypes;


    /**
     * 竞赛地址
     */
    @TableField(value = "jingsai_address")

    private String jingsaiAddress;


    /**
     * 名次
     */
    @TableField(value = "jingsai_mingci")

    private String jingsaiMingci;


    /**
     * 获奖照片
     */
    @TableField(value = "jingsai_photo")

    private String jingsaiPhoto;


    /**
     * 相关文件
     */
    @TableField(value = "jingsai_file")

    private String jingsaiFile;


    /**
     * 获奖时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "huojiang_time")

    private Date huojiangTime;


    /**
     * 竞赛获奖详情
     */
    @TableField(value = "jingsai_content")

    private String jingsaiContent;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }
    /**
	 * 获取：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 设置：竞赛名称
	 */
    public String getJingsaiName() {
        return jingsaiName;
    }
    /**
	 * 获取：竞赛名称
	 */

    public void setJingsaiName(String jingsaiName) {
        this.jingsaiName = jingsaiName;
    }
    /**
	 * 设置：竞赛类型
	 */
    public Integer getJingsaiTypes() {
        return jingsaiTypes;
    }
    /**
	 * 获取：竞赛类型
	 */

    public void setJingsaiTypes(Integer jingsaiTypes) {
        this.jingsaiTypes = jingsaiTypes;
    }
    /**
	 * 设置：竞赛地址
	 */
    public String getJingsaiAddress() {
        return jingsaiAddress;
    }
    /**
	 * 获取：竞赛地址
	 */

    public void setJingsaiAddress(String jingsaiAddress) {
        this.jingsaiAddress = jingsaiAddress;
    }
    /**
	 * 设置：名次
	 */
    public String getJingsaiMingci() {
        return jingsaiMingci;
    }
    /**
	 * 获取：名次
	 */

    public void setJingsaiMingci(String jingsaiMingci) {
        this.jingsaiMingci = jingsaiMingci;
    }
    /**
	 * 设置：获奖照片
	 */
    public String getJingsaiPhoto() {
        return jingsaiPhoto;
    }
    /**
	 * 获取：获奖照片
	 */

    public void setJingsaiPhoto(String jingsaiPhoto) {
        this.jingsaiPhoto = jingsaiPhoto;
    }
    /**
	 * 设置：相关文件
	 */
    public String getJingsaiFile() {
        return jingsaiFile;
    }
    /**
	 * 获取：相关文件
	 */

    public void setJingsaiFile(String jingsaiFile) {
        this.jingsaiFile = jingsaiFile;
    }
    /**
	 * 设置：获奖时间
	 */
    public Date getHuojiangTime() {
        return huojiangTime;
    }
    /**
	 * 获取：获奖时间
	 */

    public void setHuojiangTime(Date huojiangTime) {
        this.huojiangTime = huojiangTime;
    }
    /**
	 * 设置：竞赛获奖详情
	 */
    public String getJingsaiContent() {
        return jingsaiContent;
    }
    /**
	 * 获取：竞赛获奖详情
	 */

    public void setJingsaiContent(String jingsaiContent) {
        this.jingsaiContent = jingsaiContent;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jingsai{" +
            "id=" + id +
            ", xueshengId=" + xueshengId +
            ", jingsaiName=" + jingsaiName +
            ", jingsaiTypes=" + jingsaiTypes +
            ", jingsaiAddress=" + jingsaiAddress +
            ", jingsaiMingci=" + jingsaiMingci +
            ", jingsaiPhoto=" + jingsaiPhoto +
            ", jingsaiFile=" + jingsaiFile +
            ", huojiangTime=" + huojiangTime +
            ", jingsaiContent=" + jingsaiContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
