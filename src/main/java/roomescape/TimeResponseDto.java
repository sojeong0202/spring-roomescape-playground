package roomescape;

public class TimeResponseDto {

    private Long id;
    private String time;

    public TimeResponseDto(Time time) {
        this.id = time.getId();
        this.time = time.getTime();
    }

    public TimeResponseDto(Long id, Time time) {
        this.id = id;
        this.time = time.getTime();
    }

    public Long getId() {
        return this.id;
    }

    public String getTime() {
        return this.time;
    }
}
