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
 * 荣誉
 *
 * @author 
 * @email
 */
@TableName("rongyu")
public class RongyuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public RongyuEntity() {

	}

	public RongyuEntity(T t) {
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
     * 荣誉名称
     */
    @TableField(value = "rongyu_name")

    private String rongyuName;


    /**
     * 荣誉类型
     */
    @TableField(value = "rongyu_types")

    private Integer rongyuTypes;


    /**
     * 荣誉照片
     */
    @TableField(value = "rongyu_photo")

    private String rongyuPhoto;


    /**
     * 相关文件
     */
    @TableField(value = "rongyu_file")

    private String rongyuFile;


    /**
     * 获取时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "huojiang_time")

    private Date huojiangTime;


    /**
     * 荣誉详情
     */
    @TableField(value = "rongyu_content")

    private String rongyuContent;


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
	 * 设置：荣誉名称
	 */
    public String getRongyuName() {
        return rongyuName;
    }
    /**
	 * 获取：荣誉名称
	 */

    public void setRongyuName(String rongyuName) {
        this.rongyuName = rongyuName;
    }
    /**
	 * 设置：荣誉类型
	 */
    public Integer getRongyuTypes() {
        return rongyuTypes;
    }
    /**
	 * 获取：荣誉类型
	 */

    public void setRongyuTypes(Integer rongyuTypes) {
        this.rongyuTypes = rongyuTypes;
    }
    /**
	 * 设置：荣誉照片
	 */
    public String getRongyuPhoto() {
        return rongyuPhoto;
    }
    /**
	 * 获取：荣誉照片
	 */

    public void setRongyuPhoto(String rongyuPhoto) {
        this.rongyuPhoto = rongyuPhoto;
    }
    /**
	 * 设置：相关文件
	 */
    public String getRongyuFile() {
        return rongyuFile;
    }
    /**
	 * 获取：相关文件
	 */

    public void setRongyuFile(String rongyuFile) {
        this.rongyuFile = rongyuFile;
    }
    /**
	 * 设置：获取时间
	 */
    public Date getHuojiangTime() {
        return huojiangTime;
    }
    /**
	 * 获取：获取时间
	 */

    public void setHuojiangTime(Date huojiangTime) {
        this.huojiangTime = huojiangTime;
    }
    /**
	 * 设置：荣誉详情
	 */
    public String getRongyuContent() {
        return rongyuContent;
    }
    /**
	 * 获取：荣誉详情
	 */

    public void setRongyuContent(String rongyuContent) {
        this.rongyuContent = rongyuContent;
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
        return "Rongyu{" +
            "id=" + id +
            ", xueshengId=" + xueshengId +
            ", rongyuName=" + rongyuName +
            ", rongyuTypes=" + rongyuTypes +
            ", rongyuPhoto=" + rongyuPhoto +
            ", rongyuFile=" + rongyuFile +
            ", huojiangTime=" + huojiangTime +
            ", rongyuContent=" + rongyuContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
