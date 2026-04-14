package com.anz.presentation.ui.userlist

import com.anz.common.result.Result
import com.anz.domain.model.User
import com.anz.domain.usecase.GetUserListUseCase
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
class UserListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var getUserListUseCase: GetUserListUseCase

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `init should fetch users and update uiState with success`() = runTest {
        val users = listOf(
            User(
                id = 1,
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
        )
        whenever(getUserListUseCase()).thenReturn(Result.Success(users))

        viewModel = UserListViewModel(getUserListUseCase)

        val state = viewModel.uiState.value
        assertFalse(state.loading)
        assertEquals(users, state.users)
    }

    @Test
    fun `init should fetch users and has no user list on failure`() = runTest {
        val errorMessage = "Network Error"
        whenever(getUserListUseCase()).thenReturn(Result.Error(errorMessage))

        viewModel = UserListViewModel(getUserListUseCase)

        val state = viewModel.uiState.value
        assertFalse(state.loading)
        assertTrue(state.users.isEmpty())
    }
}
