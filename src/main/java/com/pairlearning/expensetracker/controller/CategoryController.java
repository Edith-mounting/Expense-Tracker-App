package com.pairlearning.expensetracker.controller;

import com.pairlearning.expensetracker.pojo.Category;
import com.pairlearning.expensetracker.request.AddCategory;
import com.pairlearning.expensetracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public List<Category> getAllCategories(HttpServletRequest request){
        int userId = (Integer) request.getAttribute("userId");
        List<Category> allCategory = categoryService.fetchAllCategory(userId);
        return allCategory;
    }
    @GetMapping("/{categoryId}")
    public Category getCategoryById(HttpServletRequest request,@PathVariable("categoryId")int categoryId){
        int userId = (Integer) request.getAttribute("userId");
        return categoryService.fetchCategoryById(userId, categoryId);
    }
    @PostMapping("/add")
    public Category addCategoryFun(HttpServletRequest request, @RequestBody AddCategory addCategory){
        int userId = (Integer) request.getAttribute("userId");
        Category category = categoryService.addCategory(userId, addCategory.getTitle(),addCategory.getDescription());
        return category;
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String,Boolean>> updateCategory(HttpServletRequest request,
                                                              @PathVariable("categoryId")int categoryId,
                                                              @RequestBody Category category){
        int userId = (Integer) request.getAttribute("userId");
        categoryService.updateCategory(userId,categoryId,category);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
