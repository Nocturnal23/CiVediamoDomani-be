package com.progettoweb.checasavuoibe.commons;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
public class BaseCriteria {
    private List<Long> ids;
    private List<Long> excludedIds;
    private int pageNumber = 0;
    private int pageSize = 20;
    private String sortDirection = Sort.Direction.ASC.name();
    private String orderBy = "id";
}
