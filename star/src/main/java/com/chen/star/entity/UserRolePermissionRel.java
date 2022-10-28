package com.chen.star.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限角色关联表
 * </p>
 *
 * @author chenwei
 * @since 2021-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_role_permission_rel")
public class UserRolePermissionRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_role_id")
    private Integer userRoleId;

    @TableField("user_permission_id")
    private Integer userPermissionId;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_by")
    private Long updateBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
