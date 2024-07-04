package roomescape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Long id;
    private String name;
    private String date;
    private String time;

    public Reservation(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static Reservation of(Long id, Reservation reservation) {
        return new Reservation(id, reservation.name, reservation.date, reservation.time);
    }
}
