package api;

import config.Config;
import io.restassured.response.Response;
import org.example.api.ApiClientBooking;
import org.example.api.models.Booking;
import org.example.api.models.BookingDates;
import org.example.api.models.BookingResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiBookingTest {
    private final ApiClientBooking bookingClient = new ApiClientBooking(Config.get("api.base.url"));
    private final Booking booking = new Booking("Jim",
            "Brown",
            111,
            true,
            new BookingDates("2018-01-01", "2019-01-01"),
            "Breakfast");

    @Test
    public void createdBookingCanBeRetrievedById() {
        Response createResponse = bookingClient.createBooking(booking);
        assertEquals(200, createResponse.statusCode(), "POST /booking status");
        BookingResponse createdBooking = createResponse.as(BookingResponse.class);
        Integer bookingId = createdBooking.bookingid();
        assertNotNull(bookingId);

        Response getResponse = bookingClient.getBooking(bookingId);
        assertEquals(200, getResponse.statusCode(), "GET /booking/id status");
        Booking actualBooking = getResponse.as(Booking.class);
        assertEquals(booking, actualBooking);
    }

    @ParameterizedTest(name = "POST /booking without {0} returns 500")
    @MethodSource("bookingsWithMissingField")
    void bookingWithoutRequiredFieldIsRejected(String missingField, Booking booking) {
        Response response = bookingClient.createBooking(booking);
        assertEquals(500, response.statusCode(), "POST /booking without " + missingField);
    }

    private static Stream<Arguments> bookingsWithMissingField() {
        BookingDates dates = new BookingDates("2018-01-01", "2019-01-01");
        return Stream.of(
                Arguments.of("firstname", new Booking(null, "Brown", 111, true, dates, "Breakfast")),
                Arguments.of("lastname", new Booking("Jim", null, 111, true, dates, "Breakfast")),
                Arguments.of("totalprice", new Booking("Jim", "Brown", null, true, dates, "Breakfast")),
                Arguments.of("depositpaid", new Booking("Jim", "Brown", 111, null, dates, "Breakfast")),
                Arguments.of("bookingdates", new Booking("Jim", "Brown", 111, true, null, "Breakfast"))
        );
    }
}