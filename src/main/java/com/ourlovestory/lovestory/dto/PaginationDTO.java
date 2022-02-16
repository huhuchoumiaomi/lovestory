package com.ourlovestory.lovestory.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private  boolean showNext;
    private  boolean showEndPage;
    private Integer currentPage;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount /size + 1;
        }

        //先算出当前页面距离首页和尾页的距离，然后按最大距离3填充pages
        currentPage = page;
        int disFromFirstPage = page - 1;
        int disFromEndPage = totalPage - page;
        for (int i=0; i<3 && i<disFromFirstPage; i++) {
           pages.add(page-i-1);
        }
        Collections.reverse(pages);
        pages.add(page);
        for (int i=0; i<3 && i<disFromEndPage; i++) {
            pages.add(page+i+1);
        }

        //是否展示首页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //是否展示尾页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否展示尾页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
