package com.borsibaar.controller;

import com.borsibaar.dto.*;
import com.borsibaar.entity.User;
import com.borsibaar.service.InventoryService;
import com.borsibaar.util.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private static final Logger logger = LogManager.getLogger(InventoryController.class);


    @GetMapping
    public List<InventoryResponseDto> getOrganizationInventory(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long organizationId) {
        // If organizationId is provided, use it (for public access)
        // Otherwise, get from authenticated user
        Long orgId;
        if (organizationId != null) {
            orgId = organizationId;
        } else {
            User user = SecurityUtils.getCurrentUser();
            orgId = user.getOrganizationId();
        }
        return inventoryService.getByOrganization(orgId, categoryId);
    }

    @GetMapping("/product/{productId}")
    public InventoryResponseDto getProductInventory(@PathVariable Long productId) {
        User user = SecurityUtils.getCurrentUser();
        return inventoryService.getByProductAndOrganization(productId, user.getOrganizationId());
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponseDto addStock(@RequestBody @Valid AddStockRequestDto request) {
        User user = SecurityUtils.getCurrentUser();
        logger.debug("Received request: {}", request);
        logger.debug("ProductId: {}", request.productId());
        logger.debug("Quantity: {}", request.quantity());

        return inventoryService.addStock(request, user.getId(), user.getOrganizationId());
    }

    @PostMapping("/remove")
    public InventoryResponseDto removeStock(@RequestBody @Valid RemoveStockRequestDto request) {
        User user = SecurityUtils.getCurrentUser();
        return inventoryService.removeStock(request, user.getId(), user.getOrganizationId());
    }

    @PostMapping("/adjust")
    public InventoryResponseDto adjustStock(@RequestBody @Valid AdjustStockRequestDto request) {
        User user = SecurityUtils.getCurrentUser();
        return inventoryService.adjustStock(request, user.getId(), user.getOrganizationId());
    }

    @GetMapping("/product/{productId}/history")
    public List<InventoryTransactionResponseDto> getTransactionHistory(@PathVariable Long productId) {
        User user = SecurityUtils.getCurrentUser();
        return inventoryService.getTransactionHistory(productId, user.getOrganizationId());
    }

    @GetMapping("/sales-stats")
    public List<UserSalesStatsResponseDto> getUserSalesStats() {
        User user = SecurityUtils.getCurrentUser();
        return inventoryService.getUserSalesStats(user.getOrganizationId());
    }

    @GetMapping("/station-sales-stats")
    public List<StationSalesStatsResponseDto> getStationSalesStats() {
        User user = SecurityUtils.getCurrentUser();
        return inventoryService.getStationSalesStats(user.getOrganizationId());
    }
}
