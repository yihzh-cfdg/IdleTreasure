package com.itmk.web.sys_user.entity;

import lombok.Data;


@Data
public class PageParm {
    private Long currentPage;
    private Long pageSize;
    private String nickName;
}
//模糊查询