package platform;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CodeRepository extends CrudRepository<Code, Long> {



    Code findCodeById(Long id);
    List<Code> findAll();
    Code findByUuid(String uuid);


}
