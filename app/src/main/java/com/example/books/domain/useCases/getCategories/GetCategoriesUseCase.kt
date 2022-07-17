package com.example.books.domain.useCases.getCategories

import android.util.Log
import com.example.books.common.Ressource
import com.example.books.data.Remote.DTO.toCategories
import com.example.books.domain.model.Category
import com.example.books.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<Ressource<List<Category>>> = flow {
        try {
            emit(Ressource.Loading<List<Category>>())
            val categories = repository.getCategories().toCategories()
            emit(Ressource.Success<List<Category>>(categories))
        } catch(e: HttpException) {
            emit(Ressource.Error<List<Category>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Ressource.Error<List<Category>>("Couldn't reach server. Check your internet connection."))
        }
    }
}