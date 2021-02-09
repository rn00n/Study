package me.rn00n.server.models.system.resource.service;

import me.rn00n.server.models.base.BaseService;
import me.rn00n.server.models.system.resource.domain.Resource;
import me.rn00n.server.models.system.resource.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends BaseService<Resource, Integer> implements ResourceService {

    @Autowired
    private ResourceRepository repository;

}
