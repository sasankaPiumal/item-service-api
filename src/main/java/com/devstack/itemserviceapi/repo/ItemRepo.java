package com.devstack.itemserviceapi.repo;

import com.devstack.itemserviceapi.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item, Long> {
    Optional<Item> findByCode(long id);

    public void deleteByCode(long id);

    @Query(value = "SELECT * FROM item WHERE description LIKE %?1% OR unit_price LIKE %?1%",nativeQuery = true)
    public Page<Item> searchItemByDescriptionOrUnitPrice(String searchText, PageRequest of);

    @Query(value = "SELECT COUNT(item_id) FROM item WHERE description LIKE %?1% OR unit_price LIKE %?1%",nativeQuery = true)
    public long countBySearchText(String searchText);
}
