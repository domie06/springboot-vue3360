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
 * 论文
 *
 * @author 
 * @email
 */
@TableName("lunwen")
public class LunwenEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LunwenEntity() {

	}

	public LunwenEntity(T t) {
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
     * 论文编号
     */
    @TableField(value = "lunwen_uuid_number")

    private String lunwenUuidNumber;


    /**
     * 论文标题
     */
    @TableField(value = "lunwen_name")

    private String lunwenName;


    /**
     * 论文类型
     */
    @TableField(value = "lunwen_types")

    private Integer lunwenTypes;


    /**
     * 论文文件
     */
    @TableField(value = "lunwen_file")

    private String lunwenFile;


    /**
     * 论文摘要
     */
    @TableField(value = "lunwen_content")

    private String lunwenContent;


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
	 * 设置：论文编号
	 */
    public String getLunwenUuidNumber() {
        return lunwenUuidNumber;
    }
    /**
	 * 获取：论文编号
	 */

    public void setLunwenUuidNumber(String lunwenUuidNumber) {
        this.lunwenUuidNumber = lunwenUuidNumber;
    }
    /**
	 * 设置：论文标题
	 */
    public String getLunwenName() {
        return lunwenName;
    }
    /**
	 * 获取：论文标题
	 */

    public void setLunwenName(String lunwenName) {
        this.lunwenName = lunwenName;
    }
    /**
	 * 设置：论文类型
	 */
    public Integer getLunwenTypes() {
        return lunwenTypes;
    }
    /**
	 * 获取：论文类型
	 */

    public void setLunwenTypes(Integer lunwenTypes) {
        this.lunwenTypes = lunwenTypes;
    }
    /**
	 * 设置：论文文件
	 */
    public String getLunwenFile() {
        return lunwenFile;
    }
    /**
	 * 获取：论文文件
	 */

    public void setLunwenFile(String lunwenFile) {
        this.lunwenFile = lunwenFile;
    }
    /**
	 * 设置：论文摘要
	 */
    public String getLunwenContent() {
        return lunwenContent;
    }
    /**
	 * 获取：论文摘要
	 */

    public void setLunwenContent(String lunwenContent) {
        this.lunwenContent = lunwenContent;
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
        return "Lunwen{" +
            "id=" + id +
            ", xueshengId=" + xueshengId +
            ", lunwenUuidNumber=" + lunwenUuidNumber +
            ", lunwenName=" + lunwenName +
            ", lunwenTypes=" + lunwenTypes +
            ", lunwenFile=" + lunwenFile +
            ", lunwenContent=" + lunwenContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
