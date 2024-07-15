package roomescape;

public class ReservationSaveRequestDto {

    private String name;
    private String date;
    private String time;

    public ReservationSaveRequestDto() {
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }
}
