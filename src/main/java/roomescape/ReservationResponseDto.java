package roomescape;

public class ReservationResponseDto {

    private final Long id;
    private final String name;
    private final String date;
    private final String time;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.name = reservation.getName();
        this.date = reservation.getDate();
        this.time = reservation.getTime();
    }

    public ReservationResponseDto(Long id, Reservation reservation) {
        this.id = id;
        this.name = reservation.getName();
        this.date = reservation.getDate();
        this.time = reservation.getTime();
    }

    public Long getId() {
        return this.id;
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
