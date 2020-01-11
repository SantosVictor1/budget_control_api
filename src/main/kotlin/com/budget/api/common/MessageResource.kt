package com.budget.api.common

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * Created by Victor Santos on 11/01/2020
 */
@Component
class MessageResource(
    val messageSource: MessageSource
) {
    private lateinit var acessor: MessageSourceAccessor

    @PostConstruct
    private fun init() {
        acessor = MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale())
    }

    fun getMessage(code: String): String {
        return acessor.getMessage(code, LocaleContextHolder.getLocale())
    }
}