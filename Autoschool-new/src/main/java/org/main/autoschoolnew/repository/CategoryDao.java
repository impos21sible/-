package org.main.autoschoolnew.repository;

import org.main.autoschoolnew.models.Category;

public class CategoryDao extends BaseDao<Category> {
    public CategoryDao() {
        super(Category.class);
    }
}