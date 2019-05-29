package lineball.server.app.game.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TurnDto {

    PlayerType playerType;
    List<DotDto> moves;
}
