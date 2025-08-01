package com.techcognics.erpapp.di.module


import android.content.Context
import com.techcognics.erpapp.data.network.api_service.AppApiService
import com.techcognics.erpapp.data.network.interceptor.ApiInterceptor
import com.techcognics.erpapp.data.repository.AppRepositoryImpl
import com.techcognics.erpapp.data.repository.CompanyRepositoryImpl
import com.techcognics.erpapp.data.repository.SalesRepositoryImpl
import com.techcognics.erpapp.data.repository.UserRepositoryImpl
import com.techcognics.erpapp.data.repository.UserSessionRepositoryImpl
import com.techcognics.erpapp.data.session.SessionManager
import com.techcognics.erpapp.domain.repository.AppRepository
import com.techcognics.erpapp.domain.repository.CompanyRepository
import com.techcognics.erpapp.domain.repository.SalseRepository
import com.techcognics.erpapp.domain.repository.UserRepository
import com.techcognics.erpapp.domain.repository.UserSessionRepository
import com.techcognics.erpapp.domain.usecase.ClearSessionUseCase
import com.techcognics.erpapp.domain.usecase.GetCityUseCase
import com.techcognics.erpapp.domain.usecase.GetCountryUseCase
import com.techcognics.erpapp.domain.usecase.GetLeadSourceUseCase
import com.techcognics.erpapp.domain.usecase.GetStateUseCase
import com.techcognics.erpapp.domain.usecase.GetTokenUseCase
import com.techcognics.erpapp.domain.usecase.LoginUseCase
import com.techcognics.erpapp.domain.usecase.RegistrationUseCase
import com.techcognics.erpapp.domain.usecase.SaveTokenUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.AllTotalAmountsOfSalesUseCase
import com.techcognics.erpapp.domain.usecase.sales_dashboard_usecase.GetSalesYearUseCase
import com.techcognics.erpapp.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideApiInterceptor(sessionManager: SessionManager): ApiInterceptor =
        ApiInterceptor(sessionManager)

    @Provides
    fun provideAppApiService(
        gsonFactory: GsonConverterFactory, apiInterceptor: ApiInterceptor
    ): AppApiService {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL).client(
            OkHttpClient.Builder().addInterceptor(apiInterceptor).addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        ).addConverterFactory(gsonFactory).build().create(AppApiService::class.java)
    }


    @Provides
    fun provideUserRepository(appApiService: AppApiService): UserRepository =
        UserRepositoryImpl(appApiService)

    @Provides
    fun provideCompanyRepository(appApiService: AppApiService): CompanyRepository =
        CompanyRepositoryImpl(appApiService)

    @Provides
    fun provideSalesRepository(appApiService: AppApiService): SalseRepository =
        SalesRepositoryImpl(appApiService)

    @Provides
    fun provideAppRepository(appApiService: AppApiService): AppRepository =
        AppRepositoryImpl(appApiService)

    @Provides
    fun provideLoginUserCase(userRepository: UserRepository) = LoginUseCase(userRepository)

    @Provides
    fun provideRegistrationUserCase(userRepository: UserRepository) =
        RegistrationUseCase(userRepository)

    @Provides
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager =
        SessionManager(context)

    @Provides
    fun provideUserSessionRepository(sessionManager: SessionManager): UserSessionRepository =
        UserSessionRepositoryImpl(sessionManager)

    @Provides
    fun provideSaveTokenUseCase(userSessionRepository: UserSessionRepository): SaveTokenUseCase =
        SaveTokenUseCase(userSessionRepository)

    @Provides
    fun provideGetTokenUseCase(userSessionRepository: UserSessionRepository): GetTokenUseCase =
        GetTokenUseCase(userSessionRepository)

    @Provides
    fun provideClearSessionUseCase(userSessionRepository: UserSessionRepository): ClearSessionUseCase =
        ClearSessionUseCase(userSessionRepository)

    @Provides
    fun provideAllTotalAmountsOfSalesUseCase(salseRepository: SalseRepository): AllTotalAmountsOfSalesUseCase =
        AllTotalAmountsOfSalesUseCase(salseRepository = salseRepository)

    @Provides
    fun provideGetSalesYearUseCase(salseRepository: SalseRepository): GetSalesYearUseCase =
        GetSalesYearUseCase(salseRepository = salseRepository)

    @Provides
    fun provideGetCountryUseCase(appRepository: AppRepository) = GetCountryUseCase(appRepository)

    @Provides
    fun provideGetStateUseCase(appRepository: AppRepository) = GetStateUseCase(appRepository)

    @Provides
    fun provideGetCityUseCase(appRepository: AppRepository) = GetCityUseCase(appRepository)

    @Provides
    fun provideGetLeadSourceUseCase(appRepository: AppRepository) =
        GetLeadSourceUseCase(appRepository)


}