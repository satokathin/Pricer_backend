package asenci.project.pricer.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateCurveRepository<K,V> {
    List<V> getAll();

    V getOne(K k);
}
