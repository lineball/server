package lineball.server.domain.field;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface FieldRepository {

    Optional<Field> getById(UUID id);

    Collection<Field> findAll();

    void save(Field field);
}
