package io.quarkus.poc.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.poc.adapter.out.database.entity.BrandInformationEntity;
import io.quarkus.poc.domain.port.out.BrandInformationPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class BrandInformationRepository implements
        PanacheRepositoryBase<BrandInformationEntity, UUID>, BrandInformationPort {
}
