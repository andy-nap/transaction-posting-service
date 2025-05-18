package com.posting.service.application.uow;

@FunctionalInterface
public interface UnitOfWork {
    <T> T execute(Work<T> work);

    @FunctionalInterface
    interface Work<T> {
        T run();
    }
}