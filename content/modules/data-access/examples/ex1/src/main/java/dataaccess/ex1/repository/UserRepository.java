package dataaccess.ex1.repository;

import dataaccess.ex1.entity.User;
import io.jmix.core.repository.JmixDataRepository;
import io.jmix.core.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

//tag::jmix-repository-1[]
public interface UserRepository extends JmixDataRepository<User, UUID> {
//end::jmix-repository-1[]

    //tag::jmix-repository-methods[]
    List<User> findByLastNameLike(String lastName, Pageable pageable);

    long countByActive(Boolean active);
    //end::jmix-repository-methods[]

    //tag::jmix-repository-query[]
    @Query("select u from User u where u.lastName like ?1 and u.active = ?2")
    List<User> findByLastNameIsLikeAndActive(String lastName, Boolean active);
    //end::jmix-repository-query[]

//tag::jmix-repository-2[]
}
//end::jmix-repository-2[]
