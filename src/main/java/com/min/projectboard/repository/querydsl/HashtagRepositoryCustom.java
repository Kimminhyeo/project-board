package com.min.projectboard.repository.querydsl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public interface HashtagRepositoryCustom{
    List<String> findAllHashtagNames();
}
