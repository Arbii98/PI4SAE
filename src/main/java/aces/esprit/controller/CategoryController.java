package aces.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aces.esprit.entity.Category;
import aces.esprit.service.ICategoryService;

@RestController
public class CategoryController {

	@Autowired
	ICategoryService iCategoryService;

	public CategoryController() {
	}

	public CategoryController(ICategoryService iCategoryService) {
		this.iCategoryService = iCategoryService;
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/addCategory
	 */
	/*
	 {
    "idCategory": 3,
    "nameCategory": "cat3",
    "descriptionCategory": "cat3"
	 }
	 */
	@PostMapping("/addCategory")
	@ResponseBody
	public Category addCategory(@RequestBody Category category) {
		iCategoryService.addCategory(category);
		return category;
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getCategoryById/1
	 */
	@GetMapping(value = "getCategoryById/{idCategory}")
	@ResponseBody
	public Category getCategoryById(@PathVariable("idCategory") int idCat) {
		return iCategoryService.getCategoryById(idCat);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/getAllCategories
	 */
	@GetMapping(value = "/getAllCategories")
	@ResponseBody
	public List<Category> getAllCategories() {
		return iCategoryService.getAllCategories();
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/updateCategory/1
	 */
	@PutMapping("{idCat}")
	public void updateCategory(@RequestBody Category category, @PathVariable int idCat) {
		iCategoryService.updateCategory(category, idCat);
	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/deleteCategoryById/1
	 */
	@DeleteMapping("/deleteCategoryById/{idcat}")
	@ResponseBody
	public void deleteCategoryById(@PathVariable("idcat") int idCat) {
		iCategoryService.deleteCategoryById(idCat);

	}

	/*
	 * http://localhost:8081/SpringMVC/servlet/deleteAllCategories
	 */
	@DeleteMapping("/deleteAllCategories")
	@ResponseBody
	public void deleteAllCategories() {
		iCategoryService.deleteAllCategories();

	}
}
