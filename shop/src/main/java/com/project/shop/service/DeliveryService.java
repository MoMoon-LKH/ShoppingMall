package com.project.shop.service;

import com.project.shop.domain.Delivery;
import com.project.shop.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;


    @Transactional
    public Long save(Delivery delivery) {
        deliveryRepository.save(delivery);
        return delivery.getId();
    }

    @Transactional
    public void delete(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    public List<Delivery> findAllByMemberId(Long memberId) {
        return deliveryRepository.findAllByMember_IdOrderByCreateDateAsc(memberId);
    }
}
