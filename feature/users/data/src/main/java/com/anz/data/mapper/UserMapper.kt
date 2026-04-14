package com.anz.data.mapper

import com.anz.data.model.UserDto
import com.anz.domain.model.User

fun UserDto.toDomain() = User(
    id = id,
    name = name,
    company = company,
    username = username,
    email = email,
    address = address,
    zip = zip,
    state = state,
    country = country,
    phone = phone,
    photo = photo
)

fun List<UserDto>.toDomain(): List<User> = map { it.toDomain() }