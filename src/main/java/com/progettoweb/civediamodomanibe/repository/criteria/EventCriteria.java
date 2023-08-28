package com.progettoweb.civediamodomanibe.repository.criteria;

import com.progettoweb.civediamodomanibe.core.templates.BaseCriteria;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class EventCriteria extends BaseCriteria {
    private List<String> eventTitle;
    private Long organiserId;
    private Long followerId;
    private Long attendeeId;
    private List<String> categories;
}
