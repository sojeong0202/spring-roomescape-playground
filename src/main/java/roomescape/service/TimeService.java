package roomescape.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import roomescape.domain.Time;
import roomescape.dto.TimeResponseDto;
import roomescape.dto.TimeSaveRequestDto;
import roomescape.repository.TimeDAO;

@Service
public class TimeService {

    private final TimeDAO timeDAO;

    public TimeService(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    public TimeResponseDto createTime(TimeSaveRequestDto requestDto) {
        if (isTimeArgumentEmpty(requestDto)) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        Time time = new Time(requestDto.getTime());
        Long id = timeDAO.insert(time);

        return new TimeResponseDto(id, time);
    }

    public List<TimeResponseDto> readAllTimes() {
        return timeDAO.findAllTimes()
                .stream()
                .map(TimeResponseDto::new)
                .collect(Collectors.toList());
    }

    public void deleteTime(Long id) {
        timeDAO.delete(id);
    }

    private boolean isTimeArgumentEmpty(TimeSaveRequestDto requestDto) {
        return isStringEmpty(requestDto.getTime());
    }

    private boolean isStringEmpty(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}
