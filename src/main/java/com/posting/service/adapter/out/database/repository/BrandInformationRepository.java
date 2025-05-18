package com.posting.service.adapter.out.database.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import com.posting.service.adapter.out.database.entity.BrandInformationEntity;
import com.posting.service.domain.port.out.BrandInformationPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class BrandInformationRepository implements
        PanacheRepositoryBase<BrandInformationEntity, UUID>, BrandInformationPort {
}
