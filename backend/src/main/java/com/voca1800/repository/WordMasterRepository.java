package com.voca1800.repository;

import com.voca1800.entity.master.WordMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordMasterRepository extends JpaRepository<WordMaster, Integer> {

}
