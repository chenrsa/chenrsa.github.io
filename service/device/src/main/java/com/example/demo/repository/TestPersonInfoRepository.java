package com.example.demo.repository;

import com.example.demo.entity.TestPersonInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenrunzheng
 * @since 2020/3/24 16:04
 */
@Repository
public interface TestPersonInfoRepository extends JpaRepository<TestPersonInfoEntity,Integer> {

    TestPersonInfoEntity findFirstByName(String name);

}
