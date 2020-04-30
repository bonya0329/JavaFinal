package com.project.demo.controller;

import com.project.demo.entities.Category;
import com.project.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/pageAddCategory")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String pageAddCategory(){
        return "moderator/addCategory";
    }

    @PostMapping(value = "/addCategory")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String addCategory(@RequestParam String name){
        String redirect="redirect:/category/pageAddCategory?";
        redirect+=categoryService.addCategory(name);

        return redirect;
    }

    @GetMapping(value = "/pageCategoriesList")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String pageCategoriesList(ModelMap model,
                                     @RequestParam(name = "p", defaultValue = "1") int page){
        page = (page<=0) ? 1 : page;
        Pageable pageable = PageRequest.of(page-1, 5);
      // List<Category> categories = categoryService.allCategories();
        Page<Category> categories = categoryService.allCategories(pageable);
        model.addAttribute("categories", categories);
        return "moderator/categoriesList";
    }

    @GetMapping(value = "/pageCategoryEdit/{id}")
    public String pageCategoryEdit(ModelMap model, @PathVariable(name = "id") Long id){
        Category category = categoryService.categoryById(id);
        model.addAttribute("category", category);
        return "moderator/editCategory";
    }

    @PostMapping(value = "/editCategoryName")
    public String editCategoryName(@RequestParam(name = "id") Long id,
                                   @RequestParam(name = "name") String name){
        String redirect="redirect:/category/pageCategoryEdit/"+id+"?";
        redirect+=categoryService.editCategoryName(id, name);
        return redirect;
    }

    @PostMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam(name = "id") Long id){
        String redirect="redirect:/category/pageCategoriesList?";
        redirect+=categoryService.deleteCategory(id);
        return redirect;
    }

    @PostMapping(value = "/restoreCategory")
    public String restoreCategory(@RequestParam(name = "id") Long id){
        String redirect="redirect:/category/pageCategoriesList?";
        redirect+=categoryService.restoreCategory(id);
        return redirect;
    }

}
