package com.techcognics.erpapp.di.module


import com.techcognics.erpapp.data.network.AppApiService
import com.techcognics.erpapp.data.repositorys.UserRepository
import com.techcognics.erpapp.data.repositorys.UserRepositoryImpl
import com.techcognics.erpapp.domain.usecase.LoginUseCase
import com.techcognics.erpapp.domain.usecase.RegistrationUseCase
import com.techcognics.erpapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()



    @Provides
    fun provideAppApiService(
        gsonFactory: GsonConverterFactory
    ): AppApiService {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(gsonFactory)
            .build().create(AppApiService::class.java)
    }


    @Provides
    fun provideUserRepository(appApiService: AppApiService): UserRepository =
        UserRepositoryImpl(appApiService)


    @Provides
    fun provideLoginUserCase(userRepository: UserRepository) = LoginUseCase(userRepository)

    @Provides
    fun provideRegistrationUserCase(userRepository: UserRepository) = RegistrationUseCase(userRepository)
}