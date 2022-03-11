package com.pairlearning.expensetracker.repositories;

import com.pairlearning.expensetracker.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select ct from Et_Category ct where ct.userId =?1 and ct.categoryId =?2")
    Category fetchCategoryById(int userId, int categoryId);

    @Query("select ct from Et_Category ct where ct.userId =?1")
    List<Category> fetchAllCategoryByUserId(int userId);

//    @Query("update Et_Category set title =?3 and description=?4 where userId =?1 and categoryId =?2")
//    void updateCategory(int userId, int categoryId, String title, String description);
}
