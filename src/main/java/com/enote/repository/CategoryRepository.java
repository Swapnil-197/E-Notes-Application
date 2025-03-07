package com.enote.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enote.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer> {

	public List<Category> findByIsActiveTrueAndIsDeletedFalse();

	public Optional<Category> findByIdAndIsDeletedFalse(Integer id);

	public List<Category> findByIsDeletedFalse();

	

}
