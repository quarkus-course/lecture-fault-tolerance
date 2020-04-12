package tech.donau;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;
import tech.donau.data.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Path("/book")
public class BookRepository {
    public static Logger LOGGER = Logger.getLogger(BookRepository.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @CircuitBreaker(failureRatio = 0.5, requestVolumeThreshold = 5, failOn = RuntimeException.class, delay = 20000L)
    public List<Book> getBooks(@QueryParam("id") String id) {
        if (true) {
            LOGGER.info("Couldn't connect to database");
            throw new RuntimeException("Couldn't connect to database");
        }
        return Arrays.asList(
                new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2"),
                new Book("3", "Book3", "Author3")
        );
    }

    public List<Book> getFallbackBooks(@QueryParam("id") String id) {
        LOGGER.info("Id is " + id);
        return Arrays.asList(
                new Book("1", "Fallback Book1", "Author1"),
                new Book("2", "Fallback Book2", "Author2"),
                new Book("3", "Fallback Book3", "Author3")
        );
    }

}