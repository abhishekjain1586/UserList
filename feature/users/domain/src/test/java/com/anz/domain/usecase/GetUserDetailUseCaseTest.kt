package com.anz.domain.usecase

import com.anz.common.result.Result
import com.anz.domain.model.User
import com.anz.domain.repository.UserRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetUserDetailUseCaseTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var getUserDetailUseCase: GetUserDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getUserDetailUseCase = GetUserDetailUseCase(userRepository)
    }

    @Test
    fun `invoke should return success result when repository returns success`() = runTest {
        val userId = 1
        val user = User(
            id = userId,
            name = "John Doe",
            company = "ABC",
            username = "johndoe",
            email = "john@example.com",
            address = "123 St",
            zip = "12345",
            state = "State",
            country = "Country",
            phone = "123456789",
            photo = "url"
        )
        val expectedResult = Result.Success(user)
        whenever(userRepository.getUserById(userId)).thenReturn(expectedResult)

        val result = getUserDetailUseCase(userId)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `invoke should return error result when repository returns error`() = runTest {
        val userId = 1
        val errorMessage = "Not Found"
        val expectedResult = Result.Error(errorMessage)
        whenever(userRepository.getUserById(userId)).thenReturn(expectedResult)

        val result = getUserDetailUseCase(userId)

        assertEquals(expectedResult, result)
    }
}
