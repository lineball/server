package lineball.server.persistence.memory;

import lineball.server.domain.field.Field;
import lineball.server.domain.field.FieldRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FieldInMemoryRepository implements FieldRepository {

    private final ConcurrentHashMap<UUID, Field> map = new ConcurrentHashMap<>();

    @Override
    public Optional<Field> getById(UUID id) {
        return Optional.of(map.get(id));
    }

    @Override
    public Collection<Field> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void save(Field field) {
        map.put(field.getId(), field);
    }
}
