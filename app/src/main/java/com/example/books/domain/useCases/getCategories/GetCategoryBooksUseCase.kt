package com.example.books.domain.useCases.getCategories

import com.example.books.common.Ressource
import com.example.books.data.Remote.DTO.toCategories
import com.example.books.data.Remote.DTO.toCategoryBooks
import com.example.books.data.Repository.BookRepositoryImpl
import com.example.books.domain.model.Category
import com.example.books.domain.model.CategoryBooks
import com.example.books.domain.repository.BookRepository
import com.example.books.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoryBooksUseCase @Inject constructor(
    private val repository: CategoryRepository,
) {
    operator fun invoke(category_name:String): Flow<Ressource<CategoryBooks>> = flow {
        try {
            emit(Ressource.Loading<CategoryBooks>())
            val categoryBooks = repository.getCategoryBooks(category_name).toCategoryBooks()
            emit(Ressource.Success<CategoryBooks>(categoryBooks))
        } catch(e: HttpException) {
            emit(Ressource.Error<CategoryBooks>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Ressource.Error<CategoryBooks>("Couldn't reach server. Check your internet connection."))
        }
    }
}