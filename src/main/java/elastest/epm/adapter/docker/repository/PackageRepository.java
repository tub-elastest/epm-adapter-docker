package elastest.epm.adapter.docker.repository;

import elastest.epm.adapter.docker.model.Package;
import org.springframework.data.repository.CrudRepository;

public interface PackageRepository extends CrudRepository<Package, String> {
}
