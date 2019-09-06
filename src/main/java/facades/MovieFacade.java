package facades;

import dto.MovieDTO;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO findMovie(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);
            return new MovieDTO(movie);
        } finally {
            em.close();
        }
    }

    public MovieDTO addMovie(Movie movie) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return new MovieDTO(movie);
        } finally {
            em.close();
        }
    }

    public long getMovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long movieCount = (long) em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return movieCount;
        } finally {
            em.close();
        }

    }

    public List<MovieDTO> getAllMovies() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Movie> query
                    = em.createQuery("SELECT m FROM Movie m", Movie.class);

            List<Movie> movies = query.getResultList();
            List<MovieDTO> l = new ArrayList<>();
            for (Movie m : movies) {
                l.add(new MovieDTO(m));
            }

            return l;
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> findMovieByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery<Movie> tq = em.createNamedQuery("Movie.getByName", Movie.class);
        tq.setParameter("name", "%" + name + "%");
        List<Movie> movies = tq.getResultList();
        List<MovieDTO> l = new ArrayList<>();
        for (Movie m : movies) {
            l.add(new MovieDTO(m));
        }
        return l;
    }
}
