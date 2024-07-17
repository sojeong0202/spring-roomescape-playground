package roomescape;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TimeController {

    private final TimeDAO timeDAO;

    public TimeController(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    @GetMapping("/time")
    public String goReservationPage() {
        return "time";
    }

    @PostMapping("/times")
    public ResponseEntity<TimeResponseDto> createTime(@RequestBody TimeSaveRequestDto requestDto) {
        if (isTimeArgumentEmpty(requestDto)) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        Time time = new Time(requestDto.getTime());

        Long id = timeDAO.insert(time);

        return ResponseEntity.created(URI.create("/times/" + id))
                .body(new TimeResponseDto(id, time));
    }

    @GetMapping("/times")
    public ResponseEntity<List<TimeResponseDto>> readAllTimes() {
        List<TimeResponseDto> timeResponseDtos = timeDAO.findAllTimes()
                .stream()
                .map(TimeResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(timeResponseDtos);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable Long id) {
        timeDAO.delete(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isTimeArgumentEmpty(TimeSaveRequestDto requestDto) {
        return isStringEmpty(requestDto.getTime());
    }

    private boolean isStringEmpty(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}
