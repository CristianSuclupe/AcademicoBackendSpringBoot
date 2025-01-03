package com.academico.backendjava.services;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.academico.backendjava.dtos.AcademicProductByClassDto;
import com.academico.backendjava.dtos.HttpResponseDto;
import com.academico.backendjava.entities.AcademicProduct;
import com.academico.backendjava.exceptions.HttpException;
import com.academico.backendjava.repositories.AcademicProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcademicProductService implements IAcademicProductService{

    private final AcademicProductRepository academicProductRepository;

    @Override
    public HttpResponseDto<List<AcademicProductByClassDto>> ListByClass(Long classId) {
        try {
            List<AcademicProduct> academicProducts = academicProductRepository.listByClass(classId, new Date());
            if(academicProducts.isEmpty()) throw new HttpException(HttpStatus.NOT_FOUND, "No se encontraron productos académicos para esta clase");
            List<AcademicProductByClassDto> result = academicProducts.stream()
                .map(aux -> AcademicProductByClassDto.builder()
                    .academicProductId(aux.getAcademicProductId())
                    .deadLine(aux.getDeadLine())
                    .name(aux.getName())
                    .percentage(aux.getPercentage())
                .build())
            .toList();
            return HttpResponseDto.<List<AcademicProductByClassDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(result)
                .build();
        }
        catch(HttpException e){
            throw e;
        }
        catch(Exception e){
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
