package com.example.demo.repositories;

import java.util.List;

import com.example.demo.entities.LopHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LopHocRepository extends JpaRepository<LopHoc, Long> {

  List<LopHoc> findAllByNameLikeAndLocationLike(String name, String location);
}
