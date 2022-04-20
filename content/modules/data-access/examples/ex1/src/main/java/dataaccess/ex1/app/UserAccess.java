package dataaccess.ex1.app;

import dataaccess.ex1.entity.User;
import dataaccess.ex1.repository.UserRepository;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("sample_UserAccess")
public class UserAccess {


    //tag::inject-repository[]
    @Autowired
    private UserRepository userRepository;

    //end::inject-repository[]

    //tag::inject-fetch-plan-repository[]
    @Autowired
    private FetchPlanRepository fetchPlanRepository;

    //end::inject-fetch-plan-repository[]

    private void callRepositoryMethods() {

        //tag::repository-find-all[]
        FetchPlan fetchPlan = fetchPlanRepository.getFetchPlan(User.class, FetchPlan.INSTANCE_NAME);
        Iterable<User> users = userRepository.findAll(fetchPlan);
        //end::repository-find-all[]

        while (users.iterator().hasNext()) {
            greetUser(users.iterator().next());
        }

        userRepository.findByLastNameLike("Doe", PageRequest.of(0, 5, Sort.by("firstName")));

        long activeUsers = userRepository.countByActive(true);

        userRepository.findByLastNameIsLikeAndActive("Doe", false);

    }

    private void greetUser(User user) {

    }

}