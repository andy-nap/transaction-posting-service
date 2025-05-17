package io.quarkus.poc.application.uow;

@FunctionalInterface
public interface UnitOfWork {
    <T> T execute(Work<T> work);

    @FunctionalInterface
    interface Work<T> {
        T run();
    }
}