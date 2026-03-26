package com.example.evsalesmanagement.service;

import com.example.evsalesmanagement.dto.vehicletype.CreateVehicleTypeDTO;
import com.example.evsalesmanagement.dto.vehicletype.UpdateVehicleTypeDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeRequestDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeResponseDTO;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeResponseDTO_v2;
import com.example.evsalesmanagement.dto.vehicletype.VehicleTypeSummaryDTO;
import com.example.evsalesmanagement.exception.ResourceNotFoundException;
import com.example.evsalesmanagement.model.VehicleCategory;
import com.example.evsalesmanagement.model.VehicleType;
import com.example.evsalesmanagement.model.VehicleTypeDetail;
import com.example.evsalesmanagement.repository.CategoryRepository;
import com.example.evsalesmanagement.repository.VehicleTypeRepository;
import com.example.evsalesmanagement.utils.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleTypeService {
        @Autowired
        private VehicleTypeRepository vehicleTypeRepository;

        @Autowired
        private CategoryRepository vehicleCategoryRepository;

        @Transactional
        public ApiResponse<VehicleTypeResponseDTO_v2> createVehicleType_v2(
                        CreateVehicleTypeDTO createVehicleTypeDTO) {
                VehicleType vehicleType = new VehicleType();
                vehicleType.setVehicleTypeName(createVehicleTypeDTO.getVehicleTypeName());
                vehicleType.setManufactureYear(createVehicleTypeDTO.getManufactureYear());
                vehicleType.setDescription(createVehicleTypeDTO.getDescription());

                List<VehicleTypeDetail> details = buildVehicleTypeDetails(createVehicleTypeDTO, vehicleType);

                vehicleType.setVehicleTypeDetails(details);

                VehicleType savedVehicleType = vehicleTypeRepository.save(vehicleType);

                // Liên kết với các danh mục xe
                List<VehicleCategory> categories = createVehicleTypeDTO.getVehicleCategoryIds() == null
                                ? List.of()
                                : vehicleCategoryRepository.findAllById(createVehicleTypeDTO.getVehicleCategoryIds());

                for (VehicleCategory category : categories) {
                        category.getVehicleTypes().add(savedVehicleType);
                }

                vehicleCategoryRepository.saveAll(categories);

                return new ApiResponse<>(true, null, mapToVehicleTypeResponseV2(savedVehicleType));
        }

        @Transactional
        public ApiResponse<Page<VehicleTypeSummaryDTO>> getAllVehicleType_v2(Pageable pageable) {
                Page<VehicleTypeSummaryDTO> vehicleTypeSummaryPage = vehicleTypeRepository.findAll(pageable)
                                .map(VehicleTypeSummaryDTO::new);
                return new ApiResponse<>(true, null, vehicleTypeSummaryPage);
        }

        @Transactional
        public ApiResponse<VehicleTypeResponseDTO_v2> getVehicleTypeById_v2(Integer vehicleTypeId) {
                VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy loại xe với id :" + vehicleTypeId));
                return new ApiResponse<>(true, null, mapToVehicleTypeResponseV2(vehicleType));
        }

        private List<VehicleTypeDetail> buildVehicleTypeDetails(CreateVehicleTypeDTO createVehicleTypeDTO,
                        VehicleType vehicleType) {
                if (createVehicleTypeDTO.getVehicleTypeDetails() == null) {
                        return List.of();
                }

                return createVehicleTypeDTO.getVehicleTypeDetails()
                                .stream()
                                .map(createVehicleTypeDetail -> {
                                        VehicleTypeDetail vehicleTypeDetail = new VehicleTypeDetail();
                                        vehicleTypeDetail.setVehicleImage(createVehicleTypeDetail.getVehicleImage());
                                        vehicleTypeDetail.setConfiguration(createVehicleTypeDetail.getConfiguration());
                                        vehicleTypeDetail.setColor(createVehicleTypeDetail.getColor());
                                        vehicleTypeDetail.setVersion(createVehicleTypeDetail.getVersion());
                                        vehicleTypeDetail.setFeatures(createVehicleTypeDetail.getFeatures());
                                        vehicleTypeDetail.setPrice(createVehicleTypeDetail.getPrice());
                                        vehicleTypeDetail.setVehicleType(vehicleType);
                                        return vehicleTypeDetail;
                                })
                                .collect(Collectors.toList());
        }

        private VehicleTypeResponseDTO_v2 mapToVehicleTypeResponseV2(VehicleType vehicleType) {
                VehicleTypeResponseDTO_v2 responseDTO = new VehicleTypeResponseDTO_v2();
                responseDTO.setVehicleTypeId(vehicleType.getVehicleTypeId());
                responseDTO.setVehicleTypeName(vehicleType.getVehicleTypeName());
                responseDTO.setManufactureYear(vehicleType.getManufactureYear());
                responseDTO.setDescription(vehicleType.getDescription());

                List<VehicleTypeDetail> vehicleTypeDetails = vehicleType.getVehicleTypeDetails() == null
                                ? List.of()
                                : vehicleType.getVehicleTypeDetails();

                List<VehicleTypeResponseDTO_v2.VehicleTypeDetailResponseDTO> detailResponseDTOs = vehicleTypeDetails
                                .stream()
                                .map(detail -> {
                                        VehicleTypeResponseDTO_v2.VehicleTypeDetailResponseDTO detailResponseDTO = new VehicleTypeResponseDTO_v2.VehicleTypeDetailResponseDTO();
                                        detailResponseDTO.setVehicleTypeDetailId(detail.getVehicleTypeDetailId());
                                        detailResponseDTO.setVehicleImage(detail.getVehicleImage());
                                        detailResponseDTO.setConfiguration(detail.getConfiguration());
                                        detailResponseDTO.setColor(detail.getColor());
                                        detailResponseDTO.setVersion(detail.getVersion());
                                        detailResponseDTO.setFeatures(detail.getFeatures());
                                        detailResponseDTO.setPrice(detail.getPrice());
                                        return detailResponseDTO;
                                })
                                .collect(Collectors.toList());

                responseDTO.setVehicleTypeDetails(detailResponseDTOs);
                return responseDTO;
        }

        @Transactional
        public ApiResponse<VehicleTypeResponseDTO_v2> updateVehicleType_v2(
                        Integer vehicleTypeId,
                        UpdateVehicleTypeDTO updateVehicleTypeDTO) {
                VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Không tìm thấy loại xe với id:" + vehicleTypeId));

                vehicleType.setVehicleTypeName(updateVehicleTypeDTO.getVehicleTypeName());
                vehicleType.setManufactureYear(updateVehicleTypeDTO.getManufactureYear());
                vehicleType.setDescription(updateVehicleTypeDTO.getDescription());

                // Cập nhật chi tiết loại xe đã tồn tại ( có vehicleTypeDetailId )
                Map<Integer, UpdateVehicleTypeDTO.UpdateVehicleTypeDetailDTO> updateDetailMap = updateVehicleTypeDTO
                                .getVehicleTypeDetails()
                                .stream()
                                .filter((vehicleTypeDetail) -> vehicleTypeDetail.getVehicleTypeDetailId() != null)
                                .collect(Collectors.toMap(
                                                UpdateVehicleTypeDTO.UpdateVehicleTypeDetailDTO::getVehicleTypeDetailId,
                                                detail -> detail));
                List<VehicleTypeDetail> vehicleTypeDetailsRemote = new ArrayList<>();

                for (VehicleTypeDetail existingDetail : vehicleType.getVehicleTypeDetails()) {
                        UpdateVehicleTypeDTO.UpdateVehicleTypeDetailDTO updateDetail = updateDetailMap
                                        .get(existingDetail.getVehicleTypeDetailId());
                        if (updateDetail != null) {
                                existingDetail.setVehicleImage(updateDetail.getVehicleImage());
                                existingDetail.setConfiguration(updateDetail.getConfiguration());
                                existingDetail.setColor(updateDetail.getColor());
                                existingDetail.setVersion(updateDetail.getVersion());
                                existingDetail.setFeatures(updateDetail.getFeatures());
                                existingDetail.setPrice(updateDetail.getPrice());
                        } else {
                                // Nếu chi tiết không có trong request, xóa nó
                                // existingDetail.setVehicleType(null);
                                vehicleTypeDetailsRemote.add(existingDetail);
                        }
                }

                vehicleType.getVehicleTypeDetails().removeAll(vehicleTypeDetailsRemote);

                // Thêm chi tiết loại xe mới ( không có vehicleTypeDetailId )
                List<VehicleTypeDetail> newDetails = updateVehicleTypeDTO.getVehicleTypeDetails()
                                .stream()
                                .filter((vehicleTypeDetail) -> vehicleTypeDetail.getVehicleTypeDetailId() == null)
                                .map((vehicleTypeDetail) -> {
                                        VehicleTypeDetail detail = new VehicleTypeDetail();
                                        detail.setVehicleImage(vehicleTypeDetail.getVehicleImage());
                                        detail.setConfiguration(vehicleTypeDetail.getConfiguration());
                                        detail.setColor(vehicleTypeDetail.getColor());
                                        detail.setVersion(vehicleTypeDetail.getVersion());
                                        detail.setFeatures(vehicleTypeDetail.getFeatures());
                                        detail.setPrice(vehicleTypeDetail.getPrice());
                                        detail.setVehicleType(vehicleType);
                                        return detail;
                                })
                                .collect(Collectors.toList());
                vehicleType.getVehicleTypeDetails().addAll(newDetails);

                // Cập nhật liên kết với các danh mục xe, nếu thêm mới hoặc xóa bớt
                // [1, 2, 3] -> [2, 3, 4]
                List<VehicleCategory> currentCategories = vehicleCategoryRepository
                                .findByVehicleTypes_VehicleTypeId(vehicleType.getVehicleTypeId());

                currentCategories.removeIf(
                                (currentCategory) -> !currentCategory.getVehicleTypes().contains(vehicleType));

                vehicleCategoryRepository.saveAll(currentCategories);

                List<Integer> newCategoryIds = updateVehicleTypeDTO.getCategoryList() == null
                                ? List.of()
                                : updateVehicleTypeDTO.getCategoryList();

                List<VehicleCategory> newCategories = vehicleCategoryRepository.findAllById(newCategoryIds);

                for (VehicleCategory newCategory : newCategories) {
                        newCategory.getVehicleTypes().add(vehicleType);
                }
                vehicleCategoryRepository.saveAll(newCategories);

                VehicleType updatedVehicleType = vehicleTypeRepository.save(vehicleType);

                return new ApiResponse<>(true, null, mapToVehicleTypeResponseV2(updatedVehicleType));
        }
}
