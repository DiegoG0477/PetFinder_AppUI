package com.project.petfinder.register.data.repository

//import com.project.petfinder.register.data.source.AuthApi
//import com.project.petfinder.register.data.model.RegisterRequest
//import com.project.petfinder.register.domain.model.RegisterResult
//import com.project.petfinder.register.domain.repository.RegisterRepository
//import javax.inject.Inject
//
//class RegisterRepositoryImpl @Inject constructor(
//    private val api: AuthApi
//) : RegisterRepository {
//
//    override suspend fun register(
//        name: String,
//        email: String,
//        password: String,
//        municipalityId: String
//    ): RegisterResult {
//        return try {
//            val response = api.register(
//                RegisterRequest(
//                    name = name,
//                    email = email,
//                    password = password,
//                    municipalityId = municipalityId
//                )
//            )
//            RegisterResult(
//                isSuccess = response.success,
//                errorMessage = response.message,
//                user = response.user?.toDomain()
//            )
//        } catch (e: Exception) {
//            RegisterResult(
//                isSuccess = false,
//                errorMessage = e.message ?: "Error desconocido durante el registro"
//            )
//        }
//    }
//}