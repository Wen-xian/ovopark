package com.alibaba.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AggregateRoot extends BaseEntity {
    protected Long version;
}
