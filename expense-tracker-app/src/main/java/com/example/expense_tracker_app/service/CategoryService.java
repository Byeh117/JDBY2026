package com.example.expense_tracker_app.service;

import com.example.expense_tracker_app.dto.CategoryForm;
import com.example.expense_tracker_app.entity.Category;
import com.example.expense_tracker_app.entity.CategoryType;
import com.example.expense_tracker_app.repository.CategoryRepository;
import com.example.expense_tracker_app.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    @Transactional()
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByName();
    }

    @Transactional()
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
    }

    public List<Category> findByType(CategoryType type) {
        return categoryRepository.findByType(type);
    }

    public Category create(CategoryForm form) {
        Category category = new Category();
        category.setName(form.getName());
        category.setType(CategoryType.valueOf(form.getType()));
        return categoryRepository.save(category);
    }

    public Category update(Long id, CategoryForm form) {
        Category category = findById(id);
        category.setName(form.getName());
        category.setType(CategoryType.valueOf(form.getType()));
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found: " + id);
        }
        if (transactionRepository.countByCategory_Id(id) > 0) {
            throw new EntityNotFoundException("Category id " + id + " cannot be deleted because it has linked transactions");
        }
        categoryRepository.deleteById(id);
    }
}
