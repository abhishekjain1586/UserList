package com.anz.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.anz.common.result.Result
import com.anz.domain.model.User
import com.anz.domain.usecase.GetUserDetailUseCase
import com.anz.presentation.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var getUserDetailUseCase: GetUserDetailUseCase

    private lateinit var viewModel: UserDetailViewModel
    private val userId = 1

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `init should fetch user detail and update uiState with success`() = runTest {
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
        whenever(getUserDetailUseCase(userId)).thenReturn(Result.Success(user))
        
        val savedStateHandle = SavedStateHandle(mapOf("userId" to userId))

        viewModel = UserDetailViewModel(savedStateHandle, getUserDetailUseCase)

        val state = viewModel.uiState.value
        assertFalse(state.loading)
        assertEquals(user, state.user)
    }

    @Test
    fun `init should fetch user detail and has no user on failure`() = runTest {
        val errorMessage = "Not Found"
        whenever(getUserDetailUseCase(userId)).thenReturn(Result.Error(errorMessage))
        
        val savedStateHandle = SavedStateHandle(mapOf("userId" to userId))

        viewModel = UserDetailViewModel(savedStateHandle, getUserDetailUseCase)

        val state = viewModel.uiState.value
        assertFalse(state.loading)
        assertTrue(state.user == null)
    }
}
