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
public class ReservationController {

    private final ReservationDAO reservationDAO;

    public ReservationController(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    @GetMapping("/reservation")
    public String goReservationPage() {
        return "reservation";
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> createReservation(@RequestBody ReservationSaveRequestDto requestDto) {
        if (isReservationArgumentEmpty(requestDto)) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        Reservation reservation = new Reservation(requestDto.getName(), requestDto.getDate(), requestDto.getTime());

        Long id = reservationDAO.insert(reservation);

        return ResponseEntity.created(URI.create("/reservations/" + id))
                .body(new ReservationResponseDto(id, reservation));
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDto>> readAllReservations() {
        List<ReservationResponseDto> reservationResponseDtos = reservationDAO.findAllReservations()
                .stream()
                .map(ReservationResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(reservationResponseDtos);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationDAO.delete(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isReservationArgumentEmpty(ReservationSaveRequestDto requestDto) {
        return isStringEmpty(requestDto.getName())
                || isStringEmpty(requestDto.getDate())
                || isStringEmpty(requestDto.getTime());
    }

    private boolean isStringEmpty(String argument) {
        return argument == null || argument.trim().isEmpty();
    }
}
