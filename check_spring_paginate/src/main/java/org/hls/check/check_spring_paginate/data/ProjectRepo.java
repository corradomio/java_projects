package org.hls.check.check_spring_paginate.data;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Project.class, idClass = Long.class)
public interface ProjectRepo {
    List<Project> findAll();
}
