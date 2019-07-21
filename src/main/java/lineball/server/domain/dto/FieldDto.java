package lineball.server.domain.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class FieldDto {
    UUID id;
    UUID whiteId;
    UUID blackId;
}
