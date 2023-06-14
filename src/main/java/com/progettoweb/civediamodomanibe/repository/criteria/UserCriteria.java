package com.progettoweb.civediamodomanibe.repository.criteria;

import com.progettoweb.civediamodomanibe.core.templates.BaseCriteria;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserCriteria extends BaseCriteria {
    private String firstNameSearch;
    private String lastNameSearch;
}
