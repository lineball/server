package lineball.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor
@Data
public class FieldDto {
    UUID id;
    UUID whiteId;
    UUID blackId;
}
