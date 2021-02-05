package me.rn00n.server.models.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<T extends BaseEntity, ID> {

    @Autowired
    public BaseRepository<T, ID> repository;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected ObjectMapper objectMapper;

    public T findOne(ID id) {
        Optional<T> byId = repository.findById(id);
        return byId.orElse(null);
    }

    public T save(T baseEntity) {
        return repository.save(baseEntity);
    }

    public List<T> save(List<T> baseEntityList) {
        return repository.saveAll(baseEntityList);
    }

    public void useYn(ID[] ids, String useYn) {
        for (int i = 0; i < ids.length; i++) {
            Optional<T> byId = repository.findById(ids[i]);

            if (byId.isPresent()) {
                byId.get().setUseYn(useYn);
            }
        }
    }
}
