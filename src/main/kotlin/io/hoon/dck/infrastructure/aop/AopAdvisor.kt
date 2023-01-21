package io.hoon.dck.infrastructure.aop

import io.hoon.dck.infrastructure.event.DataCollectEvent
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Aspect
@Component
class AopAdvisor(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @Around("@annotation(PublishDataCollectEvent)")
    fun publishDataCollectEvent(joinPoint: ProceedingJoinPoint): Any = kotlin.run {
        applicationEventPublisher.publishEvent(DataCollectEvent())
        joinPoint.proceed()
    }
}