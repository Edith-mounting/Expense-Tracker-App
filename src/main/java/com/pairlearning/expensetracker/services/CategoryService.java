package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.exceptions.EtBadRequestException;
import com.pairlearning.expensetracker.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetracker.pojo.Category;
import com.pairlearning.expensetracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    public Category fetchCategoryById(int userId, int categoryId) throws EtResourceNotFoundException{
        try{
            return categoryRepository.fetchCategoryById(userId,categoryId);
        }catch (Exception e){
            throw new EtResourceNotFoundException("category not found!");
        }
    }
    public Category addCategory(int userId, String title, String description) throws EtBadRequestException{
        Category category = new Category();
        category.setTitle(title);
        category.setDescription(description);
        category.setUserId(userId);
        category.setTotalExpense((double) 0.00);
        try{
            return categoryRepository.save(category);
        }catch (Exception e){
            throw new EtBadRequestException("Invalid Request");
        }

    }
    public List<Category> fetchAllCategory(int userId) throws EtResourceNotFoundException{
        try{
            return categoryRepository.fetchAllCategoryByUserId(userId);
        }
        catch (Exception e){
            throw new EtResourceNotFoundException("userId is invalid!");
        }
    }
    public void updateCategory(int userId, int categoryId, Category category) throws EtBadRequestException {
        try{
            category.setCategoryId(categoryId);
            category.setUserId(userId);
            categoryRepository.save(category);
        } catch (Exception e){
            throw new EtBadRequestException("Invalid Request!");
        }
    }
//    void removeCategoryWithAllTransactions(int userId, int categoryId) throws EtResourceNotFoundException{
//
//    }
}
