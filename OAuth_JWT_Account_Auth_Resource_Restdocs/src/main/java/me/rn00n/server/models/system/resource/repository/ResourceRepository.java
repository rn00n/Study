package me.rn00n.server.models.system.resource.repository;

import me.rn00n.server.models.base.BaseRepository;
import me.rn00n.server.models.system.resource.domain.Resource;

public interface ResourceRepository extends BaseRepository<Resource, Integer> {
    Resource findByName(String name);
}
