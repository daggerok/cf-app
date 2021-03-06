package daggerok.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.stream.Stream;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    Stream<User> findAllByUsername(@Param("username") final String username);
}
